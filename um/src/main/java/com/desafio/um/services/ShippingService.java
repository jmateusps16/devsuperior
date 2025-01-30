package com.desafio.um.services;

import com.desafio.um.entities.Order;
import org.springframework.stereotype.Service;

@Service
public class ShippingService {

    /**
     * Retorna o valor do frete, até 100 reais o frete é 20, de 100 a 200 o frete é 12, acima é gratis.
     * */
    public double calculateShipping(Order order) {
        if (order.getBasic() < 100.0) {
            return 20.0;
        } else if (order.getBasic() < 200.0) {
            return 12.0;
        } else {
            return 0;
        }
    }
}
