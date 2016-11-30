package poker.Entity;

import org.hibernate.type.BooleanType;
import org.hibernate.type.descriptor.sql.TinyIntTypeDescriptor;

import javax.persistence.*;
/**
 * Created by student on 11/28/16.
 */
@Entity
@Table(name = "games", schema = "poker")
public class Game {

    private int id;

    @Id
    @javax.persistence.Column(name = "game_id", nullable = false)
    @GeneratedValue(strategy= GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private Integer numberOfPlayers;

    @Basic
    @javax.persistence.Column(name = "Number_Of_Players", nullable = false)
    public Integer getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public void setNumberOfPlayers(Integer numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

    private Integer handsPlayed;

    @Basic
    @javax.persistence.Column(name = "Hands_Played", nullable = false)
    public Integer getHandsPlayed() {
        return handsPlayed;
    }

    public void setHandsPlayed(Integer handsPlayed) {
        this.handsPlayed = handsPlayed;
    }

    private Double startingChips;

    @Basic
    @javax.persistence.Column(name = "starting_chips", nullable = false)
    public Double getStartingChips() {
        return startingChips;
    }

    public void setStartingChips(Double startingChips) {
        this.startingChips = startingChips;
    }

    private BooleanType won;

    @Basic
    @javax.persistence.Column(name = "won", nullable = false)
    public BooleanType getWon() {
        return won;
    }

    public void setWon(BooleanType won) {
        this.won = won;
    }

    private String userName;

    @Basic
    @javax.persistence.Column(name = "user_name", nullable = true, length = 15)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
