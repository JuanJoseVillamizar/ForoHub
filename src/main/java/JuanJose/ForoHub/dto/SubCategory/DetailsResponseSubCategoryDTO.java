package JuanJose.ForoHub.dto.SubCategory;

import JuanJose.ForoHub.model.SubCategory;

public record DetailsResponseSubCategoryDTO(
        Long id,
        String name,
        String category
) {
    public DetailsResponseSubCategoryDTO(SubCategory subCategory){
        this(subCategory.getId(),subCategory.getName(),subCategory.getCategory().getName());
    }

}
