/**
 * 
 */
package com.github.frazy.spm.enums;

/**
 * zone.
 * 
 * @author hzyinglei
 *
 */
public enum ZoneEnum implements EnumValue {
    // 特殊虚拟Zone
    OPEN("OPEN"), // 不需要登录对外公开的API
    LOGIN("LOGIN"), // 只要登录即可访问的API

    // 具体Zone
    CAT("猫猫管理"), //
    DOG("狗狗管理"), //
    CODER("码农管理"), //
    ;

    private String value;

    private ZoneEnum(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }

}
