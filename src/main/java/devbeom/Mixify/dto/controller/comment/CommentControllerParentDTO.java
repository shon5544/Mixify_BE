package devbeom.Mixify.dto.controller.comment;

import devbeom.Mixify.domain.Comment;

public abstract class CommentControllerParentDTO {
    public abstract void toDTO(Comment comment);
}
