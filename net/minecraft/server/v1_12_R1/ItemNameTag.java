/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ItemNameTag
/*    */   extends Item
/*    */ {
/*    */   public ItemNameTag() {
/* 10 */     b(CreativeModeTab.i);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a(ItemStack paramItemStack, EntityHuman paramEntityHuman, EntityLiving paramEntityLiving, EnumHand paramEnumHand) {
/* 15 */     if (!paramItemStack.hasName() || paramEntityLiving instanceof EntityHuman) {
/* 16 */       return false;
/*    */     }
/*    */     
/* 19 */     paramEntityLiving.setCustomName(paramItemStack.getName());
/* 20 */     if (paramEntityLiving instanceof EntityInsentient) {
/* 21 */       ((EntityInsentient)paramEntityLiving).cW();
/*    */     }
/*    */     
/* 24 */     paramItemStack.subtract(1);
/* 25 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\ItemNameTag.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */