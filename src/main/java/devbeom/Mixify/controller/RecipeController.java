package devbeom.Mixify.controller;

import devbeom.Mixify.service.RecipeService;
import devbeom.Mixify.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class RecipeController {
    private final UserService userService;
    private final RecipeService recipeService;

    @PostMapping("/create/recipe")
    public void createRecipe(@RequestBody ){}
}
