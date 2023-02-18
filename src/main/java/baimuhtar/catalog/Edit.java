package baimuhtar.catalog;

import baimuhtar.catalog.entity.Category;
import baimuhtar.catalog.entity.Option;
import baimuhtar.catalog.entity.Product;
import baimuhtar.catalog.entity.Value;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.lang.reflect.Type;
import java.util.*;

public class Edit {
    public static void main(String[] args) {
        editEntity();

    }

    public static void editEntity() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("main");
        EntityManager manager = factory.createEntityManager();

        try {
            manager.getTransaction().begin();
            TypedQuery<Category> query = manager.createQuery("select c from Category c", Category.class);
            List<Category> categoryList = query.getResultList();

            TypedQuery<Option> queryOption = manager.createQuery("select o from Option o", Option.class);
            List<Option> optionList = queryOption.getResultList();

            TypedQuery<String> queryProduct = manager.createQuery("select p.name from Product p", String.class);
            List<String> productList = queryProduct.getResultList();
            Set<String> productSet = new HashSet<>(productList);



            long[] numbers = new long[]{1, 2, 3};

            String creating = "- Создание " + numbers[0];
            String editing = "- Редактирование " + numbers[1];
            String deleting = "- Удаление " + numbers[2];

            System.out.println(creating);
            System.out.println(editing);
            System.out.println(deleting);

            System.out.println("Выберите действие: ");
            Scanner scanner = new Scanner(System.in);
            long action = Long.parseLong(scanner.nextLine());

            if (action == numbers[0]) {
                for (Category category : categoryList) {
                    System.out.println(category.getName() + " " + category.getId());
                }

                System.out.print("Выберите категорию по номеру: ");
                long category_id = Long.parseLong(scanner.nextLine());
                System.out.print("Введите название товара: ");
                String product_name = scanner.nextLine();
                System.out.print("Введите стоимость товара: ");
                int product_price = scanner.nextInt();

                Category categoryz = manager.find(Category.class, category_id);

                for (Option option : optionList) {
                    if (productSet.contains(product_name)) {
                        System.out.println("данный товар уже существует");
                    } else {


                        Product product = new Product();
                        product.setCategory(categoryz);
                        product.setName(product_name);
                        product.setPrice(product_price);
                        manager.persist(product);

                        System.out.print(option.getName());
                        String value_name = scanner.nextLine();
                        Value value = new Value();
                        value.setValue(value_name);
                    }
                }
            }
            manager.getTransaction().commit();
        } catch (Exception e) {
            manager.getTransaction().rollback();
            e.printStackTrace();
        }
    }


    public static void createProduct() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("main");
        EntityManager manager = factory.createEntityManager();


        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Выберите категорию по номеру: ");
            long category_id = Long.parseLong(scanner.nextLine());
            System.out.println("Введите название товара");
            String product_name = scanner.nextLine();
            System.out.println("Введите стоимость товара");
            int product_price = scanner.nextInt();
            manager.getTransaction().begin();

            Category category = manager.find(Category.class, category_id);

            String[] products = product_name.split(", ");

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

    public static void createValue() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("main");
        EntityManager manager = factory.createEntityManager();

        try {
            TypedQuery<Value> query = manager.createQuery("select v from Value v", Value.class);
            List<Value> valueList = query.getResultList();


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

    public static void createOption() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("main");
        EntityManager manager = factory.createEntityManager();

        try {
            manager.getTransaction().begin();
            TypedQuery<Category> queryCategory = manager.createQuery("select c from Category c", Category.class);
            List<Category> categoryList = queryCategory.getResultList();
            for (Category category : categoryList) {
                System.out.println(category.getName() + " " + category.getId());
            }
            System.out.print("Введите категорию по номеру: ");
            Scanner scanner = new Scanner(System.in);
            int category_id = Integer.parseInt(scanner.nextLine());


            TypedQuery<Option> queryOption = manager.createQuery("select o from Option o", Option.class);
            List<Option> optionList = queryOption.getResultList();


            for (Option option : optionList) {
                if (category_id == option.getCategory().getId()) {
                    System.out.print(option.getName() + ": ");
                    String value_name = scanner.nextLine();
                    Value value = new Value();
                    value.setValue(value_name);
                    value.setOption(option);
                }
            }

            manager.getTransaction().commit();
        } catch (Exception e) {
            manager.getTransaction().rollback();
            e.printStackTrace();
        }
    }
}