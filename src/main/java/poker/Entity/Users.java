package poker.Entity;

import javax.persistence.*;
import java.util.*;

/**
 * Created by student on 11/28/16.
 */
@Entity
@Table(name = "users", schema = "poker")
public class Users {

    private String userName;
    private int gamesPlayed;
    private int gamesWon;
    private double totalWinnings;
    private String password;

    public Users() {
    }

    public Users(String userName, int gamesPlayed, int gamesWon, double totalWinnings, String password) {
        this.userName = userName;
        this.gamesPlayed = gamesPlayed;
        this.gamesWon = gamesWon;
        this.totalWinnings = totalWinnings;
        this.password = password;

    }

    @Id
    @Column(name = "user_name", nullable = false, length =15)



    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "games_played", nullable = true, length = 11)
    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public void setGamesPlayed(int gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
    }

    @Basic
    @Column(name = "games_won", nullable = true, length = 11)
    public int getGamesWon() {
        return gamesWon;
    }

    public void setGamesWon(int gamesWon) {
        this.gamesWon = gamesWon;
    }


    @Basic
    @Column(name = "total_winnings", nullable = true, length = 30)
    public Double getTotalWinnings() {
        return totalWinnings;
    }

    public void setTotalWinnings(Double totalWinnings) {
        this.totalWinnings = totalWinnings;
    }

    @Basic
    @Column(name = "user_pass", nullable = true, length = 15)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
