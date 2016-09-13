import java.util.Vector;

public class Room {

	private int lvl;
	
	private Vector<Character> Remarquezoom = new Vector<Character>();
	
	public Room(int lvl)
	{
		this.setLvl(lvl);
	}
	
	public int getLvl() {
		return lvl;
	}

	public void setLvl(int lvl) {
		this.lvl = lvl;
	}

	public Vector<Character> getRemarquezoom() {
		return Remarquezoom;
	}

	public void clearRemarquezoom() {
		Remarquezoom.clear();
	}
	
	public void addRemarquezoom(Character character)
	{
		Remarquezoom.add(character);
	}
}
