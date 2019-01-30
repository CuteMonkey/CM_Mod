package cm_mod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.core.Settings;

public class ConsumeBananaEssence extends AbstractGameAction {
	public ConsumeBananaEssence(AbstractPlayer p, int consumeAmt) {
		this.duration = Settings.ACTION_DUR_XFAST;
		this.actionType = AbstractGameAction.ActionType.REDUCE_POWER;
		this.amount = consumeAmt;
		this.source = p;
		this.target = p;
	}
	
	public void update() {
		if(this.target.hasPower("CM_BananaEssence")) {
			this.target.getPower("CM_BananaEssence").reducePower(this.amount);
			
			if(this.target.hasPower("CM_AutoBananaShield")) {
				int consumeBEAmt = this.amount;
				int ABSAmt = this.target.getPower("CM_AutoBananaShield").amount;
				for(int i = 0; i < consumeBEAmt; i++) {
					AbstractDungeon.actionManager.addToBottom(new GainBlockAction(this.target, this.source,
						ABSAmt, true));
				}
			}
			
			if(this.target.getPower("CM_BananaEssence").amount == 0) {
				AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.target, this.source,
					"CM_BananaEssence"));
			}
		}
		this.isDone = true;
	}
}
