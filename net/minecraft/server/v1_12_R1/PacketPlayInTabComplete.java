/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import javax.annotation.Nullable;
/*    */ import org.apache.commons.lang3.StringUtils;
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
/*    */ public class PacketPlayInTabComplete
/*    */   implements Packet<PacketListenerPlayIn>
/*    */ {
/*    */   private String a;
/*    */   private boolean b;
/*    */   @Nullable
/*    */   private BlockPosition c;
/*    */   
/*    */   public void a(PacketDataSerializer paramPacketDataSerializer) throws IOException {
/* 31 */     this.a = paramPacketDataSerializer.e(32767);
/* 32 */     this.b = paramPacketDataSerializer.readBoolean();
/* 33 */     boolean bool = paramPacketDataSerializer.readBoolean();
/* 34 */     if (bool) {
/* 35 */       this.c = paramPacketDataSerializer.e();
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void b(PacketDataSerializer paramPacketDataSerializer) throws IOException {
/* 41 */     paramPacketDataSerializer.a(StringUtils.substring(this.a, 0, 32767));
/* 42 */     paramPacketDataSerializer.writeBoolean(this.b);
/* 43 */     boolean bool = (this.c != null) ? true : false;
/* 44 */     paramPacketDataSerializer.writeBoolean(bool);
/* 45 */     if (bool) {
/* 46 */       paramPacketDataSerializer.a(this.c);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(PacketListenerPlayIn paramPacketListenerPlayIn) {
/* 52 */     paramPacketListenerPlayIn.a(this);
/*    */   }
/*    */   
/*    */   public String a() {
/* 56 */     return this.a;
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   public BlockPosition b() {
/* 61 */     return this.c;
/*    */   }
/*    */   
/*    */   public boolean c() {
/* 65 */     return this.b;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\PacketPlayInTabComplete.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */