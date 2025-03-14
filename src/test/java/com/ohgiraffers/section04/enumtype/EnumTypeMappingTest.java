package com.ohgiraffers.section04.enumtype;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;

public class EnumTypeMappingTest {

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
    public void enum타입_매핑_테스트() {
        Member member = new Member();

        member.setMemberId("user01");
        member.setMemberPwd("pass01");
        member.setMemberRole(RoleType.ROLE_MEMBER);

        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        entityManager.persist(member);

        transaction.commit();

        Member foundMember = entityManager.find(Member.class, member.getMemberNo());
        System.out.println("foundMember = " + foundMember);
        Assertions.assertEquals(member.getMemberNo(), foundMember.getMemberNo());

    }
}
