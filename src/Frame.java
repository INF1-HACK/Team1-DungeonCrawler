//package com.boredInteractive.humdrum;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
public class Frame extends JFrame implements KeyListener
{
	private final int startingWidth  = 850;
	private final int startingHeight = 600;
	private static final long serialVersionUID = 1L;
	private Model model;
	
	private boolean upPressed = false;
	private boolean downPressed = false;
	private boolean leftPressed = false;
	private boolean rightPressed = false;
	private boolean escapePressed = false;
	private boolean pausePressed = false;
	
	public Frame(Model input_model, View input_view)
	{
		model = input_model;
		this.setTitle("Boring, Dull, Mundane: Humdrum.");
		setIconImage(new ImageIcon("logo.png").getImage());
		this.setSize(startingWidth, startingHeight);
		this.setLocationRelativeTo(null);
		this.add(input_view);
		this.addKeyListener(this);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	@Override
	public void keyPressed(KeyEvent k) 
	{
		switch (k.getKeyCode())
		{
			case KeyEvent.VK_UP:
			case KeyEvent.VK_W:
				if (!upPressed)
				{
					upPressed = true;
					model.upPressed();
				}
				break;
			case KeyEvent.VK_DOWN:
			case KeyEvent.VK_S:
				if (!downPressed)
				{
					downPressed = true;
					model.downPressed();
				}
				break;
			case KeyEvent.VK_LEFT:
			case KeyEvent.VK_A:
				if (!leftPressed)
				{
					leftPressed = true;
					model.leftPressed();
				}
				break;
			case KeyEvent.VK_RIGHT:
			case KeyEvent.VK_D:
				if (!rightPressed)
				{
					rightPressed = true;
					model.rightPressed();
				}
				break;
			case KeyEvent.VK_ESCAPE:
				if (!escapePressed)
				{
					escapePressed = true;
					model.close();
				}
				break;
			case KeyEvent.VK_P:
				if (!pausePressed)
				{
					pausePressed = true;
					model.pPressed();
				}	
				break;
		}
	}
	@Override
	public void keyReleased(KeyEvent k) 
	{
		switch (k.getKeyCode())
		{
			case KeyEvent.VK_UP:
			case KeyEvent.VK_W:
				if (upPressed)
				{
					upPressed = false;
					model.upReleased();
				}
				break;
			case KeyEvent.VK_DOWN:
			case KeyEvent.VK_S:
				if (downPressed)
				{
					downPressed = false;
					model.downReleased();
				}
				break;
			case KeyEvent.VK_LEFT:
			case KeyEvent.VK_A:
				if (leftPressed)
				{
					leftPressed = false;
					model.leftReleased();
				}
				break;
			case KeyEvent.VK_RIGHT:
			case KeyEvent.VK_D:
				if (rightPressed)
				{
					rightPressed = false;
					model.rightReleased();
				}
				break;
			case KeyEvent.VK_ESCAPE:
				if (escapePressed)
				{
					escapePressed = false;
				}
				break;
			case KeyEvent.VK_P:
				if (pausePressed)
				{
					pausePressed = false;
				}	
				break;
		}
	}
	@Override
	public void keyTyped(KeyEvent k) 
	{
	}
}