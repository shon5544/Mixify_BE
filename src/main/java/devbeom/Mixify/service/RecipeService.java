package devbeom.Mixify.service;

import devbeom.Mixify.domain.Ingredient;
import devbeom.Mixify.domain.Recipe;
import devbeom.Mixify.domain.Step;
import devbeom.Mixify.dto.request.recipe.RecipeGeneralReqDTO;
import devbeom.Mixify.dto.response.recipe.RecipeGeneralResDTO;
import devbeom.Mixify.repository.IngredientRepository;
import devbeom.Mixify.repository.RecipeRepository;
import devbeom.Mixify.repository.StepRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RecipeService {
    private final RecipeRepository recipeRepository;
    private final IngredientRepository ingredientRepository;
    private final StepRepository stepRepository;

    @Transactional
    public Recipe createRecipe(Recipe recipe) {
        return recipeRepository.save(recipe);
    }

    @Transactional
    public Recipe editRecipe(RecipeGeneralReqDTO recipeFromDTO, Long recipeId) {
        Recipe recipe = recipeRepository.findById(recipeId).orElseThrow(EntityNotFoundException::new);

        // recipe 객체 세팅
        recipe.setTitle(recipeFromDTO.getTitle());
        recipe.setThumbnail(recipeFromDTO.getThumbnail());
        recipe.getIngredientList().clear(); // 재료 삭제. ingredient에 orphanRemoval 적용되어있음.
        recipe.getStepList().clear(); // 단계 삭제. step에 orphanRemoval 적용되어있음.

        // ingredient 재등록
        List<Ingredient> ingredientList = recipeFromDTO.getIngredients().stream()
                .map(ingredientGeneralReqDTO -> ingredientGeneralReqDTO.toEntity(recipe))
                .collect(Collectors.toList());
        ingredientRepository.saveAll(ingredientList);

        // step 재등록
        List<Step> stepList = recipeFromDTO.getStepList().stream()
                .map(stepGeneralReqDTO -> stepGeneralReqDTO.toEntity(recipe))
                .collect(Collectors.toList());
        stepRepository.saveAll(stepList);

        return recipe;
    }

    public RecipeGeneralResDTO getRecipeResById(Long recipeId) {
        Recipe recipe = recipeRepository.findById(recipeId).orElseThrow(EntityNotFoundException::new);

        return new RecipeGeneralResDTO(recipe);
    }

    public Recipe getRecipeById(Long recipeId) {
        return recipeRepository.findById(recipeId).orElseThrow(EntityNotFoundException::new);
    }
}
