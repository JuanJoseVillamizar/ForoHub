package JuanJose.ForoHub.utils;

import JuanJose.ForoHub.dto.Course.ResponseCourseDTO;
import JuanJose.ForoHub.dto.SubCategory.ResponseSubCategoryDTO;
import JuanJose.ForoHub.model.Course;
import JuanJose.ForoHub.model.SubCategory;

public class ConverterData {
    public static ResponseSubCategoryDTO convertToDTOSub (SubCategory subCategory){
        return new ResponseSubCategoryDTO(subCategory.getId(),subCategory.getName(),subCategory.getCategory().getId());
    }
    public static ResponseCourseDTO convertToDTOCourse (Course course){
        return new ResponseCourseDTO(course.getId(),course.getName(),course.getDescription(),
                course.getSubCategory().getCategory().getId(),
                course.getSubCategory().getId());
    }
}
