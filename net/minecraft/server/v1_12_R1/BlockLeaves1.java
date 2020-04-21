/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import com.google.common.base.Predicate;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BlockLeaves1
/*     */   extends BlockLeaves
/*     */ {
/*  21 */   public static final BlockStateEnum<BlockWood.EnumLogVariant> VARIANT = BlockStateEnum.a("variant", BlockWood.EnumLogVariant.class, new Predicate<BlockWood.EnumLogVariant>()
/*     */       {
/*     */         public boolean a(@Nullable BlockWood.EnumLogVariant param1EnumLogVariant) {
/*  24 */           return (param1EnumLogVariant.a() < 4);
/*     */         }
/*     */       });
/*     */   
/*     */   public BlockLeaves1() {
/*  29 */     w(this.blockStateList.getBlockData().<BlockWood.EnumLogVariant, BlockWood.EnumLogVariant>set(VARIANT, BlockWood.EnumLogVariant.OAK).<Comparable, Boolean>set(CHECK_DECAY, Boolean.valueOf(true)).set(DECAYABLE, Boolean.valueOf(true)));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void a(World paramWorld, BlockPosition paramBlockPosition, IBlockData paramIBlockData, int paramInt) {
/*  34 */     if (paramIBlockData.get(VARIANT) == BlockWood.EnumLogVariant.OAK && paramWorld.random.nextInt(paramInt) == 0) {
/*  35 */       a(paramWorld, paramBlockPosition, new ItemStack(Items.APPLE));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected int x(IBlockData paramIBlockData) {
/*  41 */     if (paramIBlockData.get(VARIANT) == BlockWood.EnumLogVariant.JUNGLE) {
/*  42 */       return 40;
/*     */     }
/*     */     
/*  45 */     return super.x(paramIBlockData);
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
/*     */   protected ItemStack u(IBlockData paramIBlockData) {
/*  58 */     return new ItemStack(Item.getItemOf(this), 1, ((BlockWood.EnumLogVariant)paramIBlockData.<BlockWood.EnumLogVariant>get(VARIANT)).a());
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData fromLegacyData(int paramInt) {
/*  63 */     return getBlockData()
/*  64 */       .<BlockWood.EnumLogVariant, BlockWood.EnumLogVariant>set(VARIANT, b(paramInt))
/*  65 */       .<Comparable, Boolean>set(DECAYABLE, Boolean.valueOf(((paramInt & 0x4) == 0)))
/*  66 */       .set(CHECK_DECAY, Boolean.valueOf(((paramInt & 0x8) > 0)));
/*     */   }
/*     */ 
/*     */   
/*     */   public int toLegacyData(IBlockData paramIBlockData) {
/*  71 */     int i = 0;
/*     */     
/*  73 */     i |= ((BlockWood.EnumLogVariant)paramIBlockData.<BlockWood.EnumLogVariant>get(VARIANT)).a();
/*     */     
/*  75 */     if (!((Boolean)paramIBlockData.<Boolean>get(DECAYABLE)).booleanValue()) {
/*  76 */       i |= 0x4;
/*     */     }
/*     */     
/*  79 */     if (((Boolean)paramIBlockData.<Boolean>get(CHECK_DECAY)).booleanValue()) {
/*  80 */       i |= 0x8;
/*     */     }
/*     */     
/*  83 */     return i;
/*     */   }
/*     */ 
/*     */   
/*     */   public BlockWood.EnumLogVariant b(int paramInt) {
/*  88 */     return BlockWood.EnumLogVariant.a((paramInt & 0x3) % 4);
/*     */   }
/*     */ 
/*     */   
/*     */   protected BlockStateList getStateList() {
/*  93 */     return new BlockStateList(this, (IBlockState<?>[])new IBlockState[] { VARIANT, CHECK_DECAY, DECAYABLE });
/*     */   }
/*     */ 
/*     */   
/*     */   public int getDropData(IBlockData paramIBlockData) {
/*  98 */     return ((BlockWood.EnumLogVariant)paramIBlockData.<BlockWood.EnumLogVariant>get(VARIANT)).a();
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(World paramWorld, EntityHuman paramEntityHuman, BlockPosition paramBlockPosition, IBlockData paramIBlockData, @Nullable TileEntity paramTileEntity, ItemStack paramItemStack) {
/* 103 */     if (!paramWorld.isClientSide && paramItemStack.getItem() == Items.SHEARS) {
/* 104 */       paramEntityHuman.b(StatisticList.a(this));
/*     */ 
/*     */       
/* 107 */       a(paramWorld, paramBlockPosition, new ItemStack(Item.getItemOf(this), 1, ((BlockWood.EnumLogVariant)paramIBlockData.<BlockWood.EnumLogVariant>get(VARIANT)).a()));
/*     */       
/*     */       return;
/*     */     } 
/* 111 */     super.a(paramWorld, paramEntityHuman, paramBlockPosition, paramIBlockData, paramTileEntity, paramItemStack);
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BlockLeaves1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */