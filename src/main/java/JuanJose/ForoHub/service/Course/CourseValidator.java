package JuanJose.ForoHub.service.Course;

import JuanJose.ForoHub.service.Validations.AbstractEntityValidator;
import JuanJose.ForoHub.exception.ResourceAlreadyExistException;
import JuanJose.ForoHub.entities.Course;
import JuanJose.ForoHub.repository.CourseRepository;
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
