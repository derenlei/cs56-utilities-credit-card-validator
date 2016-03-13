package edu.ucsb.cs56.projects.utilities.credit_card_validator;

import java.util.Scanner;
import javax.swing.*;
import static javax.swing.GroupLayout.Alignment.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;//new
import java.awt.event.KeyEvent;

/** Class displays a GUI that allows users to 
* generate a Visa, Amex, Discover, or MasterCard
* credit card number. Users can also validate a number
* @author Jonathan Easterman Ishi Von Meier
* @version  Winter 2015, CS 56
*/

public class Menu extends JFrame{
	// String attribute that holds card type of current selection
	// in ComboBox
	private String cardType = new String();

	// TextArea where user can enter cardNumberField
	//private JTextField cardNumberField;

        private JTextArea cardNumberField;
        //Global instance variable for user input
        private String TextFieldInput = "";

        //Global instance variable for JTextField input
        private String cardFieldContents = ""; //new
        //Global instance variable for KeyListener
        private int keyType = 0;
       //User checks Box if they would like to see card number 
        private JCheckBox showDigitsCheckBox;
	
	// User presses this button when ready to validate CC number
	private JButton validateButton;

	// User presses this button to generate card numbers
	private JButton generateButton;

	//Simple Label to explain to user what is held in the comboBox
	private JLabel cardTypeLabel;
    private JLabel numGen;
	//Simple JLabel to tell user if card number is valid or not
	private JLabel cardValidLabel;

    //ComboBox to hold all of the different card options
	private JComboBox cardTypeComboBox = new JComboBox();

        private JComboBox generateMultiple = new JComboBox();
    // Constructor for Menu calls the initUI() method
	public Menu() {
		initUI();
	}


	class ValidateListener implements ActionListener {
		// sometimes generates bad cards (when last number is 10)
		// Gotta change this to verify if the vard is validated or not
		public void actionPerformed (ActionEvent ae) {
		    validateHelper();
		}
	}



	// Sets the cardType and generates corresponding card number when
	// generate button is clicked
	class GenerateListener implements ActionListener {
		public void actionPerformed (ActionEvent ae) {
		    TextFieldInput = ""; //Need to empty in case there was some previous input
			Menu.this.cardType = (String)Menu.this.cardTypeComboBox.getSelectedItem();
			cardValidLabel.setText("");
			switch (Menu.this.cardType) {
				case "Visa":
				        showDigitsCheckBox.setSelected(true);
				        TextFieldInput = Visa.generateCard();
					cardNumberField.setText(TextFieldInput);
					break;
				case "Amex":
				        showDigitsCheckBox.setSelected(true);
				        TextFieldInput = AmericanExpress.generateCard();
					cardNumberField.setText(TextFieldInput);
					break;
				case "Discover":
				        showDigitsCheckBox.setSelected(true);
				        TextFieldInput = Discover.generateCard();
					cardNumberField.setText(TextFieldInput);
					break;
				case "MasterCard":
				        showDigitsCheckBox.setSelected(true);
				        TextFieldInput = MasterCard.generateCard();
					cardNumberField.setText(TextFieldInput);
					break;
				default:
					cardNumberField.setText("Please select a card type!");
					cardValidLabel.setText("Please select a card type!");
					break;
			//also print card type to label to keep label current
			}
		}
	}
        class ComboBoxListener implements ActionListener {
	    public void actionPerformed (ActionEvent ae) {
		
		int inputLength = TextFieldInput.length();
		int validLength = getValidLength();
		if(validLength == -1){
		    cardValidLabel.setText("Please select a card type!");
		}
		else if(inputLength > validLength){
		    String overByNum = "Too many digits delete ";
		    overByNum += Integer.toString(inputLength-validLength);

		    cardValidLabel.setText(overByNum);
		}
		else{
		    cardValidLabel.setText("");
		}
	    }
	}
    class NumberFieldMouseListener implements MouseListener{
	public void mouseClicked(MouseEvent e){
	    String text = cardNumberField.getText();
	    if(text.equals("Enter credit card number here") || text.equals("Please select a card type!"))
		    cardNumberField.setText("");
	       }
	    public void mouseEntered(MouseEvent e){}
	    public void mouseExited(MouseEvent e){}
	    public void mousePressed(MouseEvent e){}
	    public void mouseReleased(MouseEvent e){}
	}
	
		
		
		
		
		
		// creates the text field and buttons and adds them to JFrame
		public void initUI() {
		    cardNumberField = new JTextArea("Enter credit card number here",1,20);
		    JScrollPane scrollPane = new JScrollPane(cardNumberField);
		    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		    
		    
		validateButton = new JButton("Validate");
		generateButton = new JButton("Generate");
		showDigitsCheckBox = new JCheckBox("Show Digits");
		cardValidLabel = new JLabel("");
                cardTypeLabel = new JLabel("Card Type:");
	        numGen = new JLabel("Generate Count:");
		cardTypeComboBox = new JComboBox();
		
		
		this.setTitle("Credit Card Validator");
		this.setSize(1000,300);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		

		String[] description = { "Pick a card type","Visa", "MasterCard","Amex","Discover"};
		for (int i = 0; i < 5; i++){cardTypeComboBox.addItem(description[i]);}

		//want to fill generate combo box with amount of potential generate...
		String[] generateNums = {"1","2","3"};
		for (int i = 0; i < 3; i++){generateMultiple.addItem(generateNums[i]);}
 
        //create new grouplayout and set contentpane to this layout
        GroupLayout layout = new GroupLayout(this.getContentPane());
    	this.getContentPane().setLayout(layout);

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
 
        layout.setHorizontalGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(LEADING)
                .addComponent(scrollPane)
            	.addGroup(layout.createParallelGroup()
                    .addComponent(cardValidLabel)
		    .addComponent(cardTypeLabel)
                    .addComponent(cardTypeComboBox)))
            .addGroup(layout.createParallelGroup(LEADING)
		.addComponent(showDigitsCheckBox)
		.addComponent(validateButton)
                .addComponent(generateButton)
		      .addComponent(numGen)
		      .addComponent(generateMultiple))
        );
        
        layout.linkSize(SwingConstants.HORIZONTAL, validateButton, generateButton);
 
        layout.setVerticalGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(BASELINE)
                .addComponent(scrollPane)
		.addComponent(showDigitsCheckBox))
            .addGroup(layout.createParallelGroup(LEADING)
		.addComponent(cardValidLabel)
	      	.addComponent(generateButton))
	    .addGroup(layout.createParallelGroup(LEADING)
		.addComponent(validateButton))
				.addGroup(layout.createParallelGroup(LEADING)
                .addComponent(cardTypeLabel)
					  .addComponent(numGen))
	     .addGroup(layout.createParallelGroup(LEADING)
	        .addComponent(cardTypeComboBox)
		       .addComponent(generateMultiple)));

        pack();
     	this.setVisible(true);
	
		validateButton.addActionListener(new ValidateListener());
		generateButton.addActionListener(new GenerateListener());
		//adding listener for keyboard input from user
	       
		cardNumberField.addKeyListener(new KeyListener(){
			public void keyPressed(KeyEvent keyEvent){
			    keyType = keyEvent.getKeyCode();
			    //Backspace is value 8, check for if backspace was pressed
			    if(keyType == 8){
				if(!(TextFieldInput.isEmpty())){
				    //if there was one asterisk, now should be cleared.
				    if(TextFieldInput.length() == 1){
					TextFieldInput = "";
				    }
				    else{
					//otherwise if the length is greater than 1, subtract one digit from input
					TextFieldInput = TextFieldInput.substring(0,TextFieldInput.length()-1);
				    }
				}
				int inputLength = TextFieldInput.length();
                                int validLength = getValidLength();
                                if(validLength == -1){
                                    cardValidLabel.setText("Please select a card type!");
                                }
                                else if(inputLength > validLength){
                                    String overByNum = "Too many digits delete ";
                                    overByNum += Integer.toString(inputLength-validLength);

                                    cardValidLabel.setText(overByNum);
                                }
                                else{
                                    cardValidLabel.setText("");
                                }
				//used to check card number System.out.println(TextFieldInput);
				return;
			    }
			
			    //checks if the key typed is enter
			    if(keyType == KeyEvent.VK_ENTER){
				validateHelper();
				return;
			    }
			}
			public void keyTyped(KeyEvent keyEvent) {
			    keyEvent.consume(); //http://stackoverflow.com/questions/7525154/jtextfields-settext-method-doesnt-work-from-a-keylistener
			    if(!((keyType == 8) || (keyType == KeyEvent.VK_ENTER))){
				TextFieldInput += keyEvent.getKeyChar();
				int inputLength = TextFieldInput.length();
				int validLength = getValidLength();
				if(validLength == -1){
				    cardValidLabel.setText("Please select a card type!");
				}
				else if(inputLength > validLength){
				    String overByNum = "Too many digits delete ";
				    overByNum += Integer.toString(inputLength-validLength);

				    cardValidLabel.setText(overByNum);
				}
				else{
				    cardValidLabel.setText("");
				}
				//System.out.println(TextFieldInput); used to see if TextFieldInput updates correctly
			    }
			    
			    String asteriskString = createAsteriskString(TextFieldInput);
			    if(showDigitsCheckBox.isSelected())
				cardNumberField.setText(TextFieldInput);
			    else
			    cardNumberField.setText(asteriskString);
			    //used to check output System.out.println(TextFieldInput);
			    return;
			}
		  public void keyReleased(KeyEvent keyEvent){}
		});

		showDigitsCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
			    if(showDigitsCheckBox.isSelected())
				cardNumberField.setText(TextFieldInput);
			    else{
				String asteriskString = createAsteriskString(TextFieldInput);
				cardNumberField.setText(asteriskString);
			    }
			    return;
			}
		    });

		cardTypeComboBox.addActionListener(new ComboBoxListener());
		cardNumberField.addMouseListener(new NumberFieldMouseListener());
	}

    private String createAsteriskString(String digits){
	String asteriskString = "";
	for(int i=0; i < TextFieldInput.length(); ++i){
	    asteriskString += "*";
	}
	return asteriskString;
    }

    private int getValidLength(){
	Menu.this.cardType = (String)Menu.this.cardTypeComboBox.getSelectedItem();
	switch (Menu.this.cardType) {
	case "Visa":
	    return Visa.VALIDLENGTH;
	case "Amex":
	    return AmericanExpress.VALIDLENGTH;
	case "Discover":
	    return Discover.VALIDLENGTH;
	case "MasterCard":
	    return MasterCard.VALIDLENGTH;
	default:
	    return -1;
	}
    }

    private void validateHelper(){
	int checkLength = TextFieldInput.length();
	int validLength = getValidLength();
	try{
	    if (CCValidator.isValid(TextFieldInput)){
		//set card type variable, print to label
		cardTypeLabel.setText("Card Type: " + CCValidator.getCardType(TextFieldInput));
		cardValidLabel.setText("Valid " + CCValidator.getCardType(TextFieldInput)+ " card number!");
	    }
	    else if(validLength == -1){
                cardValidLabel.setText("Please select a card type!");
                return;
            }
	    else if(checkLength > validLength){
		String overByNum = "Too many digits delete ";
		overByNum += Integer.toString(checkLength-validLength);

		cardValidLabel.setText(overByNum);
		return;
	    }
	    else if(checkLength < validLength){
		cardValidLabel.setText("Not enough digits!");
		return;
	    }
	    else if(isInputInvalid()){
		cardValidLabel.setText("Invalid character input!");
		return;
		}
	    else{
		cardValidLabel.setText("This is an invalid card number!");
	    }

	}catch(ArrayIndexOutOfBoundsException ex){
	    if(checkLength < validLength){
		cardValidLabel.setText("Not enough digits");
		return;
	    }
	}
	catch(NumberFormatException ex){
                cardValidLabel.setText("Invalid character input!");
                return;
	}


	TextFieldInput = "";
	cardNumberField.setText("");
       
    }
    private boolean isInputInvalid(){
	for(int i=0; i<TextFieldInput.length(); ++i){
	    if(!(Character.isDigit(TextFieldInput.charAt(i)))){
		return true;
	    }
	}
	return false;
    }


	// Main function calls constructor for a Menu instance
	// Program logic is handled in initUI() method, which is
	// called by Menu constructor
    public static void main(String args[]) {
        Menu gui = new Menu();
	}
}
