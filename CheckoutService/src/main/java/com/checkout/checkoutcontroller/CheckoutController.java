package com.checkout.checkoutcontroller;
import com.checkout.checkoutservice.CheckoutService;
import com.checkout.exception.idnotavailable.OrderIdNotAvailable;
import com.checkout.model.Checkout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import java.util.List;
//http://local:9091
@RestController
public class CheckoutController {
    @Autowired
    private CheckoutService checkoutService;
    @PostMapping("/addData")
    public Checkout addCheckoutData(@Valid @RequestBody Checkout checkout) {
        return checkoutService.saveCheckoutData(checkout);
    }
    @PutMapping("/update")
    public Checkout updateCheckoutData(@RequestBody Checkout checkout) {
        return checkoutService.updateCheckoutData(checkout);
    }
    @GetMapping("/checkout/{orderId}")
    public Checkout getData(@PathVariable int orderId) throws OrderIdNotAvailable {
        return checkoutService.getByOrderId(orderId);
    }
    @GetMapping("/checkouts")
    public List<Checkout> getCheckoutData() {
        return checkoutService.allCheckout();
    }
    @DeleteMapping("/checkoutData/{orderId}")
    public String deleteCheckoutData(@PathVariable("orderId") int orderId){
        return checkoutService.deleteCheckoutData(orderId);
    }

}
