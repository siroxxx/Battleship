package client;

public class Parser {

    public char[] parseCoordinate(Flotta f) {
        return null;
    }

    public String parseComando(String risposta) {
        return risposta.split(";")[0]; // primo elemento della risposta divisa
    }

    public String[] parsePayload(String risposta) {
        String[] evNaviAffondate = null;
        if (risposta.contains("/")) { // se ci sono navi affondate ill tutto va gestito in maniera diversa
            evNaviAffondate = risposta.split("/");
        }
        String[] temp = evNaviAffondate[0].split(";"); // devo prendere la prima parte nel caso ci sia lo "/"
        String[] payload = new String[temp.length - 1];
        for (int index = 0; index < temp.length - 1; index++) {
            payload[index] = temp[index + 1];
        }
        return payload;
    }

    public Boolean[] parseSpari(String[] payload) {
        Boolean[] risultati = new Boolean[payload.length];
        for (int i = 0; i < risultati.length; i++) {
            risultati[i]=Boolean.parseBoolean(payload[i]);
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

}
