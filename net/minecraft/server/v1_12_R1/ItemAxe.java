/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import com.google.common.collect.Sets;
/*    */ import java.util.Set;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ItemAxe
/*    */   extends ItemTool
/*    */ {
/* 12 */   private static final Set<Block> e = Sets.newHashSet((Object[])new Block[] { Blocks.PLANKS, Blocks.BOOKSHELF, Blocks.LOG, Blocks.LOG2, Blocks.CHEST, Blocks.PUMPKIN, Blocks.LIT_PUMPKIN, Blocks.MELON_BLOCK, Blocks.LADDER, Blocks.WOODEN_BUTTON, Blocks.WOODEN_PRESSURE_PLATE });
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
/* 26 */   private static final float[] f = new float[] { 6.0F, 8.0F, 8.0F, 8.0F, 6.0F };
/* 27 */   private static final float[] n = new float[] { -3.2F, -3.2F, -3.1F, -3.0F, -3.0F };
/*    */   
/*    */   protected ItemAxe(Item.EnumToolMaterial paramEnumToolMaterial) {
/* 30 */     super(paramEnumToolMaterial, e);
/*    */     
/* 32 */     this.b = f[paramEnumToolMaterial.ordinal()];
/* 33 */     this.c = n[paramEnumToolMaterial.ordinal()];
/*    */   }
/*    */ 
/*    */   
/*    */   public float getDestroySpeed(ItemStack paramItemStack, IBlockData paramIBlockData) {
/* 38 */     Material material = paramIBlockData.getMaterial();
/* 39 */     if (material == Material.WOOD || material == Material.PLANT || material == Material.REPLACEABLE_PLANT) {
/* 40 */       return this.a;
/*    */     }
/* 42 */     return super.getDestroySpeed(paramItemStack, paramIBlockData);
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\ItemAxe.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */