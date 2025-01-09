package juan.forohub.dto.statistics;

public record CountDTO(
        Long categories,
        Long subCategories,
        Long courses,
        Long topics,
        Long responses
) {
}
