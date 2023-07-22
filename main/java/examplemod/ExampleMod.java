package examplemod;

import java.nio.ByteBuffer;
import java.util.Random;

import legend.core.GameEngine;
import legend.game.modding.Mod;
import legend.game.modding.events.Event;
import legend.game.modding.events.EventListener;
import legend.game.modding.events.battle.BattleEndedEvent;
import legend.game.modding.events.battle.BattleStartedEvent;
import legend.game.modding.events.characters.CharacterStatsEvent;
import legend.game.modding.events.characters.XpToLevelEvent;
import legend.game.modding.events.config.ConfigEvent;
import legend.game.modding.events.config.ConfigLoadedEvent;
import legend.game.modding.events.config.ConfigUpdatedEvent;
import legend.game.modding.events.gamestate.GameLoadedEvent;
import legend.game.modding.events.gamestate.GameStateEvent;
import legend.game.modding.events.input.InputReleasedEvent;
import legend.game.modding.events.gamestate.NewGameEvent;
import legend.game.modding.events.inventory.TakeItemEvent;
import legend.game.modding.events.registries.RegistryEvent;
import legend.game.modding.events.scripting.ScriptAllocatedEvent;
import legend.game.modding.events.scripting.ScriptEvent;
import legend.game.modding.events.scripting.ScriptTickEvent;
import static legend.game.saves.ConfigStorage.saveConfig;
import static legend.core.GameEngine.CONFIG;

import legend.game.modding.registries.Registrar;
import legend.game.modding.registries.RegistryDelegate;
import legend.game.saves.ConfigEntry;
import legend.game.saves.ConfigRegistryEvent;
import legend.game.saves.ConfigStorage;
import legend.game.saves.ConfigStorageLocation;
import legend.game.types.CharacterData2c;
import legend.game.types.GameState52c;
import legend.game.SMap;
import legend.game.types.LevelStuff08;
import legend.game.unpacker.FileData;

import static legend.game.SItem.levelStuff_800fbd30;

import static legend.game.Scus94491BpeSegment_800b.gameState_800babc8;
@Mod(id = ExampleMod.MOD_ID)
public class ExampleMod {
  public static final String MOD_ID = "example-mod";

  public static final Registrar<ConfigEntry<?>, ConfigRegistryEvent> StatRegister = new Registrar<>(GameEngine.REGISTRIES.config,MOD_ID);
  public static final RegistryDelegate<StatConfigEntry> STAT = StatRegister.register("stats_random",StatConfigEntry::new);

  public ExampleMod() {
    GameEngine.EVENTS.register(this);
  }
  @EventListener
  public void EventHandler(final GameLoadedEvent event){
    final StatConfigEntry entry = STAT.get();
  }
}
