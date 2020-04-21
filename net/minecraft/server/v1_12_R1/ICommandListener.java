/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public interface ICommandListener
/*    */ {
/*    */   String getName();
/*    */   
/*    */   default IChatBaseComponent getScoreboardDisplayName() {
/* 17 */     return new ChatComponentText(getName());
/*    */   }
/*    */ 
/*    */   
/*    */   default void sendMessage(IChatBaseComponent paramIChatBaseComponent) {}
/*    */ 
/*    */   
/*    */   boolean a(int paramInt, String paramString);
/*    */   
/*    */   default BlockPosition getChunkCoordinates() {
/* 27 */     return BlockPosition.ZERO;
/*    */   }
/*    */   
/*    */   default Vec3D d() {
/* 31 */     return Vec3D.a;
/*    */   }
/*    */   
/*    */   World getWorld();
/*    */   
/*    */   @Nullable
/*    */   default Entity f() {
/* 38 */     return null;
/*    */   }
/*    */   
/*    */   default boolean getSendCommandFeedback() {
/* 42 */     return false;
/*    */   }
/*    */   
/*    */   default void a(CommandObjectiveExecutor.EnumCommandResult paramEnumCommandResult, int paramInt) {}
/*    */   
/*    */   @Nullable
/*    */   MinecraftServer C_();
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\ICommandListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */