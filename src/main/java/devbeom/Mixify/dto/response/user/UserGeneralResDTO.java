package devbeom.Mixify.dto.response.user;

import devbeom.Mixify.domain.User;
import lombok.Getter;

@Getter
public class UserGeneralResDTO {
    private final Long id;

    private final String username;

    private final float userStar;

    public UserGeneralResDTO(User user) {
        id = user.getId();
        username = user.getUsername();
        userStar = user.getUserStar();
    }
}
