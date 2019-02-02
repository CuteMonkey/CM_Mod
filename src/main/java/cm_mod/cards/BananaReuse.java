package cm_mod.cards;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cm_mod.CMMod;
import cm_mod.cards.CMCard;
import cm_mod.patches.AddCardColor;
import cm_mod.powers.AutoBananaShield;

public class BananaReuse extends CMCard {
	public static final Logger logger = LogManager.getLogger(CMMod.class.getName());
	
	public static final String ID = "CM_BananaReuse";
	
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String IMG_PATH = "img/cards/banana_reuse.png";
	
	private static final int COST = 1;
	private static final int ABS_AMT = 3;
	private static final int UPGRADE_PLUS_ABS = 1;
	
	public BananaReuse() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.POWER, AddCardColor.BANANA_COLOR,
			CardRarity.UNCOMMON, CardTarget.SELF);
		
		this.baseMagicNumber = this.magicNumber = ABS_AMT;
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		logger.info("Apply " + this.magicNumber + " Auto Banana Shield to " + p.name + ".");
		
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new AutoBananaShield(p, this.magicNumber),
			this.magicNumber));
	}
	
	public AbstractCard makeCopy() {
		return new BananaReuse();
	}
	
	public void upgrade() {
		if(!this.upgraded) {
			upgradeName();
			upgradeMagicNumber(UPGRADE_PLUS_ABS);
		}
	}
}
