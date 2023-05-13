package devbeom.Mixify.jwt.config;

import devbeom.Mixify.jwt.JwtAccessDeniedHandler;
import devbeom.Mixify.jwt.JwtAuthenticationEntryPoint;
import devbeom.Mixify.jwt.JwtSecurityConfig;
import devbeom.Mixify.jwt.TokenProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final TokenProvider tokenProvider;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

    public SecurityConfig(
            TokenProvider tokenProvider,
            JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint,
            JwtAccessDeniedHandler jwtAccessDeniedHandler
    ) {
        this.tokenProvider = tokenProvider;
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
        this.jwtAccessDeniedHandler = jwtAccessDeniedHandler;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // 토큰을 사용하기 때문에 csrf 설정은 disable.
                .csrf().disable()

                // exceptionHandling은 직접 만든 핸들러 클래스들을 추가해준다.
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(jwtAccessDeniedHandler)

                //h2 콘솔을 위한 설정
//                .and()
//                .headers()
//                .frameOptions()
//                .sameOrigin()

                // 세션을 사용하지 않기 때문에 세션설정을 stateless로 설정.
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                //HttpServletRequest를 사용하는 모든 요청에 대한 접근 제한을 설정하겠다.
                .authorizeHttpRequests()

                //해당 url에 대한 요청은 인증없이 접근을 허용하겠다는 뜻
                .requestMatchers("/signup", "/auth/authenticate").permitAll()
                .requestMatchers("/recipe/get/**").permitAll()
//                .requestMatchers(request -> request
//                        .mvcMatchers("/api/signup", "/auth/authenticate", "/get/recipe/**")
//                        .permitAll()
//                        .anyRequest()
//                        .authenticated()
//                ).permitAll()

                //그 외의 나머지 request 들은 모두 인증되어야 한다는 의미.
                .anyRequest().authenticated()

                .and()
                .apply(new JwtSecurityConfig(tokenProvider));

        return http.build();
    }
}
