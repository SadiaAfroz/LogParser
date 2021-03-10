package net.therap;


import java.util.HashSet;

/**
 * @author sadia.afroz
 * @since 3/10/21
 */
public class Summary implements Comparable<Summary> {
    private int startTime;
    private int getCount;
    private int postCount;
    private int responseTimeTotal;
    private HashSet<String> uriList;

    public Summary(int startTime) {
        this.startTime = startTime;
        getCount = 0;
        postCount = 0;
        responseTimeTotal = 0;
        uriList = new HashSet<String>();

    }

    public Summary() {
        startTime = 0;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getGetCount() {
        return getCount;
    }

    public void setGetCount(int getCount) {
        this.getCount = getCount;
    }

    public int getPostCount() {
        return postCount;
    }

    public void setPostCount(int postCount) {
        this.postCount = postCount;
    }

    public int getResponseTimeTotal() {
        return responseTimeTotal;
    }

    public void setResponseTimeTotal(int responseTimeTotal) {
        this.responseTimeTotal = responseTimeTotal;
    }

    public HashSet<String> getUriList() {
        return uriList;
    }

    public void setUriList(HashSet<String> uriList) {
        this.uriList = uriList;
    }

    public int getUriListLength() {
        return uriList.size();
    }

    public void addURI(String URI) {
        uriList.add(URI);
    }

    public void incrementResponsetime(int value) {
        responseTimeTotal += value;
    }

    public void incrementGetCount() {
        getCount++;
    }

    public void incrementPostCount() {
        postCount++;
    }

    public int getGetPostCount() {
        return getCount + postCount;
    }

    @Override
    public String toString() {
        String timeRange="";
        // for am timing
        if(startTime>=1 && startTime<11){
            timeRange+=startTime+":00 am - "+(startTime+1)+":00 am";
        }
        //time=12am
        else if(startTime==12){
            timeRange+=startTime+":00 am"+" - 1:00 pm";
        }
        // for pm timing
        else {
            int time=startTime%12;
            // time=24:00
            if(time==0){
                timeRange+="12:00 am"+" - 1:00 am";
            }
            else {
                timeRange+=time+":00 pm - "+(time+1)+":00 pm";
            }
        }

//        return "Time= " + timeRange +
//                ", getCount/postCount= " + getCount +
//                "/" + postCount +
//                ", responseTimeTotal= " + responseTimeTotal +
//                ", uriList=" + uriList.size();


        return " " + timeRange +
                "   " + getCount +
                "/" + postCount +
                "      " + uriList.size() +
                "      " + responseTimeTotal+"ms";
    }


    @Override
    public int compareTo(Summary summary) {
        return this.getGetPostCount()- summary.getGetPostCount();
    }
}
