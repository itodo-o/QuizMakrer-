package quizapplication;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Button1 extends JFrame implements ActionListener {

	String question[][]= new String [10][5];
	String answers[][] = new String[10][2]; 
	String userAnswers[][]= new String[10][1];
	JLabel qno, quest;
	JRadioButton opt1,opt2, opt3,opt4;
	JButton next, submit;
	ButtonGroup groupOptions;
	JButton exitButton;
	 
	public static int timer = 15;
	public static int ans_given = 0;
	public static int count = 0;
	public static int score=0;
	String name;
	
	Button1() {
		
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
		
		// another title of the program 
		JLabel heading = new JLabel("Quiz Time!");
		heading.setBounds(365, 69, 700, 50);
		heading.setFont(new Font("Dialog", Font.BOLD, 40));
		heading.setForeground(new Color(113, 188, 104));
		add(heading);
		
		// question number 
		qno = new JLabel();
		qno.setBounds(170, 180, 100, 25);
		qno.setFont(new Font("Mongolian Baiti", Font.PLAIN, 20));
		add(qno);
		
		// question itself
	    quest = new JLabel();
		quest.setBounds(195, 180, 1000, 25);
		quest.setFont(new Font("Mongolian Baiti", Font.PLAIN, 20));
		add(quest);
		
		// 1
		question[0][0] = "Java is a ...";
		question[0][1] = "high-level programming language";
		question[0][2] = "object-oriented (class-based) programming language";
		question[0][3] = "functional, imperative and reflective programming language";
		question[0][4] = "All the above!";
		
		// 2
		question[1][0] = "What is the default value of data type boolean in Java?";
		question[1][1] = "1";
		question[1][2] = "0";
		question[1][3] = "false";
		question[1][4] = "true";
		
		// 3
		question[2][0] = "What is the size of char variable?";
		question[2][1] = "64 bit";
		question[2][2] = "32 bit";
		question[2][3] = "16 bit";
		question[2][4] = "8 bit";

		// 4
		question[3][0] = "Which of the below is valid way to instantiate an array in java?";
		question[3][1] = "int myArray[] = (5,4,3);";
		question[3][2] = "int myArray[] = {1,3,5};"; 
		question[3][3] = "int[] myArray = (8,4,2); ";
		question[3][4] = "int[] myArray = {one, two, three};";		 
			
		// 5
		question[4][0] = "Which of the following statements are true for inheritance in Java?";
		question[4][1] = "You can extend multiple classes jn java";
		question[4][2] = "Private members of the superclass are accessible to the subclass";
		question[4][3] = "We can not extend Final classes in java";
		question[4][4] = "The 'extend' keywords is used to extend a class in java";
			
		// 6
		question[5][0] = "Which of the following is not a Java features?";
		question[5][1] = "Architecture Neutral";
		question[5][2] = "Use of pointers";
		question[5][3] = "Object-oriented";
		question[5][4] = "Dynamic";
			
		// 7 
		question[6][0] = "Which of the following is not true for a generic collection?";
		question[6][1] = "Generic collection doesn't require typecasting";
		question[6][2] = "Generic collection is typesafe and checked at compile time";
		question[6][3] = "Generic collection is not mutable";
		question[6][4] = "Generic collection is mutable";
			
		// 8
		question[7][0] = "Which of the following is smallest integer data type?";
		question[7][1] = "byte";
		question[7][2] = "long";
		question[7][3] = "int";
		question[7][4] = "short";
			
		// 9
		question[8][0] = "In which of the following is toString() method defined?";
		question[8][1] = "java.lang.String";
		question[8][2] = "java.lang.util";
		question[8][3] = "java.lang.Object";
		question[8][4] = "None";
			
		// 10
		question[9][0] = " Which method can be used to find the length of a string?";
		question[9][1] = "getLength()";
		question[9][2] = "getSize()";
		question[9][3] = "len()";
		question[9][4] = "length()"; 

		// Answers 
		answers[0][1] = "All the above!";
		answers[1][1] = "false";
		answers[2][1] = "16 bit";
		answers[3][1] = "int myArray[] = {1,3,5};";
		answers[4][1] = "We can not extend Final classes in java";
		answers[5][1] = "Use of pointers";
		answers[6][1] = "Generic collection is not mutable";
		answers[7][1] = "byte";
		answers[8][1] = "java.lang.Object";
		answers[9][1] = "length()";
				 
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
		
		// displays the window
		setSize(1000, 650);
		setLocation(350, 100);
		
		start(count);
		setVisible(true);
	}
	
	
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == next) {

			repaint();
			ans_given = 1;
			
			if(groupOptions.getSelection()==null) {
				userAnswers[count][0]= "";
			}
			else {
				userAnswers[count][0]= groupOptions.getSelection().getActionCommand();
			}
			if(count == 8){
				next.setEnabled(false);
				submit.setEnabled(true);
			}
			count++;
			start(count);
		}
		
		else if (ae.getSource()==submit) {
			ans_given =1;
			if(groupOptions.getSelection() == null) {
				userAnswers[count][0] = "";
			}
			else {
				userAnswers[count][0] = groupOptions.getSelection().getActionCommand() ;
			}
			for(int i=0; i<userAnswers.length; i++) {
				if(userAnswers[i][0].equals(answers[i][1])) {
				 		score +=10;
				}
				else {
					score+=0;
				}
		 }
			
		 setVisible(false);
		 
		 new Score(score);
		}
		else if (ae.getSource()==exitButton) {
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
	    			 if(userAnswers[i][0].equals(answers[i][1])) {
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
	    		 count++;
	    		 start(count);
	    	 	}
	     	}
	}
	
	
	public void start(int count) {
		qno.setText("" + (count + 1) + ". ");
		quest.setText(" "+ question[count][0]);
		
		opt1.setText(question[count][1]);
		opt1.setActionCommand(question[count][1]);
		
		opt2.setText(question[count][2]);
		opt2.setActionCommand(question[count][2]);

		opt3.setText(question[count][3]);
		opt3.setActionCommand(question[count][3]);

		opt4.setText(question[count][4]);
		opt4.setActionCommand(question[count][4]);
		
		groupOptions.clearSelection();

	}
	
	
	
	public static void main(String[] args) {
		new Button1();
	}
	
}
