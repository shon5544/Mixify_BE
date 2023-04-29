package devbeom.Mixify.dto.service.user;

import devbeom.Mixify.domain.User;

public abstract class UserServiceParentDTO {
//    private String userId;
//    private String userPw;
//    private float userStar;

    public abstract User toEntity();
//    {
//        return User.builder()
//                .userId(this.userId)
//                .userPw(this.userPw)
//                .userStar(this.userStar)
//                .build();
//    }
}
