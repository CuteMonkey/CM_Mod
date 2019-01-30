package cm_mod.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;

public class AutoBananaShield extends AbstractPower {
	private static final String POWER_ID = "CM_AutoBananaShield";
	
	private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
	public static final String NAME = powerStrings.NAME;
	public static final String[] POWER_DESCRIPTIONS = powerStrings.DESCRIPTIONS;
	
	private static final String IMG = "img/powers/auto_banana_shield.png";
	
	public AutoBananaShield(AbstractCreature owner, int amount) {
		this.ID = POWER_ID;
		this.name = NAME;
		this.owner = owner;
		this.amount = amount;
		this.img = new Texture(IMG);
		
		updateDescription();
	}
	
	@Override
	public void stackPower(int stackAmt) {
		this.fontScale = 8.0f;
		this.amount += stackAmt;
		updateDescription();
	}
	
	@Override
	public void updateDescription() {
		this.description = POWER_DESCRIPTIONS[0] + this.amount + POWER_DESCRIPTIONS[1];
	}
	
	@Override
	public void onDeath() {
		AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, this.ID));
	}
	
	@Override
	public void onVictory() {
		AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, this.ID));
	}
}
