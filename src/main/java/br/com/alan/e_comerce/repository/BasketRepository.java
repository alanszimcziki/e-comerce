package br.com.alan.e_comerce.repository;

import br.com.alan.e_comerce.entity.Basket;
import br.com.alan.e_comerce.enums.Status;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface BasketRepository extends MongoRepository<Basket, String> {
    Optional<Basket> findByClientAndStatus(Long client, Status status);

}
