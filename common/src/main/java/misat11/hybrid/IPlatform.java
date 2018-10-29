package misat11.hybrid;

public interface IPlatform {
	public void log(String message);
	
	public boolean xbox();
	
	public boolean debug();
	
	public int getOnlinePlayers();
	
	public int getMaxPlayers();
	
	public String getMotd();
	
	public String getSubmotd();

}
