package com.platzi.pizza.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "pizza")
@Getter
@Setter
@NoArgsConstructor
public class PizzaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // nombre de la columna en la BD, no permite nulos
    @Column(name = "id_pizza", nullable = false)
    private Integer idPizza;

    //no permite nullos, un largo de caracteres de 30, un caracter unico
    @Column(nullable = false, length = 30, unique = true)
    private String name;
    @Column(nullable = false, length = 150)
    private String description;
    @Column(nullable = false, columnDefinition = "Decimal(5,2)")
    private Double price;
    @Column(columnDefinition = "TINYINT")
    private Boolean vegetarian;

    @Column(columnDefinition = "TINYINT")
    private Boolean vegan;
    @Column(columnDefinition = "TINYINT", nullable = false)
    private Boolean available;

}
