/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import java.util.Random;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.craftbukkit.v1_12_R1.event.CraftEventFactory;
/*     */ 
/*     */ public class BlockTallPlant extends BlockPlant implements IBlockFragilePlantElement {
/*   8 */   public static final BlockStateEnum<EnumTallFlowerVariants> VARIANT = BlockStateEnum.of("variant", EnumTallFlowerVariants.class);
/*   9 */   public static final BlockStateEnum<EnumTallPlantHalf> HALF = BlockStateEnum.of("half", EnumTallPlantHalf.class);
/*  10 */   public static final BlockStateEnum<EnumDirection> d = BlockFacingHorizontal.FACING;
/*     */   
/*     */   public BlockTallPlant() {
/*  13 */     super(Material.REPLACEABLE_PLANT);
/*  14 */     w(this.blockStateList.getBlockData().<EnumTallFlowerVariants, EnumTallFlowerVariants>set(VARIANT, EnumTallFlowerVariants.SUNFLOWER).<EnumTallPlantHalf, EnumTallPlantHalf>set(HALF, EnumTallPlantHalf.LOWER).set(d, EnumDirection.NORTH));
/*  15 */     c(0.0F);
/*  16 */     a(SoundEffectType.c);
/*  17 */     c("doublePlant");
/*     */   }
/*     */   
/*     */   public AxisAlignedBB b(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition) {
/*  21 */     return j;
/*     */   }
/*     */   
/*     */   private EnumTallFlowerVariants a(IBlockAccess iblockaccess, BlockPosition blockposition, IBlockData iblockdata) {
/*  25 */     if (iblockdata.getBlock() == this) {
/*  26 */       iblockdata = iblockdata.c(iblockaccess, blockposition);
/*  27 */       return iblockdata.<EnumTallFlowerVariants>get(VARIANT);
/*     */     } 
/*  29 */     return EnumTallFlowerVariants.FERN;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canPlace(World world, BlockPosition blockposition) {
/*  34 */     return (super.canPlace(world, blockposition) && world.isEmpty(blockposition.up()));
/*     */   }
/*     */   
/*     */   public boolean a(IBlockAccess iblockaccess, BlockPosition blockposition) {
/*  38 */     IBlockData iblockdata = iblockaccess.getType(blockposition);
/*     */     
/*  40 */     if (iblockdata.getBlock() != this) {
/*  41 */       return true;
/*     */     }
/*  43 */     EnumTallFlowerVariants blocktallplant_enumtallflowervariants = iblockdata.c(iblockaccess, blockposition).<EnumTallFlowerVariants>get(VARIANT);
/*     */     
/*  45 */     return !(blocktallplant_enumtallflowervariants != EnumTallFlowerVariants.FERN && blocktallplant_enumtallflowervariants != EnumTallFlowerVariants.GRASS);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void e(World world, BlockPosition blockposition, IBlockData iblockdata) {
/*  50 */     if (!f(world, blockposition, iblockdata)) {
/*     */       
/*  52 */       if (CraftEventFactory.callBlockPhysicsEvent(world, blockposition).isCancelled()) {
/*     */         return;
/*     */       }
/*     */       
/*  56 */       boolean flag = (iblockdata.get(HALF) == EnumTallPlantHalf.UPPER);
/*  57 */       BlockPosition blockposition1 = flag ? blockposition : blockposition.up();
/*  58 */       BlockPosition blockposition2 = flag ? blockposition.down() : blockposition;
/*  59 */       Object object = flag ? this : world.getType(blockposition1).getBlock();
/*  60 */       Object object1 = flag ? world.getType(blockposition2).getBlock() : this;
/*     */       
/*  62 */       if (object == this) {
/*  63 */         world.setTypeAndData(blockposition1, Blocks.AIR.getBlockData(), 2);
/*     */       }
/*     */       
/*  66 */       if (object1 == this) {
/*  67 */         world.setTypeAndData(blockposition2, Blocks.AIR.getBlockData(), 3);
/*  68 */         if (!flag) {
/*  69 */           b(world, blockposition2, iblockdata, 0);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean f(World world, BlockPosition blockposition, IBlockData iblockdata) {
/*  77 */     if (iblockdata.get(HALF) == EnumTallPlantHalf.UPPER) {
/*  78 */       return (world.getType(blockposition.down()).getBlock() == this);
/*     */     }
/*  80 */     IBlockData iblockdata1 = world.getType(blockposition.up());
/*     */     
/*  82 */     return (iblockdata1.getBlock() == this && super.f(world, blockposition, iblockdata1));
/*     */   }
/*     */ 
/*     */   
/*     */   public Item getDropType(IBlockData iblockdata, Random random, int i) {
/*  87 */     if (iblockdata.get(HALF) == EnumTallPlantHalf.UPPER) {
/*  88 */       return Items.a;
/*     */     }
/*  90 */     EnumTallFlowerVariants blocktallplant_enumtallflowervariants = iblockdata.<EnumTallFlowerVariants>get(VARIANT);
/*     */     
/*  92 */     return (blocktallplant_enumtallflowervariants == EnumTallFlowerVariants.FERN) ? Items.a : ((blocktallplant_enumtallflowervariants == EnumTallFlowerVariants.GRASS) ? ((random.nextInt(8) == 0) ? Items.WHEAT_SEEDS : Items.a) : super.getDropType(iblockdata, random, i));
/*     */   }
/*     */ 
/*     */   
/*     */   public int getDropData(IBlockData iblockdata) {
/*  97 */     return (iblockdata.get(HALF) != EnumTallPlantHalf.UPPER && iblockdata.get(VARIANT) != EnumTallFlowerVariants.GRASS) ? ((EnumTallFlowerVariants)iblockdata.<EnumTallFlowerVariants>get(VARIANT)).a() : 0;
/*     */   }
/*     */   
/*     */   public void a(World world, BlockPosition blockposition, EnumTallFlowerVariants blocktallplant_enumtallflowervariants, int i) {
/* 101 */     world.setTypeAndData(blockposition, getBlockData().<EnumTallPlantHalf, EnumTallPlantHalf>set(HALF, EnumTallPlantHalf.LOWER).set(VARIANT, blocktallplant_enumtallflowervariants), i);
/* 102 */     world.setTypeAndData(blockposition.up(), getBlockData().set(HALF, EnumTallPlantHalf.UPPER), i);
/*     */   }
/*     */   
/*     */   public void postPlace(World world, BlockPosition blockposition, IBlockData iblockdata, EntityLiving entityliving, ItemStack itemstack) {
/* 106 */     world.setTypeAndData(blockposition.up(), getBlockData().set(HALF, EnumTallPlantHalf.UPPER), 2);
/*     */   }
/*     */   
/*     */   public void a(World world, EntityHuman entityhuman, BlockPosition blockposition, IBlockData iblockdata, @Nullable TileEntity tileentity, ItemStack itemstack) {
/* 110 */     if (world.isClientSide || itemstack.getItem() != Items.SHEARS || iblockdata.get(HALF) != EnumTallPlantHalf.LOWER || !b(world, blockposition, iblockdata, entityhuman)) {
/* 111 */       super.a(world, entityhuman, blockposition, iblockdata, tileentity, itemstack);
/*     */     }
/*     */   }
/*     */   
/*     */   public void a(World world, BlockPosition blockposition, IBlockData iblockdata, EntityHuman entityhuman) {
/* 116 */     if (iblockdata.get(HALF) == EnumTallPlantHalf.UPPER) {
/* 117 */       if (world.getType(blockposition.down()).getBlock() == this) {
/* 118 */         if (entityhuman.abilities.canInstantlyBuild) {
/* 119 */           world.setAir(blockposition.down());
/*     */         } else {
/* 121 */           IBlockData iblockdata1 = world.getType(blockposition.down());
/* 122 */           EnumTallFlowerVariants blocktallplant_enumtallflowervariants = iblockdata1.<EnumTallFlowerVariants>get(VARIANT);
/*     */           
/* 124 */           if (blocktallplant_enumtallflowervariants != EnumTallFlowerVariants.FERN && blocktallplant_enumtallflowervariants != EnumTallFlowerVariants.GRASS) {
/* 125 */             world.setAir(blockposition.down(), true);
/* 126 */           } else if (world.isClientSide) {
/* 127 */             world.setAir(blockposition.down());
/* 128 */           } else if (!entityhuman.getItemInMainHand().isEmpty() && entityhuman.getItemInMainHand().getItem() == Items.SHEARS) {
/* 129 */             b(world, blockposition, iblockdata1, entityhuman);
/* 130 */             world.setAir(blockposition.down());
/*     */           } else {
/* 132 */             world.setAir(blockposition.down(), true);
/*     */           } 
/*     */         } 
/*     */       }
/* 136 */     } else if (world.getType(blockposition.up()).getBlock() == this) {
/* 137 */       world.setTypeAndData(blockposition.up(), Blocks.AIR.getBlockData(), 2);
/*     */     } 
/*     */     
/* 140 */     super.a(world, blockposition, iblockdata, entityhuman);
/*     */   }
/*     */   
/*     */   private boolean b(World world, BlockPosition blockposition, IBlockData iblockdata, EntityHuman entityhuman) {
/* 144 */     EnumTallFlowerVariants blocktallplant_enumtallflowervariants = iblockdata.<EnumTallFlowerVariants>get(VARIANT);
/*     */     
/* 146 */     if (blocktallplant_enumtallflowervariants != EnumTallFlowerVariants.FERN && blocktallplant_enumtallflowervariants != EnumTallFlowerVariants.GRASS) {
/* 147 */       return false;
/*     */     }
/* 149 */     entityhuman.b(StatisticList.a(this));
/* 150 */     int i = ((blocktallplant_enumtallflowervariants == EnumTallFlowerVariants.GRASS) ? BlockLongGrass.EnumTallGrassType.GRASS : BlockLongGrass.EnumTallGrassType.FERN).a();
/*     */     
/* 152 */     a(world, blockposition, new ItemStack(Blocks.TALLGRASS, 2, i));
/* 153 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack a(World world, BlockPosition blockposition, IBlockData iblockdata) {
/* 158 */     return new ItemStack(this, 1, a(world, blockposition, iblockdata).a());
/*     */   }
/*     */   
/*     */   public boolean a(World world, BlockPosition blockposition, IBlockData iblockdata, boolean flag) {
/* 162 */     EnumTallFlowerVariants blocktallplant_enumtallflowervariants = a(world, blockposition, iblockdata);
/*     */     
/* 164 */     return (blocktallplant_enumtallflowervariants != EnumTallFlowerVariants.GRASS && blocktallplant_enumtallflowervariants != EnumTallFlowerVariants.FERN);
/*     */   }
/*     */   
/*     */   public boolean a(World world, Random random, BlockPosition blockposition, IBlockData iblockdata) {
/* 168 */     return true;
/*     */   }
/*     */   
/*     */   public void b(World world, Random random, BlockPosition blockposition, IBlockData iblockdata) {
/* 172 */     a(world, blockposition, new ItemStack(this, 1, a(world, blockposition, iblockdata).a()));
/*     */   }
/*     */   
/*     */   public IBlockData fromLegacyData(int i) {
/* 176 */     return ((i & 0x8) > 0) ? getBlockData().<EnumTallPlantHalf, EnumTallPlantHalf>set(HALF, EnumTallPlantHalf.UPPER) : getBlockData().<EnumTallPlantHalf, EnumTallPlantHalf>set(HALF, EnumTallPlantHalf.LOWER).<EnumTallFlowerVariants, EnumTallFlowerVariants>set(VARIANT, EnumTallFlowerVariants.a(i & 0x7));
/*     */   }
/*     */   
/*     */   public IBlockData updateState(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition) {
/* 180 */     if (iblockdata.get(HALF) == EnumTallPlantHalf.UPPER) {
/* 181 */       IBlockData iblockdata1 = iblockaccess.getType(blockposition.down());
/*     */       
/* 183 */       if (iblockdata1.getBlock() == this) {
/* 184 */         iblockdata = iblockdata.set(VARIANT, iblockdata1.<EnumTallFlowerVariants>get(VARIANT));
/*     */       }
/*     */     } 
/*     */     
/* 188 */     return iblockdata;
/*     */   }
/*     */   
/*     */   public int toLegacyData(IBlockData iblockdata) {
/* 192 */     return (iblockdata.get(HALF) == EnumTallPlantHalf.UPPER) ? (0x8 | ((EnumDirection)iblockdata.<EnumDirection>get(d)).get2DRotationValue()) : ((EnumTallFlowerVariants)iblockdata.<EnumTallFlowerVariants>get(VARIANT)).a();
/*     */   }
/*     */   
/*     */   protected BlockStateList getStateList() {
/* 196 */     return new BlockStateList(this, (IBlockState<?>[])new IBlockState[] { HALF, VARIANT, d });
/*     */   }
/*     */   
/*     */   public Block.EnumRandomOffset u() {
/* 200 */     return Block.EnumRandomOffset.XZ;
/*     */   }
/*     */   
/*     */   public enum EnumTallPlantHalf
/*     */     implements INamable {
/* 205 */     UPPER, LOWER;
/*     */ 
/*     */ 
/*     */     
/*     */     public String toString() {
/* 210 */       return getName();
/*     */     }
/*     */     
/*     */     public String getName() {
/* 214 */       return (this == UPPER) ? "upper" : "lower";
/*     */     }
/*     */   }
/*     */   
/*     */   public enum EnumTallFlowerVariants
/*     */     implements INamable {
/* 220 */     SUNFLOWER(0, "sunflower"), SYRINGA(1, "syringa"), GRASS(2, "double_grass", "grass"), FERN(3, "double_fern", "fern"), ROSE(4, "double_rose", "rose"), PAEONIA(5, "paeonia");
/*     */     
/* 222 */     private static final EnumTallFlowerVariants[] g = new EnumTallFlowerVariants[(values()).length];
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private final int h;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private final String i;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private final String j;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     static {
/* 262 */       EnumTallFlowerVariants[] ablocktallplant_enumtallflowervariants = values();
/* 263 */       int i = ablocktallplant_enumtallflowervariants.length;
/*     */       
/* 265 */       for (int j = 0; j < i; j++) {
/* 266 */         EnumTallFlowerVariants blocktallplant_enumtallflowervariants = ablocktallplant_enumtallflowervariants[j];
/*     */         
/* 268 */         g[blocktallplant_enumtallflowervariants.a()] = blocktallplant_enumtallflowervariants;
/*     */       } 
/*     */     }
/*     */     
/*     */     EnumTallFlowerVariants(int i, String s, String s1) {
/*     */       this.h = i;
/*     */       this.i = s;
/*     */       this.j = s1;
/*     */     }
/*     */     
/*     */     public int a() {
/*     */       return this.h;
/*     */     }
/*     */     
/*     */     public String toString() {
/*     */       return this.i;
/*     */     }
/*     */     
/*     */     public static EnumTallFlowerVariants a(int i) {
/*     */       if (i < 0 || i >= g.length)
/*     */         i = 0; 
/*     */       return g[i];
/*     */     }
/*     */     
/*     */     public String getName() {
/*     */       return this.i;
/*     */     }
/*     */     
/*     */     public String c() {
/*     */       return this.j;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BlockTallPlant.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */