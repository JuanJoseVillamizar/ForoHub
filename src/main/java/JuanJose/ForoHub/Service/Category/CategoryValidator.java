package JuanJose.ForoHub.Service.Category;

import JuanJose.ForoHub.Service.Validations.AbstractEntityValidator;
import JuanJose.ForoHub.exception.ResourceAlreadyExistException;
import JuanJose.ForoHub.model.Category;
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
