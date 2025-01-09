package juan.forohub.service.course;


import juan.forohub.dto.course.*;
import juan.forohub.entities.Course;
import juan.forohub.entities.SubCategory;
import juan.forohub.entities.TopicStatus;
import juan.forohub.entities.TopicType;
import juan.forohub.dto.topic.TopicDetailsDTO;
import juan.forohub.repository.CourseRepository;
import juan.forohub.repository.SubCategoryRepository;
import juan.forohub.service.subCategory.SubCategoryValidator;
import juan.forohub.service.topic.TopicService;
import juan.forohub.utils.ConverterData;
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
    private final TopicService topicService;

    public CourseService(CourseRepository courseRepository, SubCategoryRepository subCategoryRepository,
                         CourseValidator courseValidator, SubCategoryValidator subCategoryValidator,
                         TopicService topicService) {
        this.courseRepository = courseRepository;
        this.subCategoryRepository = subCategoryRepository;
        this.courseValidator = courseValidator;
        this.subCategoryValidator= subCategoryValidator;
        this.topicService = topicService;
    }

    //Create Course
    public DetailsResponseCourseDTO createCourse(@Valid CreateCourseDTO data) {
        courseValidator.validateCourseExistsByName(data.name());
        subCategoryValidator.validateExistsById(data.idSubCategory());
        SubCategory subCategory = subCategoryRepository.getReferenceById(data.idSubCategory());
        Course course = new Course(null, data.name(), data.description(), subCategory, null);
        courseRepository.save(course);
        return new DetailsResponseCourseDTO(course);
    }

    //Get course by id
    public DetailsResponseCourseDTO getCourseById(Long id) {
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
    public Page<ResponseCourseDTO> getCoursesBySubCategoryId(Long idSubCategory, Pageable pageable) {
        Page<Course> courses = courseRepository.findCoursesBySubCategoryId(idSubCategory, pageable);
        return courses.map(ConverterData::convertToDTOCourse);
    }

    //Get all courses
    public Page<ResponseCourseDTO> getAllCourse(Pageable pageable) {
        Page<Course> courses = courseRepository.findAll(pageable);
        return courses.map(ConverterData::convertToDTOCourse);
    }


    //Get topics by courseId
    public Page<TopicDetailsDTO> getTopicsByCourseId(
            @Valid Long courseId,
            TopicStatus status,
            TopicType type, Pageable pageable) {
        courseValidator.validateExistsById(courseId);
        return topicService.getTopics(courseId,status,type,pageable);
    }

    // Converter to ResponseCourseDTO
    private ResponseCourseDTO toResponseCourseDTO(Course course) {
        return new ResponseCourseDTO(course.getId(), course.getName(),
                course.getDescription(), course.getSubCategory().getCategory().getId(), course.getSubCategory().getId());
    }
}
