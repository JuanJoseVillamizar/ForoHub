package JuanJose.ForoHub.repository;

import JuanJose.ForoHub.model.Permission;
import JuanJose.ForoHub.model.Profile;
import JuanJose.ForoHub.model.ProfilePermission;
import JuanJose.ForoHub.model.ProfilePermissionId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfilePermissionRepository extends JpaRepository<ProfilePermission, ProfilePermissionId> {


    boolean existsByProfileAndPermission(Profile profile, Permission permission);
}
