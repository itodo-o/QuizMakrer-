package quizapplication;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*; 

public class Button_Button2 extends JFrame implements ActionListener {
    

    static final String DB_URL = "jdbc:mysql://localhost:3306/quizzes"; 
	static final String USER = "root1"; 
	static final String PASSWORD = "password";

    int questionCount; 
    JTextField questionWhoText, answerAText, answerBText, answerCText, answerDText, answerText; 
    JRadioButton MC, shortAnswer, longAnswer, TF; 
    String questionTypeS; 
    JPanel multipleChoice, shortA, TrueFalse; 
    JRadioButton True, False; 
    JRadioButton answerA, answerB,answerC,answerD; 
    int QuizID = 1; 
    String correctAnswer; 
    String answerA1, answerB1, answerC1, answerD1; 
    int answerCount; 
    JButton addQuestion, newQuestion, takeQuiz; 
    String questionName;
    JFrame mainFrame; 
    ButtonGroup G1;  


    Button_Button2(){



        mainFrame = new JFrame("Questions"); 
        mainFrame.setSize(800,800); 
        mainFrame.setLayout(new GridLayout(6,2)); 

        mainFrame.setVisible(true); 

        JLabel heading = new JLabel("", JLabel.CENTER); 
        heading.setText("Let's start putting Questions in our Quiz!"); 
        heading.setFont(new Font("Mongolian Baiti", Font.PLAIN,25)); 
		heading.setForeground(new Color(113, 188, 104));
		heading.setBounds(210, 20, 700, 30);
        mainFrame.add(heading); 

        //Create Question Name 


        JPanel questionName = new JPanel(new GridBagLayout()); 
        mainFrame.add(questionName); 

        GridBagConstraints constraints = new GridBagConstraints(); 
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets = new Insets(10,10,10,10); 
		constraints.weightx = 0.0;
        constraints.weighty = 0.0; 

        JLabel questionWho = new JLabel("", JLabel.CENTER); 
        questionWho.setText("Question: "); 
        questionWho.setFont(new Font("Mongolian Baiti", Font.PLAIN,18)); 
        questionWho.setForeground(new Color(113,188,104)); 

        constraints.gridx=0; 
        constraints.gridy=0;
        questionName.add(questionWho, constraints); 

        questionWhoText = new JTextField(30); 
        questionWhoText.setFont(new Font("Times New Roman", Font.PLAIN,18)); 

        constraints.gridx=1; 
        questionName.add(questionWhoText,constraints); 

        //Question Type 
        JPanel questionT = new JPanel(); 
        mainFrame.add(questionT); 

        JLabel questionType = new JLabel("What type of quiz will it be?"); 
        questionType.setFont(new Font("Mongolian Baiti", Font.PLAIN,20));
        questionType.setForeground(new Color(113,188,104));
        questionT.add(questionType);

        JPanel questionTButtons = new JPanel(); 
        mainFrame.add(questionTButtons); 

        MC = new JRadioButton("Multiple Choice"); 
        MC.addActionListener(this); 
        questionTButtons.add(MC); 

        shortAnswer = new JRadioButton("Short Answer");
        shortAnswer.addActionListener(this); 
        questionTButtons.add(shortAnswer); 


        longAnswer = new JRadioButton("Long Answer"); 
        questionTButtons.add(longAnswer);


        TF = new JRadioButton("True or False"); 
        TF.addActionListener(this); 
        questionTButtons.add(TF);

        //Button Group 
        G1 = new ButtonGroup(); 
        G1.add(MC); 
        G1.add(shortAnswer); 
        G1.add(longAnswer); 
        G1.add(TF); 
         

        //Panels for these panels 
        JPanel answerOptions = new JPanel(new GridBagLayout()); 

        GridBagConstraints ce = new GridBagConstraints(); 
        ce.anchor = GridBagConstraints.WEST; 
        ce.insets = new Insets(1,1,1,1); 
		ce.weightx = 1.0;
        ce.weighty = 1.0;

        mainFrame.add(answerOptions); 

 

        //Multiple Choice series of panels 
        multipleChoice = new JPanel(new GridBagLayout()); 

        GridBagConstraints c1 = new GridBagConstraints(); 
        c1.anchor = GridBagConstraints.WEST; 
        c1.insets = new Insets(1,1,1,1); 
		c1.weightx = 1.0;
        c1.weighty = 0.0;

        multipleChoice.setVisible(false); 

        ce.gridx=0; 
        ce.gridy=0;
        answerOptions.add(multipleChoice,ce); 

        /*JLabel answerA = new JLabel("", JLabel.CENTER); 
        answerA.setText("A:"); 
        answerA.setFont(new Font("Mongolian Baiti", Font.PLAIN,18)); 
        answerA.setForeground(new Color(113,188,104));  */

        answerA = new JRadioButton("A"); 
        answerA.setFont(new Font("Mongolian Baiti", Font.PLAIN,18));
        answerA.addActionListener(this); 

        c1.gridx=0; 
        c1.gridy=0;
        multipleChoice.add(answerA, c1); 

        answerAText = new JTextField(30); 
        answerAText.setFont(new Font("Times New Roman", Font.PLAIN,18));

        c1.gridx =1; 
        multipleChoice.add(answerAText, c1); 

        /* JLabel answerB = new JLabel("", JLabel.CENTER); 
        answerB.setText("B:"); 
        answerB.setFont(new Font("Mongolian Baiti", Font.PLAIN,18)); 
        answerB.setForeground(new Color(113,188,104));*/

        answerB = new JRadioButton("B"); 
        answerB.setFont(new Font("Mongolian Baiti", Font.PLAIN,18));
        answerB.addActionListener(this); 


        c1.gridx=0; 
        c1.gridy=1;
        multipleChoice.add(answerB, c1); 

        answerBText = new JTextField(30); 
        answerBText.setFont(new Font("Times New Roman", Font.PLAIN,18));

        c1.gridx=1; 
        multipleChoice.add(answerBText, c1);

        /*JLabel answerC = new JLabel("", JLabel.CENTER); 
        answerC.setText("C:"); 
        answerC.setFont(new Font("Mongolian Baiti", Font.PLAIN,18)); 
        answerC.setForeground(new Color(113,188,104)); */

        answerC = new JRadioButton("C"); 
        answerC.setFont(new Font("Mongolian Baiti", Font.PLAIN,18));
        answerC.addActionListener(this);

        c1.gridx=0; 
        c1.gridy=2;
        multipleChoice.add(answerC, c1); 

        answerCText = new JTextField(30); 
        answerCText.setFont(new Font("Times New Roman", Font.PLAIN,18));

        c1.gridx=1; 
        multipleChoice.add(answerCText, c1);

       /* JLabel answerD = new JLabel("", JLabel.CENTER); 
        answerD.setText("D:"); 
        answerD.setFont(new Font("Mongolian Baiti", Font.PLAIN,18)); 
        answerD.setForeground(new Color(113,188,104)); */

        answerD= new JRadioButton("D"); 
        answerD.setFont(new Font("Mongolian Baiti", Font.PLAIN,18));
        answerD.addActionListener(this);

        c1.gridx=0; 
        c1.gridy=3;
        multipleChoice.add(answerD, c1);

        answerDText = new JTextField(30); 
        answerDText.setFont(new Font("Times New Roman", Font.PLAIN,18));

        c1.gridx=1; 
        multipleChoice.add(answerDText, c1);

        //Short Answer Panel 
        shortA = new JPanel(new GridBagLayout());
        
        GridBagConstraints c2 = new GridBagConstraints(); 
        c2.anchor = GridBagConstraints.WEST; 
        c2.insets = new Insets(10,10,10,10); 
		c2.weightx = 1.0;
        c2.weighty = 1.0;

        shortA.setVisible(false); 

        ce.gridx =0;
        ce.gridy=0; 
        answerOptions.add(shortA,ce); 

        JLabel answer = new JLabel("", JLabel.CENTER); 
        answer.setText("Answer:"); 
        answer.setFont(new Font("Mongolian Baiti", Font.PLAIN,18)); 
        answer.setForeground(new Color(113,188,104)); 

        c2.gridx=0; 
        c2.gridy=0;
        shortA.add(answer, c2);

        answerText = new JTextField(30); 
        answerText.addActionListener(this);
        answerText.setFont(new Font("Times New Roman", Font.PLAIN,18));

        c2.gridx=1; 
        shortA.add(answerText, c2);

        //True or False Panel 

        TrueFalse = new JPanel(new GridBagLayout()); 

        GridBagConstraints c3 = new GridBagConstraints(); 
        c3.anchor = GridBagConstraints.CENTER; 
        c3.insets = new Insets(10,10,10,10); 
		c3.weightx = 0.0;
        c3.weighty = 0.0;

        TrueFalse.setVisible(false); 

        ce.gridx = 0; 
        ce.gridy = 0;
        answerOptions.add(TrueFalse,ce); 

        True = new JRadioButton("True"); 
        True.addActionListener(this);
        
        
        c3.gridx =1; 
        c3.gridy=0; 
        TrueFalse.add(True,c3); 


        False = new JRadioButton("False"); 
        False.addActionListener(this); 

        c3.gridx = 1; 
        c3.gridy = 1; 
        TrueFalse.add(False,c3); 

        ButtonGroup H1 = new ButtonGroup(); 
        H1.add(True); 
        H1.add(False); 


        //Long Essay 

        //Button to place info into the database 
        addQuestion = new JButton("Add Question"); 
        addQuestion.setBounds(330, 350, 20,25);
        addQuestion.addActionListener(this); 


        JPanel buttonQ = new JPanel(); 
        mainFrame.add(buttonQ); 


       buttonQ.add(addQuestion);

       newQuestion = new JButton("New Question");
       newQuestion.setBounds(330, 350, 20,25); 
       newQuestion.addActionListener(this); 
       takeQuiz = new JButton(); 

       buttonQ.add(newQuestion);  

       takeQuiz = new JButton("Take Quiz"); 
       takeQuiz.setBounds(330, 350, 20,25); 
       takeQuiz.addActionListener(this);

       buttonQ.add(takeQuiz); 


       

    }

    public void actionPerformed(ActionEvent ae){

        if(MC.isSelected()){
            questionTypeS = "Multiple Choice"; 
            multipleChoice.setVisible(true);
            shortA.setVisible(false); 

        }

        else if(shortAnswer.isSelected()){
            questionTypeS = "Short Answer"; 
            multipleChoice.setVisible(false); 
            shortA.setVisible(true); 
            TrueFalse.setVisible(false); 
        }

        else if(TF.isSelected()){
            questionTypeS = "True or False";
            TrueFalse.setVisible(true); 
            multipleChoice.setVisible(false); 
            shortA.setVisible(false);
        }

        else {
            multipleChoice.setVisible(false); 
            shortA.setVisible(false);
        }

        if(ae.getSource() == addQuestion ){
            questionName = questionWhoText.getText(); 

           /* */ if(questionTypeS == "True or False"){
                if(True.isSelected()){
                correctAnswer = "True"; 
            }
                else{
                correctAnswer = "False"; 
            }
              
            }
             
            else if(questionTypeS == "Multiple Choice"){
            answerA1 = answerAText.getText(); 
            answerB1 = answerBText.getText();
            answerC1 = answerCText.getText();
            answerD1 = answerDText.getText();

            if (answerA.isSelected()){
                correctAnswer = answerA1; 
            }
            else if(answerB.isSelected()){
                correctAnswer = answerB1; 
            }

            else if(answerC.isSelected()){
                correctAnswer = answerC1; 
            }
            else{
                correctAnswer = answerD1; 
            }
            }

            else if(questionTypeS == "Short Answer"){
                answerA1 = answerText.getText(); 
                correctAnswer = answerA1; 
            }
  
            
            
            
            try {
                this.insertQuestions();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } 

            try {
                this.insertAnswers();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } 

            questionCount++;
            answerCount++; 
            questionWhoText.setText(""); 
            answerText.setText("");
            answerAText.setText(""); 
            answerBText.setText("");
            answerCText.setText("");
            answerDText.setText("");
            G1.clearSelection(); 
            answerA1 =""; 
            answerB1 ="";
            answerC1 ="";
            answerD1 ="";
            mainFrame.repaint(); 
            
            
        }

       else if(ae.getSource() == takeQuiz){
            setVisible(false);
            new displayQuiz(); 
        }

        else if(ae.getSource() == newQuestion){

            G1.clearSelection();
            questionWhoText.setText("");
            answerAText.setText(""); 
            answerBText.setText("");
            answerCText.setText("");
            answerDText.setText("");

            shortA.setVisible(false);
            multipleChoice.setVisible(false); 
            TrueFalse.setVisible(false); 


        }
    }


