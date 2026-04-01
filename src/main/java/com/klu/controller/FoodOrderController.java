package com.klu.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.klu.enums.OrderStatus;
import com.klu.model.Employee;
import com.klu.model.FoodOrder;
import com.klu.model.Restaurant;
import com.klu.model.User;
import com.klu.repository.EmployeeRepo;
import com.klu.repository.FoodOrderRepo;
import com.klu.repository.RestaurantRepo;
import com.klu.repository.UserRepo;

@RestController
@RequestMapping("/orders")
@CrossOrigin(origins = "http://localhost:5173")
public class FoodOrderController {

    @Autowired
    private FoodOrderRepo orderRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RestaurantRepo restaurantRepo;

    @Autowired
    private EmployeeRepo employeeRepo;

    @PostMapping("/create")
    public FoodOrder createOrder(
            @RequestParam Long userId,
            @RequestParam Long restaurantId,
            @RequestParam double totalAmount
    ) {
        User user = userRepo.findById(userId).orElseThrow();
        Restaurant restaurant = restaurantRepo.findById(restaurantId).orElseThrow();

        FoodOrder order = new FoodOrder();
        order.setCitizen(user);
        order.setRestaurant(restaurant);
        order.setTotalAmount(totalAmount);

        order.setStatus(OrderStatus.PENDING);

        order.setCreatedAt(LocalDateTime.now());

        return orderRepo.save(order);
    }

    @GetMapping
    public List<FoodOrder> getAllOrders() {
        return orderRepo.findAll();
    }

    @PutMapping("/assign/{id}")
    public FoodOrder assignEmployee(
            @PathVariable Long id,
            @RequestParam int employeeId
    ) {
        FoodOrder order = orderRepo.findById(id).orElseThrow();
        Employee emp = employeeRepo.findById(employeeId).orElseThrow();

        order.setDeliveryEmployee(emp);
        order.setStatus(OrderStatus.ASSIGNED);

        return orderRepo.save(order);
    }

    @GetMapping("/employee/{id}")
    public List<FoodOrder> getEmployeeOrders(@PathVariable int id) {
        return orderRepo.findByDeliveryEmployee_Id(id);
    }

    @PutMapping("/status/{id}")
    public FoodOrder updateStatus(
            @PathVariable Long id,
            @RequestParam String status
    ) {
        FoodOrder order = orderRepo.findById(id).orElseThrow(); 
        OrderStatus newStatus;
        try {
            newStatus = OrderStatus.valueOf(status.toUpperCase());
        } catch (Exception e) {
            throw new RuntimeException("Invalid status");
        }

        order.setStatus(newStatus);

        return orderRepo.save(order);
    }

    @GetMapping("/user/{userId}")
    public List<FoodOrder> getOrdersByUser(@PathVariable Long userId) {
        return orderRepo.findByCitizenId(userId);
    }

    @PostMapping
    public FoodOrder placeOrder(@RequestBody FoodOrder order) {
        order.setStatus(OrderStatus.PENDING);
        order.setCreatedAt(LocalDateTime.now());
        return orderRepo.save(order);
    }
    @PutMapping("/cancel/{id}")
    public ResponseEntity<?> cancelOrder(@PathVariable Long id) {

        FoodOrder order = orderRepo.findById(id).orElse(null);

        if (order == null) {
            return ResponseEntity.status(404).body("Order not found");
        }

        if (order.getStatus() == OrderStatus.ASSIGNED ||
            order.getStatus() == OrderStatus.DELIVERED) {
            return ResponseEntity.badRequest()
                    .body("Cannot cancel after assignment");
        }

        order.setStatus(OrderStatus.CANCELLED);
        orderRepo.save(order);

        return ResponseEntity.ok("Order cancelled");
    }
}