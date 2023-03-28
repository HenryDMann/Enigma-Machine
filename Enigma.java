/*-----------------------------------------------------------------------------
Enigma
author: Henry Mann

This class encapsulates logic to encrypt and decrypt messages using the rotor
class.
-----------------------------------------------------------------------------*/
public class Enigma{

    // Create the list of rotors to choose from
    private String rotorInit[] = {"#GNUAHOVBIPWCJQXDKRYELSZFMT",
        "#EJOTYCHMRWAFKPUZDINSXBGLQV",
        "#BDFHJLNPRTVXZACEGIKMOQSUWY",
        "#NWDKHGXZVRIFJBLMAOPSCYUTQE",
        "#TGOWHLIFMCSZYRVXQABUPEJKND"};

    // Create the list to be populated with the three chosen rotors
    private Rotor rotors[];
        
    /// This constructor method constructs and initializes the enigma machine
    /// @param id1, the int of the inner rotor to be chosen
    /// @param id2, the int of the middle rotor to be chosen
    /// @param id3, the int of the outer rotor to be chosen
    /// @param start, a string of the initial position of each rotor
    public Enigma(int id1, int id2, int id3, String start){
        //Create the rotors array and populate it with the three chosen rotors
        rotors = new Rotor[3];
        rotors[0] = new Rotor(rotorInit[id1-1], start.charAt(0));
        rotors[1] = new Rotor(rotorInit[id2-1], start.charAt(1));
        rotors[2] = new Rotor(rotorInit[id3-1], start.charAt(2));
    }

    /// This function converts ciphertext to plaintext
    /// @param message, a string with the ciphertext
    /// @return the plaintext message
    public String decrypt(String message){   
        // Convert the message to an array of chars     
        char[] mArray = message.toCharArray();

        // Iterate through each char
        for (int i = 0; i < mArray.length; i++) {
            // Set the current letter aside
            char currentLetter = mArray[i];
            // Apply the first step of decryption
            currentLetter = rotors[1].charAt(rotors[2].indexOf(currentLetter));
            // Apply the second step of decryption
            currentLetter = rotors[0].charAt(rotors[2].indexOf(currentLetter));
            // Set the char in the char array to the decrypted char
            mArray[i] = currentLetter;
            // Rotate the rotors
            rotate();
        }
        // Create an empty output string to populate
        String output = "";

        // Populate the output string with the chars from the char array
        for (int i = 0; i < mArray.length; i++) {
            output += mArray[i];
        }

        //Return the output string
        return output;
    }

    /// This function converts plaintext to ciphertext
    /// @param message, a string with the ciphertext
    /// @return the plaintext message
    public String encrypt(String message){
        // Convert the message to an array of chars
        char[] mArray = message.toCharArray();

        // Iterate through each char
        for (int i = 0; i < mArray.length; i++) {
            // Set aside the current char
            char currentLetter = mArray[i];
            // Apply the first step of encryption
            currentLetter = rotors[2].charAt(rotors[0].indexOf(currentLetter));
            // Apply the second step of encryption
            currentLetter = rotors[2].charAt(rotors[1].indexOf(currentLetter));
            // Set the char in the char array to the encrypted char
            mArray[i] = currentLetter;
            //Rotate the rotors
            rotate();
        }
        // Create an empty output string
        String output = "";

        // Populate the output string with the chars from the char array
        for (int i = 0; i < mArray.length; i++) {
            output += mArray[i];
        }

        // Return the output string
        return output;
    }

    /// This function rotates the rotors, it rotates the inner rotor, if the
    /// inner rotor has finished a loop, it rotates the middle rotor by 1, if
    /// the middle rotor has finished a loop, it rotates the outer rotor by 1.
    private void rotate(){
        if(rotors[0].rotate()){
            if(rotors[1].rotate()){
                rotors[2].rotate();
            }
        }
    }
    
}
