package probabilitycalculator;

import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.WindowConstants;

public class FirstWindow extends JFrame {
    
    public FirstWindow(){
        //WINDOW DESIGN INITIALIZE
        setTitle("Probability Calculator System");
        setBounds(100, 100, 1366, 565); //SIZE
        setLayout(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //TO CLOSE ON EXIT
        
        // BACKGROUND IMAGE
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/bg1.png"));
        JLabel image = new JLabel(i1);
        image.setBounds(0, 0, 1366, 565); 
        add(image);

        // START BUTTON
        JButton start = createButton("START", 550, 350);
        start.addActionListener(new ButtonActionListener("START"));
        image.add(start);

        //ABOUT BUTTON
        JButton about = createButton("ABOUT", 550, 400);
        about.addActionListener(new ButtonActionListener("ABOUT"));
        image.add(about);
        
        setVisible(true);
    }

    //BUTTON EFFECTS
    private JButton createButton(String text, int x, int y) {
        JButton button = new JButton(text);
        button.setBounds(x, y, 300, 40);
        button.setBackground(new Color(70, 130, 180)); // Steel blue
        button.setForeground(Color.WHITE);
        button.setFont(new Font("serif", Font.PLAIN, 24));
        button.setFocusPainted(false);
        button.setBorderPainted(false);

       
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(new Color(100, 149, 237)); // Lighter blue on hover
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(new Color(70, 130, 180)); // Original color
            }

            @Override
            public void mousePressed(MouseEvent e) {
                button.setBackground(new Color(30, 60, 90)); // Darker blue on click
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                button.setBackground(new Color(100, 149, 237)); // Reset to lighter blue
            }
        });

        return button;
    }
    
   //ACTION FOR BUTTONS
    private class ButtonActionListener implements ActionListener {
        private String buttonType;

        public ButtonActionListener(String buttonType) {
            this.buttonType = buttonType;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            switch (buttonType) {
                case "START":
                    // When "START" is clicked, open ChooseMenu
                    new ChooseMenu().setVisible(true);
                    dispose();  
                    break;
                case "ABOUT":
                    // When "ABOUT" is clicked, open About window
                    new About().setVisible(true);
                   
                    break;
            }
        }
    }
    
    public static void main(String[] args) {
        new FirstWindow();
    }
}
