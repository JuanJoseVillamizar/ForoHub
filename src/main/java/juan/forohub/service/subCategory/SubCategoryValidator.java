package juan.forohub.service.subCategory;

import juan.forohub.service.validations.AbstractEntityValidator;
import juan.forohub.exception.ResourceAlreadyExistException;
import juan.forohub.entities.SubCategory;
import juan.forohub.repository.SubCategoryRepository;
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
