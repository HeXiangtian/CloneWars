import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Character {
	private String name;
	private double hp;
	private double def;
	private double power;
	private double force;
	private double intelligence;
	private int lvl;
	private double xp;
    private static Character instance;
    
	public Character() {
		this.setLvl(0);
		this.setXp(0);
	}
	
	protected static Character getInstance(String classe) throws IOException {
		if(instance != null)
			throw new IOException("Ce personnage existe deja.");
		else if ("Jawa".equals(classe))
			instance = Jawa.getInstance();
		else if ("Jedi".equals(classe))
		    instance = Jedi.getInstance();
		else if ("BountyHunters".equals(classe))
		    instance = BountyHunters.getInstance();
		else if ("ImperialAgent".equals(classe))
		    instance = ImperialAgent.getInstance();
		else if ("Mercenary".equals(classe))
		    instance = Mercenary.getInstance();
		else if ("Sith".equals(classe))
		    instance = Sith.getInstance();
		else
		{
			instance = null;
			System.out.println("Arretez de me mentir, je le sens dans la force.");
		}
		return instance;
	    }
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getHp() {
		return hp;
	}

	public void setHp(double hp) {
		this.hp = hp;
	}

	public double getDef() {
		return def;
	}

	public void setDef(double def) {
		this.def = def;
	}

	public double getPower() {
		return power;
	}

	public void setPower(double power) {
		this.power = power;
	}

	public double getForce() {
		return force;
	}

	public void setForce(double force) {
		this.force = force;
	}

	public double getIntelligence() {
		return intelligence;
	}

	public void setIntelligence(double intelligence) {
		this.intelligence = intelligence;
	}

	public int getLvl() {
		return lvl;
	}

	public void setLvl(int lvl) {
		this.lvl = lvl;
	}

	public double getXp() {
		return xp;
	}

	public void setXp(double d) {
		this.xp = d;
	}

	public void physicalAttack(Character enemy) {
		double deg;
		deg = (this.getPower() - enemy.getDef()*0.01)*BonusDamage();
		if(deg > 0)
		enemy.setHp(enemy.getHp() - deg);
		else
		deg = 0;
		System.out.println(this.getName()+" inflige "+deg+" dégats.");
	}
	
	public void lvlUp() {
		this.setLvl(this.getLvl() + 1);

	}

	public void armedAttack(Character enemy) {
		if(instance.getClass().getName() != "Jedi" && instance.getClass().getName() != "Sith")
			instance.armedAttack(enemy);
		else
			System.out.println("Cette attaque ne peut pas etre utilisé avec ce personnage");
	}

	public void forceAttack(Character enemy) {
		if(instance.getClass().getName() == "Jedi" || instance.getClass().getName() == "Sith")
			instance.forceAttack(enemy);
		else
			System.out.println("Cette attaque ne peut pas etre utilisé avec ce personnage");
	}

	public String attack(Character enemy, Scanner userInputScanner) {
		String in = null;
		in = userInputScanner.nextLine();
		if(in.equals("1"))
		{
			this.physicalAttack(enemy);
			return in;	
		}
		if(in.equals("2"))
		{
			if(this.getClass().getName() == "Jedi" || this.getClass().getName() == "Sith")
			{
				this.forceAttack(enemy);
				return in;	
			}
			else
			{
				this.armedAttack(enemy);
				return in;	
			}
		}
		if(in.equals("3"))
		{
			this.randomattack(enemy);
			return in;	
		}
		if(in.equals("4"))
		{
			return in;	
		}
		else if(!in.equals("1") || !in.equals("2") || !in.equals("3") || !in.equals("4"))
		{
			in = null;
			System.out.println("veuillez entrez les commandes affichez a l'ecran.");
			return in;	
		}
		return in;	
	}
	
	public void randomattack(Character enemy) {
		double random;
		random = ThreadLocalRandom.current().nextInt(1, 2 + 1);
		int res = (int)random;
		if(res == 1)
			this.physicalAttack(enemy);
		else if(res == 2)
			if(this.getClass().getName() == "Jedi" || this.getClass().getName() == "Sith")
				this.forceAttack(enemy);
			else
				this.armedAttack(enemy);
	}
	
	public void levelUp() {
			this.setXp(this.getXp() - (500 * (this.getLvl()+1) + 500 * Math.pow((this.getLvl()+1), 2)));
			this.setLvl(this.getLvl() + 1);
			this.setHp(this.getHp()*1.5);
			this.setDef(this.getDef()*1.5);
			this.setIntelligence(this.getIntelligence()*1.5);
			this.setPower(this.getPower()*1.5);
			System.out.println("Lvl Up");
	}
	public static double BonusDamage()
	{
		double MaxBonus = 1.05;
		double MinBonus = 0.95;
		double RandomBonus;
		double FinalBonus;
		RandomBonus = ThreadLocalRandom.current().nextDouble(MinBonus, MaxBonus + 0.01);
		FinalBonus =  (double)RandomBonus;
		return FinalBonus;
	}
}
