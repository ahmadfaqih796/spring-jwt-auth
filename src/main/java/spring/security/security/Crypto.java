package spring.security.security;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import org.bouncycastle.crypto.BufferedBlockCipher;
import org.bouncycastle.crypto.engines.AESLightEngine;
import org.bouncycastle.crypto.modes.CBCBlockCipher;
import org.bouncycastle.crypto.paddings.PaddedBufferedBlockCipher;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.util.encoders.Hex;
import org.springframework.stereotype.Component;

// wajib tambahin annotasi @Component apabila tanpa konfigurasi di AppConfig dengan anotasi @Bean
@Component
public class Crypto {

  private static final String KEY_GENERATE =
    "2d38e01c52c0d9c475dac174eff7cea6464778ec35f3d78eba5c1421d684a3e6";

  private static final Integer BLOCK_SIZE = 16;

  public String encrypt(String plaintext) throws Exception {
    byte[] key = Hex.decode(KEY_GENERATE);
    byte[] iv = generateIV();

    @SuppressWarnings("deprecation")
    BufferedBlockCipher cipher = new PaddedBufferedBlockCipher(
      new CBCBlockCipher(new AESLightEngine())
    );
    cipher.init(true, new ParametersWithIV(new KeyParameter(key), iv));

    byte[] inputBytes = plaintext.getBytes(StandardCharsets.UTF_8);
    byte[] encryptedBytes = processCipher(cipher, inputBytes);

    byte[] result = new byte[iv.length + encryptedBytes.length];
    System.arraycopy(iv, 0, result, 0, iv.length);
    System.arraycopy(
      encryptedBytes,
      0,
      result,
      iv.length,
      encryptedBytes.length
    );

    return Hex.toHexString(result);
  }

  public String decrypt(String ciphertextHex) throws Exception {
    byte[] key = Hex.decode(KEY_GENERATE);
    byte[] ciphertextWithIV = Hex.decode(ciphertextHex);

    byte[] iv = new byte[BLOCK_SIZE];
    byte[] ciphertext = new byte[ciphertextWithIV.length - BLOCK_SIZE];
    System.arraycopy(ciphertextWithIV, 0, iv, 0, BLOCK_SIZE);
    System.arraycopy(
      ciphertextWithIV,
      BLOCK_SIZE,
      ciphertext,
      0,
      ciphertext.length
    );

    @SuppressWarnings("deprecation")
    BufferedBlockCipher cipher = new PaddedBufferedBlockCipher(
      new CBCBlockCipher(new AESLightEngine())
    );
    cipher.init(false, new ParametersWithIV(new KeyParameter(key), iv));

    byte[] decryptedBytes = processCipher(cipher, ciphertext);

    return new String(decryptedBytes, StandardCharsets.UTF_8);
  }

  private byte[] processCipher(BufferedBlockCipher cipher, byte[] input)
    throws Exception {
    byte[] output = new byte[cipher.getOutputSize(input.length)];
    Integer outputLength = cipher.processBytes(
      input,
      0,
      input.length,
      output,
      0
    );
    outputLength += cipher.doFinal(output, outputLength);

    byte[] result = new byte[outputLength];
    System.arraycopy(output, 0, result, 0, outputLength);
    return result;
  }

  private byte[] generateIV() {
    byte[] iv = new byte[BLOCK_SIZE];
    new SecureRandom().nextBytes(iv);
    return iv;
  }
  // public static void main(String[] args) {
  //   try {
  //     String plaintext = "ahmad faqih arifin";

  //     System.out.println("Plaintext: " + plaintext);
  //     Crypto crypto = new Crypto();
  //     String encrypted = crypto.encrypt(plaintext);
  //     System.out.println("Encrypted: " + encrypted);

  //     String decrypted = crypto.decrypt(encrypted);
  //     System.out.println("Decrypted: " + decrypted);
  //   } catch (Exception e) {
  //     e.printStackTrace();
  //   }
  // }
}
