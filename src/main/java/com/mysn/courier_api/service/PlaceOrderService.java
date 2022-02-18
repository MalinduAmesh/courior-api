package com.mysn.courier_api.service;

import com.mysn.courier_api.dto.OrderDTO;

import java.util.List;

public interface PlaceOrderService {

    boolean placeOrder(OrderDTO orderDTO) throws Exception;

    boolean updateOrder(OrderDTO orderDTO) throws Exception;

    boolean deletePlaceOrder(Long orderId) throws Exception;

    List<OrderDTO> getAllOrder();
}
