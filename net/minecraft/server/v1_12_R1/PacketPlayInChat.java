/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import com.google.common.util.concurrent.ThreadFactoryBuilder;
/*    */ import java.io.IOException;
/*    */ import java.util.concurrent.ExecutorService;
/*    */ import java.util.concurrent.Executors;
/*    */ 
/*    */ public class PacketPlayInChat implements Packet<PacketListenerPlayIn> {
/*    */   private String a;
/*    */   
/*    */   public PacketPlayInChat(String s) {
/* 12 */     if (s.length() > 256) {
/* 13 */       s = s.substring(0, 256);
/*    */     }
/*    */     
/* 16 */     this.a = s;
/*    */   }
/*    */   public PacketPlayInChat() {}
/*    */   public void a(PacketDataSerializer packetdataserializer) throws IOException {
/* 20 */     this.a = packetdataserializer.e(256);
/*    */   }
/*    */   
/*    */   public void b(PacketDataSerializer packetdataserializer) throws IOException {
/* 24 */     packetdataserializer.a(this.a);
/*    */   }
/*    */ 
/*    */   
/* 28 */   private static final ExecutorService executors = Executors.newCachedThreadPool((
/* 29 */       new ThreadFactoryBuilder()).setDaemon(true).setNameFormat("Async Chat Thread - #%d").build());
/*    */   public void a(final PacketListenerPlayIn packetlistenerplayin) {
/* 31 */     if (!this.a.startsWith("/")) {
/*    */       
/* 33 */       executors.submit(new Runnable()
/*    */           {
/*    */ 
/*    */             
/*    */             public void run()
/*    */             {
/* 39 */               packetlistenerplayin.a(PacketPlayInChat.this);
/*    */             }
/*    */           });
/*    */       
/*    */       return;
/*    */     } 
/* 45 */     packetlistenerplayin.a(this);
/*    */   }
/*    */   
/*    */   public String a() {
/* 49 */     return this.a;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\PacketPlayInChat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */