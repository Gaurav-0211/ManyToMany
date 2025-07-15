package com.mtom.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@Entity
@Table(name = "`order`")  // Due to order a reserved keyword.
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String item;
    private int quantity;

    @ManyToMany(mappedBy = "orders", cascade = CascadeType.ALL)
    private Set<Customer> customers;
}
