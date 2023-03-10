package cs301.Soccer;

import android.util.Log;
import cs301.Soccer.soccerPlayer.SoccerPlayer;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.*;

/**
 * Soccer player database -- presently, all dummied up
 *
 * @author *** put your name here ***
 * @version *** put date of completion here ***
 *
 */
public class SoccerDatabase implements SoccerDB {

    // dummied up variable; you will need to change this
    private Hashtable <String,SoccerPlayer> database = new Hashtable<>();

    /**
     * add a player
     *
     * @see SoccerDB#addPlayer(String, String, int, String)
     */
    @Override
    public boolean addPlayer(String firstName, String lastName,
                             int uniformNumber, String teamName) {
        String key = firstName + "##" + lastName;
        if(database.containsKey(key)){
            return false;
        }
        else{
            SoccerPlayer addedPlayer = new SoccerPlayer(firstName,lastName,uniformNumber,teamName);
            database.put(key,addedPlayer);
            return true;
        }
    }

    /**
     * remove a player
     *
     * @see SoccerDB#removePlayer(String, String)
     */
    @Override
    public boolean removePlayer(String firstName, String lastName) {
        String funKey = firstName + "##" + lastName;
        if(database.containsKey(funKey)){
            database.remove(funKey);
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * look up a player
     *
     * @see SoccerDB#getPlayer(String, String)
     */
    @Override
    public SoccerPlayer getPlayer(String firstName, String lastName) {
        String findKey = firstName + "##" + lastName;

        if(database.containsKey(findKey)){
            return (SoccerPlayer) database.get(findKey);
        }
        else {
            return null;
        }
    }

    /**
     * increment a player's goals
     *
     * @see SoccerDB#bumpGoals(String, String)
     */
    @Override
    public boolean bumpGoals(String firstName, String lastName) {
        String evenFunKey = firstName + "##" + lastName;
        if(database.containsKey(evenFunKey)){
            SoccerPlayer goal = (SoccerPlayer) database.get(evenFunKey);
            goal.bumpGoals();
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * increment a player's yellow cards
     *
     * @see SoccerDB#bumpYellowCards(String, String)
     */
    @Override
    public boolean bumpYellowCards(String firstName, String lastName) {
        String evenFunKey = firstName + "##" + lastName;
        if(database.containsKey(evenFunKey)){
            SoccerPlayer goal = (SoccerPlayer) database.get(evenFunKey);
            goal.bumpYellowCards();
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * increment a player's red cards
     *
     * @see SoccerDB#bumpRedCards(String, String)
     */
    @Override
    public boolean bumpRedCards(String firstName, String lastName) {
        String evenFunKey = firstName + "##" + lastName;
        if(database.containsKey(evenFunKey)){
            SoccerPlayer goal = (SoccerPlayer) database.get(evenFunKey);
            goal.bumpRedCards();
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * tells the number of players on a given team
     *
     * @see SoccerDB#numPlayers(String)
     */
    @Override
    // report number of players on a given team (or all players, if null)
    public int numPlayers(String teamName) {
        //Checkpoint 4 Complete.
        int playerCount = 0;

        if(teamName == null){
            return database.size();
        }
        else{
            String curTeam = "dur";
            SoccerPlayer current;
            Enumeration<SoccerPlayer> arr = database.elements();
            for(int i = 0; arr.hasMoreElements(); i++) {
                current = arr.nextElement();
                curTeam = current.getTeamName();
                if(teamName.equals(curTeam)){
                    playerCount++;
                }
            }

            return playerCount;
        }
    }

    /**
     * gives the nth player on a the given team
     *
     * @see SoccerDB#playerIndex(int, String)
     */
    // get the nTH player
    @Override
    public SoccerPlayer playerIndex(int idx, String teamName) {
        //In progress.
       if(teamName == null){

           Enumeration<SoccerPlayer> arr = database.elements();
           SoccerPlayer current = arr.nextElement();
           for(int i = 0; i < idx; i++) {
               current = arr.nextElement();
           }
           return current;
       }

       else{
           String curTeam;
           int playerCount = 0;

           SoccerPlayer current = null;
           Enumeration<SoccerPlayer> arr = database.elements();
           for(int i = 0; arr.hasMoreElements(); i++) {
               current = arr.nextElement();
               curTeam = current.getTeamName();
               if(curTeam.equals(teamName)){
                   if(playerCount == idx){
                       return current;
                   }
                   playerCount++;
               }
           }
           return current;
       }
    }

    /**
     * reads database data from a file
     *
     * @see SoccerDB#readData(java.io.File)
     */
    // read data from file
    @Override
    public boolean readData(File file) {
        file = new File("PlayerData.txt");
        try {
            FileInputStream input = new FileInputStream(file);
            PrintWriter print = new PrintWriter(file);
            print.println((player.getFirstName()));
            return true;
        }
        catch(Exception e){
            System.out.println("The file is closed.");
            return false;
        }
    }

    /**
     * write database data to a file
     *
     * @see SoccerDB#writeData(java.io.File)
     */
    // write data to file
    @Override
    public boolean writeData(File file) {
        file = new File("PlayerData.txt");
        try {
            PrintWriter pw = new PrintWriter(file);
            pw.write("Placeholder");

            pw.close();
            return true;
        }
        catch(Exception e){
            System.out.println("The file is closed.");
            return false;
        }
    }

    /**
     * helper method that logcat-logs a string, and then returns the string.
     * @param s the string to log
     * @return the string s, unchanged
     */
    private String logString(String s) {
        Log.i("write string", s);
        return s;
    }

    /**
     * returns the list of team names in the database
     *
     * @see cs301.Soccer.SoccerDB#getTeams()
     */
    // return list of teams
    @Override
    public HashSet<String> getTeams() {
        return new HashSet<String>();
    }

    /**
     * Helper method to empty the database and the list of teams in the spinner;
     * this is faster than restarting the app
     */
    public boolean clear() {
        if(database != null) {
            database.clear();
            return true;
        }
        return false;
    }
}
