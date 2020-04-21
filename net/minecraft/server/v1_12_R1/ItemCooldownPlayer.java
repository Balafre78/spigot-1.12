/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ 
/*    */ public class ItemCooldownPlayer
/*    */   extends ItemCooldown
/*    */ {
/*    */   private final EntityPlayer a;
/*    */   
/*    */   public ItemCooldownPlayer(EntityPlayer paramEntityPlayer) {
/* 10 */     this.a = paramEntityPlayer;
/*    */   }
/*    */ 
/*    */   
/*    */   protected void b(Item paramItem, int paramInt) {
/* 15 */     super.b(paramItem, paramInt);
/* 16 */     this.a.playerConnection.sendPacket(new PacketPlayOutSetCooldown(paramItem, paramInt));
/*    */   }
/*    */ 
/*    */   
/*    */   protected void c(Item paramItem) {
/* 21 */     super.c(paramItem);
/* 22 */     this.a.playerConnection.sendPacket(new PacketPlayOutSetCooldown(paramItem, 0));
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\ItemCooldownPlayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */