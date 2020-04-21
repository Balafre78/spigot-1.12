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
/*    */ public class ItemExpBottle
/*    */   extends Item
/*    */ {
/*    */   public ItemExpBottle() {
/* 15 */     b(CreativeModeTab.f);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public InteractionResultWrapper<ItemStack> a(World paramWorld, EntityHuman paramEntityHuman, EnumHand paramEnumHand) {
/* 25 */     ItemStack itemStack = paramEntityHuman.b(paramEnumHand);
/* 26 */     if (!paramEntityHuman.abilities.canInstantlyBuild) {
/* 27 */       itemStack.subtract(1);
/*    */     }
/* 29 */     paramWorld.a((EntityHuman)null, paramEntityHuman.locX, paramEntityHuman.locY, paramEntityHuman.locZ, SoundEffects.bz, SoundCategory.NEUTRAL, 0.5F, 0.4F / (j.nextFloat() * 0.4F + 0.8F));
/* 30 */     if (!paramWorld.isClientSide) {
/* 31 */       EntityThrownExpBottle entityThrownExpBottle = new EntityThrownExpBottle(paramWorld, paramEntityHuman);
/* 32 */       entityThrownExpBottle.a(paramEntityHuman, paramEntityHuman.pitch, paramEntityHuman.yaw, -20.0F, 0.7F, 1.0F);
/* 33 */       paramWorld.addEntity(entityThrownExpBottle);
/*    */     } 
/* 35 */     paramEntityHuman.b(StatisticList.b(this));
/* 36 */     return new InteractionResultWrapper<>(EnumInteractionResult.SUCCESS, itemStack);
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\ItemExpBottle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */