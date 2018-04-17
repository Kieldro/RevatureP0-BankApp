package project0;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
import com.revature.project0.BankApp;
import com.revature.project0.ConnectionUtil;
import com.revature.project0.DBAccess;
import com.revature.project0.DBAccessor;
import com.revature.project0.User;

public class Tests {
	final static Logger logger = Logger.getLogger(BankApp.class);

	@Test
	// @DisplayName("The first test")
	public void myFirstTest() {
		assertEquals(true, true);
		assertEquals("The optional assertion message is the first parameter.", 4, 4);
		assertTrue('a' < 'b');
	}

	@Test
	public void testGetUser() {
		String name = "Jane"; // dummy user that should be initialized in the database
		DBAccess dao = DBAccessor.getInstance();
		User u = dao.getUser(name);
		System.out.println("User: " + u);
		// System.out.println("u.name: " + u.name);
		// System.out.println("name: " + name);
		assertTrue(u.name.equals(name));
	}

	@Test
	public void testGetInstance() {
		assertTrue(DBAccessor.getInstance() != null);
	}

	@Test
	public void test1Insert() {
		String name = "ian";
		User u = new User(name);
		DBAccess dao = DBAccessor.getInstance();
		dao.insertUser(u);

		u = dao.getUser(name);
		System.out.println("User: " + u);
		System.out.println("u.name: " + u.name);
		System.out.println("name: " + name);
		assertTrue(u.name.equals(name));
		// fail("Not yet implemented");
	}

	@Test
	public void test2Delete() {
		logger.trace("testDelete() running...");
		String name = "ian";
		User u = new User(name);
		DBAccess dao = DBAccessor.getInstance();
		dao.deleteUser(u);

		u = dao.getUser(name);
		System.out.println("User: " + u);
		System.out.println("name: " + name);
		assertTrue(u == null);
		logger.trace("testDelete() finished.");
	}

	@Test
	public void test3Update() {
		logger.trace("testUpdate() running...");
		String name = "Jane";
		DBAccess dao = DBAccessor.getInstance();
		User u = dao.getUser(name);
		assert (u != null);
		System.out.println("User before update: " + u);

		u.balance = 42;
		dao.updateUser(u);
		u = dao.getUser(u.name);
		System.out.println("User: " + u);
		// System.out.println("u.balance: " + u.balance);
		logger.trace("testUpdate() finished.");
	}

	@Test
	public void test4getAllUsers() {
		logger.trace("testing getAllUsers()...");
		// String name = "Jane";
		DBAccess dao = DBAccessor.getInstance();
		Map<String, User> um = dao.getAllUsers();
		;
		// assert(u != null);
		// System.out.println("User before update: " + u);

		System.out.println("user map: " + um);
		// System.out.println("u.balance: " + u.balance);
		logger.trace("testGetAll() finished.");
	}

	@Test
	public void test5getAllUsers() {
		logger.trace("testing getAllUsers5()...");
		DBAccess dao = DBAccessor.getInstance();
		dao.insertUser(new User("Jack"));
		Map<String, User> um = dao.getAllUsers();

		System.out.println("user map: " + um);
		logger.trace("testGetAll5() finished.");
	}

	@Test
	public void testGetCon() {
		try (Connection con = ConnectionUtil.getConnection()) {
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			System.err.println("SQL State: " + e.getSQLState());
			System.err.println("Error code: " + e.getErrorCode());
		}
	}

	@Test
	public void test4() {
		String name = "Jane";
		try (Connection con = ConnectionUtil.getConnection()) {
			PreparedStatement ps = con
					.prepareStatement("SELECT name, balance, admin, approved " + "FROM user_account WHERE name = ?");
			ps.setString(1, name);
			logger.trace("query executing...");
			ResultSet rs = ps.executeQuery();
			//
			// logger.trace("query done.");
			// if (rs.next()) {
			// new User(rs.getString("name"), rs.getFloat("balance"),
			// rs.getBoolean("admin"),
			// rs.getBoolean("approved"));
			// }
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			System.err.println("SQL State: " + e.getSQLState());
			System.err.println("Error code: " + e.getErrorCode());
		}
		// System.out.println("User: " + u);
		// assertTrue(u.name == name);

	}
//
//	@Before
//	public void setUp() throws Exception {
//
//	}
//
//	@After
//	public void tearDown() throws Exception {
//	}
}
