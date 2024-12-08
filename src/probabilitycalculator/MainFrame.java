package probabilitycalculator;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    public MainFrame() {
        //Main frame on choosing probability events for cards
        setTitle("Card Probability Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 550);
        setLayout(new BorderLayout());
        
        
        getContentPane().setBackground(Color.WHITE); 
        
        
        ImageIcon icon = new ImageIcon(getClass().getResource("/icons/cardHeader.png"));
        JLabel header = new JLabel(icon);
        add(header, BorderLayout.NORTH); 

       
        setLocationRelativeTo(null);
        int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        int windowWidth = getWidth();
        int xPosition = (screenWidth - windowWidth) / 2 + 500; 
        setLocation(xPosition, 0); 

  
       // Probability Options Panel
        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new GridLayout(9, 1, 10, 10)); 
        optionsPanel.setBackground(Color.WHITE); 

    
        JPanel buttonPanel = new JPanel(new BorderLayout());
        buttonPanel.add(Box.createVerticalStrut(20), BorderLayout.NORTH); 
        buttonPanel.add(optionsPanel, BorderLayout.CENTER);

        String[] probabilities = {
            "At least one occurs",
            "Both A and B occur",
            "Exactly one occurs",
            "Neither A nor B occurs",
            "A does not occur",
            "B does not occur",
            "A given B occurs",
            "Back To Menu"
        };

       
        //SETTING UP BUTTONS
        for (String probability : probabilities) {
            JButton button = new JButton(probability);
            button.setBackground(new Color(240, 248, 255)); 
            button.setOpaque(true);
            button.setBorderPainted(false); 
            button.setFont(new Font("Helvetica", Font.BOLD, 18)); 
            
         
            if (probability.equals("Back To Menu")) {
                button.setForeground(Color.RED); 
            }
            
            
            button.addActionListener(e -> handleButtonClick(probability));
            optionsPanel.add(button);
        }

        add(buttonPanel, BorderLayout.CENTER); // Add the button panel with padding

        setVisible(true);
    }

    // CLICK ACTIONS
    private void handleButtonClick(String probabilityType) {
        if (probabilityType.equals("Back To Menu")) {
            ChooseMenu();  
        } else {
            openEventSelection(probabilityType);
        }
    }

    //OPEN EVENT SELECTION CLASS
    private void openEventSelection(String probabilityType) {
        new EventSelectionFrame(probabilityType).setVisible(true);
        this.setVisible(false);
    }

    // GO BACK TO CHOOSE MENU
    private void ChooseMenu() {
        new ChooseMenu().setVisible(true); 
        this.setVisible(false);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainFrame::new);  
    }
}
