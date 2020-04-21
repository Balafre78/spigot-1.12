/*    */ package net.minecraft.server.v1_12_R1;
/*    */ import java.util.AbstractList;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Arrays;
/*    */ import java.util.List;
/*    */ import javax.annotation.Nonnull;
/*    */ import javax.annotation.Nullable;
/*    */ import org.apache.commons.lang3.Validate;
/*    */ 
/*    */ public class NonNullList<E> extends AbstractList<E> {
/*    */   private final List<E> a;
/*    */   
/*    */   public static <E> NonNullList<E> a() {
/* 14 */     return new NonNullList<>();
/*    */   }
/*    */   private final E b;
/*    */   public static <E> NonNullList<E> a(int paramInt, E paramE) {
/* 18 */     Validate.notNull(paramE);
/*    */     
/* 20 */     Object[] arrayOfObject = new Object[paramInt];
/* 21 */     Arrays.fill(arrayOfObject, paramE);
/* 22 */     return new NonNullList<>(Arrays.asList((E[])arrayOfObject), paramE);
/*    */   }
/*    */   
/*    */   public static <E> NonNullList<E> a(E paramE, E... paramVarArgs) {
/* 26 */     return new NonNullList<>(Arrays.asList(paramVarArgs), paramE);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected NonNullList() {
/* 33 */     this(new ArrayList<>(), null);
/*    */   }
/*    */   
/*    */   protected NonNullList(List<E> paramList, @Nullable E paramE) {
/* 37 */     this.a = paramList;
/* 38 */     this.b = paramE;
/*    */   }
/*    */ 
/*    */   
/*    */   @Nonnull
/*    */   public E get(int paramInt) {
/* 44 */     return this.a.get(paramInt);
/*    */   }
/*    */ 
/*    */   
/*    */   public E set(int paramInt, E paramE) {
/* 49 */     Validate.notNull(paramE);
/*    */     
/* 51 */     return this.a.set(paramInt, paramE);
/*    */   }
/*    */ 
/*    */   
/*    */   public void add(int paramInt, E paramE) {
/* 56 */     Validate.notNull(paramE);
/*    */     
/* 58 */     this.a.add(paramInt, paramE);
/*    */   }
/*    */ 
/*    */   
/*    */   public E remove(int paramInt) {
/* 63 */     return this.a.remove(paramInt);
/*    */   }
/*    */ 
/*    */   
/*    */   public int size() {
/* 68 */     return this.a.size();
/*    */   }
/*    */ 
/*    */   
/*    */   public void clear() {
/* 73 */     if (this.b == null) {
/* 74 */       super.clear();
/*    */     } else {
/* 76 */       for (byte b = 0; b < size(); b++)
/* 77 */         set(b, this.b); 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\NonNullList.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */