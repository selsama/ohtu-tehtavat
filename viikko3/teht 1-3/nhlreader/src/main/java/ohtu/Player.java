
package ohtu;

public class Player implements Comparable<Player> {
    private String name;
    private String nationality;
    private int assists;
    private int goals;
    private int penalties;
    private String team;
    private int games;

    public void setName(String name) {
        this.name = name;
    }

    public String getNameAndNat() {
        if (name.length() <= 10 ) {
            return name + ", " + nationality + "\t";
        }
        return name + ", " + nationality;
    }
    
    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
    
    public String getNationality() {
        return nationality;
    }
    
    public int getPoints() {
        return goals + assists;
    }

    @Override
    public String toString() {
        return this.getNameAndNat() + "\t score: " + assists + " + " + 
                goals + " = " + this.getPoints() + ", penalties: " + penalties + ", "
                + "team: " + team + ", games: " + games;
    }
    
    @Override
    public int compareTo(Player p) {
        return this.getPoints() - p.getPoints();
    }
    
    
      
}
