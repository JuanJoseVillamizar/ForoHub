package JuanJose.ForoHub.Service.Category;

import JuanJose.ForoHub.Service.SubCategory.SubCategoryService;
import JuanJose.ForoHub.dto.Category.CreateCategoryDTO;
import JuanJose.ForoHub.dto.Category.DeleteCategoryDTO;
import JuanJose.ForoHub.dto.Category.ResponseCategoryDTO;
import JuanJose.ForoHub.dto.Category.UpdateCategoryDTO;
import JuanJose.ForoHub.dto.SubCategory.ResponseSubCategoryDTO;
import JuanJose.ForoHub.exception.ResourceNotFoundException;
import JuanJose.ForoHub.model.Category;
import JuanJose.ForoHub.repository.CategoryRepository;
import JuanJose.ForoHub.utils.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final SubCategoryService subCategoryService;
    private final CategoryValidator categoryValidator;

    public CategoryService(CategoryRepository categoryRepository,
                           SubCategoryService subCategoryService,
                           CategoryValidator categoryValidator) {
        this.categoryRepository = categoryRepository;
        this.subCategoryService = subCategoryService;
        this.categoryValidator = categoryValidator;
    }


    //Create Category
    public Category createCategory(CreateCategoryDTO data) {
        String formattedName = StringUtils.normalizeName(data.name());
        categoryValidator.validateCategoryExistsByName(formattedName);
        CreateCategoryDTO createCategoryDTO = new CreateCategoryDTO(formattedName);
        Category category = new Category(createCategoryDTO);
        return categoryRepository.save(category);
    }

    //Update Category
    public ResponseCategoryDTO updateCategory(Long id, UpdateCategoryDTO data) {
        String formattedName = StringUtils.normalizeName(data.name());
        categoryValidator.validateCategoryExistsByName(formattedName);
        categoryValidator.validateExistsById(id);
        UpdateCategoryDTO categoryCreateRequest = new UpdateCategoryDTO(formattedName);
        Category category = categoryRepository.getReferenceById(id);
        category.updateCategory(categoryCreateRequest);
        return new ResponseCategoryDTO(category);
    }

    //delete category by id
    public DeleteCategoryDTO deleteCategory(Long id) {
        categoryValidator.validateExistsById(id);
        Category category = categoryRepository.getReferenceById(id);
        categoryRepository.delete(category);
        ResponseCategoryDTO categoryDeleteDTO = new ResponseCategoryDTO(category.getId(), category.getName());
        return new DeleteCategoryDTO("The category was successfully deleted.", categoryDeleteDTO);
    }


    //get category by id
    public ResponseCategoryDTO getById(Long id) {
        Optional<Category> findCategory = categoryRepository.findById(id);
        if (findCategory.isPresent()) {
            Category category = findCategory.get();
            return new ResponseCategoryDTO(category);
        } else {
            throw new ResourceNotFoundException("Category with ID " + id + " was not found.");
        }
    }

    //Get categories
    public Page<ResponseCategoryDTO> getCategoriesPaginated(Pageable pageable) {
        Page<Category> categoryPage = categoryRepository.findAll(pageable);
        return categoryPage.map(this::convertToDTO);
    }

    //Get subCategories by category id
    public Page<ResponseSubCategoryDTO> getSubcategories(Long idCategory, Pageable pageable) {
       categoryValidator.validateExistsById(idCategory);
        return subCategoryService.getSubcategoriesByCategoryId(idCategory,pageable);
    }

    //Converter Category to CategoryResponseDTO
    private ResponseCategoryDTO convertToDTO(Category category) {
        return new ResponseCategoryDTO(category.getId(), category.getName());
    }

}
