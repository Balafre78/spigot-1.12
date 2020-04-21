/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import java.io.IOException;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PacketPlayOutEntityVelocity
/*    */   implements Packet<PacketListenerPlayOut>
/*    */ {
/*    */   private int a;
/*    */   private int b;
/*    */   private int c;
/*    */   private int d;
/*    */   
/*    */   public PacketPlayOutEntityVelocity() {}
/*    */   
/*    */   public PacketPlayOutEntityVelocity(Entity paramEntity) {
/* 19 */     this(paramEntity.getId(), paramEntity.motX, paramEntity.motY, paramEntity.motZ);
/*    */   }
/*    */   
/*    */   public PacketPlayOutEntityVelocity(int paramInt, double paramDouble1, double paramDouble2, double paramDouble3) {
/* 23 */     this.a = paramInt;
/* 24 */     double d = 3.9D;
/* 25 */     if (paramDouble1 < -3.9D) {
/* 26 */       paramDouble1 = -3.9D;
/*    */     }
/* 28 */     if (paramDouble2 < -3.9D) {
/* 29 */       paramDouble2 = -3.9D;
/*    */     }
/* 31 */     if (paramDouble3 < -3.9D) {
/* 32 */       paramDouble3 = -3.9D;
/*    */     }
/* 34 */     if (paramDouble1 > 3.9D) {
/* 35 */       paramDouble1 = 3.9D;
/*    */     }
/* 37 */     if (paramDouble2 > 3.9D) {
/* 38 */       paramDouble2 = 3.9D;
/*    */     }
/* 40 */     if (paramDouble3 > 3.9D) {
/* 41 */       paramDouble3 = 3.9D;
/*    */     }
/* 43 */     this.b = (int)(paramDouble1 * 8000.0D);
/* 44 */     this.c = (int)(paramDouble2 * 8000.0D);
/* 45 */     this.d = (int)(paramDouble3 * 8000.0D);
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketDataSerializer paramPacketDataSerializer) throws IOException {
/* 50 */     this.a = paramPacketDataSerializer.g();
/* 51 */     this.b = paramPacketDataSerializer.readShort();
/* 52 */     this.c = paramPacketDataSerializer.readShort();
/* 53 */     this.d = paramPacketDataSerializer.readShort();
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer paramPacketDataSerializer) throws IOException {
/* 58 */     paramPacketDataSerializer.d(this.a);
/* 59 */     paramPacketDataSerializer.writeShort(this.b);
/* 60 */     paramPacketDataSerializer.writeShort(this.c);
/* 61 */     paramPacketDataSerializer.writeShort(this.d);
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketListenerPlayOut paramPacketListenerPlayOut) {
/* 66 */     paramPacketListenerPlayOut.a(this);
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\PacketPlayOutEntityVelocity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */