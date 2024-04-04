import java.io.File; 
import java.io.FileNotFoundException;  
import java.util.Scanner;

class Main{
  
  public static void main(String[] args) {
    VigenereCipher Cipher = new VigenereCipher();
    
    String encrypted_message = Cipher.encrypt("encrypt_check.txt","key_check.txt");
    System.out.println(encrypted_message);

    String decrypted_message = Cipher.decrypt("decrypt_check.txt","key_check.txt");
    System.out.println(decrypted_message);
  }
}
interface Cipher{
  public String encrypt(String message_filename, String key_filename);
  public String decrypt(String message_filename, String key_filename);
}
class VigenereCipher implements Cipher {

  public String encrypt(String message_filename, String key_filename){
    String encrypted_message = "";
    String message = "";
    String Key = GetKey(key_filename);
    int key_length = Key.length();

    try {
      File text_File = new File(message_filename);
      Scanner myReader = new Scanner(text_File);
      while (myReader.hasNextLine()) {
        message = message + myReader.nextLine();
        message = message + "\n";
      }
      myReader.close();
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
    
    Key = Key.toUpperCase();
    message = message.toUpperCase();

    for (int i = 0; i < message.length(); i ++){
      int key_char_index = i % key_length;

      char encrypted_char = Vigenere_encrypt_char(Key.charAt(key_char_index), message.charAt(i));

      encrypted_message = encrypted_message + encrypted_char;
    }

    return encrypted_message;
  }

  public String decrypt(String message_filename, String key_filename){
    String decrypted_message = "";
    String message = "";
    String Key = GetKey(key_filename);
    int key_length = Key.length();

    try {
      File text_File = new File(message_filename);
      Scanner myReader = new Scanner(text_File);
      while (myReader.hasNextLine()) {
        message = message + myReader.nextLine();
        message = message + "\n";
      }
      myReader.close();
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
    
    Key = Key.toUpperCase();
    message = message.toUpperCase();

    for (int i = 0; i < message.length(); i ++){
      int key_char_index = i % key_length;

      char decrypted_char = Vigenere_decrypt_char(Key.charAt(key_char_index), message.charAt(i));

      decrypted_message = decrypted_message + decrypted_char;
    }

    return decrypted_message;
  }
  
  public char Vigenere_decrypt_char(char key , char plain_char)
  {
    char encrypt_char = ' ';
    
    if ((key >= 65 )&&(key <= 90)&&(plain_char >= 65)&&( plain_char <= 90))
    {
      
    
      int ascii_key = key;
      int ascii_plain_char = plain_char;
      
      ascii_key = ascii_key - 65;
      ascii_plain_char = ascii_plain_char - 65;
      
      int ascii_encrypted_char = ascii_plain_char - ascii_key;
      if (ascii_encrypted_char < 0){
        ascii_encrypted_char = ascii_encrypted_char + 26;
      }

      ascii_encrypted_char = ascii_encrypted_char + 65;

      encrypt_char = (char)ascii_encrypted_char;
    }

    else
    {
      encrypt_char = plain_char;
    }
  
    return encrypt_char;
  }
  public char Vigenere_encrypt_char(char key , char plain_char)
  {
    char encrypt_char = ' ';
    
    if ((key >= 65 )&&(key <= 90)&&(plain_char >= 65)&&( plain_char <= 90))
    {
      
    
      int ascii_key = key;
      int ascii_plain_char = plain_char;
      ascii_key = ascii_key - 65;
      ascii_plain_char = ascii_plain_char - 65;
      int ascii_encrypted_char = ascii_plain_char + ascii_key;
      if (ascii_encrypted_char > 25){
        ascii_encrypted_char = ascii_encrypted_char - 26;
      }

      ascii_encrypted_char = ascii_encrypted_char + 65;

      encrypt_char = (char)ascii_encrypted_char;
    }

    else
    {
      encrypt_char = plain_char;
    }
  
    return encrypt_char;
  }

  public String GetKey(String File_Name){
    String Key = "";

    try{

      File Key_File = new File(File_Name);
      if (Key_File.exists()) {
        Scanner myReader = new Scanner(Key_File);
        Key = myReader.nextLine(); 
        myReader.close();
      }
    }

    catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }


                      
    return Key;
  }
}
