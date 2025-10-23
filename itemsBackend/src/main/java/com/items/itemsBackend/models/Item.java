package com.items.itemsBackend.models;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
// Item.java

@Data // Lombok Ann. Generates getters, setters, toString, equals, hashCode
@AllArgsConstructor // Lombok Ann. Generates constructor with all fields
@NoArgsConstructor // Lombok Ann. Generates empty constructor
@Entity // Marks the class as a database entity (used by JPA/Hibernate)
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = "name")
})
public class Item {
    @Id // Marks this item as the primary key of the entity.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    @Size(min=3, max=20)
    private String name;

    @NotBlank
    @Size(min=3, max=20)
    private String section;

    @DecimalMax("200.0")
    @DecimalMin("0.0")
    private double price;

    @Max(value=100)
    @Min(value=0)
    private int stock;
}
