package util;

public class ValidatorUtil {

    public static boolean validatePhoneNumber(int phoneNumber){
        return String.valueOf(phoneNumber).length()==10 ;
    }

    public static boolean validateAadharNumber(String aadharNumber){
        return aadharNumber.length()==12 ;
    }

    public static boolean validatePanNumber(String panNumber){
        return panNumber.length()==10 ;
    }

    public static boolean validateVoterIDNumber(String voterId){
        return voterId.length()==10 ;
    }
}
