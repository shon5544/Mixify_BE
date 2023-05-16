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
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final RecipeRepository recipeRepository;

    @Transactional
    public Comment createComment(CommentReqDTO commentReqDTO) {
        String currentUserId = SecurityUtil.getCurrentUserId().orElseThrow(() -> new IllegalStateException("인증된 토큰이 없음."));

        User user = userRepository.findByUserId(currentUserId)
                .orElseThrow(() -> new EntityNotFoundException("user 엔티티를 찾을 수 없음: CommentService.createComment"));

        Recipe recipe = recipeRepository.findById(commentReqDTO.getRecipeId())
                .orElseThrow(() -> new EntityNotFoundException("recipe 엔티티를 찾을 수 없음: CommentService.createComment"));

        Comment comment = commentReqDTO.toEntity(user, recipe);

        return commentRepository.save(comment);
    }

    @Transactional
    public void editComment(CommentReqDTO commentReqDTO, Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(EntityNotFoundException::new);

        String currentUsername = SecurityUtil.getCurrentUserId()
                .orElseThrow(() -> new IllegalStateException("인증된 토큰 없음."));
        String commentAuthor = comment.getUser().getUserId();


        if(currentUsername.equals(commentAuthor)) {
            comment.setContent(commentReqDTO.getContent());
            comment.setCommentStar(commentReqDTO.getCommentStar());
        } else {
            throw new IllegalStateException("다른 유저의 댓글에 대한 수정 감지. 잘못된 접근을 시도하는 유저 ID: " + currentUsername);
        }

    }

    public void deleteComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new EntityNotFoundException("comment 엔티티를 찾을 수 없음: CommentService.deleteComment"));

        String currentUserId = SecurityUtil.getCurrentUserId()
                .orElseThrow(() -> new IllegalStateException("인증된 토큰 없음."));
        String commentAuthorId = comment.getUser().getUserId();

        if(currentUserId.equals(commentAuthorId)) {
            commentRepository.delete(comment);
            log.info("댓글 삭제 완료.");
        } else {
            throw new RuntimeException("다른 유저의 댓글에 대한 삭제 감지. 잘못된 접근을 시도하는 유저 ID: " + currentUserId);
        }
    }
}
