package ps.maps;

import static core.Script.screen;
import static ps.oo.PSGame.*;
import domain.VImage;
import ps.oo.City;
import ps.oo.Job;
import ps.oo.PSGame;
import ps.oo.Item;
import ps.oo.PartyMember;
import ps.oo.Specie;
import ps.oo.PSGame.Flags;
import ps.oo.PSGame.Planet;
import ps.oo.PSLibImage.PS1Image;
import ps.oo.PSLibItem.OriginalItem;
import ps.oo.PSLibMusic.PS1Music;
import ps.oo.PSMenu;
import ps.oo.PSMenu.EntityClothes;
import ps.oo.PSMenu.EntityType;
import ps.oo.PSMenu.Scene;
import ps.oo.PSMenu.SpecialEntity;
import ps.oo.PartyMember.Gender;

public class Eppi {

	
	public static void hospital() {
		PSMenu.startScene(Scene.HOSPITAL_VILLAGE, EntityType.VILLA_WMN_BLOND, EntityClothes.WHITE);
		PSGame.Hospital(1);
		PSMenu.endScene();
	}

	public static void weap_shop() {
		PSMenu.startScene(Scene.SHOP_WEAPON_VILLAGE, EntityType.VILLA_MAN_BLOND, EntityClothes.RED);
		Shop(getString("Shop_Weapon_Welcome"), false, new Item[]{	getItem(OriginalItem.Weapon_Iron_Axe),
																	getItem(OriginalItem.Weapon_Needle_Gun),
																	getItem(OriginalItem.Shield_Bronze_Shield)});
		PSMenu.endScene();
	}	
	
	
	public static void house1() {
		PSMenu.startScene(Scene.VILLAGE_HOUSE, EntityType.VILLA_MAN_BLOND, EntityClothes.GREEN);
		PSMenu.Stext(getString("Eppi_House_1"));
		PSMenu.endScene();
	}

	public static void house2() {
		PSMenu.startScene(Scene.VILLAGE_HOUSE, EntityType.VILLA_WMN_BLOND, EntityClothes.GREEN);
		PSMenu.Stext(getString("Eppi_House_2"));
		PSMenu.endScene();
	}

	public static void houseKey() {

		PSMenu.startScene(Scene.VILLAGE_HOUSE, SpecialEntity.HASHIM);
		if(PSGame.getGameType() == GameType.PS_ORIGINAL) {
			if(PSGame.getParty().hasQuestItem(PSGame.getItem(OriginalItem.Quest_Dungeon_Key))) {
				PSMenu.StextLast(getString("Eppi_House_Key_Return"));
			}
			else {
				if(PSMenu.Prompt(getString("Eppi_House_Key"), getYesNo()) == 1) {
					PSMenu.StextLast(getString("Eppi_House_KeyYes"));
					PSGame.setFlag(Flags.INFO_KEY);
				} else {
					PSMenu.StextLast(getString("Eppi_House_KeyNo"));
				}
			}
		}
		else {
			// ODIN QUEST
			if(PSGame.hasFlag(Flags.GOT_MYAU)) {
				PSMenu.Stext(getString("Eppi_House_Key_Return"));	
			} else {
				PSMenu.Stext(getString("Eppi_House_Key_Myau"));
				PSGame.setFlag(Flags.GOT_MYAU);
							
				PSGame.getParty().addMember(new PartyMember(Gender.MALE, Specie.MUSK_CAT, Job.NATURER, PSGame.getString("Name_Myau"), PS1Image.PORTRAIT_MYAU, "chars/myau.chr"));
				PSGame.getParty().getMember(1).advanceLevel();
				PSGame.getParty().getMember(1).advanceLevel();
				PSGame.getParty().getMember(1).heal();
				
				PSMenu.instance.back = new VImage(screen.width, screen.height); //.paintBlack();
				PSMenu.instance.entitySprite = null;

				screen.fadeOut(75, false);
				PSGame.playMusic(PS1Music.STORY);	
				PSMenu.cinematicText(PSGame.getImage(PS1Image.CINE_MYAU), new String[]{getString("Cinematic_Myau_Odin1")});
				PSMenu.cinematicText(PSGame.getImage(PS1Image.CINE_ODIN), new String[]{getString("Cinematic_Myau_Odin2")});
				PSMenu.cinematicText(PSGame.getImage(PS1Image.CINE_MYAU), new String[]{getString("Cinematic_Myau_Odin3")});
				PSMenu.cinematicText(PSGame.getImage(PS1Image.CINE_ODIN), new String[]{getString("Cinematic_Myau_Odin4")});
				PSGame.findAndPlayMusic();
				PSGame.getParty().reallocate();
			}
			
		}

		PSMenu.endScene();
	}

	public static void house3() {
		PSMenu.startScene(Scene.VILLAGE_HOUSE, SpecialEntity.OLDMAN);
		if(PSMenu.Prompt(getString("Eppi_House_3"), getYesNo()) == 1) {
			PSMenu.StextLast(getString("Eppi_House_3_Yes"));
		} else {
			PSMenu.StextLast(getString("Eppi_House_3_No"));
		}
		PSMenu.endScene();
	}
	
	public static void house4() {
		PSMenu.startScene(Scene.VILLAGE_HOUSE, EntityType.VILLA_MAN_BLOND, EntityClothes.RED);
		PSMenu.Stext(getString("Eppi_House_4"));
		PSMenu.endScene();
	}

	
	public static void ent1() {
		EntStart();
		PSMenu.Stext(getString("Eppi_People_Ent1"));
		EntFinish();
	}

	public static void exit() {
		mapswitch(Planet.PALMA, City.EPPI.getX(), City.EPPI.getY());
	}	
	
}
