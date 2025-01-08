package JuanJose.ForoHub.service.Topic;

import JuanJose.ForoHub.exception.ResourceAlreadyExistException;
import JuanJose.ForoHub.entities.Topic;
import JuanJose.ForoHub.repository.TopicRepository;
import JuanJose.ForoHub.service.Validations.AbstractEntityValidator;
import org.springframework.stereotype.Component;

@Component
public class TopicValidator extends AbstractEntityValidator<Topic> {

    private final TopicRepository topicRepository;

    public TopicValidator(TopicRepository topicRepository) {
        super(topicRepository);
        this.topicRepository = topicRepository;
    }

    public void validationTopicExistsByTitle(String title) {
        if (topicRepository.existsByTitle(title)) {
            throw new ResourceAlreadyExistException("A Topic with the title " + title + " is already exists.");
        }
    }
    public void validationTopicExistsByMessage(String message) {
        if (topicRepository.existsByMessage(message)) {
            throw new ResourceAlreadyExistException("A Topic with the message '" + message + "' is already exists.");
        }
    }
}
