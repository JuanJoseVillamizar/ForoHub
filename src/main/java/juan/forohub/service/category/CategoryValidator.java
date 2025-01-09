package juan.forohub.service.category;

import juan.forohub.service.validations.AbstractEntityValidator;
import juan.forohub.exception.ResourceAlreadyExistException;
import juan.forohub.entities.Category;
import juan.forohub.repository.CategoryRepository;
import org.springframework.stereotype.Component;

@Component
public class CategoryValidator extends AbstractEntityValidator<Category> {
    private final CategoryRepository categoryRepository;

    public CategoryValidator(CategoryRepository categoryRepository) {
       super(categoryRepository);
       this.categoryRepository=categoryRepository;
    }

    public void validateCategoryExistsByName(String name) {
        if (categoryRepository.existsByName(name)) {
            throw new ResourceAlreadyExistException("A category with the name " + name + " already exists.");
        }
    }
}
