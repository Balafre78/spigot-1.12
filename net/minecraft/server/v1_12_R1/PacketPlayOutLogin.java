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
/*    */ public class PacketPlayOutLogin
/*    */   implements Packet<PacketListenerPlayOut>
/*    */ {
/*    */   private int a;
/*    */   private boolean b;
/*    */   private EnumGamemode c;
/*    */   private int d;
/*    */   private EnumDifficulty e;
/*    */   private int f;
/*    */   private WorldType g;
/*    */   private boolean h;
/*    */   
/*    */   public PacketPlayOutLogin() {}
/*    */   
/*    */   public PacketPlayOutLogin(int paramInt1, EnumGamemode paramEnumGamemode, boolean paramBoolean1, int paramInt2, EnumDifficulty paramEnumDifficulty, int paramInt3, WorldType paramWorldType, boolean paramBoolean2) {
/* 30 */     this.a = paramInt1;
/* 31 */     this.d = paramInt2;
/* 32 */     this.e = paramEnumDifficulty;
/* 33 */     this.c = paramEnumGamemode;
/* 34 */     this.f = paramInt3;
/* 35 */     this.b = paramBoolean1;
/* 36 */     this.g = paramWorldType;
/* 37 */     this.h = paramBoolean2;
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketDataSerializer paramPacketDataSerializer) throws IOException {
/* 42 */     this.a = paramPacketDataSerializer.readInt();
/*    */     
/* 44 */     short s = paramPacketDataSerializer.readUnsignedByte();
/* 45 */     this.b = ((s & 0x8) == 8);
/* 46 */     int i = s & 0xFFFFFFF7;
/* 47 */     this.c = EnumGamemode.getById(i);
/*    */     
/* 49 */     this.d = paramPacketDataSerializer.readInt();
/* 50 */     this.e = EnumDifficulty.getById(paramPacketDataSerializer.readUnsignedByte());
/* 51 */     this.f = paramPacketDataSerializer.readUnsignedByte();
/* 52 */     this.g = WorldType.getType(paramPacketDataSerializer.e(16));
/* 53 */     if (this.g == null) {
/* 54 */       this.g = WorldType.NORMAL;
/*    */     }
/* 56 */     this.h = paramPacketDataSerializer.readBoolean();
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer paramPacketDataSerializer) throws IOException {
/* 61 */     paramPacketDataSerializer.writeInt(this.a);
/* 62 */     int i = this.c.getId();
/* 63 */     if (this.b) {
/* 64 */       i |= 0x8;
/*    */     }
/* 66 */     paramPacketDataSerializer.writeByte(i);
/* 67 */     paramPacketDataSerializer.writeInt(this.d);
/* 68 */     paramPacketDataSerializer.writeByte(this.e.a());
/* 69 */     paramPacketDataSerializer.writeByte(this.f);
/* 70 */     paramPacketDataSerializer.a(this.g.name());
/* 71 */     paramPacketDataSerializer.writeBoolean(this.h);
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketListenerPlayOut paramPacketListenerPlayOut) {
/* 76 */     paramPacketListenerPlayOut.a(this);
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\PacketPlayOutLogin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */