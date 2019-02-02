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
import cm_mod.powers.BananaSapling;

public class PlantBananaTree extends CMCard {
	public static final Logger logger = LogManager.getLogger(CMMod.class.getName());
	
	public static final String ID = "CM_PlantBananaTree";
	
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
	public static final String IMG_PATH = "img/cards/plant_banana_tree.png";
	
	private static final int COST = 2;
	private static final int MAGIC_AMT = 1;
	
	public PlantBananaTree() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION, CardType.POWER, AddCardColor.BANANA_COLOR,
				CardRarity.RARE, CardTarget.SELF);
		
		this.baseMagicNumber = this.magicNumber = MAGIC_AMT;
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		logger.info("Apply " + this.magicNumber + " Banana Sapling to " + p.name + ".");
		
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new BananaSapling(p, this.magicNumber),
			this.magicNumber));
	}
	
	public AbstractCard makeCopy() {
		return new PlantBananaTree();
	}
	
	public void upgrade() {
		if(!this.upgraded) {
			upgradeName();
			this.isInnate = true;
			this.rawDescription = UPGRADE_DESCRIPTION;
			initializeDescription();
		}
	}
}
