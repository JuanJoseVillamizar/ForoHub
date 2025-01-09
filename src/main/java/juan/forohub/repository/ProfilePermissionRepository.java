package juan.forohub.repository;

import juan.forohub.entities.Permission;
import juan.forohub.entities.Profile;
import juan.forohub.entities.ProfilePermission;
import juan.forohub.entities.ProfilePermissionId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfilePermissionRepository extends JpaRepository<ProfilePermission, ProfilePermissionId> {


    boolean existsByProfileAndPermission(Profile profile, Permission permission);
}
