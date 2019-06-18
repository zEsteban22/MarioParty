package view;

import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;

/**
 *
 * @author zEstebanCruz
 */
public final class Selector extends JFrame {

	private class EscuchadorDeVentana extends WindowAdapter {

		@Override
		public void windowClosing(WindowEvent e) {
			if (JOptionPane.showConfirmDialog(null, "Si sale sin realizar una selección, se escogerá de forma aleatoria.\n¿Está seguro que desea salir?", "Confirmación", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				comboBoxJugadores.setSelectedIndex(new Random().nextInt(comboBoxJugadores.getItemCount()));
				synchronized (obj) {
					obj.notifyAll();
				}
				super.windowClosing(e);
			}
		}
	}

	JComboBox<String> comboBoxJugadores;
	private final Object obj;

	public Selector(List<String> jugadores) throws HeadlessException {
		super();
		obj = new Object();
		comboBoxJugadores = new JComboBox<>(jugadores.toArray(new String[jugadores.size()]));
		comboBoxJugadores.setSelectedIndex(0);
		add(comboBoxJugadores);
		JButton boton = new JButton("Escoger");
		boton.addActionListener((e) -> {
			this.dispose();
			synchronized (obj) {
				obj.notify();
			}
		});
		add(boton);
		setLayout(new FlowLayout());
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		addWindowListener(new EscuchadorDeVentana());
		comboBoxJugadores.setPrototypeDisplayValue("Jugador a escoger");
		comboBoxJugadores.setMinimumSize(comboBoxJugadores.getPreferredSize());
	}

	public static String escogerJugador(List<String> jugadores) {
		Selector ventana = new Selector(jugadores);
		ventana.pack();
		ventana.setLocationRelativeTo(null);
		ventana.setVisible(true);
		synchronized (ventana.obj) {
			try {
				ventana.obj.wait();
			} catch (InterruptedException ex) {
			}
		}
		return String.valueOf(ventana.comboBoxJugadores.getSelectedItem());

	}

}
