/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import com.mojang.authlib.GameProfile;
/*    */ import java.io.IOException;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PacketLoginInStart
/*    */   implements Packet<PacketLoginInListener>
/*    */ {
/*    */   private GameProfile a;
/*    */   
/*    */   public PacketLoginInStart() {}
/*    */   
/*    */   public PacketLoginInStart(GameProfile paramGameProfile) {
/* 18 */     this.a = paramGameProfile;
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketDataSerializer paramPacketDataSerializer) throws IOException {
/* 23 */     this.a = new GameProfile(null, paramPacketDataSerializer.e(16));
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer paramPacketDataSerializer) throws IOException {
/* 28 */     paramPacketDataSerializer.a(this.a.getName());
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketLoginInListener paramPacketLoginInListener) {
/* 33 */     paramPacketLoginInListener.a(this);
/*    */   }
/*    */   
/*    */   public GameProfile a() {
/* 37 */     return this.a;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\PacketLoginInStart.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */