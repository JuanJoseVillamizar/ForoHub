package JuanJose.ForoHub.dto.SubCategory;

import JuanJose.ForoHub.entities.SubCategory;

public record DetailsResponseSubCategoryDTO(
        Long id,
        String name,
        String category
) {
    public DetailsResponseSubCategoryDTO(SubCategory subCategory){
        this(subCategory.getId(),subCategory.getName(),subCategory.getCategory().getName());
    }

}
