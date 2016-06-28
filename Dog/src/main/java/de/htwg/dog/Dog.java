/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package de.htwg.dog;

import com.google.inject.Guice;
import com.google.inject.Injector;

import de.htwg.dog.controller.IController;
import de.htwg.dog.view.gui.Gui;
import de.htwg.dog.view.tui.Tui;

import java.util.Scanner;
import org.apache.log4j.PropertyConfigurator;

/**
 *
 * @author kev
 */
public class Dog {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Set up logging through log4j
        PropertyConfigurator.configure("log4j.properties");
        
        // Set up Google Guice Dependency Injector
        Injector injector = Guice.createInjector(new DogModule());

        // Build up the application, resolving dependencies automatically by Guice
        IController cont = injector.getInstance(IController.class);
        Gui gui = injector.getInstance(Gui.class);
        Tui tui = injector.getInstance(Tui.class);
        tui.printTUI();
        cont.startGame();
        
        boolean continu = true;
        Scanner scanner = new Scanner(System.in);
        while (continu) {
           continu = tui.processInput(scanner.next());
        } 
        
        gui.dispose();
    }
    
}
