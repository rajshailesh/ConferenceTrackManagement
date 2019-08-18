package com.mycompany.app;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;


import static com.mycompany.app.App.ONE_MINUTE_IN_MILLIS;


class CTMService {

    static Calendar startTime = Calendar.getInstance();
    static Calendar afterNoon = Calendar.getInstance();
    static Calendar endOfSession = Calendar.getInstance();


    static void allotTalks(ArrayList<Talk> input, ArrayList<Talk> output, Session session) {
        startTime.set(Calendar.HOUR_OF_DAY, 9);
        startTime.set(Calendar.MINUTE, 0);
        startTime.set(Calendar.SECOND, 0);

        afterNoon.set(Calendar.HOUR_OF_DAY, 12);
        afterNoon.set(Calendar.MINUTE, 0);
        afterNoon.set(Calendar.SECOND, 0);

        endOfSession.set(Calendar.HOUR_OF_DAY, 17);
        endOfSession.set(Calendar.MINUTE, 0);
        endOfSession.set(Calendar.SECOND, 0);


        Talk trackName = new Talk();
        trackName.setTopic("Track 1:");
        trackName.setTime(0);

        output.add(trackName);
        allocateTrack(input, output, afterNoon);
        setLunchAndNetworking(output, output.get(output.size() - 1), "Lunch");
        allocateTrack(input, output, endOfSession);
        setLunchAndNetworking(output, output.get(output.size() - 1), "Networking Event");


        if(output.size() < input.size()){
            startTime.set(Calendar.HOUR_OF_DAY, 9);
            startTime.set(Calendar.MINUTE, 0);
            startTime.set(Calendar.SECOND, 0);
            trackName = new Talk();
            trackName.setTopic("Track 2:");
            trackName.setTime(0);
            trackName.setEndTime(output.get(output.size() - 1).getEndTime());
            output.add(trackName);
            allocateTrack(input, output, afterNoon);
            setLunchAndNetworking(output, output.get(output.size() - 1), "Lunch");
            allocateTrack(input, output, endOfSession);
            setLunchAndNetworking(output, output.get(output.size() - 1), "Networking Event");

        }

    }

    private static void allocateTrack(ArrayList<Talk> input, ArrayList<Talk> sol, Calendar target) {

        for (Talk tk : input) {
            if (tk.isAllotted()) {
                continue;
            }

            int result = isFeasible(tk, target.getTime());

            if (result <= 0) {
                tk.setStartTime(startTime.getTime());
                tk.setEndTime(new Date(startTime.getTimeInMillis() + ONE_MINUTE_IN_MILLIS * tk.getTime()));
                startTime.setTime(tk.getEndTime());//startTime variable will hold latest time allotted to talk

                tk.setAllotted(true);
                sol.add(tk);
            }
        }

    }

    private static void setLunchAndNetworking(ArrayList<Talk> sol, Talk tk, String breakName) {
        Talk brk = new Talk();
        brk.setTopic(breakName);
        brk.setTime(60);
        brk.setStartTime(tk.getEndTime());
        brk.setEndTime(new Date(tk.getEndTime().getTime() + ONE_MINUTE_IN_MILLIS * 60));
        sol.add(brk);
        startTime.setTime(brk.getEndTime());

    }

    private static int isFeasible(Talk tk, Date target) {

        Date dt = new Date(startTime.getTimeInMillis() + ONE_MINUTE_IN_MILLIS * tk.getTime());
        return dt.compareTo(target);
    }
    static Talk select(HashMap<Integer, Talk> input, int timeRemaining){
        return new Talk();
    }
}
