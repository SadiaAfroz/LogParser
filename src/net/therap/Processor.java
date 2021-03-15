package net.therap;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static net.therap.LogParser.HOURS_IN_A_DAY;

/**
 * @author sadia.afroz
 * @since 3/10/21
 */
public class Processor {
    private String fileName;

    public Processor(String fileName) {
        this.fileName = fileName;
    }

    public Summary[] getSummary() {
        Summary[] summaries = new Summary[HOURS_IN_A_DAY + 1];
        for (int i = 0; i <= HOURS_IN_A_DAY; i++) {
            summaries[i] = new Summary(i);
        }

        File file = new File(fileName);
        FileReader fr = null;
        try {
            fr = new FileReader(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String data = null;
        BufferedReader br = new BufferedReader(fr);
        while (true) {
            try {
                if (!((data = br.readLine()) != null)) {
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (data.contains("URI=[")) {
                String[] information = data.split(" ");
                Matcher m = PatternProcessor.timePattern.matcher(data);

                int startTime = 0;
                if (m.find()) {
                    startTime = Integer.parseInt(m.group().split(":")[0]);
                    Matcher m2 = PatternProcessor.necessaryInformationPattern.matcher(data);
                    if (m2.find()) {
                        String[] splittedInfo = m2.group().split(",");

                        String uri = splittedInfo[0].replaceAll(".*\\[|\\].*", "");
                        String get_post = splittedInfo[1].trim();
                        int responseTime = Integer.parseInt(splittedInfo[2].replaceAll(".*\\=|ms.*", ""));

                        summaries[startTime].addURI(uri);
                        summaries[startTime].incrementResponsetime(responseTime);
                        if (get_post.equals("G")) {
                            summaries[startTime].incrementGetCount();
                        } else {
                            summaries[startTime].incrementPostCount();
                        }
                    }
                }
            }
        }
        return summaries;
    }
}

