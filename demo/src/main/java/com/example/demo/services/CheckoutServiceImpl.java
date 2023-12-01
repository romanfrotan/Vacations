package com.example.demo.services;

import com.example.demo.dao.CartRepository;
import com.example.demo.dao.CustomerRepository;
import com.example.demo.dto.Purchase;
import com.example.demo.dto.PurchaseResponse;
import com.example.demo.entities.Cart;
import com.example.demo.entities.CartItem;
import com.example.demo.entities.Customer;
import com.example.demo.entities.StatusType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;


@Service
public class CheckoutServiceImpl implements CheckoutService{

    private CustomerRepository customerRepository;
    private CartRepository cartRepository;

    @Autowired
    public CheckoutServiceImpl(CustomerRepository customerRepository,CartRepository cartRepository) {

        this.customerRepository=customerRepository;
        this.cartRepository=cartRepository;
    }


    @Override
    public PurchaseResponse placeOrder(Purchase purchase) {

        Cart cart= purchase.getCart();
        Customer customer= purchase.getCustomer();


        //tracking number
         String orderTrackingNumber = generateOrderTrackingNumber();
         cart.setOrderTrackingNumber((orderTrackingNumber));

         //fill cart with cart items

        Set<CartItem> cartItem =purchase.getCartItems();
        cartItem.forEach(item->cart.add(item));

        //populate cart with customer and cart item

        cart.setCartItem(purchase.getCartItems());
        cart.setCustomer(purchase.getCustomer());

        //populate customer with cart

        customer.add(cart);

        //save to db
        cart.setStatus(StatusType.ordered);
        customerRepository.save(customer);
        cartRepository.save(cart);





        return new PurchaseResponse(orderTrackingNumber);
    }

    private String generateOrderTrackingNumber() {

    return UUID.randomUUID().toString();
    }
}
