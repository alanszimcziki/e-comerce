package br.com.alan.e_comerce.entity;

import lombok.*;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Product {

    private Long id;
    private String title;
    private BigDecimal price;
    private Integer quantity;
}
