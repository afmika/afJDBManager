package core;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

/**
 * @author afmika
 */
public class afReflectTools {
    public static Field[] extractPublicAndFriendlyFields (Object object) {
        Field[] fields = object.getClass().getDeclaredFields();

        ArrayList<Field> out = new ArrayList<>();
        for (Field f : fields) {
            int mod = f.getModifiers();
            boolean encapsulated = ( Modifier.isProtected(mod)
                        || Modifier.isPrivate(mod) );
            if ( !encapsulated )
                out.add(f);
        }

        return out.stream()
                .toArray(Field[]::new);
    }

    public static String capitalizeFirstLetter (String input) {
        return input.substring(0, 1).toUpperCase() + input.substring(1);
    }
}