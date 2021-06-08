package annotations;

import java.lang.annotation.*;

/**
 * @author afmika
 * Should be added before the class definition
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface afTable {
    public String alias () default "";
}