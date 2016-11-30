/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bts;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Jake
 */
public class player {
    private String info;
    private String link;
    private String name;
    private String date;
    private String time;
    private String team;
    private String convlink;
    private String plink;
    private double AVG;
    private double LSAVG;
    private double LSAB;
    private double HAVG;
    private double HAB;
    private double rank;
    public player(String info, String link) throws IOException{
        this.info = info;
        this.link = link;
        String line = info;
        int y = line.indexOf("<td align=\"right\" class=\"sortcell\">");
        AVG = Double.parseDouble(line.substring(y + 35, y + 39));
        boolean wall = false;
        /////////////////////////////////////////////
        URL url = new URL(link);
        URLConnection con = url.openConnection();
        InputStream is =con.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        while ((line = br.readLine()) != null) {
           if (line.indexOf("current mod-table mod-no-footer\"><div class=\"mod-header\"><h4>") != -1){
               int t = line.indexOf("<h4>");
               line = line.substring(t);
               t = line.indexOf("'");
               team = line.substring(4, t);
            }
            if (line.indexOf("<div class=\"time\">") != -1){
                int t = line.indexOf("<div class=\"time\">");
                line = line.substring(t);
                int a = line.indexOf("br/>");
                date = line.substring(18, a);
            }
             if (line.indexOf("/mlb/conversation") != -1){
                int f = line.indexOf("/mlb/conversation");
                line = line.substring(f);
                f = line.indexOf(">");
                convlink = line.substring(0,f - 1);
             }
             if (line.indexOf("<td>Last seven days</td>") != -1){
                 int c = line.indexOf("<td>Last seven days</td>");
                 line = line.substring(c);
                 c = line.indexOf("<td>Last seven days</td>");
                 line = line.substring(c);
                 //find spot in string  for # of at bats here
                 c = line.indexOf("\"textright\">");
                 String h = line.substring(c + 12, c + 13);
                 if (!line.substring(c + 13, c + 14).equals("<")){
                     h = h + line.substring(c + 13, c + 14);
                 }
                 LSAB = Double.parseDouble(h);
                 /////////////LSAVG
                 
                 c = line.indexOf("</td></tr><tr class=\"evenrow\">");
                 if (c == -1){
                  c = line.indexOf("</td></tr><tr class=\"oddrow\">");    
                 }
                 LSAVG = Double.parseDouble(line.substring(c -4, c));
                 if (LSAVG == .000){
                     if (line.substring(c -5, c).equals("1.000")){
                         LSAVG = 1.000;
                     }
                 }
                 
             }
        }
        if (convlink == null){
            System.out.println("n");
        }
        if (convlink != null){
        URL url2 = new URL("http://espn.go.com" + convlink);
        URLConnection con2 = url2.openConnection();
        InputStream is2 = con2.getInputStream();
        BufferedReader br2 = new BufferedReader(new InputStreamReader(is2));
        while ((line = br2.readLine()) != null) {
            if (line.indexOf("<p><span>") != -1){
                int t = line.indexOf(":");
                if (team != null){
                if (!team.equals(line.substring(14,t).toUpperCase())){
                    t = line.indexOf("/_");
                    line = line.substring(t);
                    t = line.indexOf(">");
                    plink = line.substring(0,t-1);
                }
              } 
            }
        }
        name = link;
        int h = name.indexOf("id/");
        name = name.substring(h + 3);
        h = name.indexOf("/");
        name = name.substring(h + 1);
        int r = name.indexOf("-");
        name = name.substring(0,r) + " " + name.substring(r + 1);
        name = name.substring(0,1).toUpperCase() + name.substring(1);
        r = name.indexOf(" ");
        name = name.substring(0,r) + " " + name.substring(r + 1,r + 2).toUpperCase() + name.substring(r+2);
        }
        boolean hit = false;
        if (plink != null ){
        URL url3 = new URL("http://espn.go.com/mlb/player/batvspitch" + plink);
        URLConnection con3 = url3.openConnection();
        InputStream is3 = con3.getInputStream();
        BufferedReader br3 = new BufferedReader(new InputStreamReader(is3));
        while ((line = br3.readLine()) != null) {
            line = line.toLowerCase();
            if (line.indexOf( name.toLowerCase()) != -1){
                int o = line.indexOf(name.toLowerCase());
                line = line.substring(o);
                o = line.indexOf("ght");
                if ( line.substring(o+ 6,o +7).equals("<")){
                    line = line.substring(o+ 5,o +6);
                    HAB = Double.parseDouble(line);
                }else{
                    line = line.substring(o+ 5,o +7);
                    HAB = Double.parseDouble(line);
                }
                line = br3.readLine();
                line = br3.readLine();
                line = br3.readLine();
                line = br3.readLine();
                line = br3.readLine();
                line = br3.readLine();
                line = br3.readLine();
                line = br3.readLine();
                o = line.indexOf("ght");
                HAVG = Double.parseDouble(line.substring(o+ 5,o +9));
               if (line.substring(o+ 4,o +9).equals(.000)){
                    if (line.substring(o+ 4,o +9).equals("1.000")){
                         HAVG = 1.000;
                     }
                 }
                //System.out.println(HAVG);
                hit = true;
            }
       }
        }
        if (hit == false){
            HAVG = AVG;
            HAB = 1;
        }
        rank = AVG + (1.5 * (LSAVG * (LSAB / 10))) + (HAVG * (HAB / 4));
        if (hit == false){
            HAVG = 0.000;
            HAB = 0;
        }
        //System.out.println(name);
        //System.out.println(rank);
        ///////////////////////////////////////////////////
    }
    public String getInfo(){
        return info;
    }
     public String getName(){
        return name;
    }
    public String getLink(){
        return link;
    }
     public String getTeam(){
        return team;
    }
    public double getAVG(){
        return AVG;
    }
     public double getLSAVG(){
        return LSAVG;
    }
      public double getLSAB(){
        return LSAB;
    }
      public double getHAVG(){
        return HAVG;
    }
      public double getHAB(){
        return HAB;
    }
       public double getRank(){
        return rank;
    }
}
