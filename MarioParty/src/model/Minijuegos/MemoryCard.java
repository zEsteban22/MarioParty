package model.Minijuegos;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public final class MemoryCard extends JButton {

	private static final long serialVersionUID = 1L;

	private String type;
	private int id;

	public MemoryCard() {
		setPreferredSize(new Dimension(103, 103));
		setBorder(null);
		setBackground(new Color(0, 255, 255));
	}

	public void setId(int i) {
		id = i;
	}

	public int getId() {
		return id;
	}

	public void setType(String t) {
		type = t;
		ImageIcon cardImg = new ImageIcon(ClassLoader.getSystemClassLoader().getResource("resources/" + type + ".png"));
		Icon cardIcon = new ImageIcon(cardImg.getImage().getScaledInstance(103, 103, Image.SCALE_SMOOTH));
		setIcon(cardIcon);
	}

	public String getType() {
		return type;
	}

	public void showHideCard(int action) { //1 show, 0 hide
		Thread hilo = new Thread() {
			public synchronized void run() {
				try {
					int aux = 0;
					switch (action) {
						case 0: {
							sleep(200);
							aux = 92;
							break;
						}
						case 1: {
							aux = 0;
							break;
						}
						default:
							break;
					}
					Image imagen = new ImageIcon(ClassLoader.getSystemClassLoader().getResource("resources/" + type + ".png")).getImage();
					for (int i = 1; i < 92; i += 5) {
						setIcon(new ImageIcon(imagen.getScaledInstance(Math.abs(aux - i), 91, Image.SCALE_DEFAULT)));
						sleep(5);
					}

					if (action == 0)
						setIcon(null);
				} catch (InterruptedException ex) {
				}
			}
		};
		hilo.start();
	}
}
