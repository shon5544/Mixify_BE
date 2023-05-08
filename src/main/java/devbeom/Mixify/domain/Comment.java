package devbeom.Mixify.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    @Builder
    public Comment(String content, float commentStar, User user, Recipe recipe) {
        this.content = content;
        this.commentStar = commentStar;
        this.user = user;
        this.recipe = recipe;

        this.commentedDate = LocalDateTime.now();
    }
}
