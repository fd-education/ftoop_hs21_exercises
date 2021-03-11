package ch.ffhs.ftoop.serializer;

public final class Serializer {

    public static void main(String[] args) {
        try {
            Serializer serializer = new Serializer();
            MyDataClass myDataObject = new MyDataClass(true, 2020, "FFHS");
            serializer.serializeAllFields(myDataObject);
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public void serializeAllFields(Object dataObject)
            throws Exception {

        /* Fügen Sie HIER Ihren Code ein. */


    }

    private void printFieldInfo(String fieldName,
                                Class<?> fieldType,
                                Object fieldValue) {

        System.out.format("%s : %s = %s%n", fieldName, fieldType, fieldValue);
    }
}