package model.Minijuegos;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Color;

import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import java.util.Random;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public final class MemoryPath extends JFrame {

	Boolean resultado = false;
	final Object lock;
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	JButton btn0_0, btn0_1, btn0_2,
		btn1_0, btn1_1, btn1_2,
		btn2_0, btn2_1, btn2_2,
		btn3_0, btn3_1, btn3_2,
		btn4_0, btn4_1, btn4_2,
		btn5_0, btn5_1, btn5_2,
		btn6_0, btn6_1, btn6_2,
		btn7_0, btn7_1, btn7_2;

	private int turn = -3;

	private int[][] gameMatrix = {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}, {0, 0, 0}, {0, 0, 0}, {0, 0, 0},
																{0, 0, 0}, {0, 0, 0}};

	/**
	 * Juega al memory path
	 *
	 * @return Devuelve true si gana y false si pierde
	 */
	public static boolean jugarMemoryPath() {

		MemoryPath frame = new MemoryPath();
		frame.setVisible(true);
		synchronized (frame.lock) {
			try {
				frame.lock.wait();
			} catch (InterruptedException ex) {
			}
		}
		return frame.resultado;
	}

	/**
	 * Create the frame.
	 */
	public MemoryPath() {
		lock = new Object();
		generateRandomPath();
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				synchronized (lock) {
					lock.notifyAll();
				}
				super.windowClosing(e);
			}

		});
		setBounds(100, 100, 478, 756);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));

		JLabel lblNewLabel = new JLabel("MemoryPath!");
		lblNewLabel.setForeground(new Color(255, 0, 0));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 45));
		panel.add(lblNewLabel, BorderLayout.NORTH);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		panel.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new GridLayout(8, 3, 6, 6));

		btn0_0 = new JButton("?");
		btn0_0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn0_0.setText("X");
				if (gameMatrix[0][0] == 1)
					btn0_0.setForeground(new Color(0, 255, 0));
				else {
					turn++;
					btn0_0.setForeground(new Color(255, 0, 0));
					if (turn <= 3) {
						JOptionPane.showMessageDialog(null, "Ha fallado", "Looses turn.", JOptionPane.WARNING_MESSAGE);
						btn0_0.setForeground(new Color(255, 215, 0));
						btn0_0.setText("?");
						resetPathInGui();
					} else {
						JOptionPane.showMessageDialog(null, "Ha perdido todos sus turnos", "Game lost.", JOptionPane.INFORMATION_MESSAGE);
						cerrar();
					}
				}
			}
		});
		btn0_0.setBackground(new Color(255, 255, 255));
		btn0_0.setForeground(new Color(255, 215, 0));
		btn0_0.setFont(new Font("Super Mario 256", Font.PLAIN, 45));
		panel_1.add(btn0_0);

		btn0_1 = new JButton("?");
		btn0_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn0_1.setText("X");
				if (gameMatrix[0][1] == 1)
					btn0_1.setForeground(new Color(0, 255, 0));
				else {
					turn++;
					btn0_1.setForeground(new Color(0, 0, 0));
					if (turn <= 3) {
						JOptionPane.showMessageDialog(null, "You failed.", "Looses turn.", JOptionPane.WARNING_MESSAGE);
						btn0_1.setForeground(new Color(255, 215, 0));
						btn0_1.setText("?");
						resetPathInGui();
					} else {
						JOptionPane.showMessageDialog(null, "You lost all your turns.", "Game lost.", JOptionPane.INFORMATION_MESSAGE);
						cerrar();
					}
				}
			}
		});
		btn0_1.setBackground(new Color(255, 255, 255));
		btn0_1.setForeground(new Color(255, 215, 0));
		btn0_1.setFont(new Font("Super Mario 256", Font.PLAIN, 45));
		panel_1.add(btn0_1);

		btn0_2 = new JButton("?");
		btn0_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn0_2.setText("X");
				if (gameMatrix[0][2] == 1)
					btn0_2.setForeground(new Color(0, 255, 0));
				else {
					turn++;
					btn0_2.setForeground(new Color(255, 0, 0));
					if (turn <= 3) {
						JOptionPane.showMessageDialog(null, "You failed.", "Looses turn.", JOptionPane.WARNING_MESSAGE);
						btn0_2.setForeground(new Color(255, 215, 0));
						btn0_2.setText("?");
						resetPathInGui();
					} else {
						JOptionPane.showMessageDialog(null, "You lost all your turns.", "Game lost.", JOptionPane.INFORMATION_MESSAGE);
						cerrar();
					}
				}
			}
		});
		btn0_2.setBackground(new Color(255, 255, 255));
		btn0_2.setForeground(new Color(255, 215, 0));
		btn0_2.setFont(new Font("Super Mario 256", Font.PLAIN, 45));
		panel_1.add(btn0_2);

		btn1_0 = new JButton("?");
		btn1_0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn1_0.setText("X");
				if (gameMatrix[1][0] == 1)
					btn1_0.setForeground(new Color(0, 255, 0));
				else {
					turn++;
					btn1_0.setForeground(new Color(255, 0, 0));
					if (turn <= 3) {
						JOptionPane.showMessageDialog(null, "You failed.", "Looses turn.", JOptionPane.WARNING_MESSAGE);
						btn1_0.setForeground(new Color(255, 215, 0));
						btn1_0.setText("?");
						resetPathInGui();
					} else {
						JOptionPane.showMessageDialog(null, "You lost all your turns.", "Game lost.", JOptionPane.INFORMATION_MESSAGE);
						cerrar();
					}
				}
			}
		});
		btn1_0.setBackground(new Color(255, 255, 255));
		btn1_0.setForeground(new Color(255, 215, 0));
		btn1_0.setFont(new Font("Super Mario 256", Font.PLAIN, 45));
		panel_1.add(btn1_0);

		btn1_1 = new JButton("?");
		btn1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn1_1.setText("X");
				if (gameMatrix[1][1] == 1)
					btn1_1.setForeground(new Color(0, 255, 0));
				else {
					turn++;
					btn1_1.setForeground(new Color(255, 0, 0));
					if (turn <= 3) {
						JOptionPane.showMessageDialog(null, "You failed.", "Looses turn.", JOptionPane.WARNING_MESSAGE);
						btn1_1.setForeground(new Color(255, 215, 0));
						btn1_1.setText("?");
						resetPathInGui();
					} else {
						JOptionPane.showMessageDialog(null, "You lost all your turns.", "Game lost.", JOptionPane.INFORMATION_MESSAGE);
						cerrar();
					}
				}
			}
		});
		btn1_1.setBackground(new Color(255, 255, 255));
		btn1_1.setForeground(new Color(255, 215, 0));
		btn1_1.setFont(new Font("Super Mario 256", Font.PLAIN, 45));
		panel_1.add(btn1_1);

		btn1_2 = new JButton("?");
		btn1_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn1_2.setText("X");
				if (gameMatrix[1][2] == 1)
					btn1_2.setForeground(new Color(0, 255, 0));
				else {
					turn++;
					btn1_2.setForeground(new Color(255, 0, 0));
					if (turn <= 3) {
						JOptionPane.showMessageDialog(null, "You failed.", "Looses turn.", JOptionPane.WARNING_MESSAGE);
						btn1_2.setForeground(new Color(255, 215, 0));
						btn1_2.setText("?");
						resetPathInGui();
					} else {
						JOptionPane.showMessageDialog(null, "You lost all your turns.", "Game lost.", JOptionPane.INFORMATION_MESSAGE);
						cerrar();
					}
				}
			}
		});
		btn1_2.setBackground(new Color(255, 255, 255));
		btn1_2.setForeground(new Color(255, 215, 0));
		btn1_2.setFont(new Font("Super Mario 256", Font.PLAIN, 45));
		panel_1.add(btn1_2);

		btn2_0 = new JButton("?");
		btn2_0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn2_0.setText("X");
				if (gameMatrix[2][0] == 1)
					btn2_0.setForeground(new Color(0, 255, 0));
				else {
					turn++;
					btn2_0.setForeground(new Color(255, 0, 0));
					if (turn <= 3) {
						JOptionPane.showMessageDialog(null, "You failed.", "Looses turn.", JOptionPane.WARNING_MESSAGE);
						btn2_0.setForeground(new Color(255, 215, 0));
						btn2_0.setText("?");
						resetPathInGui();
					} else {
						JOptionPane.showMessageDialog(null, "You lost all your turns.", "Game lost.", JOptionPane.INFORMATION_MESSAGE);
						cerrar();
					}
				}
			}
		});
		btn2_0.setBackground(new Color(255, 255, 255));
		btn2_0.setForeground(new Color(255, 215, 0));
		btn2_0.setFont(new Font("Super Mario 256", Font.PLAIN, 45));
		panel_1.add(btn2_0);

		btn2_1 = new JButton("?");
		btn2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn2_1.setText("X");
				if (gameMatrix[2][1] == 1)
					btn2_1.setForeground(new Color(0, 255, 0));
				else {
					turn++;
					btn2_1.setForeground(new Color(255, 0, 0));
					if (turn <= 3) {
						JOptionPane.showMessageDialog(null, "You failed.", "Looses turn.", JOptionPane.WARNING_MESSAGE);
						btn2_1.setForeground(new Color(255, 215, 0));
						btn2_1.setText("?");
						resetPathInGui();
					} else {
						JOptionPane.showMessageDialog(null, "You lost all your turns.", "Game lost.", JOptionPane.INFORMATION_MESSAGE);
						cerrar();
					}
				}
			}
		});
		btn2_1.setBackground(new Color(255, 255, 255));
		btn2_1.setForeground(new Color(255, 215, 0));
		btn2_1.setFont(new Font("Super Mario 256", Font.PLAIN, 45));
		panel_1.add(btn2_1);

		btn2_2 = new JButton("?");
		btn2_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn2_2.setText("X");
				if (gameMatrix[2][2] == 1)
					btn2_2.setForeground(new Color(0, 255, 0));
				else {
					turn++;
					btn2_2.setForeground(new Color(255, 0, 0));
					if (turn <= 3) {
						JOptionPane.showMessageDialog(null, "You failed.", "Looses turn.", JOptionPane.WARNING_MESSAGE);
						btn2_2.setForeground(new Color(255, 215, 0));
						btn2_2.setText("?");
						resetPathInGui();
					} else {
						JOptionPane.showMessageDialog(null, "You lost all your turns.", "Game lost.", JOptionPane.INFORMATION_MESSAGE);
						cerrar();
					}
				}
			}
		});
		btn2_2.setBackground(new Color(255, 255, 255));
		btn2_2.setForeground(new Color(255, 215, 0));
		btn2_2.setFont(new Font("Super Mario 256", Font.PLAIN, 45));
		panel_1.add(btn2_2);

		btn3_0 = new JButton("?");

		btn3_0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn3_0.setText("X");
				if (gameMatrix[3][0] == 1)
					btn3_0.setForeground(new Color(0, 255, 0));
				else {
					turn++;
					btn3_0.setForeground(new Color(255, 0, 0));
					if (turn <= 3) {
						JOptionPane.showMessageDialog(null, "You failed.", "Looses turn.", JOptionPane.WARNING_MESSAGE);
						btn3_0.setForeground(new Color(255, 215, 0));
						btn3_0.setText("?");
						resetPathInGui();
					} else {
						JOptionPane.showMessageDialog(null, "You lost all your turns.", "Game lost.", JOptionPane.INFORMATION_MESSAGE);
						cerrar();
					}
				}
			}
		});
		btn3_0.setBackground(new Color(255, 255, 255));
		btn3_0.setForeground(new Color(255, 215, 0));
		btn3_0.setFont(new Font("Super Mario 256", Font.PLAIN, 45));
		panel_1.add(btn3_0);

		btn3_1 = new JButton("?");
		btn3_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn3_1.setText("X");
				if (gameMatrix[3][1] == 1)
					btn3_1.setForeground(new Color(0, 255, 0));
				else {
					turn++;
					btn3_1.setForeground(new Color(255, 0, 0));
					if (turn <= 3) {
						JOptionPane.showMessageDialog(null, "You failed.", "Looses turn.", JOptionPane.WARNING_MESSAGE);
						btn3_1.setForeground(new Color(255, 215, 0));
						btn3_1.setText("?");
						resetPathInGui();
					} else {
						JOptionPane.showMessageDialog(null, "You lost all your turns.", "Game lost.", JOptionPane.INFORMATION_MESSAGE);
						cerrar();
					}
				}
			}
		});
		btn3_1.setBackground(new Color(255, 255, 255));
		btn3_1.setForeground(new Color(255, 215, 0));
		btn3_1.setFont(new Font("Super Mario 256", Font.PLAIN, 45));
		panel_1.add(btn3_1);

		btn3_2 = new JButton("?");

		btn3_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn3_2.setText("X");
				if (gameMatrix[3][2] == 1)
					btn3_2.setForeground(new Color(0, 255, 0));
				else {
					turn++;
					btn3_2.setForeground(new Color(255, 0, 0));
					if (turn <= 3) {
						JOptionPane.showMessageDialog(null, "You failed.", "Looses turn.", JOptionPane.WARNING_MESSAGE);
						btn3_2.setForeground(new Color(255, 215, 0));
						btn3_2.setText("?");
						resetPathInGui();
					} else {
						JOptionPane.showMessageDialog(null, "You lost all your turns.", "Game lost.", JOptionPane.INFORMATION_MESSAGE);
						cerrar();
					}
				}
			}
		});
		btn3_2.setBackground(new Color(255, 255, 255));
		btn3_2.setForeground(new Color(255, 215, 0));
		btn3_2.setFont(new Font("Super Mario 256", Font.PLAIN, 45));
		panel_1.add(btn3_2);

		btn4_0 = new JButton("?");
		btn4_0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn4_0.setText("X");
				if (gameMatrix[4][0] == 1)
					btn4_0.setForeground(new Color(0, 255, 0));
				else {
					turn++;
					btn4_0.setForeground(new Color(255, 0, 0));
					if (turn <= 3) {
						JOptionPane.showMessageDialog(null, "You failed.", "Looses turn.", JOptionPane.WARNING_MESSAGE);
						btn4_0.setForeground(new Color(255, 215, 0));
						btn4_0.setText("?");
						resetPathInGui();
					} else {
						JOptionPane.showMessageDialog(null, "You lost all your turns.", "Game lost.", JOptionPane.INFORMATION_MESSAGE);
						cerrar();
					}
				}
			}
		});
		btn4_0.setBackground(new Color(255, 255, 255));
		btn4_0.setForeground(new Color(255, 215, 0));
		btn4_0.setFont(new Font("Super Mario 256", Font.PLAIN, 45));
		panel_1.add(btn4_0);

		btn4_1 = new JButton("?");

		btn4_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn4_1.setText("X");
				if (gameMatrix[4][1] == 1)
					btn4_1.setForeground(new Color(0, 255, 0));
				else {
					turn++;
					btn4_1.setForeground(new Color(255, 0, 0));
					if (turn <= 3) {
						JOptionPane.showMessageDialog(null, "You failed.", "Looses turn.", JOptionPane.WARNING_MESSAGE);
						btn4_1.setForeground(new Color(255, 215, 0));
						btn4_1.setText("?");
						resetPathInGui();
					} else {
						JOptionPane.showMessageDialog(null, "You lost all your turns.", "Game lost.", JOptionPane.INFORMATION_MESSAGE);
						cerrar();
					}
				}
			}
		});
		btn4_1.setBackground(new Color(255, 255, 255));
		btn4_1.setForeground(new Color(255, 215, 0));
		btn4_1.setFont(new Font("Super Mario 256", Font.PLAIN, 45));
		panel_1.add(btn4_1);

		btn4_2 = new JButton("?");
		btn4_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn4_2.setText("X");
				if (gameMatrix[4][2] == 1)
					btn4_2.setForeground(new Color(0, 255, 0));
				else {
					turn++;
					btn4_2.setForeground(new Color(255, 0, 0));
					if (turn <= 3) {
						JOptionPane.showMessageDialog(null, "You failed.", "Looses turn.", JOptionPane.WARNING_MESSAGE);
						btn4_2.setForeground(new Color(255, 215, 0));
						btn4_2.setText("?");
						resetPathInGui();
					} else {
						JOptionPane.showMessageDialog(null, "You lost all your turns.", "Game lost.", JOptionPane.INFORMATION_MESSAGE);
						cerrar();
					}
				}
			}
		});
		btn4_2.setBackground(new Color(255, 255, 255));
		btn4_2.setForeground(new Color(255, 215, 0));
		btn4_2.setFont(new Font("Super Mario 256", Font.PLAIN, 45));
		panel_1.add(btn4_2);

		btn5_0 = new JButton("?");
		btn5_0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn5_0.setText("X");
				if (gameMatrix[5][0] == 1)
					btn5_0.setForeground(new Color(0, 255, 0));
				else {
					turn++;
					btn5_0.setForeground(new Color(255, 0, 0));
					if (turn <= 3) {
						JOptionPane.showMessageDialog(null, "You failed.", "Looses turn.", JOptionPane.WARNING_MESSAGE);
						btn5_0.setForeground(new Color(255, 215, 0));
						btn5_0.setText("?");
						resetPathInGui();
					} else {
						JOptionPane.showMessageDialog(null, "You lost all your turns.", "Game lost.", JOptionPane.INFORMATION_MESSAGE);
						cerrar();
					}
				}
			}
		});
		btn5_0.setBackground(new Color(255, 255, 255));
		btn5_0.setForeground(new Color(255, 215, 0));
		btn5_0.setFont(new Font("Super Mario 256", Font.PLAIN, 45));
		panel_1.add(btn5_0);

		btn5_1 = new JButton("?");
		btn5_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn5_1.setText("X");
				if (gameMatrix[5][1] == 1)
					btn5_1.setForeground(new Color(0, 255, 0));
				else {
					turn++;
					btn5_1.setForeground(new Color(255, 0, 0));
					if (turn <= 3) {
						JOptionPane.showMessageDialog(null, "You failed.", "Looses turn.", JOptionPane.WARNING_MESSAGE);
						btn5_1.setForeground(new Color(255, 215, 0));
						btn5_1.setText("?");
						resetPathInGui();
					} else {
						JOptionPane.showMessageDialog(null, "You lost all your turns.", "Game lost.", JOptionPane.INFORMATION_MESSAGE);
						cerrar();
					}
				}
			}
		});
		btn5_1.setBackground(new Color(255, 255, 255));
		btn5_1.setForeground(new Color(255, 215, 0));
		btn5_1.setFont(new Font("Super Mario 256", Font.PLAIN, 45));
		panel_1.add(btn5_1);

		btn5_2 = new JButton("?");
		btn5_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn5_2.setText("X");
				if (gameMatrix[5][2] == 1)
					btn5_2.setForeground(new Color(0, 255, 0));
				else {
					turn++;
					btn5_2.setForeground(new Color(255, 0, 0));
					if (turn <= 3) {
						JOptionPane.showMessageDialog(null, "You failed.", "Looses turn.", JOptionPane.WARNING_MESSAGE);
						btn5_2.setForeground(new Color(255, 215, 0));
						btn5_2.setText("?");
						resetPathInGui();
					} else {
						JOptionPane.showMessageDialog(null, "You lost all your turns.", "Game lost.", JOptionPane.INFORMATION_MESSAGE);
						cerrar();
					}
				}
			}
		});
		btn5_2.setBackground(new Color(255, 255, 255));
		btn5_2.setForeground(new Color(255, 215, 0));
		btn5_2.setFont(new Font("Super Mario 256", Font.PLAIN, 45));
		panel_1.add(btn5_2);

		btn6_0 = new JButton("?");
		btn6_0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn6_0.setText("X");
				if (gameMatrix[6][0] == 1)
					btn6_0.setForeground(new Color(0, 255, 0));
				else {
					turn++;
					btn6_0.setForeground(new Color(255, 0, 0));
					if (turn <= 3) {
						JOptionPane.showMessageDialog(null, "You failed.", "Looses turn.", JOptionPane.WARNING_MESSAGE);
						btn6_0.setForeground(new Color(255, 215, 0));
						btn6_0.setText("?");
						resetPathInGui();
					} else {
						JOptionPane.showMessageDialog(null, "You lost all your turns.", "Game lost.", JOptionPane.INFORMATION_MESSAGE);
						cerrar();
					}
				}
			}
		});
		btn6_0.setBackground(new Color(255, 255, 255));
		btn6_0.setForeground(new Color(255, 215, 0));
		btn6_0.setFont(new Font("Super Mario 256", Font.PLAIN, 45));
		panel_1.add(btn6_0);

		btn6_1 = new JButton("?");
		btn6_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn6_1.setText("X");
				if (gameMatrix[6][1] == 1)
					btn6_1.setForeground(new Color(0, 255, 0));
				else {
					turn++;
					btn6_1.setForeground(new Color(255, 0, 0));
					if (turn <= 3) {
						JOptionPane.showMessageDialog(null, "You failed.", "Looses turn.", JOptionPane.WARNING_MESSAGE);
						btn6_1.setForeground(new Color(255, 215, 0));
						btn6_1.setText("?");
						resetPathInGui();
					} else {
						JOptionPane.showMessageDialog(null, "You lost all your turns.", "Game lost.", JOptionPane.INFORMATION_MESSAGE);
						cerrar();
					}
				}
			}
		});
		btn6_1.setBackground(new Color(255, 255, 255));
		btn6_1.setForeground(new Color(255, 215, 0));
		btn6_1.setFont(new Font("Super Mario 256", Font.PLAIN, 45));
		panel_1.add(btn6_1);

		btn6_2 = new JButton("?");
		btn6_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn6_2.setText("X");
				if (gameMatrix[6][2] == 1)
					btn6_2.setForeground(new Color(0, 255, 0));
				else {
					turn++;
					btn6_2.setForeground(new Color(255, 0, 0));
					if (turn <= 3) {
						JOptionPane.showMessageDialog(null, "You failed.", "Looses turn.", JOptionPane.WARNING_MESSAGE);
						btn6_2.setForeground(new Color(255, 215, 0));
						btn6_2.setText("?");
						resetPathInGui();
					} else {
						JOptionPane.showMessageDialog(null, "You lost all your turns.", "Game lost.", JOptionPane.INFORMATION_MESSAGE);
						cerrar();
					}
				}
			}
		});
		btn6_2.setBackground(new Color(255, 255, 255));
		btn6_2.setForeground(new Color(255, 215, 0));
		btn6_2.setFont(new Font("Super Mario 256", Font.PLAIN, 45));
		panel_1.add(btn6_2);

		btn7_0 = new JButton("?");
		btn7_0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn7_0.setText("X");
				if (gameMatrix[7][0] == 1) {
					btn7_0.setForeground(new Color(0, 255, 0));
					JOptionPane.showMessageDialog(null, "You win Memory Path game", "Winner!", JOptionPane.INFORMATION_MESSAGE);
					resultado = true;
					cerrar();
				} else {
					turn++;
					btn7_0.setForeground(new Color(255, 0, 0));
					if (turn <= 3) {
						JOptionPane.showMessageDialog(null, "You failed.", "Looses turn.", JOptionPane.WARNING_MESSAGE);
						btn7_0.setForeground(new Color(255, 215, 0));
						btn7_0.setText("?");
						resetPathInGui();
					} else {
						JOptionPane.showMessageDialog(null, "You lost all your turns.", "Game lost.", JOptionPane.INFORMATION_MESSAGE);
						cerrar();
					}
				}
			}
		});
		btn7_0.setBackground(new Color(255, 255, 255));
		btn7_0.setForeground(new Color(255, 215, 0));
		btn7_0.setFont(new Font("Super Mario 256", Font.PLAIN, 45));
		panel_1.add(btn7_0);

		btn7_1 = new JButton("?");
		btn7_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn7_1.setText("X");
				if (gameMatrix[7][1] == 1) {
					btn7_1.setForeground(new Color(0, 255, 0));
					JOptionPane.showMessageDialog(null, "You win Memory Path game", "Winner!", JOptionPane.INFORMATION_MESSAGE);
					resultado = true;
					cerrar();
				} else {
					turn++;
					btn7_1.setForeground(new Color(255, 0, 0));
					if (turn <= 3) {
						JOptionPane.showMessageDialog(null, "You failed.", "Looses turn.", JOptionPane.WARNING_MESSAGE);
						btn7_1.setForeground(new Color(255, 215, 0));
						btn7_1.setText("?");
						resetPathInGui();
					} else {
						JOptionPane.showMessageDialog(null, "You lost all your turns.", "Game lost.", JOptionPane.INFORMATION_MESSAGE);
						cerrar();
					}
				}
			}
		});
		btn7_1.setBackground(new Color(255, 255, 255));
		btn7_1.setForeground(new Color(255, 215, 0));
		btn7_1.setFont(new Font("Super Mario 256", Font.PLAIN, 45));
		panel_1.add(btn7_1);

		btn7_2 = new JButton("?");
		btn7_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btn7_2.setText("X");
				if (gameMatrix[7][2] == 1) {
					btn7_2.setForeground(new Color(0, 255, 0));
					JOptionPane.showMessageDialog(null, "You win Memory Path game", "Winner!", JOptionPane.INFORMATION_MESSAGE);
					resultado = true;
					cerrar();
				} else {
					turn++;
					btn7_2.setForeground(new Color(255, 0, 0));
					if (turn <= 3) {
						JOptionPane.showMessageDialog(null, "You failed.", "Looses turn.", JOptionPane.WARNING_MESSAGE);
						btn7_2.setForeground(new Color(255, 215, 0));
						btn7_2.setText("?");
						resetPathInGui();
					} else {
						JOptionPane.showMessageDialog(null, "You lost all your turns.", "Game lost.", JOptionPane.INFORMATION_MESSAGE);
						cerrar();
					}
				}
			}
		});
		btn7_2.setBackground(new Color(255, 255, 255));
		btn7_2.setForeground(new Color(255, 215, 0));
		btn7_2.setFont(new Font("Super Mario 256", Font.PLAIN, 45));
		panel_1.add(btn7_2);
	}

	public void cerrar() {
		dispose();
		synchronized (lock) {
			lock.notify();
		}
	}

	public void generateRandomPath() {
		for (int rowCount = 0; rowCount < 8; rowCount++) {
			Random rand = new Random();
			int randColumn = rand.nextInt(3);
			gameMatrix[rowCount][randColumn] = 1;
		}
	}

	public void resetPathInGui() {

		btn0_0.setForeground(new Color(255, 215, 0));
		btn0_0.setText("?");
		btn0_1.setForeground(new Color(255, 215, 0));
		btn0_1.setText("?");
		btn0_2.setForeground(new Color(255, 215, 0));
		btn0_2.setText("?");
		btn1_0.setForeground(new Color(255, 215, 0));
		btn1_0.setText("?");
		btn1_1.setForeground(new Color(255, 215, 0));
		btn1_1.setText("?");
		btn1_2.setForeground(new Color(255, 215, 0));
		btn1_2.setText("?");
		btn2_0.setForeground(new Color(255, 215, 0));
		btn2_0.setText("?");
		btn2_1.setForeground(new Color(255, 215, 0));
		btn2_1.setText("?");
		btn2_2.setForeground(new Color(255, 215, 0));
		btn2_2.setText("?");
		btn3_0.setForeground(new Color(255, 215, 0));
		btn3_0.setText("?");
		btn3_1.setForeground(new Color(255, 215, 0));
		btn3_1.setText("?");
		btn3_2.setForeground(new Color(255, 215, 0));
		btn3_2.setText("?");
		btn4_0.setForeground(new Color(255, 215, 0));
		btn4_0.setText("?");
		btn4_1.setForeground(new Color(255, 215, 0));
		btn4_1.setText("?");
		btn4_2.setForeground(new Color(255, 215, 0));
		btn4_2.setText("?");
		btn5_0.setForeground(new Color(255, 215, 0));
		btn5_0.setText("?");
		btn5_1.setForeground(new Color(255, 215, 0));
		btn5_1.setText("?");
		btn5_2.setForeground(new Color(255, 215, 0));
		btn5_2.setText("?");
		btn6_0.setForeground(new Color(255, 215, 0));
		btn6_0.setText("?");
		btn6_1.setForeground(new Color(255, 215, 0));
		btn6_1.setText("?");
		btn6_2.setForeground(new Color(255, 215, 0));
		btn6_2.setText("?");
		btn7_0.setForeground(new Color(255, 215, 0));
		btn7_0.setText("?");
		btn7_1.setForeground(new Color(255, 215, 0));
		btn7_1.setText("?");
		btn7_2.setForeground(new Color(255, 215, 0));
		btn7_2.setText("?");
	}
}
