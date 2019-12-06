package cn.caofanqi.study.studyspringdatajpa.pojo.domain;

import cn.caofanqi.study.studyspringdatajpa.pojo.domain.listener.OrderEntityListener;
import cn.caofanqi.study.studyspringdatajpa.pojo.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 *
 * @author caofanqi
 */
@Slf4j
@Getter
@Setter
@Entity
@Builder
@Table(name = "jpa_order")
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(value = OrderEntityListener.class)
public class Order extends AbstractAuditDomain{

    @Column(unique = true)
    private String orderNo;

    @Column(nullable = false)
    private OrderStatus orderStatus;

    @Column(nullable = false)
    private BigDecimal price;


    //其他属性....

    /*
     * 以下方法也可以写在监听类中
     */

//    @PrePersist
//    public void prePersist(){
//        this.setOrderStatus(OrderStatus.NEW);
//        log.info("orderNo: {}，status :{},新增之前修改订单状态为NEW",this.getOrderNo(),this.getOrderStatus());
//    }
//
//    @PostPersist
//    public void postPersist(){
//        log.info("orderNo: {}，status :{},新增之后，异步通知仓库进行处理",this.getOrderNo(),this.getOrderStatus());
//    }
//
//    @PostLoad
//    public void postLoad(){
//        log.info("orderNo: {}，status :{},加载之后...",this.getOrderNo(),this.getOrderStatus());
//    }
//
//    @PreUpdate
//    public void preUpdate(){
//        log.info("orderNo: {}，status :{},修改之前.....",this.getOrderNo(),this.getOrderStatus());
//    }
//
//    @PostUpdate
//    public void postUpdate(){
//        log.info("orderNo: {}，status :{},修改之后根据订单状态进行不同的判断",this.getOrderNo(),this.getOrderStatus());
//    }
//
//    @PreRemove
//    public void preRemove(){
//        log.info("orderNo: {}，status :{},删除之前.....",this.getOrderNo(),this.getOrderStatus());
//    }
//
//
//    @PostRemove
//    public void postRemove(){
//        log.info("orderNo: {}，status :{},删除之后.....",this.getOrderNo(),this.getOrderStatus());
//    }

}
