/*    */ package net.minecraft.server.v1_12_R1;
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
/*    */ 
/*    */ public class BlockWorkbench
/*    */   extends Block
/*    */ {
/*    */   protected BlockWorkbench() {
/* 21 */     super(Material.WOOD);
/* 22 */     a(CreativeModeTab.c);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean interact(World paramWorld, BlockPosition paramBlockPosition, IBlockData paramIBlockData, EntityHuman paramEntityHuman, EnumHand paramEnumHand, EnumDirection paramEnumDirection, float paramFloat1, float paramFloat2, float paramFloat3) {
/* 27 */     if (paramWorld.isClientSide) {
/* 28 */       return true;
/*    */     }
/* 30 */     paramEntityHuman.openTileEntity(new TileEntityContainerWorkbench(paramWorld, paramBlockPosition));
/* 31 */     paramEntityHuman.b(StatisticList.Z);
/* 32 */     return true;
/*    */   }
/*    */   
/*    */   public static class TileEntityContainerWorkbench
/*    */     implements ITileEntityContainer {
/*    */     private final World a;
/*    */     private final BlockPosition b;
/*    */     
/*    */     public TileEntityContainerWorkbench(World param1World, BlockPosition param1BlockPosition) {
/* 41 */       this.a = param1World;
/* 42 */       this.b = param1BlockPosition;
/*    */     }
/*    */ 
/*    */     
/*    */     public String getName() {
/* 47 */       return "crafting_table";
/*    */     }
/*    */ 
/*    */     
/*    */     public boolean hasCustomName() {
/* 52 */       return false;
/*    */     }
/*    */ 
/*    */     
/*    */     public IChatBaseComponent getScoreboardDisplayName() {
/* 57 */       return new ChatMessage(Blocks.CRAFTING_TABLE.a() + ".name", new Object[0]);
/*    */     }
/*    */ 
/*    */     
/*    */     public Container createContainer(PlayerInventory param1PlayerInventory, EntityHuman param1EntityHuman) {
/* 62 */       return new ContainerWorkbench(param1PlayerInventory, this.a, this.b);
/*    */     }
/*    */ 
/*    */     
/*    */     public String getContainerName() {
/* 67 */       return "minecraft:crafting_table";
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BlockWorkbench.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */