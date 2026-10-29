import java.util.Map;
import java.util.TreeMap;

/**
 * Nodo del Trie: cada nodo representa una transicion de caracter.
 * Se usa TreeMap (en vez de HashMap) para que los hijos queden ordenados
 * alfabeticamente y las sugerencias salgan siempre en el mismo orden.
 */
public class NodoTrie {
    Map<Character, NodoTrie> hijos = new TreeMap<>();
    boolean finPalabra;
}
