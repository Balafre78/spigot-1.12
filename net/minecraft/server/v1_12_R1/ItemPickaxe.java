/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import com.google.common.collect.Sets;
/*    */ import java.util.Set;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ItemPickaxe
/*    */   extends ItemTool
/*    */ {
/* 12 */   private static final Set<Block> e = Sets.newHashSet((Object[])new Block[] { Blocks.ACTIVATOR_RAIL, Blocks.COAL_ORE, Blocks.COBBLESTONE, Blocks.DETECTOR_RAIL, Blocks.DIAMOND_BLOCK, Blocks.DIAMOND_ORE, Blocks.DOUBLE_STONE_SLAB, Blocks.GOLDEN_RAIL, Blocks.GOLD_BLOCK, Blocks.GOLD_ORE, Blocks.ICE, Blocks.IRON_BLOCK, Blocks.IRON_ORE, Blocks.LAPIS_BLOCK, Blocks.LAPIS_ORE, Blocks.LIT_REDSTONE_ORE, Blocks.MOSSY_COBBLESTONE, Blocks.NETHERRACK, Blocks.PACKED_ICE, Blocks.RAIL, Blocks.REDSTONE_ORE, Blocks.SANDSTONE, Blocks.RED_SANDSTONE, Blocks.STONE, Blocks.STONE_SLAB, Blocks.STONE_BUTTON, Blocks.STONE_PRESSURE_PLATE });
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
/*    */   protected ItemPickaxe(Item.EnumToolMaterial paramEnumToolMaterial) {
/* 43 */     super(1.0F, -2.8F, paramEnumToolMaterial, e);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canDestroySpecialBlock(IBlockData paramIBlockData) {
/* 48 */     Block block = paramIBlockData.getBlock();
/* 49 */     if (block == Blocks.OBSIDIAN) {
/* 50 */       return (this.d.d() == 3);
/*    */     }
/* 52 */     if (block == Blocks.DIAMOND_BLOCK || block == Blocks.DIAMOND_ORE) {
/* 53 */       return (this.d.d() >= 2);
/*    */     }
/* 55 */     if (block == Blocks.EMERALD_ORE || block == Blocks.EMERALD_BLOCK) {
/* 56 */       return (this.d.d() >= 2);
/*    */     }
/* 58 */     if (block == Blocks.GOLD_BLOCK || block == Blocks.GOLD_ORE) {
/* 59 */       return (this.d.d() >= 2);
/*    */     }
/* 61 */     if (block == Blocks.IRON_BLOCK || block == Blocks.IRON_ORE) {
/* 62 */       return (this.d.d() >= 1);
/*    */     }
/* 64 */     if (block == Blocks.LAPIS_BLOCK || block == Blocks.LAPIS_ORE) {
/* 65 */       return (this.d.d() >= 1);
/*    */     }
/* 67 */     if (block == Blocks.REDSTONE_ORE || block == Blocks.LIT_REDSTONE_ORE) {
/* 68 */       return (this.d.d() >= 2);
/*    */     }
/* 70 */     Material material = paramIBlockData.getMaterial();
/* 71 */     if (material == Material.STONE) {
/* 72 */       return true;
/*    */     }
/* 74 */     if (material == Material.ORE) {
/* 75 */       return true;
/*    */     }
/* 77 */     if (material == Material.HEAVY) {
/* 78 */       return true;
/*    */     }
/* 80 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public float getDestroySpeed(ItemStack paramItemStack, IBlockData paramIBlockData) {
/* 85 */     Material material = paramIBlockData.getMaterial();
/* 86 */     if (material == Material.ORE || material == Material.HEAVY || material == Material.STONE) {
/* 87 */       return this.a;
/*    */     }
/* 89 */     return super.getDestroySpeed(paramItemStack, paramIBlockData);
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\ItemPickaxe.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */