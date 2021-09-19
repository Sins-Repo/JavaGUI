import java.awt.*;        		  // Using AWT layouts
import java.awt.event.*;  		 // Using AWT event classes and listener interfaces
import javax.swing.*;     		 // Using Swing components and containers
import java.util.*; 	 		 // Display time zone
import java.text.*; 			//Setting format of date
import java.net.URL; 			//For audio clip
import javax.sound.sampled.*;   //For audio clip
import java.io.*;   			  //FileIO and exception
import javax.swing.BorderFactory; //Give solid line to border 
import javax.swing.border.Border; //Give solid line to border 
import java.nio.file.*; //For writing to file


// A Swing GUI application inherits from top-level container javax.swing.JFrame
public class Travel extends JFrame {
 
   // Private instance variables
		// For Layout				
		private JButton[] name;  						// Array of JButtons
		private JButton[] food;							// Array of JButtons
		private JButton sub;							// Subscription JButton declaration
		private JLabel headtit;							// JLabels declaration
		private JPanel top,left,right,center,bottom;	// JPanels declaration
		
		// For currency conversion
		private JLabel lblInput,lblOutput;     			// Declare input Label
		private TextField tfInput,tfOutput;   			// Declare input TextField
		private double sum = 0;   			  			// Initialize sum to 0
		private double numberIn = 0;		  			// Initialize input number to 0
		
		// For Time Zone Display
		private Date today;									//Declare Date	
		private DateFormat df,df2;							//Declare DateFormat
		private String MYT,JST;								//Declare String
		private JLabel local, local_time, dest, dest_time;  //Declare JLabel
		
		//Border layout
		private JPanel nested,nested2,nested3,nested4;	//Declare JLabels for CENTER
		private Border borderline,borderline2;			//Declare Border
		
		// For SOUTH border
		private JLabel footer;				//Declare JLabel for SOUTH
		
		// For Audio
		private URL url;					//Declare URL
		private AudioInputStream audioIn;	//Declare AudioInputStream
		private Clip clip;					//Declare Clip
		
		// For Listeners
		private JPanel textcol,pane,pane2;		//Declare JPanel 
		private JLabel image, strg;				//Declare JLabel
		private ImageIcon pic;					//Declare ImageIcon
		private String line;					//Declare String
		
		//For File I/O
		BufferedReader reader;				// Declare BufferedReader 
 
 
	public Travel() {						// Constructor to setup the GUI components and event handlers
		Container cp = getContentPane();	// Retrieve the top-level content-pane from JFrame
		// Allocate the GUI components
		//NORTH border: Headtitle
		top = new JPanel();												 // JPanel named top is created
		headtit = new JLabel("Fly To Japan",SwingConstants.CENTER);		 // Align the label to the center
		Font font = new Font("Courier",Font.BOLD,30);					 // Use Font class to set desired font type
		headtit.setFont(font);											 // Set the headtitle to desired font type
		top.add(headtit);												 // add headtit to top JPanel
		borderline = BorderFactory.createLineBorder(Color.gray,2); 		// API to set border
		top.setBorder(borderline); 										// Apply solid line to top JPanel

		// WEST border: Set up buttons for attractions
		// Fill up the array of JButtons, name[]
		left = new JPanel(new GridLayout(5, 1));						// JPanel named left having GridLayout
		name = new JButton[5];  										// Construct an array of 5 JButtons
		name[0] = new JButton("Kyoto");  								// Construct JButton with text Kyoto
		left.add(name[0]);  											// The JPanel left adds this Button
		name[1] = new JButton("Tokyo");  								// Construct JButton with text Tokyo
		left.add(name[1]);												// The JPanel left adds this Button
		name[2] = new JButton("Osaka");  								// Construct JButton with text Osaka
		left.add(name[2]);												// The JPanel left adds this Button
		name[3] = new JButton("Hokkaido");  							// Construct JButton with text Hokkaido
		left.add(name[3]);												// The JPanel left adds this Button
		name[4] = new JButton("Nara");  								// Construct JButton with text Nara
		left.add(name[4]);												// The JPanel left adds this Button

		// EAST border: Set up buttons for cuisines
		// Fill up the array of JButtons, food[]
		right = new JPanel(new GridLayout(5, 1));						//JPanel named right having GridLayout
		food = new JButton[5];											// Construct an array of 5 JButtons
		food[0] = new JButton("Ramen");  								// Construct JButton with text Ramen
		food[0].setIcon(new ImageIcon(this.getClass().getResource("Icons/ramen_mini.png")));	// Set Icon, retrive this image based on the path directory given
		food[0].setHorizontalAlignment(SwingConstants.LEFT);									// Icon Alignment, stay on LHS
		right.add(food[0]);												// The JPanel right adds this Button
		food[1] = new JButton("Gyoza");  								// Construct JButton with text Gyoza
		food[1].setIcon(new ImageIcon(this.getClass().getResource("Icons/gyoza_mini.png")));	// Set Icon, retrive this image based on the path directory given
		food[1].setHorizontalAlignment(SwingConstants.LEFT);									// Icon Alignment, stay on LHS
		right.add(food[1]);												// The JPanel right adds this Button
		food[2] = new JButton("Sushi"); 								// Construct JButton with text Sushi
		food[2].setIcon(new ImageIcon(this.getClass().getResource("Icons/sushi_mini.png")));	// Set Icon, retrive this image based on the path directory given
		food[2].setHorizontalAlignment(SwingConstants.LEFT);									// Icon Alignment, stay on LHS
		right.add(food[2]);												// The JPanel right adds this Button
		food[3] = new JButton("Tempura");  								// Construct JButton with text Tempura
		food[3].setIcon(new ImageIcon(this.getClass().getResource("Icons/tempura_mini.png")));	// Set Icon, retrive this image based on the path directory given
		food[3].setHorizontalAlignment(SwingConstants.LEFT);									// Icon Alignment, stay on LHS
		right.add(food[3]);												// The JPanel right adds this Button
		food[4] = new JButton("Takoyaki");  							// Construct JButton with text Takoyaki
		food[4].setIcon(new ImageIcon(this.getClass().getResource("Icons/takoyaki_mini.png")));	// Set Icon, retrive this image based on the path directory given
		food[4].setHorizontalAlignment(SwingConstants.LEFT);									// Icon Alignment, stay on LHS
		right.add(food[4]);												// The JPanel right adds this Button
		
		// Allocate an instance of the named inner class BtnListener
		BtnListener listener = new BtnListener();
		// Use the same listener instance for all JButtons
		name[0].addActionListener(listener); // name[0] is the source object that fires an ActionEvent
		name[1].addActionListener(listener); // name[1] is the source object that fires an ActionEvent
		name[2].addActionListener(listener); // name[2] is the source object that fires an ActionEvent
		name[3].addActionListener(listener); // name[3] is the source object that fires an ActionEvent
		name[4].addActionListener(listener); // name[4] is the source object that fires an ActionEvent
		food[0].addActionListener(listener); // food[0] is the source object that fires an ActionEvent
		food[1].addActionListener(listener); // food[1] is the source object that fires an ActionEvent
		food[2].addActionListener(listener); // food[2] is the source object that fires an ActionEvent
		food[3].addActionListener(listener); // food[3] is the source object that fires an ActionEvent
		food[4].addActionListener(listener); // food[4] is the source object that fires an ActionEvent

		
		// CENTER border 
		// Layout
		center = new JPanel();											//JPanel named center constructed
		center.setLayout(new BoxLayout(center,BoxLayout.PAGE_AXIS));	//center having BoxLayout from top to bottom
		nested = new JPanel();											//Create subpanel nested
		nested2 = new JPanel();											//Create subpanel nested2
		nested3 = new JPanel();											//Create subpanel nested 3
		nested4 = new JPanel(new GridLayout(2,1));						//Construct subpanel nested4 having GridLayout 2 rows 1 column
		
		//Subscription 
		sub = new JButton("Subscribe");									//Create JButton with text Subscribe
		sub.setFont(new Font(Font.SANS_SERIF, Font.BOLD,15));			//Set the font of the JButton to SANS_SERIF, bold type with size 15
		sub.setBackground(new Color(249,187,187));						//Set the background color of button with a particular RGB value
		sub.setForeground(Color.BLACK);									//Set the foreground to black
		nested.add(sub);												//Add JButton sub to JPanel nested
		//Use the listener instance that we created just now for all JButtons
		sub.addActionListener(listener);  								//sub is the source object that fires an ActionEvent
		
		//Currency conversion
		lblInput = new JLabel("Enter Malaysia Ringgit(MYR):");			//Construct JLabel with text Enter Malaysia Ringgit(MYR):
		nested2.add(lblInput);											//Add JLabel lblInput to JPanel nested2 
		tfInput = new TextField(15);									//Construcct TextField to size 15
		nested2.add(tfInput);											//Add TextField to JPanel nested2
		
		lblOutput = new JLabel("Converted Japanese Yen(JPY):");			//Construct JLabel with text Converted Japanese Yen(JPY):
		nested2.add(lblOutput);											//Add JLabel lblpoutput to JPanel nested2 
		tfOutput = new TextField(15);									//Construct TextField to size 15
		tfOutput.setEditable(false);									//Set the textfield tfoutput to read only 
		nested2.add(tfOutput);											//Add TextField tfOutput to JPanel nested2


		//Time Zone display
		today = new Date(); 										//Construct new Date named today to capture today's date
		df = new SimpleDateFormat("dd-MM-yy HH:mm:SS "); 			//Display format day-month-year hour-minute-second, add z at the back will display zone
		df.setTimeZone(TimeZone.getTimeZone("Asia/Singapore"));		//Set the time zone according to Singapore local time	  
		MYT = df.format(today);										//Set today's date & time to desired display format
		local = new JLabel("Local time: ",SwingConstants.CENTER); 	//Construct JLabel with text Local time, align it to the center
		local_time = new JLabel(MYT);								//Construct JLabel holding MYT which is the local time
		nested4.add(local);											//Add JLabel local to panel nested4
		nested4.add(local_time);									//Add JLabel local_time to panel nested4

		df2 = new SimpleDateFormat("dd-MM-yy HH:mm:SS ");			//Display format day-month-year hour-minute-second, add z at the back will display zone
		df2.setTimeZone(TimeZone.getTimeZone("Japan"));				//Set the time zone according to Japan local time	
		dest = new JLabel("Japan time: ",SwingConstants.CENTER);	//Construct JLabel with text Japan time, align it to the center
		JST = df2.format(today);									//Set today's date & time to desired display format
		dest_time = new JLabel(JST);								//Construct JLabel holding JST which is the Japan local time
		nested4.add(dest);											//Add JLabel dest to panel nested4
		nested4.add(dest_time);										//Add JLabel dest_time to panel nested4
		borderline2 = BorderFactory.createLineBorder(Color.gray); 	//Make use of API to set border, the color of the line border is set to gray
		nested4.setBorder(borderline2);								//Apply solid borderline to Jpanel nested4 
		
		//Add all subpanels to center border
		center.add(nested);		//Add nested to center
		center.add(nested2);	//Add nested2 to center
		center.add(nested3);	//Add nested3 to center
		center.add(nested4);	//Add nested4 to center

		//SOUTH border
		bottom = new JPanel(new FlowLayout());						//JPanel named bottom created, having FlowLayout
		footer = new JLabel("GUI",SwingConstants.CENTER);				//Set JLabel named footer with text, align it to the center
		bottom.add(footer);											//Add footer to JPanel bottom

		// Add panels to container
		setLayout(new BorderLayout());   	//Set to BorderLayout
		cp.add(top, BorderLayout.NORTH);	//Add top to NORTH
		cp.add(left, BorderLayout.WEST);	//Add left to WEST
		cp.add(right,BorderLayout.EAST);	//Add right to EAST
		cp.add(center,BorderLayout.CENTER);	//Add center to CENTER
		cp.add(bottom,BorderLayout.SOUTH);	//Add bottom to SOUTH

		//Add audio clip
		try {
			url = this.getClass().getClassLoader().getResource("Audio/Piano.wav"); 		// Open an audio input stream, get wav file based on the path directory given
			audioIn = AudioSystem.getAudioInputStream(url);			 					// Open an audio input stream
			clip = AudioSystem.getClip();												// Get a sound clip resource
			clip.open(audioIn);															// Open audio clip and load samples from the audio input stream
			clip.start();																// Start the audio
			clip.loop(Clip.LOOP_CONTINUOUSLY);											// Repeat forever
		} catch (UnsupportedAudioFileException e) {										// Exception handling of type UnsupportedAudioFileException
			e.printStackTrace();														// printStackTrace
		} catch (IOException e) {														// Exception handling of type IOException
			e.printStackTrace();														// printStackTrace
		} catch (LineUnavailableException e) {											// Exception handling of type LineUnavailableException
			e.printStackTrace();														// printStackTrace
		}

		//For Currency Conversion
		Converter listener2 = new Converter();	// Allocate an instance of the named inner class BtnListener
		tfInput.addActionListener(listener2);	// tfInput is the source object that fires an ActionEvent
		// Hitting 'enter' on tfInput invokes actionPerformed()

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		// Exit the program when the close-window button clicked
		setVisible(true);    								// "super" Frame shows
		
		//JFrame is the extended version of java.awt.Frame with Swing component architecture
		setTitle("Travel Guide System");  					//"super" Frame sets title, inherited from java.awt.Frame
		setSize(480, 480);   								//"super" Frame sets initial size, inherited from java.awt.Window
		setBounds(300, 90, 480, 480); 						//Set the position of "super" Frame
		setResizable(false); 								//Unable to resize the Frame, inherited from java.awt.Frame
	} //end of constructor					


	// The entry main() method
	public static void main(String[] args) {
		//Run GUI codes in Event-Dispatching thread for thread-safety because Swing is not thread-safe
		//An anonymous inner class is constructed
		//Compiler will generate a name for it, which is Travel$1.class 
		SwingUtilities.invokeLater(new Runnable() {	 //Implement Runnable interface
			@Override
			public void run() {						//Override run method
				new Travel();  						// Let the constructor do the job
			}
		});
	} //end of main method
	
	
	//Inner class is used to improve readability
	//Inner class can access to private attributes of outer class
	//Is a cleaner solution compared to making it an ordinary class
	//It allows us to use private modifier for the class while ordinary class does not allow 
	//So inner class is a safer option
	//"this" class is NOT used as the ActionEventListener so "implements ActionListener" is removed from class Travel
	//But the code is still compiled with the exact same meaning with the class' definition having "implements ActionListener"
	
	//Named inner class, use the same listener for all buttons
	private class BtnListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent evt){
			// Determine which button fired the event
			if(evt.getSource()==name[0]){													//If JButton name[0] fired the event
					textcol = new JPanel(new GridLayout(4,2));								//Construct JPanel having GridLayout 4 rows 2 columns						
					pic = new ImageIcon(this.getClass().getResource("Images/kyoto.png"));	//Construct ImageIcon, retrieve the png image based on the path directory given
					image = new JLabel(pic);												//Construct pic as JLabel

				try{
					reader = new BufferedReader(new FileReader("Files/kyoto_guide.txt"));	//Construct BufferedReader to read from text file, retrieve the file based on the path directory given
					line = reader.readLine();												//Read line by line												
					while(line!= null){														//Enter the loop if the line has content
						strg = new JLabel(line);											//Construct line as JLabel strg
						line = reader.readLine();											//Read line by line
						textcol.add(strg);													//Add strg to Jpanel textcol
					}
				}catch(IOException e){														//Exception handling of type IOException
																							//FileNotFoundException is a subclass of IOException
					e.printStackTrace();													//Print StackTrace
					System.out.println("File not found");									//Print message on cmd
				}finally{																	//Always execute, used for cleaning up
					try{																	//Close should be inside finally else it won't get closed after the code terminates normally
						if(reader != null) reader.close();									//Close reader
					}catch(IOException io){													//Exception handling of type IOException
						System.out.println("File is not closed");							//Print message on the cmd
					}
				}
				
				//Design the layout of pop-up window
				pane = new JPanel();																	//Construct JPanel pane 
				pane.add(image);																		//Add JLabel image to pane
				pane2 = new JPanel(new BorderLayout());													//Construct JPanel pane2 having BorderLayout
				pane2.add(textcol);																		//Add JPanel textcol to pane2
				pane2.add(pane,BorderLayout.EAST);														//Add pane to EAST of pane2 
				JOptionPane.showMessageDialog(null, pane2, "Kyoto Guide",JOptionPane.DEFAULT_OPTION);	//JOptionPane is used to display JPanel pane 2
				
			}else if (evt.getSource()==name[1]){											//If JButton name[1] fired the event
					textcol = new JPanel(new GridLayout(4,2));								//Construct JPanel having GridLayout 4 rows 2 columns						
					pic = new ImageIcon(this.getClass().getResource("Images/tokyo.png"));	//Construct ImageIcon named pic, retrieve the png image based on the path directory given
					image = new JLabel(pic);												//Construct pic as JLabel

				try{
					reader = new BufferedReader(new FileReader("Files/tokyo_guide.txt"));	//Construct BufferedReader to read from text file, retrieve the file based on the path directory given
					line = reader.readLine();												//Read line by line												
					while(line!= null){														//Continue if the line has content
						strg = new JLabel(line);											//Set line as JLabel strg
						line = reader.readLine();											//Read line by line
						textcol.add(strg);													//Add strg to panel textcol
					}
				}catch(IOException e){														//Exception handling
					e.printStackTrace();													//Print StackTrace
				}finally{																	//Always execute, used for cleaning up
					try{																	//Close should be inside finally else it won't get closed after the code terminates normally
						if(reader != null) reader.close();									//Close reader
					}catch(IOException io){													//Exception handling of type IOException
						System.out.println("File is not closed");							//Print message on the cmd
					}
				}
				
				//Design the layout of pop-up window
				pane = new JPanel();																	//Construct JPanel pane 
				pane.add(image);																		//Add JLabel image to pane
				pane2 = new JPanel(new BorderLayout());													//Construct JPanel pane2 having BorderLayout
				pane2.add(textcol);																		//Add JPanel textcol to pane2
				pane2.add(pane,BorderLayout.EAST);														//Add pane to EAST of pane2 
				JOptionPane.showMessageDialog(null, pane2, "Tokyo Guide",JOptionPane.DEFAULT_OPTION);	//JOptionPane is used to display JPanel pane2
			}else if (evt.getSource()==name[2]){											//If JButton name[2] fired the event
					textcol = new JPanel(new GridLayout(4,2));								//Construct JPanel having GridLayout 4 rows 2 columns						
					pic = new ImageIcon(this.getClass().getResource("Images/osaka.png"));	//Construct ImageIcon named pic, retrieve the png image based on the path directory given
					image = new JLabel(pic);												//Construct pic as JLabel

				try{
					reader = new BufferedReader(new FileReader("Files/osaka_guide.txt"));	//Construct BufferedReader to read from text file, retrieve the file based on the path directory given
					line = reader.readLine();												//Read line by line												
					while(line!= null){														//Enter the loop if the line has content
						strg = new JLabel(line);											//Set line as JLabel strg
						line = reader.readLine();											//Read line by line
						textcol.add(strg);													//Add strg to Jpanel textcol
					}
				}catch(IOException e){														//Exception handling of type IOException
					e.printStackTrace();													//Print StackTrace
				}finally{																	//Always execute, used for cleaning up
					try{																	//Close should be inside finally else it won't get closed after the code terminates normally
						if(reader != null) reader.close();									//Close reader
					}catch(IOException io){													//Exception handling of type IOException
						System.out.println("File is not closed");							//Print message on the cmd
					}
				}
				
				//Design the layout of pop-up window
				pane = new JPanel();																	//Construct JPanel pane
				pane.add(image);																		//Add JLabel image to pane
				pane2 = new JPanel(new BorderLayout());													//Construct JPanel pane2 having BorderLayout
				pane2.add(textcol);																		//Add JPanel textcol to pane2
				pane2.add(pane,BorderLayout.EAST);														//Add pane to EAST of pane2 
				JOptionPane.showMessageDialog(null, pane2, "Osaka Guide",JOptionPane.DEFAULT_OPTION);	//JOptionPane is used to display JPanel pane2
			}else if (evt.getSource()==name[3]){											//If JButton name[3] fired the event
					textcol = new JPanel(new GridLayout(4,2));								//Construct JPanel having GridLayout 4 rows 2 columns						
					pic = new ImageIcon(this.getClass().getResource("Images/hokkaido.png"));//Construct ImageIcon named pic, retrieve the png image based on the path directory given
					image = new JLabel(pic);												//Construct pic as JLabel

				try{
					reader = new BufferedReader(new FileReader("Files/hokkaido_guide.txt"));//Construct BufferedReader to read from text file, retrieve the file based on the path directory given
					line = reader.readLine();												//Read line by line												
					while(line!= null){														//Continue if the line has content
						strg = new JLabel(line);											//Set line as JLabel strg
						line = reader.readLine();											//Read line by line
						textcol.add(strg);													//Add strg to Jpanel textcol
					}
				}catch(IOException e){														//Exception handling of type IOException
					e.printStackTrace();													//Print StackTrace
				}finally{																	//Always execute, used for cleaning up
					try{																	//Close should be inside finally else it won't get closed after the code terminates normally
						if(reader != null) reader.close();									//Close reader
					}catch(IOException io){													//Exception handling of type IOException
						System.out.println("File is not closed");							//Print message on the cmd
					}
				}
				
				//Design the layout of pop-up window
				pane = new JPanel();																	//Construct JPanel pane 
				pane.add(image);																		//Add JLabel image to pane
				pane2 = new JPanel(new BorderLayout());													//Construct JPanel pane2 having BorderLayout
				pane2.add(textcol);																		//Add JPanel textcol to pane2
				pane2.add(pane,BorderLayout.EAST);														//Add pane to EAST of pane2 
				JOptionPane.showMessageDialog(null, pane2, "Hokkaido Guide",JOptionPane.DEFAULT_OPTION);//JOptionPane is used to display JPanel pane2
			}else if (evt.getSource()==name[4]){											//If JButton name[3] fired the event
					textcol = new JPanel(new GridLayout(4,2));								//Construct JPanel having GridLayout 4 rows 2 columns						
					pic = new ImageIcon(this.getClass().getResource("Images/nara.png"));	//Construct ImageIcon named pic, retrieve the png image based on the path directory given
					image = new JLabel(pic);												//Construct pic as JLabel

				try{
					reader = new BufferedReader(new FileReader("Files/nara_guide.txt"));	//Construct BufferedReader to read from text file, retrieve the file based on the path directory given
					line = reader.readLine();												//Read line by line												
					while(line!= null){														//Continue if the line has content
						strg = new JLabel(line);											//Set line as JLabel strg
						line = reader.readLine();											//Read line by line
						textcol.add(strg);													//Add strg to Jpanel textcol
					}
				}catch(IOException e){														//Exception handling of type IOException
					e.printStackTrace();													//Print StackTrace
				}finally{																	//Always execute, used for cleaning up
					try{																	//Close should be inside finally else it won't get closed after the code terminates normally
						if(reader != null) reader.close();									//Close reader
					}catch(IOException io){													//Exception handling of type IOException
						System.out.println("File is not closed");							//Print message on the cmd
					}
				}
				
				//Design the layout of pop-up window
				pane = new JPanel();																	//Construct JPanel pane 
				pane.add(image);																		//Add JLabel image to pane
				pane2 = new JPanel(new BorderLayout());													//Construct JPanel pane2 having BorderLayout
				pane2.add(textcol);																		//Add JPanel textcol to pane2
				pane2.add(pane,BorderLayout.EAST);														//Add pane to EAST of pane2 
				JOptionPane.showMessageDialog(null, pane2, "Nara Guide",JOptionPane.DEFAULT_OPTION);	//JOptionPane is used to display JPanel pane2
			}else if (evt.getSource()==food[0]){												//If JButton food[0] fired the event
				pic = new ImageIcon(this.getClass().getResource("Images/ramen.png")); 			//Construct ImageIcon named pic, retrieve the png image based on the path directory given
				image = new JLabel(pic);														//Set pic as JLabel
				pane = new JPanel(new BorderLayout());											//Construct JPanel pane having BorderLayout
				pane.add(image);																//Add image to JPanel pane
				JOptionPane.showMessageDialog(null, pane, "Ramen" ,JOptionPane.DEFAULT_OPTION); //JOptionPane is used to display JPanel pane
			}else if (evt.getSource()==food[1]){
				pic = new ImageIcon(this.getClass().getResource("Images/gyoza.png"));			 //Construct ImageIcon named pic, retrieve the png image based on the path directory given
				image = new JLabel(pic);														//Set pic as JLabel
				pane = new JPanel(new BorderLayout());											//Construct JPanel pane having BorderLayout
				pane.add(image);																//Add image to JPanel pane
				JOptionPane.showMessageDialog(null, pane, "Gyoza",JOptionPane.DEFAULT_OPTION);  //JOptionPane is used to display JPanel pane
			}else if (evt.getSource()==food[2]){
				pic = new ImageIcon(this.getClass().getResource("Images/sushi.png")); 			//Construct ImageIcon named pic, retrieve the png image based on the path directory given
				image = new JLabel(pic);														//Set pic as JLabel
				pane = new JPanel(new BorderLayout());											//Construct JPanel pane having BorderLayout
				pane.add(image);																//Add image to JPanel pane
				JOptionPane.showMessageDialog(null, pane, "Sushi",JOptionPane.DEFAULT_OPTION);	//JOptionPane is used to display JPanel pane
			}else if (evt.getSource()==food[3]){
				pic = new ImageIcon(this.getClass().getResource("Images/tempura.png"));			 //Construct ImageIcon named pic, retrieve the png image based on the path directory given
				image = new JLabel(pic);														 //Set pic as JLabel
				pane = new JPanel(new BorderLayout());											 //Construct JPanel pane having BorderLayout
				pane.add(image);																 //Add image to JPanel pane
				JOptionPane.showMessageDialog(null, pane, "Tempura",JOptionPane.DEFAULT_OPTION); //JOptionPane is used to display JPanel pane
			}else if(evt.getSource()==food[4]){
				pic = new ImageIcon(this.getClass().getResource("Images/takoyaki.png"));  			//Construct ImageIcon named pic, retrieve the png image based on the path directory given
				image = new JLabel(pic);															//Set pic as JLabel
				pane = new JPanel(new BorderLayout());												//Construct JPanel pane having BorderLayout
				pane.add(image);																	//Add image to JPanel pane
				JOptionPane.showMessageDialog(null, pane, "Takoyaki",JOptionPane.DEFAULT_OPTION);   //JOptionPane is used to display JPanel pane
			}else{						//If JButton Subscription fired the event
				dispose();				//Dispose main page
				clip.stop();			//Stop the audio clip
				new Subscription();		//Subscription frame will pop up, let the constructor of class Subscription do the job
			} 
			
		} //end of actionPerformed abstract method
	} //end of named inner class
	
	private class Converter implements ActionListener{							//Named iner class Converter implements ActionListener
		@Override
		public void actionPerformed(ActionEvent evt) {							//Override actionPerformed method
			try{
				numberIn = Double.parseDouble(tfInput.getText());				// Get the String entered into the TextField tfInput, convert to int
			}catch(NumberFormatException e){									//Exception handling of type NumberformatException
				System.out.println("Invalid input. Not an integer");			//Print meaningful message on the command prompt
			}
			
			sum = numberIn*25.41;   											// Perform calculation for conversion
			tfInput.setText("");  												// Clear input TextField
			tfOutput.setText(sum + ""); 										// Display sum on the output TextField
		}
	}
	
} //end of class Travel
