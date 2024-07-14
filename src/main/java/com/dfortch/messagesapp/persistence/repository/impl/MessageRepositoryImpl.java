package com.dfortch.messagesapp.persistence.repository.impl;

import java.util.List;
import java.util.Optional;

import com.dfortch.messagesapp.persistence.entity.Message;
import com.dfortch.messagesapp.persistence.repository.MessageRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

public class MessageRepositoryImpl extends AbstractRepository<Message, Long> implements MessageRepository {

    @Override
    public List<Message> findAll() {
        EntityManager em = getEntityManager();
        TypedQuery<Message> query = em.createQuery("SELECT m FROM Message m", Message.class);
        List<Message> result = query.getResultList();
        em.close();
        return result;
    }

    @Override
    public Optional<Message> findById(Long id) {
        EntityManager em = getEntityManager();

        Message message = em.find(Message.class, id);
        em.close();
        return Optional.ofNullable(message);
    }

    @Override
    public Message save(Message entity) {
        EntityManager em = getEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            if (entity.getId() == null) {
                em.persist(entity);
            } else {
                em.merge(entity);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        } finally {
            em.close();
        }

        return entity;
    }

    @Override
    public void delete(Long id) {
        EntityManager em = getEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            Message entity = em.find(Message.class, id);
            if (entity != null) {
                em.remove(entity);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }
    
}
