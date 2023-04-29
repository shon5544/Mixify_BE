package devbeom.Mixify.dto.controller.comment;

import devbeom.Mixify.domain.Comment;
import devbeom.Mixify.dto.controller.user.UserControllerForCommentDTO;
import devbeom.Mixify.dto.controller.user.UserControllerParentDTO;
import lombok.Getter;

@Getter
public class CommentControllerForRecipeDTO extends CommentControllerParentDTO{
    private String content;

    private float commentStar;
    private UserControllerForCommentDTO user;

    public void toDTO(Comment comment) {
        this.content = comment.getContent();
        this.commentStar = comment.getComment_star();

        user = new UserControllerForCommentDTO();
        user.toDTO(comment.getUser());
    }
}
