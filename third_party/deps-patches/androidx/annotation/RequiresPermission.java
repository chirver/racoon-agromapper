package androidx.annotation;
import java.lang.annotation.*;
/** Minimal stand-in for the AndroidX annotation (documentation only; no runtime effect). */
@Retention(RetentionPolicy.CLASS)
public @interface RequiresPermission { String value() default ""; String[] allOf() default {}; String[] anyOf() default {};  }
