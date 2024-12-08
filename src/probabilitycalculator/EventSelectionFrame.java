package probabilitycalculator;

import javax.swing.*;
import java.awt.*;
import java.util.HashSet;
import java.util.Set;

public class EventSelectionFrame extends JFrame {
    //AFTER CHOOSING AN EVENT FROM MAIN FRAME THIS OPENS TO CALCULATE PROBABILITIES OF CARDS
    private JComboBox<String> eventADropdown;
    private JComboBox<String> eventADetailsDropdown;
    private JComboBox<String> eventBDropdown;
    private JComboBox<String> eventBDetailsDropdown;
    private JComboBox<String> probabilityTypeDropdown;
    private JCheckBox replacementCheckbox;

  
    public EventSelectionFrame(String probabilityType) {
        setTitle("Probability Calculator");
        setSize(600, 500);
        setLayout(new BorderLayout());
        
        setLocationRelativeTo(null);
        int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        int windowWidth = getWidth();
        int xPosition = (screenWidth - windowWidth) / 2 + 500; 
        setLocation(xPosition, 0); 

       
        getContentPane().setBackground(new Color(240, 248, 255));

        // Panel for probability type selection 
        JPanel probabilityPanel = new JPanel(new GridLayout(3, 1));
        probabilityPanel.setBackground(new Color(240, 248, 255)); 
        probabilityPanel.setOpaque(true);
        probabilityPanel.add(new JLabel("Select Probability Type:"));

        String[] probabilityOptions = {
                "At least one occurs",
                "Both A and B occur",
                "Exactly one occurs",
                "Neither A nor B occurs",
                "A does not occur",
                "B does not occur",
                "A given B occurs",
        };
        probabilityTypeDropdown = new JComboBox<>(probabilityOptions);
        probabilityTypeDropdown.setSelectedItem(probabilityType);
        probabilityTypeDropdown.setBackground(Color.WHITE);  // Set background color to white
        probabilityPanel.add(probabilityTypeDropdown);

        //CHECKBOX W/ OR W/O REPLACEMENT
        replacementCheckbox = new JCheckBox("With Replacement");
        probabilityPanel.add(replacementCheckbox);

        add(probabilityPanel, BorderLayout.NORTH);

       
        JPanel eventPanel = new JPanel(new GridLayout(4, 2));
        eventPanel.setBackground(new Color(240, 248, 255)); 
        eventPanel.setOpaque(true);

        //EVENT A
        eventPanel.add(new JLabel("Select Event A:"));
        String[] eventOptions = {
                "All Hearts", "All Spades", "All Diamonds", "All Clubs",
                "All Queens", "All Kings", "All Aces", "Specific Suit"
        };
        eventADropdown = new JComboBox<>(eventOptions);
        eventADropdown.setBackground(Color.WHITE);  // Set background color to white
        eventPanel.add(eventADropdown);

        //EVENT A 1-10
        eventPanel.add(new JLabel("Select A Details:"));
        eventADetailsDropdown = new JComboBox<>();
        updateDetailsDropdown(eventADetailsDropdown, "All");
        eventADetailsDropdown.setBackground(Color.WHITE);  // Set background color to white
        eventPanel.add(eventADetailsDropdown);

        // EVENT B
        eventPanel.add(new JLabel("Select Event B:"));
        eventBDropdown = new JComboBox<>(eventOptions);
        eventBDropdown.setBackground(Color.WHITE);  // Set background color to white
        eventPanel.add(eventBDropdown);

        // Event B  1-10
        eventPanel.add(new JLabel("Select B Details:"));
        eventBDetailsDropdown = new JComboBox<>();
        updateDetailsDropdown(eventBDetailsDropdown, "All");
        eventBDetailsDropdown.setBackground(Color.WHITE);  // Set background color to white
        eventPanel.add(eventBDetailsDropdown);

        add(eventPanel, BorderLayout.CENTER);

        //EVENT LISTENERS FOR DROPDOWN MENU
        eventADropdown.addActionListener(e -> updateDetailsDropdown(eventADetailsDropdown, (String) eventADropdown.getSelectedItem()));
        eventBDropdown.addActionListener(e -> updateDetailsDropdown(eventBDetailsDropdown, (String) eventBDropdown.getSelectedItem()));

        //CALCULATE
        JButton calculateButton = new JButton("Calculate Probability");
        calculateButton.addActionListener(e -> {
            calculateProbability();
          
        });
        add(calculateButton, BorderLayout.SOUTH);

        
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    // FUNCTION TO UPDATE DETAILS IF NONE IS CHOSEN AND NOT ALL
    private void updateDetailsDropdown(JComboBox<String> detailsDropdown, String selection) {
        detailsDropdown.removeAllItems();

        if (selection.contains("All")) {
            detailsDropdown.addItem("None");
        } else if (selection.equals("Specific Suit")) {
            String[] ranks = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};
            for (String rank : ranks) {
                detailsDropdown.addItem(rank);
            }
        } else {
            detailsDropdown.addItem("None");
        }
    }

    private void calculateProbability() {
        //GET SELECTED EVENTS
        String selectedA = (String) eventADropdown.getSelectedItem();
        String selectedADetails = (String) eventADetailsDropdown.getSelectedItem();
        String selectedB = (String) eventBDropdown.getSelectedItem();
        String selectedBDetails = (String) eventBDetailsDropdown.getSelectedItem();
        String probabilityType = (String) probabilityTypeDropdown.getSelectedItem();

        // IF WITH REPLACEMENT IS CLICKED
        boolean withReplacement = replacementCheckbox.isSelected();

        //MAP SELECTED EVENTS WITH CARD MAPPER
        Set<String> eventA = mapEventToCards(selectedA, selectedADetails);
        Set<String> eventB = mapEventToCards(selectedB, selectedBDetails);

        //USE PROBABILITY CALCULATOR CLASS WITH EVENTS FOR ITS PARAMETERS
        double result = 0.0;
        switch (probabilityType) {
            case "At least one occurs":
                result = ProbabilityCalculator.atLeastOneOccurs(eventA, eventB, withReplacement);
                break;
            case "Both A and B occur":
                result = ProbabilityCalculator.bothOccur(eventA, eventB, withReplacement);
                break;
            case "Exactly one occurs":
                result = ProbabilityCalculator.exactlyOneOccurs(eventA, eventB, withReplacement);
                break;
            case "Neither A nor B occurs":
                result = ProbabilityCalculator.neitherOccurs(eventA, eventB, withReplacement);
                break;
            case "A does not occur":
                result = ProbabilityCalculator.notOccur(eventA, withReplacement);
                break;
            case "B does not occur":
                result = ProbabilityCalculator.notOccur(eventB, withReplacement);
                break;
            case "A given B occurs":
                result = ProbabilityCalculator.aGivenB(eventA, eventB, withReplacement);
                break;
            default:
                result = 0.0;
                break;
        }
        System.out.println("Selected probability type: " + probabilityType);

        // RESULT WITH PERCENTAGE 
        JOptionPane.showMessageDialog(
                this,
                "Probability of '" + probabilityType + "': " + String.format("%.2f%%", result * 100),
                "Result",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    //HASHET
    private Set<String> mapEventToCards(String event, String details) {
        Set<String> result = new HashSet<>();  

        if (event.equals("Specific Suit") && details != null && !details.equals("None")) {
            result.add(details + " of Hearts");
            result.add(details + " of Spades");
            result.add(details + " of Diamonds");
            result.add(details + " of Clubs");
        } else {
            switch (event) {
                case "All Hearts":
                    result.addAll(CardMapper.getCardsBySuit("Hearts"));
                    break;
                case "All Spades":
                    result.addAll(CardMapper.getCardsBySuit("Spades"));
                    break;
                case "All Diamonds":
                    result.addAll(CardMapper.getCardsBySuit("Diamonds"));
                    break;
                case "All Clubs":
                    result.addAll(CardMapper.getCardsBySuit("Clubs"));
                    break;
                case "All Queens":
                    result.addAll(CardMapper.getCardsByRank("Queen"));
                    break;
                case "All Kings":
                    result.addAll(CardMapper.getCardsByRank("King"));
                    break;
                case "All Aces":
                    result.addAll(CardMapper.getCardsByRank("Ace"));
                    break;
                default:
                    //RETURN EMPTY
                    break;
            }
        }

        return result;  //RETURN THE MUTABLE SET
    }

    // RUN EVENT SELECTION
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            EventSelectionFrame frame = new EventSelectionFrame("At least one occurs");
            frame.setVisible(true);
        });
    }
}
