/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import java.util.Random;
/*    */ 
/*    */ public class BlockMobSpawner
/*    */   extends BlockTileEntity {
/*    */   protected BlockMobSpawner() {
/*  8 */     super(Material.STONE);
/*    */   }
/*    */   
/*    */   public TileEntity a(World world, int i) {
/* 12 */     return new TileEntityMobSpawner();
/*    */   }
/*    */   
/*    */   public Item getDropType(IBlockData iblockdata, Random random, int i) {
/* 16 */     return Items.a;
/*    */   }
/*    */   
/*    */   public int a(Random random) {
/* 20 */     return 0;
/*    */   }
/*    */   
/*    */   public void dropNaturally(World world, BlockPosition blockposition, IBlockData iblockdata, float f, int i) {
/* 24 */     super.dropNaturally(world, blockposition, iblockdata, f, i);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getExpDrop(World world, IBlockData iblockdata, int enchantmentLevel) {
/* 34 */     int j = 15 + world.random.nextInt(15) + world.random.nextInt(15);
/*    */     
/* 36 */     return j;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean b(IBlockData iblockdata) {
/* 41 */     return false;
/*    */   }
/*    */   
/*    */   public EnumRenderType a(IBlockData iblockdata) {
/* 45 */     return EnumRenderType.MODEL;
/*    */   }
/*    */   
/*    */   public ItemStack a(World world, BlockPosition blockposition, IBlockData iblockdata) {
/* 49 */     return ItemStack.a;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BlockMobSpawner.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */