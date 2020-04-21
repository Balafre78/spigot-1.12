/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import java.util.List;
/*    */ import javax.annotation.Nullable;
/*    */ import org.bukkit.craftbukkit.v1_12_R1.event.CraftEventFactory;
/*    */ 
/*    */ public class BlockWaterLily extends BlockPlant {
/*  8 */   protected static final AxisAlignedBB a = new AxisAlignedBB(0.0625D, 0.0D, 0.0625D, 0.9375D, 0.09375D, 0.9375D);
/*    */   
/*    */   protected BlockWaterLily() {
/* 11 */     a(CreativeModeTab.c);
/*    */   }
/*    */   
/*    */   public void a(IBlockData iblockdata, World world, BlockPosition blockposition, AxisAlignedBB axisalignedbb, List<AxisAlignedBB> list, @Nullable Entity entity, boolean flag) {
/* 15 */     if (!(entity instanceof EntityBoat)) {
/* 16 */       a(blockposition, axisalignedbb, list, a);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(World world, BlockPosition blockposition, IBlockData iblockdata, Entity entity) {
/* 22 */     super.a(world, blockposition, iblockdata, entity);
/* 23 */     if (entity instanceof EntityBoat && !CraftEventFactory.callEntityChangeBlockEvent(entity, blockposition, Blocks.AIR, 0).isCancelled()) {
/* 24 */       world.setAir(new BlockPosition(blockposition), true);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public AxisAlignedBB b(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition) {
/* 30 */     return a;
/*    */   }
/*    */   
/*    */   protected boolean x(IBlockData iblockdata) {
/* 34 */     return !(iblockdata.getBlock() != Blocks.WATER && iblockdata.getMaterial() != Material.ICE);
/*    */   }
/*    */   
/*    */   public boolean f(World world, BlockPosition blockposition, IBlockData iblockdata) {
/* 38 */     if (blockposition.getY() >= 0 && blockposition.getY() < 256) {
/* 39 */       IBlockData iblockdata1 = world.getType(blockposition.down());
/* 40 */       Material material = iblockdata1.getMaterial();
/*    */       
/* 42 */       return !((material != Material.WATER || ((Integer)iblockdata1.<Integer>get(BlockFluids.LEVEL)).intValue() != 0) && material != Material.ICE);
/*    */     } 
/* 44 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public int toLegacyData(IBlockData iblockdata) {
/* 49 */     return 0;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BlockWaterLily.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */