import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runners.Parameterized.Parameters;

public class test {

	SportsEventManager manager = new SportsEventManager(); 
	public int maxGames=manager.MAX_GAMES;
	public int MAX_DAYS=manager.MAX_DAYS;
	public int MAX_PLAYERS=manager.MAX_PLAYERS;
	Game[] gamelist= manager.gameList;
	Player[] playerlist = manager.playerList;
	DaySchedule[] scheduleList = manager.scheduleList;
	int gameCounter=manager.gameCounter;
	int playerCounter=manager.playerCounter;
	int scheduleCounter=manager.scheduleCounter;

	@Test 
	public void addGameCheckMaxPlayers() throws Exception 
	{
		Game game=new Game("Cricket",0);
		game.setName(game.name);
		game.setMaxPlayers(game.maxPlayers);
		String name = game.getName();
		int maxPlayers = game.getMaxPlayers();
		assertTrue("100",maxPlayers<=0);
	}
	
	@Test
	public void AddGameTest1()
	{
		SportsEventManager sem = new SportsEventManager();
		int re = sem.addGame("Rugby", 0);
		assertEquals(100,re);
	}
	
	
	
	@Test
	public void AddGameTest2()
	{
		SportsEventManager sem = new SportsEventManager();
		int re = sem.addGame(null, 50);
		assertEquals(99,re);
	}
	
	@Test
	public void AddGameTest3()
	{
		SportsEventManager sem = new SportsEventManager();
		int re = sem.addGame("Rugby", 11);
		int re1 = sem.addGame("Rugby", 11);
		assertEquals(101,re1);
	}
	
	@Test
	public void AddGameTest4()
	{
		
		SportsEventManager sem = new SportsEventManager();
		sem.gameCounter = 25;
		int re = sem.addGame("Rugby", 11);
		
		assertEquals(98,re);
	}
	
	@Test
	public void checkaddtogamelist()
	{		
		SportsEventManager sem = new SportsEventManager();
		int re = sem.addGame("Rugby", 70);
		assertEquals(0,re);
	}
	
	@Test
	public void searchGameTest()
	{
		//System.out.println("Check1");
		SportsEventManager sem = new SportsEventManager();
		sem.addGame("Soccer", 22);
		sem.addGame("Football", 44);
		Game storedGame = sem.searchGame("Soccer");
		Game storedGame1 = sem.searchGame("Football");
		

		assertEquals("Soccer",storedGame.name);
		assertEquals("Football",storedGame1.name);
		
		
	}
	
	@Test
	public void searchGameFails()
	{
		SportsEventManager sem = new SportsEventManager();
		sem.addGame("Soccer", 22);
		Game storedGame1 = sem.searchGame("Football");
		assertEquals(null,storedGame1);
	}
	
	@Test
	public void testaddPlayer()
	{
		SportsEventManager sem = new SportsEventManager();
		String[] gamenames = {"Soccer", "Football"};
		sem.addGame("Soccer", 22);
		sem.addGame("Football", 44);
		String nam1 = sem.addPlayer("Prakash", gamenames);	
		assertEquals("Prakash added successfully",nam1);
	}
	
	@Test
	public void checkPlayerAlreadyExists()
	{
		
		SportsEventManager sem = new SportsEventManager();

		sem.addGame("Soccer", 22);
		sem.addGame("Football", 44);
		String[] gamenames = {"Soccer", "Football"};
		String nam1 = sem.addPlayer("Prakash", gamenames);
		String nam2 = sem.addPlayer("Prakash", gamenames);
		assertEquals("Prakash already exists",nam2);
		
	}
	
	@Test
	public void RegistrationFailureforNonExistentGame()
	{
		SportsEventManager sem = new SportsEventManager();
		String[] gamenames = {"Football"};
		String nam1 = sem.addPlayer("Prakash", gamenames);
		assertEquals("Error you cannot be registered for Football",nam1);
	}
	
	@Test
	public void TestAddSchedule()
	{
		SportsEventManager sem = new SportsEventManager();
		sem.addGame("Soccer", 22);
		sem.addGame("Football", 44);
		
		String[] gamenames = {"Football", "Soccer"};
		
		String msg = sem.addSchedule("Monday", gamenames);
		assertEquals("Monday added successfully",msg);

	}
	
	@Test
	public void TestDayScheduleAlreadyExists()
	{
		
		SportsEventManager sem = new SportsEventManager();
		sem.addGame("Soccer", 22);
		sem.addGame("Football", 44);
		String[] gamenames = {"Soccer", "Football"};
		String msg = sem.addSchedule("Monday", gamenames);
		String msg1 = sem.addSchedule("Monday",gamenames);
		assertEquals("Monday already scheduled",msg1);

	}
	
