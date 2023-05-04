package devbeom.Mixify.dto.response.ingredient;

import devbeom.Mixify.domain.Ingredient;
import lombok.Getter;

@Getter
public class IngredientGeneralResDTO {
    private final String name;
    private final String quantity;

    public IngredientGeneralResDTO(Ingredient ingredient) {
        name = ingredient.getName();
        quantity = ingredient.getQuantity();
    }
}
