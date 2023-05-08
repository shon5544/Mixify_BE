package devbeom.Mixify.controller;

import devbeom.Mixify.domain.Recipe;
import devbeom.Mixify.domain.Scrap;
import devbeom.Mixify.domain.User;
import devbeom.Mixify.service.RecipeService;
import devbeom.Mixify.service.ScrapService;
import devbeom.Mixify.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ScrapController {
    private final ScrapService scrapService;
    private final UserService userService;
    private final RecipeService recipeService;

    @PostMapping("/scrap/add")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<Void> addScrap(@RequestParam("u") Long userId,
                                         @RequestParam("r") Long recipeId) {
        User user = userService.getUserById(userId);
        Recipe recipe = recipeService.getRecipeById(recipeId);

        Scrap scrap = Scrap.builder()
                .user(user)
                .recipe(recipe)
                .build();

        scrapService.addScrap(scrap);

        return ResponseEntity.ok().build();
    }
}
