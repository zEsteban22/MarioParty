/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author zEstebanCruz
 */
public class ControladorEscogerPersonajes implements Initializable {

	@FXML
	private Label labelNumeroJugador;
	@FXML
	private Label labelFicha1;
	@FXML
	private Label labelFicha2;
	@FXML
	private Label labelFicha3;
	@FXML
	private Label labelFicha4;
	@FXML
	private Label labelFicha5;
	@FXML
	private Label labelFicha6;
	@FXML
	private Label labelFicha7;
	@FXML
	private Label labelFicha8;
	@FXML
	private Label labelFicha9;
	@FXML
	private Label labelFichaA;
	@FXML
	private Label labelFichaB;
	@FXML
	private Label labelFichaC;
	@FXML
	private Label labelFichaD;
	@FXML
	private Label labelFichaE;
	@FXML
	private Label labelFichaF;
	@FXML
	private Button botonAleatorio;
	private ArrayList<Label> restantes;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		restantes = new ArrayList<>(
			Arrays.asList(labelFicha1, labelFicha2, labelFicha3, labelFicha4, labelFicha5, labelFicha6,
										labelFicha7, labelFicha8, labelFicha9, labelFichaA, labelFichaB, labelFichaC,
										labelFichaD, labelFichaE, labelFichaF));
	}

	private void marcarDeshabilitado(Label label) {
		label.setDisable(true);
		label.setBackground(new Background(new BackgroundFill(Color.web("#ff0000"), CornerRadii.EMPTY, Insets.EMPTY)));
		restantes.remove(label);
	}

	@FXML
	private void ficha1Presionada(MouseEvent event) {
		marcarDeshabilitado(labelFicha1);
	}

	@FXML
	private void ficha2Presionada(MouseEvent event) {
		marcarDeshabilitado(labelFicha2);
	}

	@FXML
	private void ficha3Presionada(MouseEvent event) {
		marcarDeshabilitado(labelFicha3);
	}

	@FXML
	private void ficha4Presionada(MouseEvent event) {
		marcarDeshabilitado(labelFicha4);
	}

	@FXML
	private void ficha5Presionada(MouseEvent event) {
		marcarDeshabilitado(labelFicha5);
	}

	@FXML
	private void ficha6Presionada(MouseEvent event) {
		marcarDeshabilitado(labelFicha6);
	}

	@FXML
	private void ficha7Presionada(MouseEvent event) {
		marcarDeshabilitado(labelFicha7);
	}

	@FXML
	private void ficha8Presionada(MouseEvent event) {
		marcarDeshabilitado(labelFicha8);
	}

	@FXML
	private void ficha9Presionada(MouseEvent event) {
		marcarDeshabilitado(labelFicha9);
	}

	@FXML
	private void fichaAPresionada(MouseEvent event) {
		marcarDeshabilitado(labelFichaA);
	}

	@FXML
	private void fichaBPresionada(MouseEvent event) {
		marcarDeshabilitado(labelFichaB);
	}

	@FXML
	private void fichaCPresionada(MouseEvent event) {
		marcarDeshabilitado(labelFichaC);
	}

	@FXML
	private void fichaDPresionada(MouseEvent event) {
		marcarDeshabilitado(labelFichaD);
	}

	@FXML
	private void fichaEPresionada(MouseEvent event) {
		marcarDeshabilitado(labelFichaE);
	}

	@FXML
	private void fichaFPresionada(MouseEvent event) {
		marcarDeshabilitado(labelFichaF);
	}

	@FXML
	private void aleatorioActual(ActionEvent event) {
		marcarDeshabilitado(restantes.get(new Random().nextInt(restantes.size())));
	}

}
