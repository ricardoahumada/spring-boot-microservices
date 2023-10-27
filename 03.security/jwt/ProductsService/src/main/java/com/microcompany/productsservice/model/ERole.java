package com.netmind.productsservice.model;

public enum ERole {
    USER("USER"),
    ADMIN("ADMIN");

    private String name;

    private ERole(String name) {
        this.name = name;
    }

    /*USER(Names.USER),
    ADMIN(Names.ADMIN);

    private String value;

    private ERole (String value) {
        this.value = value;
    }

    public static class Names {
        public  final static String USER = "USER";
        public  final static String ADMIN = "ADMIN";
    }*/

}
