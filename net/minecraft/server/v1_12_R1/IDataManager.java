package net.minecraft.server.v1_12_R1;

import java.io.File;
import java.util.UUID;
import javax.annotation.Nullable;

public interface IDataManager {
  @Nullable
  WorldData getWorldData();
  
  void checkSession() throws ExceptionWorldConflict;
  
  IChunkLoader createChunkLoader(WorldProvider paramWorldProvider);
  
  void saveWorldData(WorldData paramWorldData, NBTTagCompound paramNBTTagCompound);
  
  void saveWorldData(WorldData paramWorldData);
  
  IPlayerFileData getPlayerFileData();
  
  void a();
  
  File getDirectory();
  
  File getDataFile(String paramString);
  
  DefinedStructureManager h();
  
  UUID getUUID();
}


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\IDataManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */