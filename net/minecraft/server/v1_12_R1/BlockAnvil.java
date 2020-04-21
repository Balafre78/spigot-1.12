/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
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
/*     */ public class BlockAnvil
/*     */   extends BlockFalling
/*     */ {
/*  31 */   public static final BlockStateDirection FACING = BlockFacingHorizontal.FACING;
/*  32 */   public static final BlockStateInteger DAMAGE = BlockStateInteger.of("damage", 0, 2);
/*     */   
/*  34 */   protected static final AxisAlignedBB c = new AxisAlignedBB(0.0D, 0.0D, 0.125D, 1.0D, 1.0D, 0.875D);
/*  35 */   protected static final AxisAlignedBB d = new AxisAlignedBB(0.125D, 0.0D, 0.0D, 0.875D, 1.0D, 1.0D);
/*     */   
/*  37 */   protected static final Logger e = LogManager.getLogger();
/*     */   
/*     */   protected BlockAnvil() {
/*  40 */     super(Material.HEAVY);
/*  41 */     w(this.blockStateList.getBlockData().<Comparable, EnumDirection>set(FACING, EnumDirection.NORTH).set(DAMAGE, Integer.valueOf(0)));
/*  42 */     e(0);
/*  43 */     a(CreativeModeTab.c);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean c(IBlockData paramIBlockData) {
/*  48 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumBlockFaceShape a(IBlockAccess paramIBlockAccess, IBlockData paramIBlockData, BlockPosition paramBlockPosition, EnumDirection paramEnumDirection) {
/*  53 */     return EnumBlockFaceShape.UNDEFINED;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean b(IBlockData paramIBlockData) {
/*  58 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData getPlacedState(World paramWorld, BlockPosition paramBlockPosition, EnumDirection paramEnumDirection, float paramFloat1, float paramFloat2, float paramFloat3, int paramInt, EntityLiving paramEntityLiving) {
/*  63 */     EnumDirection enumDirection = paramEntityLiving.getDirection().e();
/*     */     
/*     */     try {
/*  66 */       return super.getPlacedState(paramWorld, paramBlockPosition, paramEnumDirection, paramFloat1, paramFloat2, paramFloat3, paramInt, paramEntityLiving).<Comparable, EnumDirection>set(FACING, enumDirection).set(DAMAGE, Integer.valueOf(paramInt >> 2));
/*  67 */     } catch (IllegalArgumentException illegalArgumentException) {
/*  68 */       if (!paramWorld.isClientSide) {
/*  69 */         e.warn(String.format("Invalid damage property for anvil at %s. Found %d, must be in [0, 1, 2]", new Object[] { paramBlockPosition, Integer.valueOf(paramInt >> 2) }));
/*  70 */         if (paramEntityLiving instanceof EntityHuman) {
/*  71 */           paramEntityLiving.sendMessage(new ChatMessage("Invalid damage property. Please pick in [0, 1, 2]", new Object[0]));
/*     */         }
/*     */       } 
/*  74 */       return super.getPlacedState(paramWorld, paramBlockPosition, paramEnumDirection, paramFloat1, paramFloat2, paramFloat3, 0, paramEntityLiving).<Comparable, EnumDirection>set(FACING, enumDirection).set(DAMAGE, Integer.valueOf(0));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean interact(World paramWorld, BlockPosition paramBlockPosition, IBlockData paramIBlockData, EntityHuman paramEntityHuman, EnumHand paramEnumHand, EnumDirection paramEnumDirection, float paramFloat1, float paramFloat2, float paramFloat3) {
/*  80 */     if (!paramWorld.isClientSide) {
/*  81 */       paramEntityHuman.openTileEntity(new TileEntityContainerAnvil(paramWorld, paramBlockPosition));
/*     */     }
/*     */     
/*  84 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getDropData(IBlockData paramIBlockData) {
/*  89 */     return ((Integer)paramIBlockData.<Integer>get(DAMAGE)).intValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public AxisAlignedBB b(IBlockData paramIBlockData, IBlockAccess paramIBlockAccess, BlockPosition paramBlockPosition) {
/*  94 */     EnumDirection enumDirection = paramIBlockData.<EnumDirection>get(FACING);
/*  95 */     if (enumDirection.k() == EnumDirection.EnumAxis.X) {
/*  96 */       return c;
/*     */     }
/*  98 */     return d;
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
/*     */   protected void a(EntityFallingBlock paramEntityFallingBlock) {
/* 111 */     paramEntityFallingBlock.a(true);
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(World paramWorld, BlockPosition paramBlockPosition, IBlockData paramIBlockData1, IBlockData paramIBlockData2) {
/* 116 */     paramWorld.triggerEffect(1031, paramBlockPosition, 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void a_(World paramWorld, BlockPosition paramBlockPosition) {
/* 121 */     paramWorld.triggerEffect(1029, paramBlockPosition, 0);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static class TileEntityContainerAnvil
/*     */     implements ITileEntityContainer
/*     */   {
/*     */     private final World a;
/*     */ 
/*     */     
/*     */     private final BlockPosition b;
/*     */ 
/*     */     
/*     */     public TileEntityContainerAnvil(World param1World, BlockPosition param1BlockPosition) {
/* 136 */       this.a = param1World;
/* 137 */       this.b = param1BlockPosition;
/*     */     }
/*     */ 
/*     */     
/*     */     public String getName() {
/* 142 */       return "anvil";
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean hasCustomName() {
/* 147 */       return false;
/*     */     }
/*     */ 
/*     */     
/*     */     public IChatBaseComponent getScoreboardDisplayName() {
/* 152 */       return new ChatMessage(Blocks.ANVIL.a() + ".name", new Object[0]);
/*     */     }
/*     */ 
/*     */     
/*     */     public Container createContainer(PlayerInventory param1PlayerInventory, EntityHuman param1EntityHuman) {
/* 157 */       return new ContainerAnvil(param1PlayerInventory, this.a, this.b, param1EntityHuman);
/*     */     }
/*     */ 
/*     */     
/*     */     public String getContainerName() {
/* 162 */       return "minecraft:anvil";
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData fromLegacyData(int paramInt) {
/* 168 */     return getBlockData()
/* 169 */       .<Comparable, EnumDirection>set(FACING, EnumDirection.fromType2(paramInt & 0x3))
/* 170 */       .set(DAMAGE, Integer.valueOf((paramInt & 0xF) >> 2));
/*     */   }
/*     */ 
/*     */   
/*     */   public int toLegacyData(IBlockData paramIBlockData) {
/* 175 */     int i = 0;
/*     */     
/* 177 */     i |= ((EnumDirection)paramIBlockData.<EnumDirection>get(FACING)).get2DRotationValue();
/* 178 */     i |= ((Integer)paramIBlockData.<Integer>get(DAMAGE)).intValue() << 2;
/*     */     
/* 180 */     return i;
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData a(IBlockData paramIBlockData, EnumBlockRotation paramEnumBlockRotation) {
/* 185 */     if (paramIBlockData.getBlock() != this) {
/* 186 */       return paramIBlockData;
/*     */     }
/* 188 */     return paramIBlockData.set(FACING, paramEnumBlockRotation.a(paramIBlockData.<EnumDirection>get(FACING)));
/*     */   }
/*     */ 
/*     */   
/*     */   protected BlockStateList getStateList() {
/* 193 */     return new BlockStateList(this, (IBlockState<?>[])new IBlockState[] { FACING, DAMAGE });
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BlockAnvil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */