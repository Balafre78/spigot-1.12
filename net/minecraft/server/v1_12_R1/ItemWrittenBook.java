/*     */ package net.minecraft.server.v1_12_R1;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ItemWrittenBook
/*     */   extends Item
/*     */ {
/*     */   public ItemWrittenBook() {
/*  40 */     d(1);
/*     */   }
/*     */   
/*     */   public static boolean b(NBTTagCompound paramNBTTagCompound) {
/*  44 */     if (!ItemBookAndQuill.b(paramNBTTagCompound)) {
/*  45 */       return false;
/*     */     }
/*     */     
/*  48 */     if (!paramNBTTagCompound.hasKeyOfType("title", 8)) {
/*  49 */       return false;
/*     */     }
/*  51 */     String str = paramNBTTagCompound.getString("title");
/*  52 */     if (str == null || str.length() > 32) {
/*  53 */       return false;
/*     */     }
/*     */     
/*  56 */     return paramNBTTagCompound.hasKeyOfType("author", 8);
/*     */   }
/*     */   
/*     */   public static int h(ItemStack paramItemStack) {
/*  60 */     return paramItemStack.getTag().getInt("generation");
/*     */   }
/*     */ 
/*     */   
/*     */   public String b(ItemStack paramItemStack) {
/*  65 */     if (paramItemStack.hasTag()) {
/*  66 */       NBTTagCompound nBTTagCompound = paramItemStack.getTag();
/*     */       
/*  68 */       String str = nBTTagCompound.getString("title");
/*  69 */       if (!UtilColor.b(str)) {
/*  70 */         return str;
/*     */       }
/*     */     } 
/*  73 */     return super.b(paramItemStack);
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
/*     */   public InteractionResultWrapper<ItemStack> a(World paramWorld, EntityHuman paramEntityHuman, EnumHand paramEnumHand) {
/*  92 */     ItemStack itemStack = paramEntityHuman.b(paramEnumHand);
/*  93 */     if (!paramWorld.isClientSide) {
/*  94 */       a(itemStack, paramEntityHuman);
/*     */     }
/*  96 */     paramEntityHuman.a(itemStack, paramEnumHand);
/*  97 */     paramEntityHuman.b(StatisticList.b(this));
/*  98 */     return new InteractionResultWrapper<>(EnumInteractionResult.SUCCESS, itemStack);
/*     */   }
/*     */   
/*     */   private void a(ItemStack paramItemStack, EntityHuman paramEntityHuman) {
/* 102 */     if (paramItemStack.getTag() == null) {
/*     */       return;
/*     */     }
/* 105 */     NBTTagCompound nBTTagCompound = paramItemStack.getTag();
/* 106 */     if (nBTTagCompound.getBoolean("resolved")) {
/*     */       return;
/*     */     }
/* 109 */     nBTTagCompound.setBoolean("resolved", true);
/* 110 */     if (!b(nBTTagCompound)) {
/*     */       return;
/*     */     }
/* 113 */     NBTTagList nBTTagList = nBTTagCompound.getList("pages", 8);
/* 114 */     for (byte b = 0; b < nBTTagList.size(); b++) {
/* 115 */       IChatBaseComponent iChatBaseComponent; String str = nBTTagList.getString(b);
/*     */       
/*     */       try {
/* 118 */         iChatBaseComponent = IChatBaseComponent.ChatSerializer.b(str);
/* 119 */         iChatBaseComponent = ChatComponentUtils.filterForDisplay(paramEntityHuman, iChatBaseComponent, paramEntityHuman);
/* 120 */       } catch (Exception exception) {
/* 121 */         iChatBaseComponent = new ChatComponentText(str);
/*     */       } 
/* 123 */       nBTTagList.a(b, new NBTTagString(IChatBaseComponent.ChatSerializer.a(iChatBaseComponent)));
/*     */     } 
/* 125 */     nBTTagCompound.set("pages", nBTTagList);
/* 126 */     if (paramEntityHuman instanceof EntityPlayer && paramEntityHuman.getItemInMainHand() == paramItemStack) {
/* 127 */       Slot slot = paramEntityHuman.activeContainer.getSlot(paramEntityHuman.inventory, paramEntityHuman.inventory.itemInHandIndex);
/* 128 */       ((EntityPlayer)paramEntityHuman).playerConnection.sendPacket(new PacketPlayOutSetSlot(0, slot.rawSlotIndex, paramItemStack));
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\ItemWrittenBook.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */