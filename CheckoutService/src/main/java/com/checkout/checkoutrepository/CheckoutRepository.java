package com.checkout.checkoutrepository;
import com.checkout.model.Checkout;
import java.util.List;

public interface CheckoutRepository {

    Checkout saveCheckoutData(Checkout checkout);
    Checkout updateCheckoutData( Checkout checkout);
    List< Checkout> allCheckout();
    Checkout getByOrderId(int orderId);
    String deleteCheckoutData(int orderId);


}
