/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import java.util.Random;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BlockLongGrass
/*     */   extends BlockPlant
/*     */   implements IBlockFragilePlantElement
/*     */ {
/*  25 */   public static final BlockStateEnum<EnumTallGrassType> TYPE = BlockStateEnum.of("type", EnumTallGrassType.class);
/*     */   
/*  27 */   protected static final AxisAlignedBB c = new AxisAlignedBB(0.09999999403953552D, 0.0D, 0.09999999403953552D, 0.8999999761581421D, 0.800000011920929D, 0.8999999761581421D);
/*     */   
/*     */   protected BlockLongGrass() {
/*  30 */     super(Material.REPLACEABLE_PLANT);
/*  31 */     w(this.blockStateList.getBlockData().set(TYPE, EnumTallGrassType.DEAD_BUSH));
/*     */   }
/*     */ 
/*     */   
/*     */   public AxisAlignedBB b(IBlockData paramIBlockData, IBlockAccess paramIBlockAccess, BlockPosition paramBlockPosition) {
/*  36 */     return c;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean f(World paramWorld, BlockPosition paramBlockPosition, IBlockData paramIBlockData) {
/*  41 */     return x(paramWorld.getType(paramBlockPosition.down()));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(IBlockAccess paramIBlockAccess, BlockPosition paramBlockPosition) {
/*  46 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public Item getDropType(IBlockData paramIBlockData, Random paramRandom, int paramInt) {
/*  51 */     if (paramRandom.nextInt(8) == 0) {
/*  52 */       return Items.WHEAT_SEEDS;
/*     */     }
/*     */     
/*  55 */     return Items.a;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getDropCount(int paramInt, Random paramRandom) {
/*  60 */     return 1 + paramRandom.nextInt(paramInt * 2 + 1);
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(World paramWorld, EntityHuman paramEntityHuman, BlockPosition paramBlockPosition, IBlockData paramIBlockData, @Nullable TileEntity paramTileEntity, ItemStack paramItemStack) {
/*  65 */     if (!paramWorld.isClientSide && paramItemStack.getItem() == Items.SHEARS) {
/*  66 */       paramEntityHuman.b(StatisticList.a(this));
/*     */ 
/*     */       
/*  69 */       a(paramWorld, paramBlockPosition, new ItemStack(Blocks.TALLGRASS, 1, ((EnumTallGrassType)paramIBlockData.<EnumTallGrassType>get(TYPE)).a()));
/*     */     } else {
/*  71 */       super.a(paramWorld, paramEntityHuman, paramBlockPosition, paramIBlockData, paramTileEntity, paramItemStack);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack a(World paramWorld, BlockPosition paramBlockPosition, IBlockData paramIBlockData) {
/*  77 */     return new ItemStack(this, 1, paramIBlockData.getBlock().toLegacyData(paramIBlockData));
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
/*     */   public boolean a(World paramWorld, BlockPosition paramBlockPosition, IBlockData paramIBlockData, boolean paramBoolean) {
/*  89 */     return (paramIBlockData.get(TYPE) != EnumTallGrassType.DEAD_BUSH);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(World paramWorld, Random paramRandom, BlockPosition paramBlockPosition, IBlockData paramIBlockData) {
/*  94 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void b(World paramWorld, Random paramRandom, BlockPosition paramBlockPosition, IBlockData paramIBlockData) {
/*  99 */     BlockTallPlant.EnumTallFlowerVariants enumTallFlowerVariants = BlockTallPlant.EnumTallFlowerVariants.GRASS;
/* 100 */     if (paramIBlockData.get(TYPE) == EnumTallGrassType.FERN) {
/* 101 */       enumTallFlowerVariants = BlockTallPlant.EnumTallFlowerVariants.FERN;
/*     */     }
/* 103 */     if (Blocks.DOUBLE_PLANT.canPlace(paramWorld, paramBlockPosition)) {
/* 104 */       Blocks.DOUBLE_PLANT.a(paramWorld, paramBlockPosition, enumTallFlowerVariants, 2);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData fromLegacyData(int paramInt) {
/* 110 */     return getBlockData()
/* 111 */       .set(TYPE, EnumTallGrassType.a(paramInt));
/*     */   }
/*     */ 
/*     */   
/*     */   public int toLegacyData(IBlockData paramIBlockData) {
/* 116 */     return ((EnumTallGrassType)paramIBlockData.<EnumTallGrassType>get(TYPE)).a();
/*     */   }
/*     */ 
/*     */   
/*     */   protected BlockStateList getStateList() {
/* 121 */     return new BlockStateList(this, (IBlockState<?>[])new IBlockState[] { TYPE });
/*     */   }
/*     */   
/*     */   public enum EnumTallGrassType implements INamable {
/* 125 */     DEAD_BUSH(0, "dead_bush"),
/* 126 */     GRASS(1, "tall_grass"),
/* 127 */     FERN(2, "fern");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 135 */     private static final EnumTallGrassType[] d = new EnumTallGrassType[(values()).length]; private final int e;
/*     */     static {
/* 137 */       for (EnumTallGrassType enumTallGrassType : values())
/* 138 */         d[enumTallGrassType.a()] = enumTallGrassType; 
/*     */     }
/*     */     private final String f;
/*     */     
/*     */     EnumTallGrassType(int param1Int1, String param1String1) {
/* 143 */       this.e = param1Int1;
/* 144 */       this.f = param1String1;
/*     */     }
/*     */     
/*     */     public int a() {
/* 148 */       return this.e;
/*     */     }
/*     */ 
/*     */     
/*     */     public String toString() {
/* 153 */       return this.f;
/*     */     }
/*     */     
/*     */     public static EnumTallGrassType a(int param1Int) {
/* 157 */       if (param1Int < 0 || param1Int >= d.length) {
/* 158 */         param1Int = 0;
/*     */       }
/* 160 */       return d[param1Int];
/*     */     }
/*     */ 
/*     */     
/*     */     public String getName() {
/* 165 */       return this.f;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public Block.EnumRandomOffset u() {
/* 171 */     return Block.EnumRandomOffset.XYZ;
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BlockLongGrass.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */