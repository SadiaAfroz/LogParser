package net.therap.model;

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
        this.uriList = new HashSet<String>();
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

    public void addURI(String uri) {
        uriList.add(uri);
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
    public int compareTo(Summary summary) {
        return this.getGetPostCount() - summary.getGetPostCount();
    }
}
