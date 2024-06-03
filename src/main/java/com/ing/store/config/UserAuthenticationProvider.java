package com.ing.store.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.ing.store.dto.UserDTO;
import com.ing.store.service.authentication.UserService;
import jakarta.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Slf4j
public class UserAuthenticationProvider {
  @Value("${jwt.secret}")
  private String secretKey;
  @Value("${jwt.expirationTime}")
  private String expirationTime;
  private final UserService userService;

  @PostConstruct
  protected void init() {
    secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
  }

  public String createToken(UserDTO user) {
    Date now = new Date();
    Date validity = new Date(now.getTime() + expirationTime);

    Algorithm algorithm = Algorithm.HMAC256(secretKey);
    log.info("Generating JWT token for new user DTO");
    return JWT.create()
        .withSubject(user.getUsername())
        .withIssuedAt(now)
        .withExpiresAt(validity)
        .withClaim("ROLE_", user.getRole().name())
        .sign(algorithm);
  }

  public Authentication validateToken(String token) {
    Algorithm algorithm = Algorithm.HMAC256(secretKey);

    JWTVerifier verifier = JWT.require(algorithm)
        .build();

    DecodedJWT decoded = verifier.verify(token);

    UserDTO user = userService.findByUsername(decoded.getSubject());

    return new UsernamePasswordAuthenticationToken(user, null, Arrays.asList(user.getRole()));
  }
}
