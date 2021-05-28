package nsu.titov.peer;

import picocli.CommandLine;

import java.io.PrintWriter;

public class PrintExceptionMessageHandler implements CommandLine.IParameterExceptionHandler {
    @Override
    public int handleParseException(CommandLine.ParameterException ex, String[] args) throws Exception {
        CommandLine cmd = ex.getCommandLine();
        PrintWriter err = cmd.getErr();

        err.println("Unable to parse input parameters, try bt-client [-h, --help] for help");

        return 0;
    }
}
