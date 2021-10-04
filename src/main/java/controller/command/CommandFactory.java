package controller.command;

/**
 * Factory method to retrieve particular command by command name
 */
public class CommandFactory {

	/**
	 * Secure constructor, to prevent instantiating this class
	 */
	private CommandFactory() {
	}

	/**
	 * Retrieves command by command name. If there is no command with given name then returns default command
	 * @param action command name
	 * @return object of command
	 */
	public static Command getCommand(String action) {
		Command command;
		CommandEnum com = null;
		try {
			com = CommandEnum.valueOf(action.toUpperCase());
			command = com.getCommand();
		} catch (IllegalArgumentException e) {
			command = CommandEnum.DEFAULT.getCommand();
		}
		return command;
	}

}
