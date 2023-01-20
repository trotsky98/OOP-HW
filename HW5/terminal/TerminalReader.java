package terminal;


import repository.StudentRepository;
import service.StudentService;
import terminal.executable.Command;
import terminal.executable.CommandExecutable;
import terminal.executable.CommandExecutableFactory;
import terminal.executable.LoggingCommandExecutableFactoryImpl;
import view.StudentView;
import view.TerminalCmdView;

import java.util.Scanner;

public class TerminalReader {
    private static TerminalReader terminalReader;
    private final CommandParser commandParser;


    private TerminalReader(CommandParser commandParser) {
        this.commandParser = commandParser;
    }

    public static TerminalReader getInstance(CommandParser commandParser) {
        if (terminalReader == null) {
            terminalReader = new TerminalReader(commandParser);
        }
        return terminalReader;
    }
    CommandExecutableFactory commandExecutableFactoryImpl = new LoggingCommandExecutableFactoryImpl(new StudentService(new StudentRepository()),new StudentView(),new TerminalCmdView());//logging
    public void listen() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            String cmd = sc.nextLine();
            Command cmds = commandParser.parseCommand(cmd);
            CommandExecutable commandExecutable = commandExecutableFactoryImpl.create(cmds);
            if (commandExecutable != null) {
                commandExecutable.execute();
            }
        }
    }
}
