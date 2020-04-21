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
/*    */ public class ItemElytra
/*    */   extends Item
/*    */ {
/*    */   public ItemElytra() {
/* 18 */     this.maxStackSize = 1;
/* 19 */     setMaxDurability(432);
/* 20 */     b(CreativeModeTab.e);
/*    */     
/* 22 */     a(new MinecraftKey("broken"), new IDynamicTexture(this)
/*    */         {
/*    */         
/*    */         });
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 31 */     BlockDispenser.REGISTRY.a(this, ItemArmor.b);
/*    */   }
/*    */   
/*    */   public static boolean d(ItemStack paramItemStack) {
/* 35 */     return (paramItemStack.i() < paramItemStack.k() - 1);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a(ItemStack paramItemStack1, ItemStack paramItemStack2) {
/* 40 */     return (paramItemStack2.getItem() == Items.LEATHER);
/*    */   }
/*    */ 
/*    */   
/*    */   public InteractionResultWrapper<ItemStack> a(World paramWorld, EntityHuman paramEntityHuman, EnumHand paramEnumHand) {
/* 45 */     ItemStack itemStack1 = paramEntityHuman.b(paramEnumHand);
/* 46 */     EnumItemSlot enumItemSlot = EntityInsentient.d(itemStack1);
/* 47 */     ItemStack itemStack2 = paramEntityHuman.getEquipment(enumItemSlot);
/*    */     
/* 49 */     if (itemStack2.isEmpty()) {
/* 50 */       paramEntityHuman.setSlot(enumItemSlot, itemStack1.cloneItemStack());
/* 51 */       itemStack1.setCount(0);
/* 52 */       return new InteractionResultWrapper<>(EnumInteractionResult.SUCCESS, itemStack1);
/*    */     } 
/*    */     
/* 55 */     return new InteractionResultWrapper<>(EnumInteractionResult.FAIL, itemStack1);
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\ItemElytra.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */