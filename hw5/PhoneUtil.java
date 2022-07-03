package hw5;
import java.math.BigInteger;
import java.util.Map;

//P1 - add a 1 to the beginning of each 10-digit BigInteger
public class PhoneUtil {
    public static void prependOne(Map<String, BigInteger> m){
        m.forEach((key, value) -> {
            m.replace(key, new BigInteger("1%s".formatted(value)));
        });
    }
}

































