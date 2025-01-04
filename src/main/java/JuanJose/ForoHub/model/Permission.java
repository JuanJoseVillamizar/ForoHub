package JuanJose.ForoHub.model;

import JuanJose.ForoHub.dto.Permission.CreatePermissionDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name="permission")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 100)
    private String name;

    @Column
    private String description;

    public void updatePermission(CreatePermissionDTO data){
        if(data.name() != null){
            this.name=data.name();
        }
        if (data.description() != null){
            this.description = data.description();
        }
    }
}
