package com.thana.apiviewer.utils.sugarapi;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * After SugarAPI has loaded this mod file. They will engulf the classes
 * that mapped with @Rewrite into the located class.
 */
@Target({ElementType.METHOD, ElementType.FIELD})
public @interface Rewrite {
}
