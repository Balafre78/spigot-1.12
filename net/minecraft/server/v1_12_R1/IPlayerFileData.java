package net.minecraft.server.v1_12_R1;

import javax.annotation.Nullable;

public interface IPlayerFileData {
  void save(EntityHuman paramEntityHuman);
  
  @Nullable
  NBTTagCompound load(EntityHuman paramEntityHuman);
  
  String[] getSeenPlayers();
}


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\IPlayerFileData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */