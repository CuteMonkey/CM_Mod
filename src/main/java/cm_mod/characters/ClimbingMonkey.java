package cm_mod.characters;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import com.megacrit.cardcrawl.screens.CharSelectInfo;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cm_mod.cards.Strike;
import cm_mod.patches.AddPlayerClass;
import cm_mod.patches.AddCardColor;
import cm_mod.CMMod;

import basemod.abstracts.CustomPlayer;
import basemod.animations.SpriterAnimation;

public class ClimbingMonkey extends CustomPlayer {
	public static final Logger logger = LogManager.getLogger(CMMod.class.getName());
	
	private static final int ENERGY_PRE_TURN = 3;
	private static final String CM_SHOULDER_1 = "img/char/CM/shoulder1.png";
	private static final String CM_SHOULDER_2 = "img/char/CM/shoulder2.png";
	private static final String CM_CORPSE = "img/char/CM/fallen.png";
	private static final String CM_ANIMATION = "img/char/CM/animation/cm_movement.scml";
	
	private static final String[] ORB_TEXTURES = {
		"img/UI/EPanel/layer4.png",
		"img/UI/EPanel/layer3.png",
		"img/UI/EPanel/layer2.png",
		"img/UI/EPanel/layer1.png",
		"img/UI/EPanel/layer0.png",
		"img/UI/EPanel/layer4d.png",
		"img/UI/EPanel/layer3d.png",
		"img/UI/EPanel/layer2d.png",
		"img/UI/EPanel/layer1d.png"
	};
	private static final String ORB_VFX = "img/UI/energy_yellow_VFX.png";
	private static final float[] LAYER_SPEED = {
		-30.0f, 20.0f, -10.0f, 0.0f, -10.0f, 8.0f, -5.0f, 0.0f};
	
	public ClimbingMonkey(String name) {
		super(name, AddPlayerClass.CLIMBING_MONKEY, ORB_TEXTURES, ORB_VFX, LAYER_SPEED,
			new SpriterAnimation(CM_ANIMATION));
		
		logger.info("Start to initialize Climbing Monkey.");
		initializeClass(null, CM_SHOULDER_2, CM_SHOULDER_1, CM_CORPSE, getLoadout(),
			20.0f, -10.0f, 220.0f, 290.0f, new EnergyManager(ENERGY_PRE_TURN));
		logger.info("Initialize Climbing Monkey complete.");
	}
	
	public ArrayList<String> getStartingDeck() {
		logger.info("Start to get starting deck.");
		
		ArrayList<String> retVal = new ArrayList<String>();
		retVal.add("CM_Strike");
		retVal.add("CM_Strike");
		retVal.add("CM_Strike");
		retVal.add("CM_Strike");
		retVal.add("CM_Defend");
		retVal.add("CM_Defend");
		retVal.add("CM_Defend");
		retVal.add("CM_Defend");
		return retVal;
	}
	
	public ArrayList<String> getStartingRelics() {
		logger.info("Start to get starting relics.");
		
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
			retVal = "\u722c\u5854\u5c0f\u7334";
		}
		
		return retVal;
	}
	
	private static String setSPText() {
		String retVal = "";
		
		return retVal;
	}
	
	public CharSelectInfo getLoadout() {
		logger.info("Start to get loadout.");
		
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
	
	//I do not know what this for.
	public AbstractCard getStartCardForEvent() {
		logger.info("Start to get start card for event.");
		
		return new Strike();
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
	
	//Copy from Ironclad
	public void doCharSelectScreenSelectEffect() {
		logger.info("Start to do character select screen select effect.");
		
		CardCrawlGame.sound.playA("ATTACK_HEAVY", MathUtils.random(-0.2f, 0.2f));
		CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.MED, ScreenShake.ShakeDur.SHORT, true);
		
		logger.info("Do character select screen select effect complete.");
	}
	
	public String getCustomModeCharacterButtonSoundKey() {
		logger.info("Start to get custom mode character button sound key.");
		
		return "ATTACK_HEAVY";
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
