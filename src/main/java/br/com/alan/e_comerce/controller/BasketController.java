package br.com.alan.e_comerce.controller;

import br.com.alan.e_comerce.request.BasketRequest;
import br.com.alan.e_comerce.entity.Basket;
import br.com.alan.e_comerce.request.PaymentRequest;
import br.com.alan.e_comerce.service.BasketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/basket")
@RequiredArgsConstructor
public class BasketController {

    private final BasketService basketService;

    @GetMapping("/{id}")
    public ResponseEntity<Basket> getBasket(@PathVariable String id) {
        return ResponseEntity.ok(basketService.getBasketById(id));
    }

    @PostMapping
    public ResponseEntity<Basket> createdBasket(@RequestBody BasketRequest request) {
        return ResponseEntity.ok(basketService.createBasket(request));
    }

    @PutMapping("{id}")
    public ResponseEntity<Basket> updateBasket(@PathVariable String id, @RequestBody BasketRequest request) {
        return ResponseEntity.ok(basketService.updateBasket(id, request));
    }

    @PutMapping("/{id}/payment")
    public ResponseEntity<Basket> paymentBasket(@PathVariable String id, @RequestBody PaymentRequest request) {
        return ResponseEntity.ok(basketService.paymentBasket(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBasket(@PathVariable String id) {
        basketService.deleteBasket(id);
        return ResponseEntity.noContent().build();
    }
}
