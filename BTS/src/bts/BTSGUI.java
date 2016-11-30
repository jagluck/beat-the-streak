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
import java.util.ArrayList;

/**
 *
 * @author Jake
 */
public class BTSGUI {
    public BTSGUI() throws IOException{
        URL url = new URL("http://espn.go.com/mlb/stats/batting/_/league/al");
        URLConnection con = url.openConnection();
        InputStream is =con.getInputStream();
        ArrayList<player> players = new ArrayList<player>();

        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        String line = null;
        int p;
        String link;
   
          while ((line = br.readLine()) != null) {
            if (line.indexOf("<tr class=\"colhead\" align=\"right\"><td align=\"left\"><span title=\"Rank\">RK</span></td>") > -1){
                    int y = line.indexOf("http://espn.go.com/mlb/player/_/id/");
                    line = line.substring(y);
                    y = line.indexOf("http://espn.go.com/mlb/player/_/id/", 2); 
                    while(y != -1){
                    String z = line.substring(0, y);
                    p = line.indexOf(">");
                    link = line.substring(0,p - 1);
                    players.add(new player(z, link));
                    line = line.substring(y);
                    y = line.indexOf("http://espn.go.com/mlb/player/_/id/", 2); 
                    }
            }
        }
        URL url2 = new URL("http://espn.go.com/mlb/stats/batting/_/sort/avg/league/nl/year/2015/seasontype/2");
        URLConnection con2 = url2.openConnection();
        InputStream is2 = con2.getInputStream();
        BufferedReader br2 = new BufferedReader(new InputStreamReader(is2));
        while ((line = br2.readLine()) != null) {
            if (line.indexOf("<tr class=\"colhead\" align=\"right\"><td align=\"left\"><span title=\"Rank\">RK</span></td>") > -1){
                    int y = line.indexOf("http://espn.go.com/mlb/player/_/id/");
                    line = line.substring(y);
                    y = line.indexOf("http://espn.go.com/mlb/player/_/id/", 2); 
                    while(y != -1){
                    String z = line.substring(0, y);
                    p = line.indexOf(">");
                    link = line.substring(0,p - 1);
                    players.add(new player(z, link));
                    line = line.substring(y);
                    y = line.indexOf("http://espn.go.com/mlb/player/_/id/", 2); 
                    }
            }
        }
        double x = 0;
        int xx = 0;
        double y = 0;
        int yy = 0;
        double z = 0;
        int zz = 0;
        double w = 0;
        int ww = 0;
        double a = 0;
        int aa = 0;
          for (int g = 0;g < players.size();g++){
               // System.out.println(players.get(g).getName());
               // System.out.println(players.get(g).getAVG());
               // System.out.println(players.get(g).getLSAVG());
               // System.out.println(players.get(g).getLSAB());
               // System.out.println(players.get(g).getRank());
              if (players.get(g).getRank() > x){
                  x = players.get(g).getRank();
                  yy = xx;
                  y = players.get(yy).getRank();
                  xx = g;
              }else if (players.get(g).getRank() > y){
                  y = players.get(g).getRank();
                  zz = yy;
                  z = players.get(zz).getRank();
                  yy = g;
              }else if (players.get(g).getRank() > z){
                  z = players.get(g).getRank();
                  ww = zz;
                  w = players.get(ww).getRank();
                  zz = g;
              }else if (players.get(g).getRank() > w){
                  w = players.get(g).getRank();
                  aa = ww;
                  z = players.get(zz).getRank();
                  ww = g;
              }else if (players.get(g).getRank() > a){
                  a = players.get(g).getRank();
                  aa = g;
              }
              
          }
          System.out.println("");
          System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
          System.out.println("");
          System.out.println(players.get(xx).getName() + "  ");
          System.out.println(players.get(xx).getTeam() + "  ");
          System.out.println("AVG: " + players.get(xx).getAVG() + "  ");
          System.out.println("AVG in last seven games: " + players.get(xx).getLSAVG() + "  ");
          System.out.println("AB's in last seven games: " + players.get(xx).getLSAB() + "  ");
          System.out.println("AVG vs starting pitcher: " + players.get(xx).getHAVG() + "  ");
          System.out.println("AB's vs starting pitcher: " + players.get(xx).getHAB() + "  ");
          //System.out.println("RANK " + players.get(xx).getRank());
           System.out.println("");
          System.out.println(players.get(yy).getName() + "  ");
          System.out.println(players.get(yy).getTeam() + "  ");
          System.out.println("AVG: " + players.get(yy).getAVG() + "  ");
          System.out.println("AVG in last seven games: " + players.get(yy).getLSAVG() + "  ");
          System.out.println("AB in last seven games: " + players.get(yy).getLSAB() + "  ");
          System.out.println("AVG vs starting pitcher: " + players.get(yy).getHAVG() + "  ");
          System.out.println("AB's vs starting pitcher: " + players.get(yy).getHAB() + "  ");
          //System.out.println("RANK " + players.get(yy).getRank());
           System.out.println("");
          System.out.println(players.get(zz).getName() + "  ");
          System.out.println(players.get(zz).getTeam() + "  ");
          System.out.println("AVG: " + players.get(zz).getAVG() + "  ");
          System.out.println("AVG in last seven games: " + players.get(zz).getLSAVG() + "  ");
          System.out.println("AB's in last seven games: " + players.get(zz).getLSAB() + "  ");
          System.out.println("AVG vs starting pitcher: " + players.get(zz).getHAVG() + "  ");
          System.out.println("AB's vs starting pitcher: " + players.get(zz).getHAB() + "  ");
          //System.out.println("RANK " + players.get(zz).getRank());
           System.out.println("");
          System.out.println(players.get(ww).getName() + "  ");
          System.out.println(players.get(ww).getTeam() + "  ");
          System.out.println("AVG: " + players.get(ww).getAVG() + "  ");
          System.out.println("AVG in last seven games: " + players.get(ww).getLSAVG() + "  ");
          System.out.println("AB's in last seven games: " + players.get(ww).getLSAB() + "  ");
          System.out.println("AVG vs starting pitcher: " + players.get(ww).getHAVG() + "  ");
          System.out.println("AB's vs starting pitcher: " + players.get(ww).getHAB() + "  ");
          //System.out.println("RANK " + players.get(ww).getRank());
           System.out.println("");
          System.out.println(players.get(aa).getName() + "  ");
          System.out.println(players.get(aa).getTeam() + "  ");
          System.out.println("AVG: " + players.get(aa).getAVG() + "  ");
          System.out.println("AVG in last seven games: " + players.get(aa).getLSAVG() + "  ");
          System.out.println("AB's in last seven games: " + players.get(aa).getLSAB() + "  ");
          System.out.println("AVG vs starting pitcher: " + players.get(aa).getHAVG() + "  ");
          System.out.println("AB's vs starting pitcher: " + players.get(aa).getHAB() + "  ");
          //System.out.println("RANK " + players.get(aa).getRank());
    }
}

