package de.htwg.dog;

import com.google.inject.AbstractModule;
import de.htwg.dog.controller.IController;
import de.htwg.dog.model.IModelFactory;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author kev
 */
public class DogModule extends AbstractModule {
	
    @Override
    protected void configure() {

            bind(IModelFactory.class).to(de.htwg.dog.model.impl.ModelFactory.class);
            bind(IController.class).to(de.htwg.dog.controller.impl.Controller.class);	
    }
}
