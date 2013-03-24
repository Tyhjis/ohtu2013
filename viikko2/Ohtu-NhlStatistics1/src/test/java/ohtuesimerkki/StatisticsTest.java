package ohtuesimerkki;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import java.util.List;
import ohtuesimerkki.Player;
import ohtuesimerkki.PlayerReader;
import ohtuesimerkki.Reader;
import ohtuesimerkki.Statistics;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author krisu
 */
public class StatisticsTest {
    
    Statistics stats;
    Reader readerStub = new Reader() {

        @Override
        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<Player>();
 
            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri",   "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));
 
            return players;
        }

    };
    
    public StatisticsTest() {
    }
    
    @Before
    public void alustus(){
        stats = new Statistics(readerStub);
    }
    
    @Test
    public void searchTest(){
        Player pl = stats.search("Semenko");
        String str = String.format("%-20s","Semenko") + " " + "EDM" + " " + String.format("%2d",4) + " + " 
                + String.format("%2d",12) + " = " + 16;
        assertEquals(pl.toString(), str);
    }
    
    @Test
    public void searchTest2(){
        Player pl = stats.search("Gretzky");
        String str = String.format("%-20s","Gretzky") + " " + "EDM" + " " + String.format("%2d",35) + " + " 
                + String.format("%2d",89) + " = " + 124;
        assertEquals(pl.toString(), str);
    }
    
    @Test
    public void searchTest3(){
        Player pl = stats.search("Nobody");
        assertEquals(pl, null);
    }
    
    @Test
    public void teamTest(){
        List<Player> pelaajat = stats.team("EDM");
        for(Player player: pelaajat){
            assertEquals(player.getTeam(), "EDM");
        }
    }
    
    @Test
    public void topScorersTest(){
        List<Player> players = stats.topScorers(3);
        assertEquals(players.size(), 4);
    }
    
    @Test
    public void teamTest2(){
        List<Player> pelaajat = stats.team(null);
        assertEquals(pelaajat.isEmpty(), true);
    }
}