package devbeom.Mixify.dto.controller.recipe;

import devbeom.Mixify.domain.Recipe;

public abstract class RecipeControllerParentDTO {
    abstract public void toDTO(Recipe recipe);
}
