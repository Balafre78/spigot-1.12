/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.common.collect.Maps;
/*     */ import com.google.gson.JsonDeserializationContext;
/*     */ import com.google.gson.JsonDeserializer;
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonObject;
/*     */ import com.google.gson.JsonParseException;
/*     */ import com.google.gson.JsonSerializationContext;
/*     */ import com.google.gson.JsonSerializer;
/*     */ import java.lang.reflect.Type;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Date;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import javax.annotation.Nullable;
/*     */ 
/*     */ 
/*     */ public class AdvancementProgress
/*     */   implements Comparable<AdvancementProgress>
/*     */ {
/*  25 */   private final Map<String, CriterionProgress> a = Maps.newHashMap();
/*  26 */   private String[][] b = new String[0][];
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(Map<String, Criterion> paramMap, String[][] paramArrayOfString) {
/*  32 */     Set<String> set = paramMap.keySet();
/*  33 */     for (null = this.a.entrySet().iterator(); null.hasNext(); ) {
/*  34 */       Map.Entry entry = null.next();
/*  35 */       if (!set.contains(entry.getKey())) {
/*  36 */         null.remove();
/*     */       }
/*     */     } 
/*  39 */     for (String str : set) {
/*  40 */       if (!this.a.containsKey(str)) {
/*  41 */         this.a.put(str, new CriterionProgress(this));
/*     */       }
/*     */     } 
/*  44 */     this.b = paramArrayOfString;
/*     */   }
/*     */   
/*     */   public boolean isDone() {
/*  48 */     if (this.b.length == 0) {
/*  49 */       return false;
/*     */     }
/*  51 */     for (String[] arrayOfString : this.b) {
/*  52 */       boolean bool = false;
/*  53 */       for (String str : arrayOfString) {
/*  54 */         CriterionProgress criterionProgress = getCriterionProgress(str);
/*  55 */         if (criterionProgress != null && criterionProgress.a()) {
/*  56 */           bool = true;
/*     */           break;
/*     */         } 
/*     */       } 
/*  60 */       if (!bool) {
/*  61 */         return false;
/*     */       }
/*     */     } 
/*  64 */     return true;
/*     */   }
/*     */   
/*     */   public boolean b() {
/*  68 */     for (CriterionProgress criterionProgress : this.a.values()) {
/*  69 */       if (criterionProgress.a()) {
/*  70 */         return true;
/*     */       }
/*     */     } 
/*  73 */     return false;
/*     */   }
/*     */   
/*     */   public boolean a(String paramString) {
/*  77 */     CriterionProgress criterionProgress = this.a.get(paramString);
/*  78 */     if (criterionProgress != null && !criterionProgress.a()) {
/*  79 */       criterionProgress.b();
/*  80 */       return true;
/*     */     } 
/*  82 */     return false;
/*     */   }
/*     */   
/*     */   public boolean b(String paramString) {
/*  86 */     CriterionProgress criterionProgress = this.a.get(paramString);
/*  87 */     if (criterionProgress != null && criterionProgress.a()) {
/*  88 */       criterionProgress.c();
/*  89 */       return true;
/*     */     } 
/*  91 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/*  96 */     return "AdvancementProgress{criteria=" + this.a + ", requirements=" + 
/*     */       
/*  98 */       Arrays.deepToString((Object[])this.b) + '}';
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(PacketDataSerializer paramPacketDataSerializer) {
/* 103 */     paramPacketDataSerializer.d(this.a.size());
/* 104 */     for (Map.Entry<String, CriterionProgress> entry : this.a.entrySet()) {
/* 105 */       paramPacketDataSerializer.a((String)entry.getKey());
/* 106 */       ((CriterionProgress)entry.getValue()).a(paramPacketDataSerializer);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static AdvancementProgress b(PacketDataSerializer paramPacketDataSerializer) {
/* 111 */     AdvancementProgress advancementProgress = new AdvancementProgress();
/* 112 */     int i = paramPacketDataSerializer.g();
/* 113 */     for (byte b = 0; b < i; b++) {
/* 114 */       advancementProgress.a.put(paramPacketDataSerializer.e(32767), CriterionProgress.a(paramPacketDataSerializer, advancementProgress));
/*     */     }
/* 116 */     return advancementProgress;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public CriterionProgress getCriterionProgress(String paramString) {
/* 121 */     return this.a.get(paramString);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Iterable<String> getRemainingCriteria() {
/* 167 */     ArrayList<String> arrayList = Lists.newArrayList();
/* 168 */     for (Map.Entry<String, CriterionProgress> entry : this.a.entrySet()) {
/* 169 */       if (!((CriterionProgress)entry.getValue()).a()) {
/* 170 */         arrayList.add(entry.getKey());
/*     */       }
/*     */     } 
/* 173 */     return arrayList;
/*     */   }
/*     */   
/*     */   public Iterable<String> getAwardedCriteria() {
/* 177 */     ArrayList<String> arrayList = Lists.newArrayList();
/* 178 */     for (Map.Entry<String, CriterionProgress> entry : this.a.entrySet()) {
/* 179 */       if (((CriterionProgress)entry.getValue()).a()) {
/* 180 */         arrayList.add(entry.getKey());
/*     */       }
/*     */     } 
/* 183 */     return arrayList;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public Date g() {
/* 188 */     Date date = null;
/*     */     
/* 190 */     for (CriterionProgress criterionProgress : this.a.values()) {
/* 191 */       if (criterionProgress.a() && (date == null || criterionProgress.getDate().before(date))) {
/* 192 */         date = criterionProgress.getDate();
/*     */       }
/*     */     } 
/*     */     
/* 196 */     return date;
/*     */   }
/*     */ 
/*     */   
/*     */   public int a(AdvancementProgress paramAdvancementProgress) {
/* 201 */     Date date1 = g();
/* 202 */     Date date2 = paramAdvancementProgress.g();
/*     */     
/* 204 */     if (date1 == null && date2 != null) {
/* 205 */       return 1;
/*     */     }
/* 207 */     if (date1 != null && date2 == null) {
/* 208 */       return -1;
/*     */     }
/* 210 */     if (date1 == null && date2 == null) {
/* 211 */       return 0;
/*     */     }
/*     */     
/* 214 */     return date1.compareTo(date2);
/*     */   }
/*     */   
/*     */   public static class a
/*     */     implements JsonDeserializer<AdvancementProgress>, JsonSerializer<AdvancementProgress> {
/*     */     public JsonElement a(AdvancementProgress param1AdvancementProgress, Type param1Type, JsonSerializationContext param1JsonSerializationContext) {
/* 220 */       JsonObject jsonObject1 = new JsonObject();
/* 221 */       JsonObject jsonObject2 = new JsonObject();
/* 222 */       for (Map.Entry entry : AdvancementProgress.b(param1AdvancementProgress).entrySet()) {
/* 223 */         CriterionProgress criterionProgress = (CriterionProgress)entry.getValue();
/* 224 */         if (criterionProgress.a()) {
/* 225 */           jsonObject2.add((String)entry.getKey(), criterionProgress.e());
/*     */         }
/*     */       } 
/* 228 */       if (!jsonObject2.entrySet().isEmpty()) {
/* 229 */         jsonObject1.add("criteria", (JsonElement)jsonObject2);
/*     */       }
/* 231 */       jsonObject1.addProperty("done", Boolean.valueOf(param1AdvancementProgress.isDone()));
/* 232 */       return (JsonElement)jsonObject1;
/*     */     }
/*     */ 
/*     */     
/*     */     public AdvancementProgress a(JsonElement param1JsonElement, Type param1Type, JsonDeserializationContext param1JsonDeserializationContext) throws JsonParseException {
/* 237 */       JsonObject jsonObject1 = ChatDeserializer.m(param1JsonElement, "advancement");
/* 238 */       JsonObject jsonObject2 = ChatDeserializer.a(jsonObject1, "criteria", new JsonObject());
/* 239 */       AdvancementProgress advancementProgress = new AdvancementProgress();
/*     */       
/* 241 */       for (Map.Entry entry : jsonObject2.entrySet()) {
/* 242 */         String str = (String)entry.getKey();
/* 243 */         AdvancementProgress.b(advancementProgress).put(str, CriterionProgress.a(advancementProgress, ChatDeserializer.a((JsonElement)entry.getValue(), str)));
/*     */       } 
/*     */       
/* 246 */       return advancementProgress;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\AdvancementProgress.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */