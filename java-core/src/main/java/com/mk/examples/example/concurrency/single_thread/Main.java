package com.mk.examples.example.concurrency.single_thread;

import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    private static final Button btnPrint = new Button("Print");

    private static void listen() {
        boolean quit = false;
        while (!quit) {
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 0: {
                    quit = true;
                    break;
                }
                case 1: {
                    btnPrint.onClick();
                }
            }
        }
    }

    public static void main(String[] args) {

//        class ClickListener implements Button.OnClickListener {
//            public ClickListener() {
//                System.out.println("I've been attached");
//            }
//
//            @Override
//            public void onClick(String title) {
//                System.out.println(title + " was clicked");
//            }
//        }

        btnPrint.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(String title) {
                System.out.println(title + " was Clicked");
            }
        });

//        btnPrint.setOnClickListener(new ClickListener());
        //listen();

        Gearbox mcLaren = new Gearbox(6);
        mcLaren.operateClutch(true);
        mcLaren.changeGear(1);
        mcLaren.operateClutch(false);
        System.out.println(mcLaren.wheelSpeed(1000));
        mcLaren.changeGear(2);
        System.out.println(mcLaren.wheelSpeed(3000));
        mcLaren.changeGear(3);
        mcLaren.operateClutch(false);
        System.out.println(mcLaren.wheelSpeed(6000));
    }

}
