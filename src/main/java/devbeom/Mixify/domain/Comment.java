package devbeom.Mixify.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @Column(name = "comment_star")
    private float commentStar;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    @Column(name = "commented_date")
    private LocalDateTime commentedDate;

    @Column(name = "is_edited")
    private boolean isEdited;

    @Builder
    public Comment(String content, float commentStar, User user, Recipe recipe) {
        this.content = content;
        this.commentStar = commentStar;
        this.user = user;
        this.recipe = recipe;

        this.commentedDate = LocalDateTime.now();
        this.isEdited = false;
    }

    public void setContent(String content) {
        this.content = content;
        this.commentedDate = LocalDateTime.now();
        this.isEdited = true;
    }

    public void setCommentStar(float commentStar) {
        this.commentStar = commentStar;
        this.commentedDate = LocalDateTime.now();
        this.isEdited = true;
    }
}
