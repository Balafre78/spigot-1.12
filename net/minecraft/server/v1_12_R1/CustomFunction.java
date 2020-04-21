/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.ArrayDeque;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.command.CommandSender;
/*     */ 
/*     */ public class CustomFunction {
/*     */   private final c[] a;
/*     */   
/*     */   public CustomFunction(c[] acustomfunction_c) {
/*  15 */     this.a = acustomfunction_c;
/*     */   }
/*     */   
/*     */   public c[] a() {
/*  19 */     return this.a;
/*     */   }
/*     */   
/*     */   public static CustomFunction a(CustomFunctionData customfunctiondata, List<String> list) {
/*  23 */     ArrayList<b> arraylist = Lists.newArrayListWithCapacity(list.size());
/*  24 */     Iterator<String> iterator = list.iterator();
/*     */     
/*  26 */     while (iterator.hasNext()) {
/*  27 */       String s = iterator.next();
/*     */       
/*  29 */       s = s.trim();
/*  30 */       if (!s.startsWith("#") && !s.isEmpty()) {
/*  31 */         String[] astring = s.split(" ", 2);
/*  32 */         String s1 = astring[0];
/*     */         
/*  34 */         if (!customfunctiondata.a().getCommands().containsKey(s1)) {
/*  35 */           if (s1.startsWith("//")) {
/*  36 */             throw new IllegalArgumentException("Unknown or invalid command '" + s1 + "' (if you intended to make a comment, use '#' not '//')");
/*     */           }
/*     */           
/*  39 */           if (s1.startsWith("/") && s1.length() > 1) {
/*  40 */             throw new IllegalArgumentException("Unknown or invalid command '" + s1 + "' (did you mean '" + s1.substring(1) + "'? Do not use a preceding forwards slash.)");
/*     */           }
/*     */           
/*  43 */           throw new IllegalArgumentException("Unknown or invalid command '" + s1 + "'");
/*     */         } 
/*     */         
/*  46 */         arraylist.add(new b(s));
/*     */       } 
/*     */     } 
/*     */     
/*  50 */     return new CustomFunction(arraylist.<c>toArray(new c[arraylist.size()]));
/*     */   }
/*     */   
/*     */   public static class a
/*     */   {
/*  55 */     public static final a a = new a(null);
/*     */     @Nullable
/*     */     private final MinecraftKey b;
/*     */     private boolean c;
/*     */     private CustomFunction d;
/*     */     
/*     */     public a(@Nullable MinecraftKey minecraftkey) {
/*  62 */       this.b = minecraftkey;
/*     */     }
/*     */     
/*     */     public a(CustomFunction customfunction) {
/*  66 */       this.b = null;
/*  67 */       this.d = customfunction;
/*     */     }
/*     */     
/*     */     @Nullable
/*     */     public CustomFunction a(CustomFunctionData customfunctiondata) {
/*  72 */       if (!this.c) {
/*  73 */         if (this.b != null) {
/*  74 */           this.d = customfunctiondata.a(this.b);
/*     */         }
/*     */         
/*  77 */         this.c = true;
/*     */       } 
/*     */       
/*  80 */       return this.d;
/*     */     }
/*     */     
/*     */     public String toString() {
/*  84 */       return String.valueOf(this.b);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class d
/*     */     implements c {
/*     */     private final CustomFunction.a a;
/*     */     
/*     */     public d(CustomFunction customfunction) {
/*  93 */       this.a = new CustomFunction.a(customfunction);
/*     */     }
/*     */     
/*     */     public void a(CustomFunctionData customfunctiondata, ICommandListener icommandlistener, ArrayDeque<CustomFunctionData.a> arraydeque, int i) {
/*  97 */       CustomFunction customfunction = this.a.a(customfunctiondata);
/*     */       
/*  99 */       if (customfunction != null) {
/* 100 */         CustomFunction.c[] acustomfunction_c = customfunction.a();
/* 101 */         int j = i - arraydeque.size();
/* 102 */         int k = Math.min(acustomfunction_c.length, j);
/*     */         
/* 104 */         for (int l = k - 1; l >= 0; l--) {
/* 105 */           arraydeque.addFirst(new CustomFunctionData.a(customfunctiondata, icommandlistener, acustomfunction_c[l]));
/*     */         }
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public String toString() {
/* 112 */       return "/function " + this.a;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class b
/*     */     implements c {
/*     */     private final String a;
/*     */     
/*     */     public b(String s) {
/* 121 */       this.a = s;
/*     */     }
/*     */ 
/*     */     
/*     */     public void a(CustomFunctionData customfunctiondata, ICommandListener icommandlistener, ArrayDeque<CustomFunctionData.a> arraydeque, int i) {
/*     */       CommandSender sender;
/* 127 */       if (icommandlistener instanceof CustomFunctionData.CustomFunctionListener) {
/* 128 */         sender = ((CustomFunctionData.CustomFunctionListener)icommandlistener).sender;
/*     */       } else {
/* 130 */         sender = CommandBlockListenerAbstract.unwrapSender(icommandlistener);
/*     */       } 
/* 132 */       CommandBlockListenerAbstract.executeSafely(icommandlistener, sender, this.a);
/*     */     }
/*     */ 
/*     */     
/*     */     public String toString() {
/* 137 */       return "/" + this.a;
/*     */     }
/*     */   }
/*     */   
/*     */   public static interface c {
/*     */     void a(CustomFunctionData param1CustomFunctionData, ICommandListener param1ICommandListener, ArrayDeque<CustomFunctionData.a> param1ArrayDeque, int param1Int);
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\CustomFunction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */