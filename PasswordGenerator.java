import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Clipboard;

/*
@author Avery Swinimer
@name Password Generator 
@date June 16th, 2023
*/

public class PasswordGenerator extends JFrame {

  // Declares private instance variables for GUI components
  private JButton generateButton;
  private JButton copyButton;
  private JTextArea outputTextArea;
  private JSlider lengthSlider;
  private JLabel lengthLabel;
  private JCheckBox symbolCheckbox;
  private JCheckBox numberCheckbox;
  private JCheckBox lowercaseCheckbox;
  private JCheckBox uppercaseCheckbox;

  // Character sets for password generation
  private static final String uppercase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
  private static final String lowercase = "abcdefghijklmnopqrstuvwxyz";
  private static final String symbol = "!@#$%^&*";
  private static final String number = "1234567890";

  // Constructor to initialize the MyFrame class
  PasswordGenerator() {
    initializeComponents();
    configFrameProperties();
    addComponentstoFrame();
  }

  // Initialize all of the GUI components
  private void initializeComponents() {
    generateButton = new JButton("Generate");
    generateButton.setBounds(10, 25, 100, 25);
    generateButton.setFocusable(false);
    generateButton.setHorizontalTextPosition(JButton.CENTER);
    generateButton.setVerticalTextPosition(JButton.BOTTOM);
    generateButton.setBackground(Color.lightGray);
    generateButton.setBorder(BorderFactory.createEtchedBorder());

    // Adds an action listener to the generate password button. Lambda expression
    // calls the passwordGeneration() method on button press.
    generateButton.addActionListener(e -> generatePassword());

    copyButton = new JButton("Copy");
    copyButton.setBounds(440, 25, 50, 25);
    copyButton.setMargin(new Insets(1, 1, 1, 1));
    copyButton.setFocusable(false);
    copyButton.setBackground(Color.lightGray);
    copyButton.setBorder(BorderFactory.createEtchedBorder());

    // Adds an action listener to the copy button. Lambda expression calls the
    // copyToClipboard() method on button press.
    copyButton.addActionListener(e -> copyToClipboard());

    outputTextArea = new JTextArea("password...");
    outputTextArea.setBounds(125, 25, 300, 25);
    outputTextArea.setVisible(true);

    lengthSlider = new JSlider(JSlider.HORIZONTAL, 8, 32, 8);
    lengthSlider.setBounds(75, 70, 150, 50);
    lengthSlider.setMajorTickSpacing(8);
    lengthSlider.setMinorTickSpacing(2);
    lengthSlider.setPaintTicks(true);
    lengthSlider.setPaintLabels(true);
    lengthSlider.setSnapToTicks(true);

    lengthLabel = new JLabel();
    lengthLabel.setBounds(10, 70, 100, 25);
    lengthLabel.setText("Length");

    symbolCheckbox = new JCheckBox("Symbol");
    symbolCheckbox.setBounds(10, 130, 125, 30);

    numberCheckbox = new JCheckBox("Number");
    numberCheckbox.setBounds(10, 160, 125, 30);

    lowercaseCheckbox = new JCheckBox("Lowercase");
    lowercaseCheckbox.setBounds(135, 130, 125, 30);

    uppercaseCheckbox = new JCheckBox("Uppercase");
    uppercaseCheckbox.setBounds(135, 160, 125, 30);
  }

  // Configure the properties of the JFrame
  private void configFrameProperties() {
    this.setTitle("Password Generator v1.0");
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLayout(null);
    this.setSize(515, 300);
    this.setVisible(true);
    this.setResizable(false);
  }

  // Add all of the components to the JFrame
  private void addComponentstoFrame() {
    this.add(generateButton);
    this.add(copyButton);
    this.add(outputTextArea);
    this.add(lengthSlider);
    this.add(symbolCheckbox);
    this.add(numberCheckbox);
    this.add(lowercaseCheckbox);
    this.add(uppercaseCheckbox);
    this.add(lengthLabel);
  }

  // Generate a random password based on user preferences
  private void generatePassword() {

    // Gets length value from JSlider
    int length = lengthSlider.getValue();

    // Checks to see if the JCheckboxes are ticked
    boolean includeSymbol = symbolCheckbox.isSelected();
    boolean includeNumber = numberCheckbox.isSelected();
    boolean includeUppercase = uppercaseCheckbox.isSelected();
    boolean includeLowercase = lowercaseCheckbox.isSelected();

    Random ran = new Random();
    // String to store the combinedChars based on user preferances
    String combinedChars = "";

    // If ticked, add symbol character set to combinedChars
    if (includeSymbol) {
      combinedChars += symbol;
    }
    // If ticked, add number character set to combinedChars
    if (includeNumber) {
      combinedChars += number;
    }

    // If ticked, add uppercase character set to combinedChars
    if (includeUppercase) {
      combinedChars += uppercase;
    }

    // If ticked, add lowercase character set to combinedChars
    if (includeLowercase) {
      combinedChars += lowercase;
    }

    // If no checkboxes are ticked, set combinedChars to the lowercase character set
    if (combinedChars.isEmpty()) {
      combinedChars = lowercase;
    }

    // Creates a character array to store the generated password
    char[] password = new char[length];
    // Generates each character of the password randomly from combinedChars
    for (int i = 0; i < length; i++) {
      password[i] = combinedChars.charAt(ran.nextInt(combinedChars.length()));
    }

    // Sets the generated as the text in the outputTextArea
    outputTextArea.setText(new String(password));
  }

  // Copy the generated password to the users clipboard
  private void copyToClipboard() {
    // Get the text from the outputTextArea
    String text = outputTextArea.getText();
    // Creates a StringSelection object within the text
    StringSelection selection = new StringSelection(text);
    // Get the system clipboard using the defaulkt toolkit
    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
    // Set the contents of the clipboard to the StringSelection object
    clipboard.setContents(selection, null);
  }

  // Main method to start the application
  public static void main(String[] args) {
    // Create and show the GUI frame
    SwingUtilities.invokeLater(() -> new PasswordGenerator());
    new PasswordGenerator();
  }
}