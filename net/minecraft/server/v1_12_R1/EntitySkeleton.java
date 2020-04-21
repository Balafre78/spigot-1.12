/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ public class EntitySkeleton
/*    */   extends EntitySkeletonAbstract {
/*    */   public EntitySkeleton(World world) {
/*  8 */     super(world);
/*    */   }
/*    */   
/*    */   public static void a(DataConverterManager dataconvertermanager) {
/* 12 */     EntityInsentient.a(dataconvertermanager, EntitySkeleton.class);
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   protected MinecraftKey J() {
/* 17 */     return LootTables.ao;
/*    */   }
/*    */   
/*    */   protected SoundEffect F() {
/* 21 */     return SoundEffects.gQ;
/*    */   }
/*    */   
/*    */   protected SoundEffect d(DamageSource damagesource) {
/* 25 */     return SoundEffects.gV;
/*    */   }
/*    */   
/*    */   protected SoundEffect cf() {
/* 29 */     return SoundEffects.gR;
/*    */   }
/*    */   
/*    */   SoundEffect p() {
/* 33 */     return SoundEffects.gX;
/*    */   }
/*    */ 
/*    */   
/*    */   public void die(DamageSource damagesource) {
/* 38 */     if (damagesource.getEntity() instanceof EntityCreeper) {
/* 39 */       EntityCreeper entitycreeper = (EntityCreeper)damagesource.getEntity();
/*    */       
/* 41 */       if (entitycreeper.isPowered() && entitycreeper.canCauseHeadDrop()) {
/* 42 */         entitycreeper.setCausedHeadDrop();
/* 43 */         a(new ItemStack(Items.SKULL, 1, 0), 0.0F);
/*    */       } 
/*    */     } 
/* 46 */     super.die(damagesource);
/*    */   }
/*    */ 
/*    */   
/*    */   protected EntityArrow a(float f) {
/* 51 */     ItemStack itemstack = getEquipment(EnumItemSlot.OFFHAND);
/*    */     
/* 53 */     if (itemstack.getItem() == Items.SPECTRAL_ARROW) {
/* 54 */       EntitySpectralArrow entityspectralarrow = new EntitySpectralArrow(this.world, this);
/*    */       
/* 56 */       entityspectralarrow.a(this, f);
/* 57 */       return entityspectralarrow;
/*    */     } 
/* 59 */     EntityArrow entityarrow = super.a(f);
/*    */     
/* 61 */     if (itemstack.getItem() == Items.TIPPED_ARROW && entityarrow instanceof EntityTippedArrow) {
/* 62 */       ((EntityTippedArrow)entityarrow).a(itemstack);
/*    */     }
/*    */     
/* 65 */     return entityarrow;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\EntitySkeleton.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */