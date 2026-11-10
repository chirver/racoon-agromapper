package androidx.annotation;
import java.lang.annotation.*;
@Retention(RetentionPolicy.SOURCE) @Target({ElementType.ANNOTATION_TYPE}) public @interface LongDef { long[] value() default {}; boolean flag() default false; boolean open() default false; }
