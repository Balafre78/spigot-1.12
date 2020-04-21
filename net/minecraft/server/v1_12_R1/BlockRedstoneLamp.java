/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import java.util.Random;
/*    */ import org.bukkit.craftbukkit.v1_12_R1.event.CraftEventFactory;
/*    */ 
/*    */ public class BlockRedstoneLamp
/*    */   extends Block
/*    */ {
/*    */   private final boolean a;
/*    */   
/*    */   public BlockRedstoneLamp(boolean flag) {
/* 12 */     super(Material.BUILDABLE_GLASS);
/* 13 */     this.a = flag;
/* 14 */     if (flag) {
/* 15 */       a(1.0F);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void onPlace(World world, BlockPosition blockposition, IBlockData iblockdata) {
/* 21 */     if (!world.isClientSide) {
/* 22 */       if (this.a && !world.isBlockIndirectlyPowered(blockposition)) {
/* 23 */         world.setTypeAndData(blockposition, Blocks.REDSTONE_LAMP.getBlockData(), 2);
/* 24 */       } else if (!this.a && world.isBlockIndirectlyPowered(blockposition)) {
/*    */         
/* 26 */         if (CraftEventFactory.callRedstoneChange(world, blockposition.getX(), blockposition.getY(), blockposition.getZ(), 0, 15).getNewCurrent() != 15) {
/*    */           return;
/*    */         }
/*    */         
/* 30 */         world.setTypeAndData(blockposition, Blocks.LIT_REDSTONE_LAMP.getBlockData(), 2);
/*    */       } 
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(IBlockData iblockdata, World world, BlockPosition blockposition, Block block, BlockPosition blockposition1) {
/* 37 */     if (!world.isClientSide) {
/* 38 */       if (this.a && !world.isBlockIndirectlyPowered(blockposition)) {
/* 39 */         world.a(blockposition, this, 4);
/* 40 */       } else if (!this.a && world.isBlockIndirectlyPowered(blockposition)) {
/*    */         
/* 42 */         if (CraftEventFactory.callRedstoneChange(world, blockposition.getX(), blockposition.getY(), blockposition.getZ(), 0, 15).getNewCurrent() != 15) {
/*    */           return;
/*    */         }
/*    */         
/* 46 */         world.setTypeAndData(blockposition, Blocks.LIT_REDSTONE_LAMP.getBlockData(), 2);
/*    */       } 
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(World world, BlockPosition blockposition, IBlockData iblockdata, Random random) {
/* 53 */     if (!world.isClientSide && 
/* 54 */       this.a && !world.isBlockIndirectlyPowered(blockposition)) {
/*    */       
/* 56 */       if (CraftEventFactory.callRedstoneChange(world, blockposition.getX(), blockposition.getY(), blockposition.getZ(), 15, 0).getNewCurrent() != 0) {
/*    */         return;
/*    */       }
/*    */       
/* 60 */       world.setTypeAndData(blockposition, Blocks.REDSTONE_LAMP.getBlockData(), 2);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Item getDropType(IBlockData iblockdata, Random random, int i) {
/* 67 */     return Item.getItemOf(Blocks.REDSTONE_LAMP);
/*    */   }
/*    */   
/*    */   public ItemStack a(World world, BlockPosition blockposition, IBlockData iblockdata) {
/* 71 */     return new ItemStack(Blocks.REDSTONE_LAMP);
/*    */   }
/*    */   
/*    */   protected ItemStack u(IBlockData iblockdata) {
/* 75 */     return new ItemStack(Blocks.REDSTONE_LAMP);
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BlockRedstoneLamp.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */