import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.RenderingHints.Key;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Drawing {
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		JToolBar Tb = new JToolBar();
		JScrollBar Sb = new JScrollBar();
		frame.setVisible(true);
		frame.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));

		JButton sShot = new JButton("Screenshot");
		JRadioButton blue = new JRadioButton("Blue");
		JRadioButton red = new JRadioButton("Red");
		JRadioButton green = new JRadioButton("Green");
		JRadioButton black = new JRadioButton("Black");
		JRadioButton gray = new JRadioButton("Gray");
		JRadioButton rect = new JRadioButton("rect");
		JRadioButton white = new JRadioButton("White");
		JButton col = new JButton("Color");
		final DrawPad drawPad = new DrawPad();
		Color color = (Color.BLACK);
		JButton clearButton = new JButton("Clear");
		clearButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showConfirmDialog(null, "THIS CAN NOT BE UNDONE, ARE YOU SURE YOU WANT TO CLEAR THE PAGE");
				drawPad.clear();
			}
		});
		blue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				drawPad.graphics2D.setPaint(Color.BLUE);
			}
		});
		red.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				drawPad.graphics2D.setPaint(Color.RED);
			}
		});
		green.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				drawPad.graphics2D.setPaint(Color.GREEN);
			}
		});
		black.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				drawPad.graphics2D.setPaint(Color.BLACK);
			}
		});
		gray.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				drawPad.graphics2D.setPaint(Color.GRAY);
			}
		});
		white.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				drawPad.graphics2D.setPaint(Color.WHITE);
			}
		});
		sShot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {

					Rectangle screenshotRect = new Rectangle(Toolkit
							.getDefaultToolkit().getScreenSize());
					BufferedImage Capture = new Robot()
							.createScreenCapture(screenshotRect);
					ImageIO.write(Capture, "png", new File(
							"SketchPad Screenshot.png"));
					JOptionPane
							.showMessageDialog(null,
									"The file has been saved please change to avoid overwriting");

				} catch (Exception ex) {
					JOptionPane
							.showMessageDialog(null,
									"The file has been saved please change to avoid overwriting");

				}
			}
		});

		col.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color color = (Color.BLACK);
				color = JColorChooser
						.showDialog(null, "Pick Text Color", color);
				drawPad.graphics2D.setPaint(color);
			}
		});
		frame.add(clearButton, BorderLayout.SOUTH);
		Tb.add(blue);
		Tb.add(red);
		Tb.add(green);
		Tb.add(black);
		Tb.add(gray);
		Tb.add(white);
		Tb.addSeparator();
		Tb.add(col);
		Tb.addSeparator();
		Tb.add(sShot);

		JScrollPane Sp = new JScrollPane(drawPad,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		frame.add(Sp, BorderLayout.CENTER);

		frame.add(Tb, BorderLayout.NORTH);
		frame.setMaximumSize(new Dimension(1920, 1080));
		frame.setSize(570, 530);
		frame.setMinimumSize(new Dimension(400, 400));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

	}
}

class DrawPad extends JComponent {

	private static final long serialVersionUID = 1L;
	Image image;
	Graphics2D graphics2D;
	int currentX, currentY, oldX, oldY;
	boolean isright;
	boolean isleft;

	public DrawPad() {
		isright = false;
		isleft = false;
		setDoubleBuffered(false);
		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent Me) {
				oldX = Me.getX();
				oldY = Me.getY();
			}
		});

		addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) {
				currentX = e.getX();
				currentY = e.getY();
				if (SwingUtilities.isRightMouseButton(e) && SwingUtilities.isLeftMouseButton(e)) {
					if (oldX != currentX || oldY != currentY) {
						graphics2D.setColor(Color.RED);
						graphics2D.drawLine(oldX, oldY, currentX, currentY);
						graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
					}
				}
				if (SwingUtilities.isLeftMouseButton(e))

					graphics2D.drawLine(oldX, oldY, currentX, currentY);

				if (SwingUtilities.isRightMouseButton(e))

					graphics2D.draw3DRect(oldX, oldY, currentX, currentY, true);

				
				if (SwingUtilities.isMiddleMouseButton(e)) {
					graphics2D.drawRect(oldX, oldY, currentX, currentY);
					graphics2D.fillRect(oldX, oldY, currentX, currentY);
					graphics2D.setPaint(Color.white);
					
				}
				repaint();
				oldX = currentX;
				oldY = currentY;
				
			}

		});

	}

	public void paintComponent(Graphics g) {
		if (image == null) {
			image = createImage(1920, 1080);
			graphics2D = (Graphics2D) image.getGraphics();
			graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON);
			clear();
		}
		g.drawImage(image, 0, 0, null);
	}

	public void clear() {
		graphics2D.setPaint(Color.WHITE);
		graphics2D.fillRect(0, 0, 1920, 1080);
		graphics2D.setPaint(Color.BLACK);
		repaint();
	}
}