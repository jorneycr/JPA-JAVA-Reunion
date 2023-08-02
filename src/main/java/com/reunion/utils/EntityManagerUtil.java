package com.reunion.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerUtil{
    public static EntityManager getEntityManager(){
        EntityManagerFactory factory = null;
        EntityManager manager = null;
        try {
            factory = Persistence.createEntityManagerFactory("Reunion");
            manager = factory.createEntityManager();
        } catch (Exception e) {
            // Handle the exception appropriately, such as logging or rethrowing.
            e.printStackTrace();
        }
        return manager;
    }

    public static void main(String[] args) {
        EntityManager manager = EntityManagerUtil.getEntityManager();
        if (manager != null) {
            System.out.println("EntityManager class ===> " + manager.getClass().getCanonicalName());
            // Perform database operations using the EntityManager.
            // ...
            // Close the EntityManager when done.
            manager.close();
        }
    }
}
