package com.platzi.pizza.persistence.repository;

import com.platzi.pizza.persistence.entity.PizzaEntity;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;
import java.util.Optional;

public interface PizzaRepository extends ListCrudRepository<PizzaEntity, Integer> {
    List<PizzaEntity> findByAvailableTrueOrderByPrice();
    Optional<PizzaEntity> findAllByAvailableTrueAndNameIgnoreCase(String name);
    //podria agregar Firs para saber cual es el primero / findFirst
    // Top sirve para saber el top N de registros segun la consulta / findTop3
    List<PizzaEntity> findAllByAvailableTrueAndDescriptionContainingIgnoreCase(String description);

    List<PizzaEntity> findAllByAvailableTrueAndDescriptionNotContainingIgnoreCase(String description);

    List<PizzaEntity> findTop3ByAvailableTrueAndPriceLessThanEqualOrderByPriceAsc(double price);
    int countByVeganTrue(); //numero de pizzas veganas

}
