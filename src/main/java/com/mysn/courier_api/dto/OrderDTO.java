package com.mysn.courier_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {

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
