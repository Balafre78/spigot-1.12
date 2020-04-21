/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ public class ItemSnowball
/*    */   extends Item {
/*    */   public ItemSnowball() {
/*  6 */     this.maxStackSize = 16;
/*  7 */     b(CreativeModeTab.f);
/*    */   }
/*    */   
/*    */   public InteractionResultWrapper<ItemStack> a(World world, EntityHuman entityhuman, EnumHand enumhand) {
/* 11 */     ItemStack itemstack = entityhuman.b(enumhand);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 21 */     if (!world.isClientSide) {
/* 22 */       EntitySnowball entitysnowball = new EntitySnowball(world, entityhuman);
/*    */       
/* 24 */       entitysnowball.a(entityhuman, entityhuman.pitch, entityhuman.yaw, 0.0F, 1.5F, 1.0F);
/* 25 */       if (world.addEntity(entitysnowball)) {
/* 26 */         if (!entityhuman.abilities.canInstantlyBuild) {
/* 27 */           itemstack.subtract(1);
/*    */         }
/*    */         
/* 30 */         world.a((EntityHuman)null, entityhuman.locX, entityhuman.locY, entityhuman.locZ, SoundEffects.hp, SoundCategory.NEUTRAL, 0.5F, 0.4F / (j.nextFloat() * 0.4F + 0.8F));
/* 31 */       } else if (entityhuman instanceof EntityPlayer) {
/* 32 */         ((EntityPlayer)entityhuman).getBukkitEntity().updateInventory();
/*    */       } 
/*    */     } 
/*    */ 
/*    */     
/* 37 */     entityhuman.b(StatisticList.b(this));
/* 38 */     return new InteractionResultWrapper<>(EnumInteractionResult.SUCCESS, itemstack);
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\ItemSnowball.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */