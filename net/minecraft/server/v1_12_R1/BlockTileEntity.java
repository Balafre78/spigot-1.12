/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import javax.annotation.Nullable;
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
/*    */ public abstract class BlockTileEntity
/*    */   extends Block
/*    */   implements ITileEntity
/*    */ {
/*    */   protected BlockTileEntity(Material paramMaterial) {
/* 24 */     this(paramMaterial, paramMaterial.r());
/*    */   }
/*    */   
/*    */   protected BlockTileEntity(Material paramMaterial, MaterialMapColor paramMaterialMapColor) {
/* 28 */     super(paramMaterial, paramMaterialMapColor);
/* 29 */     this.isTileEntity = true;
/*    */   }
/*    */   
/*    */   protected boolean a(World paramWorld, BlockPosition paramBlockPosition, EnumDirection paramEnumDirection) {
/* 33 */     return (paramWorld.getType(paramBlockPosition.shift(paramEnumDirection)).getMaterial() == Material.CACTUS);
/*    */   }
/*    */   
/*    */   protected boolean b(World paramWorld, BlockPosition paramBlockPosition) {
/* 37 */     return (a(paramWorld, paramBlockPosition, EnumDirection.NORTH) || a(paramWorld, paramBlockPosition, EnumDirection.SOUTH) || a(paramWorld, paramBlockPosition, EnumDirection.WEST) || a(paramWorld, paramBlockPosition, EnumDirection.EAST));
/*    */   }
/*    */ 
/*    */   
/*    */   public EnumRenderType a(IBlockData paramIBlockData) {
/* 42 */     return EnumRenderType.INVISIBLE;
/*    */   }
/*    */ 
/*    */   
/*    */   public void remove(World paramWorld, BlockPosition paramBlockPosition, IBlockData paramIBlockData) {
/* 47 */     super.remove(paramWorld, paramBlockPosition, paramIBlockData);
/* 48 */     paramWorld.s(paramBlockPosition);
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(World paramWorld, EntityHuman paramEntityHuman, BlockPosition paramBlockPosition, IBlockData paramIBlockData, @Nullable TileEntity paramTileEntity, ItemStack paramItemStack) {
/* 53 */     if (paramTileEntity instanceof INamableTileEntity && ((INamableTileEntity)paramTileEntity).hasCustomName()) {
/* 54 */       paramEntityHuman.b(StatisticList.a(this));
/* 55 */       paramEntityHuman.applyExhaustion(0.005F);
/* 56 */       if (paramWorld.isClientSide) {
/*    */         return;
/*    */       }
/*    */       
/* 60 */       int i = EnchantmentManager.getEnchantmentLevel(Enchantments.LOOT_BONUS_BLOCKS, paramItemStack);
/* 61 */       Item item = getDropType(paramIBlockData, paramWorld.random, i);
/* 62 */       if (item == Items.a) {
/*    */         return;
/*    */       }
/*    */       
/* 66 */       ItemStack itemStack = new ItemStack(item, a(paramWorld.random));
/* 67 */       itemStack.g(((INamableTileEntity)paramTileEntity).getName());
/* 68 */       a(paramWorld, paramBlockPosition, itemStack);
/*    */     } else {
/* 70 */       super.a(paramWorld, paramEntityHuman, paramBlockPosition, paramIBlockData, null, paramItemStack);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a(IBlockData paramIBlockData, World paramWorld, BlockPosition paramBlockPosition, int paramInt1, int paramInt2) {
/* 76 */     super.a(paramIBlockData, paramWorld, paramBlockPosition, paramInt1, paramInt2);
/*    */     
/* 78 */     TileEntity tileEntity = paramWorld.getTileEntity(paramBlockPosition);
/* 79 */     if (tileEntity == null) {
/* 80 */       return false;
/*    */     }
/* 82 */     return tileEntity.c(paramInt1, paramInt2);
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BlockTileEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */