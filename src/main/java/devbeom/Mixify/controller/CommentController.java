package devbeom.Mixify.controller;

import devbeom.Mixify.dto.request.comment.CommentReqDTO;
import devbeom.Mixify.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/create")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<Void> createComment(@Valid @RequestBody CommentReqDTO commentReqDTO) {
        commentService.createComment(commentReqDTO);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/edit")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN'")
    public ResponseEntity<Void> editComment(@Valid @RequestBody CommentReqDTO commentReqDTO,
                                            @RequestParam(name = "c") Long commentId) {

        commentService.editComment(commentReqDTO, commentId);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteComment(@RequestParam(name = "c") Long commentId) {

        commentService.deleteComment(commentId);

        return ResponseEntity.ok().build();
    }
}
