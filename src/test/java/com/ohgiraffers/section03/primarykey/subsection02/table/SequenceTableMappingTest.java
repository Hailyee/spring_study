package com.ohgiraffers.section03.primarykey.subsection02.table;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;

public class SequenceTableMappingTest {
    private static EntityManagerFactory entityManagerFactory;

    private EntityManager entityManager;

    @BeforeAll
    public static void initFactory() {
        entityManagerFactory = Persistence.createEntityManagerFactory("jpatest");
    }

    @BeforeEach
    public void initManager() {
        entityManager = entityManagerFactory.createEntityManager();
    }

    @AfterAll
    public static void closeFactory() {
        entityManagerFactory.close();
    }

    @AfterEach
    public void closeManager() {
        entityManager.close();
    }

    @Test
    public void 식별자_매핑_테흐트() {
        Member member = new Member();

        //given
        com.ohgiraffers.section01.entity.Member member= new com.ohgiraffers.section01.entity.Member();
       // member.setMemberNo(1);
        member.setMemberId("user01");
        member.setMemberPwd("pass01");
        member.setNickname("홍길동");
        member.setPhone("010-1234-4567");
        member.setEmail("hong@gmail.com");

        //given
        Member member2 = new Member();

        //  member.setMemberNo(1);
        member2.setMemberId("user02");
        member2.setMemberPwd("pass02");
        member2.setNickname("유관순");
        member2.setPhone("010-1234-4567");
        member2.setEmail("hong@gmail.com");

        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(member);
        entityManager.persist(member2);
        transaction.commit();

        //설명. persist 당시에는 부여되지 않은 pk로 commit 이후 조회를 하면 가능할까?
        Member selectedMember = entityManager.find(Member.class, 1);
        System.out.println("selectedMember = " + selectedMember);

        Assertions.assertEquals(2,selectedMember.getMemberNo());

        //설명. 다중행 조회는 find로는 안되고 jsql이라는 문법을 활용





    }
}
