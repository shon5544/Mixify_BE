package devbeom.Mixify.dto.response.scrap;

import devbeom.Mixify.domain.Recipe;
import devbeom.Mixify.domain.Scrap;
import devbeom.Mixify.dto.response.user.UserGeneralResDTO;
import lombok.Getter;

@Getter
public class ScrapResDTO {
    private final UserGeneralResDTO authorUser;

    private final String thumbnail;
    private final Long recipeId;
    private final String recipeTitle;

    private final int likeCnt;
    private final int commentCnt;
    private final int scrapCnt;

    public ScrapResDTO(Scrap scrap) {
        Recipe recipe = scrap.getRecipe();

        thumbnail = recipe.getThumbnail();
        recipeId = recipe.getId();
        recipeTitle = recipe.getTitle();

        likeCnt = recipe.getLikeCnt();
        commentCnt = recipe.getCommentCnt();
        scrapCnt = recipe.getScrapCnt();

        authorUser = new UserGeneralResDTO(recipe.getUser());
    }
}
