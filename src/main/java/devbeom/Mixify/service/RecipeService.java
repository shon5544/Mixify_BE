package devbeom.Mixify.service;

import devbeom.Mixify.domain.Recipe;
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
    public void createRecipe(Recipe recipe) {
        recipeRepository.save(recipe);
    }

    public Recipe getRecipeById(Long recipeId) {
        return recipeRepository.findById(recipeId).orElseThrow(EntityNotFoundException::new);
    }
}
