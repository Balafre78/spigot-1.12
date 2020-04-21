/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import com.google.common.collect.Sets;
/*     */ import com.google.gson.TypeAdapter;
/*     */ import com.google.gson.stream.JsonReader;
/*     */ import com.google.gson.stream.JsonWriter;
/*     */ import java.io.IOException;
/*     */ import java.util.Set;
/*     */ import javax.annotation.Nullable;
/*     */ 
/*     */ 
/*     */ public class LootTableInfo
/*     */ {
/*     */   private final float a;
/*     */   private final WorldServer b;
/*     */   private final LootTableRegistry c;
/*     */   @Nullable
/*     */   private final Entity d;
/*     */   @Nullable
/*     */   private final EntityHuman e;
/*     */   @Nullable
/*     */   private final DamageSource f;
/*  23 */   private final Set<LootTable> g = Sets.newLinkedHashSet();
/*     */   
/*     */   public LootTableInfo(float paramFloat, WorldServer paramWorldServer, LootTableRegistry paramLootTableRegistry, @Nullable Entity paramEntity, @Nullable EntityHuman paramEntityHuman, @Nullable DamageSource paramDamageSource) {
/*  26 */     this.a = paramFloat;
/*  27 */     this.b = paramWorldServer;
/*  28 */     this.c = paramLootTableRegistry;
/*  29 */     this.d = paramEntity;
/*  30 */     this.e = paramEntityHuman;
/*  31 */     this.f = paramDamageSource;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public Entity a() {
/*  36 */     return this.d;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public Entity b() {
/*  41 */     return this.e;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public Entity c() {
/*  46 */     return (this.f == null) ? null : this.f.getEntity();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean a(LootTable paramLootTable) {
/*  55 */     return this.g.add(paramLootTable);
/*     */   }
/*     */   
/*     */   public void b(LootTable paramLootTable) {
/*  59 */     this.g.remove(paramLootTable);
/*     */   }
/*     */   
/*     */   public LootTableRegistry e() {
/*  63 */     return this.c;
/*     */   }
/*     */   
/*     */   public float f() {
/*  67 */     return this.a;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public Entity a(EntityTarget paramEntityTarget) {
/*  76 */     switch (null.a[paramEntityTarget.ordinal()]) {
/*     */       case 1:
/*  78 */         return a();
/*     */       case 2:
/*  80 */         return c();
/*     */       case 3:
/*  82 */         return b();
/*     */     } 
/*  84 */     return null;
/*     */   }
/*     */   
/*     */   public static class a {
/*     */     private final WorldServer a;
/*     */     private float b;
/*     */     private Entity c;
/*     */     private EntityHuman d;
/*     */     private DamageSource e;
/*     */     
/*     */     public a(WorldServer param1WorldServer) {
/*  95 */       this.a = param1WorldServer;
/*     */     }
/*     */     
/*     */     public a a(float param1Float) {
/*  99 */       this.b = param1Float;
/* 100 */       return this;
/*     */     }
/*     */     
/*     */     public a a(Entity param1Entity) {
/* 104 */       this.c = param1Entity;
/* 105 */       return this;
/*     */     }
/*     */     
/*     */     public a a(EntityHuman param1EntityHuman) {
/* 109 */       this.d = param1EntityHuman;
/* 110 */       return this;
/*     */     }
/*     */     
/*     */     public a a(DamageSource param1DamageSource) {
/* 114 */       this.e = param1DamageSource;
/* 115 */       return this;
/*     */     }
/*     */     
/*     */     public LootTableInfo a() {
/* 119 */       return new LootTableInfo(this.b, this.a, this.a.getLootTableRegistry(), this.c, this.d, this.e);
/*     */     }
/*     */   }
/*     */   
/*     */   public enum EntityTarget {
/* 124 */     THIS("this"),
/* 125 */     KILLER("killer"),
/* 126 */     KILLER_PLAYER("killer_player");
/*     */     
/*     */     private final String d;
/*     */ 
/*     */     
/*     */     EntityTarget(String param1String1) {
/* 132 */       this.d = param1String1;
/*     */     }
/*     */     
/*     */     public static EntityTarget a(String param1String) {
/* 136 */       for (EntityTarget entityTarget : values()) {
/* 137 */         if (entityTarget.d.equals(param1String)) {
/* 138 */           return entityTarget;
/*     */         }
/*     */       } 
/* 141 */       throw new IllegalArgumentException("Invalid entity target " + param1String);
/*     */     }
/*     */     
/*     */     public static class a
/*     */       extends TypeAdapter<EntityTarget> {
/*     */       public void a(JsonWriter param2JsonWriter, LootTableInfo.EntityTarget param2EntityTarget) throws IOException {
/* 147 */         param2JsonWriter.value(LootTableInfo.EntityTarget.a(param2EntityTarget));
/*     */       }
/*     */ 
/*     */       
/*     */       public LootTableInfo.EntityTarget a(JsonReader param2JsonReader) throws IOException {
/* 152 */         return LootTableInfo.EntityTarget.a(param2JsonReader.nextString());
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\LootTableInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */