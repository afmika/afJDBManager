package core;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

import annotations.afColumn;
import annotations.afTable;

/**
 * @author afmika
 */
public class afReflectTools {
    public static Field[] extractPublicAndFriendlyFields (Object object) {
        Field[] fields = object.getClass().getDeclaredFields();

        ArrayList<Field> out = new ArrayList<>();
        for (Field f : fields) {
            int mod = f.getModifiers();
            boolean encapsulated = Modifier.isProtected(mod);
            if ( !encapsulated )
                out.add(f);
        }

        return out.stream()
                .toArray(Field[]::new);
    }

    public static String capitalizeFirstLetter (String input) {
        return input.substring(0, 1).toUpperCase() + input.substring(1);
    }

    
    /**
     * Extract the target table name if the current object is annotated with @afTable (alias = "...")
     * otherwise it will return the class name
     * @param object
     * @return
     * @throws Exception
     */
    public static String extractTableTarget (Object object) throws Exception {

        Class<?> cls = object.getClass();
        String final_name = cls.getSimpleName(); // ex some.long.Stuff => Stuff

        if (cls.isAnnotationPresent(afTable.class)) {
            String keyAlias = cls.<afTable>getAnnotation(afTable.class).alias();
            if ( !"".equals(keyAlias) )
                final_name = keyAlias;
        }

        return final_name;
    }

    /**
     * Extract fields and map the proper alias given by @afColumn (alias = "...") if any.
     * If not given, the variable name will be used as a key
     * @param object
     * @return
     */
    public static Map<String, Field> extractFieldWithProperAliases (Object object) {
        Field[] fields = extractPublicAndFriendlyFields(object);
        Map<String, Field> map = new Hashtable<>();
        for (Field field : fields) {
            String final_name = field.getName();
            if (field.isAnnotationPresent(afColumn.class)) {
                String keyAlias = field.<afColumn>getAnnotation(afColumn.class).alias();
                if ( !"".equals(keyAlias) )
                    final_name = keyAlias;
            }
            map.put (final_name, field);
        }
        return map;
    }
}