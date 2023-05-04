package devbeom.Mixify.dto.response.comment;

import devbeom.Mixify.domain.Comment;
import lombok.Getter;

@Getter
public class CommentGeneralResDTO {
    // 신고 등의 기능을 위해서 추가
    private final Long id;

    private final String content;

    private final float commentStar;

    public CommentGeneralResDTO(Comment comment) {
        id = comment.getId();
        content = comment.getContent();
        commentStar = comment.getCommentStar();
    }
}
