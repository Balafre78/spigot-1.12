/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import com.google.common.base.Optional;
/*    */ import com.google.common.collect.ImmutableSet;
/*    */ import java.util.Collection;
/*    */ 
/*    */ public class BlockStateBoolean
/*    */   extends BlockState<Boolean> {
/*    */   private final ImmutableSet<Boolean> a;
/*    */   
/*    */   protected BlockStateBoolean(String paramString) {
/* 12 */     super(paramString, Boolean.class);
/* 13 */     this.a = ImmutableSet.of(Boolean.valueOf(true), Boolean.valueOf(false));
/*    */   }
/*    */ 
/*    */   
/*    */   public Collection<Boolean> c() {
/* 18 */     return (Collection<Boolean>)this.a;
/*    */   }
/*    */   
/*    */   public static BlockStateBoolean of(String paramString) {
/* 22 */     return new BlockStateBoolean(paramString);
/*    */   }
/*    */ 
/*    */   
/*    */   public Optional<Boolean> b(String paramString) {
/* 27 */     if ("true".equals(paramString) || "false".equals(paramString)) {
/* 28 */       return Optional.of(Boolean.valueOf(paramString));
/*    */     }
/*    */     
/* 31 */     return Optional.absent();
/*    */   }
/*    */ 
/*    */   
/*    */   public String a(Boolean paramBoolean) {
/* 36 */     return paramBoolean.toString();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean equals(Object paramObject) {
/* 41 */     if (this == paramObject) {
/* 42 */       return true;
/*    */     }
/*    */     
/* 45 */     if (paramObject instanceof BlockStateBoolean && super.equals(paramObject)) {
/* 46 */       BlockStateBoolean blockStateBoolean = (BlockStateBoolean)paramObject;
/*    */       
/* 48 */       return this.a.equals(blockStateBoolean.a);
/*    */     } 
/*    */     
/* 51 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public int hashCode() {
/* 56 */     return 31 * super.hashCode() + this.a.hashCode();
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BlockStateBoolean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */