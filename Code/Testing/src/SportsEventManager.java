public class SportsEventManager {
	public static final int MAX_GAMES=20;
	public static final int MAX_PLAYERS=100;
	public static final int MAX_DAYS=7;
	Game[] gameList = new Game[MAX_GAMES];
	Player[] playerList = new Player[MAX_PLAYERS];
	DaySchedule[] scheduleList = new DaySchedule[MAX_DAYS];
	int gameCounter=0;
	int playerCounter=0;
	int scheduleCounter=0;
	GameAssociation[] gameAssociationList = new GameAssociation[MAX_GAMES];
	
	public Game[] getGameList() {
		return gameList;
	}
	public void setGameList(Game[] gameList) {
		this.gameList = gameList;
	}
	/**
	 * This method adds game into gamelist
	 * @param name : sets name of the game
	 * @param maxPlayers : sets max players involved in the game
	 * @return 0 if game added successfully, otherwise error code
	 */
	public int addGame(String name,int maxPlayers){
		if(maxPlayers<=0){
			return 100;
		}
		if(gameCounter > (MAX_GAMES)){
			return 98;
		}
		if(name==null){
			return 99;
		}
		Game game = searchGame(name);
		if(game != null){
			return 101;
		}
		game = new Game(name, maxPlayers);		//fault removed, maxPlayers+1 changed
		gameList[gameCounter] = game;
		GameAssociation association = new GameAssociation();
		association.gamename = name;
		association.daynames = new String[MAX_DAYS];
		association.playerNames = new String[MAX_PLAYERS];
		gameAssociationList[gameCounter] = association; 		//fault corrected increment removed
		gameCounter++;
		
		return 0;
	}
	
	/**
	 * This method is used to search game by name
	 * @param name : used to search game by name
	 * @return game object if found else null
	 */
	public Game searchGame(String name) {
		for(int i=0; i < gameCounter; i++){
			Game storedGame = gameList[i]; 	//i+1 changed to i
			if(storedGame.name.equals(name)){
				return storedGame;
			}
		}
		return null;
	}
	
	/**
	 * This method adds player to playerlist
	 * @param name : sets name of the player
	 * @param gameNames : name of games player is playing
	 * @return string indicating successful addition otherwise error
	 */
	public String addPlayer(String name,String[] gameNames){
		Player player = searchPlayer(name);
		if(player != null){
			return name+" already exists";
		}
		//verify every gameName for its validity
		Game[] gamesPlayed = new Game[gameNames.length];
		for(int i=0; i < gameNames.length; i++){	//changed from i=1 to i=0
			String gameName = gameNames[i];
			Game storedGame = searchGame(gameName);
			if(storedGame==null){
				return "Error you cannot be registered for "+gameName;
			}
			gamesPlayed[i] = storedGame;
			GameAssociation association = searchAssociation(storedGame.name);
			association.playerNames[association.noofPlayers++]=name;
		}
		player = new Player(name,gamesPlayed);
		playerList[playerCounter] = player;		
		playerCounter++;
		return name+" added successfully";
	}
	
	/**
	 * This method add days schedule
	 * @param dayName : day name which wants to be scheduled
	 * @param gameNames : name of games to be played on day
	 * @return string indicating successful addition otherwise error
	 */
	public String addSchedule(String dayName,String[] gameNames){
		DaySchedule day = searchDay(dayName);
		if(day != null){
			return dayName+" already scheduled";
		}
		//verify every gameName for its validity
		Game[] gamesPlayed = new Game[gameNames.length];
		for(int i=0; i < gameNames.length; i++){			//length-1 changed to length
			String gameName = gameNames[i];
			Game storedGame = searchGame(gameName);
			if(storedGame==null){
				return "Error you cannot be registered for "+gameName;
			}
			gamesPlayed[i] = storedGame;
			GameAssociation association = searchAssociation(storedGame.name);
			association.daynames[association.noofDays++]=dayName;
		}
		day = new DaySchedule(dayName,gamesPlayed);
		scheduleList[scheduleCounter] = day;
		scheduleCounter++;
		return dayName+" added successfully";
	} 
	
	/**
	 * This method finds Game details by game name
	 * @param name : name of the game
	 * @return game details in GameAssociation list if found, else null
	 */
	private GameAssociation searchAssociation(String name) {
		for(GameAssociation association :gameAssociationList ){
			if(association.gamename.equals(name))
				return association;
		}
		return null;
	}
	
	/**
	 * This method finds player by player name
	 * @param name : name of the player 
	 * @return player details in Player object if found, else null
	 */
	public Player searchPlayer(String name) {
		for(int i=0; i < playerCounter; i++){	//changed from playercounter-1 to playerCounter
			Player storedPlayer = playerList[i];
			if(storedPlayer.name.equals(name)){		//changed to just name from storedPlayer.name
				return storedPlayer;
			}
		}
		return null;
	}
	
	/**
	 * This method finds day schedule by day name
	 * @param name: name of the day
	 * @return details of days schedule if found, else null
	 */
	public DaySchedule searchDay(String name) {
		for(int i=0; i < scheduleCounter; i++){		//i=1 changed to i=0
			DaySchedule storedDay = scheduleList[i];		//i-1 changed to i
			if(storedDay.dayName.equals(name)){
				return storedDay;
			}
		}
		return null;
	}
	
	
	
	/**
	 * This method displays game wise schedule by game name
	 * @param gameName : name of the game
	 * @return String with schedule
	 */
	public String displayGameWiseSchedule(String gameName){
		Game game = searchGame(gameName);
		if(game==null){
			return "Error : This game is not valid";
		}
		String[] playerNames = getPlayerNames(gameName);
		String[] dayNames    = getDayNames(gameName);
		StringBuilder sb = new StringBuilder();
		sb.append("Players Names: ");
		for(String playerName : playerNames){
			if(playerName==null)
				break;
			sb.append(playerName);
		}
		sb.append("\nDay Names: ");
		for(String dayName : dayNames){
			if(dayName==null)
				break;
			sb.append(dayName);
		}
		return sb.toString();
	}
	
	/**
	 * This method displays day wise schedule by day name
	 * @param dayName : name of the day
	 * @return String with day wise schedule
	 */
	public String displayDayWiseSchedule(String dayName){
		DaySchedule schedule = searchDay(dayName);
		if(schedule==null){
			return "Error : This day is not valid";
		}
		StringBuilder sb = new StringBuilder();
		
		Game[] gamesPlayed = schedule.games;
		for(Game g : gamesPlayed){
			sb.append("Game = "+g.name);
			String[] playerNames = getPlayerNames(g.name);
			for(String name : playerNames){
				if(name==null)
					break;
				sb.append(" "+name+"\n");
			} 
		}
		return sb.toString();
	}
	
	/**
	 * This method returns names of all players associated with game
	 * @param name : name of game
	 * @return array of string with player names
	 */
	private String[] getPlayerNames(String name) {
		GameAssociation association = searchAssociation(name);
		return association.playerNames;
	}
	
	/**
	 * This method returns names of all players associated with game
	 * @param name : name of the game
	 * @return array of string with day names
	 */
	private String[] getDayNames(String name) {
		GameAssociation association = searchAssociation(name);
		return association.daynames; 
	}

	/**
	 * This method is used to display schedule for a player
	 * @param playerName : name of player
	 * @return : string with game and days game is played 
	 */
	public String displayPlayerWiseSchedule(String playerName){
		Player player = searchPlayer(playerName);
		if(player==null){
			return "Error : This player is not valid";
		}
		StringBuilder sb = new StringBuilder();
		Game[] gamesPlayed = player.games;
		for(Game g : gamesPlayed){
			sb.append("Game : "+g.name);
			String[] dayNames = getDayNames(g.name);
			for(String name : dayNames){
				if(name==null)
					break;
				sb.append(" "+name+"\n");
			}
		}
		return sb.toString();
	}
	
}
