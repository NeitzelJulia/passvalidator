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

}