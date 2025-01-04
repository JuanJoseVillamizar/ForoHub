package JuanJose.ForoHub.service.Validations;

import JuanJose.ForoHub.exception.ResourceNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

public abstract class AbstractEntityValidator<T> implements EntityValidator<T> {
    private final JpaRepository<T, Long> repository;

    public AbstractEntityValidator(JpaRepository<T, Long> repository) {
        this.repository = repository;
    }

    @Override
    public void validateExistsById(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Resource with ID " + id + " was not found.");
        }
    }

}
