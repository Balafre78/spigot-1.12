/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import com.google.common.base.Predicate;
/*    */ import com.google.common.collect.Maps;
/*    */ import java.util.Map;
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BlockStatePredicate
/*    */   implements Predicate<IBlockData>
/*    */ {
/* 14 */   public static final Predicate<IBlockData> a = new Predicate<IBlockData>()
/*    */     {
/*    */       public boolean a(@Nullable IBlockData param1IBlockData) {
/* 17 */         return true;
/*    */       }
/*    */     };
/*    */   
/* 21 */   private final Map<IBlockState<?>, Predicate<?>> c = Maps.newHashMap(); private final BlockStateList b;
/*    */   
/*    */   private BlockStatePredicate(BlockStateList paramBlockStateList) {
/* 24 */     this.b = paramBlockStateList;
/*    */   }
/*    */   
/*    */   public static BlockStatePredicate a(Block paramBlock) {
/* 28 */     return new BlockStatePredicate(paramBlock.s());
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean a(@Nullable IBlockData paramIBlockData) {
/* 37 */     if (paramIBlockData == null || !paramIBlockData.getBlock().equals(this.b.getBlock())) {
/* 38 */       return false;
/*    */     }
/*    */     
/* 41 */     if (this.c.isEmpty()) {
/* 42 */       return true;
/*    */     }
/*    */     
/* 45 */     for (Map.Entry<IBlockState<?>, Predicate<?>> entry : this.c.entrySet()) {
/* 46 */       if (!a(paramIBlockData, (IBlockState<Comparable>)entry.getKey(), (Predicate)entry.getValue())) {
/* 47 */         return false;
/*    */       }
/*    */     } 
/*    */     
/* 51 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   protected <T extends Comparable<T>> boolean a(IBlockData paramIBlockData, IBlockState<T> paramIBlockState, Predicate<?> paramPredicate) {
/* 56 */     return paramPredicate.apply(paramIBlockData.get(paramIBlockState));
/*    */   }
/*    */   
/*    */   public <V extends Comparable<V>> BlockStatePredicate a(IBlockState<V> paramIBlockState, Predicate<? extends V> paramPredicate) {
/* 60 */     if (!this.b.d().contains(paramIBlockState)) {
/* 61 */       throw new IllegalArgumentException(this.b + " cannot support property " + paramIBlockState);
/*    */     }
/* 63 */     this.c.put(paramIBlockState, paramPredicate);
/* 64 */     return this;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BlockStatePredicate.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */