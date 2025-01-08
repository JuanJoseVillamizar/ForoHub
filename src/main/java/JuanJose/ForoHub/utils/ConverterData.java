package JuanJose.ForoHub.utils;

import JuanJose.ForoHub.dto.Category.CategoryDetailsDTO;
import JuanJose.ForoHub.dto.Course.ResponseCourseDTO;
import JuanJose.ForoHub.dto.Permission.ResponsePermissionDTO;
import JuanJose.ForoHub.dto.Profile.ResponseProfileDTO;
import JuanJose.ForoHub.dto.Response.DTOResponse;
import JuanJose.ForoHub.dto.Response.ResponseDTO;
import JuanJose.ForoHub.dto.SubCategory.ResponseSubCategoryDTO;
import JuanJose.ForoHub.dto.Topic.TopicDetailsDTO;
import JuanJose.ForoHub.dto.User.ResponseUserDTO;
import JuanJose.ForoHub.dto.User.UserDTO;
import JuanJose.ForoHub.entities.*;

public class ConverterData {
    public static ResponseSubCategoryDTO convertToDTOSub (SubCategory subCategory){
        return new ResponseSubCategoryDTO(subCategory.getId(),subCategory.getName(),subCategory.getCategory().getId());
    }
    public static ResponseCourseDTO convertToDTOCourse (Course course){
        return new ResponseCourseDTO(course.getId(),course.getName(),course.getDescription(),
                course.getSubCategory().getCategory().getId(),
                course.getSubCategory().getId());
    }

    public static ResponsePermissionDTO convertToDTOPermission(Permission permission) {
        return new ResponsePermissionDTO(permission.getId(), permission.getName(), permission.getDescription());
    }

    public static ResponseProfileDTO convertToDTOProfile(Profile profile) {
        return new ResponseProfileDTO(profile.getId(), profile.getName(), profile.getDescription());
    }

    public static ResponseUserDTO convertToDTOUser(ForumUser user) {
        return new ResponseUserDTO(user.getId(), user.getName(), user.getEmail(), user.getProfile().getId());
    }

    public static DTOResponse convertToDTOResponse(Response response) {
        return new DTOResponse(response.getId(), response.getMessage(),response.isSolution() ,response.getCreationDate(),
                response.getAuthor().getId());
    }

    public static ResponseDTO convertToResponseDTO(Response response) {
        return new ResponseDTO(response.getId(), response.getMessage(), response.getCreationDate(),
                response.getTopic().getId(), response.getAuthor().getId());
    }

    public static TopicDetailsDTO convertToTopicDetailsDTO(Topic topic) {
        return new TopicDetailsDTO(
                topic.getId(),
                topic.getTitle(),
                topic.getStatus(),
                topic.getCreationDate(),
                new UserDTO(topic.getAuthor().getId(), topic.getAuthor().getName()),
                topic.getResponses().size(),
                (topic.getCourse() != null &&
                        topic.getCourse().getSubCategory() != null &&
                        topic.getCourse().getSubCategory().getCategory() != null)
                        ? new CategoryDetailsDTO(
                        topic.getCourse().getSubCategory().getCategory().getId(),
                        topic.getCourse().getSubCategory().getCategory().getName(),
                        topic.getCourse().getSubCategory().getId(),
                        topic.getCourse().getSubCategory().getName(),
                        topic.getCourse().getId(),
                        topic.getCourse().getName() != null ? topic.getCourse().getName() : "off topic")
                        : new CategoryDetailsDTO(
                        null, // Puedes usar null o un valor por defecto
                        "off topic", // Por defecto si no hay curso
                        null,
                        "off topic", // Por defecto si no hay subcategoría
                        null,
                        "off topic" // Por defecto si no hay curso
                )
        );
    }
}
