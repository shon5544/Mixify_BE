package devbeom.Mixify.dto.controller.ingredient;

import devbeom.Mixify.domain.Ingredient;
import lombok.Getter;

@Getter
public class IngredientControllerForRecipeDTO extends IngredientControllerParentDTO {

    private String name;
    private String quantity;

    public void toDTO(Ingredient ingredient) {
        this.name = ingredient.getName();
        this.quantity = ingredient.getQuantity();
    }
}
