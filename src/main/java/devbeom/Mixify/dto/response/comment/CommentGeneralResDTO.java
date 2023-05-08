package devbeom.Mixify.dto.response.comment;

import devbeom.Mixify.domain.Comment;
import devbeom.Mixify.domain.User;
import lombok.Getter;

@Getter
public class CommentGeneralResDTO {
    // 신고 등의 기능을 위해서 추가
    private final Long commentId;

    private final String content;

    private final float commentStar;


    @Getter
    private static class UserDTOForComment {
        private final Long userPk;
        private final String username;

        UserDTOForComment(User user) {
            userPk = user.getId();
            username = user.getUsername();
        }
    }
    private final UserDTOForComment user;

    public CommentGeneralResDTO(Comment comment) {
        commentId = comment.getId();
        content = comment.getContent();
        commentStar = comment.getCommentStar();

        user = new UserDTOForComment(comment.getUser());
    }
}
