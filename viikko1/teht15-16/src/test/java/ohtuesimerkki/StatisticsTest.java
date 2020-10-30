/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtuesimerkki;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

import java.util.*;
/**
 *
 * @author salmison
 */
         

public class StatisticsTest {
 
    Reader readerStub = new Reader() {
 
        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<>();
 
            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri",   "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));
 
            return players;
        }
    };
 
    Statistics stats;

    @Before
    public void setUp() {
        // luodaan Statistics-olio joka käyttää "stubia"
        stats = new Statistics(readerStub);
    }  
    
    @Test
    public void searchSemenkoTest() {
        Player player = stats.search("Semenko");
        assertTrue("search returns wrong name", player.getName().equals("Semenko"));
        assertTrue("search returns wrong team", player.getTeam().equals("EDM"));
        assertEquals("search returns wrong goals", 4, player.getGoals());
        assertEquals("search returns wrong assists", 12, player.getAssists());
    }
    
    @Test
    public void searchNullTest() {
        Player player = stats.search("Nette");
        assertTrue("search returns a player that shouldn't exists", player == null);
    }
    
    @Test
    public void teamEDMTest() {
        List<Player> players = stats.team("EDM");
        assertEquals("wrong size of list", 3, players.size());
        int points = 0;
        for (Player player: players) {
            assertTrue("player of a wrong team on list", player.getTeam().equals("EDM"));
            points += player.getPoints();
        }
        assertEquals("wrong sum of points", 230, points);
    }
    
    @Test
    public void teamNullTest() {
        List players = stats.team("Null");
        assertEquals("list is not empty", 0, players.size());
    }
    
    @Test
    public void topThreeTest() {
        List<Player> players = stats.topScorers(2);
        assertEquals("wrong size of list", 3, players.size());
        assertTrue("wrong first scorer", players.get(0).getName().equals("Gretzky"));
        assertTrue("wrong second scorer", players.get(1).getName().equals("Lemieux"));
        assertTrue("wrong third scorer", players.get(2).getName().equals("Yzerman"));
    }
}

