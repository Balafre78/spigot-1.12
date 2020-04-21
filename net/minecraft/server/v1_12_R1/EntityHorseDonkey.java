/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EntityHorseDonkey
/*    */   extends EntityHorseChestedAbstract
/*    */ {
/*    */   public EntityHorseDonkey(World paramWorld) {
/* 17 */     super(paramWorld);
/*    */   }
/*    */   
/*    */   public static void a(DataConverterManager paramDataConverterManager) {
/* 21 */     EntityHorseChestedAbstract.b(paramDataConverterManager, EntityHorseDonkey.class);
/*    */   }
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   protected MinecraftKey J() {
/* 27 */     return LootTables.H;
/*    */   }
/*    */ 
/*    */   
/*    */   protected SoundEffect F() {
/* 32 */     super.F();
/* 33 */     return SoundEffects.aC;
/*    */   }
/*    */ 
/*    */   
/*    */   protected SoundEffect cf() {
/* 38 */     super.cf();
/* 39 */     return SoundEffects.aF;
/*    */   }
/*    */ 
/*    */   
/*    */   protected SoundEffect d(DamageSource paramDamageSource) {
/* 44 */     super.d(paramDamageSource);
/* 45 */     return SoundEffects.aG;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean mate(EntityAnimal paramEntityAnimal) {
/* 50 */     if (paramEntityAnimal == this) {
/* 51 */       return false;
/*    */     }
/*    */     
/* 54 */     if (paramEntityAnimal instanceof EntityHorseDonkey || paramEntityAnimal instanceof EntityHorse) {
/* 55 */       return (dL() && ((EntityHorseAbstract)paramEntityAnimal).dL());
/*    */     }
/*    */     
/* 58 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityAgeable createChild(EntityAgeable paramEntityAgeable) {
/* 63 */     EntityHorseAbstract entityHorseAbstract = (EntityHorseAbstract)((paramEntityAgeable instanceof EntityHorse) ? new EntityHorseMule(this.world) : new EntityHorseDonkey(this.world));
/*    */     
/* 65 */     a(paramEntityAgeable, entityHorseAbstract);
/*    */     
/* 67 */     return entityHorseAbstract;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\EntityHorseDonkey.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */