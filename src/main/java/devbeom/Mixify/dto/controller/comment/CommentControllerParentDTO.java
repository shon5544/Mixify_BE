package devbeom.Mixify.dto.controller.comment;

import devbeom.Mixify.domain.Comment;

public abstract class CommentControllerParentDTO {
    abstract public void toDTO(Comment comment);
}
