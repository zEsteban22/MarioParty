/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.SistemaDeJuego;

/**
 * FXML Controller class
 *
 * @author zEstebanCruz
 */
public class tablaFloyd implements Initializable {

	@FXML
	private TableView<List<IntegerProperty>> tabla;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		ObservableList<List<IntegerProperty>> data = FXCollections.observableArrayList();
		List<IntegerProperty> row;
		TableColumn<List<IntegerProperty>, Integer> columna;
		for (short i = 0; i < 26; i++) {
			columna = new TableColumn<>(Character.toString((char) (i + 'A')));
			tabla.getColumns().add(columna);
			final int k = i;
			columna.setCellValueFactory(dato -> dato.getValue().get(k).asObject());
		}
		for (Short[] i : SistemaDeJuego.floydCompleto()) {
			row = new ArrayList<>(26);
			for (Short j : i)
				row.add(new SimpleIntegerProperty(j));
			tabla.getItems().add(row);
		}
	}

}
