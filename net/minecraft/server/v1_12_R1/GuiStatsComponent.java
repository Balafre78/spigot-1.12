/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import java.awt.Dimension;
/*    */ import java.awt.Graphics;
/*    */ import java.awt.event.ActionEvent;
/*    */ import java.awt.event.ActionListener;
/*    */ import java.text.DecimalFormat;
/*    */ import javax.swing.JComponent;
/*    */ import javax.swing.Timer;
/*    */ 
/*    */ public class GuiStatsComponent
/*    */   extends JComponent
/*    */ {
/* 15 */   private static final DecimalFormat a = new DecimalFormat("########0.000");
/*    */ 
/*    */   
/* 18 */   private final int[] b = new int[256];
/*    */   private int c;
/* 20 */   private final String[] d = new String[11];
/*    */   private final MinecraftServer e;
/*    */   
/*    */   public GuiStatsComponent(MinecraftServer paramMinecraftServer) {
/* 24 */     this.e = paramMinecraftServer;
/* 25 */     setPreferredSize(new Dimension(456, 246));
/* 26 */     setMinimumSize(new Dimension(456, 246));
/* 27 */     setMaximumSize(new Dimension(456, 246));
/* 28 */     (new Timer(500, new ActionListener(this)
/*    */         {
/*    */           public void actionPerformed(ActionEvent param1ActionEvent) {
/* 31 */             GuiStatsComponent.a(this.a);
/*    */           }
/* 33 */         })).start();
/* 34 */     setBackground(Color.BLACK);
/*    */   }
/*    */   
/*    */   private void a() {
/* 38 */     long l = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
/* 39 */     System.gc();
/* 40 */     this.d[0] = "Memory use: " + (l / 1024L / 1024L) + " mb (" + (Runtime.getRuntime().freeMemory() * 100L / Runtime.getRuntime().maxMemory()) + "% free)";
/* 41 */     this.d[1] = "Avg tick: " + a.format(a(this.e.h) * 1.0E-6D) + " ms";
/* 42 */     this.b[this.c++ & 0xFF] = (int)(l * 100L / Runtime.getRuntime().maxMemory());
/* 43 */     repaint();
/*    */   }
/*    */   
/*    */   private double a(long[] paramArrayOflong) {
/* 47 */     long l = 0L;
/* 48 */     for (long l1 : paramArrayOflong) {
/* 49 */       l += l1;
/*    */     }
/* 51 */     return l / paramArrayOflong.length;
/*    */   }
/*    */ 
/*    */   
/*    */   public void paint(Graphics paramGraphics) {
/* 56 */     paramGraphics.setColor(new Color(16777215));
/* 57 */     paramGraphics.fillRect(0, 0, 456, 246);
/*    */     byte b;
/* 59 */     for (b = 0; b < 'Ā'; b++) {
/* 60 */       int i = this.b[b + this.c & 0xFF];
/* 61 */       paramGraphics.setColor(new Color(i + 28 << 16));
/* 62 */       paramGraphics.fillRect(b, 100 - i, 1, i);
/*    */     } 
/* 64 */     paramGraphics.setColor(Color.BLACK);
/* 65 */     for (b = 0; b < this.d.length; b++) {
/* 66 */       String str = this.d[b];
/* 67 */       if (str != null)
/* 68 */         paramGraphics.drawString(str, 32, 116 + b * 16); 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\GuiStatsComponent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */