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
/*    */ public class ItemEgg
/*    */   extends Item
/*    */ {
/*    */   public ItemEgg() {
/* 15 */     this.maxStackSize = 16;
/* 16 */     b(CreativeModeTab.l);
/*    */   }
/*    */ 
/*    */   
/*    */   public InteractionResultWrapper<ItemStack> a(World paramWorld, EntityHuman paramEntityHuman, EnumHand paramEnumHand) {
/* 21 */     ItemStack itemStack = paramEntityHuman.b(paramEnumHand);
/* 22 */     if (!paramEntityHuman.abilities.canInstantlyBuild) {
/* 23 */       itemStack.subtract(1);
/*    */     }
/* 25 */     paramWorld.a((EntityHuman)null, paramEntityHuman.locX, paramEntityHuman.locY, paramEntityHuman.locZ, SoundEffects.aH, SoundCategory.PLAYERS, 0.5F, 0.4F / (j.nextFloat() * 0.4F + 0.8F));
/* 26 */     if (!paramWorld.isClientSide) {
/* 27 */       EntityEgg entityEgg = new EntityEgg(paramWorld, paramEntityHuman);
/* 28 */       entityEgg.a(paramEntityHuman, paramEntityHuman.pitch, paramEntityHuman.yaw, 0.0F, 1.5F, 1.0F);
/* 29 */       paramWorld.addEntity(entityEgg);
/*    */     } 
/* 31 */     paramEntityHuman.b(StatisticList.b(this));
/* 32 */     return new InteractionResultWrapper<>(EnumInteractionResult.SUCCESS, itemStack);
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\ItemEgg.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */