package ohtu;

import com.google.gson.Gson;
import java.io.IOException;
import org.apache.http.client.fluent.Request;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        String url = "https://nhlstatisticsforohtu.herokuapp.com/players";
        
        String bodyText = Request.Get(url).execute().returnContent().asString();

        Gson mapper = new Gson();
        Player[] players = mapper.fromJson(bodyText, Player[].class);
        
        ArrayList<Player> list = new ArrayList<>();
        
        for (Player player : players) {
            if (player.getNationality().equals("FIN")) {
                list.add(player);
            }
        }
        
        Collections.sort(list, Collections.reverseOrder());
        
        for (Player p : list) {
            System.out.println(p);
        }

    }
  
}
