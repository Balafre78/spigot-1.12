package net.minecraft.server.v1_12_R1;

import java.io.IOException;
import javax.annotation.Nullable;

public interface IChunkLoader {
  @Nullable
  Chunk a(World paramWorld, int paramInt1, int paramInt2) throws IOException;
  
  void a(World paramWorld, Chunk paramChunk) throws IOException, ExceptionWorldConflict;
  
  void b(World paramWorld, Chunk paramChunk) throws IOException;
  
  void b();
  
  void c();
  
  boolean chunkExists(int paramInt1, int paramInt2);
}


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\IChunkLoader.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */