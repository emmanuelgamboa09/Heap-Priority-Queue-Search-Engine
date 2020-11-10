package mvc;

/**
 * All of our options for our Gui Menu bar
 * @author Emmanuel Gamboa
 *
 */
public interface AppFactory
{
   public Model makeModel();
   
   public String[] getEditCommands();
   
   public Command makeEditCommand(Model model, String type);
   
   public String getTitle();
   
   public String[] getHelp();
   
   public String about();
}
