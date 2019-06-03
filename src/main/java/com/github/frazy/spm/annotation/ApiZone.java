/**
 * 
 */
package com.github.frazy.spm.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.github.frazy.spm.enums.ZoneEnum;

/**
 * Specify the zone of the API.
 * 
 * @author hzyinglei
 *
 */
@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ApiZone {

    ZoneEnum[] value();

}
