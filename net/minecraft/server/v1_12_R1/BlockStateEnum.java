/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import com.google.common.base.Optional;
/*    */ import com.google.common.base.Predicate;
/*    */ import com.google.common.collect.ImmutableSet;
/*    */ import com.google.common.collect.Maps;
/*    */ import java.util.Collection;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BlockStateEnum<T extends Enum<T> & INamable>
/*    */   extends BlockState<T>
/*    */ {
/*    */   private final ImmutableSet<T> a;
/* 17 */   private final Map<String, T> b = Maps.newHashMap();
/*    */   
/*    */   protected BlockStateEnum(String paramString, Class<T> paramClass, Collection<T> paramCollection) {
/* 20 */     super(paramString, paramClass);
/* 21 */     this.a = ImmutableSet.copyOf(paramCollection);
/*    */     
/* 23 */     for (Enum enum_ : paramCollection) {
/* 24 */       String str = ((INamable)enum_).getName();
/* 25 */       if (this.b.containsKey(str)) {
/* 26 */         throw new IllegalArgumentException("Multiple values have the same name '" + str + "'");
/*    */       }
/* 28 */       this.b.put(str, (T)enum_);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public Collection<T> c() {
/* 34 */     return (Collection<T>)this.a;
/*    */   }
/*    */ 
/*    */   
/*    */   public Optional<T> b(String paramString) {
/* 39 */     return Optional.fromNullable(this.b.get(paramString));
/*    */   }
/*    */ 
/*    */   
/*    */   public String a(T paramT) {
/* 44 */     return ((INamable)paramT).getName();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean equals(Object paramObject) {
/* 49 */     if (this == paramObject) {
/* 50 */       return true;
/*    */     }
/*    */     
/* 53 */     if (paramObject instanceof BlockStateEnum && super.equals(paramObject)) {
/* 54 */       BlockStateEnum blockStateEnum = (BlockStateEnum)paramObject;
/* 55 */       return (this.a.equals(blockStateEnum.a) && this.b.equals(blockStateEnum.b));
/*    */     } 
/*    */     
/* 58 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public int hashCode() {
/* 63 */     int i = super.hashCode();
/* 64 */     i = 31 * i + this.a.hashCode();
/* 65 */     i = 31 * i + this.b.hashCode();
/* 66 */     return i;
/*    */   }
/*    */   
/*    */   public static <T extends Enum<T> & INamable> BlockStateEnum<T> of(String paramString, Class<T> paramClass) {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: aload_1
/*    */     //   2: invokestatic alwaysTrue : ()Lcom/google/common/base/Predicate;
/*    */     //   5: invokestatic a : (Ljava/lang/String;Ljava/lang/Class;Lcom/google/common/base/Predicate;)Lnet/minecraft/server/v1_12_R1/BlockStateEnum;
/*    */     //   8: areturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #70	-> 0
/*    */   }
/*    */   
/*    */   public static <T extends Enum<T> & INamable> BlockStateEnum<T> a(String paramString, Class<T> paramClass, Predicate<T> paramPredicate) {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: aload_1
/*    */     //   2: aload_1
/*    */     //   3: invokevirtual getEnumConstants : ()[Ljava/lang/Object;
/*    */     //   6: invokestatic newArrayList : ([Ljava/lang/Object;)Ljava/util/ArrayList;
/*    */     //   9: aload_2
/*    */     //   10: invokestatic filter : (Ljava/util/Collection;Lcom/google/common/base/Predicate;)Ljava/util/Collection;
/*    */     //   13: invokestatic a : (Ljava/lang/String;Ljava/lang/Class;Ljava/util/Collection;)Lnet/minecraft/server/v1_12_R1/BlockStateEnum;
/*    */     //   16: areturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #74	-> 0
/*    */   }
/*    */   
/*    */   public static <T extends Enum<T> & INamable> BlockStateEnum<T> of(String paramString, Class<T> paramClass, T... paramVarArgs) {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: aload_1
/*    */     //   2: aload_2
/*    */     //   3: invokestatic newArrayList : ([Ljava/lang/Object;)Ljava/util/ArrayList;
/*    */     //   6: invokestatic a : (Ljava/lang/String;Ljava/lang/Class;Ljava/util/Collection;)Lnet/minecraft/server/v1_12_R1/BlockStateEnum;
/*    */     //   9: areturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #78	-> 0
/*    */   }
/*    */   
/*    */   public static <T extends Enum<T> & INamable> BlockStateEnum<T> a(String paramString, Class<T> paramClass, Collection<T> paramCollection) {
/*    */     // Byte code:
/*    */     //   0: new net/minecraft/server/v1_12_R1/BlockStateEnum
/*    */     //   3: dup
/*    */     //   4: aload_0
/*    */     //   5: aload_1
/*    */     //   6: aload_2
/*    */     //   7: invokespecial <init> : (Ljava/lang/String;Ljava/lang/Class;Ljava/util/Collection;)V
/*    */     //   10: areturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #82	-> 0
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BlockStateEnum.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */