package service;

import adapter.PaymentProcessor;
import model.Cart;
import observer.OrderObserver;
import strategy.DiscountStrategy;
import java.util.ArrayList;
import java.util.List;

public class OrderService {
    private List<OrderObserver> observers;
    private DiscountStrategy discountStrategy;

    public OrderService() {
        this.observers = new ArrayList<>();
    }

    public void addObserver(OrderObserver observer) {
        this.observers.add(observer);
    }

    public void notifyObservers(String message) {
        for (OrderObserver observer : observers) {
            observer.update(message);
        }
    }

    public void setDiscountStrategy(DiscountStrategy discountStrategy) {
        this.discountStrategy = discountStrategy;
    }

    public void confirmOrder(Cart cart, PaymentProcessor paymentProcessor) {

        double total = cart.calculateTotal();


        double totalConDescuento = discountStrategy.applyDiscount(total);

        System.out.println("Compra confirmada por S/ " + String.format("%.2f", totalConDescuento));

        paymentProcessor.pay(totalConDescuento);

        notifyObservers(String.format("S/ %.2f", totalConDescuento));
    }
}