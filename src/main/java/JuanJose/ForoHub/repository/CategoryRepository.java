package JuanJose.ForoHub.repository;

import JuanJose.ForoHub.model.Category;
import JuanJose.ForoHub.model.SubCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface CategoryRepository extends JpaRepository<Category,Long> {
    boolean existsByName(String name);
    Category findByName(String categoryName);
}
