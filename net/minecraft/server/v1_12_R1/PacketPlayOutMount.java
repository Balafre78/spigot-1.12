/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PacketPlayOutMount
/*    */   implements Packet<PacketListenerPlayOut>
/*    */ {
/*    */   private int a;
/*    */   private int[] b;
/*    */   
/*    */   public PacketPlayOutMount() {}
/*    */   
/*    */   public PacketPlayOutMount(Entity paramEntity) {
/* 18 */     this.a = paramEntity.getId();
/* 19 */     List<Entity> list = paramEntity.bF();
/* 20 */     this.b = new int[list.size()];
/*    */     
/* 22 */     for (byte b = 0; b < list.size(); b++) {
/* 23 */       this.b[b] = ((Entity)list.get(b)).getId();
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketDataSerializer paramPacketDataSerializer) throws IOException {
/* 29 */     this.a = paramPacketDataSerializer.g();
/* 30 */     this.b = paramPacketDataSerializer.b();
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer paramPacketDataSerializer) throws IOException {
/* 35 */     paramPacketDataSerializer.d(this.a);
/* 36 */     paramPacketDataSerializer.a(this.b);
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketListenerPlayOut paramPacketListenerPlayOut) {
/* 41 */     paramPacketListenerPlayOut.a(this);
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\PacketPlayOutMount.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */