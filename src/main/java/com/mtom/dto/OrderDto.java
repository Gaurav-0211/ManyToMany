package com.mtom.dto;

import lombok.*;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class OrderDto {
    private long id;
    private String item;
    private int quantity;
    private Set<Long> customerIds;
}