	@Test
	public void TestScheduleforNonexistentGame()
	{
		SportsEventManager sem = new SportsEventManager();
		String[] gamenames = {"Football"};
		String nam1 = sem.addSchedule("Monday", gamenames);
		assertEquals("Error you cannot be registered for Football",nam1);	
	}
	
	@Test
	public void SearchPlayerTest()
	{
		SportsEventManager sem = new SportsEventManager();
		sem.addGame("Football", 22);
		sem.addGame("Soccer", 44);
		String[] gamenames = {"Football","Soccer"};
		sem.addPlayer("Prakash",gamenames);
		String playername = "Prakash";
		Player storedPlayer = sem.searchPlayer(playername);
		assertEquals("Prakash",storedPlayer.name);
	}
	
	@Test
	public void searchPlayerFails()
	{
		SportsEventManager sem = new SportsEventManager();
		sem.addGame("Football", 22);
		sem.addGame("Soccer", 44);
		String[] gamenames = {"Football","Soccer"};
		sem.addPlayer("Prakash",gamenames);
		String playername = "Rohit";
		Player storedPlayer = sem.searchPlayer(playername);
		assertEquals(null,storedPlayer);
	}
	
	
	
	@Test
	public void searchDayTest()
	{
		SportsEventManager sem = new SportsEventManager();
		sem.addGame("Soccer", 22);
		sem.addGame("Football", 44);
		String[] gamenames = {"Soccer", "Football"};	
		String msg = sem.addSchedule("Monday", gamenames);	
		DaySchedule ds = sem.searchDay("Monday");
		assertEquals("Monday",ds.dayName);
	}
	
	@Test
	public void searchDayFails()
	{
		SportsEventManager sem = new SportsEventManager();
		sem.addGame("Soccer", 22);
		sem.addGame("Football", 44);
		String[] gamenames = {"Soccer", "Football"};
		String msg = sem.addSchedule("Monday", gamenames);
		DaySchedule ds = sem.searchDay("Tuesday");
		assertEquals(null,ds);
	}
	
	@Test
	public void DayWiseScheduleTest()
	{
		SportsEventManager sem = new SportsEventManager();
		sem.addGame("Soccer", 22);
		sem.addGame("Football", 44);
		String[] gamenames = {"Soccer","Football"};
		sem.addSchedule("Monday", gamenames);
		sem.addPlayer("Prakash", gamenames);
		String dws = sem.displayDayWiseSchedule("Monday");
		assertEquals("Game = Soccer Prakash\nGame = Football Prakash\n",dws);
	}
	
	@Test
	public void DayWiseScheduleDoesntExist()
	{
		SportsEventManager sem = new SportsEventManager();
		String dws = sem.displayDayWiseSchedule("Monday");
		assertEquals("Error : This day is not valid",dws);
		
	}
	
	@Test
	public void GameWiseScheduleTest()
	{
		SportsEventManager sem = new SportsEventManager();
		sem.addGame("Soccer", 22);
		sem.addGame("Football", 44);

		String[] gamenames = {"Soccer","Football"};
		sem.addSchedule("Monday", gamenames);
		sem.addPlayer("Prakash", gamenames);
		
		String gws = sem.displayGameWiseSchedule("Soccer");
		assertEquals("Players Names: Prakash\nDay Names: Monday",gws);
	}
	
	@Test
	public void GameWiseScheduleDoesntExist()
	{
		SportsEventManager sem = new SportsEventManager();
		String gws = sem.displayGameWiseSchedule("Soccer");
		assertEquals("Error : This game is not valid",gws);
	}
	
	@Test
	public void PlayerWiseScheduleTest()
	{
		SportsEventManager sem = new SportsEventManager();
		sem.addGame("Soccer", 22);
		sem.addGame("Football", 44);
		String[] gamenames = {"Soccer","Football"};
		sem.addSchedule("Monday", gamenames);
		sem.addPlayer("Prakash", gamenames);
		String pws = sem.displayPlayerWiseSchedule("Prakash");
		assertEquals("Game : Soccer Monday\nGame : Football Monday\n",pws);
	}
	
	@Test
	public void PlayerWiseScheduleDoesntExist()
	{
		SportsEventManager sem = new SportsEventManager();
		String pws = sem.displayPlayerWiseSchedule("Prakash");
		assertEquals("Error : This player is not valid",pws);
	}
	
	
	
}