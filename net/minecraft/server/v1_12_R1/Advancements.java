/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import com.google.common.base.Function;
/*    */ import com.google.common.base.Functions;
/*    */ import com.google.common.collect.Maps;
/*    */ import com.google.common.collect.Sets;
/*    */ import java.util.Iterator;
/*    */ import java.util.Map;
/*    */ import java.util.Set;
/*    */ import java.util.function.Function;
/*    */ import javax.annotation.Nullable;
/*    */ import org.apache.logging.log4j.LogManager;
/*    */ import org.apache.logging.log4j.Logger;
/*    */ 
/*    */ public class Advancements
/*    */ {
/* 17 */   private static final Logger a = LogManager.getLogger();
/* 18 */   public final Map<MinecraftKey, Advancement> advancements = Maps.newHashMap();
/* 19 */   private final Set<Advancement> c = Sets.newLinkedHashSet();
/* 20 */   private final Set<Advancement> d = Sets.newLinkedHashSet();
/*    */   
/*    */   private a e;
/*    */ 
/*    */   
/*    */   public void a(Map<MinecraftKey, Advancement.SerializedAdvancement> map) {
/* 26 */     Function function = Functions.forMap(this.advancements, null);
/*    */ 
/*    */     
/* 29 */     while (!map.isEmpty()) {
/* 30 */       boolean flag = false;
/* 31 */       Iterator<Map.Entry> iterator = map.entrySet().iterator();
/*    */ 
/*    */ 
/*    */       
/* 35 */       while (iterator.hasNext()) {
/* 36 */         Map.Entry entry = iterator.next();
/* 37 */         MinecraftKey minecraftkey = (MinecraftKey)entry.getKey();
/* 38 */         Advancement.SerializedAdvancement advancement_serializedadvancement = (Advancement.SerializedAdvancement)entry.getValue();
/*    */         
/* 40 */         if (advancement_serializedadvancement.a((Function<MinecraftKey, Advancement>)function)) {
/* 41 */           Advancement advancement = advancement_serializedadvancement.a(minecraftkey);
/*    */           
/* 43 */           this.advancements.put(minecraftkey, advancement);
/* 44 */           flag = true;
/* 45 */           iterator.remove();
/* 46 */           if (advancement.b() == null) {
/* 47 */             this.c.add(advancement);
/* 48 */             if (this.e != null)
/* 49 */               this.e.a(advancement); 
/*    */             continue;
/*    */           } 
/* 52 */           this.d.add(advancement);
/* 53 */           if (this.e != null) {
/* 54 */             this.e.c(advancement);
/*    */           }
/*    */         } 
/*    */       } 
/*    */ 
/*    */       
/* 60 */       if (!flag) {
/* 61 */         iterator = map.entrySet().iterator();
/*    */ 
/*    */         
/* 64 */         while (iterator.hasNext()) {
/*    */ 
/*    */ 
/*    */           
/* 68 */           Map.Entry entry = iterator.next();
/* 69 */           a.error("Couldn't load advancement " + entry.getKey() + ": " + entry.getValue());
/*    */         } 
/*    */         break;
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void a() {
/* 78 */     this.advancements.clear();
/* 79 */     this.c.clear();
/* 80 */     this.d.clear();
/* 81 */     if (this.e != null) {
/* 82 */       this.e.a();
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public Iterable<Advancement> b() {
/* 88 */     return this.c;
/*    */   }
/*    */   
/*    */   public Iterable<Advancement> c() {
/* 92 */     return this.advancements.values();
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   public Advancement a(MinecraftKey minecraftkey) {
/* 97 */     return this.advancements.get(minecraftkey);
/*    */   }
/*    */   
/*    */   public static interface a {
/*    */     void a(Advancement param1Advancement);
/*    */     
/*    */     void c(Advancement param1Advancement);
/*    */     
/*    */     void a();
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\Advancements.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */