package spring.security.security.jwt;

import io.jsonwebtoken.Jwts;
import java.util.Date;
import javax.crypto.SecretKey;
import org.springframework.stereotype.Component;

@Component
public class JwtUtils {

  // private static final long EXPIRATION_TIME = 86400000; // 1 hari
  private static final long EXPIRATION_TIME = 10000; // 10 detik

  SecretKey key = Jwts.SIG.HS256.key().build();

  public String createToken(String username) {
    Date now = new Date();
    Date expiryDate = new Date(now.getTime() + EXPIRATION_TIME);
    String jws = Jwts
      .builder()
      .claim("hellow", "wolrddd")
      .subject(username)
      .expiration(expiryDate)
      .signWith(key)
      .compact();
    // assert Jwts.parser().verifyWith(key).build().parseSignedClaims(jws).getPayload().getSubject().equals("Joe");
    return jws;
  }

  public Object parseToken(String token) {
    return Jwts.parser().verifyWith(key).build().parse(token);
  }
}
