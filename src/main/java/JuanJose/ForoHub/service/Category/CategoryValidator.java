package JuanJose.ForoHub.service.Category;

import JuanJose.ForoHub.service.Validations.AbstractEntityValidator;
import JuanJose.ForoHub.exception.ResourceAlreadyExistException;
import JuanJose.ForoHub.entities.Category;
import JuanJose.ForoHub.repository.CategoryRepository;
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
