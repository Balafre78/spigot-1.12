/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import java.util.Random;
/*    */ import javax.annotation.Nullable;
/*    */ import org.bukkit.craftbukkit.v1_12_R1.event.CraftEventFactory;
/*    */ 
/*    */ public class BlockPlant extends Block {
/*  8 */   protected static final AxisAlignedBB b = new AxisAlignedBB(0.30000001192092896D, 0.0D, 0.30000001192092896D, 0.699999988079071D, 0.6000000238418579D, 0.699999988079071D);
/*    */   
/*    */   protected BlockPlant() {
/* 11 */     this(Material.PLANT);
/*    */   }
/*    */   
/*    */   protected BlockPlant(Material material) {
/* 15 */     this(material, material.r());
/*    */   }
/*    */   
/*    */   protected BlockPlant(Material material, MaterialMapColor materialmapcolor) {
/* 19 */     super(material, materialmapcolor);
/* 20 */     a(true);
/* 21 */     a(CreativeModeTab.c);
/*    */   }
/*    */   
/*    */   public boolean canPlace(World world, BlockPosition blockposition) {
/* 25 */     return (super.canPlace(world, blockposition) && x(world.getType(blockposition.down())));
/*    */   }
/*    */   
/*    */   protected boolean x(IBlockData iblockdata) {
/* 29 */     return !(iblockdata.getBlock() != Blocks.GRASS && iblockdata.getBlock() != Blocks.DIRT && iblockdata.getBlock() != Blocks.FARMLAND);
/*    */   }
/*    */   
/*    */   public void a(IBlockData iblockdata, World world, BlockPosition blockposition, Block block, BlockPosition blockposition1) {
/* 33 */     super.a(iblockdata, world, blockposition, block, blockposition1);
/* 34 */     e(world, blockposition, iblockdata);
/*    */   }
/*    */   
/*    */   public void b(World world, BlockPosition blockposition, IBlockData iblockdata, Random random) {
/* 38 */     e(world, blockposition, iblockdata);
/*    */   }
/*    */   
/*    */   protected void e(World world, BlockPosition blockposition, IBlockData iblockdata) {
/* 42 */     if (!f(world, blockposition, iblockdata)) {
/*    */       
/* 44 */       if (CraftEventFactory.callBlockPhysicsEvent(world, blockposition).isCancelled()) {
/*    */         return;
/*    */       }
/*    */       
/* 48 */       b(world, blockposition, iblockdata, 0);
/* 49 */       world.setTypeAndData(blockposition, Blocks.AIR.getBlockData(), 3);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean f(World world, BlockPosition blockposition, IBlockData iblockdata) {
/* 55 */     return x(world.getType(blockposition.down()));
/*    */   }
/*    */   
/*    */   public AxisAlignedBB b(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition) {
/* 59 */     return b;
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   public AxisAlignedBB a(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition) {
/* 64 */     return k;
/*    */   }
/*    */   
/*    */   public boolean b(IBlockData iblockdata) {
/* 68 */     return false;
/*    */   }
/*    */   
/*    */   public boolean c(IBlockData iblockdata) {
/* 72 */     return false;
/*    */   }
/*    */   
/*    */   public EnumBlockFaceShape a(IBlockAccess iblockaccess, IBlockData iblockdata, BlockPosition blockposition, EnumDirection enumdirection) {
/* 76 */     return EnumBlockFaceShape.UNDEFINED;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BlockPlant.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */