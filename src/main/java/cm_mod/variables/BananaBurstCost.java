package cm_mod.variables;

import com.megacrit.cardcrawl.cards.AbstractCard;

import basemod.abstracts.DynamicVariable;

import cm_mod.cards.CMCard;

public class BananaBurstCost extends DynamicVariable {
	public String key() {
		return "BBC";
	}
	
	public boolean isModified(AbstractCard c) {
		return false;
	}
	
	public int value(AbstractCard c) {
		return ((CMCard) c).BBCost;
	}
	
	public int baseValue(AbstractCard c) {
		return ((CMCard) c).BBCost;
	}
	
	public boolean upgraded(AbstractCard c) {
		return ((CMCard) c).upgradedBBCost;
	}
}
