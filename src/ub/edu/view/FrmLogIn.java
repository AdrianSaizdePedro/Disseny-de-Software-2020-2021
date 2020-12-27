package ub.edu.view;



import ub.edu.controller.IController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Formulari d'inici de sessió de la APP, es demana l'usuari i la contrassenya. Aquesta classe hereta de JDialog
 */
class FrmLogIn extends JDialog {
    private JPanel contentPane;
    private JButton btnLogIn;
    private JButton buttonCancel;
    private JPasswordField textPassword;
    private JTextField textUsername;
    private JLabel labelUsername;
    private JLabel labelPassword;
    private JButton btnRegistrar;

    private final IController controller;
    private final Frame owner;

    /**
     * Constructor de la finestra del LogIn on es fixa l'aspecte d'aquesta i s'inicialitzen els components
     */
    protected FrmLogIn(Frame owner, IController controller) {
        this.owner = owner;
        this.controller = controller;
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(btnLogIn);
        initComponents();
        setResizable(false);
        this.setLocation(50, 50);
        setTitle("LOG IN");
    }

    /**
     * Mètode que inicialitza tots els components de la GUI del LogIn i s'afegeixen els listeners dels events per quan es fa la acció sobre els botons.
     */
    private void initComponents() {
        btnLogIn.addActionListener(e -> onOK());

        buttonCancel.addActionListener(e -> onCancel());

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(e -> onCancel(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        btnRegistrar.addActionListener(e -> onRegistrar());
    }

    /**
     * Acció que es realitza quan es prem sobre el botó 'Log In' per accedir al contingut en cas que l'usuari estigui registrat i la contrassenya sigui correcta.
     * En cas que salti una excepció es mostra per pantalla el missatge d'error corresponent.
     */
    private void onOK() {
        try {
            boolean valid = controller.validateClient(textUsername.getText(), String.valueOf(textPassword.getPassword()));
            if (valid) {
                JOptionPane.showMessageDialog(this, "Log-in correcte", "INFO LOG-IN", JOptionPane.INFORMATION_MESSAGE);
                ((UBFLIXParty)owner).setCurrentClient(textUsername.getText());
                ((UBFLIXParty)owner).refreshUsersList();
                //owner.updateSeriesLists();
                this.dispose();
            } else{
                JOptionPane.showMessageDialog(this, "Invalid username or password", "INFO LOG-IN", JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "FINESTRA ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Acció que es realitza quan es prem sobre el botó 'Cancel' per tancar la finestra i alhora sortir de l'APP amb missatge de confirmació.
     */
    private void onCancel() {
        // add your code here if necessary
        if (JOptionPane.showConfirmDialog(this, "VOLS SORTIR? ", "SORTIR APP", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == 0)
            System.exit(0);
    }

    /**
     * Acció que es realitza quan es prem sobre el botó 'Registrar' per obrir la finestra de registre d'usuari.
     */
    private void onRegistrar() {
        FrmRegistre dialog = new FrmRegistre(owner, controller);
        dialog.pack();
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }
}
