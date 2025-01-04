package JuanJose.ForoHub.service.SubCategory;

import JuanJose.ForoHub.dto.Course.ResponseCourseDTO;
import JuanJose.ForoHub.dto.SubCategory.*;
import JuanJose.ForoHub.dto.Topic.TopicDetailsDTO;
import JuanJose.ForoHub.exception.ResourceNotFoundException;
import JuanJose.ForoHub.model.Category;
import JuanJose.ForoHub.model.SubCategory;
import JuanJose.ForoHub.model.TopicStatus;
import JuanJose.ForoHub.model.TopicType;
import JuanJose.ForoHub.repository.CategoryRepository;
import JuanJose.ForoHub.repository.SubCategoryRepository;
import JuanJose.ForoHub.service.Category.CategoryValidator;
import JuanJose.ForoHub.service.Course.CourseService;
import JuanJose.ForoHub.service.Topic.TopicService;
import JuanJose.ForoHub.utils.ConverterData;
import JuanJose.ForoHub.utils.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SubCategoryService {

    private final SubCategoryRepository subCategoryRepository;
    private final CategoryRepository categoryRepository;
    private final CourseService courseService;
    private final SubCategoryValidator subCategoryValidator;
    private final CategoryValidator categoryValidator;
    private final TopicService topicService;

    public SubCategoryService(SubCategoryRepository subCategoryRepository,
                              CategoryRepository categoryRepository,
                              CourseService courseService,
                              SubCategoryValidator subCategoryValidator,
                              CategoryValidator categoryValidator,
                              TopicService topicService
    ) {
        this.subCategoryRepository = subCategoryRepository;
        this.categoryRepository = categoryRepository;
        this.courseService = courseService;
        this.subCategoryValidator = subCategoryValidator;
        this.categoryValidator = categoryValidator;
        this.topicService = topicService;
    }

    // Create subCategory
    public DetailsResponseSubCategoryDTO createSubcategory(CreateSubCategoryDTO data) {
        String formattedName = StringUtils.normalizeName(data.name());
        subCategoryValidator.validateSubCategoryExistsByName(formattedName);
        categoryValidator.validateExistsById(data.idCategory());
        Category category = categoryRepository.getReferenceById(data.idCategory());
        CreateSubCategoryDTO createSubCategoryDTO = new CreateSubCategoryDTO(formattedName, category.getId());
        SubCategory subCategory = new SubCategory(createSubCategoryDTO, category);
        subCategoryRepository.save(subCategory);
        return new DetailsResponseSubCategoryDTO(subCategory);
    }

    // Update Category
    public ResponseSubCategoryDTO updateSubcategory(Long id, UpdateSubcategoryDTO data) {
        String formattedName = StringUtils.normalizeName(data.name());
        subCategoryValidator.validateExistsById(id);
        subCategoryValidator.validateSubCategoryExistsByName(formattedName);
        UpdateSubcategoryDTO updateSubcategoryDTO = new UpdateSubcategoryDTO(formattedName);
        SubCategory subCategory = subCategoryRepository.getReferenceById(id);
        subCategory.updateSubCategory(updateSubcategoryDTO);
        return toResponseSubCategoryDTO(subCategory);
    }

    //Delete Category
    public DeleteSubCategoryDTO deleteSubcategory(Long id) {
        subCategoryValidator.validateExistsById(id);
        SubCategory subCategory = subCategoryRepository.getReferenceById(id);
        subCategoryRepository.delete(subCategory);
        ResponseSubCategoryDTO subCategoryDeleteDTO = toResponseSubCategoryDTO(subCategory);
        return new DeleteSubCategoryDTO("The category was successfully deleted.", subCategoryDeleteDTO);
    }


    // get all subCategories
    public Page<ResponseSubCategoryDTO> getAllSubcategories(Pageable pageable) {
        Page<SubCategory> subCategories = subCategoryRepository.findAll(pageable);
        return subCategories.map(ConverterData::convertToDTOSub);
    }

    //Get subCategories by category id
    public Page<ResponseSubCategoryDTO> getSubcategoriesByCategoryId(Long idCategory, Pageable pageable) {
        Page<SubCategory> subCategoryPage = subCategoryRepository.findSubCategoriesByCategoryId(idCategory, pageable);
        return subCategoryPage.map(ConverterData::convertToDTOSub);
    }

    //Get subcategory by id
    public DetailsResponseSubCategoryDTO getById(Long id) {
        Optional<SubCategory> findSubcategory = subCategoryRepository.findById(id);
        if (findSubcategory.isPresent()) {
            SubCategory subCategory = findSubcategory.get();
            return new DetailsResponseSubCategoryDTO(subCategory);
        } else {
            throw new ResourceNotFoundException("SubCategory with ID " + id + " was not found.");
        }
    }
    //Get courses by subCategory id
    public Page<ResponseCourseDTO> getCourses(Long idSubCategory, Pageable pageable) {
        subCategoryValidator.validateExistsById(idSubCategory);
        return courseService.getCoursesBySubCategoryId(idSubCategory, pageable);
    }

    //get topics by subCategoryId
    public Page<TopicDetailsDTO> getTopicsBySubcategoryId(
            Long SubcategoryId, TopicType type, TopicStatus status, Pageable pageable) {
        System.out.println("Validation : " + SubcategoryId);
        subCategoryValidator.validateExistsById(SubcategoryId);
        return topicService.findTopicsBySubcategoryId(SubcategoryId, status, type, pageable);
    }

    //Converter to ResponseSubCategoryDTO
    private ResponseSubCategoryDTO toResponseSubCategoryDTO(SubCategory subCategory) {
        return new ResponseSubCategoryDTO(
                subCategory.getId(),
                subCategory.getName(),
                subCategory.getCategory().getId()
        );
    }
}
