package cm_mod.variables;

import com.megacrit.cardcrawl.cards.AbstractCard;

import basemod.abstracts.DynamicVariable;

import cm_mod.cards.CMCard;

public class BananaBurstBlock extends DynamicVariable {
	public String key() {
		return "BBB";
	}
	
	public boolean isModified(AbstractCard c) {
		return ((CMCard) c).isBBBlockModified;
	}
	
	public int value(AbstractCard c) {
		return ((CMCard) c).BBBlock;
	}
	
	public int baseValue(AbstractCard c) {
		return ((CMCard) c).BBBaseBlock;
	}
	
	public boolean upgraded(AbstractCard c) {
		return ((CMCard) c).upgradedBBBlock;
	}
}
