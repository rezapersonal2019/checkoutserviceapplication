package com.checkout.checkoutservice;
import com.checkout.exception.idnotavailable.OrderIdNotAvailable;
import com.checkout.model.Checkout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import java.util.List;
  @Service
public class CheckoutService {
    private static final String INSERT_USER_QUERY = "INSERT INTO CHECKOUT(orderId,subTotal,tax,total) values(?,?,?,?)";
    private static final String UPDATE_USER_BY_ID_QUERY = "UPDATE CHECKOUT SET sutTotal=? WHERE orderId=?";
    private static final String GET_USER_BY_ID_QUERY = "SELECT * FROM CHECKOUT WHERE orderId=?";
    private static final String DELETE_USER_BY_ID = "DELETE FROM CHECKOUT WHERE orderId=?";
    private static final String GET_USERS_QUERY = "SELECT * FROM CHECKOUT";
    @Autowired
    private JdbcTemplate jdbcTemplate;
   public Checkout saveCheckoutData (Checkout checkout){
       jdbcTemplate.update(INSERT_USER_QUERY, checkout.getOrderId(),checkout.getSubTotal(),
               checkout.getTax(),checkout.getTotal());
       return checkout;
   }
    public Checkout updateCheckoutData ( Checkout checkout) {
    jdbcTemplate.update(UPDATE_USER_BY_ID_QUERY,checkout.getSubTotal(),checkout.getOrderId());
    return checkout;
  }
  public Checkout getByOrderId(int orderId)throws OrderIdNotAvailable{

      Checkout checkout = jdbcTemplate.queryForObject(GET_USER_BY_ID_QUERY,(rs,rowNum) -> {
          return new Checkout(rs.getInt("orderId"), rs.getDouble("subTotal"),
                  rs.getDouble("tax"), rs.getDouble("total"));
      },orderId) ;
      if(checkout != null){
              return checkout;
           }
           else {
             throw new OrderIdNotAvailable("User could not find product with this Id: " + orderId);
           }
      //Throw(() -> new OrderIdNotAvailable(String.format("Checkout with order id %s does not exit",orderId)));
      // return jdbcTemplate.queryForObject(GET_USER_BY_ID_QUERY,(rs,rowNum) -> {
         //  return new Checkout(rs.getInt("orderId"), rs.getDouble("subTotal"),
             //   rs.getDouble("tax"), rs.getDouble("total"));
    //   },orderId);
    //  return repository.findById(id)
          //    .orElseThrow(() -> new ProductNotAvailable(String.format("Product with id %s does not exit",id)));
}
  public List< Checkout> allCheckout(){
       return jdbcTemplate.query(GET_USERS_QUERY, (rs, rowNum) ->  {
           return new Checkout(rs.getInt("orderId"),  rs.getDouble("subTotal"),
                   rs.getDouble("tax"), rs.getDouble("total"));
    });}
    public  String deleteCheckoutData (int orderId){
       jdbcTemplate.update(DELETE_USER_BY_ID, orderId);
       return "User got deleted with id " + orderId;
}}
