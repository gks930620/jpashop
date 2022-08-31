package jpabook.jpashop;


import jpabook.jpashop.domain.*;
import jpabook.jpashop.domain.item.Book;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

@Component
@RequiredArgsConstructor
public class InitDb {

    private final InitService initService;


    @PostConstruct
    public void init(){
        initService.dbInit1();  //Trasaction때문에 이렇게 service 따로 만든거

    }
    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService{
        private final EntityManager em;
        public void dbInit1(){
          Member member=createMember("userA","서울","1","1111");
            member.setName("userA");
            member.setAddress(new Address("서울","1","1111"));
            em.persist(member);

            Book book1=new Book();
            book1.setName("JPA1 BOOK");
            book1.setPrice(10000);
            book1.setStockQuantity(100);
            em.persist(book1);

            Book book2=new Book();
            book2.setName("JPA2 BOOK");
            book2.setPrice(20000);
            book2.setStockQuantity(100);
            em.persist(book2);

            OrderItem orderItem1 = OrderItem.createOrderItem(book1, 10000, 1);
            OrderItem orderItem2 = OrderItem.createOrderItem(book2, 20000, 2);

            Delivery delivery=new Delivery();
            delivery.setAddress(member.getAddress());
            Order order=Order.createOrder(member,delivery,orderItem1,orderItem2);
            em.persist(order);


            //이렇게 1일 1 커밋해도 안되는거야?
        }

    private Member createMember(String userA, String 서울 , String s, String s2){
            Member member=new Member();
            member.setName(userA);
            member.setAddress(new Address(서울,s,s2));
            return member;
    }


    }
}


