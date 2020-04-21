/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ public class EntitySkeletonWither
/*    */   extends EntitySkeletonAbstract {
/*    */   public EntitySkeletonWither(World world) {
/*  8 */     super(world);
/*  9 */     setSize(0.7F, 2.4F);
/* 10 */     this.fireProof = true;
/*    */   }
/*    */   
/*    */   public static void a(DataConverterManager dataconvertermanager) {
/* 14 */     EntityInsentient.a(dataconvertermanager, EntitySkeletonWither.class);
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   protected MinecraftKey J() {
/* 19 */     return LootTables.ap;
/*    */   }
/*    */   
/*    */   protected SoundEffect F() {
/* 23 */     return SoundEffects.iH;
/*    */   }
/*    */   
/*    */   protected SoundEffect d(DamageSource damagesource) {
/* 27 */     return SoundEffects.iJ;
/*    */   }
/*    */   
/*    */   protected SoundEffect cf() {
/* 31 */     return SoundEffects.iI;
/*    */   }
/*    */   
/*    */   SoundEffect p() {
/* 35 */     return SoundEffects.iK;
/*    */   }
/*    */ 
/*    */   
/*    */   public void die(DamageSource damagesource) {
/* 40 */     if (damagesource.getEntity() instanceof EntityCreeper) {
/* 41 */       EntityCreeper entitycreeper = (EntityCreeper)damagesource.getEntity();
/*    */       
/* 43 */       if (entitycreeper.isPowered() && entitycreeper.canCauseHeadDrop()) {
/* 44 */         entitycreeper.setCausedHeadDrop();
/* 45 */         a(new ItemStack(Items.SKULL, 1, 1), 0.0F);
/*    */       } 
/*    */     } 
/* 48 */     super.die(damagesource);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void a(DifficultyDamageScaler difficultydamagescaler) {
/* 53 */     setSlot(EnumItemSlot.MAINHAND, new ItemStack(Items.STONE_SWORD));
/*    */   }
/*    */   
/*    */   protected void b(DifficultyDamageScaler difficultydamagescaler) {}
/*    */   
/*    */   @Nullable
/*    */   public GroupDataEntity prepare(DifficultyDamageScaler difficultydamagescaler, @Nullable GroupDataEntity groupdataentity) {
/* 60 */     GroupDataEntity groupdataentity1 = super.prepare(difficultydamagescaler, groupdataentity);
/*    */     
/* 62 */     getAttributeInstance(GenericAttributes.ATTACK_DAMAGE).setValue(4.0D);
/* 63 */     dm();
/* 64 */     return groupdataentity1;
/*    */   }
/*    */   
/*    */   public float getHeadHeight() {
/* 68 */     return 2.1F;
/*    */   }
/*    */   
/*    */   public boolean B(Entity entity) {
/* 72 */     if (!super.B(entity)) {
/* 73 */       return false;
/*    */     }
/* 75 */     if (entity instanceof EntityLiving) {
/* 76 */       ((EntityLiving)entity).addEffect(new MobEffect(MobEffects.WITHER, 200));
/*    */     }
/*    */     
/* 79 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   protected EntityArrow a(float f) {
/* 84 */     EntityArrow entityarrow = super.a(f);
/*    */     
/* 86 */     entityarrow.setOnFire(100);
/* 87 */     return entityarrow;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\EntitySkeletonWither.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */