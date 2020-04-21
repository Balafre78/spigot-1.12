/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ItemMultiTexture
/*    */   extends ItemBlock
/*    */ {
/*    */   protected final Block b;
/*    */   protected final a c;
/*    */   
/*    */   public ItemMultiTexture(Block paramBlock1, Block paramBlock2, a parama) {
/* 15 */     super(paramBlock1);
/*    */     
/* 17 */     this.b = paramBlock2;
/* 18 */     this.c = parama;
/*    */     
/* 20 */     setMaxDurability(0);
/* 21 */     a(true);
/*    */   }
/*    */   
/*    */   public ItemMultiTexture(Block paramBlock1, Block paramBlock2, String[] paramArrayOfString) {
/* 25 */     this(paramBlock1, paramBlock2, new a(paramArrayOfString)
/*    */         {
/*    */           public String a(ItemStack param1ItemStack) {
/* 28 */             int i = param1ItemStack.getData();
/* 29 */             if (i < 0 || i >= this.a.length) {
/* 30 */               i = 0;
/*    */             }
/* 32 */             return this.a[i];
/*    */           }
/*    */         });
/*    */   }
/*    */ 
/*    */   
/*    */   public int filterData(int paramInt) {
/* 39 */     return paramInt;
/*    */   }
/*    */   public static interface a {
/*    */     String a(ItemStack param1ItemStack); }
/*    */   public String a(ItemStack paramItemStack) {
/* 44 */     return getName() + "." + this.c.a(paramItemStack);
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\ItemMultiTexture.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */