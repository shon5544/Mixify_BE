package devbeom.Mixify.dto.controller.user;

import devbeom.Mixify.domain.User;
import devbeom.Mixify.dto.controller.user.UserControllerParentDTO;
import lombok.Getter;

@Getter
public class UserControllerForRecipeDTO extends UserControllerParentDTO {
    private Long id;
    private String username;
    private float userStar;

    public void toDTO(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.userStar = user.getUserStar();
    }
}
