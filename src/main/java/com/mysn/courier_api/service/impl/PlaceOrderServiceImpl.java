package com.mysn.courier_api.service.impl;

import com.mysn.courier_api.dto.OrderDTO;
import com.mysn.courier_api.entity.Courier;
import com.mysn.courier_api.entity.Order;
import com.mysn.courier_api.repository.CourierRepository;
import com.mysn.courier_api.repository.OrderRepository;
import com.mysn.courier_api.service.PlaceOrderService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.BeanUtils;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlaceOrderServiceImpl implements PlaceOrderService {

    private final OrderRepository orderRepository;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ModelMapper modelMapper;
    private final EmailSenderServiceImpl emailSenderService;
    private final CourierRepository courierRepository;

    @Override
    public boolean placeOrder(OrderDTO orderDTO) throws Exception {

        if (StringUtils.isNoneBlank(orderDTO.getReceiverAddress(),orderDTO.getSenderAddress(),orderDTO.getParcelType(), orderDTO.getReceiverName(),orderDTO.getSenderName())){
            Order order = new Order();
            BeanUtils.copyProperties(orderDTO, order);
            orderRepository.save(order);

            Optional<Courier> byId = courierRepository.findById(orderDTO.getCourierCompanyCode());
            if (byId.isPresent()) {
                Courier courier3 = byId.get();

                emailSenderService.sendSimpleEmail(courier3.getEmail(),courier3.getName(),orderDTO.getSenderName(),orderDTO.getSenderAddress(),orderDTO.getReceiverName(),orderDTO.getReceiverAddress());
            }
            kafkaTemplate.send("courierService", order.getReceiverName());

        }else {
            return false;
        }
        return true;
    }

    @Override
    public boolean updateOrder(OrderDTO orderDTO) throws Exception {

        boolean existsById = orderRepository.existsById(orderDTO.getId());
        if (existsById) {
            Order order = new Order();
            BeanUtils.copyProperties(orderDTO, order);
            orderRepository.save(order);
        } else {
            return false;
        }
        return true;
    }

    @Override
    public boolean deletePlaceOrder(Long orderId) {
        if (null != orderId){
            orderRepository.deleteById(orderId);
        }else {
            return false;
        }
        return true;
    }

    @Override
    public List<OrderDTO> getAllOrder() {
        List<Order> all = orderRepository.findAll();
        return modelMapper.map(all,new TypeToken<ArrayList<OrderDTO>>(){}.getType());
    }

}
