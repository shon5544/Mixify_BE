package devbeom.Mixify.dto.controller.recipe;

import devbeom.Mixify.domain.Recipe;

public abstract class RecipeControllerParentDTO {
    public abstract void toDTO(Recipe recipe);
}
