package com.mtom.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@Entity
@Table(name = "order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String item;
    private int quantity;

    @ManyToMany
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    @JsonBackReference
    private Customer customer;
}
