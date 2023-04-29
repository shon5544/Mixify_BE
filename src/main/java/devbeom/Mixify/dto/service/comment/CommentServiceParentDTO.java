package devbeom.Mixify.dto.service.comment;

import devbeom.Mixify.domain.Comment;
import devbeom.Mixify.dto.controller.user.UserControllerParentDTO;

public abstract class CommentServiceParentDTO {
//    private String content;
//    private float comment_star;
//    private UserControllerParentDTO user;

    abstract public Comment toEntity();
}
