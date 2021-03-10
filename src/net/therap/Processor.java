package net.therap;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author sadia.afroz
 * @since 3/10/21
 */
public class Processor {
    private String fileName;

    public Processor(String fileName) {
        this.fileName = fileName;
    }
    public Summary[] getSummary(){

        Summary[] summaries = new Summary[25];
        for (int i = 0; i < 25; i++) {
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
                if (!((data = br.readLine()) != null)) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (data.contains("URI=[")) {

                String[] information = data.split(" ");
                String timePattern = "\\d+:\\d+:\\d+,\\d+";
                Pattern p = Pattern.compile(timePattern);
                Matcher m = p.matcher(data);

                int startTime = 0;
                if (m.find()) {
                    startTime = Integer.parseInt(m.group().split(":")[0]);
                    String necessaryInformation = "URI=\\[.+";
                    Pattern p2 = Pattern.compile(necessaryInformation);
                    Matcher m2 = p2.matcher(data);
                    if (m2.find()) {
                        String[] splittedInfo = m2.group().split(",");

                        //Matcher m3=Pattern.compile("\\[/.+\\S+\\]").matcher(splittedInfo[0]);
                        //Matcher m4 = Pattern.compile("=\\d+ms").matcher(splittedInfo[2]);

                        String URI = splittedInfo[0].replaceAll(".*\\[|\\].*", "");
                        String get_post = splittedInfo[1].trim();
                        int responseTime = Integer.parseInt(splittedInfo[2].replaceAll(".*\\=|ms.*", ""));
                        //System.out.println(URI + " " + get_post + "  " + responseTime);
                        summaries[startTime].addURI(URI);
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
