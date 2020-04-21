/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import java.io.IOException;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PacketPlayInClientCommand
/*    */   implements Packet<PacketListenerPlayIn>
/*    */ {
/*    */   private EnumClientCommand a;
/*    */   
/*    */   public PacketPlayInClientCommand() {}
/*    */   
/*    */   public PacketPlayInClientCommand(EnumClientCommand paramEnumClientCommand) {
/* 16 */     this.a = paramEnumClientCommand;
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketDataSerializer paramPacketDataSerializer) throws IOException {
/* 21 */     this.a = paramPacketDataSerializer.<EnumClientCommand>a(EnumClientCommand.class);
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer paramPacketDataSerializer) throws IOException {
/* 26 */     paramPacketDataSerializer.a(this.a);
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketListenerPlayIn paramPacketListenerPlayIn) {
/* 31 */     paramPacketListenerPlayIn.a(this);
/*    */   }
/*    */   
/*    */   public EnumClientCommand a() {
/* 35 */     return this.a;
/*    */   }
/*    */   
/*    */   public enum EnumClientCommand {
/* 39 */     PERFORM_RESPAWN,
/* 40 */     REQUEST_STATS;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\PacketPlayInClientCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */