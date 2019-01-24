package cm_mod;

import static cm_mod.patches.AddCardColor.BANANA_COLOR;
import static cm_mod.patches.AddPlayerClass.CLIMBING_MONKEY;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.Gdx;

import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;

import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.localization.RelicStrings;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;

import basemod.BaseMod;
import basemod.interfaces.EditStringsSubscriber;
import basemod.interfaces.EditCardsSubscriber;
import basemod.interfaces.EditRelicsSubscriber;
import basemod.interfaces.EditCharactersSubscriber;

import java.nio.charset.StandardCharsets;

import cm_mod.cards.*;
import cm_mod.relics.*;
import cm_mod.characters.ClimbingMonkey;

@SpireInitializer
public class CMMod implements EditStringsSubscriber, EditCardsSubscriber,
	EditRelicsSubscriber, EditCharactersSubscriber {
	private static final String ATTACK_CC = "img/512/bg_attack_CM_s.png";
	private static final String POWER_CC = "img/512/bg_power_CM_s.png";
	private static final String SKILL_CC = "img/512/bg_skill_CM_s.png";
	private static final String ENERGY_ORB_CC = "img/512/card_orb.png";
	
	private static final String ATTACK_CC_PORTRAIT = "img/1024/bg_attack_CM.png";
	private static final String POWER_CC_PORTRAIT = "img/1024/bg_power_CM.png";
	private static final String SKILL_CC_PORTRAIT = "img/1024/bg_skill_CM.png";
	private static final String ENERGY_ORB_CC_PORTRAIT = "img/1024/card_orb.png";
	
	private static final String CARD_ENERGY_ORB = "img/UI/energy_orb.png";
	
	public static final Color BANANA_YELLOW = CardHelper.getColor(249f, 249f, 6f);
	
	private static final String CM_BUTTON = "img/charSelect/CM_button.png";
	private static final String CM_PORTRAIT = "img/charSelect/CM_portrait.jpg";
	
	private static final String ENG_STRINGS = "localization/eng/";
	private static final String ZHT_STRINGS = "localization/zht/";
	
	private static final String CARD_STRINGS = "CMMod_cards.json";
	private static final String POWER_STRINGS = "CMMod_powers.json";
	private static final String RELIC_STRINGS = "CMMod_relics.json";
	
	public CMMod() {
		BaseMod.subscribe(this);
		
		BaseMod.addColor(
			BANANA_COLOR,
			BANANA_YELLOW,
			BANANA_YELLOW,
			BANANA_YELLOW,
			BANANA_YELLOW,
			BANANA_YELLOW,
			BANANA_YELLOW,
			BANANA_YELLOW,
			ATTACK_CC,
			POWER_CC,
			SKILL_CC,
			ENERGY_ORB_CC,
			ATTACK_CC_PORTRAIT,
			POWER_CC_PORTRAIT,
			SKILL_CC_PORTRAIT,
			ENERGY_ORB_CC_PORTRAIT,
			CARD_ENERGY_ORB
		);
	}
	
	public static void initialize() {
		new CMMod();
	}
	
	@Override
	public void receiveEditCharacters() {
		BaseMod.addCharacter(
			new ClimbingMonkey(CardCrawlGame.playerName),
			CM_BUTTON,
			CM_PORTRAIT,
			CLIMBING_MONKEY
		);
	}
	
	@Override
	public void receiveEditStrings() {
		String cardStrings, powerStrings, relicStrings,
			card, power, relic;
		
		if(Settings.language == Settings.GameLanguage.ENG) {
			card = ENG_STRINGS + CARD_STRINGS;
			power = ENG_STRINGS + POWER_STRINGS;
			relic = ENG_STRINGS + RELIC_STRINGS;
		} else {
			card = ZHT_STRINGS + CARD_STRINGS;
			power = ZHT_STRINGS + POWER_STRINGS;
			relic = ZHT_STRINGS + RELIC_STRINGS;
		}
		
		cardStrings = Gdx.files.internal(card).readString(String.valueOf(StandardCharsets.UTF_8));
		BaseMod.loadCustomStrings(CardStrings.class, cardStrings);
		powerStrings = Gdx.files.internal(power).readString(String.valueOf(StandardCharsets.UTF_8));
		BaseMod.loadCustomStrings(PowerStrings.class, powerStrings);
		relicStrings = Gdx.files.internal(relic).readString(String.valueOf(StandardCharsets.UTF_8));
		BaseMod.loadCustomStrings(RelicStrings.class, relicStrings);
	}
	
	@Override
	public void receiveEditCards() {
		BaseMod.addCard(new Strike());
		BaseMod.addCard(new Defend());
	}
	
	@Override
	public void receiveEditRelics() {
		BaseMod.addRelicToCustomPool(new BananaExtractor(), BANANA_COLOR);
	}
}
