import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Frame extends JFrame implements KeyListener{

	private Model model;

	public Frame(Model model){
		this.model = model;

		View view = new View(this.model);
		this.getContentPane().add(view);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500, 500);
		this.setTitle("Dungeon Crawler");
		addKeyListener(this);

		this.setVisible(true);
	}

	public void keyPressed(KeyEvent e) {
		System.err.println("Something was pressed.");
		if (e.getKeyChar() == 'w') {
			System.out.println(e.getKeyChar() + " was pressed");
			model.setPlayerUp(true);
		}
		else if (e.getKeyChar() == 's') {
			System.out.println(e.getKeyChar() + " was pressed");
			model.setPlayerDown(true);
		}
		else if (e.getKeyChar() == 'a') {
			System.out.println(e.getKeyChar() + " was pressed");
			model.setPlayerLeft(true);
		}
		else if (e.getKeyChar() == 'd') {
			System.out.println(e.getKeyChar() + " was pressed");
			model.setPlayerRight(true);
		}
	}

	public void keyReleased(KeyEvent e) {
		if (e.getKeyChar() == 'w') {model.setPlayerUp(false);}
		else if (e.getKeyChar() == 's') {model.setPlayerDown(false);}
		else if (e.getKeyChar() == 'a') {model.setPlayerLeft(false);}
		else if (e.getKeyChar() == 'd') {model.setPlayerRight(false);}
	}

	public void keyTyped(KeyEvent arg0) {}
}
