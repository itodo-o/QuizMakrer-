package quizapplication;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
public class displayQuiz extends JFrame implements ActionListener {


    static final String DB_URL = "jdbc:mysql://localhost:3306/quizzes"; 
	static final String USER = "root1"; 
	static final String PASSWORD = "password";
    static final String QUERY = "SELECT QuestionID, QuestionText, QuestionType, QuizID from questions"; 

    JLabel qno, quest; 
    JRadioButton opt1, opt2,opt3, opt4; 
    ButtonGroup groupOptions; 
    int questionNo = 1; 
    String questionName, questionType; 
    String answerI, answerII, answerIII, answerIV, answerC; 

    public static int timer = 0;
	public static int ans_given = 0;
	public static int count = 0;
	public static int score=0;
    public static int numRows =0; 
    public int timerAmount = 0; 

    JButton next, submit;
    JButton exitButton; 
    String userAnswers [][] = new String[20][1]; ;
    String correctAnswers [][] = new String [20][1]; 
    JTextField textOpt; 



    displayQuiz(){
        /* JFrame mainFrame = new JFrame("Quiz Time!"); 
        mainFrame.setSize(1000,650); 
        mainFrame.setLayout(new GridLayout(6,3)); */
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        //question number
        qno = new JLabel();
        qno.setBounds(170, 180, 100, 25);
		qno.setFont(new Font("Mongolian Baiti", Font.PLAIN, 20));
		add(qno);

        //question itself
        quest = new JLabel();
		quest.setBounds(195, 180, 1000, 25);
		quest.setFont(new Font("Mongolian Baiti", Font.PLAIN, 20));
		add(quest);

        opt1 = new JRadioButton();
		opt1.setBounds(200, 195, 700,60);
		opt1.setBackground(Color.white); 
        opt1.setFont(new Font("Dialog", Font.PLAIN,17));
		add(opt1);
				 
		opt2 = new JRadioButton();
		opt2.setBounds(200, 235, 700,60);
		opt2.setBackground(Color.white);
		opt2.setFont(new Font("Dialog", Font.PLAIN,17));
		add(opt2);
				 
		opt3 = new JRadioButton();
		opt3.setBounds(200, 275, 700,60);
		opt3.setBackground(Color.white);
		opt3.setFont(new Font("Dialog", Font.PLAIN,17));
		add(opt3);
				 
		opt4 = new JRadioButton();
		opt4.setBounds(200, 315, 700,60);
		opt4.setBackground(Color.white);
		opt4.setFont(new Font("Dialog", Font.PLAIN,17)); 
		add(opt4);
				 
		groupOptions = new ButtonGroup();
		groupOptions.add(opt1);
		groupOptions.add(opt2);
		groupOptions.add(opt3);
		groupOptions.add(opt4);

        textOpt = new JTextField(30); 
        textOpt.setFont(new Font("Times New Roman",Font.PLAIN,20)); 
		textOpt.setBounds(60,100,500,40); 
        textOpt.setVisible(false); 
        add(textOpt); 

        

        JPanel buttonG = new JPanel(); 
        add(buttonG);
          
        next = new JButton("Next");
		next.setBounds(400, 480, 150, 40);
		next.setFont(new Font("Mongolian Baiti", Font.BOLD, 20));
		next.setForeground(new Color(113, 188, 104));
		next.addActionListener(this);
		add(next);	 
				 
		submit = new JButton("Submit");
		submit.setBounds(630, 480, 150, 40);
		submit.setFont(new Font("Mongolian Baiti", Font.BOLD, 20));
		submit.setForeground(new Color(113, 188, 104));
		submit.addActionListener(this);
		submit.setEnabled(false);
		add(submit);
		
		// exit
		exitButton = new JButton("Exit");
		exitButton.setBounds(170, 480, 150, 40);
		exitButton.setFont(new Font("Mongolian Baiti", Font.BOLD, 20));
		exitButton.setForeground(new Color(113, 188, 104));
		exitButton.addActionListener(this);
		exitButton.setEnabled(false);
		add(exitButton);

        setSize(1000, 650);
		setLocation(350, 100);

        start(); 
        setVisible(true); 
        






    }
    
    
    
    public void actionPerformed(ActionEvent ae){

        if(ae.getSource() == next){
            repaint();
            ans_given =1; 

            try {
                displayQuestion();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            

            if(groupOptions.getSelection()==null){
                userAnswers[count][0] = ""; 
            }
            else{
                userAnswers[count][0] = groupOptions.getSelection().getActionCommand(); 
            }
        
          if(count == 8){
				next.setEnabled(false);
				submit.setEnabled(true);
			}

			count++;
			start(); 
        }

        else if(ae.getSource() == submit){

            try {
                getCorrectAnswer();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            ans_given =1; 
            if(groupOptions.getSelection()==null){
                userAnswers[count][0] = ""; 
            }
            else{
                userAnswers[count][0] = groupOptions.getSelection().getActionCommand(); 
            }

           
 

            for(int i = 0; i < numRows; i++){

                if(userAnswers[i][0].equals(correctAnswers[i][0])){
                    score+=(100 / numRows); 
                }

                else{
                    score+=0; 
                }

                setVisible(false); 

                new Score(score); 

                
            }
                
     
    }
            
        else if (ae.getSource() == exitButton){
                    setVisible(false); 
                    new Login(); 
                }
    }
    
	public void paint(Graphics g) {	
		super.paint(g);
		
		String time = "Time left - " + timer + " seconds"; 
		g.setColor(Color.RED);
		g.setFont(new Font("Tahoma", Font.BOLD, 20));
      
		exitButton.setEnabled(true);

	
	     if(timer>0) {
			g.drawString(time, 376, 445);
	     }
	     else {
				g.drawString("Times Up!!", 400, 445);
	     }
	     timer--;
	     
	     try {
	    	 Thread.sleep(1000);
	    	 repaint(); // time decrease
	     } catch (Exception e) {
	    	 e.printStackTrace(); 
	     }
	     
	     if(ans_given ==1) {
	    	 ans_given = 0;
	    	 timer = 15;
	     } else if(timer<0) {
	    	 
	    	 timer =15;
	    	 
	    	 if(count == 8) {
					next.setEnabled(false);
					submit.setEnabled(true);
					exitButton.setEnabled(true);
				}
	    	 if(count == 9) {
	    		 
	    		 if(groupOptions.getSelection() == null) {
	    			 userAnswers[count][0] = "";
	    		 }
	    		 else {
	    			 userAnswers[count][0] = groupOptions.getSelection().getActionCommand() ;
	    		 }
	    		 
	    		 for(int i=0; i<userAnswers.length; i++) {
	    			 if(userAnswers[i][0].equals(correctAnswers[i][1])) {
	    				 		score +=10;
	    			 }
	    			 else {
	    				 score+=0;
	    			 }
	    		 }
	    		 
	    		 setVisible(false);
	    		 
	    		 new Score(score);
	    	 }
	    	 
	    	 else { // next button
	    		 if(groupOptions.getSelection() == null) {
	    			 userAnswers[count][0] = "";
	    		 }
	    		 else {
	    			 userAnswers[count][0] = groupOptions.getSelection().getActionCommand() ;
	    		 }
	    		 questionNo++;
	    		 start();
	    	 	}
	     	}
	}
    

    //Obtain information from mySQL - question 
    public void displayQuestion() throws SQLException{
        try (Connection conn = DriverManager.getConnection(DB_URL,USER,PASSWORD); 
        Statement stmt = conn.createStatement();
        
        ){  int count=0; 
            String sql = "SELECT QuestionID, QuestionText,QuestionType FROM questions WHERE(QuestionID) ="+count+""; 
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()){
                questionName = rs.getString("QuestionText"); 
                questionType = rs.getString("QuestionType");

                quest.setText(questionName);
                System.out.println(quest); 
                count++; 
            }

        }

    }
    
    
    //Obtain information from mySQL - answers
    public void displayAnswer() throws SQLException{
        try(Connection conn = DriverManager.getConnection(DB_URL,USER, PASSWORD);
        Statement stmt = conn.createStatement();
        ){
            String sql = "SELECT * FROM answers WHERE (QuestionID)="+count+""; 
            ResultSet rs = stmt.executeQuery(sql); 

            while(rs.next()){
                answerI = rs.getString("Answer1") ; 
                answerII = rs.getString("Answer2");
                answerIII = rs.getString("Answer3");
                answerIV = rs.getString("Answer4") ;
                answerC = rs.getString("CorrectAnswer") ;

                opt1.setText(answerI);
                opt2.setText(answerII);
                opt3.setText(answerIII);
                opt4.setText(answerIV);

                count++; 

            }

        }

         
    }

    //Put all the correct answers in a table
    public void getCorrectAnswer() throws SQLException{
        try(Connection conn = DriverManager.getConnection(DB_URL,USER,PASSWORD);
        Statement stmt = conn.createStatement(); 
        ){
            ResultSet rs = stmt.executeQuery(QUERY); 
            String sql = "SELECT CorrectAnswer FROM answers";
            rs = stmt.executeQuery(sql); 

            while(rs.next()){
                    userAnswers[numRows][0] = rs.getString("CorrectAnswer"); 
                    numRows++; 
    
            }
        }

     }

    //Find number of rows 
    public void rowNumber() throws SQLException{
        try(Connection conn = DriverManager.getConnection(DB_URL,USER,PASSWORD);
        Statement stmt = conn.createStatement(); 
        ){
            ResultSet rs = stmt.executeQuery(QUERY); 
            String sql = "SELECT COUNT(AnswerID) as answer_num"; 
            rs = stmt.executeQuery(sql); 

            while(rs.next()){
                numRows = rs.getRow(); 
            }


        }
    }


    // Obtain Timer amount
    public void setTimer(){
        Button2 t = new Button2(); 
        timerAmount = t.getTimerAmount(); 

    }
    
    
    //start function 
    public void start(){

        try {
            displayQuestion();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 
        try {
            displayAnswer();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 

        
        qno.setText(""+questionNo+". ");
        quest.setText(questionName); 
        quest.setVisible(true); 

        if(questionType == "Multiple Choice"){
            opt1.setText(answerI); 
            opt1.setActionCommand(answerI); 

            opt2.setText(answerII); 
            opt2.setActionCommand(answerII); 

            opt3.setText(answerIII);
            opt3.setActionCommand(answerIII); 

            opt4.setText(answerIV); 
            opt4.setActionCommand(answerIV); 

            groupOptions.clearSelection(); 

            opt1.setVisible(true); 
            opt2.setVisible(true); 
            opt3.setVisible(true); 
            opt4.setVisible(true); 
        }

        else if(questionType == "True or False"){
            opt1.setText("False"); 
            opt1.setActionCommand("False"); 

            opt2.setText("True");
            opt2.setActionCommand("True"); 

            opt1.setVisible(true); 
            opt2.setVisible(true); 

            groupOptions.clearSelection(); 
        }

        else if(questionType == "Short Answer"){
            textOpt.setVisible(true); 
        }

        questionNo++;
        count++;

    } 
  

    public static void main(String[] args){
        new displayQuiz(); 
         

    }
}
