package JuanJose.ForoHub.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name="profile_permission")
@IdClass(ProfilePermissionId.class)
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProfilePermission {
    @Id
    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="profile_id")
    private Profile profile;

    @Id
    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="permission_id")
    private Permission permission;
}
