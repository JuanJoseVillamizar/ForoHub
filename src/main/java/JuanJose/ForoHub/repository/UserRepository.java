package JuanJose.ForoHub.repository;

import JuanJose.ForoHub.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
