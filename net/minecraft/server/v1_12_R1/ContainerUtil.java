/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ContainerUtil
/*    */ {
/*    */   public static ItemStack a(List<ItemStack> paramList, int paramInt1, int paramInt2) {
/* 13 */     if (paramInt1 < 0 || paramInt1 >= paramList.size() || ((ItemStack)paramList.get(paramInt1)).isEmpty() || paramInt2 <= 0) {
/* 14 */       return ItemStack.a;
/*    */     }
/*    */     
/* 17 */     return ((ItemStack)paramList.get(paramInt1)).cloneAndSubtract(paramInt2);
/*    */   }
/*    */   
/*    */   public static ItemStack a(List<ItemStack> paramList, int paramInt) {
/* 21 */     if (paramInt < 0 || paramInt >= paramList.size()) {
/* 22 */       return ItemStack.a;
/*    */     }
/*    */     
/* 25 */     return paramList.set(paramInt, ItemStack.a);
/*    */   }
/*    */   
/*    */   public static NBTTagCompound a(NBTTagCompound paramNBTTagCompound, NonNullList<ItemStack> paramNonNullList) {
/* 29 */     return a(paramNBTTagCompound, paramNonNullList, true);
/*    */   }
/*    */   
/*    */   public static NBTTagCompound a(NBTTagCompound paramNBTTagCompound, NonNullList<ItemStack> paramNonNullList, boolean paramBoolean) {
/* 33 */     NBTTagList nBTTagList = new NBTTagList();
/* 34 */     for (byte b = 0; b < paramNonNullList.size(); b++) {
/* 35 */       ItemStack itemStack = paramNonNullList.get(b);
/* 36 */       if (!itemStack.isEmpty()) {
/* 37 */         NBTTagCompound nBTTagCompound = new NBTTagCompound();
/* 38 */         nBTTagCompound.setByte("Slot", (byte)b);
/* 39 */         itemStack.save(nBTTagCompound);
/* 40 */         nBTTagList.add(nBTTagCompound);
/*    */       } 
/*    */     } 
/* 43 */     if (!nBTTagList.isEmpty() || paramBoolean) {
/* 44 */       paramNBTTagCompound.set("Items", nBTTagList);
/*    */     }
/* 46 */     return paramNBTTagCompound;
/*    */   }
/*    */   
/*    */   public static void b(NBTTagCompound paramNBTTagCompound, NonNullList<ItemStack> paramNonNullList) {
/* 50 */     NBTTagList nBTTagList = paramNBTTagCompound.getList("Items", 10);
/* 51 */     for (byte b = 0; b < nBTTagList.size(); b++) {
/* 52 */       NBTTagCompound nBTTagCompound = nBTTagList.get(b);
/* 53 */       int i = nBTTagCompound.getByte("Slot") & 0xFF;
/* 54 */       if (i >= 0 && i < paramNonNullList.size())
/* 55 */         paramNonNullList.set(i, new ItemStack(nBTTagCompound)); 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\ContainerUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */