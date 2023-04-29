package devbeom.Mixify.dto.controller.user;

import devbeom.Mixify.domain.User;

public abstract class UserControllerParentDTO {
    abstract public void toDTO(User user);
}
