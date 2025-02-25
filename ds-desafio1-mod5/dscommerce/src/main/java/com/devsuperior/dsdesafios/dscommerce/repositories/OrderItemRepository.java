package com.devsuperior.dsdesafios.dscommerce.repositories;


import com.devsuperior.dsdesafios.dscommerce.entities.OrderItem;
import com.devsuperior.dsdesafios.dscommerce.entities.OrderItemPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemPK> {

}
