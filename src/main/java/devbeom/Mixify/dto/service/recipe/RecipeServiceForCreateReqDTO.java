package devbeom.Mixify.dto.service.recipe;

import devbeom.Mixify.domain.Recipe;

public class RecipeServiceForCreateReqDTO extends RecipeServiceParentDTO{

    public Recipe toEntity() {
        return Recipe.builder().build();
    }
}
