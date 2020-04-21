/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import org.bukkit.inventory.Recipe;
/*    */ 
/*    */ 
/*    */ 
/*    */ public interface IRecipe
/*    */ {
/*    */   boolean a(InventoryCrafting paramInventoryCrafting, World paramWorld);
/*    */   
/*    */   ItemStack craftItem(InventoryCrafting paramInventoryCrafting);
/*    */   
/*    */   default boolean c() {
/* 14 */     return false;
/*    */   }
/*    */   
/*    */   ItemStack b();
/*    */   
/*    */   NonNullList<ItemStack> b(InventoryCrafting paramInventoryCrafting);
/*    */   
/*    */   Recipe toBukkitRecipe();
/*    */   
/*    */   void setKey(MinecraftKey paramMinecraftKey);
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\IRecipe.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */