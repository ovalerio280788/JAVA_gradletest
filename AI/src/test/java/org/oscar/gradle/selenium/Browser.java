package org.oscar.gradle.selenium;

/**
 * Created by Oscar on 4/6/2017.
 * The idea with this instance is to use it to refer a Browser using this interface variables instead of
 * hardcoding the text, this help us to avoid issues because of mismatched strings
 */
public interface Browser {
    String FIREFOX =  "Firefox";
    String CHROME = "chrome";
    String IE = "ie";
}
