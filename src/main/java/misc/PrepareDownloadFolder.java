package misc;

import java.lang.annotation.*;

// Annotation indicates that elements using this annotation should be documented by JavaDoc
@Documented
// Specifies where we can use the annotation.
// The target type as METHOD which means the below annotation can only be used on methods
@Target(ElementType.METHOD)
// Annotation signals that a custom annotation used in a class should be inherited by all of its sub classes.
@Inherited
// It indicates how long annotations with the annotated type are to be retained
@Retention(RetentionPolicy.RUNTIME)
public @interface PrepareDownloadFolder {
    String path() default "";
}

/*@PrepareDownloadFolder(
        studentName = "Chaitanya",
)*/
