package cn.caofanqi.study.studyspringdatajpa.repository;

import cn.caofanqi.study.studyspringdatajpa.pojo.domain.Order;
import cn.caofanqi.study.studyspringdatajpa.pojo.enums.OrderStatus;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrderRepositoryTest {

    @Resource
    private OrderRepository orderRepository;

    @Test
    void testSave(){
        Order order = Order.builder().orderNo("123456").price(BigDecimal.valueOf(123.45)).build();
        orderRepository.save(order);
    }

    @Test
    void testFindAndUpdate(){
        Order order = orderRepository.findByOrderNo("123456");
        order.setOrderStatus(OrderStatus.PAY);
        orderRepository.save(order);
    }

    @Test
    void testFindAndDelete(){
        Order order = orderRepository.findByOrderNo("123456");
        orderRepository.delete(order);
    }

}