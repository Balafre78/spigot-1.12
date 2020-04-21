/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.util.UUID;
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PacketPlayInSpectate
/*    */   implements Packet<PacketListenerPlayIn>
/*    */ {
/*    */   private UUID a;
/*    */   
/*    */   public PacketPlayInSpectate() {}
/*    */   
/*    */   public PacketPlayInSpectate(UUID paramUUID) {
/* 20 */     this.a = paramUUID;
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketDataSerializer paramPacketDataSerializer) throws IOException {
/* 25 */     this.a = paramPacketDataSerializer.i();
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer paramPacketDataSerializer) throws IOException {
/* 30 */     paramPacketDataSerializer.a(this.a);
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketListenerPlayIn paramPacketListenerPlayIn) {
/* 35 */     paramPacketListenerPlayIn.a(this);
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   public Entity a(WorldServer paramWorldServer) {
/* 40 */     return paramWorldServer.getEntity(this.a);
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\PacketPlayInSpectate.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */