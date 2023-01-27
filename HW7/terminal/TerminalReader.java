package terminal;


import terminal.executable.Command;
import terminal.executable.CommandExecutable;
import terminal.executable.CommandExecutableFactory;
import terminal.executable.LoggingCommandExecutableFactoryImpl;
import view.CommandView;
import view.LoggingCommandExecutableFactoryImplView;

import java.util.Scanner;

public class TerminalReader extends CommandView {
    private static TerminalReader terminalReader;
    private final CommandParser commandParser;
    private final CommandExecutableFactory commandExecutableFactory;


    private TerminalReader() {
        commandParser = new CommandParserImpl();//реализация парсера
        commandExecutableFactory = new LoggingCommandExecutableFactoryImpl(new LoggingCommandExecutableFactoryImplView());
    }

    public static TerminalReader getInstance() {
        if (terminalReader == null) {
            terminalReader = new TerminalReader();
        }
        return terminalReader;
    }

    public void listen() {
        Scanner sc = new Scanner(System.in);
        String cmd;
        while (true) {
            terminalCmdView.wellcome();
            cmd = sc.nextLine();
            if (cmd.equals("exit"))
                break;
            Command cmds = commandParser.parseCommand(cmd);
            CommandExecutable commandExecutable = commandExecutableFactory.create(cmds);
            if (commandExecutable != null)
                commandExecutable.execute();
        }
    }
}
