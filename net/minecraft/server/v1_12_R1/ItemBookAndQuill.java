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
/*    */ public class ItemBookAndQuill
/*    */   extends Item
/*    */ {
/*    */   public ItemBookAndQuill() {
/* 15 */     d(1);
/*    */   }
/*    */ 
/*    */   
/*    */   public InteractionResultWrapper<ItemStack> a(World paramWorld, EntityHuman paramEntityHuman, EnumHand paramEnumHand) {
/* 20 */     ItemStack itemStack = paramEntityHuman.b(paramEnumHand);
/* 21 */     paramEntityHuman.a(itemStack, paramEnumHand);
/* 22 */     paramEntityHuman.b(StatisticList.b(this));
/* 23 */     return new InteractionResultWrapper<>(EnumInteractionResult.SUCCESS, itemStack);
/*    */   }
/*    */   
/*    */   public static boolean b(NBTTagCompound paramNBTTagCompound) {
/* 27 */     if (paramNBTTagCompound == null) {
/* 28 */       return false;
/*    */     }
/* 30 */     if (!paramNBTTagCompound.hasKeyOfType("pages", 9)) {
/* 31 */       return false;
/*    */     }
/*    */     
/* 34 */     NBTTagList nBTTagList = paramNBTTagCompound.getList("pages", 8);
/* 35 */     for (byte b = 0; b < nBTTagList.size(); b++) {
/* 36 */       String str = nBTTagList.getString(b);
/*    */       
/* 38 */       if (str.length() > 32767) {
/* 39 */         return false;
/*    */       }
/*    */     } 
/*    */     
/* 43 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\ItemBookAndQuill.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */