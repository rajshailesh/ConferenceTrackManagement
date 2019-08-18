package com.mycompany.app;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Main program
 *
 */
public class App 
{
    static final long ONE_MINUTE_IN_MILLIS=60000;//millisecs
    public static void main( String[] args ) throws Exception
    {

        File file = new File(args[0]);
        BufferedReader bf = new BufferedReader(new FileReader(file));
        String st;
        Session session = new Session();
        Pattern p = Pattern.compile("[0-9]+min$");

        Talk tk;

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 9);
        cal.set(Calendar.MINUTE, 0);

        while((st = bf.readLine()) != null){

            tk = getTalk(st, p);
            session.getTopics().add(tk);

        }

        //session.getTopics().sort((t1, t2) -> t1.getTime() - t2.getTime());
        CTMService.allotTalks(session.topics, session.solution, session);
        System.out.println(session);
        System.out.println("----------------------------------");
        System.out.println(session.toStringSession());
   }

    private static Talk getTalk(String st, Pattern p) {
        Matcher m;
        Talk tk = new Talk();

        tk.setTopic(st);
        m = p.matcher(st);
        if(m.find()){
            tk.setTime(Integer.parseInt(m.group().substring(0, m.group().length() - "min".length())));
        }else if(st.contains("lightning")){
            tk.setTime(5);
        }
        return tk;
    }
}
