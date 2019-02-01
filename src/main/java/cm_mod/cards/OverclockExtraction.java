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
import cm_mod.powers.BananaGainSeal;
import cm_mod.powers.BananaFlameGas;

public class OverclockExtraction extends CMCard {
	public static final Logger logger = LogManager.getLogger(CMMod.class.getName());
	
	public static final String ID = "CM_OverclockExtraction";
	
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String IMG_PATH = "img/cards/overclock_extraction.png";
	
	private static final int COST = 1;
	private static final int BGS_AMT = 2;
	private static final int BFG_AMT = 4;
	private static final int UPGRADE_PLUS_BFG = 1;
	
	public OverclockExtraction() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.POWER, AddCardColor.BANANA_COLOR,
			CardRarity.UNCOMMON, CardTarget.SELF);
		this.BCurse = BGS_AMT;
		this.baseMagicNumber = this.magicNumber = BFG_AMT;
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		logger.info("Use OverclockExtractor. Magic numbers: " + this.baseMagicNumber + ", " + this.magicNumber);
		
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new BananaGainSeal(p, this.BCurse),
			this.BCurse));
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new BananaFlameGas(p, this.magicNumber),
			this.magicNumber));
	}
	
	public AbstractCard makeCopy() {
		return new OverclockExtraction();
	}
	
	public void upgrade() {
		if(!this.upgraded) {
			upgradeName();
			upgradeMagicNumber(UPGRADE_PLUS_BFG);
		}
	}
}
