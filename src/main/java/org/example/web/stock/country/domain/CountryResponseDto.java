package org.example.web.master.country.domain;

public class CountryResponseDto {
    private final Integer id;
    private final String code;
    private final String name;

    public CountryResponseDto(Integer id, String code, String name) {
        this.id = id;
        this.code = code;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
