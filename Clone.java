
public class Clone extends Character implements WithoutForce {

	public Clone()
	{
		this.setHp(100);
		this.setDef(100);
		this.setForce(0);
		this.setIntelligence(100);
		this.setPower(100);
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
