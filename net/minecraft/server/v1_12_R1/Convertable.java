package net.minecraft.server.v1_12_R1;

import java.io.File;

public interface Convertable {
  IDataManager a(String paramString, boolean paramBoolean);
  
  boolean isConvertable(String paramString);
  
  boolean convert(String paramString, IProgressUpdate paramIProgressUpdate);
  
  File b(String paramString1, String paramString2);
}


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\Convertable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */