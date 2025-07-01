public class Main {
    public static void main(String[] args) {

    }

    public static boolean checkPasswordLength(String password) {
        return password.length() >= 8;
    }

    public static boolean hasDigit(String password) {
        return password.matches(".*\\d.*");
    }

    public static boolean isMixedCase(String password) {
        return (password.matches(".*[a-z].*") && password.matches(".*[A-Z].*"));
    }

    //Todo isBlacklistedPassword
    public static boolean hasSpecialCharacters(String password) {
        return true;
    }
}
