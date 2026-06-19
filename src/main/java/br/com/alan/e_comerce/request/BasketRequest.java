package br.com.alan.e_comerce.request;

import java.util.List;

public record BasketRequest(Long clientId, List<ProductRequest> products) {
}
