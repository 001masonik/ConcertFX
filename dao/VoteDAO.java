package org.example.concertjavafx.dao;

import org.example.concertjavafx.HibernateUtil;
import org.example.concertjavafx.entity.Song;
import org.example.concertjavafx.entity.User;
import org.example.concertjavafx.entity.Vote;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class VoteDAO {

    public void save(Vote vote) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.save(vote);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    public List<Vote> findByUser(User user) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Vote WHERE user = :user", Vote.class)
                    .setParameter("user", user)
                    .list();
        }
    }

    public long countVotesForSong(Song song) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return (Long) session.createQuery("SELECT COUNT(v) FROM Vote v WHERE v.song = :song")
                    .setParameter("song", song)
                    .uniqueResult();
        }
    }
}