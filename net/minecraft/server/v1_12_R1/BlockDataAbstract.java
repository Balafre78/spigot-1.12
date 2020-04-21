/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import com.google.common.base.Function;
/*    */ import com.google.common.base.Joiner;
/*    */ import com.google.common.collect.Iterables;
/*    */ import java.util.Collection;
/*    */ import java.util.Iterator;
/*    */ import java.util.Map;
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ 
/*    */ public abstract class BlockDataAbstract
/*    */   implements IBlockData
/*    */ {
/* 15 */   private static final Joiner a = Joiner.on(',');
/* 16 */   private static final Function<Map.Entry<IBlockState<?>, Comparable<?>>, String> b = new Function<Map.Entry<IBlockState<?>, Comparable<?>>, String>()
/*    */     {
/*    */       @Nullable
/*    */       public String a(@Nullable Map.Entry<IBlockState<?>, Comparable<?>> param1Entry) {
/* 20 */         if (param1Entry == null) {
/* 21 */           return "<NULL>";
/*    */         }
/*    */         
/* 24 */         IBlockState<Comparable> iBlockState = (IBlockState)param1Entry.getKey();
/* 25 */         return iBlockState.a() + "=" + a(iBlockState, param1Entry.getValue());
/*    */       }
/*    */ 
/*    */       
/*    */       private <T extends Comparable<T>> String a(IBlockState<T> param1IBlockState, Comparable<?> param1Comparable) {
/* 30 */         return param1IBlockState.a((T)param1Comparable);
/*    */       }
/*    */     };
/*    */ 
/*    */   
/*    */   public <T extends Comparable<T>> IBlockData a(IBlockState<T> paramIBlockState) {
/* 36 */     return set(paramIBlockState, a(paramIBlockState.c(), get(paramIBlockState)));
/*    */   }
/*    */   
/*    */   protected static <T> T a(Collection<T> paramCollection, T paramT) {
/* 40 */     Iterator<T> iterator = paramCollection.iterator();
/*    */     
/* 42 */     while (iterator.hasNext()) {
/* 43 */       if (iterator.next().equals(paramT)) {
/* 44 */         if (iterator.hasNext()) {
/* 45 */           return iterator.next();
/*    */         }
/* 47 */         return paramCollection.iterator().next();
/*    */       } 
/*    */     } 
/*    */     
/* 51 */     return iterator.next();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 56 */     StringBuilder stringBuilder = new StringBuilder();
/* 57 */     stringBuilder.append(Block.REGISTRY.b(getBlock()));
/*    */     
/* 59 */     if (!t().isEmpty()) {
/* 60 */       stringBuilder.append("[");
/* 61 */       a.appendTo(stringBuilder, Iterables.transform((Iterable)t().entrySet(), b));
/* 62 */       stringBuilder.append("]");
/*    */     } 
/*    */     
/* 65 */     return stringBuilder.toString();
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BlockDataAbstract.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */