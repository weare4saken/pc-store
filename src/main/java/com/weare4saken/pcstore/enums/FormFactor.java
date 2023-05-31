package com.weare4saken.pcstore.enums;

public enum FormFactor {
    DESKTOP("декстоп"),
    NETTOP("неттоп"),
    MONOBLOCK("моноблок");

    private String name;

    FormFactor(String name) {
        this.name = name;
    }

}
