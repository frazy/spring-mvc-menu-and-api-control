/**
 * 
 */
package com.github.frazy.spm.enums;

/**
 * role.
 * 
 * @author hzyinglei
 *
 */
public enum RoleEnum implements EnumValue {
    CAT_ROLE("猫猫管理员", new ZoneEnum[] { ZoneEnum.CAT }), //
    DOG_ROLE("狗狗管理员", new ZoneEnum[] { ZoneEnum.DOG }), //
    MASTER("猫猫狗狗管理员", new ZoneEnum[] { ZoneEnum.CAT, ZoneEnum.DOG }), //
    GOD("GOD", new ZoneEnum[] {}), // 超级管理员
    ;

    private String value;
    private ZoneEnum[] zones;

    private RoleEnum(String value, ZoneEnum[] zones) {
        this.value = value;
        this.zones = zones;
    }

    @Override
    public String getValue() {
        return value;
    }

    public ZoneEnum[] getZones() {
        return zones;
    }

}
