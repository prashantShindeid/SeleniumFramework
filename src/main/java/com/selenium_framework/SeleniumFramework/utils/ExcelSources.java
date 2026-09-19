package com.selenium_framework.SeleniumFramework.utils;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ExcelSources {
    ExcelSource[] value();
}