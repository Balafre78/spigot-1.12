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
/*    */ public class PacketPlayOutEntityDestroy
/*    */   implements Packet<PacketListenerPlayOut>
/*    */ {
/*    */   private int[] a;
/*    */   
/*    */   public PacketPlayOutEntityDestroy() {}
/*    */   
/*    */   public PacketPlayOutEntityDestroy(int... paramVarArgs) {
/* 19 */     this.a = paramVarArgs;
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketDataSerializer paramPacketDataSerializer) throws IOException {
/* 24 */     this.a = new int[paramPacketDataSerializer.g()];
/*    */     
/* 26 */     for (byte b = 0; b < this.a.length; b++) {
/* 27 */       this.a[b] = paramPacketDataSerializer.g();
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer paramPacketDataSerializer) throws IOException {
/* 33 */     paramPacketDataSerializer.d(this.a.length);
/*    */     
/* 35 */     for (int i : this.a) {
/* 36 */       paramPacketDataSerializer.d(i);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketListenerPlayOut paramPacketListenerPlayOut) {
/* 42 */     paramPacketListenerPlayOut.a(this);
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\PacketPlayOutEntityDestroy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */