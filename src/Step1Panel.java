import model.UserProfile;

import javax.swing.*;
import java.awt.*;

public class Step1Panel extends JPanel {

    private JTextField userNameField;
    private JTextField schoolField;
    private JTextField sessionField;

    private MainFrame frame;

    public Step1Panel(MainFrame frame) {
        this.frame = frame;

        setLayout(new GridLayout(4, 2, 10, 10));

        add(new JLabel("Username:"));
        userNameField = new JTextField();
        add(userNameField);

        add(new JLabel("School:"));
        schoolField = new JTextField();
        add(schoolField);

        add(new JLabel("Session Name:"));
        sessionField = new JTextField();
        add(sessionField);

        add(new JLabel(""));

        JButton nextButton = new JButton("Next");
        nextButton.addActionListener(e -> goNext());
        add(nextButton);
    }

    private void goNext() {
        String userName = userNameField.getText().trim();
        String school = schoolField.getText().trim();
        String sessionName = sessionField.getText().trim();

        if (!validateInput()) {
            return;
        }

        UserProfile profile = new UserProfile(userName, school, sessionName);
        frame.setUserProfile(profile);

        frame.next("step2");
    }

    public boolean validateInput() {
        if (userNameField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter your username to continue.");
            return false;
        }

        if (schoolField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter your school to continue.");
            return false;
        }

        if (sessionField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter your session name to continue.");
            return false;
        }

        return true;
    }
}