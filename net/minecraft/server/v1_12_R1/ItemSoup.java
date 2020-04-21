/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ 
/*    */ public class ItemSoup
/*    */   extends ItemFood
/*    */ {
/*    */   public ItemSoup(int paramInt) {
/*  8 */     super(paramInt, false);
/*    */     
/* 10 */     d(1);
/*    */   }
/*    */ 
/*    */   
/*    */   public ItemStack a(ItemStack paramItemStack, World paramWorld, EntityLiving paramEntityLiving) {
/* 15 */     super.a(paramItemStack, paramWorld, paramEntityLiving);
/*    */     
/* 17 */     return new ItemStack(Items.BOWL);
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\ItemSoup.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */