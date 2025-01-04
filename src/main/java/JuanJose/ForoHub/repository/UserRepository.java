package JuanJose.ForoHub.repository;

import JuanJose.ForoHub.dto.User.UserProfilePermissionDTO;
import JuanJose.ForoHub.model.ForumUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<ForumUser,Long> {
    boolean existsByEmail(String email);
    Page<ForumUser> findAll(Pageable pageable);

    @Query("""
            SELECT u FROM ForumUser u
            LEFT JOIN FETCH Profile p ON u.profile.id = p.id
            LEFT JOIN FETCH ProfilePermission pp ON pp.profile.id = p.id
            WHERE u.id = :id
            """)
    ForumUser findUserProfileAndPermissions(Long id);
}
