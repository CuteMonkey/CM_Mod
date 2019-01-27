package cm_mod.variables;

import com.megacrit.cardcrawl.cards.AbstractCard;

import basemod.abstracts.DynamicVariable;

import cm_mod.cards.CMCard;

public class BananaBurstDamage extends DynamicVariable {
	public String key() {
		return "BBD";
	}
	
	public boolean isModified(AbstractCard c) {
		return ((CMCard) c).isBBDamageModified;
	}
	
	public int value(AbstractCard c) {
		return ((CMCard) c).BBDamage;
	}
	
	public int baseValue(AbstractCard c) {
		return ((CMCard) c).BBBaseDamage;
	}
	
	public boolean upgraded(AbstractCard c) {
		return ((CMCard) c).upgradedBBDamage;
	}
}
