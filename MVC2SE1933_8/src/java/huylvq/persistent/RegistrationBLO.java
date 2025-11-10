/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package huylvq.persistent;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author hanly
 */
public class RegistrationBLO {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAPU");

    public void persist(Object object) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    public Registration checkLogin(String username, String password) {
        Registration result = null;
        EntityManager em = emf.createEntityManager();
        String jpql = "Select r "//truy vấn trên bộ nhớ
                + "From Registration r "
                + "Where r.username = :username "
                + "AND r.password = :password"; //:tham số, : rồi truyền tham số vào

        try {
            Query query = em.createQuery(jpql);
            query.setParameter("username", username);
            query.setParameter("password", password);
            result = (Registration) query.getSingleResult(); // chỉ trả về 1 dòng thì dùng single
        } finally {
            this.close();
        }
        return result;
    }

    public void close() {
        if (this.emf != null) {
            if (this.emf.isOpen()) {
                this.emf.close();
            }
        }
    }

    public List<Registration> searchLastname(String searchValue) {
        EntityManager em = this.emf.createEntityManager();
        List<Registration> result = null;
        String jpql = "Select r "
                + "From Registration r "
                + "Where r.lastname like :lastname";
        try {
            Query query = em.createQuery(jpql);
            query.setParameter("lastname", "%" + searchValue + "%");

            result = query.getResultList(); // lấy hết, nhiều dòng
        } finally {
            this.close();
        }

        return result;
    }

    public boolean delete(String username) {
        EntityManager em = this.emf.createEntityManager();
        boolean result = false;
        try {
            Registration tmp = em.find(Registration.class, username);
            if (tmp != null) {
                em.getTransaction().begin();
                em.remove(tmp);
                em.getTransaction().commit();
                result = true;
            }
        } finally {
            em.close();
        }
        return result;
    }

    public boolean updatePassRole(String username, String password, boolean role) {
        EntityManager em = this.emf.createEntityManager();
        boolean result = false;
        try {
            Registration tmp = em.find(Registration.class, username);
            if (tmp != null) {
                tmp.setPassword(password);
                tmp.setIsAdmin(role);
                em.getTransaction().begin();
                em.merge(tmp);
                em.getTransaction().commit();
                result = true;
            }
        } finally {
            em.close();
        }
        return result;
    }

    public boolean create(Registration x) {
        EntityManager em = this.emf.createEntityManager();
        boolean result = false;
        try {
            Registration tmp = em.find(Registration.class, x.getUsername());
            if (tmp != null) {
               this.persist(x);
                result = true;
            }
        } finally {
            em.close();
        }
        return result;
    }
}
