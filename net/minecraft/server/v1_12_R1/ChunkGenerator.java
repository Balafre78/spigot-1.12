package net.minecraft.server.v1_12_R1;

import java.util.List;
import javax.annotation.Nullable;

public interface ChunkGenerator {
  Chunk getOrCreateChunk(int paramInt1, int paramInt2);
  
  void recreateStructures(int paramInt1, int paramInt2);
  
  boolean a(Chunk paramChunk, int paramInt1, int paramInt2);
  
  List<BiomeBase.BiomeMeta> getMobsFor(EnumCreatureType paramEnumCreatureType, BlockPosition paramBlockPosition);
  
  @Nullable
  BlockPosition findNearestMapFeature(World paramWorld, String paramString, BlockPosition paramBlockPosition, boolean paramBoolean);
  
  void recreateStructures(Chunk paramChunk, int paramInt1, int paramInt2);
  
  boolean a(World paramWorld, String paramString, BlockPosition paramBlockPosition);
}


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\ChunkGenerator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */