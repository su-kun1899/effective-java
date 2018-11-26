package red.sukun1899.effectivejava.second.item35;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * このアノテーションがつけられたメソッドがテストメソッドであることを示す
 * パラメータなしのstaticのメソッドに対してだけ使用すること
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Test {
}
