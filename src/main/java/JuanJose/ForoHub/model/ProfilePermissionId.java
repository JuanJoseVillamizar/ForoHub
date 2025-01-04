package JuanJose.ForoHub.model;

import lombok.EqualsAndHashCode;

import java.io.Serializable;


@EqualsAndHashCode
public class ProfilePermissionId implements Serializable {
    private Long profile;
    private Long permission;
}
