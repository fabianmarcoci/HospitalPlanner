package org.example.HospitalPlanner.ui;

import org.example.HospitalPlanner.service.SendMail;
import org.example.HospitalPlanner.service.network.Client;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.AbstractDocument;
import java.awt.*;
import java.awt.event.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class MainFrame extends JFrame {
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private final int width = screenSize.width;
    private final int height = screenSize.height;
    private JTextField passwordTextField;
    private JTextField usernameTextField;
    private final int maxCharacterLength = 15;

    public MainFrame() {
        super("HospitalPlanner");

        init();
        removeDefaultFocus();
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
        usernameLabel.setForeground(Color.WHITE);
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 14));

        usernameTextField = new JTextField(20);
        ((AbstractDocument) usernameTextField.getDocument()).setDocumentFilter(new CharacterLimitText(maxCharacterLength));

        // Create a label and text field for the password
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setForeground(Color.WHITE);
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 14));

        passwordTextField = new JPasswordField(20);
        ((AbstractDocument) passwordTextField.getDocument()).setDocumentFilter(new CharacterLimitText(maxCharacterLength));

        removeErrorSound(usernameTextField);
        removeErrorSound(passwordTextField);

        // Set placeholder text for the text fields
        usernameTextField.setText("Username");
        passwordTextField.setText("Password");
        changeFocus(usernameTextField, "Username");
        changeFocus(passwordTextField, "Password");


        // Set the position of the usernameLabel and usernameTextField
        GridBagConstraints gbcUsernameLabel = new GridBagConstraints();
        itemPosition(gbcUsernameLabel, 0, 0, 2, -70,0,0, 0, GridBagConstraints.CENTER);
        loginPanel.add(usernameLabel, gbcUsernameLabel);

        GridBagConstraints gbcUsernameTextField = new GridBagConstraints();
        itemPosition(gbcUsernameTextField, 0, 1, 2, -25,0,0, 0, GridBagConstraints.CENTER);
        loginPanel.add(usernameTextField, gbcUsernameTextField);

        // Set the position of the passwordLabel and passwordTextField
        GridBagConstraints gbcPasswordLabel = new GridBagConstraints();
        itemPosition(gbcPasswordLabel, 0, 0, 2, 0,0,-25, 0, GridBagConstraints.CENTER);
        loginPanel.add(passwordLabel, gbcPasswordLabel);

        GridBagConstraints gbcPasswordTextField = new GridBagConstraints();
        itemPosition(gbcPasswordTextField, 0, 1, 2, 0,0,-70, 0, GridBagConstraints.CENTER);
        loginPanel.add(passwordTextField, gbcPasswordTextField);

        // Create a label for "Forgot your password?"
        JLabel infoLabel = new JLabel("Forgot your password?");
        infoLabel.setForeground(Color.BLACK);
        infoLabel.setFont(new Font("Arial", Font.PLAIN, 15));

        addClickListenerToLabel(infoLabel, "ForgotPass");

        // Set the position of the infoLabel
        GridBagConstraints gbcInfo = new GridBagConstraints();
        itemPosition(gbcInfo, 0, 1, 2, 10, 0, 0, 0, GridBagConstraints.CENTER);


        // Add the infoLabel to the backgroundLabel.
        backgroundLabel.add(infoLabel, gbcInfo);


        JLabel secondLabel = new JLabel("Don't have an account yet? Create one.");
        secondLabel.setForeground(Color.BLACK);
        secondLabel.setFont(new Font("Arial", Font.PLAIN, 17));

        addClickListenerToLabel(secondLabel, "GoToRegister");

        // Set the position of the secondLabel
        GridBagConstraints gbcSecond = new GridBagConstraints();
        itemPosition(gbcSecond, 0, 2, 2, 0, 0, 0, 0, GridBagConstraints.CENTER);
        // Add the secondLabel to the backgroundLabel.
        backgroundLabel.add(secondLabel, gbcSecond);

        // Add the loginPanel to the center of the backgroundLabel.
        backgroundLabel.add(loginPanel);

        add(backgroundLabel, BorderLayout.CENTER);

        // Set the JFrame to start maximized
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        // Define the size of the window when it's minimized
        setPreferredSize(new Dimension(800, 600));

        submitBtn.addActionListener(this::submitLogin);

        pack();
    }

    private void itemPosition(GridBagConstraints gbc, int gridx, int gridy, int w, int top, int left, int bottom, int right, int anchor) {
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        gbc.gridwidth = w;
        gbc.insets = new Insets(top, left, bottom, right);
        gbc.anchor = anchor;
    }

    private void submitLogin(ActionEvent e) {
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();

        Client client = new Client("localhost", 1234);
        String role = client.sendLogin(username, password);
        client.closeConnection();

        if (role != null) {
            switch (role) {
                case "doctor":
                    // Open doctor form
                    break;
                case "patient":
                    // Open patient form
                    break;
                default:
                    System.out.println("Failed to identify the role.");
            }
        } else {
            System.out.println("Failed to login.");
        }
    }


    private void addClickListenerToLabel(JLabel label, String actionCommand) {
        // Make the cursor appear as a hand whenever it's over the JLabel
        label.setCursor(new Cursor(Cursor.HAND_CURSOR));

        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if ("ForgotPass".equals(actionCommand)) {
                    System.out.println("Forgot Password executed.");
                    SendMail mail = new SendMail("lacrafab@gmail.com");
                    //TODO
                } else if ("GoToRegister".equals(actionCommand)) {
                    System.out.println("Go to register executed.");
                    //TODO
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                label.setForeground(Color.BLUE);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                label.setForeground(Color.BLACK);
            }
        });

    }

    private void removeDefaultFocus() {
        this.addWindowFocusListener(new WindowAdapter() {
            public void windowGainedFocus(WindowEvent e) {
                MainFrame.this.requestFocusInWindow();
            }
        });
    }

    private void removeErrorSound(JTextField field) {
        field.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE && field.getText().isEmpty()) {
                    e.consume();
                }
                if (field.getText().length() >= maxCharacterLength && e.getKeyCode() != KeyEvent.VK_BACK_SPACE) {
                    e.consume();
                }
            }
        });
    }

    private void changeFocus(JTextField placeHolder, String placeholderText) {
        AtomicBoolean userHasEdited = new AtomicBoolean(false);

        placeHolder.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (!userHasEdited.get() && placeHolder.getText().equals(placeholderText)) {
                    placeHolder.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (placeHolder.getText().isEmpty()) {
                    placeHolder.setText(placeholderText);
                    userHasEdited.set(false);
                }
            }
        });

        placeHolder.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                userHasEdited.set(true);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                userHasEdited.set(true);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                userHasEdited.set(true);
            }
        });
    }

}
