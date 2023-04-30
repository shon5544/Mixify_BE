package devbeom.Mixify.dto.jwt.user;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class UserJwtDTO {

    @NotNull
    @Size(min = 8, max = 20)
    private String userId;

    @NotNull
    @Size(min = 8, max = 20)
    private String userPw;

    @NotNull
    @Size(min = 8, max = 20)
    private String username;
}
