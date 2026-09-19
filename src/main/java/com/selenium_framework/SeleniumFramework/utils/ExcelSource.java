package com.selenium_framework.SeleniumFramework.utils;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Repeatable(ExcelSources.class)   // ← isse ek method pe multiple @ExcelSource lag sakte hain
public @interface ExcelSource {
    String file();
    String sheet();
}