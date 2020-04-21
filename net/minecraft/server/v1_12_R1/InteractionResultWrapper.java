/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ public class InteractionResultWrapper<T> {
/*    */   private final EnumInteractionResult a;
/*    */   private final T b;
/*    */   
/*    */   public InteractionResultWrapper(EnumInteractionResult paramEnumInteractionResult, T paramT) {
/*  8 */     this.a = paramEnumInteractionResult;
/*  9 */     this.b = paramT;
/*    */   }
/*    */   
/*    */   public EnumInteractionResult a() {
/* 13 */     return this.a;
/*    */   }
/*    */   
/*    */   public T b() {
/* 17 */     return this.b;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\InteractionResultWrapper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */