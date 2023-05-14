package devbeom.Mixify.service;

import devbeom.Mixify.domain.Comment;
import devbeom.Mixify.domain.Recipe;
import devbeom.Mixify.domain.User;
import devbeom.Mixify.dto.request.comment.CommentReqDTO;
import devbeom.Mixify.jwt.utils.SecurityUtil;
import devbeom.Mixify.repository.CommentRepository;
import devbeom.Mixify.repository.RecipeRepository;
import devbeom.Mixify.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final RecipeRepository recipeRepository;

    @Transactional
    public Comment createComment(CommentReqDTO commentReqDTO) {
        String currentUserId = SecurityUtil.getCurrentUserId().orElseThrow(() -> new IllegalStateException("인증된 토큰이 없음."));
        String commentAuthorId = commentReqDTO.getUserId();

        if(currentUserId.equals(commentAuthorId)) {
            User user = userRepository.findByUserId(commentAuthorId)
                    .orElseThrow(() -> new EntityNotFoundException("user 엔티티를 찾을 수 없음: CommentService.createComment"));
            Recipe recipe = recipeRepository.findById(commentReqDTO.getRecipeId())
                    .orElseThrow(() -> new EntityNotFoundException("recipe 엔티티를 찾을 수 없음: CommentService.createComment"));

            Comment comment = commentReqDTO.toEntity(user, recipe);
            return commentRepository.save(comment);
        } else {
            throw new IllegalStateException("다른 유저의 명의로 댓글 작성 시도 감지. 잘못된 접근을 시도한 유저 ID: " + currentUserId);
        }
    }

    @Transactional
    public void editComment(CommentReqDTO commentReqDTO, Long commentId) {
        String currentUsername = SecurityUtil.getCurrentUserId()
                .orElseThrow(() -> new IllegalStateException("인증된 토큰 없음."));
        String commentAuthor = commentReqDTO.getUserId();

        Comment comment = commentRepository.findById(commentId).orElseThrow(EntityNotFoundException::new);;

        if(currentUsername.equals(commentAuthor) && comment.getUser().getUserId().equals(commentAuthor)) {
            comment.setContent(commentReqDTO.getContent());
            comment.setCommentStar(commentReqDTO.getCommentStar());
        } else {
            throw new IllegalStateException("다른 유저의 댓글에 대한 수정 감지. 잘못된 접근을 시도하는 유저 ID: " + currentUsername);
        }

    }
}
