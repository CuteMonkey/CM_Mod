package cm_mod.variables;

import com.megacrit.cardcrawl.cards.AbstractCard;

import basemod.abstracts.DynamicVariable;

import cm_mod.cards.CMCard;

public class BananaCurse extends DynamicVariable {
	public String key() {
		return "BC";
	}
	
	public boolean isModified(AbstractCard c) {
		return false;
	}
	
	public int value(AbstractCard c) {
		return ((CMCard) c).BCurse;
	}
	
	public int baseValue(AbstractCard c) {
		return ((CMCard) c).BCurse;
	}
	
	public boolean upgraded(AbstractCard c) {
		return false;
	}
}
