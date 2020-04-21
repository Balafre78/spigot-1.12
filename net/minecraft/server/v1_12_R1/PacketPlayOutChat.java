/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import net.md_5.bungee.api.chat.BaseComponent;
/*    */ import net.md_5.bungee.chat.ComponentSerializer;
/*    */ 
/*    */ public class PacketPlayOutChat
/*    */   implements Packet<PacketListenerPlayOut>
/*    */ {
/*    */   private IChatBaseComponent a;
/*    */   public BaseComponent[] components;
/*    */   
/*    */   public PacketPlayOutChat(IChatBaseComponent ichatbasecomponent) {
/* 14 */     this(ichatbasecomponent, ChatMessageType.SYSTEM);
/*    */   } private ChatMessageType b;
/*    */   public PacketPlayOutChat() {}
/*    */   public PacketPlayOutChat(IChatBaseComponent ichatbasecomponent, ChatMessageType chatmessagetype) {
/* 18 */     this.a = ichatbasecomponent;
/* 19 */     this.b = chatmessagetype;
/*    */   }
/*    */   
/*    */   public void a(PacketDataSerializer packetdataserializer) throws IOException {
/* 23 */     this.a = packetdataserializer.f();
/* 24 */     this.b = ChatMessageType.a(packetdataserializer.readByte());
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer packetdataserializer) throws IOException {
/* 29 */     if (this.components != null) {
/* 30 */       packetdataserializer.a(ComponentSerializer.toString(this.components));
/*    */     } else {
/* 32 */       packetdataserializer.a(this.a);
/*    */     } 
/*    */     
/* 35 */     packetdataserializer.writeByte(this.b.a());
/*    */   }
/*    */   
/*    */   public void a(PacketListenerPlayOut packetlistenerplayout) {
/* 39 */     packetlistenerplayout.a(this);
/*    */   }
/*    */   
/*    */   public boolean b() {
/* 43 */     return !(this.b != ChatMessageType.SYSTEM && this.b != ChatMessageType.GAME_INFO);
/*    */   }
/*    */   
/*    */   public ChatMessageType c() {
/* 47 */     return this.b;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\PacketPlayOutChat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */