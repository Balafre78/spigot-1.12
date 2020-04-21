package net.minecraft.server.v1_12_R1;

import javax.annotation.Nullable;

public interface IChunkProvider {
  @Nullable
  Chunk getLoadedChunkAt(int paramInt1, int paramInt2);
  
  Chunk getChunkAt(int paramInt1, int paramInt2);
  
  boolean unloadChunks();
  
  String getName();
  
  boolean e(int paramInt1, int paramInt2);
}


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\IChunkProvider.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */