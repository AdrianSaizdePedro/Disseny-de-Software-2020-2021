package ub.edu.view;

import ub.edu.controller.IController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Formulari amb el detall d'un post. Aquesta classe hereta de JDialog
 */
public class FerPost extends JDialog {
    // Atributos
    private JTextArea AreaMissatge;
    private JButton sendButton;
    private JButton cancelButton;
    private JPanel jpanel;

    /**
     * Constructor de la classe FormEpisodi que crida initComponents()
     * @param owner Owner
     * @param controller ControladorGUI
     */
    protected FerPost(Frame owner, IController controller) {
        setContentPane(jpanel);
        setModal(true);
        getRootPane().setDefaultButton(cancelButton);
        initComponents();
        setResizable(true);
        setTitle("FER POST");
        this.setLocationRelativeTo(null);
    }

    /**
     *  Mètodo que inicializa todos los componentes de la GUI de FormPost
     */
    private void initComponents() {
        setSize(250,300);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        sendButton.addActionListener(e -> onPost());
        cancelButton.addActionListener(e -> onCancel());

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        jpanel.registerKeyboardAction(e -> onCancel(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        AreaMissatge.requestFocusInWindow();
    }

    /**
     * Método que se llama cuando cerramos una ventana usando la "x"
     */
    private void onCancel() { dispose(); }

    /**
     * Metodo para mostrar un post
     */
    private void onPost() {
        String estat = AreaMissatge.getText();
        JOptionPane.showMessageDialog(jpanel, estat);
        dispose();
    }
}
