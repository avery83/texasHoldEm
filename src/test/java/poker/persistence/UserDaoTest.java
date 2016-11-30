package poker.persistence;

import poker.Entity.Users;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
/**
 * Created by student on 11/28/16.
 */
public class UserDaoTest {
    @Test
    public void testGetAllUsers() throws Exception {
        UserDao dao = new UserDao();
        //List<Users> users = new ArrayList<>();
        //String insertedUserName = "";
        //create user to add
        int currentSize = dao.getAllUsers().size();
        Users user = new Users();
        user.setUserName("Joeys");
        user.setGamesPlayed(3);
        user.setGamesWon(2);
        user.setTotalWinnings(12000.00);
        user.setPassword("secr");
        dao.addUser(user);
        //List<Users> users = new ArrayList<Users>();
        //users.addAll(dao.getAllUsers());
        assertTrue(dao.getAllUsers().size() > 0);
        dao.deleteUser(user);
    }

    @Test
    public void testUpdateUser() throws Exception {

    }

    @Test
    public void testGetUser() throws Exception {
        UserDao dao = new UserDao();
        String user = dao.getUser("Heidi").getUserName();
        assertEquals(user,"Heidi");
    }

    @Test
    public void testDeleteUser() throws Exception {

    }


    @Test
    public void testAddUser() throws Exception {
        UserDao dao = new UserDao();
        String insertedUserName = "";
        //create user to add
        Users user = new Users();
        user.setUserName("James");
        user.setGamesPlayed(3);
        user.setGamesWon(2);
        user.setTotalWinnings(12000.00);
        user.setPassword("secret");

        insertedUserName = dao.addUser(user);

        assertEquals(insertedUserName,"James");
        dao.deleteUser(user);


    }
}
