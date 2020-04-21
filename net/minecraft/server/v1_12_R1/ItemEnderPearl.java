/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ public class ItemEnderPearl
/*    */   extends Item {
/*    */   public ItemEnderPearl() {
/*  6 */     this.maxStackSize = 16;
/*  7 */     b(CreativeModeTab.f);
/*    */   }
/*    */   
/*    */   public InteractionResultWrapper<ItemStack> a(World world, EntityHuman entityhuman, EnumHand enumhand) {
/* 11 */     ItemStack itemstack = entityhuman.b(enumhand);
/*    */ 
/*    */     
/* 14 */     if (!world.isClientSide) {
/* 15 */       EntityEnderPearl entityenderpearl = new EntityEnderPearl(world, entityhuman);
/*    */       
/* 17 */       entityenderpearl.a(entityhuman, entityhuman.pitch, entityhuman.yaw, 0.0F, 1.5F, 1.0F);
/* 18 */       if (!world.addEntity(entityenderpearl)) {
/* 19 */         if (entityhuman instanceof EntityPlayer) {
/* 20 */           ((EntityPlayer)entityhuman).getBukkitEntity().updateInventory();
/*    */         }
/* 22 */         return new InteractionResultWrapper<>(EnumInteractionResult.FAIL, itemstack);
/*    */       } 
/*    */     } 
/*    */     
/* 26 */     if (!entityhuman.abilities.canInstantlyBuild) {
/* 27 */       itemstack.subtract(1);
/*    */     }
/*    */     
/* 30 */     world.a((EntityHuman)null, entityhuman.locX, entityhuman.locY, entityhuman.locZ, SoundEffects.bn, SoundCategory.NEUTRAL, 0.5F, 0.4F / (j.nextFloat() * 0.4F + 0.8F));
/* 31 */     entityhuman.getCooldownTracker().a(this, 20);
/*    */ 
/*    */     
/* 34 */     entityhuman.b(StatisticList.b(this));
/* 35 */     return new InteractionResultWrapper<>(EnumInteractionResult.SUCCESS, itemstack);
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\ItemEnderPearl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */