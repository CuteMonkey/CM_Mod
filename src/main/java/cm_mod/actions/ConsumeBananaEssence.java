package cm_mod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;

public class ConsumeBananaEssence extends AbstractGameAction {
	public ConsumeBananaEssence(AbstractPlayer p, AbstractCreature target, int consumeAmt) {
		this.duration = Settings.ACTION_DUR_XFAST;
		this.actionType = AbstractGameAction.ActionType.REDUCE_POWER;
		this.amount = consumeAmt;
		this.source = p;
		this.target = target;
	}
	
	public void update() {
		if(this.source.hasPower("CM_BananaEssence")) {
			this.source.getPower("CM_BananaEssence").reducePower(this.amount);
		}
		this.isDone = true;
	}
}
