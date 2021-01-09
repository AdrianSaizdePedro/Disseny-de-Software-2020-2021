package ub.edu.view;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import ub.edu.controller.IController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.io.File;
import java.net.MalformedURLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class FormReproductorVideo extends JDialog {
    private  JPanel panelReproduccio;

    private int duracioVisualitzacio;
    private int duracioVisualitzada;
    private int segonsRestants;

    private final String serie;
    private final int numTemporada;
    private final int episodi;
    private JFXPanel fxPanel;
    private Scene scene = null;
    private MediaPlayer mediaPlayer;
    private MediaControl mediaControl;

    private final String currentClient;
    private final String currentUser;
    private final IController controller;
    private final Frame owner;

    private static final int MAX_TIME_REPRODUCCION = 125;
    private static final String MEDIA_URL = "assets/sample-mp4-file.mp4";

    private void initComponents() {
        // This method is invoked on the EDT thread
        this.setLocation(20, 20);
        panelReproduccio.setSize(530, 375);
        fxPanel = new JFXPanel();

        if (scene == null || !scene.getWindow().isShowing()) {
            Platform.runLater(() -> initFX(fxPanel));
            panelReproduccio.add(fxPanel);
        }

    }

    /**
     * Mètode que es crida quan es tanca la finestra de reproducció
     * @param evt event del mètode
     */
    private void formWindowClosing(WindowEvent evt) {
        if (mediaPlayer!=null) {

            int tempsVisualitzacio = (int) ((mediaPlayer.getCurrentTime().toMillis()) / 1000.0);
            int segundosRestantes = duracioVisualitzacio - tempsVisualitzacio;

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            LocalDate localDate = LocalDate.now();

            String info = controller.visualitzarEpisodi(1, currentClient, currentUser, serie, numTemporada, episodi, dtf.format(localDate), segundosRestantes);


            JOptionPane.showMessageDialog(panelReproduccio, info);
            scene = null;
            mediaPlayer.stop();
            mediaControl.setVisible(false);

        }

    }


    private  void initFX(JFXPanel fxPanel) {
        // This method is invoked on the JavaFX thread
            try {
                scene= createScene();
                fxPanel.setScene(scene);

            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

    }

    private  Scene createScene() throws MalformedURLException {
        Group root = new Group();
        scene = new Scene(root, 729, 405);

        File mediaFile = new File(MEDIA_URL);
        Media media = new Media(mediaFile.toURI().toURL().toString());
        if (mediaPlayer==null)  {
            mediaPlayer = new MediaPlayer(media);

            //Limitem la visualització de l'episodi al rang donat pel fitxer MP4
            if(duracioVisualitzacio >  MAX_TIME_REPRODUCCION){
                if (MAX_TIME_REPRODUCCION - (duracioVisualitzacio - duracioVisualitzada) > 0)
                    duracioVisualitzada = MAX_TIME_REPRODUCCION - (duracioVisualitzacio - duracioVisualitzada);
                duracioVisualitzacio = MAX_TIME_REPRODUCCION;
            }

            mediaPlayer.setStartTime(new Duration(duracioVisualitzada*1000));
            mediaPlayer.setStopTime(new Duration(duracioVisualitzacio*1000));
            mediaPlayer.setAutoPlay(true);
            mediaControl = new MediaControl(mediaPlayer);

            scene.setRoot(mediaControl);
        }
        mediaPlayer.play();
        mediaControl.setVisible(true);
        return scene;
    }



    public FormReproductorVideo (Frame owner, IController controller, String idSerie, int numTemporada, int episodi, int duracioEpisodi, String currentClient, String currentUser) {
        this.owner = owner;
        this.controller = controller;
        this.currentClient = currentClient;
        this.currentUser = currentUser;
        this.duracioVisualitzacio = duracioEpisodi;
        this.duracioVisualitzada = controller.getDuracioVisualitzada(currentClient, currentUser, idSerie, numTemporada, episodi, duracioEpisodi);
        this.serie = idSerie;
        this.numTemporada = numTemporada;
        this.episodi = episodi;
        setContentPane(panelReproduccio);
        setModal(true);
        setResizable(true);

        setTitle("Play");

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(WindowEvent evt) {
                formWindowOpened(evt);
            }
            public void windowClosing(WindowEvent evt) { formWindowClosing(evt);
            }
        });
        setDefaultCloseOperation(HIDE_ON_CLOSE);
    }

    private void formWindowOpened(WindowEvent evt) {
        if(duracioVisualitzada == duracioVisualitzacio) duracioVisualitzada = 0;
        if (scene == null || !scene.getWindow().isShowing()) {
                SwingUtilities.invokeLater(this::initComponents);
            }
    }
}
