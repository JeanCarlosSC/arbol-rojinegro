package lib.sRAD.logic;

public class Extension {

    public static Boolean isInt(String string) {
        try {
            Integer.parseInt(string);
            return true;
        }catch (NumberFormatException numberFormat){
            return false;
        }
    }

}
