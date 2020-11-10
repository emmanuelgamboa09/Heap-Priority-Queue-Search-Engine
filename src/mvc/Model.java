package mvc;

/**
 * Our base model to outline our models 
 * for our gui
 * @author Emmanuel Gamboa
 *
 */
public abstract class Model extends Bean {
   private static final long serialVersionUID = 1L;

   private String fileName = null;
   private Boolean unsavedChanges = false;
   
   /**
    * Returns the name of the file
    * @return fileName file name
    */
   public String getFileName() {
      return fileName;
   }
   
   /**
    * Sets our file name
    * @param fileName name of file
    */
   public void setFileName(String fileName) {
      this.fileName = fileName;
   }
   
   /**
    * Returns true if the file is saved, false if it isn't save
    * @return boolean value
    */
   public Boolean getUnsavedChanges() {
      return unsavedChanges;
   }
   
   /**
    * Sets unSavedChanges to unSavedChanges
    * @param unsavedChanges
    */
   public void setUnsavedChanges(Boolean unsavedChanges) {
      this.unsavedChanges = unsavedChanges;
   }

   /**
    * If a change is performed, unsavedChanges is set to true 
    */
   public void changed() {
      unsavedChanges = true;
      firePropertyChange(null, null, null);
   }
}
