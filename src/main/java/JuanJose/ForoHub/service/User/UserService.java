package JuanJose.ForoHub.service.User;

import JuanJose.ForoHub.dto.Topic.TopicDetailsDTO;
import JuanJose.ForoHub.dto.User.*;
import JuanJose.ForoHub.model.ForumUser;
import JuanJose.ForoHub.model.Profile;
import JuanJose.ForoHub.repository.ProfileRepository;
import JuanJose.ForoHub.repository.UserRepository;
import JuanJose.ForoHub.service.Profile.ProfileValidator;
import JuanJose.ForoHub.service.Topic.TopicService;
import JuanJose.ForoHub.utils.ConverterData;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserValidator userValidator;
    private final ProfileValidator profileValidator;
    private final ProfileRepository profileRepository;
    private final TopicService topicService;

    public UserService(UserRepository userRepository, UserValidator userValidator,
                       ProfileValidator profileValidator, ProfileRepository profileRepository,
                       TopicService topicService) {
        this.userRepository = userRepository;
        this.userValidator = userValidator;
        this.profileValidator = profileValidator;
        this.profileRepository = profileRepository;
        this.topicService = topicService;
    }

    // Create user
    public DetailsResponseUserDTO createUser(CreateUserDTO data) {
        userValidator.validateUserExistsByEmail(data.email());
        profileValidator.validateExistsById(data.idProfile());
        Profile profile = profileRepository.getReferenceById(data.idProfile());
        ForumUser user = new ForumUser(null, data.name(), data.email(), data.password(), profile);
        userRepository.save(user);
        return new DetailsResponseUserDTO(user);
    }


    // Get user by id
    public ResponseUserDTO getById(@Valid Long id) {
        userValidator.validateExistsById(id);
        ForumUser user = userRepository.getReferenceById(id);
        return new ResponseUserDTO(user);
    }


    // Update user
    public MessageResponseUserDTO updateUser(@Valid Long id, UpdateUserDTO data) {
        userValidator.validateExistsById(id);
        userValidator.validateUserExistsByEmail(data.email());
        ForumUser user = userRepository.getReferenceById(id);
        if (data.idProfile() != null) {
            profileValidator.validateExistsById(data.idProfile());
            Profile profile = profileRepository.getReferenceById(data.idProfile());
            user.updateProfile(profile);
        }
        user.updateBasicInfo(data);
        DetailsResponseUserDTO response = new DetailsResponseUserDTO(user);
        return new MessageResponseUserDTO("User updated successfully", response);
    }


    // Delete user
    public MessageResponseUserDTO deleteUser(@Valid Long id) {
        userValidator.validateExistsById(id);
        ForumUser user = userRepository.getReferenceById(id);
        userRepository.delete(user);
        DetailsResponseUserDTO response = new DetailsResponseUserDTO(user);
        return new MessageResponseUserDTO("User deleted successfully", response);
    }

    // Get all users
    public Page<ResponseUserDTO> getAllUsers(Pageable pageable) {
        Page<ForumUser> userPage = userRepository.findAll(pageable);
        return userPage.map(ConverterData::convertToDTOUser);
    }

    // Get profile-permission by user id
    public UserProfilePermissionDTO getUserProfileAndPermissions(Long id) {
        userValidator.validateExistsById(id);
        ForumUser user = userRepository.findUserProfileAndPermissions(id);
        return new UserProfilePermissionDTO(user);
    }

    public Page<TopicDetailsDTO> GetTopicsByUser(Long id, Pageable pageable) {
        userValidator.validateExistsById(id);
        return topicService.getTopicsByUser(id, pageable);
    }

    public Page<TopicDetailsDTO> GetTopicsByUserResponse(Long id, Pageable pageable) {
        userValidator.validateExistsById(id);
        return topicService.getTopicsByUserResponse(id, pageable);
    }
}
