package ub.edu.view;


import ub.edu.controller.IController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Formulari de valorar un episodi, es pot valorar segons emoció, marcar el dispositiu i/o escollir el personatge favorit. Aquesta classe hereta de JDialog
 */
class FrmValoracio extends JDialog {
    private JPanel contentPane;
    private JButton btnValorar;
    private JButton buttonCancel;
    private JRadioButton corRatioButton;
    private JRadioButton emocioRadioButton;
    private JPanel panelEmocio;
    private JSlider barraEmocio;
    private JPanel panelCor;
    private JButton btnCor;

    //Afegit manualment
    private final IController controller;
    private final Frame owner;

    /**
     * Constructor de la classe FrmValoracio
     * @param idSerie identificador de la sèrie de l'episodi
     * @param numTemporada número de temporada de l'episodi
     * @param episodi títol de l'episodi seleccionat
     */
    protected FrmValoracio(Frame owner, IController controller, String idSerie, int numTemporada, int idEpisodi, String episodi) {
        this.owner = owner;
        this.controller = controller;
        initComponents(idSerie, numTemporada, idEpisodi, episodi);
        setResizable(false);
        setTitle("Valoració de l'episodi");
    }

    /**
     * Mètode que inicialitza tots els components de la GUI de FrmValoracio i s'afegeixen els listeners dels events per quan es fa l'acció sobre els diferents components de Java.
     * @param idSerie identificador de la sèrie de l'episodi
     * @param numTemporada número de temporada de l'episodi
     * @param episodi títol de l'episodi seleccionat
     */
    private void initComponents(String idSerie, int numTemporada, int idEpisodi, String episodi) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(btnValorar);
        groupButton();
        inici();

        btnValorar.addActionListener(e -> valorarEstrella(idSerie, numTemporada, idEpisodi, episodi));

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
        corRatioButton.addActionListener(e -> {
            panelCor.setVisible(true);
            panelEmocio.setVisible(false);
        });
        emocioRadioButton.addActionListener(e -> {
            panelCor.setVisible(false);
            panelEmocio.setVisible(true);
        });

        btnCor.addActionListener(e -> valorarCor(idSerie, numTemporada, idEpisodi, episodi));
    }

    /**
     * Condicions inicials a l'entrar al formulari de valoració, per defecte es mostra valorar per Emoció
     */
    private void inici(){
        emocioRadioButton.setSelected(true);
        panelCor.setVisible(false);
        panelEmocio.setVisible(true);
    }

    /**
     * Mètode que crea un grup de radioButtons per fer que només un es pugui seleccionar alhora
     */
    private void groupButton() {

        ButtonGroup buttonGroup = new ButtonGroup();

        buttonGroup.add(corRatioButton);
        buttonGroup.add(emocioRadioButton);

    }

    /**
     * Mètode que es crida quan es prem el botó Cancel que tanca la finestra
     */
    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    /**
     * Mètode que serveix per guardar la Valoració per Emoció un cop s'ha valorat
     * @param idSerie identificador de la sèrie de l'episodi
     * @param numTemporada número de temporada de l'episodi
     * @param episodi títol de l'episodi seleccionat
     */
    private void valorarEstrella(String idSerie, int numTemporada, int idEpisodi, String episodi) {

        int estrelles = barraEmocio.getValue();

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate localDate = LocalDate.now();

        String info = controller.valorarEpisodiEstrellas(1, ((UBFLIXParty) owner).getCurrentClient(),
                ((UBFLIXParty) owner).getCurrentUser(), idSerie, numTemporada, idEpisodi, estrelles, dtf.format(localDate));

        JOptionPane.showMessageDialog(contentPane, info);
        dispose();
    }

    /**
     * Mètode que serveix per guardar la Valoració per Emoció un cop s'ha valorat
     * @param idSerie identificador de la sèrie de l'episodi
     * @param numTemporada número de temporada de l'episodi
     * @param episodi títol de l'episodi seleccionat
     */
    private void valorarCor(String idSerie, int numTemporada, int idEpisodi, String episodi) {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate localDate = LocalDate.now();

        String info = controller.valorarEpisodiCor(1, ((UBFLIXParty) owner).getCurrentClient(),
                ((UBFLIXParty) owner).getCurrentUser(), idSerie, numTemporada, idEpisodi, dtf.format(localDate));

        JOptionPane.showMessageDialog(contentPane, info);
        dispose();
    }

    /**
     * Mètode que serveix per preguntar al client si està segur de voler acabar la valoració o vol continuar valorant.
     * @param estat resultat de la valoració feta
     */
    private void confirmacioContinuarValoracio(String estat) {
        JOptionPane.showMessageDialog(contentPane, estat);
        if (JOptionPane.showConfirmDialog(contentPane, "Vols acabar la valoració?") == 0)
            dispose();
    }
}
