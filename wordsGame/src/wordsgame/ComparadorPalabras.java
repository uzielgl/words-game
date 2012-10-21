package wordsgame;

import java.util.Comparator;

/**
 *
 * @author Vostro
 */
public class ComparadorPalabras implements Comparator<String>{

    public static boolean esSubconjunto(char[] letrasPalabra, char[] letras) {
        int indLetras = -1;
        cicloPalabra:
        for (char letra : letrasPalabra) {
            while (++indLetras < letras.length) {
                if (letra == letras[indLetras]) continue cicloPalabra;
                if (letras[indLetras] > letra) return false;
            }
            return false;
        }
        return true;
    }

    @Override
    public int compare(String o1, String o2) {
        if (o1 == null && o2 == null) return 0;
        if (o1 == null) return 1;
        if (o2 == null) return -1;
        if (o1.length() != o2.length()) return o1.length() - o2.length();
        else {
            return o1.compareTo(o2);
        }
    }
}
