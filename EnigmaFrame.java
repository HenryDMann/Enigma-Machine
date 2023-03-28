import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/*-----------------------------------------------------------------------------
Enigma Frame
author: Henry Mann

This class encapsulates the methods to interface between a GUI and the
encryption and decryption methods.
-----------------------------------------------------------------------------*/

public class EnigmaFrame extends JFrame{
    public EnigmaFrame() {
        setTitle("Enigma");
        setSize(1000, 500);
        setLocation(100, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // The choices of different rotors
        Integer[] Settings = {1, 2, 3, 4, 5};

        // The innter rotor label and combobox
        JLabel lblInner = new JLabel();
        lblInner.setText("Inner: ");
        JComboBox cbxInner = new JComboBox<Integer>(Settings);

        // The middle rotor label and combobox
        JLabel lblMiddle = new JLabel();
        lblMiddle.setText("Middle: ");
        JComboBox cbxMiddle = new JComboBox<Integer>(Settings);

        // The outer rotor label and combobox
        JLabel lblOuter = new JLabel();
        lblOuter.setText("Outer: ");
        JComboBox cbxOuter = new JComboBox<Integer>(Settings);

        // The initial position label and text field
        JLabel lblInitPos = new JLabel();
        lblInitPos.setText("Initial Positions: ");
        JTextField txfInitPos = new JTextField(3);

        // The input and read-only output text areas
        JTextArea txaInput = new JTextArea(3, 50);
        JTextArea txaOutput = new JTextArea(3, 50);
        txaOutput.setEditable(false);

        // The encrypt button
        JButton btnEnc = new JButton();
        btnEnc.setText("Encrypt");
        btnEnc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Check to ensure the initial position box is 3 letters
                if (!txfInitPos.getText().matches("[a-zA-Z][a-zA-Z][a-zA-Z]")) {
                    // If it is not, display the error message in the output text area
                    txaOutput.setText("Error: Initial Positions " + txfInitPos.getText().toUpperCase() + " is invalid, please enter three letters.");
                // Check to ensure the input box is only letters and spaces
                } else if (!txaInput.getText().matches("[a-zA-Z ]*")){
                    // If it is not, display the error message in the output text area
                    txaOutput.setText("Error: invalid input, please enter only letters and spaces.");
                } else {
                    // Otherwise, set the input to be all the arguments in the run command in comms for encrypt, replaces spaces with hashes
                    String[] input = {cbxInner.getSelectedItem().toString(), cbxMiddle.getSelectedItem().toString(), cbxOuter.getSelectedItem().toString(), txfInitPos.getText().toUpperCase(), "encrypt", txaInput.getText().toUpperCase().replace(" ", "#")};
                    // Set the output to be the result of the encryption, replaces hashes back to spaces
                    txaOutput.setText(Comms.run(input).replace("#", " "));
                }
            }
        });

        // The decrypt button
        JButton btnDec = new JButton();
        btnDec.setText("Decrypt");
        btnDec.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Check to ensure the initial position box is 3 letters
                if (!txfInitPos.getText().matches("[a-zA-Z][a-zA-Z][a-zA-Z]")) {
                    // If it is not, display the error message in the output text area
                    txaOutput.setText("Error: Initial Positions " + txfInitPos.getText().toUpperCase() + " is invalid, please enter three letters.");
                // Check to ensure the input box is only letters and spaces
                } else if (!txaInput.getText().matches("[a-zA-Z ]*")){
                    // If it is not, display the error message in the output text area
                    txaOutput.setText("Error: invalid input, please enter only letters and spaces.");
                } else {
                    // Otherwise, set the input to be all the arguments in the run command in comms for decrypt, replaces spaces with hashes
                    String[] input = {cbxInner.getSelectedItem().toString(), cbxMiddle.getSelectedItem().toString(), cbxOuter.getSelectedItem().toString(), txfInitPos.getText(), "decrypt", txaInput.getText().toUpperCase().replace(" ", "#")};
                    // Set the output to be the result of the encryption, replaces hashes back to spaces
                    txaOutput.setText(Comms.run(input).replace("#", " "));
                }
            }
        });

        // Create the top panel and place all the elements that belong there
        JPanel PTop = new JPanel();
        PTop.add(lblInner);
        PTop.add(cbxInner);
        PTop.add(lblMiddle);
        PTop.add(cbxMiddle);
        PTop.add(lblOuter);
        PTop.add(cbxOuter);
        PTop.add(lblInitPos);
        PTop.add(txfInitPos);
        PTop.add(btnEnc);
        PTop.add(btnDec);
        add(PTop, BorderLayout.NORTH);

        // Prepare and place the remaining elements
        // Input label
        JLabel lblInput = new JLabel();
        lblInput.setText("Input: ");
        
        // Middle panel
        JPanel PMid = new JPanel();
        PMid.add(lblInput);
        PMid.add(txaInput);
        add(PMid, BorderLayout.CENTER);

        // Output label
        JLabel lblOutput = new JLabel();
        lblOutput.setText("Output: ");
        
        // Bottom panel
        JPanel PBot = new JPanel();
        PBot.add(lblOutput);
        PBot.add(txaOutput);
        add(PBot, BorderLayout.SOUTH);

        // Pack
        pack();
    }
}
