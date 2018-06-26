import java.io.*;
import java.util.ArrayList;
import java.util.logging.Logger;

import static java.lang.Character.isLowerCase;
import static java.lang.Character.isUpperCase;
import static java.lang.Character.toLowerCase;

public class ROT13 {

    private int shifter;

    public ROT13(Character cs, Character cf) { shifter = cf - cs; }

    public ROT13() { this.shifter = 13; }

    public String crypt(String text) throws UnsupportedOperationException
    {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < text.length(); i++)
        {
            sb = (!Character.isLetter(text.charAt(i))) ? sb.append(text.charAt(i)) :
                    (Character.isUpperCase(text.charAt(i))) ?
                            sb.append((char) (((int) text.charAt(i) + shifter - 65) % 26 + 65)) :
                            sb.append((char) (((int) text.charAt(i) + shifter - 97) % 26 + 97));
        }
        return sb.toString();
    }


    public String encrypt(String text) { return crypt(text); }

    public String decrypt(String text) { return crypt(text); }

    public static String rotate(String s, Character c)
    {
        String rotated = s.substring(s.indexOf(c)) + s.substring(0, s.indexOf(c)); return rotated;
    }

    public String encryptFile(File fileIn, String fileOut)
    {
        String fileInput;  StringBuilder builder = new StringBuilder();  BufferedReader reader = null;

        try { reader = new BufferedReader(new FileReader(fileIn));

            while ((fileInput = reader.readLine()) != null) { builder.append(crypt(fileInput)); } }

          catch (FileNotFoundException e) { System.out.println("Unable to read file " + fileIn); }
          catch (IOException e) { e.printStackTrace(); }

        finally { if (reader != null) { try { reader.close(); } catch (IOException e) { e.printStackTrace(); } } }

        BufferedWriter writer = null;

        try { writer = new BufferedWriter(new FileWriter(new File(fileOut))); writer.write(builder.toString()); }

            catch (IOException e) { e.printStackTrace(); }

            finally { try { if (writer != null) { writer.close(); } } catch (IOException e) { e.printStackTrace(); }
        }
        return builder.toString();
    }

    class EncryptOffset {

        private int offset;
        private boolean isOffset;

        public EncryptOffset(Character offset) {
            super();
            this.offset = offset;
            isOffset = false;
        }

        public String encryptWithOffset(String text) {
            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < text.length(); i++)
            {
                if (isOffset = false) {
                    sb = (!Character.isLetter(text.charAt(i))) ? sb.append(text.charAt(i)) :
                            (Character.isUpperCase(text.charAt(i))) ?
                                    sb.append((char) (((int) text.charAt(i) + shifter - 65) % 26 + 65)) :
                                    sb.append((char) (((int) text.charAt(i) + shifter - 97) % 26 + 97));
                    isOffset = true;
                } else if (isOffset = true){
                    sb = (!Character.isLetter(text.charAt(i))) ? sb.append(text.charAt(i)) :
                            (Character.isUpperCase(text.charAt(i))) ?
                                    sb.append((char) ((((int) text.charAt(i) + shifter - 65) - offset) % 26 + 65)) :
                                    sb.append((char) ((((int) text.charAt(i) + shifter - 97) - offset) % 26 + 97));
                    isOffset = false;
                }
            }
            return sb.toString();
        }
    }

    public static void main(String[] args)
    {
        ROT13 rot13 = new ROT13();
        File file = new File("/Users/jasong/Labs/ZCW-ORM-SimpleAccount/.idea/EnCryptMe.txt");
       // rot13.encryptFile(file, "/Users/jasong/Labs/ZCW-ORM-SimpleAccount/.idea/IamEncrypted.txt");
        ROT13.EncryptOffset encryptOffset =  rot13.new EncryptOffset('b');
        System.out.println(encryptOffset.encryptWithOffset
                ("This file needs to be read and encrypted. It can also be used for file reading practice!"));
    }


}
