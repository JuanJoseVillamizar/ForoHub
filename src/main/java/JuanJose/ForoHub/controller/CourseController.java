package JuanJose.ForoHub.controller;

import JuanJose.ForoHub.Service.Course.CourseService;
import JuanJose.ForoHub.dto.Course.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    // Create Course
    @PostMapping("/course")
    @Transactional
    public ResponseEntity<DetailsResponseCourseDTO> registerCourse(@RequestBody @Valid CreateCourseDTO data,
                                                                   UriComponentsBuilder uriComponentsBuilder) {
        DetailsResponseCourseDTO detailsResponseCourseDTO = courseService.createCourse(data);
        URI url = uriComponentsBuilder.path("/courses/{id}").buildAndExpand(detailsResponseCourseDTO.id()).toUri();
        return ResponseEntity.created(url).body(detailsResponseCourseDTO);
    }

    //Update course
    @PutMapping("/course/{id}")
    @Transactional
    public ResponseEntity<ResponseCourseDTO> updateCourse(
            @PathVariable Long id,
            @RequestBody @Valid UpdateCourseDTO data) {
        ResponseCourseDTO responseCourseDTO = courseService.updateCourse(id, data);
        return ResponseEntity.ok().body(responseCourseDTO);
    }

    //Delete course
    @DeleteMapping("/course/{id}")
    public ResponseEntity<DeleteCourseDTO> deleteCourse(@PathVariable Long id) {
        return ResponseEntity.ok().body(courseService.deleteCourse(id));
    }

    //Get course by id
    @GetMapping("/course/{id}")
    public ResponseEntity<DetailsResponseCourseDTO> getCourseByDd(@PathVariable Long id) {
        DetailsResponseCourseDTO response = courseService.getById(id);
        return ResponseEntity.ok().body(response);
    }

    // Get all courses
    @GetMapping("/courses-list")
    public ResponseEntity<PagedModel<ResponseCourseDTO>> getAllCourses(
            @PageableDefault(size = 10, sort = "subCategory.id")
            Pageable pageable, PagedResourcesAssembler assembler) {
        Page<ResponseCourseDTO> responseCourseDTOPage = courseService.getAllCourse(pageable);
        PagedModel pagedModel = assembler.toModel(responseCourseDTOPage);
        return ResponseEntity.ok(pagedModel);
    }

}
