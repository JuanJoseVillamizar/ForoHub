package JuanJose.ForoHub.service.User;

import JuanJose.ForoHub.exception.ResourceAlreadyExistException;
import JuanJose.ForoHub.model.ForumUser;
import JuanJose.ForoHub.repository.UserRepository;
import JuanJose.ForoHub.service.Validations.AbstractEntityValidator;
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
