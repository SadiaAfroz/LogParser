package net.therap.view;

import net.therap.model.Summary;

/**
 * @author sadia.afroz
 * @since 3/21/21
 */
public class LogView {

    public void printSummary(Summary[] summaries) {
        System.out.println("Time                 GET / POST  Total Response Time  Unique URI Count");
        for (Summary smr : summaries) {
            if (smr.getGetPostCount() != 0) {
                System.out.println(processView(smr));
            }
        }
    }

    public String processView(Summary eachSummary) {
        String timeRange = "";
        int startTime = eachSummary.getStartTime();
        // for am timing
        if (startTime >= 1 && startTime < 11) {
            timeRange += startTime + ":00 am - " + (startTime + 1) + ":00 am";
        }
        //time=12am
        else if (startTime == 12) {
            timeRange += startTime + ":00 am" + " - 1:00 pm";
        }
        // for pm timing
        else {
            int time = startTime % 12;
            // time=24:00
            if (time == 0) {
                timeRange += "12:00 am" + " - 1:00 am";
            } else {
                timeRange += time + ":00 pm - " + (time + 1) + ":00 pm";
            }
        }

        return " " + timeRange +
                "   " + eachSummary.getGetCount() +
                "/" + eachSummary.getPostCount() +
                "      " + eachSummary.getUriList().size() +
                "      " + eachSummary.getResponseTimeTotal() + "ms";
    }
}
