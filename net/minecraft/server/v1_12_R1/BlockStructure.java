/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import java.util.Random;
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
/*     */ public class BlockStructure
/*     */   extends BlockTileEntity
/*     */ {
/*  21 */   public static final BlockStateEnum<TileEntityStructure.UsageMode> a = BlockStateEnum.of("mode", TileEntityStructure.UsageMode.class);
/*     */   
/*     */   public BlockStructure() {
/*  24 */     super(Material.ORE, MaterialMapColor.y);
/*  25 */     w(this.blockStateList.getBlockData());
/*     */   }
/*     */ 
/*     */   
/*     */   public TileEntity a(World paramWorld, int paramInt) {
/*  30 */     return new TileEntityStructure();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean interact(World paramWorld, BlockPosition paramBlockPosition, IBlockData paramIBlockData, EntityHuman paramEntityHuman, EnumHand paramEnumHand, EnumDirection paramEnumDirection, float paramFloat1, float paramFloat2, float paramFloat3) {
/*  35 */     TileEntity tileEntity = paramWorld.getTileEntity(paramBlockPosition);
/*  36 */     if (tileEntity instanceof TileEntityStructure) {
/*  37 */       return ((TileEntityStructure)tileEntity).a(paramEntityHuman);
/*     */     }
/*  39 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void postPlace(World paramWorld, BlockPosition paramBlockPosition, IBlockData paramIBlockData, EntityLiving paramEntityLiving, ItemStack paramItemStack) {
/*  44 */     if (paramWorld.isClientSide) {
/*     */       return;
/*     */     }
/*  47 */     TileEntity tileEntity = paramWorld.getTileEntity(paramBlockPosition);
/*  48 */     if (!(tileEntity instanceof TileEntityStructure)) {
/*     */       return;
/*     */     }
/*     */     
/*  52 */     TileEntityStructure tileEntityStructure = (TileEntityStructure)tileEntity;
/*  53 */     tileEntityStructure.a(paramEntityLiving);
/*     */   }
/*     */ 
/*     */   
/*     */   public int a(Random paramRandom) {
/*  58 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumRenderType a(IBlockData paramIBlockData) {
/*  63 */     return EnumRenderType.MODEL;
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData getPlacedState(World paramWorld, BlockPosition paramBlockPosition, EnumDirection paramEnumDirection, float paramFloat1, float paramFloat2, float paramFloat3, int paramInt, EntityLiving paramEntityLiving) {
/*  68 */     return getBlockData().set(a, TileEntityStructure.UsageMode.DATA);
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData fromLegacyData(int paramInt) {
/*  73 */     return getBlockData()
/*  74 */       .set(a, TileEntityStructure.UsageMode.a(paramInt));
/*     */   }
/*     */ 
/*     */   
/*     */   public int toLegacyData(IBlockData paramIBlockData) {
/*  79 */     return ((TileEntityStructure.UsageMode)paramIBlockData.<TileEntityStructure.UsageMode>get(a)).a();
/*     */   }
/*     */ 
/*     */   
/*     */   protected BlockStateList getStateList() {
/*  84 */     return new BlockStateList(this, (IBlockState<?>[])new IBlockState[] { a });
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(IBlockData paramIBlockData, World paramWorld, BlockPosition paramBlockPosition1, Block paramBlock, BlockPosition paramBlockPosition2) {
/*  89 */     if (paramWorld.isClientSide) {
/*     */       return;
/*     */     }
/*     */     
/*  93 */     TileEntity tileEntity = paramWorld.getTileEntity(paramBlockPosition1);
/*  94 */     if (!(tileEntity instanceof TileEntityStructure)) {
/*     */       return;
/*     */     }
/*     */     
/*  98 */     TileEntityStructure tileEntityStructure = (TileEntityStructure)tileEntity;
/*     */     
/* 100 */     boolean bool1 = paramWorld.isBlockIndirectlyPowered(paramBlockPosition1);
/* 101 */     boolean bool2 = tileEntityStructure.F();
/*     */     
/* 103 */     if (bool1 && !bool2) {
/* 104 */       tileEntityStructure.d(true);
/* 105 */       a(tileEntityStructure);
/* 106 */     } else if (!bool1 && bool2) {
/* 107 */       tileEntityStructure.d(false);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void a(TileEntityStructure paramTileEntityStructure) {
/* 112 */     switch (null.a[paramTileEntityStructure.k().ordinal()]) {
/*     */       case 1:
/* 114 */         paramTileEntityStructure.b(false);
/*     */         break;
/*     */       case 2:
/* 117 */         paramTileEntityStructure.c(false);
/*     */         break;
/*     */       case 3:
/* 120 */         paramTileEntityStructure.s();
/*     */         break;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BlockStructure.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */