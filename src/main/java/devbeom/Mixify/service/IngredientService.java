package devbeom.Mixify.service;

import devbeom.Mixify.domain.Ingredient;
import devbeom.Mixify.domain.Recipe;
import devbeom.Mixify.dto.service.ingredient.IngredientServiceParentDTO;
import devbeom.Mixify.repository.IngredientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class IngredientService {
    private final IngredientRepository ingredientRepository;

    @Transactional
    public void createIngredient(IngredientServiceParentDTO ingredientServiceParentDTO) {
        Ingredient ingredient = ingredientServiceParentDTO.toEntity();
        ingredientRepository.save(ingredient);
    }

    public List<Ingredient> getIngredientListByRecipe(Recipe recipe) {
        return ingredientRepository.findAllByRecipe(recipe);
    }
}