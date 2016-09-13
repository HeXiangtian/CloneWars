
public class Soldat extends Character implements WithoutForce {

	public Soldat()
	{
		this.setHp(100);
		this.setDef(150);
		this.setForce(0);
		this.setIntelligence(125);
		this.setPower(125);
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
