import java.awt.*;        		  	// Using AWT layouts
import java.awt.event.*;  		 	// Using AWT event classes and listener interfaces
import javax.swing.*;     		 	// Using Swing components and containers
import java.text.*; 				//Setting format of date
import java.io.*;   			  	//FileIO and exception
import javax.swing.BorderFactory; 	//Give solid line to border 
import javax.swing.border.Border; 	//Give solid line to border 
import java.nio.file.*; 			//For writing to file

public class Subscription extends JFrame implements ActionListener {	//Class Subscription extends JFrame implements ActionListener
	//For constructor
	private JFrame f = new JFrame("Subscription Form");		//Create JFrame named Subscription Form
	private JPanel pann;									//Declare JPanel
	private JLabel title,lab;								//Declare JLabel
	private JTextArea area,response;						//Declare JTextArea
	private Border border;									//Declare Border
	private JTextField textf;								//Declare JTextField
	private ImageIcon icon;									//Declare ImageIcon
	private JCheckBoxMenuItem term;							//Declare JCheckBoxMenuItem
	private JButton but;									//Declare JButton
	
	//For ActionListener method
	private String email = "";								//Initialize String email to empty string
	private FileWriter subrp;								//Declare FileWriter for writing file later
	private boolean flag = false;							//Initialize flag to false
	
	//Constructor
	public Subscription(){				
		f.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);		//Close button will have no response
		f.setSize(350,350);											//Set frame size to 350x350
		f.setBounds(300, 90, 350, 350); 							//Set position of frame
		f.setVisible(true);											//Set visible
		f.setResizable(false);										//Resize is not allowed
		
		pann = new JPanel();										//Construct JPanel
		setLayout(new BoxLayout(pann,BoxLayout.Y_AXIS));			//Set BoxLayout for JPanel and arrange component with Y axis
		title = new JLabel("Subscription Form"); 					//Construct JLabel with text Subscription Form 
		title.setFont(new Font("Arial", Font.ITALIC, 30)); 			//Set font to Arial, Italic type, size 30 for JLabel
		title.setSize(300, 30); 									//Set size for JLabel title 300x30
		pann.add(title);  											//Add JLabel title to JPanel pann
		
		area = new JTextArea(6,22);									//Construct JTextArea to size 6 rows 22 columns
		area.setLineWrap(true);										//Move to new line if the string is too long
		area.setWrapStyleWord(true);								//Move the entire word to next line instead of breking it
		area.setEditable(false);									//JTextArea not editable so read only
		area.setText("\nSubscribe us to get the latest information about Japan for free! Your email address " +  //Set text to JTextarea area
					"will be kept private and will not be shared to third party.");
		area.setFont(new Font("Arial",Font.ITALIC,15));				//Set font to Arial, Italic type and size 15 for JTextArea
		border = BorderFactory.createLineBorder(Color.GRAY);		//Set border for JTextArea, line border is set to gray color
		area.setBorder(BorderFactory.createCompoundBorder(border,	//Apply the solid border for JTextArea
            BorderFactory.createEmptyBorder(10, 10, 10, 10)));		//Specify the width of top, left, bottom, right
		pann.add(area);												//Add JTextArea area to JPanel pann
		
		lab = new JLabel("Enter email address:");			//Construct JLabel with text Enter email address
		pann.add(lab);										//Add JLabel lab to JPane pann
		textf = new JTextField(15);							//Construct JTextField textf to size 15
		pann.add(textf);									//Add JTextField textf to JPanel pann
		
		icon = new ImageIcon(this.getClass().getResource("Icons/please.png"));	//Construct ImageICon, retrieve the png image based on the path directory given
		term = new JCheckBoxMenuItem("T&C",icon); 								//Create JCheckBoxMenuItem term with text T&C and icon
		term.setHorizontalTextPosition(SwingConstants.LEFT);					//Set the text position to left 
		term.setFont(new Font("Arial", Font.PLAIN, 15));						//Set the font to Arial, Plain type, size 15 of JCheckBoxMenuItem
		pann.add(term);															//Add JCheckBoxMenuItem term to JPanel pann
		
		response = new JTextArea(2,25);					//Create JTextArea response with fixed size 2 rows 25 columns
		response.setLineWrap(true);						//Move to new line if the string is too long
		response.setWrapStyleWord(true);				//Move the entire word to next line instead of breking it
		response.setEditable(false);					//JTextArea not editable so read only
		pann.add(response);								//Add JTextArea response to JPanel pann
		textf.addActionListener(this);					//Register JTextArea to ActionListener
		//textf is the source object that fires the ActionEvent
		//The source add "this" instance as an ActionEvent listener
		//Which provides an ActionEvent handler called actionPerformed()
		
		but = new JButton("Back");		//Create JButton with text Back
		pann.add(but);					//Add JButton but to JPanel pann
		but.addActionListener(this);	//Register JButton to ActionListener
		//but is the source object that fires the ActionEvent
		//The source add "this" instance as an ActionEvent listener
		//Which provides an ActionEvent handler called actionPerformed()
		
		f.add(pann);					//Add JPanel pann to JFrame f
		
	}//end of constructor
	
	//Override abstract method of ActionListener
	@Override
	public void actionPerformed(ActionEvent evt){
		if(evt.getSource()==textf){								//Check which registered component trigger this, if this is from JTextField textf
			try{
				email = textf.getText();						//Get the String entered into the JTextField textf
			}catch(NumberFormatException e){					//Exception handling of type NumberFormatException
				System.out.println("Invalid input.");			//Print meaningful message on the command prompt
			}
			
			if(term.isSelected() && (email!=null && !email.isEmpty())){	//If CheckBox is selected and email is not empty
				try {
					subrp = new FileWriter("Files/Subscription.txt",true);//With second argument, make it append instead of overwrite
					  subrp.write(email);								//Write string email with FileWriter
					  subrp.write("\r\n");								//Next line
					  flag = true;										//Set flag to true
					  //subrp.close();									//Close the FileWriter
				} catch (IOException e) {								//Catch exception
					  System.out.println("An error occurred.");			//Print error message on command prompt
					  e.printStackTrace();								//Print stack trace
				}finally{																	//Always execute, used for cleaning up
					try{																	//Close should be inside finally else it won't get closed after the code terminates normally
						if(subrp != null) subrp.close();									//Close reader
					}catch(IOException io){													//Exception handling of type IOException
						System.out.println("File is not closed");							//Print message on the cmd
					}
				}
				
				if(flag){															//If flag is true
					textf.setText("");												//Set JTextField textf to empty
					term.setSelected(false);										//Set the state of JCheckbox term to false, which means unchecked
					response.setText("Successful.Thank you for your subscription.");//Set text to JTextField response
				}else{																//If flag is not true
					textf.setText("");												//Set JTextField textf to empty
					response.setText("Please try again");							//Set text to JTextField response, ask user to try again
				}
			}else{																		//If CheckBox is not selected						
				response.setText("Please enter your email address and accept the T&C"); //Set text to JTextField response, make sure user enter email and accept the T&C
			}
			
		}else{				//If it is JButton "Back" that fired the event
			f.dispose();	//Dispose current frame
			new Travel();	//Back to main page
		}
	}//end of listener
	
}//end of class