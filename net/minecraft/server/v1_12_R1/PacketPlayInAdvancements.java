/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import java.io.IOException;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PacketPlayInAdvancements
/*    */   implements Packet<PacketListenerPlayIn>
/*    */ {
/*    */   private Status a;
/*    */   private MinecraftKey b;
/*    */   
/*    */   public void a(PacketDataSerializer paramPacketDataSerializer) throws IOException {
/* 34 */     this.a = paramPacketDataSerializer.<Status>a(Status.class);
/* 35 */     if (this.a == Status.OPENED_TAB) {
/* 36 */       this.b = paramPacketDataSerializer.l();
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer paramPacketDataSerializer) throws IOException {
/* 42 */     paramPacketDataSerializer.a(this.a);
/* 43 */     if (this.a == Status.OPENED_TAB) {
/* 44 */       paramPacketDataSerializer.a(this.b);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketListenerPlayIn paramPacketListenerPlayIn) {
/* 50 */     paramPacketListenerPlayIn.a(this);
/*    */   }
/*    */   
/*    */   public Status b() {
/* 54 */     return this.a;
/*    */   }
/*    */   
/*    */   public MinecraftKey c() {
/* 58 */     return this.b;
/*    */   }
/*    */   
/*    */   public enum Status {
/* 62 */     OPENED_TAB,
/* 63 */     CLOSED_SCREEN;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\PacketPlayInAdvancements.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */