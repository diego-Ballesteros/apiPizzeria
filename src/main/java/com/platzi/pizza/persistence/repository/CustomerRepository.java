package com.platzi.pizza.persistence.repository;

import com.platzi.pizza.persistence.entity.CustomerEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends ListCrudRepository<CustomerEntity, Integer> {
    // Query JPQL para hacer consultas sql directamente con las clases y no con las tablas de la BD
    @Query(value = "SELECT c FROM CustomerEntity c WHERE c.phoneNumber = :phone")
    CustomerEntity findByPhone(@Param("phone") String phone);
}
