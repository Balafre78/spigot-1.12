/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ public class ItemWithAuxData
/*    */   extends ItemBlock
/*    */ {
/*    */   private String[] b;
/*    */   
/*    */   public ItemWithAuxData(Block paramBlock, boolean paramBoolean) {
/*  9 */     super(paramBlock);
/*    */     
/* 11 */     if (paramBoolean) {
/* 12 */       setMaxDurability(0);
/* 13 */       a(true);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public int filterData(int paramInt) {
/* 19 */     return paramInt;
/*    */   }
/*    */ 
/*    */   
/*    */   public ItemWithAuxData a(String[] paramArrayOfString) {
/* 24 */     this.b = paramArrayOfString;
/* 25 */     return this;
/*    */   }
/*    */ 
/*    */   
/*    */   public String a(ItemStack paramItemStack) {
/* 30 */     if (this.b == null) {
/* 31 */       return super.a(paramItemStack);
/*    */     }
/* 33 */     int i = paramItemStack.getData();
/* 34 */     if (i >= 0 && i < this.b.length) {
/* 35 */       return super.a(paramItemStack) + "." + this.b[i];
/*    */     }
/* 37 */     return super.a(paramItemStack);
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\ItemWithAuxData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */