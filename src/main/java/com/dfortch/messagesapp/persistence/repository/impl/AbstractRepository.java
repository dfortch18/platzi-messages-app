package com.dfortch.messagesapp.persistence.repository.impl;

import com.dfortch.messagesapp.persistence.repository.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public abstract class AbstractRepository<T, ID> implements Repository<T, ID> {

    protected static final EntityManagerFactory entityManagerFactory = buildEntityManagerFactory();

    protected static EntityManagerFactory buildEntityManagerFactory() {
        return Persistence.createEntityManagerFactory("messagesAppPersistenceUnit");
    }

    protected static EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }
}
