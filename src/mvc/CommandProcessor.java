package mvc;

/**
 * Executes our Commands
 * @author Emmanuel Gamboa
 *
 */
public abstract class CommandProcessor
{
	
	/*
	 * Performs the Command
	 * @param command which command you want to perform
	 */
   public static void execute(Command cmmd)
   {
      cmmd.execute();
   }
}
