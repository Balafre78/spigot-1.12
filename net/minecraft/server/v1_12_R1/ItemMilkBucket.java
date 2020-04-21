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
/*    */ 
/*    */ 
/*    */ public class ItemMilkBucket
/*    */   extends Item
/*    */ {
/*    */   public ItemMilkBucket() {
/* 17 */     d(1);
/* 18 */     b(CreativeModeTab.f);
/*    */   }
/*    */ 
/*    */   
/*    */   public ItemStack a(ItemStack paramItemStack, World paramWorld, EntityLiving paramEntityLiving) {
/* 23 */     if (paramEntityLiving instanceof EntityPlayer) {
/* 24 */       EntityPlayer entityPlayer = (EntityPlayer)paramEntityLiving;
/* 25 */       CriterionTriggers.y.a(entityPlayer, paramItemStack);
/* 26 */       entityPlayer.b(StatisticList.b(this));
/*    */     } 
/*    */     
/* 29 */     if (paramEntityLiving instanceof EntityHuman && !((EntityHuman)paramEntityLiving).abilities.canInstantlyBuild) {
/* 30 */       paramItemStack.subtract(1);
/*    */     }
/*    */     
/* 33 */     if (!paramWorld.isClientSide) {
/* 34 */       paramEntityLiving.removeAllEffects();
/*    */     }
/*    */     
/* 37 */     if (paramItemStack.isEmpty()) {
/* 38 */       return new ItemStack(Items.BUCKET);
/*    */     }
/* 40 */     return paramItemStack;
/*    */   }
/*    */ 
/*    */   
/*    */   public int e(ItemStack paramItemStack) {
/* 45 */     return 32;
/*    */   }
/*    */ 
/*    */   
/*    */   public EnumAnimation f(ItemStack paramItemStack) {
/* 50 */     return EnumAnimation.DRINK;
/*    */   }
/*    */ 
/*    */   
/*    */   public InteractionResultWrapper<ItemStack> a(World paramWorld, EntityHuman paramEntityHuman, EnumHand paramEnumHand) {
/* 55 */     paramEntityHuman.c(paramEnumHand);
/* 56 */     return new InteractionResultWrapper<>(EnumInteractionResult.SUCCESS, paramEntityHuman.b(paramEnumHand));
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\ItemMilkBucket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */