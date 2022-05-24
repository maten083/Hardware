package GraphicsCard.Annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)

public @interface getterFuncionName{
    String value() default "";
    Class type() default Object.class;
    int noInConsturct() default 0;
}
