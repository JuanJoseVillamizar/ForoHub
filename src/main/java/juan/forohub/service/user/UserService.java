package juan.forohub.service.user;

import juan.forohub.dto.topic.TopicDetailsDTO;
import juan.forohub.dto.user.*;
import juan.forohub.entities.ForumUser;
import juan.forohub.entities.Profile;
import juan.forohub.exception.ResourceNotFoundException;
import juan.forohub.repository.ProfileRepository;
import juan.forohub.repository.UserRepository;
import juan.forohub.service.profile.ProfileValidator;
import juan.forohub.service.topic.TopicService;
import juan.forohub.utils.ConverterData;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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


    // Get user by id
    public ResponseUserDTO getById(@Valid Long id) {
        userValidator.validateExistsById(id);
        ForumUser user = userRepository.getReferenceById(id);
        return new ResponseUserDTO(user);
    }

    // Create user
    public DetailsResponseUserDTO createUser(@Valid CreateUserDTO data) {
        userValidator.validateUserExistsByEmail(data.email());
        Profile profile = null;
        if(data.idProfile() != null){
            profileValidator.validateExistsById(data.idProfile());
            profile = profileRepository.getReferenceById(data.idProfile());
        }else {
            profile = profileRepository.findByName("USER")
                    .orElseThrow(()-> new ResourceNotFoundException("Error creating user"));
        }
        String passwordEncode = new BCryptPasswordEncoder().encode(data.password());
        ForumUser user = new ForumUser(null, data.name(), data.email(), passwordEncode, profile);
        userRepository.save(user);
        return new DetailsResponseUserDTO(user);

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

    public Page<TopicDetailsDTO> getTopicsByUser(Long id, Pageable pageable) {
        userValidator.validateExistsById(id);
        return topicService.getTopicsByUser(id, pageable);
    }

    public Page<TopicDetailsDTO> getTopicsByUserResponse(Long id, Pageable pageable) {
        userValidator.validateExistsById(id);
        return topicService.getTopicsByUserResponse(id, pageable);
    }
}
