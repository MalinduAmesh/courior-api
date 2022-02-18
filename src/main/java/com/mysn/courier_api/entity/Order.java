package com.mysn.courier_api.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String senderName;
    private String senderAddress;
    private String receiverName;
    private String receiverAddress;
    private String parcelWeight;
    private Long courierCompanyCode;
    private String parcelType;
    private Long userId;

}
