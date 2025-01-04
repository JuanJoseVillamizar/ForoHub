package JuanJose.ForoHub.model;

import JuanJose.ForoHub.dto.User.CreateUserDTO;
import JuanJose.ForoHub.dto.User.UpdateUserDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name="forum_user")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class ForumUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,length = 500)
    private String name;

    @Column(nullable = false, unique = true, length = 200)
    private String email;

    @Column(nullable = false, length = 200)
    private String password;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "profile_id")
    private Profile profile;

    public void updateBasicInfo (UpdateUserDTO data){
        if(data.name() != null){
            this.name = data.name();
        }
        if(data.email() != null){
            this.email = data.email();
        }
    }
    public void updateProfile (Profile profile){
        this.profile = profile;
    }
}
