/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import com.google.common.base.Optional;
/*    */ import com.google.common.collect.ImmutableSet;
/*    */ import com.google.common.collect.Sets;
/*    */ import java.util.Collection;
/*    */ import java.util.HashSet;
/*    */ 
/*    */ public class BlockStateInteger
/*    */   extends BlockState<Integer> {
/*    */   private final ImmutableSet<Integer> a;
/*    */   
/*    */   protected BlockStateInteger(String paramString, int paramInt1, int paramInt2) {
/* 14 */     super(paramString, Integer.class);
/* 15 */     if (paramInt1 < 0) {
/* 16 */       throw new IllegalArgumentException("Min value of " + paramString + " must be 0 or greater");
/*    */     }
/* 18 */     if (paramInt2 <= paramInt1) {
/* 19 */       throw new IllegalArgumentException("Max value of " + paramString + " must be greater than min (" + paramInt1 + ")");
/*    */     }
/*    */ 
/*    */     
/* 23 */     HashSet<Integer> hashSet = Sets.newHashSet();
/* 24 */     for (int i = paramInt1; i <= paramInt2; i++) {
/* 25 */       hashSet.add(Integer.valueOf(i));
/*    */     }
/*    */     
/* 28 */     this.a = ImmutableSet.copyOf(hashSet);
/*    */   }
/*    */ 
/*    */   
/*    */   public Collection<Integer> c() {
/* 33 */     return (Collection<Integer>)this.a;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean equals(Object paramObject) {
/* 38 */     if (this == paramObject) {
/* 39 */       return true;
/*    */     }
/*    */     
/* 42 */     if (paramObject instanceof BlockStateInteger && super.equals(paramObject)) {
/* 43 */       BlockStateInteger blockStateInteger = (BlockStateInteger)paramObject;
/*    */       
/* 45 */       return this.a.equals(blockStateInteger.a);
/*    */     } 
/*    */     
/* 48 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public int hashCode() {
/* 53 */     return 31 * super.hashCode() + this.a.hashCode();
/*    */   }
/*    */   
/*    */   public static BlockStateInteger of(String paramString, int paramInt1, int paramInt2) {
/* 57 */     return new BlockStateInteger(paramString, paramInt1, paramInt2);
/*    */   }
/*    */ 
/*    */   
/*    */   public Optional<Integer> b(String paramString) {
/*    */     try {
/* 63 */       Integer integer = Integer.valueOf(paramString);
/*    */       
/* 65 */       return this.a.contains(integer) ? Optional.of(integer) : Optional.absent();
/* 66 */     } catch (NumberFormatException numberFormatException) {
/* 67 */       return Optional.absent();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public String a(Integer paramInteger) {
/* 73 */     return paramInteger.toString();
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BlockStateInteger.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */