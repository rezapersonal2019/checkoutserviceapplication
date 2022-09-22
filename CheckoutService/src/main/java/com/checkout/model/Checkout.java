package com.checkout.model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Checkout {
    @NotNull(message = "Order Id is required")
   // @Min(value = 101, message = "Order Id can not be less than 101.")
   // @Max(value = 1000, message = "Order Id can not be more than 10000")
    @Size(min = 101, message = "Name can not be less 2 character")
    private int orderId;
    @NotNull(message = "Sub Total is required")
    @Min(value = (long) 0.0, message = "Sub total amount can not be less than 0.0.")
    private double subTotal;
    @NotNull(message = "Product tax amount is required")
    @Min(value = (long) 0.0, message = "Product tax amount can not be less than 0.0.")
    private double tax;
    @NotNull(message = "Product Total is required")
    @Min(value = (long) 0.0, message = "Product total amount can not be less than 0.0.")
    private double total;
}
