package ub.edu.resources.dao.MOCK;

import ub.edu.model.Episodi;
import ub.edu.model.Temporada;
import ub.edu.resources.dao.DAOEpisodi;

import java.util.*;

public class DAOEpisodiMOCK implements DAOEpisodi {

    private final Map<String, ArrayList<Episodi>> listEpisodis = new HashMap<>();

    public DAOEpisodiMOCK() {

        ///////////////////////////////
        /*       BREAKING BAD        */
        ///////////////////////////////
        // Episodis para Temporada 1 de la Serie Breaking Bad
        ArrayList<Episodi> episodis_Breaking_Bad = new ArrayList<>();
        episodis_Breaking_Bad.add(new Episodi("bbad", "Breaking Bad",1, 1, "Piloto", "55:00:00", "VOSE",   "Walter White, era un famoso investigador químico de un prestigioso Laboratorio del Gobierno, que a dia de hoy ha sido relegado a enseñr a aplicos estudiantes de un Instituto en Albuquerque. Para poder llegar a fin de mes también tiene un trabajo a media jornada en un lavadero de coches. Además, él y su esposa, Skyler, una mujer que se ha forjado una modesta carrera en la compra-venta de artӣulos por eBay, está criando a Walter, Jr., un joven de diecisiete añs, con una gran fuerza de voluntad, que tiene parálisis cerebral lo que presenta un continuo reto para la familia. Pero todas estas dificultades palidecen en relación con el nuevo descubrimiento de Walter: tiene cancer con un pronóstico de dos añs de vida.", "20/01/2008"));
        episodis_Breaking_Bad.add(new Episodi("bbad", "Breaking Bad",1 , 2, "Cat's in the Bag","55:00:00", "VOSE","Ahora que su incursiخ inicial en el mundo del trȦico de drogas ha terminado con varias muertes, Walter no estǠseguro de tener lo que hay que tener para ser socio de Jesse. Decidido en acabar con el negocio tan pronto escondan los cadȶeres, las cosas dan un giro inesperado cuando uno de los muertos vuelve a la vida..." , "27/01/2008"));
        episodis_Breaking_Bad.add(new Episodi("bbad", "Breaking Bad",1 , 3, "..And The Bag's In The River","55:00:00", "VOSE","Walter no sabe si matar o no a Krazy-8. Mientras, Marie cree que su hijo estǠenganchado a la marihuana y le pide a Hank que le de algݮ susto para que lo deje. Walter decide invitar a comer a Krazy-8, pero las cosas se tuercen cuando se marea mientras baja unos escalones. Le contarǠlo que pasa?","10/02/2008"));
        episodis_Breaking_Bad.add(new Episodi("bbad", "Breaking Bad",1 , 4, "Cancer man","55:00:00", "VOSE","Ahora que Skyler sabe su secreto, y sin conocer que el equipo de la DEA estǠcercano a descubrir su negocio de drogas, Walt decide contar en una barbacoa familiar las novedades de su cȮcer. Aunque Hank le ofrece 5.000 dجares para su tratamiento, Ϭ rechaza el dinero. Le dice a Skyler que recurrirǠal dinero de su pensiخ, pero en realidad utilizarǠel dinero que han conseguido Jesse y Ϭ vendiendo drogas.","10/02/2008"));

        // Episodis para Temporada 2 de la Serie Breaking Bad
        episodis_Breaking_Bad.add(new Episodi("bbad", "Breaking Bad",2 , 1, "Seven Thirty-Seven", "55:00:00", "VOSE",   "Walt y Jesse intentar poner fin a su acuerdo con Tuco. Mientras tanto, Hank intenta arreglar la relaciخ entre Marie y Skyler.", "08/03/2009"));
        episodis_Breaking_Bad.add(new Episodi("bbad", "Breaking Bad",2 , 2, "Grilled","55:00:00", "VOSE","Con Walt y Jesse atrapados por Tuco, Marie y Hank intentan reconfortar a Skyler tras su desapariciخ." , "15/03/2009"));
        episodis_Breaking_Bad.add(new Episodi("bbad", "Breaking Bad",2 , 3, "Bit by a Dead Bee","55:00:00", "VOSE","Walt y Jesse tratan de cubrir sus huellas pero no resulta muy efectivo. Mientras tanto, la DEA sigue una pista que podrӡ llegar directamente hasta ellos.","22/03/2009"));

        // Episodis para Temporada 3 de la Serie Breaking Bad
        episodis_Breaking_Bad.add(new Episodi("bbad", "Breaking Bad",3 , 1, "No more", "55:00:00", "VOSE",   "Todo Alburquerque queda paralizado despuϳ del accidente aϲeo. Walter White estǠviviendo solo en su casa. Su esposa, Skyler se ha mudado con su hijo y su reciϮ nacida hija para darle oportunidad a Walt para que empaque.", "20/01/2008"));
        episodis_Breaking_Bad.add(new Episodi("bbad", "Breaking Bad",3 , 2, "Horse with no name","55:00:00", "VOSE","Walt tiene problemas ajustȮdose a su nueva vida. Se niega a seguir manufacturando el producto." , "27/01/2008"));
        episodis_Breaking_Bad.add(new Episodi("bbad", "Breaking Bad",3 , 3, "I.F.T","55:00:00", "VOSE","Walt regresa a su casa y le dice a Skyler que no tiene intenciخ de irse, Walter Jr. se alegra de saber que su padre estǠde vuelta.","10/02/2008"));
        episodis_Breaking_Bad.add(new Episodi("bbad", "Breaking Bad",3 , 4, "Green Light","55:00:00", "VOSE","Jesse intercambia con una cajera metanfetamina a cambio de pagar por la gasolina.","10/02/2008"));
        episodis_Breaking_Bad.add(new Episodi("bbad", "Breaking Bad",3 , 5, "Mȳ","55:00:00", "VOSE","Gus trata de convencer a Walt a regresar al negocio con un \"super-laboratorio\" de alta tecnologӡ" ,"22/03/2009"));

        listEpisodis.put("bbad", episodis_Breaking_Bad);



        ///////////////////////////////
        /*      GAME OF THRONES      */
        ///////////////////////////////
        // Episodis para Temporada 1 de la Serie Breaking Bad
        ArrayList<Episodi> episodis_GameOfThrones = new ArrayList<>();
        episodis_GameOfThrones.add(new Episodi("gofthrones", "Game of Thrones",1 , 1, "Winter Is Coming", "00:13:00", "VOSE",   "El rey Robert Baratheon de Poniente viaja al Norte para ofrecerle a su viejo amigo Ned Stark, GuardiȮ del Norte y Ser de Invernalia, el puesto de Mano del Rey", "17/04/2011"));
        episodis_GameOfThrones.add(new Episodi("gofthrones", "Game of Thrones",1 , 2, "The Kingsroad","00:01:00", "VOSE", "Tras aceptar su nuevo rol como Mano del Rey, Ned parte hacia Desembarco del Rey con sus hijas Sansa y Arya", "24/04/2011"));
        episodis_GameOfThrones.add(new Episodi("gofthrones", "Game of Thrones",1 , 3, "Lord Snow","00:01:15", "VOSE","Ned se une al Consejo Privado del Rey en Desembarco del Rey","01/05/2011"));

        // Episodis para Temporada 2 de la Serie Breaking Bad
        episodis_GameOfThrones.add(new Episodi("gofthrones", "Game of Thrones",2 , 1, "The north remembers", "00:15:00", "VOSE",   "Tyrion llega para salvar la corona de Joffrey", "01/04/2012"));
        episodis_GameOfThrones.add(new Episodi("gofthrones", "Game of Thrones",2 , 2, "The Night Lands","00:00:15", "VOSE","Arya comparte un secreto con un recluta familiar" , "08/04/2012"));
        episodis_GameOfThrones.add(new Episodi("gofthrones", "Game of Thrones",2 , 3, "What is dead may never die","00:00:15", "VOSE","Tyrion desenmascara...","15/05/2012"));

        // Episodis para Temporada 3 de la Serie Breaking Bad
        episodis_GameOfThrones.add(new Episodi("gofthrones", "Game of Thrones",3 , 1, "Valar Dohaeris", "00:00:30", "VOSE",   "Todo Alburquerque queda paralizado despuϳ del accidente aϲeo. Walter White estǠviviendo solo en su casa. Su esposa, Skyler se ha mudado con su hijo y su reciϮ nacida hija para darle oportunidad a Walt para que empaque.", "20/01/2008"));
        episodis_GameOfThrones.add(new Episodi("gofthrones", "Game of Thrones",3 , 2, "Dark wings","00:00:30", "VOSE","Walt tiene problemas ajustȮdose a su nueva vida. Se niega a seguir manufacturando el producto." , "27/01/2008"));
        episodis_GameOfThrones.add(new Episodi("gofthrones", "Game of Thrones",3 , 3, "Walk of punishment","00:00:30", "VOSE","Walt regresa a su casa y le dice a Skyler que no tiene intenciخ de irse, Walter Jr. se alegra de saber que su padre estǠde vuelta.","10/02/2008"));

        listEpisodis.put("gofthrones", episodis_GameOfThrones);


        ///////////////////////////////
        /*         MR ROBOT          */
        ///////////////////////////////
        // Episodis para Temporada 1 de la Serie Mr Robot
        ArrayList<Episodi> episodis_MrRobot = new ArrayList<>();
        episodis_MrRobot.add(new Episodi("mrobot", "Mr Robot",1 , 1, "eps1.0_hellofriend.mov", "00:00:30", "ES",   "Elliot Alderson trabaja en Allsafe, una empresa de seguridad informȴica.", "24/06/2015"));
        episodis_MrRobot.add(new Episodi("mrobot", "Mr Robot",1 , 2, "Hey, ¿Que pasa?","00:00:45", "ES", "Walt tiene problemas ajustȮdose a su nueva vida. Se niega a seguir manufacturando el producto.", "01/07/2015"));
        episodis_MrRobot.add(new Episodi("mrobot", "Mr Robot",1 , 3, "Marvel >> DC","00:01:30", "ES","Tyrell pega una paliza a un mendigo para liberar estrϳ.","08/07/2015"));

        listEpisodis.put("mrobot", episodis_MrRobot);

        ///////////////////////////////
        /*      Stranger Things      */
        ///////////////////////////////
        // Episodis para Temporada 1 de la Serie Stranger Things
        ArrayList<Episodi> episodis_Strangerthings = new ArrayList<>();
        episodis_Strangerthings.add(new Episodi("sthings", "Stranger Things",1 , 1, "Episodio 1: Stranger Things", "00:00:30", "ES",   "Ahora que Skyler sabe su secreto, y sin conocer que el equipo de la DEA estǠcercano a descubrir su negocio de drogas, Walt decide contar en una barbacoa familiar las novedades de su cȮcer. Aunque Hank le ofrece 5.000 dجares para su tratamiento, Ϭ rechaza el dinero. Le dice a Skyler que recurrirǠal dinero de su pensiخ, pero en realidad utilizarǠel dinero que han conseguido Jesse y Ϭ vendiendo drogas.","10/02/2008"));
        episodis_Strangerthings.add(new Episodi("sthings", "Stranger Things",1 , 2, "Episodio 2: Stranger Things","00:00:05", "ES", "Elliott rechaza una bien remunerada oferta de trabajo ofrecida por", "01/07/2015"));
        episodis_Strangerthings.add(new Episodi("sthings", "Stranger Things",1 , 3, "Episodio 3: Stranger Things","00:00:10", "ES","Tyrell pega una paliza a un mendigo para liberar estrϳ.","08/07/2015"));

        listEpisodis.put("sthings", episodis_Strangerthings);


        ///////////////////////////////
        /*      Rick Y Morty      */
        ///////////////////////////////
        // Episodis para Temporada 1 de la Serie Rick Y Morty
        ArrayList<Episodi> episodis_RickYMorty = new ArrayList<>();
        episodis_RickYMorty.add(new Episodi("RyM", "Rick y Morty",1 , 1, "Episodio 1: Rick Y Morty", "00:00:30", "ES", "Pepinillo Rick", "03/04/2000"));
        episodis_RickYMorty.add(new Episodi("RyM", "Rick y Morty",1 , 2, "Episodio 2: Rick Y Morty","00:00:20", "ES", "Elliott rechaza una bien remunerada oferta de trabajo ofrecida por", "01/07/2015"));
        episodis_RickYMorty.add(new Episodi("RyM", "Rick y Morty",1 , 3, "Episodio 3: Rick Y Morty","00:00:50", "ES","Tyrell pega una paliza a un mendigo para liberar estrϳ.","08/07/2015"));

        listEpisodis.put("RyM", episodis_RickYMorty);

        ///////////////////////////////
        /*      The Mandalorian      */
        ///////////////////////////////
        // Episodis para Temporada 1 de la Serie The Mandalorian
        ArrayList<Episodi> episodis_TheMandalorian = new ArrayList<>();
        episodis_TheMandalorian.add(new Episodi("TMand", "The Mandalorian",1 , 1, "Episodio 1: The Mandalorian", "00:00:30", "ES", "AHHHHHH StarWars new Serie!!!", "03/04/2000"));
        episodis_TheMandalorian.add(new Episodi("TMand", "The Mandalorian",1 , 2, "Episodio 2: The Mandalorian","00:00:20", "ES", "Elliott rechaza una bien remunerada oferta de trabajo ofrecida por", "01/07/2015"));
        episodis_TheMandalorian.add(new Episodi("TMand", "The Mandalorian",1 , 3, "Episodio 3: The Mandalorian","00:00:05", "ES","Tyrell pega una paliza a un mendigo para liberar estrϳ.","08/07/2015"));

        listEpisodis.put("TMand", episodis_TheMandalorian);



        // PENNY DREADFUL

        // BLACK MIRROR

        // SONS OF ANARCHY

        // STRANGER THINGS

        // MISFITS

        // PEAKY BLINDERS

        // WALKING DEAD
    }
    @Override
    public Optional<Episodi> getById(String id){
        for (Map.Entry<String, ArrayList<Episodi>> entry: listEpisodis.entrySet()) {
            List<Episodi> values = entry.getValue();
            for (Episodi e:values) if (e.getIdSerie().equals(id)) return Optional.of(e);
        }
        return Optional.empty();
    }

