package ub.edu.view;

import ub.edu.controller.IController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Finestra amb la barra de reproducció d'un episodi. Aquesta classe hereta de JDialog
 */
class FormReproduccio extends JDialog {
    private JPanel jPanel;
    private JProgressBar progressBar;
    private JButton tancaButton;
    private Timer timer;
    private ActionListener actionListener;
    private int duracioVisualitzacio;
    private int duracioVisualitzada;
    private String serie;
    private int numTemporada;
    private int episodi;

    private String currentClient;
    private String currentUser;
    private IController controller;
    private Frame owner;

    /**
     * Constructor de la classe
     * @param idSerie identificador de la sèrie de l'episodi a reproduir
     * @param numTemporada número de temporada de l'episodi a reproduir
     * @param episodi títol de l'episodi a reproduir
     * @param duracioEpisodi duració de l'episodi a reproduir
     */
    protected FormReproduccio(Frame owner, IController controller, String idSerie, int numTemporada, int episodi, int duracioEpisodi, String currentClient, String currentUser) {
        this.owner = owner;
        this.controller = controller;
        this.currentClient = currentClient;
        this.currentUser = currentUser;
        this.duracioVisualitzacio = duracioEpisodi;
        this.duracioVisualitzada = controller.getDuracioVisualitzada(currentClient, currentUser, idSerie, numTemporada, episodi);
        this.serie = idSerie;
        this.numTemporada = numTemporada;
        this.episodi = episodi;
        initComponents();
        setResizable(false);
        setTitle("Reproducció");
        setDefaultCloseOperation(HIDE_ON_CLOSE);
    }

    /**
     * Mètode que inicialitza tots els components de la GUI de FormReproduccio i s'afegeixen els listeners dels events per quan es fa l'acció sobre els diferents components de Java.
     */
    private void initComponents() {
        add(jPanel);
        setModal(true);
        setMinimumSize(new Dimension(300,300));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(WindowEvent evt) {
                formWindowOpened(evt);
            }
            public void windowClosing(WindowEvent evt) { formWindowClosing(evt, serie, numTemporada, episodi);
            }
        });
        progressBar.setMinimum(0);
        progressBar.setMaximum(100);
        progressBar.setStringPainted(true);

        tancaButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onPause(serie, numTemporada, episodi);
            }
        });

    }

    private void onPause(String serie, int numTemporada, int episodi) {
        timer.stop();
        int tempsVisualitzacio = (progressBar.getValue()*duracioVisualitzacio)/100;
        String estat = "Visualització parada";
        JOptionPane.showMessageDialog(jPanel, estat);
    }

    /**
     * Mètode que serveix per començar a reproduir l'episodi una vegada s'ha obert la finestra de reproducció
     * @param evt event que detecta quan s'obra la finestra
     */
    private void formWindowOpened(WindowEvent evt) {
        if(duracioVisualitzada == duracioVisualitzacio) duracioVisualitzada = 0;
        progressBar.setValue((duracioVisualitzada*100)/duracioVisualitzacio);
        int delay = (duracioVisualitzacio*1000)/100;
        actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (progressBar.getValue() < 100)
                    progressBar.setValue(progressBar.getValue() + 1);
                else {
                    formWindowClosing(evt, serie, numTemporada, episodi);
                }
            }
        };
        if (timer==null) this.timer = new Timer(delay, actionListener);
        timer.start();
    }


    /**
     * Mètode que es crida quan es tanca la finestra de reproducció
     * @param evt event del mètode
     * @param serie identificador de la sèrie de l'episodi a reproduir
     * @param numTemporada número de temporada de l'episodi a reproduir
     * @param idEpisodi títol de l'episodi a reproduir
     */
    private void formWindowClosing(WindowEvent evt, String serie, int numTemporada, int idEpisodi) {
        timer.stop();
        int tempsVisualitzacio = ((progressBar.getValue()*duracioVisualitzacio)/100);
        int segundosRestantes = duracioVisualitzacio - tempsVisualitzacio;


        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate localDate = LocalDate.now();

        controller.visualitzarEpisodi(1, currentClient, currentUser, serie, numTemporada, idEpisodi, dtf.format(localDate), segundosRestantes);

        String estat = "Episodi visualitzat";
        JOptionPane.showMessageDialog(jPanel, estat);
        dispose();
    }


}
