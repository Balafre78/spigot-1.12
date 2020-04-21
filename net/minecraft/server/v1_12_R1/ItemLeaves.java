/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ public class ItemLeaves
/*    */   extends ItemBlock
/*    */ {
/*    */   private final BlockLeaves b;
/*    */   
/*    */   public ItemLeaves(BlockLeaves paramBlockLeaves) {
/*  9 */     super(paramBlockLeaves);
/* 10 */     this.b = paramBlockLeaves;
/*    */     
/* 12 */     setMaxDurability(0);
/* 13 */     a(true);
/*    */   }
/*    */ 
/*    */   
/*    */   public int filterData(int paramInt) {
/* 18 */     return paramInt | 0x4;
/*    */   }
/*    */ 
/*    */   
/*    */   public String a(ItemStack paramItemStack) {
/* 23 */     return getName() + "." + this.b.b(paramItemStack.getData()).d();
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\ItemLeaves.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */