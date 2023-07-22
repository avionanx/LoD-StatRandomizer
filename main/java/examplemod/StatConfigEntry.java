package examplemod;

import legend.game.saves.ConfigEntry;
import legend.game.saves.ConfigStorageLocation;

import java.nio.ByteBuffer;

public class StatConfigEntry extends ConfigEntry<Integer> {


    public StatConfigEntry(){
        super(1, ConfigStorageLocation.SAVE,StatConfigEntry::serializer,StatConfigEntry::deserializer);

    }

    private static byte[] serializer(final int val){
        return new byte[]{(byte)val};
    }
    private static int deserializer(final byte[] data){
        return 1;//ByteBuffer.wrap(data).getInt();
    }
}
