public class Main {
    public static void main(String[] args) {

    }

    public static boolean checkPasswordLength(String password) {
        return password.length() >= 8;
    }

    public static boolean hasDigit(String password) {
        return password.matches(".*\\d.*");
    }

    //ToDo hasUpperAndLowerCase
    public static boolean hasUpperAndLowerCase(String password) {
        return true;
    }

    //Todo isBlacklistedPassword
    public static boolean hasSpecialCharacters(String password) {
        return true;
    }
}
