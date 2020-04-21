package net.minecraft.server.v1_12_R1;

import javax.annotation.Nullable;

public interface IDragonController {
  boolean a();
  
  void b();
  
  void c();
  
  void a(EntityEnderCrystal paramEntityEnderCrystal, BlockPosition paramBlockPosition, DamageSource paramDamageSource, @Nullable EntityHuman paramEntityHuman);
  
  void d();
  
  void e();
  
  float f();
  
  float h();
  
  DragonControllerPhase<? extends IDragonController> getControllerPhase();
  
  @Nullable
  Vec3D g();
  
  float a(EntityComplexPart paramEntityComplexPart, DamageSource paramDamageSource, float paramFloat);
}


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\IDragonController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */