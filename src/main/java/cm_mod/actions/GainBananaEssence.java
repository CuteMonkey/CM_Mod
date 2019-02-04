package cm_mod.actions;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.DamageInfo;

import cm_mod.powers.BananaEssence;

public class GainBananaEssence extends AbstractGameAction {
	boolean isFast;
	
	public GainBananaEssence(AbstractPlayer p, int stackAmt, boolean isFast) {
		this.duration = Settings.ACTION_DUR_FAST;
		this.actionType = ActionType.POWER;
		this.amount = stackAmt;
		this.source = p;
		this.target = p;
		this.isFast = isFast;
	}
	
	public void update() {
		if(!this.target.hasPower("CM_BananaGainSeal")) {
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.target, this.source,
				new BananaEssence(this.target, this.amount), this.amount, this.isFast));
			
			if(this.target.hasPower("CM_BananaFlameGas")) {
				int gainBEAmt = this.amount;
				int BFGAmt = this.target.getPower("CM_BananaFlameGas").amount;
				for(int i = 0; i < gainBEAmt; i++) {
					AbstractDungeon.actionManager.addToBottom(new DamageRandomEnemyAction(
						new DamageInfo(this.target, BFGAmt, DamageInfo.DamageType.NORMAL),
						AbstractGameAction.AttackEffect.FIRE));
				}
			}
		}
		
		this.isDone = true;
	}
}
