package juan.forohub.service.validations;

public interface EntityValidator<T> {

    void validateExistsById(Long id);
}
