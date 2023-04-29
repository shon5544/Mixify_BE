package devbeom.Mixify.dto.controller.ingredient;

import devbeom.Mixify.domain.Ingredient;

public abstract class IngredientControllerParentDTO {
    abstract public void toDTO(Ingredient ingredient);
}
