package dev.syafii.triabsensi.utils;

public class ValidationUtils {
    public static boolean isEmpty(String string){
        boolean isValid = false;
        if (string == null){
            isValid = true;
        }else if (string.equals("")){
            isValid = true;
        }
        return isValid;
    }

    public static boolean isValidNik(String string){
        boolean isValid = true;
        if (isEmpty(string)){
            isValid = false;
        }else if (string.length() < 7){
            isValid = false;
        }
        return isValid;
    }

    public static boolean isValidPassword(String string){
        boolean isValid = true;
        if (isEmpty(string)){
            isValid = false;
        }else if (string.length() < 6){
            isValid = false;
        }
        return isValid;
    }
}
