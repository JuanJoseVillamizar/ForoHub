package JuanJose.ForoHub.repository;

import JuanJose.ForoHub.entities.Permission;
import JuanJose.ForoHub.entities.Profile;
import JuanJose.ForoHub.entities.ProfilePermission;
import JuanJose.ForoHub.entities.ProfilePermissionId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfilePermissionRepository extends JpaRepository<ProfilePermission, ProfilePermissionId> {


    boolean existsByProfileAndPermission(Profile profile, Permission permission);
}
