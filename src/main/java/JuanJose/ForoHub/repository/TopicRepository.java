package JuanJose.ForoHub.repository;

import JuanJose.ForoHub.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<Topic,Long> {
}
