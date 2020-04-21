/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import java.io.IOException;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PacketPlayInResourcePackStatus
/*    */   implements Packet<PacketListenerPlayIn>
/*    */ {
/*    */   public EnumResourcePackStatus status;
/*    */   
/*    */   public PacketPlayInResourcePackStatus() {}
/*    */   
/*    */   public PacketPlayInResourcePackStatus(EnumResourcePackStatus paramEnumResourcePackStatus) {
/* 16 */     this.status = paramEnumResourcePackStatus;
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketDataSerializer paramPacketDataSerializer) throws IOException {
/* 21 */     this.status = paramPacketDataSerializer.<EnumResourcePackStatus>a(EnumResourcePackStatus.class);
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer paramPacketDataSerializer) throws IOException {
/* 26 */     paramPacketDataSerializer.a(this.status);
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketListenerPlayIn paramPacketListenerPlayIn) {
/* 31 */     paramPacketListenerPlayIn.a(this);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public enum EnumResourcePackStatus
/*    */   {
/* 39 */     SUCCESSFULLY_LOADED,
/* 40 */     DECLINED,
/* 41 */     FAILED_DOWNLOAD,
/* 42 */     ACCEPTED;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\PacketPlayInResourcePackStatus.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */