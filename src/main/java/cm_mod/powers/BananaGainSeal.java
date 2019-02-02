package cm_mod.powers;

import com.badlogic.gdx.graphics.Texture;

import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;

public class BananaGainSeal extends AbstractPower {
	public static final String POWER_ID = "CM_BananaGainSeal";
	
	private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
	public static final String NAME = powerStrings.NAME;
	public static final String[] POWER_DESCRIPTIONS = powerStrings.DESCRIPTIONS;
	
	private static final String IMG = "img/powers/banana_gain_seal.png";
	
	public BananaGainSeal(AbstractCreature owner, int amount) {
		this.ID = POWER_ID;
		this.name = NAME;
		this.owner = owner;
		this.amount = amount;
		this.img = new Texture(IMG);
		this.type = PowerType.DEBUFF;
		this.isTurnBased = true;
		
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
	public void atEndOfRound() {
		if(this.amount == 0) {
			AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, this.ID));
		} else {
			AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(this.owner, this.owner, this.ID, 1));
			updateDescription();
		}
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
