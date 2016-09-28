package core.utils;

public class StringUtils {

     public String replaceNull(String input) {
        if (input == null || input.equals(null) || input.equals("null") || input.equals("") || input.equals("[]")) {
            return "";
        } else {
            return input;
        }
    }
}
