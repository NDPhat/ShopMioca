package Utils;
import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
public class TokenUltils {
    private static String SecretKey="IloveyouIloveyouIloveyouIloveyouIloveyouIloveyouIloveyouIloveyou";

    private static String EndcodeBase64(String value){
        byte[] encodedBytes = Base64.getEncoder().encode(value.getBytes());
        return new String(encodedBytes);
    }
    private static String DecodeBase64(String value){
        byte[] decodedBytes = Base64.getDecoder().decode(value);
        return new String(decodedBytes);
    }

    private static String HashKey(String value){
        String hash = Hashing.hmacSha256(SecretKey.getBytes(StandardCharsets.UTF_8)).hashString(value, StandardCharsets.UTF_8).toString();
        return hash;
    }

    public static String getToken(String email){
        String token ="";
        String emailtext = EndcodeBase64(email);
        String emailHash = HashKey(emailtext);
        return emailtext+"."+emailHash;
    }

    public static String getPlanText(String token){
        String[] parts = token.split("\\.");
        System.out.println(token);
        System.out.println(parts);
        if(token.length() <=5 || token == null || token.indexOf(".") < 0 || parts.length != 2)
            return null;

        String emailHash = HashKey(parts[0]);
        if(emailHash.equals(parts[1]))
            return DecodeBase64(parts[0]);
        return null;
    }
}
