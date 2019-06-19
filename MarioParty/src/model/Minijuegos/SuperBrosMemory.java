package model.Minijuegos;

import model.Minijuegos.MemoryController;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import javax.swing.SwingConstants;

public final class SuperBrosMemory extends JFrame {

	private static final long serialVersionUID = 1L;
	final Object lock;
	Boolean resultado = false;
	private JPanel contentPane;
	JPanel panel = new JPanel();
	JPanel panel_2 = new JPanel();
	private ButtonAction event = new ButtonAction();
	private MemoryCard[] gameCards;
	private MemoryController controller;
	private int totalCards = 6 * 3;
	int actualPlayer = 0;
	int totalPairsFirst = 0;
	int totalPairsSecond = 0;
	JLabel lblPairsOne = new JLabel("Pairs: 0");
	JLabel lblPairsTwo = new JLabel("Pairs: 0");

	public static boolean jugarSuperBrosMemory() {
		SuperBrosMemory frame = new SuperBrosMemory();
		frame.setVisible(true);
		synchronized (frame.lock) {
			try {
				frame.lock.wait();
			} catch (InterruptedException e) {
			}
		}
		return frame.resultado;
	}

	/**
	 * Create the frame.
	 */
	public SuperBrosMemory() {
		lock = new Object();
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				synchronized (lock) {
					lock.notify();
				}
				resultado = false;
				super.windowClosing(e);
			}

		});
		setSize(870, 515);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		panel.setBackground(Color.WHITE);
		contentPane.add(panel);
		panel.setLayout(new BorderLayout(0, 0));

		JLabel lblNewLabel = new JLabel("Super Bro's Memory");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 50));
		lblNewLabel.setBackground(Color.WHITE);
		panel.add(lblNewLabel, BorderLayout.NORTH);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));

		JButton btnNewButton = new JButton("Jugar!");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_2.setVisible(true);
				controller.hideImages(gameCards);
			}
		});
		btnNewButton.setFont(new Font("Arial", Font.PLAIN, 35));
		btnNewButton.setForeground(new Color(0, 128, 0));
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setBorder(null);
		panel_1.add(btnNewButton, BorderLayout.SOUTH);

		lblPairsOne.setForeground(new Color(0, 0, 128));
		lblPairsOne.setFont(new Font("Arial", Font.PLAIN, 25));
		lblPairsOne.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_1.add(lblPairsOne, BorderLayout.WEST);

		lblPairsTwo.setForeground(new Color(255, 215, 0));
		lblPairsTwo.setFont(new Font("Arial", Font.PLAIN, 25));
		lblPairsTwo.setHorizontalAlignment(SwingConstants.LEFT);
		panel_1.add(lblPairsTwo, BorderLayout.EAST);

		panel_2.setBackground(new Color(255, 255, 255));
		panel_1.add(panel_2, BorderLayout.CENTER);
		controller = new MemoryController();
		gameCards = controller.generateMemoryCards();
		for (int i = 0; i < totalCards; i++) {
			gameCards[i].addActionListener(event);
			panel_2.add(gameCards[i]);
		}
		panel_2.setVisible(false);
	}

	private class ButtonAction implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			MemoryCard card = (MemoryCard) e.getSource();
			boolean[] result = controller.checkSelectedCards(card, gameCards);
			if (result[0])
				if (actualPlayer % 2 == 0) {
					totalPairsFirst++;
					lblPairsOne.setText("Pairs: " + Integer.toString(totalPairsFirst));
				} else {
					totalPairsSecond++;
					lblPairsTwo.setText("Pairs: " + Integer.toString(totalPairsSecond));
				}

			if (result[1])
				actualPlayer++;

			if (controller.winner()) {
				controller.showImagesToWinner(gameCards);
				if (totalPairsFirst > totalPairsSecond)
					JOptionPane.showMessageDialog(null, "El jugador que entró en la casilla de bros memory ha ganado", "Ganador!", JOptionPane.INFORMATION_MESSAGE);
				else
					JOptionPane.showMessageDialog(null, "El jugador que entró en la casilla de bros memory ha perdido", "Ganador!", JOptionPane.INFORMATION_MESSAGE);
				resultado = totalPairsFirst > totalPairsSecond;
				dispose();
				synchronized (lock) {
					lock.notify();
				}
			}
		}
	}
}
