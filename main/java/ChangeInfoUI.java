import com.google.gson.Gson;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChangeInfoUI {

    public JFrame view;

    public JButton btnLoad = new JButton("Load Information");
    public JButton btnSave = new JButton("Save Information");

    public JTextField txtPassword = new JTextField(20);
    public JTextField txtFullname = new JTextField(20);


    public ChangeInfoUI() {
        this.view = new JFrame();

        view.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        view.setTitle("Change information Information");
        view.setSize(600, 400);
        view.getContentPane().setLayout(new BoxLayout(view.getContentPane(), BoxLayout.PAGE_AXIS));

        JPanel panelButtons = new JPanel(new FlowLayout());
        panelButtons.add(btnLoad);
        panelButtons.add(btnSave);
        view.getContentPane().add(panelButtons);

        JPanel line1 = new JPanel(new FlowLayout());
        line1.add(new JLabel("Password "));
        line1.add(txtPassword);
        view.getContentPane().add(line1);

        JPanel line2 = new JPanel(new FlowLayout());
        line2.add(new JLabel("Full Name "));
        line2.add(txtFullname);
        view.getContentPane().add(line2);

        btnLoad.addActionListener(new LoadButtonListerner());

        btnSave.addActionListener(new SaveButtonListener());

    }

    public void run() {
        view.setVisible(true);
    }

    class LoadButtonListerner implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            UserModel user = new UserModel();
            String pw = txtPassword.getText();

            if (pw.length() == 0) {
                JOptionPane.showMessageDialog(null, "Password cannot be null!");
                return;
            }

            try {
                user.mPassword = pw;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Password is invalid!");
                return;
            }

            //user = StoreManager.getInstance().getDataAdapter().loadUser(customer.mCustomerID);

        }
    }

    class SaveButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            UserModel user = new UserModel();

            String password = txtPassword.getText();
            if (password.length() == 0) {
                JOptionPane.showMessageDialog(null,  "Password cannot be empty!");
                return;
            }

            user.mPassword = password;

            String fullname = txtFullname.getText();
            if (fullname.length() == 0) {
                JOptionPane.showMessageDialog(null,  "Full name cannot be empty!");
                return;
            }

            user.mFullname = fullname;

            int  res = StoreManager.getInstance().getDataAdapter().saveUser(user);

            if (res == IDataAdapter.USER_SAVE_FAILED)
                JOptionPane.showMessageDialog(null, "Information is NOT saved successfully!");
            else
                JOptionPane.showMessageDialog(null, "Information is SAVED successfully!");
        }
    }
}