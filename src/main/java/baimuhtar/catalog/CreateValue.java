package baimuhtar.catalog;

import baimuhtar.catalog.entity.Category;
import baimuhtar.catalog.entity.Option;
import baimuhtar.catalog.entity.Product;
import baimuhtar.catalog.entity.Value;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

public class CreateValue {
    public static void main(String[] args) {
        createValue();

    }


    public static void createValue() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("main");
        EntityManager manager = factory.createEntityManager();




        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Введите id товара: ");
            Long product_id = Long.parseLong(scanner.nextLine());
            System.out.println("Введите id характеристики товара: ");
            Long option_id = Long.parseLong(scanner.nextLine());
            System.out.println("Введите название значения: ");
            String value = scanner.nextLine();

            manager.getTransaction().begin();

            Product product = manager.find(Product.class, product_id);
            Option option = manager.find(Option.class, option_id);

            Value new_value = new Value();
            new_value.setProduct(product);
            new_value.setOption(option);
            new_value.setValue(value);

            manager.persist(new_value);
            manager.getTransaction().commit();

        } catch (Exception e) {
            manager.getTransaction().rollback();
            e.printStackTrace();
        }
    }
}
