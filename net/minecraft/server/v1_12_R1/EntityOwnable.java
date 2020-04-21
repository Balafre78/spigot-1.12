package net.minecraft.server.v1_12_R1;

import java.util.UUID;
import javax.annotation.Nullable;

public interface EntityOwnable {
  @Nullable
  UUID getOwnerUUID();
  
  @Nullable
  Entity getOwner();
}


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\EntityOwnable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */