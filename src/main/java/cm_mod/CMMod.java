package cm_mod;

import static cm_mod.patches.AddCardColor.BANANA_COLOR;

import com.badlogic.gdx.graphics.Color;

import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;

import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.helpers.CardHelper;

import basemod.BaseMod;
import basemod.interfaces.EditStringsSubscriber;
import basemod.interfaces.EditCardsSubscriber;

import cm_mod.cards.*;

@SpireInitializer
public class CMMod implements EditStringsSubscriber, EditCardsSubscriber {
	private static final String ATTACK_CC = "img/512/bg_attack_MC_s.png";
	private static final String POWER_CC = "img/512/bg_power_MC_s.png";
	private static final String SKILL_CC = "img/512/bg_skill_MC_s.png";
	private static final String ENERGY_ORB_CC = "img/512/card_orb.png";
	
	private static final String ATTACK_CC_PORTRAIT = "img/1024/bg_attack_MC.png";
	private static final String POWER_CC_PORTRAIT = "img/1024/bg_power_MC.png";
	private static final String SKILL_CC_PORTRAIT = "img/1024/bg_skill_MC.png";
	private static final String ENERGY_ORB_CC_PORTRAIT = "img/1024/card_orb.png";
	
	private static final String CARD_ENERGY_ORB = "img/UI/energy_orb.png";
	
	public static final Color BANANA_YELLOW = CardHelper.getColor(249f, 249f, 6f);
	
	private static final String CARD_STRING = "localization/CMMod_cards.json";
	
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
	public void receiveEditStrings() {
		String card;
		
		card = CARD_STRING;
		
		BaseMod.loadCustomStringsFile(CardStrings.class, card);
	}
	
	@Override
	public void receiveEditCards() {
		BaseMod.addCard(new Strike());
	}
}
