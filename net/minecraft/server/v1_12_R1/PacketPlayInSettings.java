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
/*    */ public class PacketPlayInSettings
/*    */   implements Packet<PacketListenerPlayIn>
/*    */ {
/*    */   private String a;
/*    */   private int b;
/*    */   private EntityHuman.EnumChatVisibility c;
/*    */   private boolean d;
/*    */   private int e;
/*    */   private EnumMainHand f;
/*    */   
/*    */   public void a(PacketDataSerializer paramPacketDataSerializer) throws IOException {
/* 35 */     this.a = paramPacketDataSerializer.e(16);
/* 36 */     this.b = paramPacketDataSerializer.readByte();
/*    */     
/* 38 */     this.c = paramPacketDataSerializer.<EntityHuman.EnumChatVisibility>a(EntityHuman.EnumChatVisibility.class);
/* 39 */     this.d = paramPacketDataSerializer.readBoolean();
/*    */     
/* 41 */     this.e = paramPacketDataSerializer.readUnsignedByte();
/* 42 */     this.f = paramPacketDataSerializer.<EnumMainHand>a(EnumMainHand.class);
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer paramPacketDataSerializer) throws IOException {
/* 47 */     paramPacketDataSerializer.a(this.a);
/* 48 */     paramPacketDataSerializer.writeByte(this.b);
/* 49 */     paramPacketDataSerializer.a(this.c);
/* 50 */     paramPacketDataSerializer.writeBoolean(this.d);
/* 51 */     paramPacketDataSerializer.writeByte(this.e);
/* 52 */     paramPacketDataSerializer.a(this.f);
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketListenerPlayIn paramPacketListenerPlayIn) {
/* 57 */     paramPacketListenerPlayIn.a(this);
/*    */   }
/*    */   
/*    */   public String a() {
/* 61 */     return this.a;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public EntityHuman.EnumChatVisibility c() {
/* 69 */     return this.c;
/*    */   }
/*    */   
/*    */   public boolean d() {
/* 73 */     return this.d;
/*    */   }
/*    */   
/*    */   public int e() {
/* 77 */     return this.e;
/*    */   }
/*    */   
/*    */   public EnumMainHand getMainHand() {
/* 81 */     return this.f;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\PacketPlayInSettings.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */