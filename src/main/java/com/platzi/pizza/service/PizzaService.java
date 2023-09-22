package com.platzi.pizza.service;

import com.platzi.pizza.persistence.entity.PizzaEntity;
import com.platzi.pizza.persistence.repository.PizzaPagSortRepository;
import com.platzi.pizza.persistence.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PizzaService {

    private final PizzaRepository pizzaRepository;
    private final PizzaPagSortRepository pizzaPagSortRepository;
    @Autowired
    public PizzaService(PizzaRepository pizzaRepository, PizzaPagSortRepository pizzaPagSortRepository) {
        this.pizzaRepository = pizzaRepository;
        this.pizzaPagSortRepository = pizzaPagSortRepository;
    }

   /* public List<PizzaEntity> getAll(){
        return this.pizzaRepository.findAll();
    }*/
    public Page<PizzaEntity> getAll(int page, int elements){
        Pageable pageRequest = PageRequest.of(page, elements);
        return this.pizzaPagSortRepository.findAll(pageRequest);
    }

    /*public List<PizzaEntity> getAvailable(){
        System.out.println(this.pizzaRepository.countByVeganTrue());
        return this.pizzaRepository.findByAvailableTrueOrderByPrice();
    }*/

    public Page<PizzaEntity> getAvailable(int page, int elements, String sortBy, String sortDirection){
        System.out.println(this.pizzaRepository.countByVeganTrue());
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        Pageable pageRequest = PageRequest.of(page, elements, sort);
        return this.pizzaPagSortRepository.findByAvailableTrue(pageRequest);
    }

    public PizzaEntity getByName(String name){
        return this.pizzaRepository.findAllByAvailableTrueAndNameIgnoreCase(name)
                .orElseThrow(()-> new RuntimeException("la pizza no existe "));
    }

    public List<PizzaEntity> getWhitIngredient(String ingredient){
        return this.pizzaRepository.findAllByAvailableTrueAndDescriptionContainingIgnoreCase(ingredient);
    }

    public List<PizzaEntity> getWhitoutIngredient(String ingredient){
        return this.pizzaRepository.findAllByAvailableTrueAndDescriptionNotContainingIgnoreCase(ingredient);
    }

    public List<PizzaEntity> getCheapest(double prince){
        return this.pizzaRepository.findTop3ByAvailableTrueAndPriceLessThanEqualOrderByPriceAsc(prince);
    }

    public PizzaEntity getPizzaById(int idPizza){
        return this.pizzaRepository.findById(idPizza).orElse(null);
    }

    public PizzaEntity savePizza(PizzaEntity pizza){
        return this.pizzaRepository.save(pizza);
    }

    public void deletePizza(int idPizza){
        this.pizzaRepository.deleteById(idPizza);
    }

    public boolean existsPizza(int idPizza){
        return this.pizzaRepository.existsById(idPizza);
    }
}
