package JuanJose.ForoHub.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name="response")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Response {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String message;
    private LocalDateTime creationDate = LocalDateTime.now();
    private boolean isSolution = false;
    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="topic_id")
    private  Topic topic;
    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="author_id")
    private  User author;

}
