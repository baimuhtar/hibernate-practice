package baimuhtar.catalog.controller;

import baimuhtar.catalog.entity.Category;
import baimuhtar.catalog.entity.Product;
import baimuhtar.catalog.entity.Value;
import org.hibernate.collection.internal.PersistentArrayHolder;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class DeleteProduct {

    public static void deleteProduct () {

    }
    static EntityManagerFactory factory;

    static {
        LogManager logManager = LogManager.getLogManager();
        Logger logger = logManager.getLogger("");
        logger.setLevel(Level.OFF);
        factory = Persistence.createEntityManagerFactory("main");
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите id товара для удаления");
        long product_id = Long.parseLong(scanner.nextLine());

        EntityManager manager = factory.createEntityManager();

        try {
            manager.getTransaction().begin();

            Product product = manager.find(Product.class, product_id);

            manager.remove(product);

            manager.getTransaction().commit();

        } catch (Exception e) {
            manager.getTransaction().rollback();
            e.printStackTrace();

        }
    }
}