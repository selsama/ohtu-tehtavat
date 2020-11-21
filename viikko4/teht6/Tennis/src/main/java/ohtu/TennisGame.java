package ohtu;

public class TennisGame {
    
    private int m_score1 = 0;
    private int m_score2 = 0;
    private String player1Name;
    private String player2Name;

    public TennisGame(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public void wonPoint(String playerName) {
        if (playerName == "player1")
            m_score1 += 1;
        else
            m_score2 += 1;
    }

    public String getScore() {
        if (m_score1==m_score2) {
            return this.scorewhenPointsEven();
        } else if (m_score1>=4 || m_score2>=4) {
            return this.scoreWhenPointsOverForty();
        } else {
            return this.scoreWhenStandard();
        }
    }
    
    private String scorewhenPointsEven() {
        if (m_score1 == 4) {
            return "Deuce";
        }
        return this.pointsToString(m_score1) + "-All";
    }
    
    private String scoreWhenStandard() {
        return this.pointsToString(m_score1) + "-" + this.pointsToString(m_score2);
    }
    
    private String pointsToString(int points) {
        switch(points)
            {
                case 0:
                    return "Love";
                case 1:
                    return "Fifteen";
                case 2:
                    return "Thirty";
                case 3:
                    return "Forty";
            }
        return null;
    }
    
    private String scoreWhenPointsOverForty() {  
        String playerAhead = m_score1 > m_score2 ? "player1" : "player2";
        int pointDifference = m_score1-m_score2;
        if (pointDifference == 1 || pointDifference == -1) {
            return "Advantage " + playerAhead;
        } else {
            return "Win for " + playerAhead;
        }
    }
}