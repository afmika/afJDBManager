package annotations;

import java.lang.reflect.Field;
import java.util.Hashtable;
import java.util.Map;
import java.util.Objects;

/**
 * @author afmika
 */

public class CustomTool {

    private static void checkIfCanBeMapped (Object object) throws Exception {
        if (Objects.isNull(object))
            throw new Exception("The object to serialize is null");

        Class<?> clazz = object.getClass();
        if (!clazz.isAnnotationPresent(afTable.class)) {
            throw new Exception("The class " 
              + clazz.getSimpleName() 
              + " is not annotated with JsonSerializable");
        }
    }

    public static Map<String, Object> convertToMap (Object object) throws Exception {
        checkIfCanBeMapped (object);
        Map<String, Object> map = new Hashtable <> ();

        Class<?> cl = object.getClass();
        String objName = cl.getName ();
        map.put("_type_", objName);

        // fetching the annoted fields
        Field[] fields = cl.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(afColumn.class)) {
                String name = field.getName();
                String keyAlias  = field.<afColumn>getAnnotation(afColumn.class).alias();
                if ( !"".equals(keyAlias) )
                    name = keyAlias;
                map.put (name, field.get(object));
            }
        }
        return map;
    }
}