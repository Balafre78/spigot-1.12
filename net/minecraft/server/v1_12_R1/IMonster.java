/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import com.google.common.base.Predicate;
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public interface IMonster
/*    */   extends IAnimal
/*    */ {
/* 17 */   public static final Predicate<Entity> d = new Predicate<Entity>()
/*    */     {
/*    */       public boolean a(@Nullable Entity param1Entity) {
/* 20 */         return param1Entity instanceof IMonster;
/*    */       }
/*    */     };
/*    */   
/* 24 */   public static final Predicate<Entity> e = new Predicate<Entity>()
/*    */     {
/*    */       public boolean a(@Nullable Entity param1Entity) {
/* 27 */         return (param1Entity instanceof IMonster && !param1Entity.isInvisible());
/*    */       }
/*    */     };
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\IMonster.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */