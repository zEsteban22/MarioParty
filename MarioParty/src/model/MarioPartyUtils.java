/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.IOException;
import java.net.URL;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author zEstebanCruz
 */
public class MarioPartyUtils {

	public static final String[] PERSONAJES = {"daisy", "luigi", "mario", "peach", "toad", "birdo",
																						 "yoshi", "donkey_kong", "waluigi", "wario", "bowser",
																						 "diddy_kong", "goomba", "king_boo", "bowsy"};

	public static void cambiarAVentana(String siguienteVentana, Stage ventanaAnterior) {
		ventanaAnterior.close();
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(ClassLoader.getSystemClassLoader().getResource("view/" + siguienteVentana));
			Parent root = (Parent) fxmlLoader.load();
			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.show();
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}

	public static URL urlPersonajeActual() {
		String s = "resources/" + SistemaDeJuego.getFicha(0) + ".png";
		return ClassLoader.getSystemClassLoader().getResource(s);
	}
}
