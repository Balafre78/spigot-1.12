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
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BlockWeb
/*    */   extends Block
/*    */ {
/*    */   public BlockWeb() {
/* 26 */     super(Material.WEB);
/* 27 */     a(CreativeModeTab.c);
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(World paramWorld, BlockPosition paramBlockPosition, IBlockData paramIBlockData, Entity paramEntity) {
/* 32 */     paramEntity.ba();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean b(IBlockData paramIBlockData) {
/* 37 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public AxisAlignedBB a(IBlockData paramIBlockData, IBlockAccess paramIBlockAccess, BlockPosition paramBlockPosition) {
/* 43 */     return k;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean c(IBlockData paramIBlockData) {
/* 48 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Item getDropType(IBlockData paramIBlockData, Random paramRandom, int paramInt) {
/* 54 */     return Items.STRING;
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean n() {
/* 59 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void a(World paramWorld, EntityHuman paramEntityHuman, BlockPosition paramBlockPosition, IBlockData paramIBlockData, @Nullable TileEntity paramTileEntity, ItemStack paramItemStack) {
/* 69 */     if (!paramWorld.isClientSide && paramItemStack.getItem() == Items.SHEARS) {
/* 70 */       paramEntityHuman.b(StatisticList.a(this));
/*    */ 
/*    */       
/* 73 */       a(paramWorld, paramBlockPosition, new ItemStack(Item.getItemOf(this), 1));
/*    */       
/*    */       return;
/*    */     } 
/* 77 */     super.a(paramWorld, paramEntityHuman, paramBlockPosition, paramIBlockData, paramTileEntity, paramItemStack);
/*    */   }
/*    */ 
/*    */   
/*    */   public EnumBlockFaceShape a(IBlockAccess paramIBlockAccess, IBlockData paramIBlockData, BlockPosition paramBlockPosition, EnumDirection paramEnumDirection) {
/* 82 */     return EnumBlockFaceShape.UNDEFINED;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BlockWeb.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */