/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import com.mojang.authlib.GameProfile;
/*    */ import java.io.IOException;
/*    */ import java.util.UUID;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PacketLoginOutSuccess
/*    */   implements Packet<PacketLoginOutListener>
/*    */ {
/*    */   private GameProfile a;
/*    */   
/*    */   public PacketLoginOutSuccess() {}
/*    */   
/*    */   public PacketLoginOutSuccess(GameProfile paramGameProfile) {
/* 19 */     this.a = paramGameProfile;
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketDataSerializer paramPacketDataSerializer) throws IOException {
/* 24 */     String str1 = paramPacketDataSerializer.e(36);
/* 25 */     String str2 = paramPacketDataSerializer.e(16);
/* 26 */     UUID uUID = UUID.fromString(str1);
/* 27 */     this.a = new GameProfile(uUID, str2);
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer paramPacketDataSerializer) throws IOException {
/* 32 */     UUID uUID = this.a.getId();
/* 33 */     paramPacketDataSerializer.a((uUID == null) ? "" : uUID.toString());
/* 34 */     paramPacketDataSerializer.a(this.a.getName());
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketLoginOutListener paramPacketLoginOutListener) {
/* 39 */     paramPacketLoginOutListener.a(this);
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\PacketLoginOutSuccess.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */