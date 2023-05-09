package devbeom.Mixify.controller;

import devbeom.Mixify.domain.Ingredient;
import devbeom.Mixify.domain.Recipe;
import devbeom.Mixify.domain.Step;
import devbeom.Mixify.domain.User;
import devbeom.Mixify.dto.request.recipe.RecipeGeneralReqDTO;
import devbeom.Mixify.dto.response.recipe.RecipeGeneralResDTO;
import devbeom.Mixify.service.IngredientService;
import devbeom.Mixify.service.RecipeService;
import devbeom.Mixify.service.StepService;
import devbeom.Mixify.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/recipe")
public class RecipeController {
    private final UserService userService;
    private final RecipeService recipeService;
    private final StepService stepService;
    private final IngredientService ingredientService;

    @GetMapping("/get/{recipeId}")
    public ResponseEntity<RecipeGeneralResDTO> getRecipe(@PathVariable Long recipeId) {

        return ResponseEntity.ok()
                        .body(recipeService.getRecipeResById(recipeId));

    }

    @PostMapping("/create")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<Void> createRecipe(@Valid @RequestBody RecipeGeneralReqDTO recipeGeneralReqDTO) {
        Long userId = recipeGeneralReqDTO.getUserId();
        User user = userService.getUserById(userId);

        Recipe recipe = recipeService.createRecipe(recipeGeneralReqDTO.toEntity(user));

        List<Step> steps = recipeGeneralReqDTO.getSteps().stream()
                .map(stepGeneralReqDTO -> stepGeneralReqDTO.toEntity(recipe))
                .collect(Collectors.toList());

        List<Ingredient> ingredients = recipeGeneralReqDTO.getIngredients().stream()
                .map(ingredientGeneralReqDTO -> ingredientGeneralReqDTO.toEntity(recipe))
                .collect(Collectors.toList());

        stepService.createSteps(steps);
        ingredientService.createIngredients(ingredients);

        return ResponseEntity.ok().build();
    }
}
