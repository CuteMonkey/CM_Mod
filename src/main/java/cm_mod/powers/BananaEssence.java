package cm_mod.powers;

import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.core.AbstractCreature;

public class BananaEssence extends AbstractPower {
	public static final String POWER_ID = "CM_BananaEssence";
	
	public BananaEssence(AbstractCreature owner, int amount) {
		this.ID = POWER_ID;
		this.amount = amount;
	}
}
