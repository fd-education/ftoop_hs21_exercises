package pva05.divisor;
/**
 * Hält das Ergebnis einer Berechnung
 *
 * @author bele
 */
class DivisorResult {
    // das eigentlich ergebnis - die Zahl mit der max. Anzahl von Divisoren
    long result;

    // Anzahl der Divisoren von Result
    long countDiv;

    public DivisorResult(long r, long c) {
        result = r;
        countDiv = c;
    }

    public long getResult() {
        return result;
    }

    public long getCountDiv() {
        return countDiv;
    }

    @Override
    public String toString() {
        return "Zahl mit maximaler Anzahl Divisoren: " + result + " ("
                + countDiv + " Divisoren)";
    }

}
