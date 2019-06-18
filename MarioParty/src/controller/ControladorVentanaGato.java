/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Kenneth
 */

    
    
public class ControladorVentanaGato implements Initializable {

    @FXML
    private ImageView cuadro1;
    
    @FXML
    private ImageView cuadro2;
    
    @FXML
    private ImageView cuadro3;
    
    @FXML
    private ImageView cuadro4;
    
    @FXML
    private ImageView cuadro5;
    
    @FXML
    private ImageView cuadro6;
    
    @FXML
    private ImageView cuadro7;
    
    @FXML
    private ImageView cuadro8;
    
    @FXML
    private ImageView cuadro9;
    
    @FXML
	void cuadroPresionado(MouseEvent mouseEvent) throws IOException {
            System.out.println("El cuadro que llamo fue: \n" + mouseEvent.getSource());
            
        }
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
