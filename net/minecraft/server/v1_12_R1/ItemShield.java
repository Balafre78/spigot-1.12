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
/*    */ 
/*    */ public class ItemShield
/*    */   extends Item
/*    */ {
/*    */   public ItemShield() {
/* 25 */     this.maxStackSize = 1;
/* 26 */     b(CreativeModeTab.j);
/* 27 */     setMaxDurability(336);
/*    */     
/* 29 */     a(new MinecraftKey("blocking"), new IDynamicTexture(this)
/*    */         {
/*    */         
/*    */         });
/*    */ 
/*    */     
/* 35 */     BlockDispenser.REGISTRY.a(this, ItemArmor.b);
/*    */   }
/*    */ 
/*    */   
/*    */   public String b(ItemStack paramItemStack) {
/* 40 */     if (paramItemStack.d("BlockEntityTag") != null) {
/* 41 */       EnumColor enumColor = TileEntityBanner.d(paramItemStack);
/*    */       
/* 43 */       return LocaleI18n.get("item.shield." + enumColor.d() + ".name");
/*    */     } 
/* 45 */     return LocaleI18n.get("item.shield.name");
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public EnumAnimation f(ItemStack paramItemStack) {
/* 55 */     return EnumAnimation.BLOCK;
/*    */   }
/*    */ 
/*    */   
/*    */   public int e(ItemStack paramItemStack) {
/* 60 */     return 72000;
/*    */   }
/*    */ 
/*    */   
/*    */   public InteractionResultWrapper<ItemStack> a(World paramWorld, EntityHuman paramEntityHuman, EnumHand paramEnumHand) {
/* 65 */     ItemStack itemStack = paramEntityHuman.b(paramEnumHand);
/* 66 */     paramEntityHuman.c(paramEnumHand);
/* 67 */     return new InteractionResultWrapper<>(EnumInteractionResult.SUCCESS, itemStack);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a(ItemStack paramItemStack1, ItemStack paramItemStack2) {
/* 72 */     if (paramItemStack2.getItem() == Item.getItemOf(Blocks.PLANKS)) {
/* 73 */       return true;
/*    */     }
/* 75 */     return super.a(paramItemStack1, paramItemStack2);
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\ItemShield.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */