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
/*    */ public class EntityCaveSpider
/*    */   extends EntitySpider
/*    */ {
/*    */   public EntityCaveSpider(World paramWorld) {
/* 21 */     super(paramWorld);
/* 22 */     setSize(0.7F, 0.5F);
/*    */   }
/*    */   
/*    */   public static void a(DataConverterManager paramDataConverterManager) {
/* 26 */     EntityInsentient.a(paramDataConverterManager, EntityCaveSpider.class);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void initAttributes() {
/* 31 */     super.initAttributes();
/*    */     
/* 33 */     getAttributeInstance(GenericAttributes.maxHealth).setValue(12.0D);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean B(Entity paramEntity) {
/* 38 */     if (super.B(paramEntity)) {
/* 39 */       if (paramEntity instanceof EntityLiving) {
/* 40 */         byte b = 0;
/* 41 */         if (this.world.getDifficulty() == EnumDifficulty.NORMAL) {
/* 42 */           b = 7;
/* 43 */         } else if (this.world.getDifficulty() == EnumDifficulty.HARD) {
/* 44 */           b = 15;
/*    */         } 
/*    */         
/* 47 */         if (b > 0) {
/* 48 */           ((EntityLiving)paramEntity).addEffect(new MobEffect(MobEffects.POISON, b * 20, 0));
/*    */         }
/*    */       } 
/*    */       
/* 52 */       return true;
/*    */     } 
/* 54 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public GroupDataEntity prepare(DifficultyDamageScaler paramDifficultyDamageScaler, @Nullable GroupDataEntity paramGroupDataEntity) {
/* 61 */     return paramGroupDataEntity;
/*    */   }
/*    */ 
/*    */   
/*    */   public float getHeadHeight() {
/* 66 */     return 0.45F;
/*    */   }
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   protected MinecraftKey J() {
/* 72 */     return LootTables.t;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\EntityCaveSpider.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */