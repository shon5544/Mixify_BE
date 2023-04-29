package devbeom.Mixify.service;

import devbeom.Mixify.domain.Recipe;
import devbeom.Mixify.dto.controller.recipe.RecipeControllerParentDTO;
import devbeom.Mixify.dto.service.recipe.RecipeServiceParentDTO;
import devbeom.Mixify.repository.RecipeRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RecipeService {
    private final RecipeRepository recipeRepository;

    @Transactional
    public void createRecipe(RecipeServiceParentDTO recipeServiceParentDTO) {
        Recipe recipe = recipeServiceParentDTO.toEntity();
        recipeRepository.save(recipe);
    }

    // 의존성 문제로 인하여 추후 개발
//    public RecipeControllerParentDTO getRecipeById(Long recipeId) {
//        Recipe recipe = recipeRepository.findById(recipeId).orElseThrow(EntityNotFoundException::new);
//
//        RecipeControllerParentDTO recipeControllerParentDTO;
//        return recipeControllerParentDTO;
//    }
}
