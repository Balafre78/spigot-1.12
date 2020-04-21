/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ItemMapEmpty
/*    */   extends ItemWorldMapBase
/*    */ {
/*    */   protected ItemMapEmpty() {
/* 12 */     b(CreativeModeTab.f);
/*    */   }
/*    */ 
/*    */   
/*    */   public InteractionResultWrapper<ItemStack> a(World paramWorld, EntityHuman paramEntityHuman, EnumHand paramEnumHand) {
/* 17 */     ItemStack itemStack1 = ItemWorldMap.a(paramWorld, paramEntityHuman.locX, paramEntityHuman.locZ, (byte)0, true, false);
/*    */     
/* 19 */     ItemStack itemStack2 = paramEntityHuman.b(paramEnumHand);
/* 20 */     itemStack2.subtract(1);
/* 21 */     if (itemStack2.isEmpty()) {
/* 22 */       return new InteractionResultWrapper<>(EnumInteractionResult.SUCCESS, itemStack1);
/*    */     }
/* 24 */     if (!paramEntityHuman.inventory.pickup(itemStack1.cloneItemStack())) {
/* 25 */       paramEntityHuman.drop(itemStack1, false);
/*    */     }
/* 27 */     paramEntityHuman.b(StatisticList.b(this));
/*    */     
/* 29 */     return new InteractionResultWrapper<>(EnumInteractionResult.SUCCESS, itemStack2);
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\ItemMapEmpty.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */