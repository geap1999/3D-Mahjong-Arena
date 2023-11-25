package Tuiles;

import javax.swing.ImageIcon;

import GUI.IGMTuile;

public class TuileEcran implements IGMTuile {
 private boolean highlighted = false;
 private int x, y, z;
 private ImageIcon image;

 public TuileEcran(int x, int y, int z, ImageIcon image) {
  super();
  this.x = x;
  this.y = y;
  this.z = z;
  this.image = image;
 }

 @Override
 public ImageIcon getImage() {
  return image;
 }

 @Override
 public int getX() {
  return x;
 }

 @Override
 public int getY() {
  return y;
 }

 @Override
 public int getZ() {
  return z;
 }

 @Override
 public boolean isHighlighted() {
  return highlighted;
 }

 @Override
 public void setHighlighted(boolean b) {
  highlighted = b;
 }
}
