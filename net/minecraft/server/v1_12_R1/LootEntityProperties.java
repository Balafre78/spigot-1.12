/*    */ package net.minecraft.server.v1_12_R1;
/*    */ 
/*    */ import com.google.common.collect.Maps;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ public class LootEntityProperties
/*    */ {
/*  9 */   private static final Map<MinecraftKey, LootEntityProperty.a<?>> a = Maps.newHashMap();
/* 10 */   private static final Map<Class<? extends LootEntityProperty>, LootEntityProperty.a<?>> b = Maps.newHashMap();
/*    */   
/*    */   static {
/* 13 */     a(new LootEntityPropertyOnFire.a());
/*    */   }
/*    */ 
/*    */   
/*    */   public static <T extends LootEntityProperty> void a(LootEntityProperty.a<? extends T> parama) {
/* 18 */     MinecraftKey minecraftKey = parama.a();
/* 19 */     Class<? extends T> clazz = parama.b();
/* 20 */     if (a.containsKey(minecraftKey)) {
/* 21 */       throw new IllegalArgumentException("Can't re-register entity property name " + minecraftKey);
/*    */     }
/* 23 */     if (b.containsKey(clazz)) {
/* 24 */       throw new IllegalArgumentException("Can't re-register entity property class " + clazz.getName());
/*    */     }
/* 26 */     a.put(minecraftKey, parama);
/* 27 */     b.put(clazz, parama);
/*    */   }
/*    */   
/*    */   public static LootEntityProperty.a<?> a(MinecraftKey paramMinecraftKey) {
/* 31 */     LootEntityProperty.a<?> a = a.get(paramMinecraftKey);
/* 32 */     if (a == null) {
/* 33 */       throw new IllegalArgumentException("Unknown loot entity property '" + paramMinecraftKey + "'");
/*    */     }
/* 35 */     return a;
/*    */   }
/*    */ 
/*    */   
/*    */   public static <T extends LootEntityProperty> LootEntityProperty.a<T> a(T paramT) {
/* 40 */     LootEntityProperty.a<T> a = (LootEntityProperty.a)b.get(paramT.getClass());
/* 41 */     if (a == null) {
/* 42 */       throw new IllegalArgumentException("Unknown loot entity property " + paramT);
/*    */     }
/* 44 */     return a;
/*    */   }
/*    */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\LootEntityProperties.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */