/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public enum EnumCreatureType
/*    */ {
/* 13 */   MONSTER((Class)IMonster.class, 70, Material.AIR, false, false),
/* 14 */   CREATURE((Class)EntityAnimal.class, 10, Material.AIR, true, true),
/* 15 */   AMBIENT((Class)EntityAmbient.class, 15, Material.AIR, true, false),
/* 16 */   WATER_CREATURE((Class)EntityWaterAnimal.class, 5, Material.WATER, true, false);
/*    */   
/*    */   private final Class<? extends IAnimal> e;
/*    */   
/*    */   private final int f;
/*    */   
/*    */   private final Material g;
/*    */   
/*    */   private final boolean h;
/*    */   
/*    */   private final boolean i;
/*    */   
/*    */   EnumCreatureType(Class<? extends IAnimal> paramClass, int paramInt1, Material paramMaterial, boolean paramBoolean1, boolean paramBoolean2) {
/* 29 */     this.e = paramClass;
/* 30 */     this.f = paramInt1;
/* 31 */     this.g = paramMaterial;
/* 32 */     this.h = paramBoolean1;
/* 33 */     this.i = paramBoolean2;
/*    */   }
/*    */   
/*    */   public Class<? extends IAnimal> a() {
/* 37 */     return this.e;
/*    */   }
/*    */   
/*    */   public int b() {
/* 41 */     return this.f;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean d() {
/* 49 */     return this.h;
/*    */   }
/*    */   
/*    */   public boolean e() {
/* 53 */     return this.i;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\EnumCreatureType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */