package br.com.alan.e_comerce.controller;

import br.com.alan.e_comerce.client.response.PlatziProductResponse;
import br.com.alan.e_comerce.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@Slf4j
public class ProductController {

    private final ProductService productService;


    @GetMapping
    public ResponseEntity<List<PlatziProductResponse>> getAll(){
        log.info("REST request to get all PlatziProducts");
        return ResponseEntity.ok(productService.getProducts());
    }

    @GetMapping("{id}")
    public ResponseEntity<PlatziProductResponse> getById(@PathVariable Long id){
        log.info("REST request to get PlatziProducts by id {}", id);
        return ResponseEntity.ok(productService.getProductById(id));
    }


}
