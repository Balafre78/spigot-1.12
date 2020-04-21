/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import javax.annotation.concurrent.Immutable;
/*    */ 
/*    */ 
/*    */ 
/*    */ @Immutable
/*    */ public class ChestLock
/*    */ {
/* 10 */   public static final ChestLock a = new ChestLock("");
/*    */   
/*    */   private final String b;
/*    */ 
/*    */   
/*    */   public ChestLock(String paramString) {
/* 16 */     this.b = paramString;
/*    */   }
/*    */   
/*    */   public boolean a() {
/* 20 */     return (this.b == null || this.b.isEmpty());
/*    */   }
/*    */   
/*    */   public String getKey() {
/* 24 */     return this.b;
/*    */   }
/*    */   
/*    */   public void a(NBTTagCompound paramNBTTagCompound) {
/* 28 */     paramNBTTagCompound.setString("Lock", this.b);
/*    */   }
/*    */   
/*    */   public static ChestLock b(NBTTagCompound paramNBTTagCompound) {
/* 32 */     if (paramNBTTagCompound.hasKeyOfType("Lock", 8)) {
/* 33 */       String str = paramNBTTagCompound.getString("Lock");
/* 34 */       return new ChestLock(str);
/*    */     } 
/* 36 */     return a;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\ChestLock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */