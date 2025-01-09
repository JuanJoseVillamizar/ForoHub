package juan.forohub.service.user;

import juan.forohub.exception.ResourceAlreadyExistException;
import juan.forohub.entities.ForumUser;
import juan.forohub.repository.UserRepository;
import juan.forohub.service.validations.AbstractEntityValidator;
import org.springframework.stereotype.Component;

@Component
public class UserValidator extends AbstractEntityValidator<ForumUser> {

    private final UserRepository userRepository;

    public UserValidator(UserRepository userRepository){
        super(userRepository);
        this.userRepository=userRepository;
    }

    public void validateUserExistsByEmail(String email){
        if(userRepository.existsByEmail(email)){
            throw  new ResourceAlreadyExistException("A user with email " + email + " already exists.");
        }
    }
}
