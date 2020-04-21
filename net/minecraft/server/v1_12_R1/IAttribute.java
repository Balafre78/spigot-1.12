package net.minecraft.server.v1_12_R1;

import javax.annotation.Nullable;

public interface IAttribute {
  String getName();
  
  double a(double paramDouble);
  
  double getDefault();
  
  boolean c();
  
  @Nullable
  IAttribute d();
}


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\IAttribute.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */