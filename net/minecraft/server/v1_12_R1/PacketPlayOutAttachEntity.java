/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PacketPlayOutAttachEntity
/*    */   implements Packet<PacketListenerPlayOut>
/*    */ {
/*    */   private int a;
/*    */   private int b;
/*    */   
/*    */   public PacketPlayOutAttachEntity() {}
/*    */   
/*    */   public PacketPlayOutAttachEntity(Entity paramEntity1, @Nullable Entity paramEntity2) {
/* 18 */     this.a = paramEntity1.getId();
/* 19 */     this.b = (paramEntity2 != null) ? paramEntity2.getId() : -1;
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketDataSerializer paramPacketDataSerializer) throws IOException {
/* 24 */     this.a = paramPacketDataSerializer.readInt();
/* 25 */     this.b = paramPacketDataSerializer.readInt();
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer paramPacketDataSerializer) throws IOException {
/* 30 */     paramPacketDataSerializer.writeInt(this.a);
/* 31 */     paramPacketDataSerializer.writeInt(this.b);
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketListenerPlayOut paramPacketListenerPlayOut) {
/* 36 */     paramPacketListenerPlayOut.a(this);
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\PacketPlayOutAttachEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */