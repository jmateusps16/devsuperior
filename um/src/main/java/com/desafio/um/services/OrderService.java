package com.desafio.um.services;

import com.desafio.um.entities.Order;
import com.desafio.um.entities.OrderImpl;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final ShippingService shippingService;

    /**
     * Autowired não necessário para este tipo de injeção a partir da versão 4.3 do spring
     * https://docs.spring.io/spring-framework/reference/core/beans/annotation-config/autowired.html
     */
    public OrderService(ShippingService shippingService) {
        this.shippingService = shippingService;
    }

    public Order newOrder(int code, double basic, double discount) {
        return new OrderImpl(code, basic, discount);
    }

    public double total(Order order) {
        double finalTotal = order.getBasic() - calcDiscount(order.getBasic(), order.getDiscount());
        double shippingCost = shippingService.calculateShipping(order);
        return finalTotal + shippingCost;
    }

    private double calcDiscount(double basic, double discount) {
        return (basic * discount) / 100;
    }
}
