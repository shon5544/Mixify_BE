package devbeom.Mixify.dto.response.user;

import devbeom.Mixify.domain.User;
import lombok.Getter;

@Getter
public class UserGeneralResDTO {
    private final Long userPk;

    private final String username;

    private final float userStar;

    public UserGeneralResDTO(User user) {
        userPk = user.getId();
        username = user.getUsername();
        userStar = user.getUserStar();
    }
}
