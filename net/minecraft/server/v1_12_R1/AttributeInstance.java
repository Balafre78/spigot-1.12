package net.minecraft.server.v1_12_R1;

import java.util.Collection;
import java.util.UUID;
import javax.annotation.Nullable;

public interface AttributeInstance {
  IAttribute getAttribute();
  
  double b();
  
  void setValue(double paramDouble);
  
  Collection<AttributeModifier> a(int paramInt);
  
  Collection<AttributeModifier> c();
  
  boolean a(AttributeModifier paramAttributeModifier);
  
  @Nullable
  AttributeModifier a(UUID paramUUID);
  
  void b(AttributeModifier paramAttributeModifier);
  
  void c(AttributeModifier paramAttributeModifier);
  
  void b(UUID paramUUID);
  
  double getValue();
}


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\AttributeInstance.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */