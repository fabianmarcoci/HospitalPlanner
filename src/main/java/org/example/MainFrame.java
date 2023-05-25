package org.example;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

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
        loginLabel.setFont(new Font("Arial", Font.BOLD, 22));

        // Set the position of the loginLabel
        GridBagConstraints gbc = new GridBagConstraints();
        itemPosition(gbc, 0, 0, 2, -160, 0, 0, 0, GridBagConstraints.CENTER);
        // Add the loginLabel at the top middle of loginPanel
        loginPanel.add(loginLabel, gbc);

        // Create a button for "Submit"
        JButton submitBtn = new RoundedButton("Submit", 10, Color.WHITE);
        submitBtn.setBackground(Color.WHITE);
        submitBtn.setFont(new Font("Arial", Font.BOLD, 12));

        // Set the position of the submitBtn
        itemPosition(gbc, 0, 1, 2, 0,0, -180, 0, GridBagConstraints.CENTER);
        // Add the submitBtn at the bottom middle of loginPanel
        loginPanel.add(submitBtn, gbc);

        // Create a label and text field for the username
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setForeground(Color.BLACK);
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 14));

        JTextField usernameTextField = new JTextField(20);
        ((AbstractDocument) usernameTextField.getDocument()).setDocumentFilter(new CharacterLimitText(15));

        // Create a label and text field for the password
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setForeground(Color.BLACK);
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 14));

        JTextField passwordTextField = new JTextField(20);
        ((AbstractDocument) passwordTextField.getDocument()).setDocumentFilter(new CharacterLimitText(15));

        // Set placeholder text for the text fields
        usernameTextField.setText("Username");
        passwordTextField.setText("Password");
        changeFocus(usernameTextField);
        changeFocus(passwordTextField);

        // Set the position of the usernameLabel and usernameTextField
        GridBagConstraints gbcUsernameLabel = new GridBagConstraints();
        itemPosition(gbcUsernameLabel, 0, 0, 2, -70,-160,0, 0, GridBagConstraints.LINE_START);
        loginPanel.add(usernameLabel, gbcUsernameLabel);

        GridBagConstraints gbcUsernameTextField = new GridBagConstraints();
        itemPosition(gbcUsernameTextField, 0, 1, 2, -25,-160,0, 0, GridBagConstraints.LINE_START);
        loginPanel.add(usernameTextField, gbcUsernameTextField);

        // Set the position of the passwordLabel and passwordTextField
        GridBagConstraints gbcPasswordLabel = new GridBagConstraints();
        itemPosition(gbcPasswordLabel, 0, 0, 2, 0,-160,-25, 0, GridBagConstraints.LINE_START);
        loginPanel.add(passwordLabel, gbcPasswordLabel);


        GridBagConstraints gbcPasswordTextField = new GridBagConstraints();
        itemPosition(gbcPasswordTextField, 0, 1, 2, 0,-160,-70, 0, GridBagConstraints.LINE_START);
        loginPanel.add(passwordTextField, gbcPasswordTextField);



        // Add the loginPanel to the center of the backgroundLabel.
        backgroundLabel.add(loginPanel);

        add(backgroundLabel, BorderLayout.CENTER);

        // Set the JFrame to start maximized
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        // Define the size of the window when it's minimized
        setPreferredSize(new Dimension(800, 600));

        pack();
    }

    private void itemPosition(GridBagConstraints gbc, int gridx, int gridy, int w, int top, int left, int bottom, int right, int anchor) {
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        gbc.gridwidth = w;
        gbc.insets = new Insets(top, left, bottom, right);
        gbc.anchor = anchor;
    }
    private void changeFocus(JTextField placeHolder) {
        String text = placeHolder.getText();

        placeHolder.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (placeHolder.getText().equals(placeHolder.getText())) {
                    placeHolder.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (placeHolder.getText().isEmpty()) {
                    placeHolder.setText(text);
                }
            }
        });
    }
}
