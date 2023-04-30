package devbeom.Mixify.jwt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                //HttpServletRequest를 사용하는 모든 요청에 대한 접근 제한을 설정하겠다.
                .authorizeHttpRequests()

                //해당 url에 대한 요청은 인증없이 접근을 허용하겠다는 뜻
                .requestMatchers("/get/recipes").permitAll()

                //그 외의 나머지 request 들은 모두 인증되어야 한다는 의미.
                .anyRequest().authenticated();

        return http.build();
    }
}
