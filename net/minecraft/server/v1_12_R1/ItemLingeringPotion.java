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
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ItemLingeringPotion
/*    */   extends ItemPotion
/*    */ {
/*    */   public String b(ItemStack paramItemStack) {
/* 23 */     return LocaleI18n.get(PotionUtil.d(paramItemStack).b("lingering_potion.effect."));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public InteractionResultWrapper<ItemStack> a(World paramWorld, EntityHuman paramEntityHuman, EnumHand paramEnumHand) {
/* 33 */     ItemStack itemStack1 = paramEntityHuman.b(paramEnumHand);
/* 34 */     ItemStack itemStack2 = paramEntityHuman.abilities.canInstantlyBuild ? itemStack1.cloneItemStack() : itemStack1.cloneAndSubtract(1);
/*    */     
/* 36 */     paramWorld.a((EntityHuman)null, paramEntityHuman.locX, paramEntityHuman.locY, paramEntityHuman.locZ, SoundEffects.dL, SoundCategory.NEUTRAL, 0.5F, 0.4F / (j.nextFloat() * 0.4F + 0.8F));
/* 37 */     if (!paramWorld.isClientSide) {
/* 38 */       EntityPotion entityPotion = new EntityPotion(paramWorld, paramEntityHuman, itemStack2);
/* 39 */       entityPotion.a(paramEntityHuman, paramEntityHuman.pitch, paramEntityHuman.yaw, -20.0F, 0.5F, 1.0F);
/* 40 */       paramWorld.addEntity(entityPotion);
/*    */     } 
/* 42 */     paramEntityHuman.b(StatisticList.b(this));
/* 43 */     return new InteractionResultWrapper<>(EnumInteractionResult.SUCCESS, itemStack1);
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\ItemLingeringPotion.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */