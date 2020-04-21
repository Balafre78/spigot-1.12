/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.TreeType;
/*     */ import org.bukkit.World;
/*     */ import org.bukkit.block.BlockState;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.world.StructureGrowEvent;
/*     */ 
/*     */ public class BlockSapling
/*     */   extends BlockPlant
/*     */   implements IBlockFragilePlantElement {
/*  16 */   public static final BlockStateEnum<BlockWood.EnumLogVariant> TYPE = BlockStateEnum.of("type", BlockWood.EnumLogVariant.class);
/*  17 */   public static final BlockStateInteger STAGE = BlockStateInteger.of("stage", 0, 1);
/*  18 */   protected static final AxisAlignedBB d = new AxisAlignedBB(0.09999999403953552D, 0.0D, 0.09999999403953552D, 0.8999999761581421D, 0.800000011920929D, 0.8999999761581421D);
/*     */   public static TreeType treeType;
/*     */   
/*     */   protected BlockSapling() {
/*  22 */     w(this.blockStateList.getBlockData().<BlockWood.EnumLogVariant, BlockWood.EnumLogVariant>set(TYPE, BlockWood.EnumLogVariant.OAK).set(STAGE, Integer.valueOf(0)));
/*  23 */     a(CreativeModeTab.c);
/*     */   }
/*     */   
/*     */   public AxisAlignedBB b(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition) {
/*  27 */     return d;
/*     */   }
/*     */   
/*     */   public String getName() {
/*  31 */     return LocaleI18n.get(String.valueOf(a()) + "." + BlockWood.EnumLogVariant.OAK.d() + ".name");
/*     */   }
/*     */   
/*     */   public void b(World world, BlockPosition blockposition, IBlockData iblockdata, Random random) {
/*  35 */     if (!world.isClientSide) {
/*  36 */       super.b(world, blockposition, iblockdata, random);
/*  37 */       if (world.getLightLevel(blockposition.up()) >= 9 && random.nextInt(Math.max(2, (int)(100.0F / world.spigotConfig.saplingModifier * 7.0F + 0.5F))) == 0) {
/*     */         
/*  39 */         world.captureTreeGeneration = true;
/*     */         
/*  41 */         grow(world, blockposition, iblockdata, random);
/*     */         
/*  43 */         world.captureTreeGeneration = false;
/*  44 */         if (world.capturedBlockStates.size() > 0) {
/*  45 */           TreeType treeType = BlockSapling.treeType;
/*  46 */           BlockSapling.treeType = null;
/*  47 */           Location location = new Location((World)world.getWorld(), blockposition.getX(), blockposition.getY(), blockposition.getZ());
/*  48 */           List<BlockState> blocks = (List<BlockState>)world.capturedBlockStates.clone();
/*  49 */           world.capturedBlockStates.clear();
/*  50 */           StructureGrowEvent event = null;
/*  51 */           if (treeType != null) {
/*  52 */             event = new StructureGrowEvent(location, treeType, false, null, blocks);
/*  53 */             Bukkit.getPluginManager().callEvent((Event)event);
/*     */           } 
/*  55 */           if (event == null || !event.isCancelled()) {
/*  56 */             for (BlockState blockstate : blocks) {
/*  57 */               blockstate.update(true);
/*     */             }
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void grow(World world, BlockPosition blockposition, IBlockData iblockdata, Random random) {
/*  68 */     if (((Integer)iblockdata.<Integer>get(STAGE)).intValue() == 0) {
/*  69 */       world.setTypeAndData(blockposition, iblockdata.a(STAGE), 4);
/*     */     } else {
/*  71 */       d(world, blockposition, iblockdata, random);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void d(World world, BlockPosition blockposition, IBlockData iblockdata, Random random) {
/*     */     WorldGenForest worldGenForest;
/*     */     WorldGenAcaciaTree worldGenAcaciaTree;
/*     */     Object object;
/*     */     IBlockData iblockdata2;
/*  80 */     if (random.nextInt(10) == 0) {
/*  81 */       treeType = TreeType.BIG_TREE;
/*  82 */       object = new WorldGenBigTree(true);
/*     */     } else {
/*  84 */       treeType = TreeType.TREE;
/*  85 */       object = new WorldGenTrees(true);
/*     */     } 
/*     */     
/*  88 */     int i = 0;
/*  89 */     int j = 0;
/*  90 */     boolean flag = false;
/*     */ 
/*     */     
/*  93 */     switch ((BlockWood.EnumLogVariant)iblockdata.get((IBlockState)TYPE)) {
/*     */       
/*     */       case SPRUCE:
/*  96 */         label68: for (i = 0; i >= -1; i--) {
/*  97 */           for (j = 0; j >= -1; j--) {
/*  98 */             if (a(world, blockposition, i, j, BlockWood.EnumLogVariant.SPRUCE)) {
/*  99 */               treeType = TreeType.MEGA_REDWOOD;
/* 100 */               object = new WorldGenMegaTree(false, random.nextBoolean());
/* 101 */               flag = true;
/*     */               
/*     */               break label68;
/*     */             } 
/*     */           } 
/*     */         } 
/* 107 */         if (!flag) {
/* 108 */           i = 0;
/* 109 */           j = 0;
/* 110 */           treeType = TreeType.REDWOOD;
/* 111 */           object = new WorldGenTaiga2(true);
/*     */         } 
/*     */         break;
/*     */       
/*     */       case BIRCH:
/* 116 */         treeType = TreeType.BIRCH;
/* 117 */         worldGenForest = new WorldGenForest(true, false);
/*     */         break;
/*     */       
/*     */       case JUNGLE:
/* 121 */         iblockdata1 = Blocks.LOG.getBlockData().set(BlockLog1.VARIANT, BlockWood.EnumLogVariant.JUNGLE);
/* 122 */         iblockdata2 = Blocks.LEAVES.getBlockData().<BlockWood.EnumLogVariant, BlockWood.EnumLogVariant>set(BlockLeaves1.VARIANT, BlockWood.EnumLogVariant.JUNGLE).set(BlockLeaves.CHECK_DECAY, Boolean.valueOf(false));
/*     */ 
/*     */         
/* 125 */         label69: for (i = 0; i >= -1; i--) {
/* 126 */           for (j = 0; j >= -1; j--) {
/* 127 */             if (a(world, blockposition, i, j, BlockWood.EnumLogVariant.JUNGLE)) {
/* 128 */               treeType = TreeType.JUNGLE;
/* 129 */               object = new WorldGenJungleTree(true, 10, 20, iblockdata1, iblockdata2);
/* 130 */               flag = true;
/*     */               
/*     */               break label69;
/*     */             } 
/*     */           } 
/*     */         } 
/* 136 */         if (!flag) {
/* 137 */           i = 0;
/* 138 */           j = 0;
/* 139 */           treeType = TreeType.SMALL_JUNGLE;
/* 140 */           object = new WorldGenTrees(true, 4 + random.nextInt(7), iblockdata1, iblockdata2, false);
/*     */         } 
/*     */         break;
/*     */       
/*     */       case null:
/* 145 */         treeType = TreeType.ACACIA;
/* 146 */         worldGenAcaciaTree = new WorldGenAcaciaTree(true);
/*     */         break;
/*     */ 
/*     */       
/*     */       case DARK_OAK:
/* 151 */         label70: for (i = 0; i >= -1; i--) {
/* 152 */           for (j = 0; j >= -1; j--) {
/* 153 */             if (a(world, blockposition, i, j, BlockWood.EnumLogVariant.DARK_OAK)) {
/* 154 */               treeType = TreeType.DARK_OAK;
/* 155 */               object = new WorldGenForestTree(true);
/* 156 */               flag = true;
/*     */               
/*     */               break label70;
/*     */             } 
/*     */           } 
/*     */         } 
/* 162 */         if (!flag) {
/*     */           return;
/*     */         }
/*     */         break;
/*     */     } 
/*     */ 
/*     */     
/* 169 */     IBlockData iblockdata1 = Blocks.AIR.getBlockData();
/* 170 */     if (flag) {
/* 171 */       world.setTypeAndData(blockposition.a(i, 0, j), iblockdata1, 4);
/* 172 */       world.setTypeAndData(blockposition.a(i + 1, 0, j), iblockdata1, 4);
/* 173 */       world.setTypeAndData(blockposition.a(i, 0, j + 1), iblockdata1, 4);
/* 174 */       world.setTypeAndData(blockposition.a(i + 1, 0, j + 1), iblockdata1, 4);
/*     */     } else {
/* 176 */       world.setTypeAndData(blockposition, iblockdata1, 4);
/*     */     } 
/*     */     
/* 179 */     if (!((WorldGenerator)object).generate(world, random, blockposition.a(i, 0, j))) {
/* 180 */       if (flag) {
/* 181 */         world.setTypeAndData(blockposition.a(i, 0, j), iblockdata, 4);
/* 182 */         world.setTypeAndData(blockposition.a(i + 1, 0, j), iblockdata, 4);
/* 183 */         world.setTypeAndData(blockposition.a(i, 0, j + 1), iblockdata, 4);
/* 184 */         world.setTypeAndData(blockposition.a(i + 1, 0, j + 1), iblockdata, 4);
/*     */       } else {
/* 186 */         world.setTypeAndData(blockposition, iblockdata, 4);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean a(World world, BlockPosition blockposition, int i, int j, BlockWood.EnumLogVariant blockwood_enumlogvariant) {
/* 193 */     return (a(world, blockposition.a(i, 0, j), blockwood_enumlogvariant) && a(world, blockposition.a(i + 1, 0, j), blockwood_enumlogvariant) && a(world, blockposition.a(i, 0, j + 1), blockwood_enumlogvariant) && a(world, blockposition.a(i + 1, 0, j + 1), blockwood_enumlogvariant));
/*     */   }
/*     */   
/*     */   public boolean a(World world, BlockPosition blockposition, BlockWood.EnumLogVariant blockwood_enumlogvariant) {
/* 197 */     IBlockData iblockdata = world.getType(blockposition);
/*     */     
/* 199 */     return (iblockdata.getBlock() == this && iblockdata.get(TYPE) == blockwood_enumlogvariant);
/*     */   }
/*     */   
/*     */   public int getDropData(IBlockData iblockdata) {
/* 203 */     return ((BlockWood.EnumLogVariant)iblockdata.<BlockWood.EnumLogVariant>get(TYPE)).a();
/*     */   }
/*     */   
/*     */   public boolean a(World world, BlockPosition blockposition, IBlockData iblockdata, boolean flag) {
/* 207 */     return true;
/*     */   }
/*     */   
/*     */   public boolean a(World world, Random random, BlockPosition blockposition, IBlockData iblockdata) {
/* 211 */     return (world.random.nextFloat() < 0.45D);
/*     */   }
/*     */   
/*     */   public void b(World world, Random random, BlockPosition blockposition, IBlockData iblockdata) {
/* 215 */     grow(world, blockposition, iblockdata, random);
/*     */   }
/*     */   
/*     */   public IBlockData fromLegacyData(int i) {
/* 219 */     return getBlockData().<BlockWood.EnumLogVariant, BlockWood.EnumLogVariant>set(TYPE, BlockWood.EnumLogVariant.a(i & 0x7)).set(STAGE, Integer.valueOf((i & 0x8) >> 3));
/*     */   }
/*     */   
/*     */   public int toLegacyData(IBlockData iblockdata) {
/* 223 */     byte b0 = 0;
/* 224 */     int i = b0 | ((BlockWood.EnumLogVariant)iblockdata.<BlockWood.EnumLogVariant>get(TYPE)).a();
/*     */     
/* 226 */     i |= ((Integer)iblockdata.<Integer>get(STAGE)).intValue() << 3;
/* 227 */     return i;
/*     */   }
/*     */   
/*     */   protected BlockStateList getStateList() {
/* 231 */     return new BlockStateList(this, (IBlockState<?>[])new IBlockState[] { TYPE, STAGE });
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BlockSapling.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */