/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ItemSpectralArrow
/*    */   extends ItemArrow
/*    */ {
/*    */   public EntityArrow a(World paramWorld, ItemStack paramItemStack, EntityLiving paramEntityLiving) {
/* 14 */     return new EntitySpectralArrow(paramWorld, paramEntityLiving);
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\ItemSpectralArrow.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */