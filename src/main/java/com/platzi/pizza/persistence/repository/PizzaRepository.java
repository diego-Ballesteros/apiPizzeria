package com.platzi.pizza.persistence.repository;

import com.platzi.pizza.persistence.entity.PizzaEntity;
import com.platzi.pizza.service.DTO.UpdatePizzaPriceDto;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

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

    //se puede recibir un unico parametro que pueda ser un bojeto
    // para llamar el atributo del objeto es -> ( :#{# pizza.newPrice}  )
    // o solo usar parametros por separado, precio y Id
    @Query(value = "UPDATE pizza " +
                    "SET price = :#{#newPizzaDto.newPrice} " +
                    "WHERE id_pizza = :#{#newPizzaDto.pizzaId}", nativeQuery = true)
    @Modifying
    void updatePrice (@Param("newPizzaDto") UpdatePizzaPriceDto newPizzaDto);

}
