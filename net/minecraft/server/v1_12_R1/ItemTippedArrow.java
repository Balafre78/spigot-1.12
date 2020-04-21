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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ItemTippedArrow
/*    */   extends ItemArrow
/*    */ {
/*    */   public EntityArrow a(World paramWorld, ItemStack paramItemStack, EntityLiving paramEntityLiving) {
/* 26 */     EntityTippedArrow entityTippedArrow = new EntityTippedArrow(paramWorld, paramEntityLiving);
/* 27 */     entityTippedArrow.a(paramItemStack);
/* 28 */     return entityTippedArrow;
/*    */   }
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
/*    */   public String b(ItemStack paramItemStack) {
/* 49 */     return LocaleI18n.get(PotionUtil.d(paramItemStack).b("tipped_arrow.effect."));
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\ItemTippedArrow.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */