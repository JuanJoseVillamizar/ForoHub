package JuanJose.ForoHub.repository;

import JuanJose.ForoHub.model.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile,Long> {
    boolean existsByName(String name);
    Page<Profile> findAll(Pageable pageable);
}
