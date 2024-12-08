package probabilitycalculator;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ChooseMenu extends JFrame {

    public ChooseMenu() {
        //Choose Menu for probabilities calculator
        setTitle("Main Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400); 
        setLocationRelativeTo(null);
        setLayout(null);

        
        getContentPane().setBackground(Color.WHITE);

        // TITLE HEADER
        JLabel titleLabel = new JLabel("Choose a Probability Calculation:");
        titleLabel.setBounds(90, 20, 400, 30);
        titleLabel.setFont(new Font("serif", Font.BOLD, 22)); 
        add(titleLabel);

        //BUTTONS
        JButton diceButton = new JButton("Dice");
        diceButton.setBounds(150, 80, 200, 50);
        addIconToButton(diceButton, "/icons/dice.png"); 
        styleButton(diceButton);
        add(diceButton);

        JButton cardButton = new JButton("Card");
        cardButton.setBounds(150, 140, 200, 50); 
        addIconToButton(cardButton, "/icons/cards.png"); 
        styleButton(cardButton);
        add(cardButton);

        JButton coinButton = new JButton("Coin");
        coinButton.setBounds(150, 200, 200, 50); 
        addIconToButton(coinButton, "/icons/coin.png");
        styleButton(coinButton);
        add(coinButton);

        JButton backButton = new JButton("BACK");
        backButton.setBounds(150, 260, 200, 50); 
        backButton.setBackground(Color.decode("#e6a0a0")); 
        backButton.setForeground(Color.WHITE); 
        backButton.setFont(new Font("Arial", Font.BOLD, 16));
        backButton.setFocusPainted(false);
        backButton.setBorderPainted(false);
        addIconToButton(backButton, "/icons/close.png"); 
        add(backButton);

        //ACTION LISTENERS
        diceButton.addActionListener(e -> new DiceProbability().setVisible(true));
        cardButton.addActionListener(e -> new MainFrame().setVisible(true));
        coinButton.addActionListener(e -> new CoinProbability().setVisible(true));
        backButton.addActionListener(e -> new FirstWindow().setVisible(true));
    }

    // ADD ICONS TO BUTTON
    private void addIconToButton(JButton button, String iconName) {
        ImageIcon icon = new ImageIcon(getClass().getResource(iconName));
        icon = new ImageIcon(icon.getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH));
        button.setIcon(icon);
    }

    //BUTTON DESIGN
    private void styleButton(JButton button) {
        button.setBackground(new Color(70, 130, 180)); 
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 16)); 
        button.setFocusPainted(false);
        button.setBorderPainted(false);

        
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(new Color(100, 149, 237)); 
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(new Color(70, 130, 180)); 
            }

            @Override
            public void mousePressed(MouseEvent e) {
                button.setBackground(new Color(30, 60, 90));
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                button.setBackground(new Color(100, 149, 237)); 
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ChooseMenu app = new ChooseMenu();
            app.setVisible(true);
        });
    }
}
