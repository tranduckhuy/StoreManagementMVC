
package util;

/**
 *
 * @author ADMIN
 */
public class Validation{
    
//    public static <T extends Number> boolean checkPrice (T price) {
//        return price.doubleValue() >= 0;
//    }
    
    public static String checkString(String s) {
        s = s.replaceAll("\\s+", " ").trim();
        if(!s.isEmpty()) {
            return s;
        }
        return null;
    }
    
    
}
