package com.david.general.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * non-prime values should provides non-null values 
 * whether in the annotation definition or in the concrete classes
 * use -1 or empty string to represent the absence of the default value instead 
 * @author wangwei
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SimulatingNull {

  public int id() default -1;
  
  public String description() default "";
}
