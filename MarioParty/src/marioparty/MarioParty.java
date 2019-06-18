/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marioparty;

import java.io.IOException;
import java.util.Arrays;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import static view.Selector.escogerJugador;

/**
 *
 * @author zEstebanCruz
 */
public class MarioParty extends Application {

	Scene scene;

	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(ClassLoader.getSystemClassLoader().getResource("view/VentanaInicio.fxml"));
			scene = new Scene(root);

			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException ex) {
			System.out.println("Error: " + ex.getMessage());
			try {
				this.stop();
			} catch (Exception ex1) {
			}
		}
	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		//launch(args);
		String s = escogerJugador(Arrays.asList("hola", "JAJA", "Me cago xd", ":c"));
		System.out.println(s);
	}

}
