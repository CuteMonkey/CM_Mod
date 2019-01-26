package cm_mod.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;

public class BananaEssence extends AbstractPower {
	public static final String POWER_ID = "CM_BananaEssence";
	
	private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
	public static final String NAME = powerStrings.NAME;
	
	private static final String IMG = "img/powers/banana_essence.png";
	
	public BananaEssence(AbstractCreature owner, int amount) {
		this.ID = POWER_ID;
		this.name = NAME;
		this.owner = owner;
		this.amount = amount;
		this.img = new Texture(IMG);
	}
}
