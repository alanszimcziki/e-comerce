package br.com.alan.e_comerce.entity;

import br.com.alan.e_comerce.enums.PaymentMethod;
import br.com.alan.e_comerce.enums.Status;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.List;

@Document(collection = "basket")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
public class Basket {

    @Id
    private String id;
    private Long client;
    private BigDecimal price;
    private List<Product> products;
    private Status status;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private PaymentMethod paymentMethod;


    public void calculatePrice(){
        this.price = products.stream().map(product -> product.getPrice()
                .multiply(BigDecimal.valueOf(product.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }





}
