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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EntityZombieHusk
/*    */   extends EntityZombie
/*    */ {
/*    */   public EntityZombieHusk(World paramWorld) {
/* 23 */     super(paramWorld);
/*    */   }
/*    */   
/*    */   public static void a(DataConverterManager paramDataConverterManager) {
/* 27 */     EntityInsentient.a(paramDataConverterManager, EntityZombieHusk.class);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean P() {
/* 32 */     return (super.P() && this.world.h(new BlockPosition(this)));
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean p() {
/* 37 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   protected SoundEffect F() {
/* 42 */     return SoundEffects.cY;
/*    */   }
/*    */ 
/*    */   
/*    */   protected SoundEffect d(DamageSource paramDamageSource) {
/* 47 */     return SoundEffects.da;
/*    */   }
/*    */ 
/*    */   
/*    */   protected SoundEffect cf() {
/* 52 */     return SoundEffects.cZ;
/*    */   }
/*    */ 
/*    */   
/*    */   protected SoundEffect dm() {
/* 57 */     return SoundEffects.db;
/*    */   }
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   protected MinecraftKey J() {
/* 63 */     return LootTables.ar;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean B(Entity paramEntity) {
/* 68 */     boolean bool = super.B(paramEntity);
/* 69 */     if (bool && getItemInMainHand().isEmpty() && paramEntity instanceof EntityLiving) {
/* 70 */       float f = this.world.D(new BlockPosition(this)).b();
/* 71 */       ((EntityLiving)paramEntity).addEffect(new MobEffect(MobEffects.HUNGER, 140 * (int)f));
/*    */     } 
/*    */     
/* 74 */     return bool;
/*    */   }
/*    */ 
/*    */   
/*    */   protected ItemStack dn() {
/* 79 */     return ItemStack.a;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\EntityZombieHusk.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */