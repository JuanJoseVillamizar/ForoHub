package JuanJose.ForoHub.repository;

import JuanJose.ForoHub.model.SubCategory;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubCategoryRepository extends JpaRepository<SubCategory,Long> {
    boolean existsByName(String name);
}
