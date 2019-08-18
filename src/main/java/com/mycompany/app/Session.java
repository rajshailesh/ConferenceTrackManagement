package com.mycompany.app;

import java.text.SimpleDateFormat;
import java.util.ArrayList;


public class Session {

    ArrayList<Talk> topics = new ArrayList<>();
    ArrayList<Talk> solution = new ArrayList<>();


    ArrayList<Talk> getTopics() {
        return topics;
    }

    @Override
    public String toString() {

        String str = "";
        for (Talk tk: topics) {
            str += tk.getTime() + "::" + tk.getTopic() + System.lineSeparator();
        }

        return str;
    }
    String toStringSession(){
        SimpleDateFormat formatDate = new SimpleDateFormat("hh:mm a");
        String str = "";
        for (Talk tk: solution) {
            if(tk.getTime() == 0){
                str += tk.getTopic() + System.lineSeparator();
            }else{
                str += formatDate.format(tk.getStartTime()) + "::"  + tk.getTopic() + System.lineSeparator();
            }

        }

        return str;
    }
}
