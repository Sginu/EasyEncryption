import javax.crypto.*;
import java.security.SecureRandom;
import java.util.Base64;

public class AESEncryption {

    static Cipher cipher = null;

    public static void main(String[] args)  {

       try{
           String plainText = "AES symmetric encryption";
           System.out.println("Input string : "+plainText);

           KeyGenerator keyGen = KeyGenerator.getInstance("AES");
           keyGen.init(128);
           SecretKey secret = keyGen.generateKey();

           cipher = Cipher.getInstance("AES");

           String encryptedString = encrypt(plainText, secret);
           System.out.println("Encrypted string : "+encryptedString);

           String decryptedString = decrypt(encryptedString, secret);
           System.out.println("Decrypted string : "+decryptedString);

       }catch (Exception e){
           System.out.println("Exception while encrypt or decrypt:"+e);
       }

    }

   private static String encrypt(String inputString,  SecretKey secret ){

       try {
           byte[] inputByte = inputString.getBytes();
           cipher.init(Cipher.ENCRYPT_MODE, secret);
           byte[] encryptedByte = cipher.doFinal(inputByte);
           Base64.Encoder encoder = Base64.getEncoder();
           String encodedString = encoder.encodeToString(encryptedByte);
           return  encodedString;
       } catch (Exception e) {
           throw new RuntimeException(e);
       }

    }

    public static String decrypt(String encodedString, SecretKey secret ){
        try {
            Base64.Decoder decoder = Base64.getDecoder();
            byte[] decodedBytes = decoder.decode(encodedString);

            cipher.init(Cipher.DECRYPT_MODE, secret);
            byte[] decryptedByte  = cipher.doFinal(decodedBytes);
            String decryptedString = new String(decryptedByte);
            return decryptedString;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
