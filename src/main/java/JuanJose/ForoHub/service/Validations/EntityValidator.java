package JuanJose.ForoHub.service.Validations;

public interface EntityValidator<T> {

    void validateExistsById(Long id);
}
