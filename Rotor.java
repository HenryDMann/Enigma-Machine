/*-----------------------------------------------------------------------------
Rotor
author: Henry Mann

This class encapsulates logic for the rotors used in encryption and decryption
-----------------------------------------------------------------------------*/
public class Rotor {

    // Each rotor has a string rotorValues which is the list of characters in
    // order as they are displayed along the rotor
    private String rotorValues;
    // Each rotor also has a startChar which is the character it starts on,
    // allowing the program to know when it has completed a revolution
    private char startChar;
        
    /// This constructor method constructs and initializes the rotor, setting
    /// the letters along the rotor and rotating it to the starting position.
    /// @param v, a string that contains the characters in the order that they
    /// appear along the rotor
    /// @param c, a char marking the character the rotor started on, allowing
    /// the rotor to know when it has completed a revolution
    public Rotor(String v, char c){
        this.rotorValues = new String(v);
        this.startChar = c;
        
        // Rotate the rotor until it is in the starting position
        while(!this.rotate());
            
    }
    
    /// This function rotates the rotor, it returns true only if the rotor
    /// has completed a full revolution
    /// @return a boolean based on weather or not a full revolution has been
    /// completed
    public boolean rotate(){
        // Take all but the last letter of the string
        String firstPart = rotorValues.substring(0, (rotorValues.length() - 1));
        // Take the last letter of the string
        String lastLetter = rotorValues.substring(rotorValues.length() - 1);
        // Add the last letter on to the front of the string
        rotorValues = lastLetter + firstPart;
        // Convert the startChar char into a string
        String firstLetter = "" + startChar;
        // If the rotation has finished a full loop, return true, otherwise, false
        if (lastLetter.equals(firstLetter)) {
            return true;
        } else {
            return false;
        }
    }
    
    /// This function find the index of a character on a rotor
    /// @param c, the character's index to be found
    /// @return an int of the index where the character is
    public int indexOf(char c){
        // Check each letter, if they are the same as c, return 
        // that letter's index
        for (int i = 0; i < rotorValues.length(); i++) {
            if (rotorValues.charAt(i) == c) {
                return i;
            }
        }
        // If such a letter is not found, return -1
        return -1;
    }

    /// This function find char at an index
    /// @param idx, the index of the char to be found
    /// @return the char found at the input index
    public char charAt(int idx){
        // Convert the string to a char array
        char[] rotorValueArray = rotorValues.toCharArray();
        // Find the char at the given index
        return rotorValueArray[idx];
    }
}
    