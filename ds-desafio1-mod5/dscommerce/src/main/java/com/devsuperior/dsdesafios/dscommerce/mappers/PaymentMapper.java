package com.devsuperior.dsdesafios.dscommerce.mappers;

import com.devsuperior.dsdesafios.dscommerce.dto.PaymentDTO;
import com.devsuperior.dsdesafios.dscommerce.entities.Payment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {OrderMapper.class})
public interface PaymentMapper extends BeanMapper<Payment, PaymentDTO> {
    
}
