/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ public class DataWatcherObject<T> {
/*    */   private final int a;
/*    */   private final DataWatcherSerializer<T> b;
/*    */   
/*    */   public DataWatcherObject(int paramInt, DataWatcherSerializer<T> paramDataWatcherSerializer) {
/*  8 */     this.a = paramInt;
/*  9 */     this.b = paramDataWatcherSerializer;
/*    */   }
/*    */   
/*    */   public int a() {
/* 13 */     return this.a;
/*    */   }
/*    */   
/*    */   public DataWatcherSerializer<T> b() {
/* 17 */     return this.b;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean equals(Object paramObject) {
/* 22 */     if (this == paramObject) {
/* 23 */       return true;
/*    */     }
/* 25 */     if (paramObject == null || getClass() != paramObject.getClass()) {
/* 26 */       return false;
/*    */     }
/*    */     
/* 29 */     DataWatcherObject dataWatcherObject = (DataWatcherObject)paramObject;
/*    */     
/* 31 */     return (this.a == dataWatcherObject.a);
/*    */   }
/*    */ 
/*    */   
/*    */   public int hashCode() {
/* 36 */     return this.a;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\DataWatcherObject.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */