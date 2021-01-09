package ub.edu.view;

import ub.edu.controller.IController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


/**
 * Formulari amb el detall d'un episodi, permet visualitzar, subscriure's i valorar un episodi. Aquesta classe hereta de JDialog
 */
class FormEpisodi extends JDialog{
    private JButton valorarButton;
    private JButton visualitzarButton;
    private JLabel titolEpisodi;
    private JLabel descripcioEpisodi;
    private JButton tornarAlMenuButton;
    private JPanel jpanel;
    private JLabel duracioEpisodi;
    private final String currentClient;
    private final String currentUsuari;

    private final Frame owner;
    private final IController controller;

    private final String idSerie;
    private final int numTemporada;
    private final int idEpisodi;
    private final String titol;
    private final int duracio;
    private final String descripcio;

    /**
     * Constructor de la classe FormEpisodi que crida initComponents()
     * @param idSerie identificador de la sèrie de l'episodi
     * @param numTemporada número de temporada de l'episodi
     * @param titol títol de l'episodi seleccionat
     * @param duracio duració de l'episodi seleccionat
     * @param descripcio descripció de l'episodi seleccionat
     */
    protected FormEpisodi(Frame owner, IController controller, String idSerie, int numTemporada, int idEpisodi, String titol, int duracio, String descripcio, String currentClient, String currentUsuari) {
        this.owner = owner;
        this.controller = controller;
        this.idSerie = idSerie;
        this.numTemporada = numTemporada;
        this.idEpisodi = idEpisodi;
        this.titol = titol;
        this.duracio = duracio;
        this.descripcio = descripcio;
        this.currentClient = currentClient;
        this.currentUsuari = currentUsuari;

        initComponents();
        setResizable(false);
        setTitle("Detall de l'episodi");
    }

    /**
     * Mètode que inicialitza tots els components de la GUI de FormEpisodi i s'afegeixen els listeners dels events per quan es fa l'acció sobre els diferents components de Java.
     */
    private void initComponents() {
        add(jpanel);
        setModal(true);
        setSize(500, 400);
        setMinimumSize(new Dimension(500, 400));
        setMaximumSize(new Dimension(600, 400));

        titolEpisodi.setText("<html><u> Títol de l'episodi:</u> " + " "+ "<html></u> " + titol);
        duracioEpisodi.setText("<html><u> Duració:</u> " + duracio + " segons");
        descripcioEpisodi.setText("<html><body style=' width: 300 px'>"+"<html><u> Descripció:</u> " + descripcio);
        valorarButton.setEnabled(estaVisualitzat(idSerie, numTemporada, idEpisodi));
        tornarAlMenuButton.addActionListener(e -> onCancel());
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        visualitzarButton.addActionListener(e -> onVisualitzar(idSerie, numTemporada, idEpisodi, duracio));

        valorarButton.addActionListener(e -> {
                FrmValoracio dialog = new FrmValoracio(owner, controller, idSerie, numTemporada, idEpisodi);
                dialog.pack();
                dialog.setVisible(true);
        });
    }

    /**
     * Mètode que serveix per instanciar la finestra de visualització d'un episodi
     * @param idSerie identificador de la sèrie de l'episodi
     * @param numTemporada número de temporada de l'episodi
     * @param idEpisodi títol de l'episodi seleccionat
     * @param duracio duració de l'episodi seleccionat
     */
    private void onVisualitzar(String idSerie, int numTemporada, int idEpisodi, int duracio) {
        FormReproduccio fr = new FormReproduccio(owner, controller, idSerie, numTemporada, idEpisodi, duracio, currentClient, currentUsuari);
        //FormReproductorVideo fr = new FormReproductorVideo(owner, controller, idSerie, numTemporada, idEpisodi, duracio, currentClient, currentUsuari);
        fr.pack();
        fr.setVisible(true);

        valorarButton.setEnabled(estaVisualitzat(idSerie, numTemporada, idEpisodi));
    }

    /**
     * Mètode que serveix per saber si el client està subscrit a l'episodi.
     * @param idSerie identificador de la sèrie de l'episodi
     * @param numTemporada número de temporada de l'episodi
     * @param idEpisodi títol de l'episodi seleccionat
     * @return True si el client hi està subscrit. False en cas contrari.
     */
    private boolean estaVisualitzat(String idSerie, int numTemporada, int idEpisodi) {
        return controller.isEpisodiVisualitzat(idSerie, numTemporada, idEpisodi, this.currentClient, this.currentUsuari);
    }

    /**
     * Método que se llama cuando cerramos una ventana usando la "x"
     */
    private void onCancel() { dispose(); }

}
