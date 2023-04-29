package devbeom.Mixify.service;

import devbeom.Mixify.domain.Comment;
import devbeom.Mixify.dto.service.comment.CommentServiceParentDTO;
import devbeom.Mixify.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentService {
    private final CommentRepository commentRepository;

    @Transactional
    public void createComment(CommentServiceParentDTO commentServiceParentDTO) {
        Comment comment = commentServiceParentDTO.toEntity();
        commentRepository.save(comment);
    }
}
