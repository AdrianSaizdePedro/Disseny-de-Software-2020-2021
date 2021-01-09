package ub.edu.view;

/* Interfície Gràfica desenvolupada per: Nils Ballús, Joan Cano, David Rial i Miquel Guiot */

import ub.edu.controller.IController;
import ub.edu.model.Episodi;
import ub.edu.model.StrategyTOP.IterableStrategy;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.*;
import java.util.List;


/**
 * GUI bàsica de l'app UBFLIX on es mostraran les diferents llistes corresponent a cada client que hagi realitzat el Log In.
 * Aquesta classe hereta de JFrame i és la vista principal de l'aplicació.
 */
public class UBFLIXParty extends JFrame implements RegisterObserver{

    private JPanel jPanel;
    private JTabbedPane llistes;
    private JPanel fieldAll;
    private JPanel fieldWatchNext;
    private JPanel fieldWatched;
    private JPanel fieldNotStarted;
    private JList<String> listAll;
    private JList<String> listMyList;
    private JList<String> listWatched;
    private JList<String> listContinueWatching;
    private JTable tableTopVisualitzacions;
    private JTable tableTopValoracions;
    private JLabel labelTopVisualitzacions;
    private JLabel labelTopValoracions;
    private JButton btnTancarSessio;
    private JButton btnCrearUsuari;
    private JButton btnAfegirMyList;
    private JButton btnTreureMyList;
    private JComboBox<String> comboBoxUsuaris;
    private JButton perfilButton;

    private static final int NUM_MAX_TOPS = 10;

    //Afegits manualment
    private HashMap<String, JPopupMenu> popupMenuTemporades;
    private DefaultTableModel tableModelVis;
    private DefaultTableModel tableModelVal;
    private final IController controller;
    private String currentClient;
    private String currentUser;


    /**
     * Constructor de la classe UBFLIX que crida initComponents()
     */
    public UBFLIXParty(IController controller) {
        super("UBFLIXParty");
        this.controller = controller;
        this.setLocation(30, 30);
        setVisible(true);
    }

    /**
     * Mètode que inicialitza tots els components de la GUI de l'APP UBFLIXParty i s'afegeixen els listeners dels events
     * per quan es fa l'acció sobre els diferents components de Java.
     */
    public void init(){
        add(jPanel);
        setSize(800,700);
        setMinimumSize(new Dimension(800,700));
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        btnTancarSessio.addActionListener(e -> ferLogIn());
        btnAfegirMyList.addActionListener(e -> addSerieToMyList());
        btnTreureMyList.addActionListener(e -> removeSerieFromMyList());
        btnCrearUsuari.addActionListener(e -> userActionPerformed());
        perfilButton.addActionListener(e -> mostrarPerfil());
        llistes.addChangeListener(e -> {
            if(llistes.getSelectedIndex() == 0) {
                btnAfegirMyList.setEnabled(true);
                btnTreureMyList.setEnabled(false);
            } else if (llistes.getSelectedIndex() == 2) {
                btnAfegirMyList.setEnabled(false);
                btnTreureMyList.setEnabled(true);
            } else {
                btnAfegirMyList.setEnabled(false);
                btnTreureMyList.setEnabled(false);
            }
            refreshLlistes();
        });
        listAll.addListSelectionListener(e -> mostrarPopupMenuTemporades(listAll, fieldAll));
        listMyList.addListSelectionListener(e -> mostrarPopupMenuTemporades(listMyList, fieldWatchNext));
        listWatched.addListSelectionListener(e -> mostrarPopupMenuTemporades(listWatched, fieldWatched));
        listContinueWatching.addListSelectionListener(e -> mostrarPopupMenuTemporades(listContinueWatching, fieldNotStarted));

        comboBoxUsuaris.addActionListener(e -> {
            if (comboBoxUsuaris.getItemCount() > 0) {
                setCurrentUser((String)comboBoxUsuaris.getSelectedItem());
                refreshLlistes();
            }
        });
        popupMenuTemporades = new HashMap<>();

        inicialitzarLlistaTopVisualitzacions();
        inicialitzarLlistaTopValoracions();
        controller.registerObserver(this);
    }


    private void mostrarPerfil() {
        FormPerfilUsuari perfil = new FormPerfilUsuari(this, controller);
        perfil.pack();
        perfil.setVisible(true);
    }

    /**
     * Mètode que serveix per inicialitzar el Top10 de sèries més visualitzades
     */
    private void inicialitzarLlistaTopVisualitzacions() {
        tableModelVis = new DefaultTableModel(new Object[][]{}, new Object[]{"nomSerie","visualitzacions"}){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tableTopVisualitzacions.setModel(tableModelVis);
        tableTopVisualitzacions.setShowGrid(false);
        tableTopVisualitzacions.setFocusable(false);
        tableTopVisualitzacions.setRowSelectionAllowed(false);
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
        tableTopVisualitzacions.getColumnModel().getColumn(1).setCellRenderer(rightRenderer);
    }

    private void userActionPerformed() {
        FormUser dialog = new FormUser(this, controller);
        dialog.pack();
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    /**
     * Mètode que serveix per inicialitzar el top10 de sèries més ben valorades
     */
    private void inicialitzarLlistaTopValoracions() {
        tableModelVal = new DefaultTableModel(new Object[][]{}, new Object[]{"nomSerie","valoracio"}){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tableTopValoracions.setModel(tableModelVal);
        tableTopValoracions.setFocusable(false);
        tableTopValoracions.setRowSelectionAllowed(false);
        tableTopValoracions.setShowGrid(false);
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
        tableTopValoracions.getColumnModel().getColumn(1).setCellRenderer(rightRenderer);
    }

    /**
     * Mètode que serveix per mostrar les temporades disponibles d'una sèrie concreta
     * @param llista llista que conté la sèrie seleccionada
     * @param panel panel de la vista que conté les llistes
     */
    private void mostrarPopupMenuTemporades(JList<String> llista, JPanel panel) {
        int index = llista.getSelectedIndex();
        Object element = llista.getSelectedValue();
        llista.removeSelectionInterval(llista.getLastVisibleIndex()+1, llista.getLastVisibleIndex()+1);
        if (popupMenuTemporades.get(llista.getSelectedValue()) != null)
            mostrarTemporades(element, index, panel);
    }

    /**
     * Mètode que mostra les diferents temporades de cada sèrie
     * @param serie sèrie de la qual s'han de mostrar les temporades
     * @param index índex de la sèrie seleccionada
     */
    private void mostrarTemporades(Object serie, int index, JPanel panel) {
        String s = (String) serie;
        JPopupMenu pm = popupMenuTemporades.get(s);
        pm.show(panel, panel.getWidth()/2, index*18);
    }

    /**
     * Mètode que hace visible el formulario de Log In
     */
    private void ferLogIn() {
        jPanel.setVisible(false);
        FrmLogIn dialog = new FrmLogIn(this, controller);
        dialog.pack();
        dialog.setVisible(true);
        jPanel.setVisible(true);
        refreshLlistes();
    }

    /**
     * Mètodo que se llama cuandso se abre la ventana y abre el Log In
     * @param evt evento que sucede al abrir la APP
     */
    private void formWindowOpened(java.awt.event.WindowEvent evt) {
        refreshListAll();
        ferLogIn();
    }

    /**
     * Mètode que  se llama cuandso se cierra la ventana , haciendo click
     * sobre la 'x' con el mensaje de confirmación de salida.
     * @param evt evento que sucede al cerra la ventana
     */
    private void formWindowClosing(java.awt.event.WindowEvent evt) {
        if (JOptionPane.showConfirmDialog(this, "VOLS SORTIR? ", "SORTIR APP", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == 0)
            System.exit(0);
    }

    /**
     * Mètodo que actualiza las Series del catálogo
     */
    private void refreshListAll() {
        List<String> series = controller.llistarCatalegSeries();
        listAll.setListData(new Vector<>(series));
        refreshTemporades(series);
    }

    /**
     * Mètodo para actualizar las Temporadas de las Series
     * @param series Series da actualizar
     */
    private void refreshTemporades(List<String> series) {
        popupMenuTemporades.clear();
        for (String serie: series) {
            JPopupMenu s = new JPopupMenu();
            List<String> temporades = controller.getTemporades(serie);
            for (String temporada: temporades) {
                JMenu temp = new JMenu(temporada);
                refreshEpisodis(serie, temporada, temp);
                s.add(temp);
            }
            popupMenuTemporades.put(serie, s);
        }
    }

    /**
     * Mètodo para actualizar los Episodios de una Temporada de una Serie
     * @param serie Serie de la cual se actualizaran los Episodios
     * @param temporada Temporada de la cual se actualizaran los Episodios
     * @param jm JMenu del Episodio
     */
    private void refreshEpisodis(String serie, String temporada, JMenu jm) {
        List<Episodi> episodis = controller.getEpisodis(serie, Integer.parseInt(temporada.split(" ")[1]));

        for (Episodi episodi: episodis) {
            JMenuItem ep = new JMenuItem(episodi.getTitol());
            ep.addActionListener(e -> {
                String idSerie = episodi.getIdSerie();

                int numTemporada = Integer.parseInt(temporada.split(" ")[1]);
                int duracio = episodi.getDuracio();
                String descripcio = episodi.getDescripcio();
                onEpisodi(idSerie, numTemporada, episodi.getNumEpisodi(), episodi.getTitol(), duracio, descripcio);
            });
            jm.add(ep);
        }
    }

    /**
     * Mètodo para actualitzar casi todas las listas de View
     */
    private void refreshLlistes() {
        refreshWatched();
        refreshMyList();
        refreshContinueWatching();
    }

    /**
     * Método que añade una serie a MyList
     */
    private void addSerieToMyList() {
        String info = controller.addSerieToMyList(1, currentClient, currentUser, listAll.getSelectedValue());
        JOptionPane.showMessageDialog(jPanel, info);
        refreshMyList();
    }

    /**
     * Método que elimina una serie de MyList
     */
    private void removeSerieFromMyList() {
        String info = controller.removeSerieFromMyList(1, currentClient, currentUser, listMyList.getSelectedValue());
        JOptionPane.showMessageDialog(jPanel, info);
        refreshMyList();
    }

    /**
     * Metodo que actualiza las Series de MyList
     */
    private void refreshMyList() {
        List<String> series = (List<String>) controller.listMyList(currentClient, currentUser);
        listMyList.setListData(new Vector<>(series));
    }

    /**
     * Metodo que actualiza las Series de la lista Watched
     */
    private void refreshWatched() {
        List<String> series = (List<String>) controller.listMyWatchedList(currentClient, currentUser);
        listWatched.setListData(new Vector<>(series));
    }

    /**
     * Metodo que actualiza las Series de la lista ContinueWatching
     */
    private void refreshContinueWatching() {
        List<String> series = (List<String>) controller.listMyContinueWatchingList(currentClient, currentUser);
        listContinueWatching.setListData(new Vector<>(series));
    }

    /**
     * Metodo que actualiza las Series del top10 de Series mejor valoradas
     */
    @Override
    public void refreshTopValoracions(IterableStrategy topValoracions) {
        int numRows = tableModelVal.getRowCount();
        for (int i = numRows - 1; i >= 0; i--)
            tableModelVal.removeRow(i);

        int numIteraciones = 0;
        for (Map.Entry serie: (Iterable<Map.Entry>) topValoracions) {
            tableModelVal.addRow(new String[]{(String) serie.getKey(), String.format("%s", serie.getValue())});
            if(numIteraciones == NUM_MAX_TOPS) break;
            numIteraciones ++;
        }
    }

    /**
     * Metodo que actualiza las Series del top10 de Series más visualizadas
     */
    @Override
    public void refreshTopVisualitzacions(IterableStrategy topVisualitzacions) {
        int numRows = tableModelVis.getRowCount();
        for (int i = numRows - 1; i >= 0; i--)
            tableModelVis.removeRow(i);

        int numIteraciones = 0;
        for (Map.Entry serie: (Iterable<Map.Entry>) topVisualitzacions) {
            if((int) serie.getValue() != 0) tableModelVis.addRow(new String[]{(String) serie.getKey(), String.format("%s", serie.getValue())});
            if(numIteraciones == NUM_MAX_TOPS) break;
            numIteraciones ++;
        }
    }

    /**
     * Método que refresca la lista de Usuarios del Cliente actual
     */
    public void refreshUsersList() {
        comboBoxUsuaris.removeAllItems();
        List<String> l = (List<String>) controller.listUsuaris(currentClient);
        for (String userName : l)
            comboBoxUsuaris.addItem(userName);

        comboBoxUsuaris.setSelectedIndex(0);
        setCurrentUser((String)comboBoxUsuaris.getSelectedItem());
    }

    /**
     * Mètodo para abrir la ventana con toda la informacion y opciones disponibles de un Episodio
     * @param idSerie identificador de la Serie
     * @param temporada número de Temporada
     * @param nomEpisodi título del Episodio seleccionado
     * @param duracio duración del Episodio seleccionado
     * @param descripcio descripción del Episodio seleccionado
     */
    private void onEpisodi(String idSerie, int temporada, int idEpisodi, String nomEpisodi, int duracio, String descripcio) {
        FormEpisodi dialog = new FormEpisodi(this, controller, idSerie, temporada, idEpisodi, nomEpisodi, duracio, descripcio, currentClient, currentUser);
        dialog.pack();
        dialog.setVisible(true);
    }

    /**
     * Método que devuelve el Cliente actual que está usando la APP
     * @return Cliente actual
     */
    public String getCurrentClient() { return currentClient; }

    /**
     * Método para establecer un Cliente como el actual que usa la APP
     * @param currentClient Nuevo Cliente actual
     */
    public void setCurrentClient(String currentClient) { this.currentClient = currentClient; }

    /**
     * Método que devuelve el Usuario actual que está usando la APP
     * @return Usuario actual
     */
    public String getCurrentUser() { return currentUser; }

    /**
     * Método para establecer un Usuario como el actual que usa la APP
     * @param currentUser Nuevo Usuario actual
     */
    public void setCurrentUser(String currentUser) { this.currentUser = currentUser; }

}
