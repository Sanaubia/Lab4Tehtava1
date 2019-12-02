package mobiili.ohjelmointi.lab4tehtava1;

import java.util.ArrayList;

public class LeagueEngine {

    private ArrayList<League> allLeagues = new ArrayList<>();

    public void addLeague(League league){
        allLeagues.add(league);
    }

    public int leagueAmmount(){
        return allLeagues.size();
    }

    public League leagueIndex(int index){
        if(allLeagues.size() > index){
            return allLeagues.get(index);
        } else {

            return null;
        }
    }
}
