package annotations;

import java.lang.annotation.*;

/**
 * @author afmika
 * Should be added before the field definition
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface afColumn {
    public String alias () default "";
}