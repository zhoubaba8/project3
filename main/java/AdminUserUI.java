import com.google.gson.Gson;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminUserUI {

    public JFrame view;

    public JButton btnLoad = new JButton("Load User");
    public JButton btnSave = new JButton("Save User");

    public JTextField txtCustomerID = new JTextField(20);
    public JTextField txtUserType = new JTextField(20);


    public AdminUserUI() {
        this.view = new JFrame();

        view.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        view.setTitle("Update User Information");
        view.setSize(600, 400);
        view.getContentPane().setLayout(new BoxLayout(view.getContentPane(), BoxLayout.PAGE_AXIS));

        JPanel panelButtons = new JPanel(new FlowLayout());
        panelButtons.add(btnLoad);
        panelButtons.add(btnSave);
        view.getContentPane().add(panelButtons);

        JPanel line1 = new JPanel(new FlowLayout());
        line1.add(new JLabel("CustomerID "));
        line1.add(txtCustomerID);
        view.getContentPane().add(line1);

        JPanel line2 = new JPanel(new FlowLayout());
        line2.add(new JLabel("User Type "));
        line2.add(txtUserType);
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
            String id = txtCustomerID.getText();

            if (id.length() == 0) {
                JOptionPane.showMessageDialog(null, "CustomerID cannot be null!");
                return;
            }

            try {
                user.mCustomerID = Integer.parseInt(id);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "CustomerID is invalid!");
                return;
            }

            // call data access!

            //user = StoreManager.getInstance().getDataAdapter().loadUser(user.mUserType);

            if (user == null) {
                JOptionPane.showMessageDialog(null, "Product NOT exists!");
            } else {
                txtUserType.setText(Double.toString(user.mUserType));
            }
        }
    }

    class SaveButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            UserModel user = new UserModel();
            String id = txtCustomerID.getText();

            if (id.length() == 0) {
                JOptionPane.showMessageDialog(null, "CustomerID cannot be null!");
                return;
            }

            try {
                user.mCustomerID = Integer.parseInt(id);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "CustomerID is invalid!");
                return;
            }

            String type = txtUserType.getText();
            if (type.length() == 0) {
                JOptionPane.showMessageDialog(null, "User Type cannot be empty!");
                return;
            }

            int  res = StoreManager.getInstance().getDataAdapter().saveUser(user);

            if (res == IDataAdapter.USER_SAVE_FAILED)
                JOptionPane.showMessageDialog(null, "User is NOT saved successfully!");
            else
                JOptionPane.showMessageDialog(null, "User is SAVED successfully!");
        }
    }
}

