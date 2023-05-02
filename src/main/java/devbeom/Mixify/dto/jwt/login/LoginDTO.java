package devbeom.Mixify.dto.jwt.login;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginDTO {

    @NotNull
    @Size(min = 8, max = 20)
    private String userId;

    @NotNull
    @Size(min = 8, max = 20)
    private String userPw;
}
