package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("##CREATING COMPANY EMAIL# \n");
        System.out.println("Enter your name: ");
        String name = scanner.nextLine().toLowerCase();
        System.out.println("Enter your surname: ");
        String surname = scanner.nextLine().toLowerCase();
        Email email1 = new Email(name,surname);
        scanner.close();
        System.out.print("Your new email in our company is: " + email1.getFullEmail());

    }
}
