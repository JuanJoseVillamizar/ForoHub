package JuanJose.ForoHub.service.Topic;

import JuanJose.ForoHub.dto.Response.ResponseDTO;
import JuanJose.ForoHub.dto.Topic.*;
import JuanJose.ForoHub.entities.*;
import JuanJose.ForoHub.repository.CourseRepository;
import JuanJose.ForoHub.repository.TopicRepository;
import JuanJose.ForoHub.repository.UserRepository;
import JuanJose.ForoHub.service.Course.CourseValidator;
import JuanJose.ForoHub.service.Response.ResponseService;
import JuanJose.ForoHub.service.User.CustomUserDetails;
import JuanJose.ForoHub.service.User.UserValidator;
import JuanJose.ForoHub.utils.ConverterData;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Objects;

@Service
public class TopicService {

    private final TopicRepository topicRepository;
    private final TopicValidator topicValidator;
    private final UserValidator userValidator;
    private final UserRepository userRepository;
    private final CourseValidator courseValidator;
    private final CourseRepository courseRepository;
    private final ResponseService responseService;

    public TopicService(TopicRepository topicRepository, TopicValidator topicValidator,
                        UserValidator userValidator, UserRepository userRepository,
                        CourseValidator courseValidator, CourseRepository courseRepository,
                        ResponseService responseService) {
        this.topicRepository = topicRepository;
        this.topicValidator = topicValidator;
        this.userValidator = userValidator;
        this.userRepository = userRepository;
        this.courseValidator = courseValidator;
        this.courseRepository = courseRepository;
        this.responseService = responseService;
    }


    //Create new Topic
    private Topic createNewTopic(CreateTopicDTO data, ForumUser user, Course course) {
        return new Topic(null, data.title(), data.type(), data.message(), LocalDateTime.now(),
                TopicStatus.UNANSWERED, user,  course, null);
    }

    //Create topic
    public ResponseMessageTopicDTO createTopic(CreateTopicDTO data) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        Long userId = userDetails.getId();

        topicValidator.validationTopicExistsByTitle(data.title());
        topicValidator.validationTopicExistsByMessage(data.message());
        userValidator.validateExistsById(userId);
        Course course = null;
        if (data.idCourse() != null) {
            courseValidator.validateExistsById(Long.valueOf(data.idCourse()));
            course = courseRepository.getReferenceById(Long.valueOf(data.idCourse()));
        }
        ForumUser user = userRepository.getReferenceById(userId);
        Topic topic = createNewTopic(data, user, course);
        topicRepository.save(topic);
        ResponseTopicDTO responseTopicDTO = new ResponseTopicDTO(topic);
        return new ResponseMessageTopicDTO("Topic created successfully", responseTopicDTO);
    }

    //Get topic by id
    public ResponseTopicDTO getById(@Valid Long id) {
        topicValidator.validateExistsById(id);
        Topic topic = topicRepository.getReferenceById(id);
        return new ResponseTopicDTO(topic);
    }

    //update topic
    public ResponseMessageTopicDTO updateTopic(@Valid Long id, @Valid UpdateTopicDTO data, Principal principal) throws AccessDeniedException {
        topicValidator.validateExistsById(id);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        Long authenticatedUserId  = userDetails.getId();
        boolean hasEditPermission = userDetails.getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals("EDIT_ANY_TOPIC"));

        Topic topic = topicRepository.getReferenceById(id);
        if (!topic.getAuthor().getId().equals(authenticatedUserId) && !hasEditPermission){
            throw new AccessDeniedException("You do not have permission to edit this topic.");
        }

        topic.updateTopic(data);
        ResponseTopicDTO response = new ResponseTopicDTO(topic);
        return new ResponseMessageTopicDTO("Topic updated successfully", response);
    }

    //Delete topic
    public ResponseMessageTopicDTO deleteTopic(@Valid Long id) {
        topicValidator.validateExistsById(id);
        Topic topic = topicRepository.getReferenceById(id);
        topicRepository.delete(topic);
        ResponseTopicDTO response = new ResponseTopicDTO(topic);
        return new ResponseMessageTopicDTO("Topic deleted successfully", response);
    }


    //Get responses by topic id
    public Page<ResponseDTO> getResponsesByTopicId(@Valid Long id, TopicStatus status, Pageable pageable) {
        topicValidator.validateExistsById(id);
        return responseService.getResponsesByTopicId(id, status, pageable);
    }

    //Get Topics
    public Page<TopicDetailsDTO> getTopics(Long courseId, TopicStatus status, TopicType type, Pageable pageable) {
        return topicRepository.findTopics(courseId, status, type, pageable)
                .map(ConverterData::convertToTopicDetailsDTO);
    }


    // Get topics by subcategory
    public Page<TopicDetailsDTO> findTopicsBySubcategoryId(
            Long subcategoryId, TopicStatus status, TopicType type, Pageable pageable) {
        return topicRepository.findTopicsBySubcategory(subcategoryId, status, type, pageable)
                .map(ConverterData::convertToTopicDetailsDTO);
    }

    // get topics by category
    public Page<TopicDetailsDTO> findTopicsByCategory(
            Long id, TopicStatus status, TopicType type, Pageable pageable) {
        return topicRepository.findTopicsByCategory(id, status, type, pageable)
                .map(ConverterData::convertToTopicDetailsDTO);
    }

    // Get topics by user
    public Page<TopicDetailsDTO> getTopicsByUser(Long id, Pageable pageable) {
        return topicRepository.findTopicsByAuthor(id, pageable)
                .map(ConverterData::convertToTopicDetailsDTO);
    }

    public Page<TopicDetailsDTO> getTopicsByUserResponse(Long id, Pageable pageable) {
        return topicRepository.findTopicsByAuthorResponse(id, pageable)
                .map(ConverterData::convertToTopicDetailsDTO);
    }
}
