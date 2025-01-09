package juan.forohub.service.response;

import juan.forohub.entities.Response;
import juan.forohub.repository.ResponseRepository;
import juan.forohub.service.validations.AbstractEntityValidator;
import org.springframework.stereotype.Component;

@Component
public class ResponseValidator extends AbstractEntityValidator<Response> {

    private final ResponseRepository repository;

    public ResponseValidator (ResponseRepository repository){
        super(repository);
        this.repository=repository;
    }
}
