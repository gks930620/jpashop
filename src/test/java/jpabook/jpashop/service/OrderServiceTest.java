package jpabook.jpashop.service;


import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderStatus;
import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.OrderRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class OrderServiceTest {

    @Autowired
    EntityManager em;
    @Autowired
    OrderService orderService;

    @Autowired
    OrderRepository orderRepository;
    @Test
    public void 상품주문 () throws Exception {
        //given
        Member member= new Member();
        member.setName("회원1");
        member.setAddress(new Address("서울","강가","123-123"));
        em.persist(member);

        Book book= new Book();
        book.setName("시골 JPA");
        book.setPrice(10000);
        book.setStockQuantity(10);
        em.persist(book);
        int orderCount=2;

        //when
        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);
        //then
        Order getOrder = orderRepository.findOne(orderId);

        Assert.assertEquals("상품 주문시 상태는 order" , OrderStatus.ORDER,getOrder.getStatus());
        Assert.assertEquals("주문한 상품 종류수가 정확해야 ", 1 , getOrder.getOrderItems().size());
        Assert.assertEquals("주문가격은  가격 * 수량이다 ", 10000*orderCount , getOrder.getTotalPrice());
        



    }
    @Test
    public void 주문취소 () throws Exception {
        //given

        //when

        //then
    }
    @Test
    public void  상품주문_재고수량초과() throws Exception {
        //given

        //when

        //then
    }
}
