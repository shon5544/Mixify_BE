package devbeom.Mixify.controller;

import devbeom.Mixify.domain.Ingredient;
import devbeom.Mixify.domain.Recipe;
import devbeom.Mixify.domain.Step;
import devbeom.Mixify.dto.request.recipe.RecipeGeneralReqDTO;
import devbeom.Mixify.dto.response.recipe.RecipeGeneralResDTO;
import devbeom.Mixify.service.IngredientService;
import devbeom.Mixify.service.RecipeService;
import devbeom.Mixify.service.StepService;
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
    private final RecipeService recipeService;
    private final StepService stepService;
    private final IngredientService ingredientService;

    @GetMapping("/get/{recipeId}")
    public ResponseEntity<RecipeGeneralResDTO> getRecipe(@PathVariable Long recipeId) {
        RecipeGeneralResDTO recipeGeneralResDTO = new RecipeGeneralResDTO(recipeService.getRecipeById(recipeId));

        return ResponseEntity.ok()
                        .body(recipeGeneralResDTO);

    }

    @PostMapping("/create")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<Void> createRecipe(@Valid @RequestBody RecipeGeneralReqDTO recipeGeneralReqDTO) {

        Recipe recipe = recipeService.createRecipe(recipeGeneralReqDTO);

        List<Step> steps = recipeGeneralReqDTO.getStepList().stream()
                .map(stepGeneralReqDTO -> stepGeneralReqDTO.toEntity(recipe))
                .collect(Collectors.toList());

        List<Ingredient> ingredients = recipeGeneralReqDTO.getIngredients().stream()
                .map(ingredientGeneralReqDTO -> ingredientGeneralReqDTO.toEntity(recipe))
                .collect(Collectors.toList());

        stepService.createSteps(steps);
        ingredientService.createIngredients(ingredients);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/edit")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<Void> editRecipe(@Valid @RequestBody RecipeGeneralReqDTO recipeGeneralReqDTO,
                                           @RequestParam(name = "r") Long recipeId) {

        Recipe recipe = recipeService.editRecipe(recipeGeneralReqDTO, recipeId);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteRecipe(@RequestParam(name = "r") Long recipeId) {
        recipeService.deleteRecipe(recipeId);

        return ResponseEntity.ok().build();
    }
}
