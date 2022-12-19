package quizapplication;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Login extends JFrame implements ActionListener {
	
	JButton button1, button2, button3;
	
    JTextField name;

	Login() {
		// creating a window
		getContentPane().setBackground(Color.white);
		setLayout(null);
		
		// inserting an image --> Quiz Logo
		ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/quizLogo.png"));
		JLabel image = new JLabel(i1);
		image.setBounds(0, 0, 1250, 500);
		add(image);
		
		// question
		JLabel question = new JLabel("Choose Game Option:");
		question.setBounds(430, 420, 350, 70);
		question.setFont(new Font("Mongolian Baiti", Font.BOLD, 31));
		question.setForeground(new Color(113, 188, 104));
		add(question);
		
		// button 1
		button1 = new JButton("Quiz Taker");
		button1.setBounds(530, 495, 150, 50);
		button1.setFont(new Font("Mongolian Baiti", Font.BOLD, 17));
		button1.setForeground(new Color(113, 188, 104));
		button1.addActionListener(this);
		add(button1);
		
		// button 2
		button2 = new JButton("Quiz Maker");
		button2.setBounds(530, 555, 150, 50);
		button2.setFont(new Font("Mongolian Baiti", Font.BOLD, 17));
		button2.setForeground(new Color(113, 188, 104));
		button2.addActionListener(this);
		add(button2);
		
		// button 3
		button3 = new JButton("Exit the Program");
		button3.setBounds(520, 615, 175, 50);
		button3.setFont(new Font("Mongolian Baiti", Font.BOLD, 17));
		button3.setForeground(new Color(113, 188, 104));
		button3.addActionListener(this);
		add(button3);
		
		 // will display a window
		setSize(1250, 800);
		setLocation(200, 200);
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == button1) {
			setVisible(false);
			new Button1();
		}
		else if(ae.getSource() == button2) {
			setVisible(false);
			new Button2();
		}
		else {
			setVisible(false);
		}
	}
	
	public static void main(String[] args) {
		new Login();
	}
}
