package com.platzi.pizza.persistence.audit;

import com.platzi.pizza.persistence.entity.PizzaEntity;
import jakarta.persistence.PostLoad;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.PreRemove;
import org.springframework.util.SerializationUtils;

public class AuditPizzaListener {
    private PizzaEntity currentValue;
    @PostLoad
    public void postLoad(PizzaEntity pizza){
        System.out.println("POST LOAD");
        this.currentValue = SerializationUtils.clone(pizza);
    }
    @PostPersist
    @PostUpdate
    public void onPostPersist(PizzaEntity pizza){
        System.out.println("Post persist or update");
        System.out.println("Old value :"+ this.currentValue);
        System.out.println("New value :"+ pizza.toString());
    }
    @PreRemove
    public void onPreDelete(PizzaEntity pizza){
        System.out.println(pizza.toString());
    }
}
