package cn.caofanqi.study.studyspringdatajpa.pojo.enums;


/**
 * 订单状态
 * @author caofanqi
 */
public enum  OrderStatus {

    /**
     * 新订单，未付款
     */
    NEW,
    /**
     * 已付款
     */
    PAY,
    /**
     *已发货
     */
    SEND,
    /**
     * 完成
     */
    SUCCESS,
    /**
     * 退货
     */
    RETURN,
    /**
     * 已退款
     */
    REFUND,
    /**
     * 已取消
     */
    CANCEL


}
