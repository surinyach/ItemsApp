package com.items.itemsBackend.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.items.itemsBackend.models.Item;
import java.util.List;

// ItemRepository.java

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {

    // Methods in repository interfaces can be defined and Spring Data JPA
    // Automatically translates them into appropriate SQL queries.
    Item findById(int id);
    Item findByName(String name);
}