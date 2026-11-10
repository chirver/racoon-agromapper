package androidx.annotation;
import java.lang.annotation.*;
@Retention(RetentionPolicy.CLASS) public @interface FloatRange { double from() default Double.NEGATIVE_INFINITY; double to() default Double.POSITIVE_INFINITY; boolean fromInclusive() default true; boolean toInclusive() default true; }
