package com.ibanfr.hibernate.manager;

import com.ibanfr.hibernate.connection.HibernateUtil;
import com.ibanfr.hibernate.model.Passport;
import com.ibanfr.hibernate.model.Person;
import com.ibanfr.hibernate.model.Team;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.PersistenceException;
import java.util.Arrays;

/**
 * Test class including test cases for common hibernate ORM scenarios.
 */

public class HibernateIT {



    /**
     * Logical transaction in the scope of the IT test
     */
    private Transaction tx;

    private Session session;

    @BeforeEach
    public void setUp() {
        session = HibernateUtil.getSessionFactory()
                               .getCurrentSession();
        tx = session.beginTransaction();
    }

    @AfterEach
    public void tearDown() {
        if (tx != null && tx.isActive())
            tx.rollback();
    }

    /**
     * Test to save an entity containing a uni-directional association with CASCADE=ALL.
     * <p>
     * See output of the test for the launched SQL queries by Hibernate.
     */
    @Test
    public void testSave(){

        Person u1 = new Person(new Passport("Ivan"));
        Person u2 = new Person(new Passport("Lech"));
        Person u3 = new Person(new Passport("David"));

        Team team = new Team();
        team.setName("Team Awesome");
        team.setMembers(Arrays.asList(u1, u2, u3));

        try {

            session.save(team);

        } catch (PersistenceException pe) {
            System.out.println(pe);
            if (null != tx)
                tx.rollback();
            throw pe;
        }
    }

    @Test
    public void testFind(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {

            Team teamAwesome =  session.get(Team.class,Long.valueOf(2));

            System.out.println(teamAwesome);

        } catch (PersistenceException pe) {
            System.out.println(pe);
            if (null != tx)
                tx.rollback();
            throw pe;
        }

    }


}
