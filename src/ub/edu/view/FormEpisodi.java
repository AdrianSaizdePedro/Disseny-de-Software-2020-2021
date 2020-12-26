package ub.edu.view;

import ub.edu.controller.IController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    private String currentClient;
    private String currentUsuari;
    private IController controller;
    private Frame owner;

    /**
     * Constructor de la classe FormEpisodi que crida initComponents()
     * @param idSerie identificador de la sèrie de l'episodi
     * @param numTemporada número de temporada de l'episodi
     * @param episodi títol de l'episodi seleccionat
     * @param duracio duració de l'episodi seleccionat
     * @param descripcio descripció de l'episodi seleccionat
     */
    protected FormEpisodi(Frame owner, IController controller, String idSerie, int numTemporada, int idEpisodi, String episodi, int duracio, int duracioVisualitzada,
                          String descripcio, String currentClient, String currentUsuari) {
        this.owner = owner;
        this.controller = controller;
        this.currentClient = currentClient;
        this.currentUsuari = currentUsuari;

        initComponents(idSerie, numTemporada, idEpisodi, episodi, duracio, duracioVisualitzada, descripcio);
        setResizable(false);
        setTitle("Detall de l'episodi");
    }

    /**
     * Mètode que inicialitza tots els components de la GUI de FormEpisodi i s'afegeixen els listeners dels events per quan es fa l'acció sobre els diferents components de Java.
     * @param idSerie identificador de la sèrie de l'episodi
     * @param numTemporada número de temporada de l'episodi
     * @param titol títol de l'episodi seleccionat
     * @param duracio duració de l'episodi seleccionat
     * @param descripcio descripció de l'episodi seleccionat
     */
    private void initComponents(String idSerie, int numTemporada, int idEpisodi, String titol, int duracio, int duracioVisualitzada, String descripcio) {

        add(jpanel);
        setModal(true);
        setSize(500, 400);
        setMinimumSize(new Dimension(500, 400));
        setMaximumSize(new Dimension(600, 400));

        titolEpisodi.setText("<html><u> Títol de l'episodi:</u> " + " "+ "<html></u> " + titol);
        duracioEpisodi.setText("<html><u> Duració:</u> " + duracio + " segons");
        descripcioEpisodi.setText("<html><body style=' width: 300 px'>"+"<html><u> Descripció:</u> " + descripcio);
        valorarButton.setEnabled(estaVisualitzat(idSerie, numTemporada, titol));
        tornarAlMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        visualitzarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onVisualitzar(idSerie, numTemporada, titol, duracio, duracioVisualitzada);
            }
        });

        valorarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    FrmValoracio dialog = new FrmValoracio(owner, controller, idSerie, numTemporada, idEpisodi, titol);
                    dialog.pack();
                    dialog.setVisible(true);
            }
        });
    }

    /**
     * Mètode que serveix per instanciar la finestra de visualització d'un episodi
     * @param idSerie identificador de la sèrie de l'episodi
     * @param numTemporada número de temporada de l'episodi
     * @param titol títol de l'episodi seleccionat
     * @param duracio duració de l'episodi seleccionat
     */
    @SuppressWarnings("deprecation")
    private void onVisualitzar(String idSerie, int numTemporada, String titol, int duracio, int duracioVisualitzada) {

        if(duracio == duracioVisualitzada) duracioVisualitzada = 0;
        //FormReproduccio fr = new FormReproduccio(owner, controller, idSerie, numTemporada, titol, duracio, duracioVisualitzada);
        FormReproductorVideo fr = new FormReproductorVideo(owner, controller, idSerie, numTemporada, titol, duracio, duracioVisualitzada);
        fr.pack();
        fr.setVisible(true);

        valorarButton.setEnabled(estaVisualitzat(idSerie, numTemporada, titol));
    }

    /**
     * Mètode que serveix per saber si el client està subscrit a l'episodi.
     * @param idSerie identificador de la sèrie de l'episodi
     * @param numTemporada número de temporada de l'episodi
     * @param titol títol de l'episodi seleccionat
     * @return True si el client hi està subscrit. False en cas contrari.
     */
    private boolean estaVisualitzat(String idSerie, int numTemporada, String titol) {
        return true;
    }


}
