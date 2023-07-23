package statsrandomizer;

import java.io.*;

import legend.game.saves.ConfigEntry;
import legend.game.saves.ConfigStorageLocation;

public class StatConfigEntry extends ConfigEntry<StatData[]> {

    public StatConfigEntry(){
        super(new StatData[9], ConfigStorageLocation.SAVE,
                (StatData[] arr)->
                {
                    try {
                        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
                        objectOutputStream.writeInt(arr.length);
                        for (StatData data : arr) {
                            objectOutputStream.writeObject(data);
                        }
                        objectOutputStream.flush();
                        return byteArrayOutputStream.toByteArray();
                    }catch (IOException err){

                    }
                    return new byte[]{};
                },
                (byte[] bytes)->
                {
                    try {
                        if(bytes.length == 0) return new StatData[9];
                        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
                        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
                        int length = objectInputStream.readInt();
                        StatData[] data = new StatData[length];
                        for (int i = 0; i < length; i++) {
                            data[i] = (StatData) objectInputStream.readObject();
                        }
                        return data;
                    }catch (IOException | ClassNotFoundException err){

                    }
                    return new StatData[9];
                }
        );
    }
}
