package JuanJose.ForoHub.Service.Validations;

public interface EntityValidator<T> {

    void validateExistsById(Long id);
}
