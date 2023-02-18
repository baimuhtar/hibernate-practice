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

public class DeleteProduct {
    public static void main(String[] args) {
        deleteProduct();
    }

    public static void deleteProduct() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите id товара для удаления");
        long product_id = Long.parseLong(scanner.nextLine());


        EntityManagerFactory factory = Persistence.createEntityManagerFactory("main");
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