/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ItemCarrotStick
/*    */   extends Item
/*    */ {
/*    */   public ItemCarrotStick() {
/* 13 */     b(CreativeModeTab.e);
/* 14 */     d(1);
/* 15 */     setMaxDurability(25);
/*    */   }
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
/*    */   public InteractionResultWrapper<ItemStack> a(World paramWorld, EntityHuman paramEntityHuman, EnumHand paramEnumHand) {
/* 30 */     ItemStack itemStack = paramEntityHuman.b(paramEnumHand);
/* 31 */     if (paramWorld.isClientSide) {
/* 32 */       return new InteractionResultWrapper<>(EnumInteractionResult.PASS, itemStack);
/*    */     }
/*    */     
/* 35 */     if (paramEntityHuman.isPassenger() && paramEntityHuman.bJ() instanceof EntityPig) {
/* 36 */       EntityPig entityPig = (EntityPig)paramEntityHuman.bJ();
/*    */       
/* 38 */       if (itemStack.k() - itemStack.getData() >= 7 && entityPig.dm()) {
/* 39 */         itemStack.damage(7, paramEntityHuman);
/* 40 */         if (itemStack.isEmpty()) {
/* 41 */           ItemStack itemStack1 = new ItemStack(Items.FISHING_ROD);
/* 42 */           itemStack1.setTag(itemStack.getTag());
/* 43 */           return new InteractionResultWrapper<>(EnumInteractionResult.SUCCESS, itemStack1);
/*    */         } 
/* 45 */         return new InteractionResultWrapper<>(EnumInteractionResult.SUCCESS, itemStack);
/*    */       } 
/*    */     } 
/* 48 */     paramEntityHuman.b(StatisticList.b(this));
/*    */     
/* 50 */     return new InteractionResultWrapper<>(EnumInteractionResult.PASS, itemStack);
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\ItemCarrotStick.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */