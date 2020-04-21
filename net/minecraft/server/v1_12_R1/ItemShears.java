/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ItemShears
/*    */   extends Item
/*    */ {
/*    */   public ItemShears() {
/* 13 */     d(1);
/* 14 */     setMaxDurability(238);
/* 15 */     b(CreativeModeTab.i);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a(ItemStack paramItemStack, World paramWorld, IBlockData paramIBlockData, BlockPosition paramBlockPosition, EntityLiving paramEntityLiving) {
/* 20 */     if (!paramWorld.isClientSide) {
/* 21 */       paramItemStack.damage(1, paramEntityLiving);
/*    */     }
/*    */     
/* 24 */     Block block = paramIBlockData.getBlock();
/* 25 */     if (paramIBlockData.getMaterial() == Material.LEAVES || block == Blocks.WEB || block == Blocks.TALLGRASS || block == Blocks.VINE || block == Blocks.TRIPWIRE || block == Blocks.WOOL) {
/* 26 */       return true;
/*    */     }
/* 28 */     return super.a(paramItemStack, paramWorld, paramIBlockData, paramBlockPosition, paramEntityLiving);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canDestroySpecialBlock(IBlockData paramIBlockData) {
/* 33 */     Block block = paramIBlockData.getBlock();
/* 34 */     return (block == Blocks.WEB || block == Blocks.REDSTONE_WIRE || block == Blocks.TRIPWIRE);
/*    */   }
/*    */ 
/*    */   
/*    */   public float getDestroySpeed(ItemStack paramItemStack, IBlockData paramIBlockData) {
/* 39 */     Block block = paramIBlockData.getBlock();
/* 40 */     if (block == Blocks.WEB || paramIBlockData.getMaterial() == Material.LEAVES) {
/* 41 */       return 15.0F;
/*    */     }
/* 43 */     if (block == Blocks.WOOL) {
/* 44 */       return 5.0F;
/*    */     }
/* 46 */     return super.getDestroySpeed(paramItemStack, paramIBlockData);
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\ItemShears.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */