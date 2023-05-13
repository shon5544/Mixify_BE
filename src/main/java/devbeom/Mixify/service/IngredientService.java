package devbeom.Mixify.service;

import devbeom.Mixify.domain.Ingredient;
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
    public void createIngredients(List<Ingredient> ingredientList) {
        ingredientRepository.saveAll(ingredientList);
    }

    // 아직은 재료만 따로 가져와야 하는 상황은 없음. 나중에 생기면 주석 해제
//    public List<Ingredient> getIngredientListByRecipe(Recipe recipe) {
//        return ingredientRepository.findAllByRecipe(recipe).orElseThrow(EntityNotFoundException::new);
//    }
}
