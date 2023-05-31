package com.weare4saken.pcstore.enums;

public enum Capacity {
    GB_250("250Гб"),
    GB_500("500Гб"),
    TB_1("1Тб"),
    TB_2("2Тб");

    private String name;

    Capacity(String name) {
        this.name = name;
    }

}
