package devbeom.Mixify.dto.controller.user;

import devbeom.Mixify.domain.User;
import lombok.Getter;

@Getter
public class UserControllerForScrapDTO extends UserControllerParentDTO{
    private String username;

    public void toDTO(User user) {
        this.username = user.getUsername();
    }
}
