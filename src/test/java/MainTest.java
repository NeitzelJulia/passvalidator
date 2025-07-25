import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void testCheckPasswordLength_exactlyEightCharacters(){
        assertTrue(Main.checkPasswordLength("Abcdef12"));
    }

    @Test
    void testCheckPasswordLength_moreThanEightCharacters(){
        assertTrue(Main.checkPasswordLength("Password123"));
    }

    @Test
    void testCheckPasswordLength_lessThanEightCharacters(){
        assertFalse(Main.checkPasswordLength("Pass1"));
    }

    @Test
    void testHasDigit_withDigit() {
        assertTrue(Main.hasDigit("abc123XYZ"));
    }

    @Test
    void testHasDigit_noDigit() {
        assertFalse(Main.hasDigit("abcdefXYZ"));
    }

    @Test
    void testHasDigit_digitAtEdges() {
        assertTrue(Main.hasDigit("1Password"));
        assertTrue(Main.hasDigit("Password9"));
    }

    @Test
    void testIsMixedCase_mixedCase() {
        assertTrue(Main.isMixedCase("AbCdEf"));
    }

    @Test
    void testIsMixedCase_onlyUpperCase() {
        assertFalse(Main.isMixedCase("PASSWORD"));
    }

    @Test
    void testIsMixedCase_onlyLowerCase() {
        assertFalse(Main.isMixedCase("password"));
    }

    @Test
    void testIsPasswordBlacklisted_withBlacklistedPassword() {
        assertTrue(Main.isBlacklistedPassword("passwort"));
    }

    @Test
    void testIsPasswordBlacklisted_caseInsensitive() {
        assertTrue(Main.isBlacklistedPassword("PassWort"));
    }

    @Test
    void testIsPasswordBlacklisted_withUniquePassword() {
        assertFalse(Main.isBlacklistedPassword("Un1qu3Pwd!"));
    }

    @Test
    void testHasSpecialCharacter_withSpecialCharacter() {
        assertTrue(Main.hasSpecialCharacter("abc!123"));
    }

    @Test
    void testHasSpecialCharacter_withMultipleSpecialCharacters() {
        assertTrue(Main.hasSpecialCharacter("a@b#c$d%"));
    }

    @Test
    void testHasSpecialCharacter_withLettersAndDigitsOnly() {
        assertFalse(Main.hasSpecialCharacter("abc123XYZ"));
    }

    @Test
    void testGeneratePassword_with8Characters() {
        String password = Main.generatePassword(8);
        assertEquals(8, password.length());
        assertTrue(Main.checkPasswordLength(password));
        assertTrue(Main.hasDigit(password));
        assertTrue(Main.isMixedCase(password));
        assertTrue(Main.hasSpecialCharacter(password));
        assertFalse(Main.isBlacklistedPassword(password));
    }

    @Test
    void testGeneratePassword_with12Characters() {
        String password = Main.generatePassword(12);
        assertEquals(12, password.length());
        assertTrue(Main.checkPasswordLength(password));
        assertTrue(Main.hasDigit(password));
        assertTrue(Main.isMixedCase(password));
        assertTrue(Main.hasSpecialCharacter(password));
        assertFalse(Main.isBlacklistedPassword(password));
    }
}