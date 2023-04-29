package devbeom.Mixify.dto.controller.user;

import devbeom.Mixify.domain.User;

public abstract class UserControllerParentDTO {
    public abstract void toDTO(User user);
}
