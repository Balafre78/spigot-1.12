/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ public class ItemCloth
/*    */   extends ItemBlock
/*    */ {
/*    */   public ItemCloth(Block paramBlock) {
/*  7 */     super(paramBlock);
/*    */     
/*  9 */     setMaxDurability(0);
/* 10 */     a(true);
/*    */   }
/*    */ 
/*    */   
/*    */   public int filterData(int paramInt) {
/* 15 */     return paramInt;
/*    */   }
/*    */ 
/*    */   
/*    */   public String a(ItemStack paramItemStack) {
/* 20 */     return getName() + "." + EnumColor.fromColorIndex(paramItemStack.getData()).d();
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\ItemCloth.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */