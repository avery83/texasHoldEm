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

        //List<Users> users = new ArrayList<Users>();
        //users.addAll(dao.getAllUsers());
        assertTrue(dao.getAllUsers().size() > 0);

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
