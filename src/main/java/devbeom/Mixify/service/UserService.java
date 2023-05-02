package devbeom.Mixify.service;

import devbeom.Mixify.domain.Authority;
import devbeom.Mixify.domain.User;
import devbeom.Mixify.dto.jwt.user.UserJwtDTO;
import devbeom.Mixify.jwt.exception.DuplicateMemberException;
import devbeom.Mixify.jwt.exception.NotFoundMemberException;
import devbeom.Mixify.jwt.utils.SecurityUtil;
import devbeom.Mixify.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public UserJwtDTO signup(UserJwtDTO userDto) {
        if (userRepository.findOneWithAuthoritiesByUserId(userDto.getUsername()).orElse(null) != null) {
            throw new DuplicateMemberException("이미 가입되어 있는 유저입니다.");
        }

        Authority authority = Authority.builder()
                .authorityName("ROLE_USER")
                .build();

        User user = User.builder()
                .userId(userDto.getUserId())
                .userPw(passwordEncoder.encode(userDto.getUserPw()))
                .username(userDto.getUsername())
                .authorities(Collections.singleton(authority))
                .activated(true)
                .build();

        return UserJwtDTO.from(userRepository.save(user));
    }

    @Transactional(readOnly = true)
    public UserJwtDTO getUserWithAuthorities(String username) {
        return UserJwtDTO.from(userRepository.findOneWithAuthoritiesByUserId(username).orElse(null));
    }

    @Transactional(readOnly = true)
    public UserJwtDTO getMyUserWithAuthorities() {
        return UserJwtDTO.from(
                SecurityUtil.getCurrentUsername()
                        .flatMap(userRepository::findOneWithAuthoritiesByUserId)
                        .orElseThrow(() -> new NotFoundMemberException("Member not found"))
        );
    }
}
