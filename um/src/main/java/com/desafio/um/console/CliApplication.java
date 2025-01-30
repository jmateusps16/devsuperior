package com.desafio.um.console;

import com.desafio.um.entities.Order;
import com.desafio.um.services.OrderService;
import com.desafio.um.services.ShippingService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class CliApplication implements CommandLineRunner {

    private final OrderService orderService;
    private final ShippingService shippingService;

    /**
     * Autowired não necessário para este tipo de injeção a partir da versão 4.3 do spring
     * https://docs.spring.io/spring-framework/reference/core/beans/annotation-config/autowired.html
     */
    public CliApplication(OrderService orderService, ShippingService shippingService) {
        this.orderService = orderService;
        this.shippingService = shippingService;
    }

    @Override
    public void run(String... args) {
        executePredefinedCalculations();
        executeUserInputCalculations();
    }

    private void executePredefinedCalculations() {
        System.out.println("\n🔹 Cálculos iniciais:");

        Order order1 = orderService.newOrder(1034, 150.0, 20.0); //Exemplo 1
        Order order2 = orderService.newOrder(2282, 800.0, 10.0); //Exemplo 2
        Order order3 = orderService.newOrder(1309, 95.90, 0.0);  //Exemplo 3

        printOrderSummary(order1);
        printOrderSummary(order2);
        printOrderSummary(order3);
    }

    private void executeUserInputCalculations() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n📢 Digite os valores do pedido para calcular o total (ou '0' para sair):");
            System.out.print("➡ Código do pedido (ou 0 para sair): ");
            int code = scanner.nextInt();

            if (code == 0) {
                System.out.println("\n🚪 Saindo do programa... Obrigado!");
                break;
            }

            System.out.print("➡ Valor básico: ");
            double basic = scanner.nextDouble();

            System.out.print("➡ Desconto (%): ");
            double discount = scanner.nextDouble();

            Order userOrder = orderService.newOrder(code, basic, discount);
            printOrderSummary(userOrder);

            // Opção de continuar ou sair
            System.out.print("\n🔄 Deseja fazer outro cálculo? (S/N): ");
            scanner.nextLine(); // Consumir quebra de linha pendente
            String choice = scanner.nextLine().trim().toUpperCase();

            if (!choice.equals("S")) {
                System.out.println("\n🚪 Saindo do programa... Obrigado!");
                break;
            }
        }

        scanner.close();
    }

    private void printOrderSummary(Order order) {
        double totalWithShipping = orderService.total(order);
        double shippingCost = shippingService.calculateShipping(order);

        System.out.println("\n📦 Pedido #" + order.getCode());
        System.out.println("➡ Valor Básico: R$ " + order.getBasic());
        System.out.println("➡ Desconto Aplicado: R$ " + (order.getBasic() * order.getDiscount() / 100));
        System.out.println("➡ Frete: R$ " + shippingCost);
        System.out.println("✅ Total Final: R$ " + totalWithShipping);
    }
}
