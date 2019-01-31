package cm_mod.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;

import cm_mod.actions.GainBananaEssence;;

public class BananaSapling extends AbstractPower {
	public static final String POWER_ID = "CM_BananaSapling";
	
	private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
	public static final String NAME = powerStrings.NAME;
	public static final String[] POWER_DESCRIPTIONS = powerStrings.DESCRIPTIONS;
	
	private static final String IMG = "img/powers/banana_sapling.png";
	
	private static final int PRODUCE_INTERVAL = 2;
	private static int roundCount = PRODUCE_INTERVAL;
	
	public BananaSapling(AbstractCreature owner, int amount) {
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
		this.description = POWER_DESCRIPTIONS[0] + PRODUCE_INTERVAL + POWER_DESCRIPTIONS[1] + this.amount +
			POWER_DESCRIPTIONS[2] + System.lineSeparator();
		this.description += (POWER_DESCRIPTIONS[3] + roundCount + POWER_DESCRIPTIONS[4]);
	}
	
	@Override
	public void atEndOfRound() {
		roundCount--;
		if(roundCount == 0) {
			AbstractDungeon.actionManager.addToBottom(new GainBananaEssence((AbstractPlayer)this.owner,
				this.amount, true, false));
			roundCount = PRODUCE_INTERVAL;
		}
		
		updateDescription();
	}
	
	@Override
	public void onDeath() {
		roundCount = PRODUCE_INTERVAL;
		AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, this.ID));
	}
	
	@Override
	public void onVictory() {
		roundCount = PRODUCE_INTERVAL;
		AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, this.ID));
	}
}
