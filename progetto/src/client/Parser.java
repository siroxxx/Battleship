package client;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Parser {

    public String parseCoordinate(Flotta f) {
        String str = "";

        for (Nave n : f.flotta) {
            for (Point p : n.coordinate) {
                Point p2 = Minimappa.getMapPoint(p, Costanti.WIDTH);
                str += p2.x + ";" + p2.y + ";";
            }
        }

        return str;
    }

    public String parseComando(String risposta) {
        return risposta.split(";")[0]; // primo elemento della risposta divisa
    }

    public String[] parsePayload(String risposta) {
        String[] evNaviAffondate = null;
        String[] temp = null;

        if (risposta.contains("/")) { // se ci sono navi affondate ill tutto va gestito in maniera diversa
            evNaviAffondate = risposta.split("/");
        }

        if (evNaviAffondate != null)
            temp = evNaviAffondate[0].split(";"); // devo prendere la prima parte nel caso ci sia lo "/"
        else
            temp = risposta.split(";");

        String[] payload = new String[temp.length - 1];
        for (int index = 0; index < temp.length - 1; index++) {
            payload[index] = temp[index + 1];
        }
        return payload;
    }

    public String[] parsePayloadDifesa(String risposta) { // ritorna i true/false
        String[] evNaviAffondate = null;
        String[] temp = null;

        temp = risposta.split("_");

        if (risposta.contains("/")) { // se ci sono navi affondate ill tutto va gestito in maniera diversa
            evNaviAffondate = temp[1].split("/");
        }

        if (evNaviAffondate != null)
            temp = evNaviAffondate[0].split(";"); // devo prendere la prima parte nel caso ci sia lo "/"
        else
            temp = temp[1].split(";");

        String[] payload = new String[temp.length - 1];
        for (int index = 0; index < temp.length - 1; index++) {
            payload[index] = temp[index + 1];
        }
        return payload;
    }

    public String[] getNaviAffDifesa(String risposta) { // ritorna i true/false
        String[] evNaviAffondate = null;
        String[] temp = null;

        temp = risposta.split("_");

        if (risposta.contains("/")) { // se ci sono navi affondate ill tutto va gestito in maniera diversa
            evNaviAffondate = temp[1].split("/");
        }

        if (evNaviAffondate != null)
            return evNaviAffondate[1].split(";"); // devo prendere la prima parte nel caso ci sia lo "/"

        return null;
    }

    public List<Point> getCoordinateDifesa(String risposta) {
        String[] temp = null;
        temp = risposta.split("_");
        List<Point> lista = new ArrayList<>();

        temp = temp[0].split(";");
        for (int i = 1; i < temp.length; i += 2) {
            lista.add(new Point(Integer.parseInt(temp[i]), Integer.parseInt(temp[i + 1])));
        }

        return lista;
    }

    public Boolean[] parseSpari(String[] payload) {
        Boolean[] risultati = new Boolean[payload.length];
        for (int i = 0; i < risultati.length; i++) {
            risultati[i] = Boolean.parseBoolean(payload[i]);
        }
        return risultati;
    }

    public String[] parseNaviAffondate(String naviAff) {
        return naviAff.split(";");
    }

    public Integer parseRadar(String[] payload) {
        return Integer.parseInt(payload[0]);
    }

    public String getNaviAffondate(String risposta) {
        return risposta.split("/")[1];
    }

    public String parseInvio(String comandoNuovo, List<Point> payloadNuovo) {
        String s = comandoNuovo + ";";

        for (Point p : payloadNuovo) {
            Point p2 = Minimappa.getMapPoint(p, 0);
            s += p2.x + ";" + p2.y + ";";
        }

        return s;
    }

}
