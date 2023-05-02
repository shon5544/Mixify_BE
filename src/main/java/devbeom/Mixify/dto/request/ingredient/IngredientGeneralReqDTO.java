package devbeom.Mixify.dto.request.ingredient;

import devbeom.Mixify.domain.Ingredient;
import devbeom.Mixify.domain.Recipe;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
public class IngredientGeneralReqDTO {
    private String name;
    private String quantity;

    public Ingredient toEntity(Recipe recipe) {
        return Ingredient.builder()
                .name(name)
                .quantity(quantity)
                .recipe(recipe)
                .build();
    }
}
