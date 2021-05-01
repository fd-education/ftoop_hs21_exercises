package pva07.serializer;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.lang.Class;

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

    public void serializeAllFields(Object dataObject) throws Exception {
        /* Fügen Sie HIER Ihren Code ein. */
        try {
            // // Alternative, falls voller Name bekannt
            // final Class<?> clazz = Class.forName("pva07.serializer.MyDataClass");
            final Class<?> clazz = dataObject.getClass();
            final Class<? extends Annotation> doNotSerialize = DoNotSerialize.class;

            final Field[] fields = clazz.getDeclaredFields();

            for (Field field : fields) {

                // Überspringe Durchlauf für Attribute welche mit @DoNotSerialize annotiert sind.
                final Annotation[] annotations = field.getAnnotations();
                if(hasAnnotationType(annotations, doNotSerialize)) continue;

                // private Attribute müssen der Reflection erst zugänglich gemacht werden
                field.setAccessible(true);
                printFieldInfo(field.getName(), field.getType(), field.get(dataObject));
            }

        } catch(final ReflectiveOperationException e){
            throw new IllegalStateException("Can not access field!", e);
        } catch(final SecurityException e) {
            throw new IllegalStateException("Insufficient security rights to access field!", e);
        }
    }

    private boolean hasAnnotationType(final Annotation[] annotations, final Class<? extends Annotation> annotationType){

        for(Annotation a: annotations){
            Class<? extends Annotation> type = a.annotationType();
            if(type.equals(annotationType)) return true;
        }

        return false;
    }

    private void printFieldInfo(String fieldName,
                                Class<?> fieldType,
                                Object fieldValue) {

        System.out.format("%s : %s = %s%n", fieldName, fieldType, fieldValue);
    }
}