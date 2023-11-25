package GUI;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class IGMahjong extends JFrame {
	private static final long serialVersionUID = 1L;
private IGMPanel jp;
 private IGMTuile[][][] tank = new IGMTuile[100][100][15];
 private ImageIcon highlight;
 private JTextField jtf = new JTextField(60);
 private JPanel buttonPanel;
 private MahjongButtonListener jbl;
 private MahjongControler controler;
 public void setControler(MahjongControler controler) {
  this.controler = controler;
  jbl = new MahjongButtonListener(controler);
 }
 private boolean simple = false;
 public IGMahjong(boolean simple) {
  this.simple = simple;
  buttonPanel = new JPanel();
  this.jp = new IGMPanel(this.tank);
  jp.setPreferredSize(new Dimension(1000,700));
  buttonPanel.setPreferredSize(new Dimension(1000,35));
  jp.addMouseListener(new IGMMouseListener(this));
  jp.add(jtf);
  jp.add(buttonPanel);
  this.add(jp);
  jp.add(jtf);
  this.pack();
  jtf.setFont(new Font("default", Font.BOLD, 16));
  this.setVisible(true);
 }
 public void ajouterTuile(ImageIcon im, int x, int y, int z) {
  if (this.simple)
   tank[x*2][y*2][z] = new IGMIcon(im,x*2,y*2,z);   
  else
   tank[x][y][z] = new IGMIcon(im,x,y,z);
  this.repaint();
 }
 public void ajouterTuile(IGMTuile igmt) {
  if (this.simple)
   tank[igmt.getX()*2][igmt.getY()*2][igmt.getZ()]=igmt;
  else 
   tank[igmt.getX()][igmt.getY()][igmt.getZ()]=igmt;
  this.repaint();
 }
 private IGMTuile findPosClicked(int xx, int yy) {
  for (int z=14; z>=0; z--)
   for (int x = 0; x<100; x++)
    for (int y=0; y<100; y++)
     if (tank[x][y][z]!=null)
      if (this.covers(tank[x][y][z],xx, yy)) {
       return tank[x][y][z];
      }
  return null;
      
 }
 private boolean covers(IGMTuile igmt, int a, int b) {
  int posX, posY;
  if (this.simple) {
   posY = igmt.getY()*54+50-igmt.getZ()*8;
   posX = igmt.getX()*70+90-igmt.getZ()*8;   
  }else {
   posY = igmt.getY()*27+50-igmt.getZ()*8;
   posX = igmt.getX()*35+90-igmt.getZ()*8;
  }
  return (a >= posX) && (a < posX+50) && (b >= posY) && (b < posY+50);
 }
 void reportClic(int x, int y) {
  IGMTuile igic;
  igic = findPosClicked(x,y);
  if (igic != null) {
   if (controler != null)
    controler.clic(igic.getX(),igic.getY(),igic.getZ());
  }
 }
 public void highlight(int x, int y, int z) {
  jtf.setText("");
  if (this.simple)
   tank[x*2][y*2][z].setHighlighted(true);
  else
   tank[x][y][z].setHighlighted(true);
  this.repaint();
 }
 public void setMessage(String string) {
  jtf.setText(string);  
 }
 public void retirerPaire(int x, int y, int z, int posX, int posY, int posZ) {
  if (this.simple) {
   tank[x*2][y*2][z]=null;
   tank[posX*2][posY*2][posZ]=null;
  }else {
   tank[x][y][z]=null;
   tank[posX][posY][posZ]=null;
  }
  this.repaint();
 }
 public void retirerPaire(IGMTuile igmt1, IGMTuile igmt2) {
  if (this.simple) {
   tank[igmt1.getX()*2][igmt1.getY()*2][igmt1.getZ()]=null;
   tank[igmt2.getX()*2][igmt2.getY()*2][igmt2.getZ()]=null;
  }else {
   tank[igmt1.getX()][igmt1.getY()][igmt1.getZ()]=null;
   tank[igmt2.getX()][igmt2.getY()][igmt2.getZ()]=null;
  }
  this.repaint();
 }
 public void unhighlight(int x, int y, int z) {
  if (tank[x][y][z] != null)
   tank[x][y][z].setHighlighted(false);
  this.repaint();   
 }
 public void unhighlight() {
  for (int z=14; z>=0; z--)
   for (int x = 0; x<100; x++)
    for (int y=0; y<100; y++)
     if (tank[x][y][z] != null)
      tank[x][y][z].setHighlighted(false);
  this.repaint();   
 }
 public void ajouterBouton(String ident) {
  JButton newbutton = new JButton(ident);
  if (jbl != null)
   jbl.addButton(ident, newbutton);
  buttonPanel.add(newbutton);
  buttonPanel.updateUI();
 }
}