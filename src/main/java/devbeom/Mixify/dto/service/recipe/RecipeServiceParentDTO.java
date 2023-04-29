package devbeom.Mixify.dto.service.recipe;

import devbeom.Mixify.domain.Recipe;
import devbeom.Mixify.dto.controller.recipe.RecipeControllerParentDTO;

public abstract class RecipeServiceParentDTO {
    abstract public Recipe toEntity();
    abstract public RecipeControllerParentDTO toControllerDTO(Recipe recipe);
}
