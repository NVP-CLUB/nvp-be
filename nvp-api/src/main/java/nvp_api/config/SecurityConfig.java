package nvp_api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        // permitall() => 접근 모두 허용
        // hasRole => 하나의 권한만 접근 가능하게 설정
        // hasAnyRole => 여러 개의 권한 접근 가능하게 설정 가능

        // CORS 설정
        http.cors(cors -> cors
                .configurationSource(corsConfigurationSource()));

        // CSRF 설정 disable
        http.csrf(AbstractHttpConfigurer::disable)

                // 기본 인증 로그인 이용 X
                .httpBasic(AbstractHttpConfigurer::disable)

                        // JWT 사용을 위해 세션 사용 X (상태 유지 X) ++ 허용 로그인 수 2, 초과 동시 로그인시 기존 세션 만료
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                                // 임시 전체 허용
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/**").permitAll());

        // 시큐리티 자체 로그인, 로그아웃 비활성화
        http.formLogin((AbstractHttpConfigurer::disable));
        http.logout(AbstractHttpConfigurer::disable);

        return http.build();
    }

    @Bean
    public UrlBasedCorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);                                                                // 쿠키, 인증 정보 포함 허용
        config.setAllowedOriginPatterns(List.of("*"));                                              // 도메인 전체 허용
//        config.setAllowedOrigins(List.of("http://localhost:3000"));                                  // 허용 도메인 지정
        config.setAllowedHeaders(List.of("Authorization", "Content-Type", "Accept", "Refresh-Token"));   // 허용 헤더 제한
        config.setAllowedMethods(List.of("*"));                                                     // HTTP 메서드 전체 허용
//        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));                               // 허용 HTTP 메서드 제한
        config.setExposedHeaders(List.of("Authorization", "Refresh-Token"));                             // 클라이언트에서 접근 가능한 응답 헤더 지정

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}
