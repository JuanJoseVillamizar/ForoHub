package JuanJose.ForoHub.service.Response;

import JuanJose.ForoHub.entities.Response;
import JuanJose.ForoHub.repository.ResponseRepository;
import JuanJose.ForoHub.service.Validations.AbstractEntityValidator;
import org.springframework.stereotype.Component;

@Component
public class ResponseValidator extends AbstractEntityValidator<Response> {

    private final ResponseRepository repository;

    public ResponseValidator (ResponseRepository repository){
        super(repository);
        this.repository=repository;
    }
}
