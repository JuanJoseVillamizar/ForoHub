package JuanJose.ForoHub.service.SubCategory;

import JuanJose.ForoHub.service.Validations.AbstractEntityValidator;
import JuanJose.ForoHub.exception.ResourceAlreadyExistException;
import JuanJose.ForoHub.model.SubCategory;
import JuanJose.ForoHub.repository.SubCategoryRepository;
import org.springframework.stereotype.Component;

@Component
public class SubCategoryValidator extends AbstractEntityValidator<SubCategory> {

    private final SubCategoryRepository subCategoryRepository;

    public SubCategoryValidator(SubCategoryRepository subCategoryRepository) {
        super(subCategoryRepository);
        this.subCategoryRepository=subCategoryRepository;
    }
    public void validateSubCategoryExistsByName(String name) {
        if (subCategoryRepository.existsByName(name)) {
            throw new ResourceAlreadyExistException("A category with the name " + name + " already exists.");
        }
    }
}
