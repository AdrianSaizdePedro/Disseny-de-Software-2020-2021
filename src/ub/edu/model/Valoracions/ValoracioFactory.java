package ub.edu.model.Valoracions;

public class ValoracioFactory {
    public Valoracio createValoracio(String tipus, int id, String idClient, String idUser, String idSerie, int idTemp, int idEpisodi, int stars, String data){
        Valoracio valoracio;
        switch(tipus){
            case "Cor": valoracio = new CorValoracio(id, idClient, idUser, idSerie, idTemp, idEpisodi, data); break;
            case "Estrellas": valoracio = new EstrellasValoracio(id, idClient, idUser, idSerie, idTemp, idEpisodi, stars, data); break;
            default:valoracio = null;
        }
        return valoracio;
    }
}
