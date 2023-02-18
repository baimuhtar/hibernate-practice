package baimuhtar.catalog.controller;

import baimuhtar.catalog.entity.Option;
import baimuhtar.catalog.entity.Product;
import baimuhtar.catalog.entity.Value;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Scanner;

public class UpdateProduct {

    static final Scanner scanner = new Scanner(System.in);
    static EntityManagerFactory factory = Persistence.createEntityManagerFactory("main");
    static EntityManager manager = factory.createEntityManager();

    public static void main(String[] args) {
        try {
            TypedQuery<Product> queryProduct = manager.createQuery("select p from Product p", Product.class);
            List<Product> productList = queryProduct.getResultList();

            TypedQuery<Option> optionQuery = manager.createQuery("select o from Option o", Option.class);
            List<Option> optionList = optionQuery.getResultList();

            TypedQuery<Value> valueQuery = manager.createQuery("select o from Option o", Value.class);
            List<Value> valueList = valueQuery.getResultList();

            manager.getTransaction().begin();
            System.out.print("Введите идентификатор товара: ");
            long product_id = Long.parseLong(scanner.nextLine());

            for (Product product : productList) {
                if (product_id == product.getId()) {
                    System.out.print("Введите новое название товара: " + product.getName());
                    String product_name = scanner.nextLine();
                    System.out.print("Введите новую стоимость товара: " + product.getPrice());
                    int product_price = scanner.nextInt();
                    product.setName(product_name);
                    product.setPrice(product_price);
                }
                for (int i = 0; i < optionList.size(); i++) {
                    System.out.printf("%s [%s]", optionList.get(i).getName(), valueList.get(i).getValue());
                    String value_name = scanner.nextLine();
                    if (value_name.equals("")) {
                        valueList.get(i).setValue(value_name);
                    }
                }
            }
            manager.getTransaction().commit();
        } catch (Exception e) {
            manager.getTransaction().rollback();
            e.printStackTrace();
        }
    }
}
