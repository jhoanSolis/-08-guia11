# Guía 11. Trie: mini motor de autocompletado

**Asignatura:** Estructura de Datos II
**Programa:** Ingeniería de Software, Semestre IV
**Estudiante:** Miguel Angel Meneses Arevalo
**Ecosistema:** Java / GitHub

## Objetivo

Construir un Trie propio para insertar palabras, buscar términos y sugerir palabras por prefijo.

## Archivos

| Archivo | Descripción |
|---|---|
| `NodoTrie.java` | Nodo con un mapa de hijos (`Map<Character, NodoTrie>`) y la marca `finPalabra`. |
| `Trie.java` | Métodos `insertar`, `buscar`, `empiezaCon` y `sugerir`. |
| `Main.java` | Carga un diccionario de 32 palabras y prueba los métodos. |
| `salida_consola.txt` | Salida real de la ejecución. |

## Estructura

Un Trie guarda las palabras letra por letra y las palabras que comparten prefijo comparten el mismo camino. Cada nodo representa una letra y tiene:

- `hijos`: mapa de la letra siguiente al nodo hijo.
- `finPalabra`: indica si el camino hasta ese nodo forma una palabra completa.

Ejemplo con `cas`, `casa` y `casco`:

```
raiz -> c -> a -> s -> a (fin: casa)
                  |
                  +-> c -> o (fin: casco)
```

Se usó `TreeMap` en lugar de `HashMap` para que los hijos queden ordenados y las sugerencias salgan siempre en orden alfabético.

## Métodos

- **insertar(palabra):** recorre la palabra letra por letra, crea los nodos que falten con `computeIfAbsent` y marca `finPalabra = true` en el último.
- **buscar(palabra):** recorre el camino; devuelve `true` solo si el camino existe y el último nodo tiene `finPalabra`. Por eso `buscar("cas")` es `false` aunque `cas` sea prefijo de `casa`.
- **empiezaCon(prefijo):** igual que buscar, pero solo exige que el camino exista.
- **sugerir(prefijo, limite):** llega al nodo final del prefijo y desde ahí hace un recorrido en profundidad (DFS), recolectando palabras hasta llegar a 5 resultados.

## Pruebas (5 prefijos o más)

```
sugerir("ca")  -> [cama, camino, camisa, campo, canal]
sugerir("cas") -> [casa, casaca, casado, casco, casino]
sugerir("pro") -> [problema, proceso, programa, programador, programar]
sugerir("pe")  -> [pelo, pelota, pera, perro, peso]
sugerir("da")  -> [dado, dato, datos]
sugerir("z")   -> sin resultados
```

Salida completa en `salida_consola.txt`.

## Complejidad respecto a la longitud de la cadena

Sea L la longitud de la palabra o prefijo y N la cantidad de palabras del diccionario.

| Operación | Complejidad |
|---|---|
| insertar | O(L) |
| buscar | O(L) |
| empiezaCon | O(L) |
| sugerir | O(P + R), con P = longitud del prefijo y R = nodos recorridos para reunir los resultados |

Lo importante es que buscar e insertar **no dependen de N**: con 30 palabras o con un millón el tiempo es el mismo, solo cambia con la longitud de la palabra.

## Comparación con revisar una lista de 1000 palabras

En una lista, buscar una palabra obliga a compararla con cada elemento, en el peor caso 1000 comparaciones, es decir O(N · L). Si se quieren las palabras con un prefijo, hay que revisar las 1000.

En un Trie se sigue un solo camino de L letras (por ejemplo 5 pasos para una palabra de 5 letras), sin importar cuántas palabras haya guardadas. Para autocompletar, el Trie va directo al nodo del prefijo y solo recorre las palabras que realmente coinciden.

La desventaja es que el Trie usa más memoria, porque cada nodo tiene su propio mapa de hijos.

## Cómo ejecutar

```
javac *.java
java Main
```
