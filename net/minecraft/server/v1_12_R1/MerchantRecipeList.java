/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import javax.annotation.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MerchantRecipeList
/*     */   extends ArrayList<MerchantRecipe>
/*     */ {
/*     */   public MerchantRecipeList() {}
/*     */   
/*     */   public MerchantRecipeList(NBTTagCompound paramNBTTagCompound) {
/*  21 */     a(paramNBTTagCompound);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public MerchantRecipe a(ItemStack paramItemStack1, ItemStack paramItemStack2, int paramInt) {
/*  26 */     if (paramInt > 0 && paramInt < size()) {
/*     */       
/*  28 */       MerchantRecipe merchantRecipe = get(paramInt);
/*  29 */       if (a(paramItemStack1, merchantRecipe.getBuyItem1()) && ((paramItemStack2.isEmpty() && !merchantRecipe.hasSecondItem()) || (merchantRecipe.hasSecondItem() && a(paramItemStack2, merchantRecipe.getBuyItem2()))) && 
/*  30 */         paramItemStack1.getCount() >= merchantRecipe.getBuyItem1().getCount() && (!merchantRecipe.hasSecondItem() || paramItemStack2.getCount() >= merchantRecipe.getBuyItem2().getCount())) {
/*  31 */         return merchantRecipe;
/*     */       }
/*     */       
/*  34 */       return null;
/*     */     } 
/*  36 */     for (byte b = 0; b < size(); b++) {
/*  37 */       MerchantRecipe merchantRecipe = get(b);
/*  38 */       if (a(paramItemStack1, merchantRecipe.getBuyItem1()) && paramItemStack1.getCount() >= merchantRecipe.getBuyItem1().getCount() && ((!merchantRecipe.hasSecondItem() && paramItemStack2.isEmpty()) || (merchantRecipe.hasSecondItem() && a(paramItemStack2, merchantRecipe.getBuyItem2()) && paramItemStack2.getCount() >= merchantRecipe.getBuyItem2().getCount()))) {
/*  39 */         return merchantRecipe;
/*     */       }
/*     */     } 
/*  42 */     return null;
/*     */   }
/*     */   
/*     */   private boolean a(ItemStack paramItemStack1, ItemStack paramItemStack2) {
/*  46 */     return (ItemStack.c(paramItemStack1, paramItemStack2) && (!paramItemStack2.hasTag() || (paramItemStack1.hasTag() && GameProfileSerializer.a(paramItemStack2.getTag(), paramItemStack1.getTag(), false))));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(PacketDataSerializer paramPacketDataSerializer) {
/*  76 */     paramPacketDataSerializer.writeByte((byte)(size() & 0xFF));
/*  77 */     for (byte b = 0; b < size(); b++) {
/*  78 */       MerchantRecipe merchantRecipe = get(b);
/*  79 */       paramPacketDataSerializer.a(merchantRecipe.getBuyItem1());
/*  80 */       paramPacketDataSerializer.a(merchantRecipe.getBuyItem3());
/*     */       
/*  82 */       ItemStack itemStack = merchantRecipe.getBuyItem2();
/*  83 */       paramPacketDataSerializer.writeBoolean(!itemStack.isEmpty());
/*  84 */       if (!itemStack.isEmpty()) {
/*  85 */         paramPacketDataSerializer.a(itemStack);
/*     */       }
/*  87 */       paramPacketDataSerializer.writeBoolean(merchantRecipe.h());
/*  88 */       paramPacketDataSerializer.writeInt(merchantRecipe.e());
/*  89 */       paramPacketDataSerializer.writeInt(merchantRecipe.f());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(NBTTagCompound paramNBTTagCompound) {
/* 119 */     NBTTagList nBTTagList = paramNBTTagCompound.getList("Recipes", 10);
/*     */     
/* 121 */     for (byte b = 0; b < nBTTagList.size(); b++) {
/* 122 */       NBTTagCompound nBTTagCompound = nBTTagList.get(b);
/* 123 */       add(new MerchantRecipe(nBTTagCompound));
/*     */     } 
/*     */   }
/*     */   
/*     */   public NBTTagCompound a() {
/* 128 */     NBTTagCompound nBTTagCompound = new NBTTagCompound();
/*     */     
/* 130 */     NBTTagList nBTTagList = new NBTTagList();
/* 131 */     for (byte b = 0; b < size(); b++) {
/* 132 */       MerchantRecipe merchantRecipe = get(b);
/* 133 */       nBTTagList.add(merchantRecipe.k());
/*     */     } 
/* 135 */     nBTTagCompound.set("Recipes", nBTTagList);
/* 136 */     return nBTTagCompound;
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\MerchantRecipeList.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */