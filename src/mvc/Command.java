package mvc;

/**
 * Abstract class for our commands that we need for our gui
 * @author Emmanuel Gamboa
 *
 */
public abstract class Command
{
   protected Model model;
  
   /*
	 * Constructor
	 * @param model sets our model to model
	 */
   public Command(Model model)
   {
      this.model = model;
   }
   
   public abstract void execute();
}
