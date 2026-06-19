package br.com.alan.e_comerce.request;

import br.com.alan.e_comerce.enums.PaymentMethod;

public record PaymentRequest(PaymentMethod paymentMethod) {
}
