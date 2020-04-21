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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ItemEnchantedBook
/*    */   extends Item
/*    */ {
/*    */   public boolean g_(ItemStack paramItemStack) {
/* 24 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public EnumItemRarity g(ItemStack paramItemStack) {
/* 29 */     if (h(paramItemStack).isEmpty()) {
/* 30 */       return super.g(paramItemStack);
/*    */     }
/* 32 */     return EnumItemRarity.UNCOMMON;
/*    */   }
/*    */ 
/*    */   
/*    */   public static NBTTagList h(ItemStack paramItemStack) {
/* 37 */     NBTTagCompound nBTTagCompound = paramItemStack.getTag();
/* 38 */     if (nBTTagCompound != null) {
/* 39 */       return nBTTagCompound.getList("StoredEnchantments", 10);
/*    */     }
/*    */     
/* 42 */     return new NBTTagList();
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static void a(ItemStack paramItemStack, WeightedRandomEnchant paramWeightedRandomEnchant) {
/* 63 */     NBTTagList nBTTagList = h(paramItemStack);
/* 64 */     boolean bool = true;
/*    */     
/* 66 */     for (byte b = 0; b < nBTTagList.size(); b++) {
/* 67 */       NBTTagCompound nBTTagCompound = nBTTagList.get(b);
/*    */       
/* 69 */       if (Enchantment.c(nBTTagCompound.getShort("id")) == paramWeightedRandomEnchant.enchantment) {
/* 70 */         if (nBTTagCompound.getShort("lvl") < paramWeightedRandomEnchant.level) {
/* 71 */           nBTTagCompound.setShort("lvl", (short)paramWeightedRandomEnchant.level);
/*    */         }
/*    */         
/* 74 */         bool = false;
/*    */         
/*    */         break;
/*    */       } 
/*    */     } 
/* 79 */     if (bool) {
/* 80 */       NBTTagCompound nBTTagCompound = new NBTTagCompound();
/*    */       
/* 82 */       nBTTagCompound.setShort("id", (short)Enchantment.getId(paramWeightedRandomEnchant.enchantment));
/* 83 */       nBTTagCompound.setShort("lvl", (short)paramWeightedRandomEnchant.level);
/*    */       
/* 85 */       nBTTagList.add(nBTTagCompound);
/*    */     } 
/*    */     
/* 88 */     if (!paramItemStack.hasTag()) {
/* 89 */       paramItemStack.setTag(new NBTTagCompound());
/*    */     }
/* 91 */     paramItemStack.getTag().set("StoredEnchantments", nBTTagList);
/*    */   }
/*    */   
/*    */   public static ItemStack a(WeightedRandomEnchant paramWeightedRandomEnchant) {
/* 95 */     ItemStack itemStack = new ItemStack(Items.ENCHANTED_BOOK);
/* 96 */     a(itemStack, paramWeightedRandomEnchant);
/* 97 */     return itemStack;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\ItemEnchantedBook.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */