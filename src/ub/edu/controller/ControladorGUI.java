package ub.edu.controller;

import ub.edu.resources.service.DataService;
import ub.edu.resources.service.FactoryMOCK;
import ub.edu.model.FacadeClients;
import ub.edu.model.FacadeRegistre;
import ub.edu.model.FacadeSeries;

public class ControladorGUI implements IController{
    private volatile static ControladorGUI uniqueInstance;

    private DataService dataService;
    private FacadeSeries facadeSeries;
    private FacadeClients facadeClients;
    private FacadeRegistre facadeRegistre;

    private ControladorGUI() {
    }

    public static ControladorGUI getInstance() throws Exception {
        if (uniqueInstance == null) {
            synchronized (ControladorGUI.class) {
                if (uniqueInstance == null) {
                    uniqueInstance = new ControladorGUI();
                    uniqueInstance.dataService = new DataService(new FactoryMOCK());
                    uniqueInstance.facadeSeries = new FacadeSeries();
                    uniqueInstance.facadeClients = new FacadeClients();
                    uniqueInstance.facadeRegistre = new FacadeRegistre();
                }
            }
        }
        return uniqueInstance;
    }

    public void init() throws Exception {
        facadeSeries.init(dataService.getAllSeries(), dataService.getAllTemporades(), dataService.getAllEpisodis());
        facadeClients.init(dataService.getAllClients(), dataService.getAllUsuaris(),dataService.getAllFollowers(), dataService.getAllFollowings());
        facadeRegistre.init(dataService.getAllPreferencias(), dataService.getAllCorValoracions(), dataService.getAllEstrellaValoracions(), dataService.getAllVisualitzacions());
    }
}
