package spring.security.security.jwt;

import io.jsonwebtoken.Jwts;
import javax.crypto.SecretKey;

public class JwtUtils {

  public String createToken(String username) {
    SecretKey key = Jwts.SIG.HS256.key().build();
    String jws = Jwts.builder().subject("Joe").signWith(key).compact();
    return jws;
  }
}
