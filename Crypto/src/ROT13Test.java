
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ROT13Test {


    @Test
    public void rotateStringTest0() {
        // Given
        String s1 = "ABCDEF";
        String s2 = "ABCDEF";

        // When
        ROT13 cipher = new ROT13();
        String actual = cipher.rotate(s1, 'A');

        // Then
        assertTrue(actual.equals(s2));
    }

    @Test
    public void rotateStringTest1() {
        // Given
        String s1 = "ABCDEF";
        String s2 = "DEFABC";

        // When
        ROT13 cipher = new ROT13();
        String actual = cipher.rotate(s1, 'D');

        // Then
        assertTrue(actual.equals(s2));
    }

    @Test
    public void rotateStringTest2() {
        // Given
        String s1 = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String s2 = "NOPQRSTUVWXYZABCDEFGHIJKLM";

        // When
        ROT13 cipher = new ROT13();
        String actual = cipher.rotate(s1, 'N');
        System.out.println(s1);
        System.out.println(actual);
        // Then
        assertTrue(actual.equals(s2));
    }

    @Test
    public void cryptTest1() {
        // Given
        ROT13 cipher = new ROT13('a', 'n');

        String Q1 = "Why did the chicken cross the road?";
        String A1 = "Jul qvq gur puvpxra pebff gur ebnq?";

        String Q2 = "Gb trg gb gur bgure fvqr!";
        String A2 = "To get to the other side!";

        // When
        String actual = cipher.encrypt(Q1);
        System.out.println(Q1);
        System.out.println(A1);
        // Then
        assertTrue(actual.equals(A1));

        // When
        String actual2 = cipher.decrypt(Q2);
        System.out.println(Q2);
        System.out.println(A2);
        // Then
        assertTrue(actual2.equals(A2));
    }
    @Test
    public void cryptTest2() {
        // Given
        ROT13 cipher = new ROT13('a', 'n');

        String Q1 = "Why did the chicken cross the road?";
        System.out.println(Q1);

        // When
        String actual = cipher.crypt(cipher.crypt(Q1));
        System.out.println(actual);
        // Then
        assertTrue(actual.equals(Q1));
    }

    @Test
    public void encryptFileTest1() {
        ROT13 rot13 = new ROT13();
//        rot13.encryptFile(new File("/Users/jasong/Labs/ZCW-ORM-SimpleAccount/.idea/EnCryptMe.txt"));
        String expected = rot13.encrypt("This file needs to be read and encrypted. " +
                "It can also be used for file reading practice!");
        String actual = rot13.encryptFile(new File("/Users/jasong/Labs/ZCW-ORM-SimpleAccount/.idea/EnCryptMe.txt"), "BogusFile");
        assertEquals(expected, actual);
    }

    @Test
    public void encryptFileTest2() {
        ROT13 rot13 = new ROT13();
        File file = new File("/Users/jasong/Labs/ZCW-ORM-SimpleAccount/.idea/EnCryptMe.txt");
//        rot13.encryptFile(new File("/Users/jasong/Labs/ZCW-ORM-SimpleAccount/.idea/EnCryptMe.txt"));
        String expected = rot13.encrypt("This file needs to be read and encrypted. " +
                                "It can also be used for file reading practice!");
        String actual = rot13.encryptFile(file, "BogusFile");
        assertEquals(expected, actual);
    }

    @Test
    void encryptWithOffsetTest() {
        ROT13 rot13 = new ROT13();
        String text = "abcdefghi";
        String expected = "nppqsstvv";
        String actual = rot13.cryptOffset(text, 4);
        assertEquals(expected, actual);
    }
}