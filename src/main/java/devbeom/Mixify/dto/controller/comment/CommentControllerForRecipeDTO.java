package devbeom.Mixify.dto.controller.comment;

import devbeom.Mixify.domain.Comment;
import devbeom.Mixify.dto.controller.user.UserControllerForCommentDTO;
import devbeom.Mixify.dto.controller.user.UserControllerParentDTO;
import lombok.Getter;

@Getter
public class CommentControllerForRecipeDTO extends CommentControllerParentDTO{
    private String content;

    private float commentStar;
    private UserControllerParentDTO user;

    public void toDTO(Comment comment) {
        this.content = comment.getContent();
        this.commentStar = comment.getComment_star();

        UserControllerForCommentDTO userControllerForCommentDTO = new UserControllerForCommentDTO();
        userControllerForCommentDTO.toDTO(comment.getUser());
        this.user = userControllerForCommentDTO;
    }
}