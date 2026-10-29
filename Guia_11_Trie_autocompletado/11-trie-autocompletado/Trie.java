import java.util.ArrayList;
import java.util.List;

public class Trie {
    private final NodoTrie raiz = new NodoTrie();

    // Inserta una palabra: O(L), L = longitud de la palabra
    public void insertar(String palabra) {
        NodoTrie actual = raiz;
        for (char c : palabra.toLowerCase().toCharArray()) {
            actual = actual.hijos.computeIfAbsent(c, k -> new NodoTrie());
        }
        actual.finPalabra = true;
    }

    // Devuelve el nodo al que se llega siguiendo el texto, o null si no existe el camino
    private NodoTrie recorrer(String texto) {
        NodoTrie actual = raiz;
        for (char c : texto.toLowerCase().toCharArray()) {
            actual = actual.hijos.get(c);
            if (actual == null) {
                return null;
            }
        }
        return actual;
    }

    // true solo si la palabra completa fue insertada: O(L)
    public boolean buscar(String palabra) {
        NodoTrie nodo = recorrer(palabra);
        return nodo != null && nodo.finPalabra;
    }

    // true si existe al menos una palabra que empiece con el prefijo: O(P)
    public boolean empiezaCon(String prefijo) {
        return recorrer(prefijo) != null;
    }

    // Devuelve hasta 'limite' palabras que empiezan con el prefijo
    public List<String> sugerir(String prefijo, int limite) {
        List<String> resultado = new ArrayList<>();
        NodoTrie nodoPrefijo = recorrer(prefijo);
        if (nodoPrefijo == null) {
            return resultado; // ninguna palabra con ese prefijo
        }
        recolectar(nodoPrefijo, new StringBuilder(prefijo.toLowerCase()), resultado, limite);
        return resultado;
    }

    // Recorrido en profundidad desde el nodo del prefijo
    private void recolectar(NodoTrie nodo, StringBuilder actual, List<String> resultado, int limite) {
        if (resultado.size() >= limite) {
            return; // ya se alcanzo el maximo
        }
        if (nodo.finPalabra) {
            resultado.add(actual.toString());
        }
        for (var entrada : nodo.hijos.entrySet()) {
            actual.append(entrada.getKey());
            recolectar(entrada.getValue(), actual, resultado, limite);
            actual.deleteCharAt(actual.length() - 1); // backtracking
            if (resultado.size() >= limite) {
                return;
            }
        }
    }
}