    @Override
    public List<Episodi> getAll() {
        List<Episodi> llistaCompleta = new ArrayList<>();

        for (Map.Entry<String, ArrayList<Episodi>> entry: listEpisodis.entrySet()) {
            List<Episodi> values = entry.getValue();
            llistaCompleta.addAll(values);
        }
        return llistaCompleta;
    }

    @Override
    public boolean add(Episodi episodi) {
        if (listEpisodis.containsKey(episodi.getIdSerie()) && listEpisodis.containsKey(episodi.getIdTemporada())) {
            ArrayList<Episodi> llista = listEpisodis.get(episodi.getIdSerie());
            for (Episodi e:llista) {
                if (e.getIdTemporada() == (episodi.getIdTemporada()) && e.getIdSerie().equals(episodi.getIdSerie())) return false;
            }
            llista.add(episodi);
        }
        else {
            ArrayList<Episodi> episodis_T1_Breaking_Bad = new ArrayList<>();
            episodis_T1_Breaking_Bad.add(episodi);
            listEpisodis.put(episodi.getIdSerie(), episodis_T1_Breaking_Bad);
        }
        return true;
    }

    @Override
    public boolean update(Episodi episodi, String[] params) {
        if (listEpisodis.containsKey(episodi.getIdSerie())) {
            ArrayList<Episodi> llista = listEpisodis.get(episodi.getIdSerie());
            for (Episodi e : llista) {
                if (e.getIdTemporada() == (episodi.getIdTemporada())) {
                    e.setIdSerie(Objects.requireNonNull(
                            params[0], "Season's ID cannot be null"));
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean delete(Episodi episodi) throws Exception {
        throw new Exception();
    }

    @Override
    public List<Episodi> getEpisodisForTemporada(Temporada t) {
        if (t == null) {
            throw new ClassCastException();
        }
        if (listEpisodis.containsKey(t.getIdTemporada())) {
            return (listEpisodis.get(t.getIdTemporada()));
        }
        return null;
    }

    @Override
    public Episodi getEpisodiBySerieTemporadaEpisodi(String idSerie, int idTemporada, int idEpisodi){
        if (listEpisodis.containsKey(idSerie)){
            ArrayList<Episodi> llista = listEpisodis.get(idSerie);
            for (Episodi episodi:llista) {
                if (episodi.getIdTemporada()==idTemporada && episodi.getNumEpisodi()==idEpisodi) return episodi;
            }
        }
        return null;
    }

}
