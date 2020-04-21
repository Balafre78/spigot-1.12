/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import com.google.common.base.Predicate;
/*    */ import com.google.common.base.Predicates;
/*    */ import com.google.common.collect.Collections2;
/*    */ import com.google.common.collect.Lists;
/*    */ import java.util.Collection;
/*    */ 
/*    */ public class BlockStateDirection
/*    */   extends BlockStateEnum<EnumDirection>
/*    */ {
/*    */   protected BlockStateDirection(String paramString, Collection<EnumDirection> paramCollection) {
/* 13 */     super(paramString, EnumDirection.class, paramCollection);
/*    */   }
/*    */   
/*    */   public static BlockStateDirection of(String paramString) {
/* 17 */     return of(paramString, Predicates.alwaysTrue());
/*    */   }
/*    */   
/*    */   public static BlockStateDirection of(String paramString, Predicate<EnumDirection> paramPredicate) {
/* 21 */     return a(paramString, Collections2.filter(Lists.newArrayList((Object[])EnumDirection.values()), paramPredicate));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static BlockStateDirection a(String paramString, Collection<EnumDirection> paramCollection) {
/* 29 */     return new BlockStateDirection(paramString, paramCollection);
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\BlockStateDirection.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */