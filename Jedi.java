import java.io.IOException;

public class Jedi extends Character implements WithForce {
    private static Jedi instance = new Jedi();
    
	private Jedi () {
		this.setHp(100);
		this.setDef(200);
		this.setPower(100);
		this.setForce(200);
		this.setIntelligence(200);
		this.setLvl(1);
	}

	protected static Jedi getInstance() throws IOException {
		Class<?> callerClass = null;
		try {
			callerClass = Class.forName(Thread.currentThread().getStackTrace()[2].getClassName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.exit(0);
		}
		if(!callerClass.isAssignableFrom(instance.getClass()))
		{
			throw new IOException("Vous ne pouvez pas appeler cette fonction depuis cette Class");
		}
        return instance;
    }
	
	public void forceAttack(Character enemy) {
		double deg;
		deg = ((this.getForce()/10)*2)*BonusDamage();
		if(deg > 0)
			enemy.setHp(enemy.getHp() - deg);
		else
			deg = 0;
		System.out.println(this.getName()+" inflige "+deg+" d�gats.");
	}
}