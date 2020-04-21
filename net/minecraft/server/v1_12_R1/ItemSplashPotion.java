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
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ItemSplashPotion
/*    */   extends ItemPotion
/*    */ {
/*    */   public String b(ItemStack paramItemStack) {
/* 20 */     return LocaleI18n.get(PotionUtil.d(paramItemStack).b("splash_potion.effect."));
/*    */   }
/*    */ 
/*    */   
/*    */   public InteractionResultWrapper<ItemStack> a(World paramWorld, EntityHuman paramEntityHuman, EnumHand paramEnumHand) {
/* 25 */     ItemStack itemStack1 = paramEntityHuman.b(paramEnumHand);
/* 26 */     ItemStack itemStack2 = paramEntityHuman.abilities.canInstantlyBuild ? itemStack1.cloneItemStack() : itemStack1.cloneAndSubtract(1);
/*    */     
/* 28 */     paramWorld.a((EntityHuman)null, paramEntityHuman.locX, paramEntityHuman.locY, paramEntityHuman.locZ, SoundEffects.hE, SoundCategory.PLAYERS, 0.5F, 0.4F / (j.nextFloat() * 0.4F + 0.8F));
/* 29 */     if (!paramWorld.isClientSide) {
/* 30 */       EntityPotion entityPotion = new EntityPotion(paramWorld, paramEntityHuman, itemStack2);
/* 31 */       entityPotion.a(paramEntityHuman, paramEntityHuman.pitch, paramEntityHuman.yaw, -20.0F, 0.5F, 1.0F);
/* 32 */       paramWorld.addEntity(entityPotion);
/*    */     } 
/* 34 */     paramEntityHuman.b(StatisticList.b(this));
/* 35 */     return new InteractionResultWrapper<>(EnumInteractionResult.SUCCESS, itemStack1);
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\ItemSplashPotion.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */