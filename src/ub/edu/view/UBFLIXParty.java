package ub.edu.view;

/* Interfície Gràfica desenvolupada per: Nils Ballús, Joan Cano, David Rial i Miquel Guiot */

import ub.edu.controller.IController;
import ub.edu.model.Episodi;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

/**
 * GUI bàsica de l'app UBFLIX on es mostraran les diferents llistes corresponent a cada client que hagi realitzat el Log In.
 * Aquesta classe hereta de JFrame i és la vista principal de l'aplicació.
 */
public class UBFLIXParty extends JFrame implements Observer{

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


    //Afegits manualment
    private HashMap<String, JPopupMenu> popupMenuTemporades;
    private DefaultTableModel tableModelVis;
    private DefaultTableModel tableModelVal;

    private List<Map.Entry<String, Double>> listaTopTenValoracions;
    private List<Map.Entry<String, Double>> listaTopTenVisualitzacions;

    private IController controller;
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
        btnTancarSessio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ferLogIn();
            }
        });
        btnAfegirMyList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addSerieToMyList();
            }
        });
        btnTreureMyList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeSerieFromMyList();
            }
        });
        btnCrearUsuari.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userActionPerformed();
            }
        });
        perfilButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarPerfil();
            }
        });
        llistes.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if(llistes.getSelectedIndex() == 0){
                    btnAfegirMyList.setEnabled(true);
                    btnTreureMyList.setEnabled(false);
                } else if(llistes.getSelectedIndex() == 2){
                    btnAfegirMyList.setEnabled(false);
                    btnTreureMyList.setEnabled(true);
                }
                else{
                    btnAfegirMyList.setEnabled(false);
                    btnTreureMyList.setEnabled(false);
                }
            }
        });
        listAll.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                mostrarPopupMenuTemporades(listAll, fieldAll);
            }
        });
        listMyList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                mostrarPopupMenuTemporades(listMyList, fieldWatchNext);
            }
        });
        listWatched.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                mostrarPopupMenuTemporades(listWatched, fieldWatched);
            }
        });
        listContinueWatching.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                mostrarPopupMenuTemporades(listContinueWatching, fieldNotStarted);
            }
        });
        comboBoxUsuaris.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (comboBoxUsuaris.getItemCount() > 0) {
                    setCurrentUser((String)comboBoxUsuaris.getSelectedItem());
                    refreshLlistes();
                }
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
    private void mostrarPopupMenuTemporades(JList llista, JPanel panel) {
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
     * Mètode que fa visible el formulari de LogIn
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
     * Mètode que es crida quan s'obre la finestra i crida a ferLogIn()
     * @param evt event que es dóna a l'obrir l'aplicació
     */
    private void formWindowOpened(java.awt.event.WindowEvent evt) {
        refreshListAll();
        ferLogIn();
    }

    /**
     * Mètode que es crida quan es tanca la finestra, fet click sobre la 'x' amb missatge de confirmació de sortida.
     * @param evt event que es dóna al tancar la finestra
     */
    private void formWindowClosing(java.awt.event.WindowEvent evt) {
        if (JOptionPane.showConfirmDialog(this, "VOLS SORTIR? ", "SORTIR APP", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == 0)
            System.exit(0);
    }

    /**
     * Mètode que actualitza les sèries del catàleg
     */
    private void refreshListAll() {
        //String[] series = {"serie 1","serie 2", "serie 3"};
        List<String> series = controller.llistarCatalegSeries();
        listAll.setListData(new Vector<>(series));
        refreshTemporades(series);
    }

    /**
     * Mètode que serveix per actualitzar les temporades de les sèries passades per paràmetres
     * @param series sèries de les quals s'han d'actualitzar les temporades
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
     * Mètode que serveix per actualitzar els episodis de la temporada de la sèrie indicats per paràmetres
     * @param serie sèrie de la qual s'ha d'actualitzar els episodis
     * @param temporada temporadad de la qual s'ha d'actualitzar els episodis
     * @param jm JMenu de l'episodi
     */
    private void refreshEpisodis(String serie, String temporada, JMenu jm) {

        List<Episodi> episodis = controller.getEpisodis(serie, Integer.parseInt(temporada.split(" ")[1]));

        for (Episodi episodi: episodis) {

            String idSerie = episodi.getIdSerie();

            int numTemporada = Integer.parseInt(temporada.split(" ")[1]);
            int duracio = episodi.getDuracio();
            int duracioVisualitzada = 0;//controller.getDuracioVisualitzada(currentClient, currentUser, idSerie, numTemporada, episodi.getNumEpisodi());
            String descripcio = episodi.getDescripcio();

            JMenuItem ep = new JMenuItem(episodi.getTitol());
            ep.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    onEpisodi(idSerie, numTemporada, episodi.getNumEpisodi(), episodi.getTitol(), duracio, duracioVisualitzada, descripcio);
                }
            });
            jm.add(ep);
        }
    }

    /**
     * Mètode que serveix per actualitzar totes les llistes de la vista
     */
    private void refreshLlistes() {
        refreshWatched();
        refreshMyList();
        refreshContinueWatching();
        refreshTopValoracions();
        refreshTopVisualitzacions();
    }


    /**
     * Método que añade una serie a mi MyList
     */
    private void addSerieToMyList() {
        String info = controller.addSerieToMyList(1, currentClient, currentUser, listAll.getSelectedValue());
        JOptionPane.showMessageDialog(jPanel, info);
        refreshMyList();
    }

    /**
     * Método que elimina una serie a mi MyList
     */
    private void removeSerieFromMyList() {
        String info = controller.removeSerieFromMyList(1, currentClient, currentUser, listMyList.getSelectedValue());
        JOptionPane.showMessageDialog(jPanel, info);
        refreshMyList();
    }

    /**
     * Mètode que actualitza les sèries de la llista MyList
     */
    private void refreshMyList() {
        List<String> series = (List<String>) controller.listMyList(currentClient, currentUser);
        listMyList.setListData(new Vector<>(series));
    }

    /**
     * Mètode que actualitza les sèries de la llista Watched
     */
    private void refreshWatched() {
        String[] series = {"serie 21", "serie 22", "serie 23"};
        listWatched.setListData(series);
    }

    /**
     * Mètode que actualitza les sèries de la llista ContinueWatching
     */
    private void refreshContinueWatching() {
        String[] series = {"serie 31", "serie 32", "serie 33"};
        listContinueWatching.setListData(series);
    }

    /**
     * Mètode que actualitza les sèries del top10 de sèries més ben valorades
     */
    private void refreshTopValoracions() {
        //Fem el clear de la llista del top 10 de valoracions
        int numRows = tableModelVal.getRowCount();
        for (int i = numRows - 1; i >= 0; i--)
            tableModelVal.removeRow(i);

        for (Map.Entry serie: listaTopTenValoracions) {
            tableModelVal.addRow(new String[]{(String) serie.getKey(), String.format("%.2f", serie.getValue())});
        }
    }

    /**
     * Mètode que actualitza les sèries del top10 de sèries més visualitzades
     */
    private void refreshTopVisualitzacions() {
        //Fem el clear de la llista del top 10 de visualitzacions
        int numRows = tableModelVis.getRowCount();
        for (int i = numRows - 1; i >= 0; i--)
            tableModelVis.removeRow(i);

        String [] topTenVisualitzacions = {"serie 1", "serie 2", "serie 3", "serie 4", "serie 5", "serie 6", "serie 7", "serie 8", "serie 9", "serie 10"};
        for (String serie: topTenVisualitzacions) {
            tableModelVis.addRow(new Object[]{serie, 10});
        }
    }

    /**
     * Refresca la llista d'Usuaris del client actual
     */
    public void refreshUsersList() {
        comboBoxUsuaris.removeAllItems();
        List<String> l = (List<String>) controller.listUsuaris(currentClient);
        for (String userName : l) {
            comboBoxUsuaris.addItem(userName);
        }
        comboBoxUsuaris.setSelectedIndex(0);
        setCurrentUser((String)comboBoxUsuaris.getSelectedItem());
    }

    /**
     * Mètode que serveix per obrir la finestra amb tota la informació i opcions disponibles d'un episodi seleccionat
     * @param idSerie identificador de la sèrie de l'episodi
     * @param temporada número de temporada de l'episodi
     * @param nomEpisodi títol de l'episodi seleccionat
     * @param duracio duració de l'episodi seleccionat
     * @param duracioVisualitzada duració ja visualitzada pel client de l'episodi seleccionat
     * @param descripcio descripció de l'episodi seleccionat
     */
    private void onEpisodi(String idSerie, int temporada, int idEpisodi, String nomEpisodi, int duracio, int duracioVisualitzada, String descripcio) {
        FormEpisodi dialog = new FormEpisodi(this, controller, idSerie, temporada, idEpisodi, nomEpisodi, duracio, duracioVisualitzada, descripcio, currentUser, currentClient);
        dialog.pack();
        dialog.setVisible(true);
    }

    /**
     *
     * @param listaTopTenValoracions
     */
    @Override
    public void update(List<Map.Entry<String, Double>> listaTopTenValoracions) {
        this.listaTopTenValoracions = listaTopTenValoracions;
        refreshLlistes();
    }

    public String getCurrentClient() {
        return currentClient;
    }

    public void setCurrentClient(String currentClient) {
        this.currentClient = currentClient;
    }

    public String getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(String currentUser) {
        this.currentUser = currentUser;
    }


}
