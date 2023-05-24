package org.example;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private final int width = screenSize.width;
    private final int height = screenSize.height;

    public MainFrame() {
        super("HospitalPlanner");
        init();
    }

    private void init() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        ImageIcon originalImageIcon = new ImageIcon(getClass().getResource("/img/background.jpg"));
        Image originalImage = originalImageIcon.getImage();
        Image resizedImage = originalImage.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
        ImageIcon backgroundImage = new ImageIcon(resizedImage);

        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setLayout(new GridBagLayout());

        JPanel loginPanel = new RoundedPanel(backgroundLabel.getLayout(), 20, Color.DARK_GRAY);
        loginPanel.setPreferredSize(new Dimension(460, 280));

        // Create a label for "Login"
        JLabel loginLabel = new JLabel("Login");
        loginLabel.setForeground(Color.WHITE);
        loginLabel.setFont(new Font("Arial", Font.BOLD, 20));

        // Set the position of the loginLabel
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 200, 0);
        gbc.anchor = GridBagConstraints.PAGE_START;

        // At the loginLabel at the top middle of loginPanel
        loginPanel.add(loginLabel, gbc);

        // Add the loginPanel to the center of the backgroundLabel.
        backgroundLabel.add(loginPanel);

        add(backgroundLabel, BorderLayout.CENTER);

        // Set the JFrame to start maximized
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        // Define the size of the window when it's minimized
        setPreferredSize(new Dimension(800, 600));

        pack();
    }
}
