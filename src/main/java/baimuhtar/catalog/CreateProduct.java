package baimuhtar.catalog;

import baimuhtar.catalog.entity.Category;
import baimuhtar.catalog.entity.Option;
import baimuhtar.catalog.entity.Product;
import baimuhtar.catalog.entity.Value;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CreateProduct {
    public static void main(String[] args) {
        createProduct();
    }

    public static void createProduct() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите id категории товара");
        long category_id = Long.parseLong(scanner.nextLine());
        System.out.println("Введите название товара");
        String product_name = scanner.nextLine();
        System.out.println("Введите цену на товар");
        int product_price = scanner.nextInt();

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("main");
        EntityManager manager = factory.createEntityManager();

        try {
            manager.getTransaction().begin();

            Category category = manager.find(Category.class, category_id);


            String [] products = product_name.split(", ");

            for (int i = 0; i < products.length; i++) {
                Product product = new Product();
                product.setCategory(category);
                product.setName(products[i]);
                product.setPrice(product_price);
                manager.persist(product);
            }

            manager.getTransaction().commit();
        } catch (Exception e) {
            manager.getTransaction().rollback();
            e.printStackTrace();
        }

    }
}
