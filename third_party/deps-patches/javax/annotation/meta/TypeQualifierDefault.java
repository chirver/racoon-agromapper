package javax.annotation.meta;
import java.lang.annotation.*;
@Retention(RetentionPolicy.RUNTIME) @Target(ElementType.ANNOTATION_TYPE) public @interface TypeQualifierDefault { ElementType[] value() default {}; }
