/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ItemCoal
/*    */   extends Item
/*    */ {
/*    */   public ItemCoal() {
/* 10 */     a(true);
/* 11 */     setMaxDurability(0);
/* 12 */     b(CreativeModeTab.l);
/*    */   }
/*    */ 
/*    */   
/*    */   public String a(ItemStack paramItemStack) {
/* 17 */     if (paramItemStack.getData() == 1) {
/* 18 */       return "item.charcoal";
/*    */     }
/* 20 */     return "item.coal";
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\ItemCoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */