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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.image.ImageView;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;
import model.Tablero;


/**
 * FXML Controller class
 *
 * @author zEstebanCruz
 */
public class ControladorInicio implements Initializable {

    
        private AudioClip coin;
        private AudioClip mainTheme;
	/**
	 * Initializes the controller class.
	 */
        @FXML
	private AnchorPane AnchorPane;
	@FXML
	private ImageView inicio;

        //"resources/Coin_Sound.wav"
        @FXML
	void inicioPresionado(MouseEvent mouseEvent) throws IOException {
            coin.play();
            mainTheme.stop();
            Parent siguiente = FXMLLoader.load(ClassLoader
                    .getSystemClassLoader().getResource("view/VentanaEscogerJugadores.fxml"));
            Scene jugadores = new Scene(siguiente);
            Stage window = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
            window.setScene(jugadores);
            window.show();
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
            //TODO
            coin = new AudioClip(ClassLoader.getSystemClassLoader()
                    .getResource("resources/Coin_Sound.wav").toExternalForm());
            mainTheme = new AudioClip(ClassLoader.getSystemClassLoader()
                    .getResource("resources/Main_Theme.wav").toExternalForm());
            mainTheme.setCycleCount(Integer.MAX_VALUE);
            mainTheme.play();
	}

}
