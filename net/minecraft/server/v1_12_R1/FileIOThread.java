/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import com.google.common.collect.Lists;
/*    */ import java.util.Collections;
/*    */ import java.util.List;
/*    */ 
/*    */ public class FileIOThread
/*    */   implements Runnable {
/*  9 */   private static final FileIOThread a = new FileIOThread();
/*    */   
/* 11 */   private final List<IAsyncChunkSaver> b = Collections.synchronizedList(Lists.newArrayList());
/*    */   
/*    */   private volatile long c;
/*    */   private volatile long d;
/*    */   private volatile boolean e;
/*    */   
/*    */   private FileIOThread() {
/* 18 */     Thread thread = new Thread(this, "File IO Thread");
/* 19 */     thread.setPriority(1);
/* 20 */     thread.start();
/*    */   }
/*    */   
/*    */   public static FileIOThread a() {
/* 24 */     return a;
/*    */   }
/*    */ 
/*    */   
/*    */   public void run() {
/*    */     while (true) {
/* 30 */       c();
/*    */     }
/*    */   }
/*    */   
/*    */   private void c() {
/* 35 */     for (byte b = 0; b < this.b.size(); b++) {
/* 36 */       IAsyncChunkSaver iAsyncChunkSaver = this.b.get(b);
/* 37 */       boolean bool = iAsyncChunkSaver.a();
/* 38 */       if (!bool) {
/* 39 */         this.b.remove(b--);
/* 40 */         this.d++;
/*    */       } 
/*    */       
/*    */       try {
/* 44 */         Thread.sleep(this.e ? 0L : 10L);
/* 45 */       } catch (InterruptedException interruptedException) {
/* 46 */         interruptedException.printStackTrace();
/*    */       } 
/*    */     } 
/* 49 */     if (this.b.isEmpty()) {
/*    */       try {
/* 51 */         Thread.sleep(25L);
/* 52 */       } catch (InterruptedException interruptedException) {
/* 53 */         interruptedException.printStackTrace();
/*    */       } 
/*    */     }
/*    */   }
/*    */   
/*    */   public void a(IAsyncChunkSaver paramIAsyncChunkSaver) {
/* 59 */     if (this.b.contains(paramIAsyncChunkSaver)) {
/*    */       return;
/*    */     }
/* 62 */     this.c++;
/* 63 */     this.b.add(paramIAsyncChunkSaver);
/*    */   }
/*    */   
/*    */   public void b() throws InterruptedException {
/* 67 */     this.e = true;
/* 68 */     while (this.c != this.d) {
/* 69 */       Thread.sleep(10L);
/*    */     }
/* 71 */     this.e = false;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\FileIOThread.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */