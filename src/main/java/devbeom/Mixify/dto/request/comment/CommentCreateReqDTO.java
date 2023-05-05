package devbeom.Mixify.dto.request.comment;

import devbeom.Mixify.domain.Comment;
import devbeom.Mixify.domain.Recipe;
import devbeom.Mixify.domain.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class CommentCreateReqDTO {
    @NotBlank
    @Size(max = 250)
    private String content;

    private Long userId;

    private Long recipeId;

    public Comment toEntity(User user, Recipe recipe) {
        return Comment.builder()
                .content(this.content)
                .user(user)
                .recipe(recipe)
                .build();
    }
}
