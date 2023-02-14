package baimuhtar.catalog;

import baimuhtar.catalog.entity.Product;

import javax.persistence.*;
import java.util.Scanner;

public class JpaLesson {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("main");
        EntityManager manager = factory.createEntityManager();

        int max_Price = 50000;


        int min_Price = 20000;


    }
}
