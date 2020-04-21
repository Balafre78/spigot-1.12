/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import java.util.Random;
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BlockDeadBush
/*    */   extends BlockPlant
/*    */ {
/* 22 */   protected static final AxisAlignedBB a = new AxisAlignedBB(0.09999999403953552D, 0.0D, 0.09999999403953552D, 0.8999999761581421D, 0.800000011920929D, 0.8999999761581421D);
/*    */   
/*    */   protected BlockDeadBush() {
/* 25 */     super(Material.REPLACEABLE_PLANT);
/*    */   }
/*    */ 
/*    */   
/*    */   public AxisAlignedBB b(IBlockData paramIBlockData, IBlockAccess paramIBlockAccess, BlockPosition paramBlockPosition) {
/* 30 */     return a;
/*    */   }
/*    */ 
/*    */   
/*    */   public MaterialMapColor c(IBlockData paramIBlockData, IBlockAccess paramIBlockAccess, BlockPosition paramBlockPosition) {
/* 35 */     return MaterialMapColor.p;
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean x(IBlockData paramIBlockData) {
/* 40 */     return (paramIBlockData.getBlock() == Blocks.SAND || paramIBlockData.getBlock() == Blocks.HARDENED_CLAY || paramIBlockData.getBlock() == Blocks.STAINED_HARDENED_CLAY || paramIBlockData.getBlock() == Blocks.DIRT);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a(IBlockAccess paramIBlockAccess, BlockPosition paramBlockPosition) {
/* 45 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public int a(Random paramRandom) {
/* 50 */     return paramRandom.nextInt(3);
/*    */   }
/*    */ 
/*    */   
/*    */   public Item getDropType(IBlockData paramIBlockData, Random paramRandom, int paramInt) {
/* 55 */     return Items.STICK;
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(World paramWorld, EntityHuman paramEntityHuman, BlockPosition paramBlockPosition, IBlockData paramIBlockData, @Nullable TileEntity paramTileEntity, ItemStack paramItemStack) {
/* 60 */     if (paramWorld.isClientSide || paramItemStack.getItem() != Items.SHEARS) {
/* 61 */       super.a(paramWorld, paramEntityHuman, paramBlockPosition, paramIBlockData, paramTileEntity, paramItemStack);
/*    */     } else {
/* 63 */       paramEntityHuman.b(StatisticList.a(this));
/*    */       
/* 65 */       a(paramWorld, paramBlockPosition, new ItemStack(Blocks.DEADBUSH, 1, 0));
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BlockDeadBush.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */