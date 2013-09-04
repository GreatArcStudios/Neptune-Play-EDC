
/*
    In this applet, the user can draw straight lines by clicking and dragging
    and can draw filled rectangles by right-clicking and dragging.  Shift-
    clicking will clear the applet.  The picture is stored in an off-screen
    image, so that the screen can be redrawn as necessary.  A "rubber-band
    cursor" is implemented by drawing the shape the user is sketching
    on top of the stored image.  Since Swing already implements double buffering,
    this applet is, in effect, using triple buffering.
    
    It is assumed that this applet will not change size after it is created.
    The off-screen image is created in the applet's init() method and 
    will not be resized if the applet's size is changed.
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class RubberBand extends JApplet {
               
   Image offScreenImage;  // An in-memory image that is used to hold a
                          // copy of the picture that is displayed
                          // on the applet (not including the figure
                          // that the user is currently drawing, if any).
                          
   public void init() {
         // The applet's init() method creates a drawing panel to be
         // used as the drawing surface of the applet.  It also
         // creates the off-screen image and fills it with a white
         // rectangle with a black border.
      setContentPane( new Display() );
      offScreenImage = createImage(getSize().width, getSize().height);
      Graphics g = offScreenImage.getGraphics();
      g.setColor(Color.white);
      g.fillRect(0,0,getSize().width,getSize().height);
      g.setColor(Color.black);
      g.drawRect(0,0,getSize().width-1,getSize().height-1);
      g.dispose();
   }
   

   class Display extends JPanel
               implements MouseListener, MouseMotionListener{
          // This nested class represents the drawing surface of the applet.
          // It implements all the programming necessary to let the user
          // draw on the applet.

      boolean heardMouse = false;  // Set to true the first time the user clicks on
                                   //   the applet.  Until that time, some instructions
                                   //   are drawn by the paintComponent() method.

      /* Some variables used during dragging. */

      boolean dragging;    // This is set to true when a drag begins, and to false
                           //    when it ends.  The value is checked by mouseReleased()
                           //    and mouseDragged().

      boolean drawingLine; // The user can draw lines or rects.  This variable is true if
                           //    the user is drawing a line, and false if the user is drawing
                           //    a rectangle.

      int startx, starty;  // The location of the mouse when the dragging started.

      int mousex, mousey;    // The location of the mouse during dragging.


      Display() {
            // Constructor sets the background color to white and
            // set up this panel to listen for mouse events on itself.
         setBackground(Color.white);
         addMouseListener(this);
         addMouseMotionListener(this);
      }


      public void paintComponent(Graphics g) {
             // Copy the off-screen image to the screen.  If there
             // have not yet been any user actions, show some instructions.
             // If the user is dragging, draw the figure that the user
             // is sketching on top of the permanent picture from
             // the off-screen image.  (Note:  There is no need to
             // call super.paintComponent(g) since this paintComponent
             // method fills in the entire component.)
         g.drawImage(offScreenImage,0,0,null);
         if (heardMouse == false) {
            g.setColor(Color.red);
            g.drawString("Click and drag to draw lines.", 10, 15);
            g.drawString("Right-click and drag to draw rects.", 10, 30);
            g.drawString("Shift-click to clear.", 10, 45);
         }
         else if (dragging) {
            drawFigure(g);
         }
      }


      void drawFigure(Graphics g) {
              // This is called during dragging to draw the line or rect
              // between the starting position of the mouse and the current
              // position.  The figure is drawn in the graphics context g.
              // The variables startx, starty, mousex, mousey, and drawingLine
              // contain the information needed to draw the figure.
         if (drawingLine) {
                // Draw a red line, but only if the two points are different.
            if (startx != mousex || starty != mousey) {
               g.setColor(Color.red);
               g.drawLine(startx, starty, mousex, mousey);
            }
         }
         else {
                // Draw a blue rect, but only if it is non-empty.
            if (startx != mousex && starty != mousey) {
               int x, y;  // Top left corner of the rectangle.
               int w, h;  // Width and height of the rectangle.
                   // x,y,w,h must be computed from the coordinates
                   // of the two corner points.
               if (mousex > startx) {
                  x = startx;
                  w = mousex - startx;
               }
               else {
                  x = mousex;
                  w = startx - mousex;
               }
               if (mousey > starty) {
                  y = starty;
                  h = mousey - starty;
               }
               else {
                  y = mousey;
                  h = starty - mousey;
               }
               g.setColor(Color.blue);
               g.fillRect(x, y, w, h);
               g.setColor(Color.black);
               g.drawRect(x, y, w-1, h-1);
            }
         }
      }  // end drawFigure()


      public void mousePressed(MouseEvent evt) {
             // Respond when the user presses a mouse button.

         if (dragging)  // If already dragging, don't do anything.
            return;     //  (This can happen if the user presses two mouse buttons.

         if (heardMouse == false) {
               // Erase the instructions, if this is the first mause click.
            heardMouse = true;
            repaint();
         }

         if (evt.isShiftDown()) {
                // If the shift key is down, just erase the applet.
            Graphics g = offScreenImage.getGraphics();
            g.setColor(Color.white);
            g.fillRect(0,0,getSize().width,getSize().height);
            g.setColor(Color.black);
            g.drawRect(0,0,getSize().width-1,getSize().height-1);
            g.dispose();
            repaint();
            return;
         }

         startx = mousex = evt.getX();  // Save coords of mouse position.
         starty = mousey = evt.getY();

         drawingLine = (evt.isMetaDown() == false); // Check if it's a right-click.

         dragging = true;  // Start dragging.

      }  // end mousePressed()


      public void mouseReleased(MouseEvent evt) {
             // End the dragging operation, if one is in progress.  Draw
             // the final figure, if any onto the off-screen canvas, so
             // it becomes a permanent part of the image.
         if (dragging) {
            Graphics g = offScreenImage.getGraphics();
            drawFigure(g);
            g.dispose();
            dragging = false;
            repaint();
         }
      }

      public void mouseDragged(MouseEvent evt) {
              // If a dragging operation is in progress, get the new
              // values for mousex and mousey, and repaint.
         if (dragging) {
            mousex = evt.getX();
            mousey = evt.getY();
            repaint();
         }
      }

      public void mouseClicked(MouseEvent evt) { }  // Empty methods, required by
      public void mouseEntered(MouseEvent evt) { }  //   the MouseEvent and
      public void mouseExited(MouseEvent evt) { }   //   MouseListener intefaces.
      public void mouseMoved(MouseEvent evt) { }
      
   } // end nested class Display


}  // end class RubberBand