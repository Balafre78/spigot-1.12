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
/*    */ public class EntityHorseMule
/*    */   extends EntityHorseChestedAbstract
/*    */ {
/*    */   public EntityHorseMule(World paramWorld) {
/* 15 */     super(paramWorld);
/*    */   }
/*    */   
/*    */   public static void a(DataConverterManager paramDataConverterManager) {
/* 19 */     EntityHorseChestedAbstract.b(paramDataConverterManager, EntityHorseMule.class);
/*    */   }
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   protected MinecraftKey J() {
/* 25 */     return LootTables.I;
/*    */   }
/*    */ 
/*    */   
/*    */   protected SoundEffect F() {
/* 30 */     super.F();
/* 31 */     return SoundEffects.ej;
/*    */   }
/*    */ 
/*    */   
/*    */   protected SoundEffect cf() {
/* 36 */     super.cf();
/* 37 */     return SoundEffects.el;
/*    */   }
/*    */ 
/*    */   
/*    */   protected SoundEffect d(DamageSource paramDamageSource) {
/* 42 */     super.d(paramDamageSource);
/* 43 */     return SoundEffects.em;
/*    */   }
/*    */ 
/*    */   
/*    */   protected void dp() {
/* 48 */     a(SoundEffects.ek, 1.0F, (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\EntityHorseMule.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */