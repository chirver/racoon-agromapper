package javax.annotation.concurrent;
import java.lang.annotation.*;
@Retention(RetentionPolicy.CLASS) public @interface GuardedBy { String value(); }
