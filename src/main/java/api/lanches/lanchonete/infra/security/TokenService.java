package api.lanches.lanchonete.infra.security;

import api.lanches.lanchonete.modules.user.infra.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String apiSecret;
    public String generateToken(User user) {
        try {
            var algorithm = Algorithm.HMAC256(apiSecret);
            var expires = LocalDateTime.now().plusHours(1).toInstant(ZoneOffset.of("-03:00"));

            return JWT.create()
                    .withIssuer("API LANCHONETE")
                    .withSubject(user.getLogin())
                    .withExpiresAt(expires)
                    .sign(algorithm);
        } catch(JWTCreationException exception) {
            throw new RuntimeException("Erro ao gerar token JWT", exception);
        }
    }

    public String getSubject(String tokenJWT) {
        try {
            var algorithm = Algorithm.HMAC256(apiSecret);

            return JWT.require(algorithm)
                    .withIssuer("API LANCHONETE")
                    .build()
                    .verify(tokenJWT)
                    .getSubject();
        } catch(JWTVerificationException exception) {
            throw new RuntimeException("Token JWT invalido", exception);
        }
    }


}
