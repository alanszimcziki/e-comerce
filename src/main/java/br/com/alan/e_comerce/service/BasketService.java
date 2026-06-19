package br.com.alan.e_comerce.service;

import br.com.alan.e_comerce.client.response.PlatziProductResponse;
import br.com.alan.e_comerce.entity.Basket;
import br.com.alan.e_comerce.entity.Product;
import br.com.alan.e_comerce.enums.Status;
import br.com.alan.e_comerce.exceptions.BusinessException;
import br.com.alan.e_comerce.exceptions.DataNotFoundException;
import br.com.alan.e_comerce.repository.BasketRepository;
import br.com.alan.e_comerce.request.BasketRequest;
import br.com.alan.e_comerce.request.PaymentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BasketService {

    private final BasketRepository basketRepository;
    private final ProductService productService;


    public Basket getBasketById(String id) {
        return basketRepository.findById(id).orElseThrow(
                () -> new DataNotFoundException("Basket with id " + id + " not found!")
        );
    }

    public Basket createBasket(BasketRequest request) {
        basketRepository.findByClientAndStatus(request.clientId(), Status.open)
                .orElseThrow(() -> new BusinessException("There is already an open basket for this customer.\n"));
        List<Product> products = getProducts(request);

        Basket basket = Basket.builder()
                .products(products)
                .client(request.clientId())
                .status(Status.open)
                .build();

        basket.calculatePrice();
        return basketRepository.save(basket);
    }

    public Basket updateBasket(String id, BasketRequest request) {
        Basket savedBasket = getBasketById(id);
        List<Product> products = getProducts(request);
        savedBasket.setProducts(products);
        savedBasket.calculatePrice();
        return basketRepository.save(savedBasket);
    }

    public Basket paymentBasket(String id, PaymentRequest request) {
        Basket basket = getBasketById(id);
        basket.setPaymentMethod(request.paymentMethod());
        basket.setStatus(Status.sold);
        return basketRepository.save(basket);
    }

    public void deleteBasket(String id) {
        Basket basket = getBasketById(id);
        basketRepository.delete(basket);
    }

    private List<Product> getProducts(BasketRequest request) {
        List<Product> products = new ArrayList<>();
        request.products().forEach(productRequest -> {
            PlatziProductResponse platziProductResponse = productService.getProductById(productRequest.id());
            products.add(Product.builder()
                    .id(platziProductResponse.id())
                    .title(platziProductResponse.title())
                    .price(platziProductResponse.price())
                    .quantity(productRequest.quantity()).build());
        });
        return products;
    }


}
