package devbeom.Mixify.dto.controller.ingredient;

import devbeom.Mixify.domain.Ingredient;

public abstract class IngredientControllerParentDTO {
    public abstract void toDTO(Ingredient ingredient);
}
