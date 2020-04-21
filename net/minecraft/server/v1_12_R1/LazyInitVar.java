/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ public abstract class LazyInitVar<T> {
/*    */   private T a;
/*    */   private boolean b;
/*    */   
/*    */   public T c() {
/*  8 */     if (!this.b) {
/*  9 */       this.b = true;
/* 10 */       this.a = init();
/*    */     } 
/*    */     
/* 13 */     return this.a;
/*    */   }
/*    */   
/*    */   protected abstract T init();
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\LazyInitVar.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */