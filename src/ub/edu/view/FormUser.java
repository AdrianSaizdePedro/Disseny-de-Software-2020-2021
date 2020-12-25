package ub.edu.view;

import ub.edu.controller.ControladorGUI;
import ub.edu.controller.IController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FormUser extends JDialog{
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField textFieldUsername;
    private JLabel usernameLabel;

    private IController controller;
    private Frame owner;

    public FormUser(Frame owner, IController controller) {
        this.owner = owner;
        this.controller = controller;
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setResizable(false);
        setTitle("LOG IN");
        this.setLocationRelativeTo(null);
        initComponents();
    }

    private void initComponents() {
        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onRegister();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        textFieldUsername.requestFocusInWindow();
    }

    private void onRegister() {
        String info = controller.addUser(((UBFLIXParty)owner).getCurrentClient(), textFieldUsername.getText());
        JOptionPane.showMessageDialog(this, info, "INFO REGISTER USER", JOptionPane.INFORMATION_MESSAGE);
        if (info.equals("User created")) {
            ((UBFLIXParty)owner).refreshUsersList();
            dispose();
        }
    }

    private void onCancel() {
        dispose();
    }
}
