import java.util.Vector;
import java.util.concurrent.ThreadLocalRandom;

public class Factory {
    private static Factory instance = new Factory();
    
    private Factory() {
    	
    }
    
	public void GenerateChar(Room room)
	{
		Character enemy = null;
		String baseName = "TRT_";
		int luck;
		
		int Max = 100;
		int Min = 1;
		int Random;
		
		int MaxName = 999999;
		int MinName = 100000;
		double RandomName;
		String FinalName;
		
		int MaxBonus = 2;
		int MinBonus = 0;
		double RandomBonus;
		int FinalBonus;
		
		Random = ThreadLocalRandom.current().nextInt(Min, Max + 1);
		luck = (int)Random;
	
		RandomName = ThreadLocalRandom.current().nextInt(MinName, MaxName + 1);
		FinalName =  baseName + (int)RandomName;
		
		RandomBonus = ThreadLocalRandom.current().nextInt(MinBonus, MaxBonus + 1);
		FinalBonus =  (int)RandomBonus;
		
		if(luck < 50)
			enemy = new Clone();
		if(luck > 49 && luck < 80)
			enemy = new Soldat();
		if(luck > 79)
			enemy = new Droid();
		
		enemy.setLvl(room.getLvl() + FinalBonus);
		enemy.setHp(enemy.getHp()+enemy.getHp()*0.5*enemy.getLvl());
		enemy.setDef(enemy.getDef()+enemy.getDef()*0.5*enemy.getLvl());
		enemy.setIntelligence(enemy.getIntelligence()+enemy.getIntelligence()*0.5*enemy.getLvl());
		enemy.setPower(enemy.getPower()+enemy.getPower()*0.5*enemy.getLvl());
		enemy.setName(FinalName);

		room.addRemarquezoom(enemy);
	}
	public Vector<Character> GenerateList(Room room, int VectorSize) {
		room.clearRemarquezoom();
		for(int x = 0; x < VectorSize; x++)
			GenerateChar(room);
		return room.getRemarquezoom();
	}
	
	public static Factory getInstance() {
        return instance;
    }
}
