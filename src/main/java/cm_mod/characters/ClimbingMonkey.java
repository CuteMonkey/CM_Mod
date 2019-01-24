package cm_mod.characters;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import com.megacrit.cardcrawl.screens.CharSelectInfo;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;

import cm_mod.patches.AddPlayerClass;
import cm_mod.patches.AddCardColor;
import cm_mod.CMMod;
import basemod.abstracts.CustomPlayer;

public class ClimbingMonkey extends CustomPlayer {
	private static final int ENERGY_PRE_TURN = 3;
	private static final String CM_SHOULDER_1 = null;
	private static final String CM_SHOULDER_2 = null;
	private static final String CM_CORPSE = null;
	
	public ClimbingMonkey(String name) {
		super(name, AddPlayerClass.CLIMBING_MONKEY, null, null);
		
		initializeClass(null, CM_SHOULDER_2, CM_SHOULDER_1, CM_CORPSE, getLoadout(),
			20.0f, -10.0f, 220.0f, 290.0f, new EnergyManager(ENERGY_PRE_TURN));
	}
	
	public ArrayList<String> getStartingDeck() {
		ArrayList<String> retVal = new ArrayList<String>();
		retVal.add("CM_Strick");
		retVal.add("CM_Strick");
		retVal.add("CM_Strick");
		retVal.add("CM_Strick");
		retVal.add("CM_Defend");
		retVal.add("CM_Defend");
		retVal.add("CM_Defend");
		retVal.add("CM_Defend");
		return retVal;
	}
	
	public ArrayList<String> getStartingRelics() {
		ArrayList<String> retVal = new ArrayList<String>();
		retVal.add("CM_BananaExtractor");
		UnlockTracker.markRelicAsSeen("CM_BananaExtractor");
		return retVal;
	}
	
	private static final int STARTING_HP = 80;
	private static final int MAX_HP = 80;
	private static final int STARTING_GOLD = 100;
	private static final int HAND_SIZE = 5;
	private static final int ASCENSION_MAX_HP_LOSS = 5;
	
	private static String cmTitle = setTitle();
	private static String cmFlavor = setFlavor();
	private static String cmName = setName();
	
	//The text to be shown when the character encounters Spire Heart.
	private static String spireHeartText = setSPText();
	
	private static String setTitle() {
		String retVal = "";
		
		return retVal;
	}
	
	private static String setFlavor() {
		String retVal = "";
		
		return retVal;
	}
	
	private static String setName() {
		String retVal;
		
		if(Settings.language == Settings.GameLanguage.ENG) {
			retVal = "Climbing Monkey";
		} else {
			retVal = "\u722c\u5c0f\u7334\u5854";
		}
		
		return retVal;
	}
	
	private static String setSPText() {
		String retVal = "";
		
		return retVal;
	}
	
	public CharSelectInfo getLoadout() {
		return new CharSelectInfo(cmTitle, cmFlavor, STARTING_HP, MAX_HP, 0, STARTING_GOLD, HAND_SIZE,
			this, getStartingRelics(), getStartingDeck(), false);
	}
	
	public String getTitle(PlayerClass playerclass) {
		return cmTitle;
	}
	
	public AbstractCard.CardColor getCardColor() {
		return AddCardColor.BANANA_COLOR;
	}
	
	public Color getCardRenderColor() {
		return CMMod.BANANA_YELLOW;
	}
	
	public AbstractCard getStartCardForEvent() {
		return null;
	}
	
	public Color getCardTrailColor() {
		return CMMod.BANANA_YELLOW;
	}
	
	public int getAscensionMaxHPLoss() {
		return ASCENSION_MAX_HP_LOSS;
	}
	
	public BitmapFont getEnergyNumFont() {
		return FontHelper.energyNumFontRed;
	}
	
	public void doCharSelectScreenSelectEffect() {
		//TO DO
	}
	
	public String getCustomModeCharacterButtonSoundKey() {
		return "SELECT_CM";
	}
	
	public String getLocalizedCharacterName() {
		return cmName;
	}
	
	public AbstractPlayer newInstance() {
		return new ClimbingMonkey(this.name);
	}
	
	public String getSpireHeartText() {
		return spireHeartText;
	}
	
	public Color getSlashAttackColor() {
		return CMMod.BANANA_YELLOW;
	}
	
	public AttackEffect[] getSpireHeartSlashEffect() {
		return new AttackEffect[] {
			AttackEffect.SLASH_HEAVY,
			AttackEffect.FIRE,
			AttackEffect.SLASH_DIAGONAL
		};
	}
	
	public String getVampireText() {
		return com.megacrit.cardcrawl.events.city.Vampires.DESCRIPTIONS[0];
	}
}
