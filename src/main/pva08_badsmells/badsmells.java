public final class BadSmells {

    /* [...] Constants, fields, constructors, and initializations omitted. */

    public static void main(String[] args) {
        BadSmells badSmells = new BadSmells();
        badSmells.processData();
    }

    public void processData() {

        /* Create 10 data objects with random values. */
        System.out.println("Creating database objects ... ");
        DataObject[] dataObjects = new DataObject[10];
        int index = 0;
        try {
            while (true) {
                int value1 = ThreadLocalRandom.current().nextInt();
                int value2 = ThreadLocalRandom.current().nextInt();
                int value3 = ThreadLocalRandom.current().nextInt();
                dataObjects[index] = new DataObject(value1, value2, value3);
                index++;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("... Done");
        }

        /* Save data objects to database and close log file afterwards. */
        try {
            database.openConnection(DATABASE_NAME);
            for (int i = 0; i < dataObjects.length; i++) {
                database.addEntry(dataObjects[i]);
                logger.log(logFile, Level.INFO, "Stored entry " + i);
            }
        } catch (DatabaseException e) {
            logger.log(logFile, Level.SEVERE, "Database error");
        } finally {
            database.commitTransaction();
            database.shutdown();
            logFile.flush();
            logFile.close();
        }

        System.out.println("Finished");
    }

    public static void printSumOfValues(DataObject data) {
        System.out.println(data.getValue1()
                + data.getValue2()
                + data.getValue3());
    }
}

class DataObject {

    private int value1;

    private int value2;

    private int value3;

    public DataObject(int value1, int value2, int value3) {
        BadSmells.printSumOfValues(this);
        this.value1 = value1;
        this.value2 = value2;
        this.value3 = value3;
    }

    public int getValue1() {
        return value1;
    }

    public int getValue2() {
        return value2;
    }

    public int getValue3() {
        return value3;
    }
}