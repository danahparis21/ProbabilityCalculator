package probabilitycalculator;

import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class About extends JFrame {
    public About() {
       
        setTitle("About Our Project");
        
        setSize(1366, 565);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(240, 248, 255)); 
        setLocationRelativeTo(null);

       //ABOUT TITLE
        JLabel headerLabel = new JLabel("ABOUT", JLabel.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 36)); // Large bold font for the header
        headerLabel.setForeground(Color.WHITE); // White text for the header
        headerLabel.setOpaque(true);
        headerLabel.setBackground(new Color(0, 102, 204)); // Blue background for the header
        headerLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0)); // Add some padding
        add(headerLabel, BorderLayout.NORTH);

        // TEXT FOR PROJECT DESCRIPTION
        JTextArea aboutTextArea = new JTextArea();
        aboutTextArea.setFont(new Font("Arial", Font.PLAIN, 16)); // Larger font for readability
        aboutTextArea.setEditable(false);
        aboutTextArea.setLineWrap(true);
        aboutTextArea.setWrapStyleWord(true);
        aboutTextArea.setForeground(Color.BLACK); // Black text
        aboutTextArea.setBackground(new Color(240, 248, 255)); // Pale blue background
        aboutTextArea.setBorder(new EmptyBorder(20, 20, 20, 20)); // Add padding around the text

        // TEXT FILE IMPORT
        try (InputStream inputStream = getClass().getResourceAsStream("/icons/about_project.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
       StringBuilder content = new StringBuilder();
       String line;
       while ((line = reader.readLine()) != null) {
           content.append(line).append("\n");
       }
       aboutTextArea.setText(content.toString());
   } catch (IOException | NullPointerException e) {
       aboutTextArea.setText("Error loading content. Please check the file path or ensure the file exists.");
       e.printStackTrace();
   }


        //JScrollPane for TExt
        JScrollPane scrollPane = new JScrollPane(aboutTextArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1)); // Add a border to the scroll pane

        
        add(scrollPane, BorderLayout.CENTER);

        
        setVisible(true);
    }

    public static void main(String[] args) {
        new About();
    }
}
