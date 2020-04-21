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
/*    */ public class PacketPlayOutOpenWindow
/*    */   implements Packet<PacketListenerPlayOut>
/*    */ {
/*    */   private int a;
/*    */   private String b;
/*    */   private IChatBaseComponent c;
/*    */   private int d;
/*    */   private int e;
/*    */   
/*    */   public PacketPlayOutOpenWindow() {}
/*    */   
/*    */   public PacketPlayOutOpenWindow(int paramInt, String paramString, IChatBaseComponent paramIChatBaseComponent) {
/* 26 */     this(paramInt, paramString, paramIChatBaseComponent, 0);
/*    */   }
/*    */   
/*    */   public PacketPlayOutOpenWindow(int paramInt1, String paramString, IChatBaseComponent paramIChatBaseComponent, int paramInt2) {
/* 30 */     this.a = paramInt1;
/* 31 */     this.b = paramString;
/* 32 */     this.c = paramIChatBaseComponent;
/* 33 */     this.d = paramInt2;
/*    */   }
/*    */   
/*    */   public PacketPlayOutOpenWindow(int paramInt1, String paramString, IChatBaseComponent paramIChatBaseComponent, int paramInt2, int paramInt3) {
/* 37 */     this(paramInt1, paramString, paramIChatBaseComponent, paramInt2);
/* 38 */     this.e = paramInt3;
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketListenerPlayOut paramPacketListenerPlayOut) {
/* 43 */     paramPacketListenerPlayOut.a(this);
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketDataSerializer paramPacketDataSerializer) throws IOException {
/* 48 */     this.a = paramPacketDataSerializer.readUnsignedByte();
/* 49 */     this.b = paramPacketDataSerializer.e(32);
/* 50 */     this.c = paramPacketDataSerializer.f();
/* 51 */     this.d = paramPacketDataSerializer.readUnsignedByte();
/* 52 */     if (this.b.equals("EntityHorse")) {
/* 53 */       this.e = paramPacketDataSerializer.readInt();
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer paramPacketDataSerializer) throws IOException {
/* 59 */     paramPacketDataSerializer.writeByte(this.a);
/* 60 */     paramPacketDataSerializer.a(this.b);
/* 61 */     paramPacketDataSerializer.a(this.c);
/* 62 */     paramPacketDataSerializer.writeByte(this.d);
/* 63 */     if (this.b.equals("EntityHorse"))
/* 64 */       paramPacketDataSerializer.writeInt(this.e); 
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\PacketPlayOutOpenWindow.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */