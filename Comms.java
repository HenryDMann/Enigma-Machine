import java.util.Scanner;

/*-----------------------------------------------------------------------------
Comms
author: Henry Mann

Emulates an enigma machine based on inputs (from command line or other classes)
and uses the Enigma class to encrypt or decrypt.
-----------------------------------------------------------------------------*/

public class Comms {
  /// Takes a number of initialization variables, an encrypt or decrypt
  /// argument and some text (assumed to be plain text with the encrypt or
  /// cypher text with the decrypt argument) and encrypts or decrypts the
  /// message
  /// @param args[0], an int selecting which inner rotor to use
  /// @param args[1], an int selecting which middle rotor to use
  /// @param args[2], an int selecting which outer rotor to use
  /// @param args[3], a string of 3 letters selecting the initial position for each 
  /// rotor
  /// @param args[4], a string saying either encrypt or decrypt depending on what the
  /// user wants to do
  /// @param args[5], a string containing the text to be encrypted or decrypted
  /// @return a string containing the encrypted or decrypted text
  public static String run(String[] args) {
    
    // Check if the user wants to encrypt or decrypt the text
    boolean encrypt = true;
    if( args[4].equals("decrypt") )
      encrypt = false;

    // Retrieve the rotor ids
    int id1 = Integer.parseInt(args[0]);
    int id2 = Integer.parseInt(args[1]);
    int id3 = Integer.parseInt(args[2]);

    // Retrieve the message from the input
    String message = args[5];

    // Call the Enigma's constructor to build the machine
    Enigma enigma = new Enigma(id1, id2, id3, args[3]);

    // Encrypt or Decrypt
    if(encrypt)
      return enigma.encrypt(message);
    else
      return enigma.decrypt(message);
  }
}