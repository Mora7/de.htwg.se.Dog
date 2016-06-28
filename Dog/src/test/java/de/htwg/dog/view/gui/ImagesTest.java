/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package de.htwg.dog.view.gui;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author kev
 */
public class ImagesTest {
    
    public ImagesTest() {
    }
    
    @Before
    public void setUp() {
    }

    @Test
    public void testScale() {
        System.out.println("scale");
        File file = new File("Images/tailchase.png");
        BufferedImage image;
        try {
            image = ImageIO.read(file);
            BufferedImage expResult = null;
            BufferedImage result = Images.scale(image, BufferedImage.TYPE_INT_ARGB, 123, 123);
            assertEquals(result.getHeight(), 123);
            assertEquals(result.getWidth(), 123);
        } catch (IOException ex) {
            Logger.getLogger(ImagesTest.class.getName()).log(Level.SEVERE, null, ex);
            fail("Could not load image.");
        }
    }
    
}
