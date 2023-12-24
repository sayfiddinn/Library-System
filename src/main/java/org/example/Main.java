package org.example;

import org.example.controller.MainController;
import org.example.util.Runner;


public class Main {

    private static final MainController mainController = new MainController();

    public static void main(String[] args) {
        Runner.generateDefault();
        mainController.run();
    }


}