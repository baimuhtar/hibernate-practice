package baimuhtar.catalog.controller;

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

    static EntityManagerFactory factory = Persistence.createEntityManagerFactory("main");
    static EntityManager manager = factory.createEntityManager();

    static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        try {
            manager.getTransaction().begin();

            System.out.println("Введите id категории товара");
            long category_id = Long.parseLong(scanner.nextLine());

            System.out.println("Введите название товара");
            String product_name = scanner.nextLine();

            System.out.println("Введите цену на товар");
            int product_price = scanner.nextInt();

            Category category = manager.find(Category.class, category_id);

            List<Option> optionList = category.getOptions();

            Product product = new Product();
            product.setCategory(category);
            product.setName(product_name);
            product.setPrice(product_price);
            manager.persist(product);

            for (Option option : optionList) {

                System.out.printf("%s: ", option.getName());
                String value_name = scanner.nextLine();

                Value value = new Value();
                value.setProduct(product);
                value.setOption(option);
                value.setValue(value_name);
                manager.persist(value);
            }

            manager.getTransaction().commit();
        } catch (Exception e) {
            manager.getTransaction().rollback();
            e.printStackTrace();
        }
    }
}