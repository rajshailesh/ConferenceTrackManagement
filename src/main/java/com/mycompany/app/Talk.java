package com.mycompany.app;

import java.util.Date;

class Talk {
    private Date startTime;
    private Date endTime;
    private Integer time;
    private String topic;
    private boolean isAllotted;

    Talk() {
    }

    Date getEndTime() {
        return endTime;
    }

    void setEndTime(Date endTime) {
        this.endTime = endTime;
    }




    boolean isAllotted() {
        return isAllotted;
    }

    void setAllotted(boolean allotted) {
        isAllotted = allotted;
    }

    String getTopic() {
        return topic;
    }

    void setTopic(String topic) {
        this.topic = topic;
    }

    Date getStartTime() {
        return startTime;
    }

    void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    int getTime() {
        return time;
    }

    void setTime(int time) {
        this.time = time;
    }
}
