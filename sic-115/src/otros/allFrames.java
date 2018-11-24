/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otros;

import java.awt.Image;
import java.awt.Toolkit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UnsupportedLookAndFeelException;
import sic115.catalogoVista;

/**
 *
 * @author sic
 */
//clase para aplicar algunos formatos sin instancias :D
public class allFrames {

    public static void aplicarTema(JFrame j) {
        try {
            String s = "";
            s = "com.jtattoo.plaf.hifi.HiFiLookAndFeel";
            s = "com.jtattoo.plaf.aluminium.AluminiumLookAndFeel";
            javax.swing.UIManager.setLookAndFeel(s);
            SwingUtilities.updateComponentTreeUI(j);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(allFrames.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(allFrames.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(allFrames.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(allFrames.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void aplicarTemaDialogo(javax.swing.JDialog j) {

        try {
            String s = "";
            s = "com.jtattoo.plaf.hifi.HiFiLookAndFeel";
            s = "com.jtattoo.plaf.aluminium.AluminiumLookAndFeel";
            javax.swing.UIManager.setLookAndFeel(s);
            SwingUtilities.updateComponentTreeUI(j);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(allFrames.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(allFrames.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(allFrames.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(allFrames.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void CargarIcono(JFrame j) {

        j.setIconImage(new ImageIcon(getClass().getResource("/imagenes/panesal.png")).getImage().getScaledInstance(80, 80, 200));

    }
 public void CargarIcono(javax.swing.JDialog j) {

        j.setIconImage(new ImageIcon(getClass().getResource("/imagenes/panesal.png")).getImage().getScaledInstance(80, 80, 200));

    }

}
