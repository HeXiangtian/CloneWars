
public class Droid extends Character implements WithoutForce {

	public Droid()
	{
		this.setHp(50);
		this.setDef(100);
		this.setForce(0);
		this.setIntelligence(200);
		this.setPower(25);
	}

	public void armedAttack(Character enemy) {
		double deg;
		deg = (this.getIntelligence() - enemy.getDef()*0.01)*BonusDamage();
		if(deg > 0)
			enemy.setHp(enemy.getHp() - deg);
		else
			deg = 0;
		System.out.println(this.getName()+" inflige "+deg+" dégats.");
	}
}
