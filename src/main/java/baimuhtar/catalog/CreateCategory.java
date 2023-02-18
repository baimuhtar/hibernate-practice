package baimuhtar.catalog;

import baimuhtar.catalog.entity.Category;
import baimuhtar.catalog.entity.Option;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import java.util.*;

public class CreateCategory {

    static EntityManagerFactory factory = Persistence.createEntityManagerFactory("main");
    static EntityManager manager = factory.createEntityManager();

    static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.print("Введите название категории: ");
        String category_name = scanner.nextLine();

        TypedQuery<String> queryCategory = manager.createQuery("select c.name from Category c", String.class);
        List<String> categoryList = queryCategory.getResultList();
        Set<String> categoryNameSet = new HashSet<>(categoryList);

        if (categoryNameSet.contains(category_name)) {
            System.out.println("Категория с названием '".concat(category_name) + "' уже существует");
            return;
        }

        System.out.print("Введите название характеристики (через запятую): ");
        String option_name = scanner.nextLine();
        String[] options = option_name.split(", ");

        try {
            manager.getTransaction().begin();

            Category category = new Category();
            category.setName(category_name);
            manager.persist(category);

            for (int i = 0; i < options.length; i++) {
                Option option = new Option();
                option.setCategory(category);
                option.setName(options[i]);
                manager.persist(option);
            }

            manager.getTransaction().commit();
        } catch (
                Exception e) {
            manager.getTransaction().rollback();
            e.printStackTrace();
        }
    }
}
