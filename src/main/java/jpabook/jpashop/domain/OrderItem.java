package jpabook.jpashop.domain;

import jpabook.jpashop.domain.item.Item;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter@Setter
public class OrderItem {

    @Id@GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;



    private int orderPrice;
    private int count;


    //생성메서드
    public static OrderItem createOrderItem(Item item, int orderPrice, int count){ // orderPrice가 할인 된 가격일 수도 있으니까 매개변수에 추가
        OrderItem orderItem=new OrderItem();
        orderItem.setItem(item);
        orderItem.setOrderPrice(orderPrice);
        orderItem.setCount(count);
        item.removeStock(count);
        return orderItem;
    }

    //주문취소
    public void cancel() {
        getItem().addStock(count);
    }

    public int getTotalPrice(){
        return getOrderPrice()*getCount();
    }

}
