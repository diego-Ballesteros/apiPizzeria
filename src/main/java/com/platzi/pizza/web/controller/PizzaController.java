package com.platzi.pizza.web.controller;

import com.platzi.pizza.persistence.entity.PizzaEntity;
import com.platzi.pizza.service.DTO.UpdatePizzaPriceDto;
import com.platzi.pizza.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pizzas")
public class PizzaController {

    private final PizzaService pizzaService;    @Autowired
    public PizzaController(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }
   /* @GetMapping
    public ResponseEntity<List<PizzaEntity>> getAll(){
        return ResponseEntity.ok(this.pizzaService.getAll());
    }*/
    @GetMapping
    public ResponseEntity<Page<PizzaEntity>> getAll(@RequestParam(defaultValue = "0") int page,
                                                    @RequestParam(defaultValue = "8") int elements){
        return ResponseEntity.ok(this.pizzaService.getAll(page, elements));
    }
    @GetMapping("/{idPizza}")
    public ResponseEntity<PizzaEntity> getPizzaById(@PathVariable int idPizza){
        return ResponseEntity.ok(this.pizzaService.getPizzaById(idPizza));
    }
    @GetMapping("/cheapest/{price}")
    public ResponseEntity<List<PizzaEntity>> getCheapest(@PathVariable double price){
        return ResponseEntity.ok(this.pizzaService.getCheapest(price));
    }

    @PostMapping
    public ResponseEntity<PizzaEntity> addPizza (@RequestBody PizzaEntity pizza){
        if(pizza.getIdPizza() == null || !this.pizzaService.existsPizza(pizza.getIdPizza())){
            return ResponseEntity.ok(this.pizzaService.savePizza(pizza));
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping
    public ResponseEntity<PizzaEntity> uptadePizza (@RequestBody PizzaEntity pizza){
        if(pizza.getIdPizza() != null && this.pizzaService.existsPizza(pizza.getIdPizza())){
            return ResponseEntity.ok(this.pizzaService.savePizza(pizza));
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/price")
    public ResponseEntity<Void> uptadePizzaPrice (@RequestBody UpdatePizzaPriceDto pizzaDto){
        //if(this.pizzaService.existsPizza(pizzaDto.getPizzaId())){
            this.pizzaService.updatePrice(pizzaDto);
            return ResponseEntity.ok().build();
        //}
       // return ResponseEntity.badRequest().build();
    }
    @DeleteMapping("/{idPizza}")
    public ResponseEntity<Void> detelePizza(@PathVariable int idPizza){
        if(this.pizzaService.existsPizza(idPizza)){
            this.pizzaService.deletePizza(idPizza);
            return ResponseEntity.ok().build();
        }
    return ResponseEntity.badRequest().build();
    }

    /*@GetMapping("/available")
    public ResponseEntity<List<PizzaEntity>> getAvailable(){
        return ResponseEntity.ok(this.pizzaService.getAvailable());
    }*/

    @GetMapping("/available")
    public ResponseEntity<Page<PizzaEntity>> getAvailable(@RequestParam(defaultValue = "0") int page,
                                                          @RequestParam(defaultValue = "8") int elements,
                                                          @RequestParam(defaultValue = "price") String sortBy,
                                                          @RequestParam(defaultValue = "ASC") String sortDirection){
        return ResponseEntity.ok(this.pizzaService.getAvailable(page, elements, sortBy, sortDirection));
    }
    @GetMapping("/whit/{ingredient}")
    public ResponseEntity<List<PizzaEntity>> getWhitIngredient(@PathVariable String ingredient){
        return ResponseEntity.ok(this.pizzaService.getWhitIngredient(ingredient));
    }

    @GetMapping("/whitout/{ingredient}")
    public ResponseEntity<List<PizzaEntity>> getWhitoutIngredient(@PathVariable String ingredient){
        return ResponseEntity.ok(this.pizzaService.getWhitoutIngredient(ingredient));
    }
    @GetMapping("/name/{name}")
    public ResponseEntity<PizzaEntity> getByName(@PathVariable String name){
        return ResponseEntity.ok(this.pizzaService.getByName(name));
    }

}
