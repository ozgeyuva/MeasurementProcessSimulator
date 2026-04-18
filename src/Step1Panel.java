import javax.swing.*;
import java.awt.*;

import static java.awt.AWTEventMulticaster.add;

public class Step1Panel extends JPanel {
    private JTextField userNameField;
    private JTextField schoolField;
    private JTextField sessionField;

    private MainFrame frame;
    public Step1Panel(MainFrame frame) {
        MainFrame frame;
        setLayout(new GridLayout(4, 2));

        add(new JLabel("Username:"));
        userNameField = new JTextField();
        add(userNameField);

        add(new JLabel("School:"));
        schoolField = new JTextField();
        add(schoolField);

        add(new JLabel("Session Name:"));
        sessionField = new JTextField();
        add(sessionField);

        JButton nextButton = new JButton("Next");
        nextButton.addActionListener(e -> goNext());
        add(nextButton);
    }
    public void goNext() {
        String userName = userNameField.getText();
        String school = schoolField.getText();
        String session = sessionField.getText();
        if (userName.isEmpty() || school.isEmpty() || session.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all the fields");
            return;
        }
        UserProfile profile = new UserProfile(userName, school, session);
        frame.setUserProfile(profile);
        frame.next("step2");

    }


}
