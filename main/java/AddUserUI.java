import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class AddUserUI {
    public JFrame view;

    public JButton btnAdd = new JButton("Add");
    public JButton btnCancel = new JButton("Cancel");

    public JTextField txtUsername = new JTextField(10);
    public JTextField txtPassword = new JTextField(10);
    public JTextField txtFullname = new JTextField(10);
    public JTextField txtUserType = new JTextField(10);
    public JTextField txtCustomerID = new JTextField(10);

    public JLabel labUserName = new JLabel("User Name: ");

    CustomerModel customer;
    UserModel user;

    public AddUserUI() {
        this.view = new JFrame();

        view.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        view.setTitle("Add User");
        view.setSize(600, 400);
        view.getContentPane().setLayout(new BoxLayout(view.getContentPane(), BoxLayout.PAGE_AXIS));

        JPanel line = new JPanel(new FlowLayout());
        line.add(new JLabel("User Name "));
        line.add(txtUsername);
        view.getContentPane().add(line);

        line = new JPanel(new FlowLayout());
        line.add(new JLabel("Password "));
        line.add(txtPassword);
        view.getContentPane().add(line);

        line = new JPanel(new FlowLayout());
        line.add(new JLabel("Full name "));
        line.add(txtFullname);
        view.getContentPane().add(line);

        line = new JPanel(new FlowLayout());
        line.add(new JLabel("User Type "));
        line.add(txtUserType);
        view.getContentPane().add(line);

        line = new JPanel(new FlowLayout());
        line.add(new JLabel("CustomerID "));
        line.add(txtCustomerID);
        view.getContentPane().add(line);

        line = new JPanel(new FlowLayout());
        line.add(btnAdd);
        line.add(btnCancel);
        view.getContentPane().add(line);

        txtCustomerID.addFocusListener(new CustomerIDFocusListener());

        btnAdd.addActionListener(new AddButtonListerner());
    }

    public void run() {
        user = new UserModel();
        view.setVisible(true);
    }

    private class CustomerIDFocusListener implements FocusListener {
        @Override
        public void focusGained(FocusEvent focusEvent) {

        }

        @Override
        public void focusLost(FocusEvent focusEvent) {
            process();
        }

        private void process() {
            String s = txtCustomerID.getText();

            if (s.length() == 0) {
                labUserName.setText("CustomerID: [not specified!]");
                return;
            }

            System.out.println("User Name = " + s);

            try {
                user.mCustomerID = Integer.parseInt(s);

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null,
                        "Error: Invalid CustomerID", "Error Message",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            user = StoreManager.getInstance().getDataAdapter().loadUser(user.mUsername);

            if (user == null) {
                JOptionPane.showMessageDialog(null,
                        "Error: No custmoer with id = " + user.mUsername + " in store!", "Error Message",
                        JOptionPane.ERROR_MESSAGE);
                labUserName.setText("User Name: ");

                return;
            }

            labUserName.setText("User Name: " + user.mUsername);
        }

    }

    class AddButtonListerner implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {

            String id = txtUsername.getText();

            if (id.length() == 0) {
                JOptionPane.showMessageDialog(null, "User Name cannot be null!");
                return;
            }

            try {
                user.mCustomerID = Integer.parseInt(id);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "CustomerID is invalid!");
                return;
            }

            int res = StoreManager.getInstance().getDataAdapter().saveUser(user);
            if (res == SQLiteDataAdapter.USER_SAVE_FAILED)
                JOptionPane.showMessageDialog(null, "User NOT added successfully! Duplicate product ID!");
            else
                JOptionPane.showMessageDialog(null, "User added successfully!" + user);
        }
    }

}

