package devbeom.Mixify.controller;

import devbeom.Mixify.domain.Recipe;
import devbeom.Mixify.domain.User;
import devbeom.Mixify.dto.request.comment.CommentCreateReqDTO;
import devbeom.Mixify.service.CommentService;
import devbeom.Mixify.service.RecipeService;
import devbeom.Mixify.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    private final UserService userService;
    private final RecipeService recipeService;

    @PostMapping("/create/comment")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<Void> createComment(@Valid @RequestBody CommentCreateReqDTO commentCreateReqDTO) {
        User user = userService.getUserById(commentCreateReqDTO.getUserId());
        Recipe recipe = recipeService.getRecipeById(commentCreateReqDTO.getRecipeId());

        commentService.createComment(commentCreateReqDTO.toEntity(user, recipe));

        return ResponseEntity.ok().build();
    }
}
