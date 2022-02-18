package com.mysn.courier_api.controller;

import com.mysn.courier_api.dto.OrderDTO;
import com.mysn.courier_api.service.PlaceOrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("api/v1/placeOrder")
public class PlaceOrderApi {

    private final PlaceOrderService placeOrderService;

    @PostMapping("/create")
    public ResponseEntity<?> placeOrder(@RequestBody OrderDTO orderDTO) {
        try {
            boolean placeOrder = placeOrderService.placeOrder(orderDTO);
            if (placeOrder){
                return ResponseEntity.ok("Post");
            }else {
                return ResponseEntity.status(500).body("Internal Server Error");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Bad request");
        }
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateOrder(@RequestBody OrderDTO orderDTO) {
        try {
          boolean updateOrder =  placeOrderService.updateOrder(orderDTO);
          if (updateOrder){
              return ResponseEntity.ok("Update");
          }else {
              return ResponseEntity.status(500).body("Internal Server Error");
          }
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Bad request");
        }
    }

    @GetMapping("/get")
    public ResponseEntity<?> getOrder() {
        try {
            List<OrderDTO> allOrders=  placeOrderService.getAllOrder();
            return ResponseEntity.ok(allOrders);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Bad request");
        }
    }

    @DeleteMapping("/delete/{orderId}")
    public String deleteOrder(@PathVariable Long orderId) {
//        placeOrderService.deletePlaceOrder(orderId);
        return "Delete";
    }

}
