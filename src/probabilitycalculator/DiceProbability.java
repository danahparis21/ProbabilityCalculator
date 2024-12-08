package probabilitycalculator;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class DiceProbability extends JFrame {

    public DiceProbability() {
        //CALCULATE THE PROBABILITY OF DICE DEPENING ON HOW MANY DICE IS CHOSEN
        setTitle("Dice Probability");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 450);
        setLayout(null);
        
        ImageIcon icon = new ImageIcon(getClass().getResource("/icons/diceHeader.png"));
        JLabel header = new JLabel(icon);
        header.setBounds(0, 0, 500, 70);
        add(header);

       
        getContentPane().setBackground(new Color(240, 248, 255)); // Light blue

        // Labels
        JLabel label = new JLabel("Number of Dice:");
        label.setBounds(50, 90, 200, 30); // Adjusted Y position to 90
        label.setFont(new Font("Arial", Font.BOLD, 14));
        label.setForeground(new Color(25, 25, 112)); // Midnight blue
        add(label);

        // Radio Buttons
        JRadioButton radioBtn1 = new JRadioButton("1 Dice");
        radioBtn1.setBounds(50, 130, 100, 30); // Adjusted Y position to 130
        styleRadioButton(radioBtn1);

        JRadioButton radioBtn2 = new JRadioButton("2 Dice");
        radioBtn2.setBounds(150, 130, 100, 30); // Adjusted Y position to 130
        styleRadioButton(radioBtn2);

        JRadioButton radioBtn3 = new JRadioButton("3 Dice");
        radioBtn3.setBounds(250, 130, 100, 30); // Adjusted Y position to 130
        styleRadioButton(radioBtn3);

        add(radioBtn1);
        add(radioBtn2);
        add(radioBtn3);

        // Combo Boxes for dice values
        JComboBox<Integer> comboBox1 = createComboBox(50, 170); // Adjusted Y position to 170
        JComboBox<Integer> comboBox2 = createComboBox(150, 170); // Adjusted Y position to 170
        JComboBox<Integer> comboBox3 = createComboBox(250, 170); // Adjusted Y position to 170

        add(comboBox1);
        add(comboBox2);
        add(comboBox3);

        // Initially disable combo boxes
        comboBox1.setEnabled(false);
        comboBox2.setEnabled(false);
        comboBox3.setEnabled(false);

        // Enable/disable combo boxes based on radio button selection
        radioBtn1.addActionListener(e -> comboBox1.setEnabled(radioBtn1.isSelected()));
        radioBtn2.addActionListener(e -> comboBox2.setEnabled(radioBtn2.isSelected()));
        radioBtn3.addActionListener(e -> comboBox3.setEnabled(radioBtn3.isSelected()));

        // Checkbox for "Exact Sequence"
        JCheckBox exactSequenceCheckBox = new JCheckBox("Exact Sequence");
        exactSequenceCheckBox.setBounds(50, 240, 150, 30); // Adjusted Y position to 240
        exactSequenceCheckBox.setBackground(new Color(240, 248, 255)); // Light blue
        exactSequenceCheckBox.setForeground(new Color(25, 25, 112)); // Midnight blue
        exactSequenceCheckBox.setFont(new Font("Arial", Font.PLAIN, 12));
        add(exactSequenceCheckBox);

        // Calculate Button
        JButton calculateButton = new JButton("Calculate");
        calculateButton.setBounds(150, 280, 150, 40); // Adjusted Y position to 280
        styleButton(calculateButton);
        add(calculateButton);

        // Result Label
        JLabel resultLabel = new JLabel("Probability:");
        resultLabel.setBounds(50, 330, 400, 30); // Adjusted Y position to 330
        resultLabel.setFont(new Font("Arial", Font.BOLD, 14));
        resultLabel.setForeground(new Color(25, 25, 112)); // Midnight blue
        add(resultLabel);

        // Action Listener for Calculation
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int diceCount = 0;
                if (radioBtn1.isSelected()) diceCount++;
                if (radioBtn2.isSelected()) diceCount++;
                if (radioBtn3.isSelected()) diceCount++;

                // Calculate total outcomes (6^n)
                int totalOutcomes = (int) Math.pow(6, diceCount);

                // Get the selected dice values
                int value1 = (radioBtn1.isSelected()) ? (int) comboBox1.getSelectedItem() : -1;
                int value2 = (radioBtn2.isSelected()) ? (int) comboBox2.getSelectedItem() : -1;
                int value3 = (radioBtn3.isSelected()) ? (int) comboBox3.getSelectedItem() : -1;

                // Calculate the probability based on exact sequence or not
                double probability;
                if (exactSequenceCheckBox.isSelected()) {
                    // specific outcomes / total outcomes
                    int uniqueArrangements = 1; 
                    probability = (double) uniqueArrangements / totalOutcomes;
                } else {
                    // In non-exact sequence, calculate unique arrangements (permutations)
                    int uniqueArrangements = calculateUniqueArrangements(value1, value2, value3);
                    probability = (double) uniqueArrangements / totalOutcomes;
                }

                // Display the results
                resultLabel.setText(String.format("Probability: %.5f (%.2f%%)", probability, probability * 100));
            }
        });

        // Back Button
        JButton backButton = new JButton("Back");
        backButton.setBounds(150, 370, 120, 30); // Adjusted Y position to 370
        backButton.setBackground(Color.decode("#787774")); // Set background color
        backButton.setForeground(Color.WHITE); // Set text color
        add(backButton);

        // Back Button Action
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the current frame
                new ChooseMenu().setVisible(true); // Open the ChooseMenu
            }
        });
    }

    // Helper Method: Create Combo Box
    private JComboBox<Integer> createComboBox(int x, int y) {
        JComboBox<Integer> comboBox = new JComboBox<>();
        for (int i = 1; i <= 6; i++) {
            comboBox.addItem(i);
        }
        comboBox.setBounds(x, y, 80, 30);
        comboBox.setBackground(new Color(224, 255, 255)); // Light cyan
        comboBox.setForeground(new Color(25, 25, 112)); // Midnight blue
        comboBox.setFont(new Font("Arial", Font.BOLD, 12));
        return comboBox;
    }

    // Helper Method: Style Radio Button
    private void styleRadioButton(JRadioButton radioButton) {
        radioButton.setBackground(new Color(240, 248, 255)); // Light blue
        radioButton.setForeground(new Color(25, 25, 112)); // Midnight blue
        radioButton.setFont(new Font("Arial", Font.PLAIN, 12));
    }

    // Helper Method: Style Button
    private void styleButton(JButton button) {
        button.setBackground(new Color(70, 130, 180)); // Steel blue
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setFocusPainted(false);
        button.setBorderPainted(false);
    }

    // Helper Method: Calculate Unique Arrangements for specific dice values
    private int calculateUniqueArrangements(int value1, int value2, int value3) {
        int[] values = {value1, value2, value3};
        
        // Filter out -1 values (not selected dice)
        values = Arrays.stream(values)
                       .filter(v -> v != -1)
                       .toArray();
        
        if (values.length == 0) return 0; // No dice selected, no arrangement

        // Case for exact sequence (only one arrangement)
        if (values.length == 1) {
            return 1; // Single dice always has 1 arrangement
        }

        // Count duplicates in values array
        Map<Integer, Long> frequencyMap = Arrays.stream(values)
                                                .boxed()
                                                .collect(Collectors.groupingBy(e -> e, Collectors.counting()));

        // If no duplicates, all values are unique
        if (frequencyMap.size() == values.length) {
            return factorial(values.length); // n! permutations for all unique values
        }

        // Handle repeated values
        return calculatePermutationsWithRepetition(frequencyMap);
    }

   
    private int calculatePermutationsWithRepetition(Map<Integer, Long> frequencyMap) {
        int total = frequencyMap.values().stream().mapToInt(v -> v.intValue()).sum();
        int result = factorial(total);
        
        // Divide by factorial of the frequency of each repeated number
        for (long count : frequencyMap.values()) {
            result /= factorial((int) count);
        }
        
        return result;
    }
    
    private int factorial(int n) {
        int result = 1;
        for (int i = 1; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new DiceProbability().setVisible(true);
        });
    }
}