    //Create a JOptionPane
    public class moreQuestions implements ActionListener{
        public void actionPerformed(ActionEvent a){
            if(newQuestion.isSelected()){
             questionCount+=1; 

             G1.clearSelection();
             questionWhoText.setText("");
             answerAText.setText(""); 
             answerBText.setText("");
             answerCText.setText("");
             answerDText.setText("");
              
            }
             
        }

    }



    //Upload question into the database
    public void insertQuestions() throws SQLException{ 


        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD); 
		Statement stmt = conn.createStatement(); 
        ){
            String sql = "INSERT INTO questions(QuestionID, QuestionText, QuestionType, QuizID) VALUES ("+questionCount+", '"+questionName+"', '"+questionTypeS+"',"+QuizID+")"; 
            stmt.executeUpdate(sql); 
        }
    } 
    
    //Correct Answer in the true or false 
    public class trueFalseAnswer implements ActionListener{
        public void actionPerformed (ActionEvent ea){
            if(True.isSelected()){
                correctAnswer = "True"; 
            }
            else{
                correctAnswer = "False"; 
            }
        }

    }
    
    //Correct Answer in MultipleChoice
    public class multipleAnswer implements ActionListener{
        public void actionPerformed (ActionEvent e){

            answerA1 = answerAText.getText(); 
            answerB1 = answerBText.getText();
            answerC1 = answerCText.getText();
            answerD1 = answerDText.getText();

            if (answerA.isSelected()){
                correctAnswer = answerA1; 
            }
            else if(answerB.isSelected()){
                correctAnswer = answerB1; 
            }

            else if(answerC.isSelected()){
                correctAnswer = answerC1; 
            }
            else{
                correctAnswer = answerD1; 
            }

        }
    }
    
    //Upload answers to database
    public void insertAnswers() throws SQLException{
        try(Connection conn = DriverManager.getConnection(DB_URL,USER,PASSWORD); 
        Statement stmt = conn.createStatement(); 
        ){
            String sql = "INSERT INTO answers(AnswerID, Answer1, Answer2, Answer3, Answer4, CorrectAnswer,QuestionID) VALUES ("+answerCount+",'"+answerA1+"', '"+answerB1+"','"+answerC1+"','"+answerD1+"','"+correctAnswer+"', "+questionCount+" )"; 
            stmt.executeUpdate(sql);     }
    }

    //Do a series of updates - questionCounter,erasing old information to make way

    public static void main(String[] args){
        new Button_Button2(); 

        System.out.println("Connecting database...");

try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
    System.out.println("Database connected!");
} catch (SQLException e) {
    throw new IllegalStateException("Cannot connect the database!", e);
}
    }
}
