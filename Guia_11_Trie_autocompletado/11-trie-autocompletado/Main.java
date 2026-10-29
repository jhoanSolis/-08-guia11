import java.util.List;

public class Main {
    static final int LIMITE = 5;

    public static void main(String[] args) {
        Trie trie = new Trie();

        String[] diccionario = {
            "casa", "casaca", "casado", "casino", "casco", "cama", "camino", "camisa", "campo",
            "canal", "canto", "carro", "carta", "cartel", "cargar", "perro", "pera", "peso",
            "pelota", "pelo", "programa", "programar", "programador", "proyecto", "problema",
            "proceso", "dato", "datos", "dado", "dedo", "arbol", "arco"
        };
        for (String p : diccionario) {
            trie.insertar(p);
        }
        System.out.println("Diccionario cargado: " + diccionario.length + " palabras\n");

        System.out.println("=== Pruebas de buscar() ===");
        String[] busquedas = {"casa", "cas", "programa", "programas", "ARBOL"};
        for (String b : busquedas) {
            System.out.println("buscar(\"" + b + "\") -> " + trie.buscar(b));
        }

        System.out.println("\n=== Pruebas de empiezaCon() ===");
        String[] prefijosExiste = {"cas", "pro", "xyz"};
        for (String p : prefijosExiste) {
            System.out.println("empiezaCon(\"" + p + "\") -> " + trie.empiezaCon(p));
        }

        System.out.println("\n=== Autocompletado (maximo " + LIMITE + " sugerencias) ===");
        String[] prefijos = {"ca", "cas", "pro", "pe", "da", "z"};
        for (String p : prefijos) {
            List<String> sug = trie.sugerir(p, LIMITE);
            System.out.println("sugerir(\"" + p + "\") -> " + (sug.isEmpty() ? "sin resultados" : sug));
        }
    }
}
