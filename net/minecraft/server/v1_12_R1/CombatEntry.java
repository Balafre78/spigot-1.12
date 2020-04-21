/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CombatEntry
/*    */ {
/*    */   private final DamageSource a;
/*    */   private final int b;
/*    */   private final float c;
/*    */   private final float d;
/*    */   private final String e;
/*    */   private final float f;
/*    */   
/*    */   public CombatEntry(DamageSource paramDamageSource, int paramInt, float paramFloat1, float paramFloat2, String paramString, float paramFloat3) {
/* 18 */     this.a = paramDamageSource;
/* 19 */     this.b = paramInt;
/* 20 */     this.c = paramFloat2;
/* 21 */     this.d = paramFloat1;
/* 22 */     this.e = paramString;
/* 23 */     this.f = paramFloat3;
/*    */   }
/*    */   
/*    */   public DamageSource a() {
/* 27 */     return this.a;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public float c() {
/* 35 */     return this.c;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean f() {
/* 47 */     return this.a.getEntity() instanceof EntityLiving;
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   public String g() {
/* 52 */     return this.e;
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   public IChatBaseComponent h() {
/* 57 */     return (a().getEntity() == null) ? null : a().getEntity().getScoreboardDisplayName();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public float j() {
/* 66 */     if (this.a == DamageSource.OUT_OF_WORLD) {
/* 67 */       return Float.MAX_VALUE;
/*    */     }
/* 69 */     return this.f;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\CombatEntry.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */