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
/*    */ public class PacketPlayInEntityAction
/*    */   implements Packet<PacketListenerPlayIn>
/*    */ {
/*    */   private int a;
/*    */   private EnumPlayerAction animation;
/*    */   private int c;
/*    */   
/*    */   public void a(PacketDataSerializer paramPacketDataSerializer) throws IOException {
/* 29 */     this.a = paramPacketDataSerializer.g();
/* 30 */     this.animation = paramPacketDataSerializer.<EnumPlayerAction>a(EnumPlayerAction.class);
/* 31 */     this.c = paramPacketDataSerializer.g();
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer paramPacketDataSerializer) throws IOException {
/* 36 */     paramPacketDataSerializer.d(this.a);
/* 37 */     paramPacketDataSerializer.a(this.animation);
/* 38 */     paramPacketDataSerializer.d(this.c);
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketListenerPlayIn paramPacketListenerPlayIn) {
/* 43 */     paramPacketListenerPlayIn.a(this);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public EnumPlayerAction b() {
/* 51 */     return this.animation;
/*    */   }
/*    */   
/*    */   public int c() {
/* 55 */     return this.c;
/*    */   }
/*    */   
/*    */   public enum EnumPlayerAction {
/* 59 */     START_SNEAKING,
/* 60 */     STOP_SNEAKING,
/* 61 */     STOP_SLEEPING,
/* 62 */     START_SPRINTING,
/* 63 */     STOP_SPRINTING,
/* 64 */     START_RIDING_JUMP,
/* 65 */     STOP_RIDING_JUMP,
/* 66 */     OPEN_INVENTORY,
/* 67 */     START_FALL_FLYING;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\PacketPlayInEntityAction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */