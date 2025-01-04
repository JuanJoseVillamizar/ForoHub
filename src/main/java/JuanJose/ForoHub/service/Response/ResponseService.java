package JuanJose.ForoHub.service.Response;

import JuanJose.ForoHub.dto.Response.*;
import JuanJose.ForoHub.model.ForumUser;
import JuanJose.ForoHub.model.Response;
import JuanJose.ForoHub.model.Topic;
import JuanJose.ForoHub.model.TopicStatus;
import JuanJose.ForoHub.repository.ResponseRepository;
import JuanJose.ForoHub.repository.TopicRepository;
import JuanJose.ForoHub.repository.UserRepository;
import JuanJose.ForoHub.service.Topic.TopicValidator;
import JuanJose.ForoHub.service.User.UserValidator;
import JuanJose.ForoHub.utils.ConverterData;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ResponseService {

    private final ResponseRepository responseRepository;
    private final ResponseValidator responseValidator;
    private final TopicRepository topicRepository;
    private final TopicValidator topicValidator;
    private final UserRepository userRepository;
    private final UserValidator userValidator;

    public ResponseService(
            ResponseRepository responseRepository, ResponseValidator responseValidator, TopicRepository topicRepository,
            TopicValidator topicValidator, UserRepository userRepository, UserValidator userValidator) {
        this.responseRepository = responseRepository;
        this.responseValidator = responseValidator;
        this.topicRepository = topicRepository;
        this.topicValidator = topicValidator;
        this.userRepository = userRepository;
        this.userValidator = userValidator;
    }

    //Create response
    public DetailsResponseDTO createResponse(CreateResponseDTO data) {
        topicValidator.validateExistsById(data.topicId());
        userValidator.validateExistsById(data.userId());
        Topic topic = topicRepository.getReferenceById(data.topicId());
        topic.answeredStatus();
        ForumUser user = userRepository.getReferenceById(data.userId());
        Response response = new Response(null, data.message(), LocalDateTime.now(), false, topic, user);
        responseRepository.save(response);
        return new DetailsResponseDTO(response);
    }

    //Get response by id
    public ResponseDTO getById(@Valid Long id) {
        responseValidator.validateExistsById(id);
        Response response = responseRepository.getReferenceById(id);
        return new ResponseDTO(response);
    }

    //Update response
    public MessageResponseDTO updateResponse(@Valid Long id, @Valid UpdateResponse data) {
        responseValidator.validateExistsById(id);
        Response response = responseRepository.getReferenceById(id);
        response.updateResponse(data);
        DetailsResponseDTO responseDTO = new DetailsResponseDTO(response);
        return new MessageResponseDTO("Response updated successfully", responseDTO);
    }

    //Delete response
    public MessageResponseDTO deleteResponse(@Valid Long id) {
        responseValidator.validateExistsById(id);
        Response response = responseRepository.getReferenceById(id);
        Topic topic = topicRepository.getReferenceById(response.getTopic().getId());
        int responses = responseRepository.countResponsesInATopicById(response.getId());
        if(responses < 1 ){
            topic.unansweredStatus();
        }
        responseRepository.delete(response);
        DetailsResponseDTO responseDTO = new DetailsResponseDTO(response);
        return new MessageResponseDTO("Response deleted successfully", responseDTO);
    }

    //Get all responses
    public Page<ResponseDTO> geAllResponses(Pageable pageable) {
        Page<Response> responses = responseRepository.findAll(pageable);
        return responses.map(ConverterData::convertToResponseDTO);
    }

    //Get responses by topic id
    public Page<ResponseDTO> getResponsesByTopicId(@Valid Long id, TopicStatus status, Pageable pageable) {
        Page<Response> responses = responseRepository.findResponsesByTopicId(id,status,pageable);
        return responses.map(ConverterData::convertToResponseDTO);
    }
}
