/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import java.util.Random;
/*    */ import org.bukkit.craftbukkit.v1_12_R1.event.CraftEventFactory;
/*    */ 
/*    */ public class BlockSnowBlock extends Block {
/*    */   protected BlockSnowBlock() {
/*  8 */     super(Material.SNOW_BLOCK);
/*  9 */     a(true);
/* 10 */     a(CreativeModeTab.b);
/*    */   }
/*    */   
/*    */   public Item getDropType(IBlockData iblockdata, Random random, int i) {
/* 14 */     return Items.SNOWBALL;
/*    */   }
/*    */   
/*    */   public int a(Random random) {
/* 18 */     return 4;
/*    */   }
/*    */   
/*    */   public void b(World world, BlockPosition blockposition, IBlockData iblockdata, Random random) {
/* 22 */     if (world.getBrightness(EnumSkyBlock.BLOCK, blockposition) > 11) {
/*    */       
/* 24 */       if (CraftEventFactory.callBlockFadeEvent(world.getWorld().getBlockAt(blockposition.getX(), blockposition.getY(), blockposition.getZ()), Blocks.AIR).isCancelled()) {
/*    */         return;
/*    */       }
/*    */       
/* 28 */       b(world, blockposition, world.getType(blockposition), 0);
/* 29 */       world.setAir(blockposition);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BlockSnowBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */