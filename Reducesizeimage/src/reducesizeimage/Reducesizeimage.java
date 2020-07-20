/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reducesizeimage;

import java.awt.BorderLayout;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author gerson7
 */
public class Reducesizeimage {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        // TODO code application logic here
        // Read from an input stream
      InputStream is = new BufferedInputStream(new FileInputStream("C:\\Users\\gerson7\\Documents\\NetBeansProjects\\Reducesizeimage\\src\\reducesizeimage\\cat.1.jpg"));
      BufferedImage image = ImageIO.read(is);
    
   //  JFrame frame = new JFrame();

   
     Image newImage = resizeImage(image,300,150,"gatobig");
 /*    JLabel label = new JLabel(new ImageIcon(newImage));
     frame.getContentPane().add(label, BorderLayout.CENTER);

     frame.pack(); 
     frame.setVisible(true);
     */
     //imagen reducida pasada a buffer 
      BufferedImage imgrb=null; 
     imgrb=toBufferedImage(newImage);
   final File carpeta = new File("C:\\Users\\gerson7\\Documents\\NetBeansProjects\\Reducesizeimage\\src\\reducesizeimage\\dogs");
     listarFicherosPorCarpeta(carpeta );
   
    }
    public static Image resizeImage(BufferedImage img, int width, int height,String name) throws IOException {
      Image imgr  =img.getScaledInstance(width, height, Image.SCALE_DEFAULT);
      BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
      BufferedImage imgb=null; 
      imgb=toBufferedImage(imgr);
      // System.out.print("Nombre de archivo modificado: ");
      String outputfname =name;
      File myNewJPegFile = new File(System.getProperty("user.dir")+"\\src\\reducesizeimage\\imagenperrocambiado\\"+outputfname+".jpg");
      ImageIO.write(imgb, "jpg", myNewJPegFile);
      return  imgr;
    }
    /**
 * Converts a given Image into a BufferedImage
 *
 * @param img The Image to be converted
 * @return The converted BufferedImage
 */
public static BufferedImage toBufferedImage(Image img)
{
    if (img instanceof BufferedImage)
    {
        return (BufferedImage) img;
    }

    // Create a buffered image with transparency
    BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

    // Draw the image on to the buffered image
    Graphics2D bGr = bimage.createGraphics();
    bGr.drawImage(img, 0, 0, null);
    bGr.dispose();

    // Return the buffered image
    return bimage;
}
public  static void  listarFicherosPorCarpeta(final File carpeta) throws FileNotFoundException, IOException {
    for (final File ficheroEntrada : carpeta.listFiles()) {
        if (ficheroEntrada.isDirectory()) {
            listarFicherosPorCarpeta(ficheroEntrada);
        } else {
            System.out.println(ficheroEntrada.getName());
              InputStream is = new BufferedInputStream(new FileInputStream("C:\\Users\\gerson7\\Documents\\NetBeansProjects\\Reducesizeimage\\src\\reducesizeimage\\dogs\\"+ficheroEntrada.getName()));
              BufferedImage image = ImageIO.read(is);
            Image newImage = resizeImage(image,300,250,ficheroEntrada.getName());
        }
    }
}



   
}
