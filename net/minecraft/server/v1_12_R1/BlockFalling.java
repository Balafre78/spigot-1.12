/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import java.util.Random;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BlockFalling
/*    */   extends Block
/*    */ {
/*    */   public static boolean instaFall;
/*    */   
/*    */   public BlockFalling() {
/* 17 */     super(Material.SAND);
/* 18 */     a(CreativeModeTab.b);
/*    */   }
/*    */   
/*    */   public BlockFalling(Material paramMaterial) {
/* 22 */     super(paramMaterial);
/*    */   }
/*    */ 
/*    */   
/*    */   public void onPlace(World paramWorld, BlockPosition paramBlockPosition, IBlockData paramIBlockData) {
/* 27 */     paramWorld.a(paramBlockPosition, this, a(paramWorld));
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(IBlockData paramIBlockData, World paramWorld, BlockPosition paramBlockPosition1, Block paramBlock, BlockPosition paramBlockPosition2) {
/* 32 */     paramWorld.a(paramBlockPosition1, this, a(paramWorld));
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(World paramWorld, BlockPosition paramBlockPosition, IBlockData paramIBlockData, Random paramRandom) {
/* 37 */     if (!paramWorld.isClientSide) {
/* 38 */       b(paramWorld, paramBlockPosition);
/*    */     }
/*    */   }
/*    */   
/*    */   private void b(World paramWorld, BlockPosition paramBlockPosition) {
/* 43 */     if (!x(paramWorld.getType(paramBlockPosition.down())) || paramBlockPosition.getY() < 0) {
/*    */       return;
/*    */     }
/*    */     
/* 47 */     byte b = 32;
/* 48 */     if (instaFall || !paramWorld.areChunksLoadedBetween(paramBlockPosition.a(-32, -32, -32), paramBlockPosition.a(32, 32, 32))) {
/* 49 */       paramWorld.setAir(paramBlockPosition);
/*    */       
/* 51 */       BlockPosition blockPosition = paramBlockPosition.down();
/* 52 */       while (x(paramWorld.getType(blockPosition)) && blockPosition.getY() > 0) {
/* 53 */         blockPosition = blockPosition.down();
/*    */       }
/* 55 */       if (blockPosition.getY() > 0)
/*    */       {
/* 57 */         paramWorld.setTypeUpdate(blockPosition.up(), getBlockData());
/*    */       }
/* 59 */     } else if (!paramWorld.isClientSide) {
/* 60 */       EntityFallingBlock entityFallingBlock = new EntityFallingBlock(paramWorld, paramBlockPosition.getX() + 0.5D, paramBlockPosition.getY(), paramBlockPosition.getZ() + 0.5D, paramWorld.getType(paramBlockPosition));
/* 61 */       a(entityFallingBlock);
/* 62 */       paramWorld.addEntity(entityFallingBlock);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   protected void a(EntityFallingBlock paramEntityFallingBlock) {}
/*    */ 
/*    */   
/*    */   public int a(World paramWorld) {
/* 71 */     return 2;
/*    */   }
/*    */ 
/*    */   
/*    */   public static boolean x(IBlockData paramIBlockData) {
/* 76 */     Block block = paramIBlockData.getBlock();
/* 77 */     Material material = paramIBlockData.getMaterial();
/* 78 */     return (block == Blocks.FIRE || material == Material.AIR || material == Material.WATER || material == Material.LAVA);
/*    */   }
/*    */   
/*    */   public void a(World paramWorld, BlockPosition paramBlockPosition, IBlockData paramIBlockData1, IBlockData paramIBlockData2) {}
/*    */   
/*    */   public void a_(World paramWorld, BlockPosition paramBlockPosition) {}
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BlockFalling.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */