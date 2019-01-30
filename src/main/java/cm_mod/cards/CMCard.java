package cm_mod.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import basemod.abstracts.CustomCard;

public abstract class CMCard extends CustomCard {
	public CMCard(String id, String name, String img, int cost, String rawDescription, CardType type, CardColor color,
		CardRarity rarity, CardTarget target) {
		super(id, name, img, cost, rawDescription, type, color, rarity, target);
	}
	
	public int BBCost = 0;
	public boolean upgradedBBCost = false;
	
	public int BBBaseDamage = 0;
	public int BBDamage = 0;
	public boolean isBBDamageModified = false;
	public boolean upgradedBBDamage = false;
	
	public int BBBaseBlock = 0;
	public int BBBlock = 0;
	public boolean isBBBlockModified = false;
	public boolean upgradedBBBlock = false;
	
	public int BBMagic = 0;
	public boolean upgradedBBMagic = false;
	
	public int BCurse = 0;
	
	protected void reseBBtAttributes() {
		this.BBDamage = this.BBBaseDamage;
		this.isBBDamageModified = false;
		this.BBBlock = this.BBBaseBlock;
		this.isBBBlockModified = false;
	}
	
	protected void upgradeBBCost(int newBBCost) {
		this.BBCost = newBBCost;
		this.upgradedBBCost = true;
	}
	
	protected void calculateBBDamage(AbstractPlayer p, AbstractMonster m) {
		this.isBBDamageModified = false;
		float temp = (float) this.BBBaseDamage;
		
		if(p.hasPower("Weakened")) {
			this.isBBDamageModified = true;
			temp *= 0.75f;
		}
		if(m.hasPower("Vulnerable")) {
			this.isBBDamageModified = true;
			temp *= 1.5f;
		}
		if(p.hasPower("Strength")) {
			this.isBBDamageModified = true;
			temp += p.getPower("Strength").amount;
		}
		
		this.BBDamage = Math.round(temp);
	}
	
	protected void upgradeBBDamage(int delta) {
		this.baseDamage += delta;
		this.upgradedBBDamage = true;
	}
	
	protected void calculateBBBlock(AbstractPlayer p) {
		this.isBBBlockModified = false;
		float temp = (float) this.BBBaseBlock;
		
		if(p.hasPower("Frail")) {
			this.isBBBlockModified = true;
			temp *= 0.75f;
		}
		if(p.hasPower("Dexterity")) {
			this.isBBBlockModified = true;
			temp += p.getPower("Dexterity").amount;
		}
		
		this.BBBlock = Math.round(temp);
	}
	
	protected void upgradeBBBlock(int delta) {
		this.baseBlock += delta;
		this.upgradedBBBlock = true;
	}
	
	protected void upgradeBBMagic(int delta) {
		this.BBMagic += delta;
		this.upgradedBBMagic = true;
	}
	
	protected boolean isBEEnough(AbstractPlayer p) {
		if(p.hasPower("CM_BananaEssence")) {
			if(p.getPower("CM_BananaEssence").amount >= this.BBCost) {
				return true;
			}
		}
		return false;
	}
}
