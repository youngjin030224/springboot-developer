package me.scpark.springdeveloper.config.jwt;

import io.jsonwebtoken.Jwts;
import me.scpark.springdeveloper.dao.User;
import me.scpark.springdeveloper.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class TokenProviderTest {
    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtProperties jwtProperties;

    @DisplayName("GenerateToken() : 유저 정보와 만료 기간을 전달해 토큰을 생성")
    @Test
    void generateToken(){
        //given
        User testUser = userRepository.save(User.builder().email("user@gmail.com").password("test").build());
        //when
        String token = tokenProvider.generateToken(testUser, Duration.ofDays(14));
        //then
        Long userId = Jwts.parser().setSigningKey(jwtProperties.getSecretKet())
                .parseClaimsJws(token)
                .getBody()
                .get("id",Long.class);
        assertThat(userId).isEqualTo(testUser.getId());
    }
    @DisplayName("validToken(): 만료된 토큰인 경우에 유효성 검증에 실패")
    @Test
    void validToken_invalidToken(){
        //given
        String token = JwtFactory.builder()
                .expiresAt(new Date(new Date().getTime() - Duration.ofDays(7).toMillis()))
                .build().createToken(jwtProperties);
        //when
        boolean result = tokenProvider.validateToken(token);

        //then
        assertThat(result).isFalse();
    }

    @DisplayName("validToken() : 유효한 토큰인 경우에 유효성 검증에 성공함")
    @Test
    void validToken_validToken(){
        //given
        String token = JwtFactory.withDefaultValues().createToken(jwtProperties);
        //when
        boolean result = tokenProvider.validateToken(token);
        //then
        assertThat(result).isTrue();
    }
}
