package devbeom.Mixify.dto.request.comment;

import devbeom.Mixify.domain.Comment;
import devbeom.Mixify.domain.Recipe;
import devbeom.Mixify.domain.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class CommentReqDTO {
    @NotBlank
    @Size(max = 250)
    private String content;

    private Long recipeId;
    
    // 유저가 레시피에 주는 별점
    private float commentStar;

    public Comment toEntity(User user, Recipe recipe) {
        return Comment.builder()
                .content(this.content)
                .user(user)
                .recipe(recipe)
                .commentStar(this.commentStar)
                .build();
    }
}
