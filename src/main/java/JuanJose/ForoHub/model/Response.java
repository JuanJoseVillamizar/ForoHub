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

    @Column(nullable = false)
    private String message;

    @Column(name = "creation_date", nullable = false)
    private LocalDateTime creationDate = LocalDateTime.now();

    @Column(name="is_solution", nullable = false)
    private boolean isSolution = false;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="topic_id")
    private  Topic topic;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="author_id")
    private  User author;

}
