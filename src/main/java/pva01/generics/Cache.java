package pva01.generics;

import org.jetbrains.annotations.NotNull;

import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Mit dieser Klasse wollen wir veranschaulichen, wie wir mit generischen
 * Klassen arbeiten können. Wir verwenden mit der generischen Klasse eine
 * sortierte Datenstruktur des JDK's, so dass wir gleichzeitig das Interface
 * #java.lang.Comparable umsetzen müssen.
 *
 * @param <T> zu verwaltender Datentype
 */
public class Cache<T extends Comparable<T>> implements Comparable<T>{

    private final T cache;

    public Cache(T cache) {
        this.cache = cache;
    }

    /**
     * Vergleicht dieses Objekt mit dem angegebenen Objekt. Liefert eine
     * negative ganze Zahl, null oder eine positive Zahl, falls dieses Objekt
     * kleiner, gleich oder grösser als das angegebene Objekt ist.
     */
    public int compareTo(@NotNull T object) {
        return cache.compareTo(object);
    }

    /**
     * Kleine Testmethode für unsere Klasse.
     *
     * @param args wird nicht verwendet.
     */
    @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
    public static void main(String[] args) {
        Cache<String> cache = new Cache<>("Text");

        SortedSet<Cache<String>> list = new TreeSet<>(); // Sortiertes Set
        list.add(cache);
    }

}
