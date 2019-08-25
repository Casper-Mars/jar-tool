package org.r.tool.jar.enums;

/**
 * @Author Casper
 * @DATE 2019/8/25 20:25
 **/
public enum ProtocalEnum {

    FILE("file", "file:");

    private String name;

    private String value;

    ProtocalEnum(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }
}
