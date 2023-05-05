package devbeom.Mixify.dto.request.recipe;

import devbeom.Mixify.domain.Recipe;
import devbeom.Mixify.domain.User;
import devbeom.Mixify.dto.request.ingredient.IngredientGeneralReqDTO;
import devbeom.Mixify.dto.request.step.StepGeneralReqDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
public class RecipeGeneralReqDTO {

    @NotBlank
    @Size(max = 50)
    private String title;

    private String thumbnail;

    // 아무래도 해당 클래스의 toEntity 메서드에 의존하는 것보단 직접 User를 가져와서 꽂아주는 게 제일 안전할 듯.
//    private UserGeneralReqDTO user;
    @NotNull
    private Long userId;

    // 프론트 개발 편의성을 위해 연관관계 주인을 여기서 받음
    // DTO 받은 이후에 리스트에 있는 요소들을 엔티티로 바꾼 후 일일히 저장해주는 로직 필요.
    private List<StepGeneralReqDTO> steps;

    // 프론트 개발 편의성을 위해 연관관계 주인을 여기서 받음
    // DTO 받은 이후에 리스트에 있는 요소들을 엔티티로 바꾼 후 일일히 저장해주는 로직 필요.
    private List<IngredientGeneralReqDTO> ingredients;

    public Recipe toEntity(User user) {
        return Recipe.builder()
                .title(title)
                .thumbnail(thumbnail)
                .user(user)
                .build();
    }
}
