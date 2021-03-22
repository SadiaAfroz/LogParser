package net.therap;

import net.therap.controller.Processor;
import net.therap.model.LogInfo;
import net.therap.model.Summary;
import net.therap.view.LogView;

import java.util.Arrays;
import java.util.List;

/**
 * @author sadia.afroz
 * @since 3/10/21
 */
public class LogParser {
    public static void main(String[] args) {

        boolean sortFlag = false;

        if (args.length > 1) {
            sortFlag = args[1].equals("--sort");
        }
        String fileName = args[0];

        Processor processor = new Processor(fileName);
        List<LogInfo> logs = processor.processLogInfo();    // collecting only necessary lines
        Summary[] summaries = processor.getSummary(logs);

        if (sortFlag) {
            Arrays.sort(summaries);
        }
        LogView logView = new LogView();
        logView.printSummary(summaries);
    }
}
