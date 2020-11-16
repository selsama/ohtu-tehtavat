
package ohtu;

public class Player {
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

    public String getName() {
        return name;
    }
    
    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
    
    public String getNationality() {
        return nationality;
    }

    @Override
    public String toString() {
        return name + ", " + nationality + ", assists: " + assists + 
                ", goals: " + goals + ", penalties: " + penalties + ", team: " + team +
                ", games: " + games;
    }
      
}
