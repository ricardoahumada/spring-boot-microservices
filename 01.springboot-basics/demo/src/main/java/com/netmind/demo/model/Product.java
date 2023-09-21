package es.netmind.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class Product {
    private Long id;
    private String name;
}

/*
public record Product(Long id, String name) {
}
*/
