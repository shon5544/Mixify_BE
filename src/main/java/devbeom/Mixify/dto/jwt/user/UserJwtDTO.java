package devbeom.Mixify.dto.jwt.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import devbeom.Mixify.domain.User;
import devbeom.Mixify.dto.jwt.auth.AuthorityDTO;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class UserJwtDTO {

    @NotNull
    @Size(min = 8, max = 20)
    private String userId;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull
    @Size(min = 8, max = 20)
    private String userPw;

    @NotNull
    @Size(min = 8, max = 20)
    private String username;

    private Set<AuthorityDTO> authorityDtoSet;

    public static UserJwtDTO from(User user) {
        if(user == null) return null;

        return UserJwtDTO.builder()
                .userId(user.getUserId())
                .username(user.getUsername())
                .authorityDtoSet(user.getAuthorities().stream()
                        .map(authority -> AuthorityDTO.builder().authorityName(authority.getAuthorityName()).build())
                        .collect(Collectors.toSet()))
                .build();
    }
}
