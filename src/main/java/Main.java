import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Main {

    private static final String RAW_URL =
            "https://raw.githubusercontent.com/danielmiessler/SecLists/"
                    + "master/Passwords/Common-Credentials/"
                    + "Language-Specific/German_Pwdb_common-password-list-top-150.txt";
    private static final String[] BLACKLIST = loadCommonPasswords();
    private static final String UPPER    = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWER    = "abcdefghijklmnopqrstuvwxyz";
    private static final String DIGITS   = "0123456789";
    private static final String SPECIALS = "!@#$%^&*()-_+=<>?";
    private static final String ALL = UPPER + LOWER + DIGITS + SPECIALS;
    private static final SecureRandom RANDOM = new SecureRandom();

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

    public static boolean isBlacklistedPassword(String password) {
        return Arrays.asList(BLACKLIST)
                .contains(password.toLowerCase());
    }

    public static boolean hasSpecialCharacter(String password) {
        return password.matches(".*[^A-Za-z0-9].*");
    }


    public static String[] loadCommonPasswords() {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(RAW_URL))
                .build();
        try {
            String body = client.send(request, HttpResponse.BodyHandlers.ofString()).body();
            return Arrays.stream(body.split("\\R"))
                    .map(String::trim)
                    .filter(s -> !s.isEmpty() && !s.startsWith("#"))
                    .map(String::toLowerCase)
                    .toArray(String[]::new);
        } catch (Exception e) {
            System.err.println("Error loading remote passwords: " + e.getMessage());
            return new String[0];
        }
    }

    /**
     *
     * @param length minimum length = 8
     */
    public static String generatePassword(int length) {
        if (length < 8) {
            throw new IllegalArgumentException(
                    "length must be at least 8");
        }

        ArrayList<Character> chars = new ArrayList<>();

        chars.add(UPPER.charAt(RANDOM.nextInt(UPPER.length())));
        chars.add(LOWER.charAt(RANDOM.nextInt(LOWER.length())));
        chars.add(DIGITS.charAt(RANDOM.nextInt(DIGITS.length())));
        chars.add(SPECIALS.charAt(RANDOM.nextInt(SPECIALS.length())));

        for (int i = chars.size(); i < length; i++) {
            chars.add(ALL.charAt(RANDOM.nextInt(ALL.length())));
        }
        Collections.shuffle(chars, RANDOM);

        StringBuilder stringBuilder = new StringBuilder();
        for (char c : chars) stringBuilder.append(c);

        String password = stringBuilder.toString();

        if (isBlacklistedPassword(password)) {
            password = generatePassword(length);
        }

        return password;
    }
}
