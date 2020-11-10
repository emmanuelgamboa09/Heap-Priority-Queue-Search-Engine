package mvc;

import java.awt.event.*;
import java.io.*;
import java.util.Random;

import javax.swing.*;

/**
 * Utilities that creating our gui easier
 * @author Emmanuel Gamboa
 *
 */
public class Utilities {
   
   // Asks the user for an answer based off of a yes or no question
   public static boolean confirm(String query) {
      int result = JOptionPane.showConfirmDialog(null,
            query, "choose one", JOptionPane.YES_NO_OPTION);
      return result == 1; 
   }  
   
   // Asks the user for info based off the the question given
   public static String ask(String query) {
      return JOptionPane.showInputDialog(null, query);
   }
   
   // Informs the user about the given information
   public static void inform(String info) {
      JOptionPane.showMessageDialog(null,info);
   }
   
   // Informs the user about the given array of information
   public static void inform(String[] items) {
      String helpString = "";
      for(int i = 0; i < items.length; i++) {
         helpString = helpString + "\n" + items[i];
      }
      inform(helpString);
   }
   
   // Notifies the user about an error that has occured
   public static void error(String gripe) {
      JOptionPane.showMessageDialog(null,
            gripe,
            "OOPS!",
            JOptionPane.ERROR_MESSAGE);
   }
   
   // Notifies the user about an exception that has occured
   public static void error(Exception gripe) {
      gripe.printStackTrace();
      JOptionPane.showMessageDialog(null,
            gripe.getMessage(),
            "OOPS!",
            JOptionPane.ERROR_MESSAGE);
   }
   
   // Ask the user to save the changes
   public static void saveChanges(Model model) {
      if (model.getUnsavedChanges() && Utilities.confirm("current model has unsaved changes, continue?"))
         Utilities.save(model, false);
   }
   
   // aAsks the user to give a fileName
   public static String getFileName(String fName, Boolean open) {
      JFileChooser chooser = new JFileChooser();
      String result = null;
      if (fName != null) {
         // open chooser in directory of fName
           chooser.setCurrentDirectory(new File(fName));
      }
      if (open) {
         int returnVal = chooser.showOpenDialog(null);
         if(returnVal == JFileChooser.APPROVE_OPTION) {
            result= chooser.getSelectedFile().getPath();
         }
      } else {
         int returnVal = chooser.showSaveDialog(null);
         if(returnVal == JFileChooser.APPROVE_OPTION) {
            result= chooser.getSelectedFile().getPath();
         }
      }
      return result;
   }
   
   // Saves the given model
   public static void save(Model model, Boolean saveAs) {
      String fName = model.getFileName();
      if (fName == null || saveAs) {
         fName = getFileName(fName, false);
         model.setFileName(fName);
      }
      try {
         ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(fName));
         model.setUnsavedChanges(false);
         os.writeObject(model);
         os.close();
      } catch (Exception err) {
         model.setUnsavedChanges(true);
         Utilities.error(err);
      }
   }
   
   // Opens the given model
   public static Model open(Model model) {
      saveChanges(model);
      String fName = getFileName(model.getFileName(), true);
      Model newModel = null;
      try {
         ObjectInputStream is = new ObjectInputStream(new FileInputStream(fName));
         newModel = (Model)is.readObject();
         is.close();
      } catch (Exception err) {
         Utilities.error(err);
      }
      return newModel;
   }
   
   // Creates a simple menu bar
   public static JMenu makeMenu(String name, String[] items, ActionListener handler) {
      JMenu result = new JMenu(name);
      for(int i = 0; i < items.length; i++) {
         JMenuItem item = new JMenuItem(items[i]);
         item.addActionListener(handler);
         result.add(item);
      }
      return result;
   }
   
   public static Random rng = new Random(System.currentTimeMillis());

}
