package cm_mod.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.core.AbstractCreature;

public class BananaEssence extends AbstractPower {
	public static final String POWER_ID = "CM_BananaEssence";
	
	private static final String IMG = "img/powers/banana_essence.png";
	
	public BananaEssence(AbstractCreature owner, int amount) {
		this.ID = POWER_ID;
		this.amount = amount;
		
		this.img = new Texture(IMG);
	}
}
