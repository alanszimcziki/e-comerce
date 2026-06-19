package br.com.alan.e_comerce.service;

import br.com.alan.e_comerce.client.PlatziStoreClient;
import br.com.alan.e_comerce.client.response.PlatziProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final PlatziStoreClient platziStoreClient;

    @Cacheable(value = "products")
    public List<PlatziProductResponse> getProducts() {
        return platziStoreClient.getAllProducts();
    }
    @Cacheable(value = "products", key = "#id")
    public PlatziProductResponse getProductById(Long id) {
        return platziStoreClient.getProductById(id);
    }





}
