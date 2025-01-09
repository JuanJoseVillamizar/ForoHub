package juan.forohub.service.course;

import juan.forohub.service.validations.AbstractEntityValidator;
import juan.forohub.exception.ResourceAlreadyExistException;
import juan.forohub.entities.Course;
import juan.forohub.repository.CourseRepository;
import org.springframework.stereotype.Component;

@Component
public class CourseValidator extends AbstractEntityValidator<Course> {

    private final CourseRepository courseRepository;

    public CourseValidator(CourseRepository courseRepository){
        super(courseRepository);
        this.courseRepository=courseRepository;
    }

    public void validateCourseExistsByName (String name){
        if(courseRepository.existsByName(name)){
            throw new ResourceAlreadyExistException("A course with the name " + name  + " already exists.");
        }
    }
}
