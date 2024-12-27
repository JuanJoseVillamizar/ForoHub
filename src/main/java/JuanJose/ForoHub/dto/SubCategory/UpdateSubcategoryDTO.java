package JuanJose.ForoHub.dto.SubCategory;

import jakarta.validation.constraints.NotBlank;

public record UpdateSubcategoryDTO (
        @NotBlank
        String name
){
        public UpdateSubcategoryDTO (String name){
                this.name=name;
        }
}
