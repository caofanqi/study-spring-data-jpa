package cn.caofanqi.study.studyspringdatajpa.pojo.domain.listener;

import cn.caofanqi.study.studyspringdatajpa.pojo.domain.Order;
import cn.caofanqi.study.studyspringdatajpa.pojo.enums.OrderStatus;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

/**
 * 订单实体监听类
 * @author  caofanqi
 */
@Slf4j
public class OrderEntityListener {


    @PrePersist
    public void prePersist(Order order){
        order.setOrderStatus(OrderStatus.NEW);
        log.info("orderNo: {}，status :{},新增之前修改订单状态为NEW",order.getOrderNo(),order.getOrderStatus());
    }

    @PostPersist
    public void postPersist(Order order){
        log.info("orderNo: {}，status :{},新增之后，异步通知厂库进行处理",order.getOrderNo(),order.getOrderStatus());
    }

    @PostLoad
    public void postLoad(Order order){
        log.info("orderNo: {}，status :{},加载之后...",order.getOrderNo(),order.getOrderStatus());
    }

    @PreUpdate
    public void preUpdate(Order order){
        log.info("orderNo: {}，status :{},修改之前.....",order.getOrderNo(),order.getOrderStatus());
    }

    @PostUpdate
    public void postUpdate(Order order){
        log.info("orderNo: {}，status :{},修改之后根据订单状态进行不同的判断",order.getOrderNo(),order.getOrderStatus());
    }

    @PreRemove
    public void preRemove(Order order){
        log.info("orderNo: {}，status :{},删除之前.....",order.getOrderNo(),order.getOrderStatus());
    }


    @PostRemove
    public void postRemove(Order order){
        log.info("orderNo: {}，status :{},删除之后.....",order.getOrderNo(),order.getOrderStatus());
    }

}
