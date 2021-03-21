package net.therap.controller;

import net.therap.model.LogInfo;
import net.therap.model.Summary;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

import static net.therap.controller.PatternProcessor.HOURS_IN_A_DAY;
import static net.therap.controller.PatternProcessor.getCheck;

/**
 * @author sadia.afroz
 * @since 3/10/21
 */
public class Processor {
    private String fileName;
    static final String URI = "URI=[";

    public Processor(String fileName) {
        this.fileName = fileName;
    }

    public List<LogInfo> processLogInfo() {
        List<LogInfo> necessaryLogs = new ArrayList<LogInfo>();

        File file = new File(fileName);
        FileReader fr = null;
        try {
            fr = new FileReader(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String data = null;
        BufferedReader br = new BufferedReader(fr);
        try {
            data = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (data != null) {
            if (data.contains(URI)) {
                LogInfo logLine = new LogInfo(data);
                necessaryLogs.add(logLine);
            }
            try {
                data = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return necessaryLogs;
    }

    public Summary[] getSummary(List<LogInfo> logs) {

        Summary[] summaries = new Summary[HOURS_IN_A_DAY + 1];
        for (int i = 0; i <= HOURS_IN_A_DAY; i++) {
            summaries[i] = new Summary(i);
        }
        for (LogInfo log : logs) {
            String data = log.getLogLine();
            String[] information = data.split(" ");
            Matcher m = PatternProcessor.timePattern.matcher(data);

            if (m.find()) {
                int startTime;
                startTime = Integer.parseInt(m.group().split(":")[0]);
                Matcher m2 = PatternProcessor.necessaryInformationPattern.matcher(data);
                if (m2.find()) {
                    String[] splittedInfo = m2.group().split(",");

                    String uri = splittedInfo[0].replaceAll(".*\\[|\\].*", "");
                    String getPost = splittedInfo[1].trim();
                    int responseTime = Integer.parseInt(splittedInfo[2].replaceAll(".*\\=|ms.*", ""));
                    summaries[startTime].addURI(uri);
                    summaries[startTime].incrementResponsetime(responseTime);

                    if (getPost.equals(getCheck)) {
                        summaries[startTime].incrementGetCount();
                    } else {
                        summaries[startTime].incrementPostCount();
                    }
                }
            }

        }
        return summaries;
    }
}

