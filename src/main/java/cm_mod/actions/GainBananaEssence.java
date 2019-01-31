package cm_mod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;

import cm_mod.powers.BananaEssence;

public class GainBananaEssence extends AbstractGameAction {
	boolean isFast;
	boolean isFirst;
	
	public GainBananaEssence(AbstractPlayer p, int stackAmt, boolean isFast, boolean isFirst) {
		this.duration = Settings.ACTION_DUR_FAST;
		this.actionType = ActionType.POWER;
		this.amount = stackAmt;
		this.source = p;
		this.target = p;
		this.isFast = isFast;
		this.isFirst = isFirst;
	}
	
	public void update() {
		if(!this.target.hasPower("CM_BananaGainSeal")) {
			if(this.isFirst) {
				AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(this.target, this.source,
						new BananaEssence(this.target, this.amount), this.amount, this.isFast));
			} else {
				AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.target, this.source,
						new BananaEssence(this.target, this.amount), this.amount, this.isFast));
			}
		}
		
		this.isDone = true;
	}
}
