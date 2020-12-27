package ub.edu.view;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
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
    private final int duracioVisualitzacio;
    private final int duracioVisualitzada;
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

    private static final String MEDIA_URL = "assets/sample-mp4-file.mp4";

    private void initAndShowGUI() {
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
     * @param serie identificador de la sèrie de l'episodi a reproduir
     * @param numTemporada número de temporada de l'episodi a reproduir
     * @param idEpisodi títol de l'episodi a reproduir
     */
    private void formWindowClosing(WindowEvent evt, String serie, int numTemporada, int idEpisodi) {
        if (mediaPlayer!=null) {
            int tempsVisualitzacio = (int) ((mediaPlayer.getCurrentTime().toMillis() * duracioVisualitzacio) / 100.0);
            int segundosRestantes = tempsVisualitzacio - duracioVisualitzada;


            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            LocalDate localDate = LocalDate.now();

            controller.visualitzarEpisodi(1, currentClient, currentUser, serie, numTemporada, idEpisodi, dtf.format(localDate), segundosRestantes);

            String estat = "Visualització tancada del reproductor";
            JOptionPane.showMessageDialog(panelReproduccio, estat);
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
            // Sempre comença a reproduir des de 0
            mediaPlayer.setAutoPlay(true);

            mediaControl = new MediaControl(mediaPlayer);

            scene.setRoot(mediaControl);
        }
        mediaPlayer.play();
        mediaControl.setVisible(true);
        return scene;
    }



    public FormReproductorVideo (Frame owner, IController controller, String idSerie, int numTemporada, int episodi, int duracioEpisodi, int duracioVisualitzada, String currentClient, String currentUser) {
        this.owner = owner;
        this.controller = controller;
        this.currentClient = currentClient;
        this.currentUser = currentUser;
        this.duracioVisualitzacio = duracioEpisodi;
        this.duracioVisualitzada = duracioVisualitzada;
        this.serie = idSerie;
        this.numTemporada = numTemporada;
        this.episodi = episodi;
        setContentPane(panelReproduccio);
        setModal(true);
        setResizable(true);

        setTitle("Play");

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(WindowEvent evt) {
                formWindowOpened(evt, idSerie, numTemporada, episodi);
            }
            public void windowClosing(WindowEvent evt) { formWindowClosing(evt, idSerie, numTemporada, episodi);
            }
        });
        setDefaultCloseOperation(HIDE_ON_CLOSE);
    }

    private void formWindowOpened(WindowEvent evt, String serie, int numTemporada, int episodi) {
            if (scene == null || !scene.getWindow().isShowing()) {
                SwingUtilities.invokeLater(this::initAndShowGUI);
            }
    }
}
