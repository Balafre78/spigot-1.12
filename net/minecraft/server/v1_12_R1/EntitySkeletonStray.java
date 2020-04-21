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
/*    */ public class EntitySkeletonStray
/*    */   extends EntitySkeletonAbstract
/*    */ {
/*    */   public EntitySkeletonStray(World paramWorld) {
/* 22 */     super(paramWorld);
/*    */   }
/*    */   
/*    */   public static void a(DataConverterManager paramDataConverterManager) {
/* 26 */     EntityInsentient.a(paramDataConverterManager, EntitySkeletonStray.class);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean P() {
/* 31 */     return (super.P() && this.world.h(new BlockPosition(this)));
/*    */   }
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   protected MinecraftKey J() {
/* 37 */     return LootTables.aq;
/*    */   }
/*    */ 
/*    */   
/*    */   protected SoundEffect F() {
/* 42 */     return SoundEffects.hR;
/*    */   }
/*    */ 
/*    */   
/*    */   protected SoundEffect d(DamageSource paramDamageSource) {
/* 47 */     return SoundEffects.hT;
/*    */   }
/*    */ 
/*    */   
/*    */   protected SoundEffect cf() {
/* 52 */     return SoundEffects.hS;
/*    */   }
/*    */ 
/*    */   
/*    */   SoundEffect p() {
/* 57 */     return SoundEffects.hU;
/*    */   }
/*    */ 
/*    */   
/*    */   protected EntityArrow a(float paramFloat) {
/* 62 */     EntityArrow entityArrow = super.a(paramFloat);
/* 63 */     if (entityArrow instanceof EntityTippedArrow) {
/* 64 */       ((EntityTippedArrow)entityArrow).a(new MobEffect(MobEffects.SLOWER_MOVEMENT, 600));
/*    */     }
/* 66 */     return entityArrow;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\EntitySkeletonStray.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */