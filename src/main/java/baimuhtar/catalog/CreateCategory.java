package baimuhtar.catalog;

import baimuhtar.catalog.entity.Category;
import baimuhtar.catalog.entity.Option;
import baimuhtar.catalog.entity.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.net.SecureCacheResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CreateCategory {
    public static void main(String[] args) {
        createCategoryWithOptions();
    }
    // Введите название категории...

    // Реализовать создание новой категории в соответствующей  таблице
    // с названием введенным пользователем через консоль.

    public static void createCategoryWithOptions() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите название категории: ");
        String category_name = scanner.nextLine();
        System.out.println("Введите название характеристики (через запятую): ");
        String option_name = scanner.nextLine();


        EntityManagerFactory factory = Persistence.createEntityManagerFactory("main");
        EntityManager manager = factory.createEntityManager();


        try {
            TypedQuery<Category> findCategoryIfExist = manager.createQuery("select c.name from Category c", Category.class);
            manager.getTransaction().begin();
            Category category = new Category();
            List<Category> categoryList = findCategoryIfExist.getResultList();

            for (int i = 0; i < categoryList.size(); i++) {
                categoryList.add(category);
               manager.persist(category);
            }
            String[] options = option_name.split(", ");

            for (int i = 0; i < options.length; i++) {
                Option option = new Option();
                option.setCategory(category);
                option.setName(options[i]);
                manager.persist(option);
            }

            manager.getTransaction().commit();
        } catch (Exception e) {

            manager.getTransaction().rollback();
            e.printStackTrace();
        }
    }
}

