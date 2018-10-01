package sawczuk.AutoCenter.util;

public class VinChecker {

    private static int transliterate(char c) {
        return "0123456789.ABCDEFGH..JKLMN.P.R..STUVWXYZ".indexOf(c) % 10;
    }

    private static char getCheckDigit(String vin) {
        String map = "0123456789X";
        String weights = "8765432X098765432";
        int sum = 0;
        for (int i = 0; i < 17; ++i) {
            sum += transliterate(vin.charAt(i)) * map.indexOf(weights.charAt(i));
        }
        return map.charAt(sum % 11);
    }

    public static boolean validate(String vin) {
        if (vin.length() != 17) return false;
        return getCheckDigit(vin) == vin.charAt(8);
    }
}