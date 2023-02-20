package baimuhtar.catalog.controller;

import baimuhtar.catalog.entity.Category;
import baimuhtar.catalog.entity.Option;
import baimuhtar.catalog.entity.Product;
import baimuhtar.catalog.entity.Value;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class UpdateProduct {

    static final Scanner scanner = new Scanner(System.in);

    static EntityManagerFactory factory;

    static {
        LogManager logManager = LogManager.getLogManager();
        Logger logger = logManager.getLogger("");
        logger.setLevel(Level.OFF);
        factory = Persistence.createEntityManagerFactory("main");
    }

    static EntityManager manager = factory.createEntityManager();

    public static void main(String[] args) {
        try {

            manager.getTransaction().begin();
            System.out.print("Введите идентификатор товара: ");
            long product_id = Long.parseLong(scanner.nextLine());

            Product product = manager.find(Product.class, product_id);

            System.out.print("Введите новое название товара [" + product.getName() + "]:");
            String product_name = scanner.nextLine();
            product.setName(product_name);

            System.out.print("Введите новую стоимость товара: [" + product.getPrice() + "]:");
            int product_price = Integer.parseInt(scanner.nextLine());
            product.setPrice(product_price);

            List<Option> optionList = product.getCategory().getOptions();

            for (Option option : optionList) {

                TypedQuery<Value> query = manager.createQuery("select v from Value v where v.product = ?1 and v.option = ?2", Value.class);
                query.setParameter(1, product);
                query.setParameter(2, option);

                List<Value> valueList = query.getResultList();
               //  System.out.printf("%s [%s]: ", valueList.get(0).getOption(), option.getValues());
                String value_name = scanner.nextLine();
                valueList.get(0).setValue(value_name);
            }

        manager.getTransaction().commit();
    } catch(
    Exception e)

    {
        manager.getTransaction().rollback();
        e.printStackTrace();
    }
}
}
