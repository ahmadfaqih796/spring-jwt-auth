package spring.security.services;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import spring.security.dto.request.RegisterRequest;
import spring.security.models.User;
import spring.security.repository.UserRepository;
import spring.security.utils.FindModel;

@Service
public class AuthService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private FindModel findModel;

  public ResponseEntity<Object> register(RegisterRequest registerRequest) {
    Optional<User> user = userRepository.findByUsername(
      registerRequest.getUsername()
    );
    if (user.isPresent()) {
      return ResponseEntity.badRequest().body("Username already exists");
    }
    User userPayload = new User();
    userPayload.setUsername(registerRequest.getUsername());
    userPayload.setEmail(registerRequest.getEmail());
    userPayload.setPassword(registerRequest.getPassword());
    userPayload.setRole(findModel.getRoleById(registerRequest.getRoleId()));
    userRepository.save(userPayload);
    return ResponseEntity.ok().body("User registered successfully");
  }
}
