package cm_mod.variables;

import com.megacrit.cardcrawl.cards.AbstractCard;

import basemod.abstracts.DynamicVariable;

import cm_mod.cards.CMCard;

public class BananaBurstMagic extends DynamicVariable {
	public String key() {
		return "BBM";
	}
	
	public boolean isModified(AbstractCard c) {
		return false;
	}
	
	public int value(AbstractCard c) {
		return ((CMCard) c).BBMagic;
	}
	
	public int baseValue(AbstractCard c) {
		return ((CMCard) c).BBMagic;
	}
	
	public boolean upgraded(AbstractCard c) {
		return ((CMCard) c).upgradedBBMagic;
	}
}
