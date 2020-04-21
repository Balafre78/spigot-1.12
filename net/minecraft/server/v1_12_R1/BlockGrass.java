/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import java.util.Random;
/*     */ import org.bukkit.block.BlockState;
/*     */ import org.bukkit.craftbukkit.v1_12_R1.CraftWorld;
/*     */ import org.bukkit.craftbukkit.v1_12_R1.event.CraftEventFactory;
/*     */ import org.bukkit.craftbukkit.v1_12_R1.util.CraftMagicNumbers;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.block.BlockFadeEvent;
/*     */ import org.bukkit.event.block.BlockSpreadEvent;
/*     */ 
/*     */ public class BlockGrass
/*     */   extends Block
/*     */   implements IBlockFragilePlantElement {
/*  15 */   public static final BlockStateBoolean SNOWY = BlockStateBoolean.of("snowy");
/*     */   
/*     */   protected BlockGrass() {
/*  18 */     super(Material.GRASS);
/*  19 */     w(this.blockStateList.getBlockData().set(SNOWY, Boolean.valueOf(false)));
/*  20 */     a(true);
/*  21 */     a(CreativeModeTab.b);
/*     */   }
/*     */   
/*     */   public IBlockData updateState(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition) {
/*  25 */     Block block = iblockaccess.getType(blockposition.up()).getBlock();
/*     */     
/*  27 */     return iblockdata.set(SNOWY, Boolean.valueOf(!(block != Blocks.SNOW && block != Blocks.SNOW_LAYER)));
/*     */   }
/*     */   
/*     */   public void b(World world, BlockPosition blockposition, IBlockData iblockdata, Random random) {
/*  31 */     if (!world.isClientSide) {
/*  32 */       if (world.getLightLevel(blockposition.up()) < 4 && world.getType(blockposition.up()).c() > 2) {
/*     */ 
/*     */         
/*  35 */         CraftWorld craftWorld = world.getWorld();
/*  36 */         BlockState blockState = craftWorld.getBlockAt(blockposition.getX(), blockposition.getY(), blockposition.getZ()).getState();
/*  37 */         blockState.setType(CraftMagicNumbers.getMaterial(Blocks.DIRT));
/*     */         
/*  39 */         BlockFadeEvent event = new BlockFadeEvent(blockState.getBlock(), blockState);
/*  40 */         world.getServer().getPluginManager().callEvent((Event)event);
/*     */         
/*  42 */         if (!event.isCancelled()) {
/*  43 */           blockState.update(true);
/*     */         
/*     */         }
/*     */       }
/*  47 */       else if (world.getLightLevel(blockposition.up()) >= 9) {
/*  48 */         for (int i = 0; i < 4; i++) {
/*  49 */           BlockPosition blockposition1 = blockposition.a(random.nextInt(3) - 1, random.nextInt(5) - 3, random.nextInt(3) - 1);
/*     */           
/*  51 */           if (blockposition1.getY() >= 0 && blockposition1.getY() < 256 && !world.isLoaded(blockposition1)) {
/*     */             return;
/*     */           }
/*     */           
/*  55 */           IBlockData iblockdata1 = world.getType(blockposition1.up());
/*  56 */           IBlockData iblockdata2 = world.getType(blockposition1);
/*     */           
/*  58 */           if (iblockdata2.getBlock() == Blocks.DIRT && iblockdata2.get(BlockDirt.VARIANT) == BlockDirt.EnumDirtVariant.DIRT && world.getLightLevel(blockposition1.up()) >= 4 && iblockdata1.c() <= 2) {
/*     */ 
/*     */             
/*  61 */             CraftWorld craftWorld = world.getWorld();
/*  62 */             BlockState blockState = craftWorld.getBlockAt(blockposition1.getX(), blockposition1.getY(), blockposition1.getZ()).getState();
/*  63 */             blockState.setType(CraftMagicNumbers.getMaterial(Blocks.GRASS));
/*     */             
/*  65 */             BlockSpreadEvent event = new BlockSpreadEvent(blockState.getBlock(), craftWorld.getBlockAt(blockposition.getX(), blockposition.getY(), blockposition.getZ()), blockState);
/*  66 */             world.getServer().getPluginManager().callEvent((Event)event);
/*     */             
/*  68 */             if (!event.isCancelled()) {
/*  69 */               blockState.update(true);
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Item getDropType(IBlockData iblockdata, Random random, int i) {
/*  81 */     return Blocks.DIRT.getDropType(Blocks.DIRT.getBlockData().set(BlockDirt.VARIANT, BlockDirt.EnumDirtVariant.DIRT), random, i);
/*     */   }
/*     */   
/*     */   public boolean a(World world, BlockPosition blockposition, IBlockData iblockdata, boolean flag) {
/*  85 */     return true;
/*     */   }
/*     */   
/*     */   public boolean a(World world, Random random, BlockPosition blockposition, IBlockData iblockdata) {
/*  89 */     return true;
/*     */   }
/*     */   
/*     */   public void b(World world, Random random, BlockPosition blockposition, IBlockData iblockdata) {
/*  93 */     BlockPosition blockposition1 = blockposition.up();
/*  94 */     int i = 0;
/*     */     
/*  96 */     while (i < 128) {
/*  97 */       BlockPosition blockposition2 = blockposition1;
/*  98 */       int j = 0;
/*     */ 
/*     */       
/* 101 */       while (j < i / 16) {
/* 102 */         blockposition2 = blockposition2.a(random.nextInt(3) - 1, (random.nextInt(3) - 1) * random.nextInt(3) / 2, random.nextInt(3) - 1);
/* 103 */         if (world.getType(blockposition2.down()).getBlock() == Blocks.GRASS) { if (!world.getType(blockposition2).l()) {
/* 104 */             j++; continue;
/*     */           }  // Byte code: goto -> 304 }
/*     */          // Byte code: goto -> 304
/* 107 */       }  if ((world.getType(blockposition2).getBlock()).material == Material.AIR) {
/* 108 */         if (random.nextInt(8) == 0) {
/* 109 */           BlockFlowers.EnumFlowerVarient blockflowers_enumflowervarient = world.getBiome(blockposition2).a(random, blockposition2);
/* 110 */           BlockFlowers blockflowers = blockflowers_enumflowervarient.a().a();
/* 111 */           IBlockData iblockdata1 = blockflowers.getBlockData().set(blockflowers.g(), blockflowers_enumflowervarient);
/*     */           
/* 113 */           if (blockflowers.f(world, blockposition2, iblockdata1))
/*     */           {
/* 115 */             CraftEventFactory.handleBlockGrowEvent(world, blockposition2.getX(), blockposition2.getY(), blockposition2.getZ(), iblockdata1.getBlock(), iblockdata1.getBlock().toLegacyData(iblockdata1));
/*     */           }
/*     */         } else {
/* 118 */           IBlockData iblockdata2 = Blocks.TALLGRASS.getBlockData().set(BlockLongGrass.TYPE, BlockLongGrass.EnumTallGrassType.GRASS);
/*     */           
/* 120 */           if (Blocks.TALLGRASS.f(world, blockposition2, iblockdata2))
/*     */           {
/* 122 */             CraftEventFactory.handleBlockGrowEvent(world, blockposition2.getX(), blockposition2.getY(), blockposition2.getZ(), iblockdata2.getBlock(), iblockdata2.getBlock().toLegacyData(iblockdata2));
/*     */           }
/*     */         } 
/*     */       }
/*     */       
/* 127 */       i++;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int toLegacyData(IBlockData iblockdata) {
/* 135 */     return 0;
/*     */   }
/*     */   
/*     */   protected BlockStateList getStateList() {
/* 139 */     return new BlockStateList(this, (IBlockState<?>[])new IBlockState[] { SNOWY });
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BlockGrass.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */