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
/*     */ public class BlockLeaves2
/*     */   extends BlockLeaves
/*     */ {
/*  21 */   public static final BlockStateEnum<BlockWood.EnumLogVariant> VARIANT = BlockStateEnum.a("variant", BlockWood.EnumLogVariant.class, new Predicate<BlockWood.EnumLogVariant>()
/*     */       {
/*     */         public boolean a(@Nullable BlockWood.EnumLogVariant param1EnumLogVariant) {
/*  24 */           return (param1EnumLogVariant.a() >= 4);
/*     */         }
/*     */       });
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BlockLeaves2() {
/*  32 */     w(this.blockStateList.getBlockData().<BlockWood.EnumLogVariant, BlockWood.EnumLogVariant>set(VARIANT, BlockWood.EnumLogVariant.ACACIA).<Comparable, Boolean>set(CHECK_DECAY, Boolean.valueOf(true)).set(DECAYABLE, Boolean.valueOf(true)));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void a(World paramWorld, BlockPosition paramBlockPosition, IBlockData paramIBlockData, int paramInt) {
/*  37 */     if (paramIBlockData.get(VARIANT) == BlockWood.EnumLogVariant.DARK_OAK && paramWorld.random.nextInt(paramInt) == 0) {
/*  38 */       a(paramWorld, paramBlockPosition, new ItemStack(Items.APPLE));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public int getDropData(IBlockData paramIBlockData) {
/*  44 */     return ((BlockWood.EnumLogVariant)paramIBlockData.<BlockWood.EnumLogVariant>get(VARIANT)).a();
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack a(World paramWorld, BlockPosition paramBlockPosition, IBlockData paramIBlockData) {
/*  49 */     return new ItemStack(this, 1, paramIBlockData.getBlock().toLegacyData(paramIBlockData) & 0x3);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected ItemStack u(IBlockData paramIBlockData) {
/*  60 */     return new ItemStack(Item.getItemOf(this), 1, ((BlockWood.EnumLogVariant)paramIBlockData.<BlockWood.EnumLogVariant>get(VARIANT)).a() - 4);
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData fromLegacyData(int paramInt) {
/*  65 */     return getBlockData()
/*  66 */       .<BlockWood.EnumLogVariant, BlockWood.EnumLogVariant>set(VARIANT, b(paramInt))
/*  67 */       .<Comparable, Boolean>set(DECAYABLE, Boolean.valueOf(((paramInt & 0x4) == 0)))
/*  68 */       .set(CHECK_DECAY, Boolean.valueOf(((paramInt & 0x8) > 0)));
/*     */   }
/*     */ 
/*     */   
/*     */   public int toLegacyData(IBlockData paramIBlockData) {
/*  73 */     int i = 0;
/*     */     
/*  75 */     i |= ((BlockWood.EnumLogVariant)paramIBlockData.<BlockWood.EnumLogVariant>get(VARIANT)).a() - 4;
/*     */     
/*  77 */     if (!((Boolean)paramIBlockData.<Boolean>get(DECAYABLE)).booleanValue()) {
/*  78 */       i |= 0x4;
/*     */     }
/*     */     
/*  81 */     if (((Boolean)paramIBlockData.<Boolean>get(CHECK_DECAY)).booleanValue()) {
/*  82 */       i |= 0x8;
/*     */     }
/*     */     
/*  85 */     return i;
/*     */   }
/*     */ 
/*     */   
/*     */   public BlockWood.EnumLogVariant b(int paramInt) {
/*  90 */     return BlockWood.EnumLogVariant.a((paramInt & 0x3) + 4);
/*     */   }
/*     */ 
/*     */   
/*     */   protected BlockStateList getStateList() {
/*  95 */     return new BlockStateList(this, (IBlockState<?>[])new IBlockState[] { VARIANT, CHECK_DECAY, DECAYABLE });
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(World paramWorld, EntityHuman paramEntityHuman, BlockPosition paramBlockPosition, IBlockData paramIBlockData, @Nullable TileEntity paramTileEntity, ItemStack paramItemStack) {
/* 100 */     if (!paramWorld.isClientSide && paramItemStack.getItem() == Items.SHEARS) {
/* 101 */       paramEntityHuman.b(StatisticList.a(this));
/*     */ 
/*     */       
/* 104 */       a(paramWorld, paramBlockPosition, new ItemStack(Item.getItemOf(this), 1, ((BlockWood.EnumLogVariant)paramIBlockData.<BlockWood.EnumLogVariant>get(VARIANT)).a() - 4));
/*     */       
/*     */       return;
/*     */     } 
/* 108 */     super.a(paramWorld, paramEntityHuman, paramBlockPosition, paramIBlockData, paramTileEntity, paramItemStack);
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BlockLeaves2.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */