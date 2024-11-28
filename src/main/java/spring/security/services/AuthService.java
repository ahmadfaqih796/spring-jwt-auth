package spring.security.services;

import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import spring.security.dto.request.RegisterRequest;
import spring.security.models.User;
import spring.security.repository.UserRepository;
import spring.security.security.Crypto;
import spring.security.utils.FindModel;

@Service
public class AuthService {

  private final UserRepository userRepository;
  private final FindModel findModel;
  private final Crypto crypto;

  // Field Injection
  // tidak disarankan untuk di gunakan
  // @Autowired
  // private UserRepository userRepository;

  // @Autowired
  // private FindModel findModel;

  // @Autowired
  // private Crypto crypto;

  // Constructor Injection
  public AuthService(
    UserRepository userRepository,
    FindModel findModel,
    Crypto crypto
  ) {
    this.userRepository = userRepository;
    this.findModel = findModel;
    this.crypto = crypto;
  }

  public ResponseEntity<Object> register(RegisterRequest registerRequest) {
    Optional<User> user = userRepository.findByUsername(
      registerRequest.getUsername()
    );
    if (user.isPresent()) {
      return ResponseEntity.badRequest().body("Username already exists");
    }
    try {
      User userPayload = new User();
      userPayload.setUsername(registerRequest.getUsername());
      userPayload.setEmail(registerRequest.getEmail());
      userPayload.setPassword(crypto.encrypt(registerRequest.getPassword()));
      userPayload.setRole(findModel.getRoleById(registerRequest.getRoleId()));
      userRepository.save(userPayload);
      return ResponseEntity.ok().body("User registered successfully");
    } catch (Exception e) {
      return ResponseEntity
        .status(500)
        .body("Encryption error: " + e.getMessage());
    }
  }
}
