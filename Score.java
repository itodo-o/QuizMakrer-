package quizapplication;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Score extends JFrame implements ActionListener {

    Score(int score) {
        setBounds(400, 150, 750, 550);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/score.jpg"));
        Image i2 = i1.getImage().getScaledInstance(300, 250, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 200, 500, 230);
        add(image);
        
        JLabel heading = new JLabel("Thank you for playing!");
        heading.setBounds(105, 73, 700, 90);
        heading.setFont(new Font("Mongolian Baiti", Font.BOLD, 45));
        heading.setForeground(new Color(113, 188, 104));
        add(heading);
        
        JLabel lblscore = new JLabel("Your score is: " + score);
        lblscore.setBounds(405, 230, 300, 30);
        lblscore.setFont(new Font("Mongolian Baiti", Font.BOLD, 25));
        lblscore.setForeground(new Color(113, 188, 104));
        add(lblscore);
        
        JButton submit = new JButton("Play Again");
        submit.setBounds(420, 290, 130, 40);
        submit.setFont(new Font("Mongolian Baiti", Font.BOLD, 16));
        submit.setBackground(new Color(255, 255, 255));
        submit.setForeground(new Color(113, 188, 104));
        submit.addActionListener(this);
        add(submit);
        
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae) {
        setVisible(false);
        new Login();
    }

    public static void main(String[] args) {
        new Score(0);
    }
}
