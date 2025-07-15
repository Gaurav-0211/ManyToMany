package com.mtom.dto;

import lombok.*;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class CustomerDto {
    private long id;
    private String customerName;
    private String city;
    private Set<Long> orderIds;
}
