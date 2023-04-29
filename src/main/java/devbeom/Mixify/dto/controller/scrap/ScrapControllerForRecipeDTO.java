package devbeom.Mixify.dto.controller.scrap;

import devbeom.Mixify.domain.Scrap;
import devbeom.Mixify.dto.controller.recipe.RecipeControllerForScrapDTO;
import devbeom.Mixify.dto.controller.recipe.RecipeControllerParentDTO;
import devbeom.Mixify.dto.controller.user.UserControllerForScrapDTO;
import devbeom.Mixify.dto.controller.user.UserControllerParentDTO;
import lombok.Getter;

@Getter
public class ScrapControllerForRecipeDTO extends ScrapControllerParentDTO {
    private final UserControllerParentDTO user = new UserControllerForScrapDTO();
    private final RecipeControllerParentDTO recipe = new RecipeControllerForScrapDTO();

    public void toDTO(Scrap scrap) {
        user.toDTO(scrap.getUser());
        recipe.toDTO(scrap.getRecipe());
    }
}
