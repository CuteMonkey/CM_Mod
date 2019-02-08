package cm_mod.powers;

import com.badlogic.gdx.graphics.Texture;

import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.DamageInfo;

import cm_mod.CMMod;

public class BananaEssence extends AbstractPower {
	public static final String POWER_ID = "CM_BananaEssence";
	
	private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
	public static final String NAME = powerStrings.NAME;
	public static final String DESCRIPTION = powerStrings.DESCRIPTIONS[0];
	
	private static final String IMG = "img/powers/banana_essence.png";
	
	private static final int REDUCE_AMT_AT_END = 5;
	
	public BananaEssence(AbstractCreature owner, int amount) {
		this.ID = POWER_ID;
		this.name = NAME;
		this.description = DESCRIPTION;
		this.owner = owner;
		this.amount = amount;
		this.img = new Texture(IMG);
	}
	
	@Override
	public void stackPower(int stackAmt) {
		this.fontScale = 8.0f;
		this.amount += stackAmt;
		
		if(this.owner.hasPower("CM_BananaFlameGas")) {
			int BFGAmt = this.owner.getPower("CM_BananaFlameGas").amount;
			for(int i = 0; i < stackAmt; i++) {
				AbstractDungeon.actionManager.addToBottom(new DamageRandomEnemyAction(new DamageInfo(
					this.owner, BFGAmt), AbstractGameAction.AttackEffect.FIRE));
			}
		}
	}
	
	@Override
	public void onVictory() {
		if(this.amount > REDUCE_AMT_AT_END) {
			CMMod.nextStartingBE = this.amount - REDUCE_AMT_AT_END;
		} else {
			CMMod.nextStartingBE = 0;
		}
	}
}
