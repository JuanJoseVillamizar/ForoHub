package JuanJose.ForoHub.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name="topic")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true ,length = 500)
    private String title;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TopicType type;

    @Column(nullable = false, unique = true)
    private String message;

    @Column(name = "creation_date", nullable = false)
    private LocalDateTime creationDate = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    private TopicStatus status = TopicStatus.UNANSWERED;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="author_id")
    private User author;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="course_id")
    private Course course;

    public void setType(String typeValue) {
        this.type = TopicType.fromString(typeValue);
    }
}
