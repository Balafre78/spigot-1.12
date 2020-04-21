/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ItemArrow
/*    */   extends Item
/*    */ {
/*    */   public ItemArrow() {
/* 10 */     b(CreativeModeTab.j);
/*    */   }
/*    */   
/*    */   public EntityArrow a(World paramWorld, ItemStack paramItemStack, EntityLiving paramEntityLiving) {
/* 14 */     EntityTippedArrow entityTippedArrow = new EntityTippedArrow(paramWorld, paramEntityLiving);
/* 15 */     entityTippedArrow.a(paramItemStack);
/* 16 */     return entityTippedArrow;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\ItemArrow.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */