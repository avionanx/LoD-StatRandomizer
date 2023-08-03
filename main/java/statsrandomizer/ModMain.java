package statsrandomizer;

import java.util.Random;

import legend.core.GameEngine;
import legend.game.modding.Mod;
import legend.game.modding.events.EventListener;

import legend.game.modding.events.characters.CharacterStatsEvent;
import legend.game.modding.events.gamestate.GameLoadedEvent;
import legend.game.saves.ConfigRegistryEvent;

import legend.game.modding.registries.Registrar;
import legend.game.modding.registries.RegistryDelegate;
import legend.game.saves.ConfigEntry;

import legend.game.types.LevelStuff08;
import static legend.core.GameEngine.CONFIG;
import static legend.game.SItem.levelStuff_800fbd30;
import static legend.game.Scus94491BpeSegment_800b.gameState_800babc8;



@Mod(id = ModMain.MOD_ID)
public class ModMain {

  public static final String MOD_ID = "stats-randomizer";
  public static final Registrar<ConfigEntry<?>, ConfigRegistryEvent> StatRegister = new Registrar<>(GameEngine.REGISTRIES.config,MOD_ID);
  public static final RegistryDelegate<StatConfigEntry> STAT = StatRegister.register("stats_random",StatConfigEntry::new);

  private final int[] charIndexes = new int[]{0,3,4,5,6,7,8};

  public ModMain() {
    GameEngine.EVENTS.register(this);
  }

  @EventListener
  public void registerConfigs(final ConfigRegistryEvent event){
    StatRegister.registryEvent(event);
  }



  @EventListener
  public void StatHandler(final CharacterStatsEvent event){
    StatConfigEntry entry = STAT.get();
    StatData[] data = CONFIG.getConfig(entry);

    //if(gameState_800babc8.charData_32c[event.characterId].partyFlags_04 == 0) return;


    //character leveled up OR they just joined the party
    if(data[event.characterId] == null || data[event.characterId].level != event.level){
      if(data[event.characterId] == null){
        StatData statData = new StatData();
        data[event.characterId] = statData;
      }
      data[event.characterId].level = event.level;
      Random random = new Random();
      /*There's got to be a better way to do this*/
      LevelStuff08 levelStuff08 = levelStuff_800fbd30.get(charIndexes[random.nextInt(charIndexes.length)]).deref().get(event.level);
      data[event.characterId].hp = levelStuff08.hp_00.get();

      levelStuff08 = levelStuff_800fbd30.get(charIndexes[random.nextInt(charIndexes.length)]).deref().get(event.level);
      data[event.characterId].speed = levelStuff08.bodySpeed_03.get();

      levelStuff08 = levelStuff_800fbd30.get(charIndexes[random.nextInt(charIndexes.length)]).deref().get(event.level);
      data[event.characterId].bodyAtt = levelStuff08.bodyAttack_04.get();

      levelStuff08 = levelStuff_800fbd30.get(charIndexes[random.nextInt(charIndexes.length)]).deref().get(event.level);
      data[event.characterId].bodyDef = levelStuff08.bodyDefence_06.get();

      levelStuff08 = levelStuff_800fbd30.get(charIndexes[random.nextInt(charIndexes.length)]).deref().get(event.level);
      data[event.characterId].bodyMat = levelStuff08.bodyMagicAttack_05.get();

      levelStuff08 = levelStuff_800fbd30.get(charIndexes[random.nextInt(charIndexes.length)]).deref().get(event.level);
      data[event.characterId].bodyMdef= levelStuff08.bodyMagicDefence_07.get();

      CONFIG.setConfig(entry,data);
    }
    event.bodySpeed = data[event.characterId].speed;
    event.maxHp = data[event.characterId].hp;
    event.bodyAttack = data[event.characterId].bodyAtt;
    event.bodyDefence = data[event.characterId].bodyDef;
    event.bodyMagicAttack = data[event.characterId].bodyMat;
    event.bodyMagicDefence = data[event.characterId].bodyMdef;
  }

  @EventListener
  public void GameLoadDebugger(final GameLoadedEvent event){
    StatConfigEntry entry = STAT.get();
    StatData[] data = CONFIG.getConfig(entry);
    for(int i = 0; i < 9; i++){
      if(data[i] == null) continue;
    }
  }
}
