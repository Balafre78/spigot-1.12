/*     */ package net.minecraft.server.v1_12_R1;
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.common.collect.Maps;
/*     */ import com.google.common.collect.Sets;
/*     */ import com.google.common.io.Files;
/*     */ import com.google.gson.Gson;
/*     */ import com.google.gson.GsonBuilder;
/*     */ import com.google.gson.JsonParseException;
/*     */ import com.google.gson.reflect.TypeToken;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.nio.charset.StandardCharsets;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Comparator;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedHashSet;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.stream.Collectors;
/*     */ import java.util.stream.Stream;
/*     */ import javax.annotation.Nullable;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.player.PlayerAdvancementDoneEvent;
/*     */ import org.spigotmc.SpigotConfig;
/*     */ 
/*     */ public class AdvancementDataPlayer {
/*  31 */   private static final Logger a = LogManager.getLogger();
/*  32 */   private static final Gson b = (new GsonBuilder()).registerTypeAdapter(AdvancementProgress.class, new AdvancementProgress.a()).registerTypeAdapter(MinecraftKey.class, new MinecraftKey.a()).setPrettyPrinting().create();
/*  33 */   private static final TypeToken<Map<MinecraftKey, AdvancementProgress>> c = new TypeToken<Map<MinecraftKey, AdvancementProgress>>() {  }
/*     */   ;
/*     */   private final MinecraftServer d;
/*     */   private final File e;
/*  37 */   public final Map<Advancement, AdvancementProgress> data = Maps.newLinkedHashMap();
/*  38 */   private final Set<Advancement> g = Sets.newLinkedHashSet();
/*  39 */   private final Set<Advancement> h = Sets.newLinkedHashSet();
/*  40 */   private final Set<Advancement> i = Sets.newLinkedHashSet();
/*     */   private EntityPlayer player;
/*     */   @Nullable
/*     */   private Advancement k;
/*     */   private boolean l = true;
/*     */   
/*     */   public AdvancementDataPlayer(MinecraftServer minecraftserver, File file, EntityPlayer entityplayer) {
/*  47 */     this.d = minecraftserver;
/*  48 */     this.e = file;
/*  49 */     this.player = entityplayer;
/*  50 */     g();
/*     */   }
/*     */   
/*     */   public void a(EntityPlayer entityplayer) {
/*  54 */     this.player = entityplayer;
/*     */   }
/*     */   
/*     */   public void a() {
/*  58 */     Iterator<? extends CriterionTrigger<?>> iterator = CriterionTriggers.a().iterator();
/*     */     
/*  60 */     while (iterator.hasNext()) {
/*  61 */       CriterionTrigger criteriontrigger = iterator.next();
/*     */       
/*  63 */       criteriontrigger.a(this);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void b() {
/*  69 */     a();
/*  70 */     this.data.clear();
/*  71 */     this.g.clear();
/*  72 */     this.h.clear();
/*  73 */     this.i.clear();
/*  74 */     this.l = true;
/*  75 */     this.k = null;
/*  76 */     g();
/*     */   }
/*     */   
/*     */   private void d() {
/*  80 */     Iterator<Advancement> iterator = this.d.getAdvancementData().c().iterator();
/*     */     
/*  82 */     while (iterator.hasNext()) {
/*  83 */       Advancement advancement = iterator.next();
/*     */       
/*  85 */       c(advancement);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void e() {
/*  91 */     ArrayList<Map.Entry<Advancement, AdvancementProgress>> arraylist = Lists.newArrayList();
/*  92 */     Iterator<Map.Entry<Advancement, AdvancementProgress>> iterator = this.data.entrySet().iterator();
/*     */     
/*  94 */     while (iterator.hasNext()) {
/*  95 */       Map.Entry<Advancement, AdvancementProgress> entry = iterator.next();
/*     */       
/*  97 */       if (((AdvancementProgress)entry.getValue()).isDone()) {
/*  98 */         arraylist.add(entry.getKey());
/*  99 */         this.i.add(entry.getKey());
/*     */       } 
/*     */     } 
/*     */     
/* 103 */     iterator = arraylist.iterator();
/*     */     
/* 105 */     while (iterator.hasNext()) {
/* 106 */       Advancement advancement = (Advancement)iterator.next();
/*     */       
/* 108 */       e(advancement);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void f() {
/* 114 */     Iterator<Advancement> iterator = this.d.getAdvancementData().c().iterator();
/*     */     
/* 116 */     while (iterator.hasNext()) {
/* 117 */       Advancement advancement = iterator.next();
/*     */       
/* 119 */       if (advancement.getCriteria().isEmpty()) {
/* 120 */         grantCriteria(advancement, "");
/* 121 */         advancement.d().a(this.player);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void g() {
/* 128 */     if (this.e.isFile()) {
/*     */       try {
/* 130 */         String s = Files.toString(this.e, StandardCharsets.UTF_8);
/*     */         
/* 132 */         Map<MinecraftKey, AdvancementProgress> map = ChatDeserializer.<Map<MinecraftKey, AdvancementProgress>>a(b, s, c.getType());
/*     */         
/* 134 */         if (map == null) {
/* 135 */           throw new JsonParseException("Found null for advancements");
/*     */         }
/*     */         
/* 138 */         Stream stream = map.entrySet().stream().sorted(Comparator.comparing(Map.Entry::getValue));
/*     */         
/* 140 */         Iterator<Map.Entry> iterator = ((List)stream.collect(Collectors.toList())).iterator();
/*     */         
/* 142 */         while (iterator.hasNext()) {
/* 143 */           Map.Entry entry = iterator.next();
/* 144 */           Advancement advancement = this.d.getAdvancementData().a((MinecraftKey)entry.getKey());
/*     */           
/* 146 */           if (advancement == null) {
/*     */             
/* 148 */             if (((MinecraftKey)entry.getKey()).b().equals("minecraft")) {
/* 149 */               a.warn("Ignored advancement '" + entry.getKey() + "' in progress file " + this.e + " - it doesn't exist anymore?");
/*     */             }
/*     */             continue;
/*     */           } 
/* 153 */           a(advancement, (AdvancementProgress)entry.getValue());
/*     */         }
/*     */       
/* 156 */       } catch (JsonParseException jsonparseexception) {
/* 157 */         a.error("Couldn't parse player advancements in " + this.e, (Throwable)jsonparseexception);
/* 158 */       } catch (IOException ioexception) {
/* 159 */         a.error("Couldn't access player advancements in " + this.e, ioexception);
/*     */       } 
/*     */     }
/*     */     
/* 163 */     f();
/* 164 */     e();
/* 165 */     d();
/*     */   }
/*     */   
/*     */   public void c() {
/* 169 */     if (SpigotConfig.disableAdvancementSaving)
/* 170 */       return;  HashMap<MinecraftKey, AdvancementProgress> hashmap = Maps.newHashMap();
/* 171 */     Iterator<Map.Entry> iterator = this.data.entrySet().iterator();
/*     */     
/* 173 */     while (iterator.hasNext()) {
/* 174 */       Map.Entry entry = iterator.next();
/* 175 */       AdvancementProgress advancementprogress = (AdvancementProgress)entry.getValue();
/*     */       
/* 177 */       if (advancementprogress.b()) {
/* 178 */         hashmap.put(((Advancement)entry.getKey()).getName(), advancementprogress);
/*     */       }
/*     */     } 
/*     */     
/* 182 */     if (this.e.getParentFile() != null) {
/* 183 */       this.e.getParentFile().mkdirs();
/*     */     }
/*     */     
/*     */     try {
/* 187 */       Files.write(b.toJson(hashmap), this.e, StandardCharsets.UTF_8);
/* 188 */     } catch (IOException ioexception) {
/* 189 */       a.error("Couldn't save player advancements to " + this.e, ioexception);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean grantCriteria(Advancement advancement, String s) {
/* 195 */     boolean flag = false;
/* 196 */     AdvancementProgress advancementprogress = getProgress(advancement);
/* 197 */     boolean flag1 = advancementprogress.isDone();
/*     */     
/* 199 */     if (advancementprogress.a(s)) {
/* 200 */       d(advancement);
/* 201 */       this.i.add(advancement);
/* 202 */       flag = true;
/* 203 */       if (!flag1 && advancementprogress.isDone()) {
/* 204 */         this.player.world.getServer().getPluginManager().callEvent((Event)new PlayerAdvancementDoneEvent((Player)this.player.getBukkitEntity(), advancement.bukkit));
/* 205 */         advancement.d().a(this.player);
/* 206 */         if (advancement.c() != null && advancement.c().i() && this.player.world.getGameRules().getBoolean("announceAdvancements")) {
/* 207 */           this.d.getPlayerList().sendMessage(new ChatMessage("chat.type.advancement." + advancement.c().e().a(), new Object[] { this.player.getScoreboardDisplayName(), advancement.j() }));
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 212 */     if (advancementprogress.isDone()) {
/* 213 */       e(advancement);
/*     */     }
/*     */     
/* 216 */     return flag;
/*     */   }
/*     */   
/*     */   public boolean revokeCritera(Advancement advancement, String s) {
/* 220 */     boolean flag = false;
/* 221 */     AdvancementProgress advancementprogress = getProgress(advancement);
/*     */     
/* 223 */     if (advancementprogress.b(s)) {
/* 224 */       c(advancement);
/* 225 */       this.i.add(advancement);
/* 226 */       flag = true;
/*     */     } 
/*     */     
/* 229 */     if (!advancementprogress.b()) {
/* 230 */       e(advancement);
/*     */     }
/*     */     
/* 233 */     return flag;
/*     */   }
/*     */   
/*     */   private void c(Advancement advancement) {
/* 237 */     AdvancementProgress advancementprogress = getProgress(advancement);
/*     */     
/* 239 */     if (!advancementprogress.isDone()) {
/* 240 */       Iterator<Map.Entry> iterator = advancement.getCriteria().entrySet().iterator();
/*     */       
/* 242 */       while (iterator.hasNext()) {
/* 243 */         Map.Entry entry = iterator.next();
/* 244 */         CriterionProgress criterionprogress = advancementprogress.getCriterionProgress((String)entry.getKey());
/*     */         
/* 246 */         if (criterionprogress != null && !criterionprogress.a()) {
/* 247 */           CriterionInstance criterioninstance = ((Criterion)entry.getValue()).a();
/*     */           
/* 249 */           if (criterioninstance != null) {
/* 250 */             CriterionTrigger<CriterionInstance> criteriontrigger = CriterionTriggers.a(criterioninstance.a());
/*     */             
/* 252 */             if (criteriontrigger != null) {
/* 253 */               criteriontrigger.a(this, new CriterionTrigger.a<>(criterioninstance, advancement, (String)entry.getKey()));
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void d(Advancement advancement) {
/* 263 */     AdvancementProgress advancementprogress = getProgress(advancement);
/* 264 */     Iterator<Map.Entry> iterator = advancement.getCriteria().entrySet().iterator();
/*     */     
/* 266 */     while (iterator.hasNext()) {
/* 267 */       Map.Entry entry = iterator.next();
/* 268 */       CriterionProgress criterionprogress = advancementprogress.getCriterionProgress((String)entry.getKey());
/*     */       
/* 270 */       if (criterionprogress != null && (criterionprogress.a() || advancementprogress.isDone())) {
/* 271 */         CriterionInstance criterioninstance = ((Criterion)entry.getValue()).a();
/*     */         
/* 273 */         if (criterioninstance != null) {
/* 274 */           CriterionTrigger<CriterionInstance> criteriontrigger = CriterionTriggers.a(criterioninstance.a());
/*     */           
/* 276 */           if (criteriontrigger != null) {
/* 277 */             criteriontrigger.b(this, new CriterionTrigger.a<>(criterioninstance, advancement, (String)entry.getKey()));
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void b(EntityPlayer entityplayer) {
/* 286 */     if (!this.h.isEmpty() || !this.i.isEmpty()) {
/* 287 */       HashMap<MinecraftKey, AdvancementProgress> hashmap = Maps.newHashMap();
/* 288 */       LinkedHashSet<Advancement> linkedhashset = Sets.newLinkedHashSet();
/* 289 */       LinkedHashSet<MinecraftKey> linkedhashset1 = Sets.newLinkedHashSet();
/* 290 */       Iterator<Advancement> iterator = this.i.iterator();
/*     */ 
/*     */ 
/*     */       
/* 294 */       while (iterator.hasNext()) {
/* 295 */         Advancement advancement = iterator.next();
/* 296 */         if (this.g.contains(advancement)) {
/* 297 */           hashmap.put(advancement.getName(), this.data.get(advancement));
/*     */         }
/*     */       } 
/*     */       
/* 301 */       iterator = this.h.iterator();
/*     */       
/* 303 */       while (iterator.hasNext()) {
/* 304 */         Advancement advancement = iterator.next();
/* 305 */         if (this.g.contains(advancement)) {
/* 306 */           linkedhashset.add(advancement); continue;
/*     */         } 
/* 308 */         linkedhashset1.add(advancement.getName());
/*     */       } 
/*     */ 
/*     */       
/* 312 */       if (!hashmap.isEmpty() || !linkedhashset.isEmpty() || !linkedhashset1.isEmpty()) {
/* 313 */         entityplayer.playerConnection.sendPacket(new PacketPlayOutAdvancements(this.l, linkedhashset, linkedhashset1, hashmap));
/* 314 */         this.h.clear();
/* 315 */         this.i.clear();
/*     */       } 
/*     */     } 
/*     */     
/* 319 */     this.l = false;
/*     */   }
/*     */   
/*     */   public void a(@Nullable Advancement advancement) {
/* 323 */     Advancement advancement1 = this.k;
/*     */     
/* 325 */     if (advancement != null && advancement.b() == null && advancement.c() != null) {
/* 326 */       this.k = advancement;
/*     */     } else {
/* 328 */       this.k = null;
/*     */     } 
/*     */     
/* 331 */     if (advancement1 != this.k) {
/* 332 */       this.player.playerConnection.sendPacket(new PacketPlayOutSelectAdvancementTab((this.k == null) ? null : this.k.getName()));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public AdvancementProgress getProgress(Advancement advancement) {
/* 338 */     AdvancementProgress advancementprogress = this.data.get(advancement);
/*     */     
/* 340 */     if (advancementprogress == null) {
/* 341 */       advancementprogress = new AdvancementProgress();
/* 342 */       a(advancement, advancementprogress);
/*     */     } 
/*     */     
/* 345 */     return advancementprogress;
/*     */   }
/*     */   
/*     */   private void a(Advancement advancement, AdvancementProgress advancementprogress) {
/* 349 */     advancementprogress.a(advancement.getCriteria(), advancement.i());
/* 350 */     this.data.put(advancement, advancementprogress);
/*     */   }
/*     */   
/*     */   private void e(Advancement advancement) {
/* 354 */     boolean flag = f(advancement);
/* 355 */     boolean flag1 = this.g.contains(advancement);
/*     */     
/* 357 */     if (flag && !flag1) {
/* 358 */       this.g.add(advancement);
/* 359 */       this.h.add(advancement);
/* 360 */       if (this.data.containsKey(advancement)) {
/* 361 */         this.i.add(advancement);
/*     */       }
/* 363 */     } else if (!flag && flag1) {
/* 364 */       this.g.remove(advancement);
/* 365 */       this.h.add(advancement);
/*     */     } 
/*     */     
/* 368 */     if (flag != flag1 && advancement.b() != null) {
/* 369 */       e(advancement.b());
/*     */     }
/*     */     
/* 372 */     Iterator<Advancement> iterator = advancement.e().iterator();
/*     */     
/* 374 */     while (iterator.hasNext()) {
/* 375 */       Advancement advancement1 = iterator.next();
/*     */       
/* 377 */       e(advancement1);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean f(Advancement advancement) {
/* 383 */     for (int i = 0; advancement != null && i <= 2; i++) {
/* 384 */       if (i == 0 && g(advancement)) {
/* 385 */         return true;
/*     */       }
/*     */       
/* 388 */       if (advancement.c() == null) {
/* 389 */         return false;
/*     */       }
/*     */       
/* 392 */       AdvancementProgress advancementprogress = getProgress(advancement);
/*     */       
/* 394 */       if (advancementprogress.isDone()) {
/* 395 */         return true;
/*     */       }
/*     */       
/* 398 */       if (advancement.c().j()) {
/* 399 */         return false;
/*     */       }
/*     */       
/* 402 */       advancement = advancement.b();
/*     */     } 
/*     */     
/* 405 */     return false;
/*     */   }
/*     */   private boolean g(Advancement advancement) {
/*     */     Advancement advancement1;
/* 409 */     AdvancementProgress advancementprogress = getProgress(advancement);
/*     */     
/* 411 */     if (advancementprogress.isDone()) {
/* 412 */       return true;
/*     */     }
/* 414 */     Iterator<Advancement> iterator = advancement.e().iterator();
/*     */ 
/*     */ 
/*     */     
/*     */     do {
/* 419 */       if (!iterator.hasNext()) {
/* 420 */         return false;
/*     */       }
/*     */       
/* 423 */       advancement1 = iterator.next();
/* 424 */     } while (!g(advancement1));
/*     */     
/* 426 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\AdvancementDataPlayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */