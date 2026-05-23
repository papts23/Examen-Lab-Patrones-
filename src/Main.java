import adapter.ExternalPayPalService;
import adapter.PayPalAdapter;
import adapter.PaymentProcessor;
import model.Cart;
import model.Product;
import observer.AdminNotificationObserver;
import observer.EmailNotificationObserver;
import observer.InventoryObserver;
import service.OrderService;
import strategy.PercentageDiscountStrategy;
import strategy.FixedAmountDiscountStrategy;

public class Main {
    public static void main(String[] args) {

        OrderService orderService = new OrderService();
        orderService.addObserver(new EmailNotificationObserver());
        orderService.addObserver(new InventoryObserver());
        orderService.addObserver(new AdminNotificationObserver());

        ExternalPayPalService externalService = new ExternalPayPalService();
        PaymentProcessor payPalAdapter = new PayPalAdapter(externalService);

        System.out.println("=== EJECUCIÓN CASO 1: EJEMPLO DE LA GUÍA ===");

        Product laptop = new Product("Laptop Básica", 100.00);
        Product mouse = new Product("Mouse", 33.34);

        Cart cart1 = new Cart();
        cart1.addProduct(laptop);
        cart1.addProduct(mouse);

        orderService.setDiscountStrategy(new PercentageDiscountStrategy(0.10));

        orderService.confirmOrder(cart1, payPalAdapter);

        System.out.println("\n---------------------------------------------------\n");

        System.out.println("=== EJECUCIÓN CASO 2: COMPRA CON MÁS PRODUCTOS ===");


        Product teclado = new Product("Teclado Mecánico", 150.00);
        Product audifonos = new Product("Audífonos Gamer", 80.00);
        Product pad = new Product("Mouse Pad XL", 40.00);
        Product cable = new Product("Cable HDMI", 15.00);

        Cart cart2 = new Cart();
        cart2.addProduct(teclado);
        cart2.addProduct(audifonos);
        cart2.addProduct(pad);
        cart2.addProduct(cable);

        orderService.setDiscountStrategy(new FixedAmountDiscountStrategy(20.00));

        orderService.confirmOrder(cart2, payPalAdapter);
    }
}