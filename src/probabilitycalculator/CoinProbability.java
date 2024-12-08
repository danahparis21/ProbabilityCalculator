package probabilitycalculator;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class CoinProbability extends JFrame {
    public CoinProbability() {
        //CALCULATE PROBABILITY HEAD OR TAILS, BASED ON HOW MANY TIMES IT'S FLIPPED
        setTitle("Coin Probability");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 400);
        setLayout(new BorderLayout());

       
        ImageIcon icon = new ImageIcon(getClass().getResource("/icons/CoinHeader.png"));
        JLabel header = new JLabel(icon);
        header.setHorizontalAlignment(JLabel.CENTER);
        header.setPreferredSize(new java.awt.Dimension(400, 70)); // Set header height
        
        
        add(header, BorderLayout.NORTH);

       
        getContentPane().setBackground(new Color(240, 248, 255)); // Light blue

       
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(240, 248, 255)); 
        add(panel, BorderLayout.CENTER); 

        // UI Components 
        JLabel flipsLabel = new JLabel("Enter number of flips:");
        flipsLabel.setBounds(50, 30, 200, 30);
        panel.add(flipsLabel);

        JTextField flipsField = new JTextField();
        flipsField.setBounds(250, 30, 100, 30);
        panel.add(flipsField);

        JLabel headsLabel = new JLabel("Enter desired number of head:");
        headsLabel.setBounds(50, 70, 200, 30);
        panel.add(headsLabel);

        JTextField headsField = new JTextField();
        headsField.setBounds(250, 70, 100, 30);
        panel.add(headsField);

        JLabel tailsLabel = new JLabel("Enter desired number of tails:");
        tailsLabel.setBounds(50, 110, 200, 30);
        panel.add(tailsLabel);

        JTextField tailsField = new JTextField();
        tailsField.setBounds(250, 110, 100, 30);
        panel.add(tailsField);

        JButton calculateButton = new JButton("Calculate");
        calculateButton.setBounds(125, 160, 120, 30);
        calculateButton.setBackground(Color.decode("#A8D1D1")); 
        calculateButton.setForeground(Color.BLACK); 
        panel.add(calculateButton);

        JLabel resultLabel = new JLabel("Probability:");
        resultLabel.setBounds(50, 200, 300, 30);
        panel.add(resultLabel);

        JButton backButton = new JButton("Back");
        backButton.setBounds(125, 250, 120, 30);
        backButton.setBackground(Color.decode("#787774")); 
        backButton.setForeground(Color.WHITE); 
        panel.add(backButton);

        // Back Button Action
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); 
                new ChooseMenu().setVisible(true); 
            }
        });

        // Calculate Button Action
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                validateInputAndCalculate(flipsField, headsField, tailsField, resultLabel);
            }
        });
    }

    private void validateInputAndCalculate(JTextField flipsField, JTextField headsField, JTextField tailsField, JLabel resultLabel) {
        try {
            int flips = Integer.parseInt(flipsField.getText());
            int heads = Integer.parseInt(headsField.getText());
            int tails = Integer.parseInt(tailsField.getText());

            if (heads + tails != flips) {
                resultLabel.setText("Error has been occurred. Check your input and try again.");
                resultLabel.setFont(resultLabel.getFont().deriveFont(java.awt.Font.ITALIC)); // Italic for error
                resultLabel.setForeground(Color.RED); // Red for error
                return;
            }

           
            resultLabel.setFont(resultLabel.getFont().deriveFont(java.awt.Font.BOLD)); // Plain for normal
            resultLabel.setForeground(Color.BLACK); 

            double probability = calculateBinomialProbability(flips, heads, 0.5);

            // Convert to a whole number percentage
            int percentage = (int) Math.round(probability * 100);

            // Display the result as both decimal and percentage
            resultLabel.setText(String.format("Probability: %.5f (%d%%)", probability, percentage));
        } catch (NumberFormatException ex) {
            resultLabel.setText("Error has been occurred. Check your input and try again.");
            resultLabel.setFont(resultLabel.getFont().deriveFont(java.awt.Font.ITALIC)); // Italic for error
            resultLabel.setForeground(Color.RED); // Red for error
        }
    }

    private double calculateBinomialProbability(int n, int k, double p) {
        return combination(n, k) * Math.pow(p, k) * Math.pow(1 - p, n - k);
    }

    private long combination(int n, int k) {
        return factorial(n) / (factorial(k) * factorial(n - k));
    }

    private long factorial(int n) {
        if (n == 0) return 1;
        long fact = 1;
        for (int i = 1; i <= n; i++) fact *= i;
        return fact;
    }
}
