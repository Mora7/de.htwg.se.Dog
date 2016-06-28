/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package de.htwg.dog.model.impl;

import de.htwg.dog.model.IModel;
import de.htwg.dog.model.IModelFactory;

/**
 *
 * @author kev
 */
public class ModelFactory implements IModelFactory {

    @Override
    public IModel create() {
        return new Game();
    }
}
