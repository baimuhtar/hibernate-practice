package baimuhtar.catalog;

import baimuhtar.catalog.controller.CreateCategory;
import baimuhtar.catalog.controller.CreateProduct;
import baimuhtar.catalog.controller.DeleteProduct;
import baimuhtar.catalog.controller.UpdateProduct;
import baimuhtar.catalog.entity.Category;
import baimuhtar.catalog.entity.Option;
import baimuhtar.catalog.entity.Product;
import baimuhtar.catalog.entity.Value;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.awt.*;
import java.util.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        int[] numbers = new int[]{1, 2, 3};

        String creating = "- Создание " + numbers[0];
        String editing = "- Редактирование " + numbers[1];
        String deleting = "- Удаление " + numbers[2];

        System.out.println(creating);
        System.out.println(editing);
        System.out.println(deleting);

        System.out.println("Выберите действие: ");
        Scanner scanner = new Scanner(System.in);
        int action = Integer.parseInt(scanner.nextLine());

        switch (action) {
            case 1 -> {
                EntityManagerFactory factory = Persistence.createEntityManagerFactory("main");
                EntityManager manager = factory.createEntityManager();

                TypedQuery<Category> query = manager.createQuery("select c from Category c", Category.class);
                List<Category> categoryList = query.getResultList();

                for (Category category : categoryList)
                    System.out.printf(" - %s [%d]\n", category.getName(), category.getId());

                CreateProduct.main(null);
            }

            case 2 -> UpdateProduct.main(null);
            case 3 -> DeleteProduct.main(null);
        }
    }
}