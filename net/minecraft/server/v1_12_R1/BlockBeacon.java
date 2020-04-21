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
/*     */ public class BlockBeacon
/*     */   extends BlockTileEntity
/*     */ {
/*     */   public BlockBeacon() {
/*  24 */     super(Material.SHATTERABLE, MaterialMapColor.H);
/*  25 */     c(3.0F);
/*  26 */     a(CreativeModeTab.f);
/*     */   }
/*     */ 
/*     */   
/*     */   public TileEntity a(World paramWorld, int paramInt) {
/*  31 */     return new TileEntityBeacon();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean interact(World paramWorld, BlockPosition paramBlockPosition, IBlockData paramIBlockData, EntityHuman paramEntityHuman, EnumHand paramEnumHand, EnumDirection paramEnumDirection, float paramFloat1, float paramFloat2, float paramFloat3) {
/*  36 */     if (paramWorld.isClientSide) {
/*  37 */       return true;
/*     */     }
/*     */     
/*  40 */     TileEntity tileEntity = paramWorld.getTileEntity(paramBlockPosition);
/*  41 */     if (tileEntity instanceof TileEntityBeacon) {
/*  42 */       paramEntityHuman.openContainer((TileEntityBeacon)tileEntity);
/*  43 */       paramEntityHuman.b(StatisticList.N);
/*     */     } 
/*     */     
/*  46 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean b(IBlockData paramIBlockData) {
/*  51 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean c(IBlockData paramIBlockData) {
/*  56 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumRenderType a(IBlockData paramIBlockData) {
/*  61 */     return EnumRenderType.MODEL;
/*     */   }
/*     */ 
/*     */   
/*     */   public void postPlace(World paramWorld, BlockPosition paramBlockPosition, IBlockData paramIBlockData, EntityLiving paramEntityLiving, ItemStack paramItemStack) {
/*  66 */     super.postPlace(paramWorld, paramBlockPosition, paramIBlockData, paramEntityLiving, paramItemStack);
/*     */     
/*  68 */     if (paramItemStack.hasName()) {
/*  69 */       TileEntity tileEntity = paramWorld.getTileEntity(paramBlockPosition);
/*  70 */       if (tileEntity instanceof TileEntityBeacon) {
/*  71 */         ((TileEntityBeacon)tileEntity).setCustomName(paramItemStack.getName());
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(IBlockData paramIBlockData, World paramWorld, BlockPosition paramBlockPosition1, Block paramBlock, BlockPosition paramBlockPosition2) {
/*  78 */     TileEntity tileEntity = paramWorld.getTileEntity(paramBlockPosition1);
/*  79 */     if (tileEntity instanceof TileEntityBeacon) {
/*  80 */       ((TileEntityBeacon)tileEntity).n();
/*  81 */       paramWorld.playBlockAction(paramBlockPosition1, this, 1, 0);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void c(World paramWorld, BlockPosition paramBlockPosition) {
/*  91 */     HttpUtilities.a.submit(new Runnable(paramWorld, paramBlockPosition)
/*     */         {
/*     */           public void run() {
/*  94 */             Chunk chunk = this.a.getChunkAtWorldCoords(this.b);
/*  95 */             for (int i = this.b.getY() - 1; i >= 0; ) {
/*  96 */               BlockPosition blockPosition = new BlockPosition(this.b.getX(), i, this.b.getZ());
/*  97 */               if (chunk.c(blockPosition)) {
/*  98 */                 IBlockData iBlockData = this.a.getType(blockPosition);
/*  99 */                 if (iBlockData.getBlock() == Blocks.BEACON)
/* 100 */                   ((WorldServer)this.a).postToMainThread(new Runnable(this, blockPosition)
/*     */                       {
/*     */                         public void run() {
/* 103 */                           TileEntity tileEntity = this.b.a.getTileEntity(this.a);
/* 104 */                           if (tileEntity instanceof TileEntityBeacon) {
/* 105 */                             ((TileEntityBeacon)tileEntity).n();
/* 106 */                             this.b.a.playBlockAction(this.a, Blocks.BEACON, 1, 0);
/*     */                           } 
/*     */                         }
/*     */                       }); 
/*     */                 i--;
/*     */               } 
/*     */             } 
/*     */           }
/*     */         });
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BlockBeacon.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */