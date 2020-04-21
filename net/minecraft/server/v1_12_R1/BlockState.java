/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import com.google.common.base.MoreObjects;
/*    */ 
/*    */ public abstract class BlockState<T extends Comparable<T>> implements IBlockState<T> {
/*    */   private final Class<T> a;
/*    */   private final String b;
/*    */   
/*    */   protected BlockState(String paramString, Class<T> paramClass) {
/* 10 */     this.a = paramClass;
/* 11 */     this.b = paramString;
/*    */   }
/*    */ 
/*    */   
/*    */   public String a() {
/* 16 */     return this.b;
/*    */   }
/*    */ 
/*    */   
/*    */   public Class<T> b() {
/* 21 */     return this.a;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 26 */     return MoreObjects.toStringHelper(this)
/* 27 */       .add("name", this.b)
/* 28 */       .add("clazz", this.a)
/* 29 */       .add("values", c())
/* 30 */       .toString();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean equals(Object paramObject) {
/* 35 */     if (this == paramObject) {
/* 36 */       return true;
/*    */     }
/*    */     
/* 39 */     if (paramObject instanceof BlockState) {
/* 40 */       BlockState blockState = (BlockState)paramObject;
/*    */       
/* 42 */       return (this.a.equals(blockState.a) && this.b.equals(blockState.b));
/*    */     } 
/*    */     
/* 45 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public int hashCode() {
/* 50 */     return 31 * this.a.hashCode() + this.b.hashCode();
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BlockState.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */