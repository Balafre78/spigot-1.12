/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public interface IDispenseBehavior
/*    */ {
/*  9 */   public static final IDispenseBehavior NONE = new IDispenseBehavior()
/*    */     {
/*    */       public ItemStack a(ISourceBlock param1ISourceBlock, ItemStack param1ItemStack) {
/* 12 */         return param1ItemStack;
/*    */       }
/*    */     };
/*    */   
/*    */   ItemStack a(ISourceBlock paramISourceBlock, ItemStack paramItemStack);
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\IDispenseBehavior.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */