/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import java.util.Random;
/*    */ import org.bukkit.block.BlockState;
/*    */ import org.bukkit.craftbukkit.v1_12_R1.CraftWorld;
/*    */ import org.bukkit.craftbukkit.v1_12_R1.util.CraftMagicNumbers;
/*    */ import org.bukkit.event.Event;
/*    */ import org.bukkit.event.block.BlockFadeEvent;
/*    */ import org.bukkit.event.block.BlockSpreadEvent;
/*    */ 
/*    */ public class BlockMycel
/*    */   extends Block
/*    */ {
/* 14 */   public static final BlockStateBoolean SNOWY = BlockStateBoolean.of("snowy");
/*    */   
/*    */   protected BlockMycel() {
/* 17 */     super(Material.GRASS, MaterialMapColor.A);
/* 18 */     w(this.blockStateList.getBlockData().set(SNOWY, Boolean.valueOf(false)));
/* 19 */     a(true);
/* 20 */     a(CreativeModeTab.b);
/*    */   }
/*    */   
/*    */   public IBlockData updateState(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition) {
/* 24 */     Block block = iblockaccess.getType(blockposition.up()).getBlock();
/*    */     
/* 26 */     return iblockdata.set(SNOWY, Boolean.valueOf(!(block != Blocks.SNOW && block != Blocks.SNOW_LAYER)));
/*    */   }
/*    */   
/*    */   public void b(World world, BlockPosition blockposition, IBlockData iblockdata, Random random) {
/* 30 */     if (!world.isClientSide) {
/* 31 */       if (world.getLightLevel(blockposition.up()) < 4 && world.getType(blockposition.up()).c() > 2) {
/*    */ 
/*    */         
/* 34 */         CraftWorld craftWorld = world.getWorld();
/* 35 */         BlockState blockState = craftWorld.getBlockAt(blockposition.getX(), blockposition.getY(), blockposition.getZ()).getState();
/* 36 */         blockState.setType(CraftMagicNumbers.getMaterial(Blocks.DIRT));
/*    */         
/* 38 */         BlockFadeEvent event = new BlockFadeEvent(blockState.getBlock(), blockState);
/* 39 */         world.getServer().getPluginManager().callEvent((Event)event);
/*    */         
/* 41 */         if (!event.isCancelled()) {
/* 42 */           blockState.update(true);
/*    */         
/*    */         }
/*    */       }
/* 46 */       else if (world.getLightLevel(blockposition.up()) >= 9) {
/* 47 */         for (int i = 0; i < 4; i++) {
/* 48 */           BlockPosition blockposition1 = blockposition.a(random.nextInt(3) - 1, random.nextInt(5) - 3, random.nextInt(3) - 1);
/* 49 */           IBlockData iblockdata1 = world.getType(blockposition1);
/* 50 */           IBlockData iblockdata2 = world.getType(blockposition1.up());
/*    */           
/* 52 */           if (iblockdata1.getBlock() == Blocks.DIRT && iblockdata1.get(BlockDirt.VARIANT) == BlockDirt.EnumDirtVariant.DIRT && world.getLightLevel(blockposition1.up()) >= 4 && iblockdata2.c() <= 2) {
/*    */ 
/*    */             
/* 55 */             CraftWorld craftWorld = world.getWorld();
/* 56 */             BlockState blockState = craftWorld.getBlockAt(blockposition1.getX(), blockposition1.getY(), blockposition1.getZ()).getState();
/* 57 */             blockState.setType(CraftMagicNumbers.getMaterial(this));
/*    */             
/* 59 */             BlockSpreadEvent event = new BlockSpreadEvent(blockState.getBlock(), craftWorld.getBlockAt(blockposition.getX(), blockposition.getY(), blockposition.getZ()), blockState);
/* 60 */             world.getServer().getPluginManager().callEvent((Event)event);
/*    */             
/* 62 */             if (!event.isCancelled()) {
/* 63 */               blockState.update(true);
/*    */             }
/*    */           } 
/*    */         } 
/*    */       } 
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Item getDropType(IBlockData iblockdata, Random random, int i) {
/* 75 */     return Blocks.DIRT.getDropType(Blocks.DIRT.getBlockData().set(BlockDirt.VARIANT, BlockDirt.EnumDirtVariant.DIRT), random, i);
/*    */   }
/*    */   
/*    */   public int toLegacyData(IBlockData iblockdata) {
/* 79 */     return 0;
/*    */   }
/*    */   
/*    */   protected BlockStateList getStateList() {
/* 83 */     return new BlockStateList(this, (IBlockState<?>[])new IBlockState[] { SNOWY });
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BlockMycel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */