/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.Random;
/*     */ import org.bukkit.TreeType;
/*     */ import org.bukkit.block.BlockState;
/*     */ import org.bukkit.craftbukkit.v1_12_R1.CraftWorld;
/*     */ import org.bukkit.craftbukkit.v1_12_R1.util.CraftMagicNumbers;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.block.BlockSpreadEvent;
/*     */ 
/*     */ public class BlockMushroom
/*     */   extends BlockPlant implements IBlockFragilePlantElement {
/*  14 */   protected static final AxisAlignedBB a = new AxisAlignedBB(0.30000001192092896D, 0.0D, 0.30000001192092896D, 0.699999988079071D, 0.4000000059604645D, 0.699999988079071D);
/*     */   
/*     */   protected BlockMushroom() {
/*  17 */     a(true);
/*     */   }
/*     */   
/*     */   public AxisAlignedBB b(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition) {
/*  21 */     return a;
/*     */   }
/*     */   
/*     */   public void b(World world, BlockPosition blockposition, IBlockData iblockdata, Random random) {
/*  25 */     int sourceX = blockposition.getX(), sourceY = blockposition.getY(), sourceZ = blockposition.getZ();
/*  26 */     if (random.nextInt(Math.max(1, (int)(100.0F / world.spigotConfig.mushroomModifier) * 25)) == 0) {
/*  27 */       int i = 5;
/*     */       
/*  29 */       Iterator<BlockPosition.MutableBlockPosition> iterator = BlockPosition.b(blockposition.a(-4, -1, -4), blockposition.a(4, 1, 4)).iterator();
/*     */       
/*  31 */       while (iterator.hasNext()) {
/*  32 */         BlockPosition blockposition1 = iterator.next();
/*     */ 
/*     */         
/*  35 */         i--;
/*  36 */         if (world.getType(blockposition1).getBlock() == this && i <= 0) {
/*     */           return;
/*     */         }
/*     */       } 
/*     */ 
/*     */       
/*  42 */       BlockPosition blockposition2 = blockposition.a(random.nextInt(3) - 1, random.nextInt(2) - random.nextInt(2), random.nextInt(3) - 1);
/*     */       
/*  44 */       for (int j = 0; j < 4; j++) {
/*  45 */         if (world.isEmpty(blockposition2) && f(world, blockposition2, getBlockData())) {
/*  46 */           blockposition = blockposition2;
/*     */         }
/*     */         
/*  49 */         blockposition2 = blockposition.a(random.nextInt(3) - 1, random.nextInt(2) - random.nextInt(2), random.nextInt(3) - 1);
/*     */       } 
/*     */       
/*  52 */       if (world.isEmpty(blockposition2) && f(world, blockposition2, getBlockData())) {
/*     */ 
/*     */         
/*  55 */         CraftWorld craftWorld = world.getWorld();
/*  56 */         BlockState blockState = craftWorld.getBlockAt(blockposition2.getX(), blockposition2.getY(), blockposition2.getZ()).getState();
/*  57 */         blockState.setType(CraftMagicNumbers.getMaterial(this));
/*     */         
/*  59 */         BlockSpreadEvent event = new BlockSpreadEvent(blockState.getBlock(), craftWorld.getBlockAt(sourceX, sourceY, sourceZ), blockState);
/*  60 */         world.getServer().getPluginManager().callEvent((Event)event);
/*     */         
/*  62 */         if (!event.isCancelled()) {
/*  63 */           blockState.update(true);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canPlace(World world, BlockPosition blockposition) {
/*  72 */     return (super.canPlace(world, blockposition) && f(world, blockposition, getBlockData()));
/*     */   }
/*     */   
/*     */   protected boolean x(IBlockData iblockdata) {
/*  76 */     return iblockdata.b();
/*     */   }
/*     */   
/*     */   public boolean f(World world, BlockPosition blockposition, IBlockData iblockdata) {
/*  80 */     if (blockposition.getY() >= 0 && blockposition.getY() < 256) {
/*  81 */       IBlockData iblockdata1 = world.getType(blockposition.down());
/*     */       
/*  83 */       return (iblockdata1.getBlock() == Blocks.MYCELIUM) ? true : ((iblockdata1.getBlock() == Blocks.DIRT && iblockdata1.get(BlockDirt.VARIANT) == BlockDirt.EnumDirtVariant.PODZOL) ? true : ((world.j(blockposition) < 13 && x(iblockdata1))));
/*     */     } 
/*  85 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean c(World world, BlockPosition blockposition, IBlockData iblockdata, Random random) {
/*  90 */     world.setAir(blockposition);
/*  91 */     WorldGenHugeMushroom worldgenhugemushroom = null;
/*     */     
/*  93 */     if (this == Blocks.BROWN_MUSHROOM) {
/*  94 */       BlockSapling.treeType = TreeType.BROWN_MUSHROOM;
/*  95 */       worldgenhugemushroom = new WorldGenHugeMushroom(Blocks.BROWN_MUSHROOM_BLOCK);
/*  96 */     } else if (this == Blocks.RED_MUSHROOM) {
/*  97 */       BlockSapling.treeType = TreeType.RED_MUSHROOM;
/*  98 */       worldgenhugemushroom = new WorldGenHugeMushroom(Blocks.RED_MUSHROOM_BLOCK);
/*     */     } 
/*     */     
/* 101 */     if (worldgenhugemushroom != null && worldgenhugemushroom.generate(world, random, blockposition)) {
/* 102 */       return true;
/*     */     }
/* 104 */     world.setTypeAndData(blockposition, iblockdata, 3);
/* 105 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(World world, BlockPosition blockposition, IBlockData iblockdata, boolean flag) {
/* 110 */     return true;
/*     */   }
/*     */   
/*     */   public boolean a(World world, Random random, BlockPosition blockposition, IBlockData iblockdata) {
/* 114 */     return (random.nextFloat() < 0.4D);
/*     */   }
/*     */   
/*     */   public void b(World world, Random random, BlockPosition blockposition, IBlockData iblockdata) {
/* 118 */     c(world, blockposition, iblockdata, random);
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BlockMushroom.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */