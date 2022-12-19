package quizapplication;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.SocketTimeoutException;
import java.sql.*; 

public class Button2 extends JFrame implements ActionListener {

	JButton exitButton, newQuiz, addQuestion;
	JTextField qname, quizSubText, quizCreaText, quizDescText; 
	String quizName, quizSubject, quizCreator, quizDescription; 
	static final String DB_URL = "jdbc:mysql://localhost:3306/quizzes"; 
	static final String USER = "root1"; 
	static final String PASSWORD = "password"; 
	public int timerCount; 
	JRadioButton tenSeconds, fifteenSeconds, twentySeconds, twentyfiveSeconds; 
	public int quizCounter =1;
	public int currentQuizCounter = quizCounter-1; 
	  

	

	Button2() { 
		
		JFrame mainFrame = new JFrame("Quiz Maker!"); 
		mainFrame.setSize(1200,800); 
		mainFrame.setLayout(new GridLayout(6,2)); 
		

		JLabel heading = new JLabel("", JLabel.CENTER); 
		heading.setText("Let's Create Our Own Quiz!");
		heading.setFont(new Font("Mongolian Baiti", Font.PLAIN,25)); 
		heading.setForeground(new Color(113, 188, 104));
		heading.setBounds(210, 20, 700, 30);
		


		mainFrame.setVisible(true); 
		mainFrame.add(heading); 

		//Create a panel
		JPanel form1 = new JPanel(new GridBagLayout());

		GridBagConstraints constraints = new GridBagConstraints(); 
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets = new Insets(5,5,5,5); 
		constraints.weightx = 1.0;
        constraints.weighty = 0.0;
		


		mainFrame.add(form1);  
		

		//Create the form 
		JLabel label = new JLabel("",JLabel.CENTER);
		label.setText("Name of the Quiz: ");
		label.setFont(new Font("Mongolian Baiti", Font.PLAIN,18)); 
		label.setForeground(new Color(113,188,104)); 
		label.setBounds(60,45,10,20);

		constraints.gridx=0;
		constraints.gridy=0; 
		form1.add(label, constraints);

		qname = new JTextField(30); 
		qname.setBounds(60,100,500,40); 
		qname.setFont(new Font("Times New Roman",Font.PLAIN,20));

		constraints.gridx=1;
		form1.add(qname, constraints);  

		//Enter the Subject, a description, and possibly the creator 
		//First, new Panel 
		JPanel form2 = new JPanel(new GridBagLayout()); 

		GridBagConstraints c = new GridBagConstraints(); 
		c.anchor = GridBagConstraints.CENTER; 
		c.insets = new Insets(10,10,10,10); 
		c.weightx = 0.0;
        c.weighty = 0.0;
		mainFrame.add(form2); 

		JLabel quizSub = new JLabel("",JLabel.CENTER);
		quizSub.setText("Subject of the Quiz:");
		quizSub.setFont(new Font("Mongolian Baiti", Font.PLAIN,15));
		quizSub.setForeground(new Color(113,188,104)); 

		constraints.gridx=0; 
		constraints.gridy=1; 
		form1.add(quizSub,constraints); 

		quizSubText = new JTextField(15); 
		quizSubText.setFont(new Font("Times New Roman",Font.PLAIN,20)); 
		quizSubText.setBounds(60,100,500,40); 

		constraints.gridx=1;  
		form1.add(quizSubText,constraints); 



		
		JLabel quizCrea = new JLabel("",JLabel.CENTER);
		quizCrea.setText("Created By: "); 
		quizCrea.setFont(new Font("Mongolian Baiti", Font.PLAIN,15));
		quizCrea.setForeground(new Color(113,188,104)); 

		constraints.gridx=0;
		constraints.gridy=2; 
		form1.add(quizCrea,constraints);

		quizCreaText = new JTextField(20); 
		quizCreaText.setFont(new Font("Times New Roman",Font.PLAIN,20)); 
		quizCreaText.setBounds(60,100,500,40); 

		constraints.gridx=1;
		form1.add(quizCreaText,constraints);
		
		JLabel quizDesc = new JLabel("",JLabel.CENTER);
		quizDesc.setText("OPTIONAL DESCRIPTION: "); 
		quizDesc.setFont(new Font("Mongolian Baiti", Font.PLAIN,15));
		quizCrea.setForeground(new Color(113,188,104));

		constraints.gridx=0; 
		constraints.gridy=3;
		form2.add(quizDesc,constraints); 

		quizDescText = new JTextField(40); 
		quizDescText.setFont(new Font("Times New Roman",Font.PLAIN,20)); 
		quizDescText.setBounds(60,100,500,40);

		constraints.gridx=1; 
		form2.add(quizDescText,constraints); 

		//Create the Timer
		JPanel form3 = new JPanel(); 
		mainFrame.add(form3); 

		JLabel timerLabel = new JLabel("How long should the timer be?"); 
		timerLabel.setBounds(275, 400,700,40);
		timerLabel.setFont(new Font("Mongolian Baiti", Font.PLAIN,20)); 
		timerLabel.setForeground(new Color(113,188,104)); 
		form3.add(timerLabel); 

		JPanel form4 = new JPanel(); 
		mainFrame.add(form4); 

				//Create Radio Buttons 
				tenSeconds = new JRadioButton("10 seconds"); 
				tenSeconds.setBounds(150,450,120,25); 
				tenSeconds.addActionListener(new SetTimerListener()); 
				form4.add(tenSeconds); 
				
		
				fifteenSeconds = new JRadioButton("15 seconds"); 
				fifteenSeconds.setBounds(290,450,120,25); 
				fifteenSeconds.addActionListener(new SetTimerListener()); 
				form4.add(fifteenSeconds); 
		
				twentySeconds = new JRadioButton("20 Seconds"); 
				twentySeconds.setBounds(430, 450,120,25); 
				twentySeconds.addActionListener(new SetTimerListener()); 
				form4.add(twentySeconds); 
		
				twentyfiveSeconds = new JRadioButton("25 seconds"); 
				twentyfiveSeconds.setBounds(570,450,120,25); 
				twentyfiveSeconds.addActionListener(new SetTimerListener()); 
				form4.add(twentyfiveSeconds);  
		
				//Create a group of Buttons 
				ButtonGroup G1 = new ButtonGroup(); 
				G1.add(tenSeconds); 
				G1.add(fifteenSeconds); 
				G1.add(twentySeconds); 
				G1.add(twentyfiveSeconds); 

		JPanel form5 = new JPanel(); 
		mainFrame.add(form5); 
		


		//Create Button 
		newQuiz = new JButton("Create");
		newQuiz.setBounds(330, 350, 120,25); 
		newQuiz.addActionListener(new QuizMakerListener()); 



		form5.add(newQuiz); 

		exitButton = new JButton("Exit");
		exitButton.setFont(new Font("Mongolian Baiti", Font.BOLD, 13));
		exitButton.setForeground(new Color(113, 188, 104));
		exitButton.addActionListener(this);

		form5.add(exitButton); 




	}
	
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == exitButton) {
			setVisible(false);
			new Login();
		}
		/*else {
			setVisible(false);
		}*/


        //Used EventDemo 
		if(ae.getSource() == newQuiz){
			newQuiz.setEnabled(false);
			quizName = qname.getText();
			System.out.println(quizName);
			
			//Create a new table that will contain the new quiz 
			try {
				this.createATable(quizName);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			

		
		}



	}

	//Create a table for the quiz 
	public void createATable(String quizName) throws SQLException {
		try(Connection conn =DriverManager.getConnection(DB_URL, USER, PASSWORD); 
				Statement stmt = conn.createStatement();
				){
			String sql = 
				"CREATE TABLE "+quizName+","+
				"(id INTEGER not null,"+
				"question VARCHAR(1000),"+
				"answer1 VARCHAR(255), "+
				"answer2 VARCHAR(255), "+
				"answer3 VARCHAR(255), "+
				"answer4 VARCHAR(255), "+
				"PRIMARY KEY (id))"; 
			stmt.executeUpdate(sql); 
		}
	}


	//Create a new Quiz 
	private class QuizMakerListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			
			quizName = qname.getText(); 
			System.out.println(quizName); 

			quizSubject = quizSubText.getText(); 
			System.out.println(quizSubject);

			quizCreator = quizCreaText.getText(); 
			System.out.println(quizCreator);

			quizDescription = quizDescText.getText();
			System.out.println(quizDescription); 

			try {
				insertQuiz();
				System.out.println("Quiz have been added to the database!");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			if(tenSeconds.isSelected()){
				timerCount = 10; 
			}
			else if(fifteenSeconds.isSelected()){
				timerCount = 15; 
			}
			else if(twentySeconds.isSelected()){
				timerCount = 20; 
			}
			else{
				timerCount = 25; 
			}
			System.out.println(timerCount); 
			new Button_Button2();


			
		}

		
	}
	
	//For timers - NOT USED
	private class SetTimerListener implements ActionListener{
		public void actionPerformed (ActionEvent e){
			if(e.getSource() == tenSeconds){
				timerCount = 10; 
			}
			else if(e.getSource() == fifteenSeconds){
				timerCount = 15; 
			}
			else if(e.getSource() == twentySeconds){
				timerCount = 20; 
			}
			else{
				timerCount = 25; 
			}

		}
	}
	
	//Get the QuizID 
	public int getQuizID(){
		return currentQuizCounter; 
	}

	public void setGetID(int currentQuizCounter){
		this.currentQuizCounter = currentQuizCounter; 

	}

	public int getTimerAmount(){
		return timerCount; 
	}

	public void setTimerAmount(int timerCount){
		this.timerCount = timerCount; 
	}
	
	//Upload questions to the table
	public void insertQuiz() throws SQLException{
		try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD); 
		Statement stmt = conn.createStatement(); 
		){
			String sql= "INSERT INTO quiz(QuizID, QuizName, QuizDescription,QuizSubject, QuizCreator ) VALUES ("+quizCounter+", '"+quizName+"', '"+quizDescription+"', '"+quizSubject+"', '"+quizCreator+"')"; 
			stmt.executeUpdate(sql); 

		}
		quizCounter+=1; 
	}
	
	public static void main(String[] args) {
	 new Button2();

	 System.out.println("Connecting database...");

try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
    System.out.println("Database connected!");
} catch (SQLException e) {
    throw new IllegalStateException("Cannot connect the database!", e);
}
	}
	

}