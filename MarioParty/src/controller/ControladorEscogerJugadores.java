/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author zEstebanCruz
 */
public class ControladorEscogerJugadores implements Initializable {

        private AudioClip jugadoresTheme;
        
	@FXML
	private ComboBox<?> comboBoxCantJugadores;
	@FXML
	private Button botonListoxd;
        
        @FXML
        void listoPresionado(MouseEvent mouseEvent) throws IOException{
            jugadoresTheme.stop();
            Parent siguiente = FXMLLoader.load(ClassLoader
                    .getSystemClassLoader().getResource("view/VentanaEscogerPersonajes.fxml"));
            Scene personajes = new Scene(siguiente);
            Stage window = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
            window.setScene(personajes);
            window.show();
        }

	@Override
	public void initialize(URL url, ResourceBundle rb) {
            jugadoresTheme = new AudioClip(ClassLoader.getSystemClassLoader()
                    .getResource("resources/Jugadores_Theme.wav").toExternalForm());
            jugadoresTheme.play();
	}

	@FXML
	private void accion(ActionEvent event) {
	}

}
