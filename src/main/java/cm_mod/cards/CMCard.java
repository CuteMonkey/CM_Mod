package cm_mod.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.MonsterGroup;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.cards.DamageInfo;

import com.badlogic.gdx.math.MathUtils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import basemod.abstracts.CustomCard;
import basemod.helpers.ModalChoice;

import cm_mod.CMMod;

public abstract class CMCard extends CustomCard implements ModalChoice.Callback {
	public static final Logger logger = LogManager.getLogger(CMMod.class.getName());
	
	public CMCard(String id, String name, String img, int cost, String rawDescription, CardType type, CardColor color,
		CardRarity rarity, CardTarget target) {
		super(id, name, img, cost, rawDescription, type, color, rarity, target);
	}
	
	protected ModalChoice monkeyCardChoice;
	
	public void optionSelected(AbstractPlayer p, AbstractMonster m, int index) {
		
	}
	
	public boolean didBurst = false;
	
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
	
	protected void resetBBAttributes() {
		this.BBDamage = this.BBBaseDamage;
		this.isBBDamageModified = false;
		this.BBBlock = this.BBBaseBlock;
		this.isBBBlockModified = false;
	}
	
	protected void upgradeBBCost(int newBBCost) {
		this.BBCost = newBBCost;
		this.upgradedBBCost = true;
	}
	
	protected void calculateBBDamage(AbstractPlayer p, MonsterGroup mg) {
		this.isBBDamageModified = false;
		float temp = (float) this.BBBaseDamage;
		
		for(AbstractPower power : p.powers) {
			temp = power.atDamageGive(temp, DamageInfo.DamageType.NORMAL);
		}
		for(AbstractMonster m : mg.monsters) {
			for(AbstractPower power : m.powers) {
				temp = power.atDamageReceive(temp, DamageInfo.DamageType.NORMAL);
			}
		}
		
		if(this.BBBaseDamage != (int) temp) {
			this.isBBDamageModified = true;
		}
		if(temp < 0.0f) {
			temp = 0.0f;
		}
		
		this.BBDamage = MathUtils.round(temp);
	}
	
	protected void calculateBBDamage(AbstractPlayer p, AbstractMonster m) {
		MonsterGroup singleMonster = new MonsterGroup(m);
		calculateBBDamage(p, singleMonster);
	}
	
	protected void upgradeBBDamage(int delta) {
		this.BBBaseDamage += delta;
		this.upgradedBBDamage = true;
	}
	
	protected void calculateBBBlock(AbstractPlayer p) {
		this.isBBBlockModified = false;
		float temp = (float) this.BBBaseBlock;
		
		for(AbstractPower power : p.powers) {
			temp = power.modifyBlock(temp);
		}
		
		if(this.BBBaseBlock != (int) temp) {
			this.isBBBlockModified = true;
		}
		if(temp < 0.0f) {
			temp = 0.0f;
		}
		
		this.BBBlock = MathUtils.round(temp);
	}
	
	protected void upgradeBBBlock(int delta) {
		this.BBBaseBlock += delta;
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
	
	protected void modifyBCurse(int newBCurse) {
		this.BCurse = newBCurse;
	}
	
	public void calculateMonkeyCardDamage(AbstractMonster mo) {
		AbstractPlayer p = AbstractDungeon.player;
		
		if((this.target == CardTarget.ENEMY) || (this.target == CardTarget.SELF_AND_ENEMY)) {
			calculateBBDamage(p, mo);
		} else {
			calculateBBDamage(p, AbstractDungeon.getMonsters());
		}
		calculateBBBlock(p);
	}
}
