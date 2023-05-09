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

    @NotBlank(message = "제목은 반드시 들어가있어야합니다.")
    @Size(max = 50)
    private String title;

    @NotBlank(message = "썸네일은 반드시 넣어주셔야 합니다.")
    private String thumbnail;

    // 아무래도 해당 클래스의 toEntity 메서드에 의존하는 것보단 직접 User를 가져와서 꽂아주는 게 제일 안전할 듯.
//    private UserGeneralReqDTO user;
    @NotNull(message = "userId에 대한 정보가 없습니다.")
    private Long userId;

    // 프론트 개발 편의성을 위해 연관관계 주인을 여기서 받음
    // DTO 받은 이후에 리스트에 있는 요소들을 엔티티로 바꾼 후 일일히 저장해주는 로직 필요.
    @NotNull(message = "레시피의 단계는 누락되어선 안 됩니다.")
    @Size(min = 1, message = "레시피의 단계는 적어도 한 개 이상이어야 합니다.")
    private List<StepGeneralReqDTO> steps;

    // 프론트 개발 편의성을 위해 연관관계 주인을 여기서 받음
    // DTO 받은 이후에 리스트에 있는 요소들을 엔티티로 바꾼 후 일일히 저장해주는 로직 필요.
    @NotNull(message = "레시피의 재료는 누락되어선 안 됩니다.")
    @Size(min = 1, message = "재료는 무조건 1개 이상이어야 합니다.")
    private List<IngredientGeneralReqDTO> ingredients;

    public Recipe toEntity(User user) {
        return Recipe.builder()
                .title(title)
                .thumbnail(thumbnail)
                .user(user)
                .build();
    }
}
