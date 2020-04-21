/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import java.util.Random;
/*    */ import javax.annotation.Nullable;
/*    */ import org.bukkit.craftbukkit.v1_12_R1.event.CraftEventFactory;
/*    */ 
/*    */ public class BlockIce extends BlockHalfTransparent {
/*    */   public BlockIce() {
/*  9 */     super(Material.ICE, false);
/* 10 */     this.frictionFactor = 0.98F;
/* 11 */     a(true);
/* 12 */     a(CreativeModeTab.b);
/*    */   }
/*    */   
/*    */   public void a(World world, EntityHuman entityhuman, BlockPosition blockposition, IBlockData iblockdata, @Nullable TileEntity tileentity, ItemStack itemstack) {
/* 16 */     entityhuman.b(StatisticList.a(this));
/* 17 */     entityhuman.applyExhaustion(0.005F);
/* 18 */     if (n() && EnchantmentManager.getEnchantmentLevel(Enchantments.SILK_TOUCH, itemstack) > 0) {
/* 19 */       a(world, blockposition, u(iblockdata));
/*    */     } else {
/* 21 */       if (world.worldProvider.l()) {
/* 22 */         world.setAir(blockposition);
/*    */         
/*    */         return;
/*    */       } 
/* 26 */       int i = EnchantmentManager.getEnchantmentLevel(Enchantments.LOOT_BONUS_BLOCKS, itemstack);
/*    */       
/* 28 */       b(world, blockposition, iblockdata, i);
/* 29 */       Material material = world.getType(blockposition.down()).getMaterial();
/*    */       
/* 31 */       if (material.isSolid() || material.isLiquid()) {
/* 32 */         world.setTypeUpdate(blockposition, Blocks.FLOWING_WATER.getBlockData());
/*    */       }
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public int a(Random random) {
/* 39 */     return 0;
/*    */   }
/*    */   
/*    */   public void b(World world, BlockPosition blockposition, IBlockData iblockdata, Random random) {
/* 43 */     if (world.getBrightness(EnumSkyBlock.BLOCK, blockposition) > 11 - getBlockData().c()) {
/* 44 */       b(world, blockposition);
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void b(World world, BlockPosition blockposition) {
/* 51 */     if (CraftEventFactory.callBlockFadeEvent(world.getWorld().getBlockAt(blockposition.getX(), blockposition.getY(), blockposition.getZ()), world.worldProvider.l() ? Blocks.AIR : Blocks.WATER).isCancelled()) {
/*    */       return;
/*    */     }
/*    */     
/* 55 */     if (world.worldProvider.l()) {
/* 56 */       world.setAir(blockposition);
/*    */     } else {
/* 58 */       b(world, blockposition, world.getType(blockposition), 0);
/* 59 */       world.setTypeUpdate(blockposition, Blocks.WATER.getBlockData());
/* 60 */       world.a(blockposition, Blocks.WATER, blockposition);
/*    */     } 
/*    */   }
/*    */   
/*    */   public EnumPistonReaction h(IBlockData iblockdata) {
/* 65 */     return EnumPistonReaction.NORMAL;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BlockIce.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */