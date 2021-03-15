package net.therap;

import java.util.Arrays;

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
        Processor process = new Processor(fileName);
        Summary[] summaries = process.getSummary();

        if (sortFlag) {
            Arrays.sort(summaries);
        }
        System.out.println("Time                 GET / POST  Total Response Time  Unique URI Count");
        for (Summary smr : summaries) {
            if (smr.getGetPostCount() != 0) {
                System.out.println(smr.getFormatedString());
            }
        }
    }
}
