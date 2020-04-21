/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import com.google.common.collect.Multimap;
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
/*    */ public class ItemHoe
/*    */   extends Item
/*    */ {
/*    */   private final float b;
/*    */   protected Item.EnumToolMaterial a;
/*    */   
/*    */   public ItemHoe(Item.EnumToolMaterial paramEnumToolMaterial) {
/* 27 */     this.a = paramEnumToolMaterial;
/* 28 */     this.maxStackSize = 1;
/* 29 */     setMaxDurability(paramEnumToolMaterial.a());
/* 30 */     b(CreativeModeTab.i);
/* 31 */     this.b = paramEnumToolMaterial.c() + 1.0F;
/*    */   }
/*    */ 
/*    */   
/*    */   public EnumInteractionResult a(EntityHuman paramEntityHuman, World paramWorld, BlockPosition paramBlockPosition, EnumHand paramEnumHand, EnumDirection paramEnumDirection, float paramFloat1, float paramFloat2, float paramFloat3) {
/* 36 */     ItemStack itemStack = paramEntityHuman.b(paramEnumHand);
/* 37 */     if (!paramEntityHuman.a(paramBlockPosition.shift(paramEnumDirection), paramEnumDirection, itemStack)) {
/* 38 */       return EnumInteractionResult.FAIL;
/*    */     }
/*    */     
/* 41 */     IBlockData iBlockData = paramWorld.getType(paramBlockPosition);
/* 42 */     Block block = iBlockData.getBlock();
/*    */     
/* 44 */     if (paramEnumDirection != EnumDirection.DOWN && paramWorld.getType(paramBlockPosition.up()).getMaterial() == Material.AIR) {
/* 45 */       if (block == Blocks.GRASS || block == Blocks.GRASS_PATH) {
/* 46 */         a(itemStack, paramEntityHuman, paramWorld, paramBlockPosition, Blocks.FARMLAND.getBlockData());
/* 47 */         return EnumInteractionResult.SUCCESS;
/* 48 */       }  if (block == Blocks.DIRT) {
/* 49 */         switch (null.a[((BlockDirt.EnumDirtVariant)iBlockData.get((IBlockState)BlockDirt.VARIANT)).ordinal()]) {
/*    */           case 1:
/* 51 */             a(itemStack, paramEntityHuman, paramWorld, paramBlockPosition, Blocks.FARMLAND.getBlockData());
/* 52 */             return EnumInteractionResult.SUCCESS;
/*    */           case 2:
/* 54 */             a(itemStack, paramEntityHuman, paramWorld, paramBlockPosition, Blocks.DIRT.getBlockData().set(BlockDirt.VARIANT, BlockDirt.EnumDirtVariant.DIRT));
/* 55 */             return EnumInteractionResult.SUCCESS;
/*    */         } 
/*    */       
/*    */       }
/*    */     } 
/* 60 */     return EnumInteractionResult.PASS;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a(ItemStack paramItemStack, EntityLiving paramEntityLiving1, EntityLiving paramEntityLiving2) {
/* 65 */     paramItemStack.damage(1, paramEntityLiving2);
/* 66 */     return true;
/*    */   }
/*    */   
/*    */   protected void a(ItemStack paramItemStack, EntityHuman paramEntityHuman, World paramWorld, BlockPosition paramBlockPosition, IBlockData paramIBlockData) {
/* 70 */     paramWorld.a(paramEntityHuman, paramBlockPosition, SoundEffects.cE, SoundCategory.BLOCKS, 1.0F, 1.0F);
/*    */     
/* 72 */     if (!paramWorld.isClientSide) {
/* 73 */       paramWorld.setTypeAndData(paramBlockPosition, paramIBlockData, 11);
/* 74 */       paramItemStack.damage(1, paramEntityHuman);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String g() {
/* 84 */     return this.a.toString();
/*    */   }
/*    */ 
/*    */   
/*    */   public Multimap<String, AttributeModifier> a(EnumItemSlot paramEnumItemSlot) {
/* 89 */     Multimap<String, AttributeModifier> multimap = super.a(paramEnumItemSlot);
/*    */ 
/*    */     
/* 92 */     if (paramEnumItemSlot == EnumItemSlot.MAINHAND) {
/* 93 */       multimap.put(GenericAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(h, "Weapon modifier", 0.0D, 0));
/* 94 */       multimap.put(GenericAttributes.g.getName(), new AttributeModifier(i, "Weapon modifier", (this.b - 4.0F), 0));
/*    */     } 
/*    */     
/* 97 */     return multimap;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\ItemHoe.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */