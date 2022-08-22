package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter@Setter
public class Member {

    @Id@GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String username;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "member") //Order 클래스의 member 변수가 값이 변경이 되는 연관관계 주인
    private List<ORDER> orders=new ArrayList<>();




}
