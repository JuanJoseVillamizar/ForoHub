package JuanJose.ForoHub.Service.Course;

import JuanJose.ForoHub.Service.SubCategory.SubCategoryValidator;
import JuanJose.ForoHub.dto.Course.*;
import JuanJose.ForoHub.exception.ResourceAlreadyExistException;
import JuanJose.ForoHub.exception.ResourceNotFoundException;
import JuanJose.ForoHub.model.Course;
import JuanJose.ForoHub.model.SubCategory;
import JuanJose.ForoHub.repository.CourseRepository;
import JuanJose.ForoHub.repository.SubCategoryRepository;
import JuanJose.ForoHub.utils.ConverterData;
import JuanJose.ForoHub.utils.StringUtils;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final SubCategoryRepository subCategoryRepository;
    private final CourseValidator courseValidator;
    private final SubCategoryValidator subCategoryValidator;

    public CourseService(CourseRepository courseRepository, SubCategoryRepository subCategoryRepository,
                         CourseValidator courseValidator, SubCategoryValidator subCategoryValidator) {
        this.courseRepository = courseRepository;
        this.subCategoryRepository = subCategoryRepository;
        this.courseValidator = courseValidator;
        this.subCategoryValidator= subCategoryValidator;
    }

    //Create Course
    public DetailsResponseCourseDTO createCourse(@Valid CreateCourseDTO data) {
        courseValidator.validateCourseExistsByName(data.name());
        subCategoryValidator.validateExistsById(data.idSubCategory());
        SubCategory subCategory = subCategoryRepository.findById(data.idSubCategory()).get();
        Course course = new Course(null, data.name(), data.description(), subCategory);
        courseRepository.save(course);
        return new DetailsResponseCourseDTO(course);
    }

    //Get course by id
    public DetailsResponseCourseDTO getById(Long id) {
        courseValidator.validateExistsById(id);
        Course course = courseRepository.getReferenceById(id);
        return new DetailsResponseCourseDTO(course);
    }

    //Delete course
    public DeleteCourseDTO deleteCourse(Long id) {
        courseValidator.validateExistsById(id);
        Course course = courseRepository.getReferenceById(id);
        courseRepository.delete(course);
        ResponseCourseDTO responseCourseDTO = toResponseCourseDTO(course);
        return new DeleteCourseDTO("The course was successfully deleted", responseCourseDTO);
    }

    //Update Course
    public ResponseCourseDTO updateCourse(Long id ,@Valid UpdateCourseDTO data) {
        courseValidator.validateExistsById(id);
        courseValidator.validateCourseExistsByName(data.name());
        Course course = courseRepository.getReferenceById(id);
        course.updateCourse(data);
        return  toResponseCourseDTO(course);
    }

    //Get courses by subCategory id
    public Page<ResponseCourseDTO> getCoursesBySubcategoryId(Long idSubCategory, Pageable pageable) {
        Page<Course> courses = courseRepository.findCoursesBySubCategoryId(idSubCategory, pageable);
        return courses.map(ConverterData::convertToDTOCourse);
    }

    // Converter to ResponseCourseDTO
    private ResponseCourseDTO toResponseCourseDTO(Course course) {
        return new ResponseCourseDTO(course.getId(), course.getName(),
                course.getDescription(), course.getSubCategory().getCategory().getId(), course.getSubCategory().getId());
    }

    public Page<ResponseCourseDTO> getAllCourse(Pageable pageable) {
        Page<Course> courses = courseRepository.findAll(pageable);
        return courses.map(ConverterData::convertToDTOCourse);
    }
}
