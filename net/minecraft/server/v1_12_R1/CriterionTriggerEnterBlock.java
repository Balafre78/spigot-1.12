/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import com.google.common.base.Optional;
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.common.collect.Maps;
/*     */ import com.google.common.collect.Sets;
/*     */ import com.google.gson.JsonDeserializationContext;
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonObject;
/*     */ import com.google.gson.JsonSyntaxException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import javax.annotation.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CriterionTriggerEnterBlock
/*     */   implements CriterionTrigger<CriterionTriggerEnterBlock.b>
/*     */ {
/*  27 */   private static final MinecraftKey a = new MinecraftKey("enter_block");
/*  28 */   private final Map<AdvancementDataPlayer, a> b = Maps.newHashMap();
/*     */ 
/*     */   
/*     */   public MinecraftKey a() {
/*  32 */     return a;
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(AdvancementDataPlayer paramAdvancementDataPlayer, CriterionTrigger.a<b> parama) {
/*  37 */     a a1 = this.b.get(paramAdvancementDataPlayer);
/*  38 */     if (a1 == null) {
/*  39 */       a1 = new a(paramAdvancementDataPlayer);
/*  40 */       this.b.put(paramAdvancementDataPlayer, a1);
/*     */     } 
/*  42 */     a1.a(parama);
/*     */   }
/*     */ 
/*     */   
/*     */   public void b(AdvancementDataPlayer paramAdvancementDataPlayer, CriterionTrigger.a<b> parama) {
/*  47 */     a a1 = this.b.get(paramAdvancementDataPlayer);
/*  48 */     if (a1 != null) {
/*  49 */       a1.b(parama);
/*  50 */       if (a1.a()) {
/*  51 */         this.b.remove(paramAdvancementDataPlayer);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(AdvancementDataPlayer paramAdvancementDataPlayer) {
/*  58 */     this.b.remove(paramAdvancementDataPlayer);
/*     */   }
/*     */ 
/*     */   
/*     */   public b b(JsonObject paramJsonObject, JsonDeserializationContext paramJsonDeserializationContext) {
/*  63 */     Block block = null;
/*  64 */     if (paramJsonObject.has("block")) {
/*  65 */       MinecraftKey minecraftKey = new MinecraftKey(ChatDeserializer.h(paramJsonObject, "block"));
/*  66 */       if (Block.REGISTRY.d(minecraftKey)) {
/*  67 */         block = Block.REGISTRY.get(minecraftKey);
/*     */       } else {
/*  69 */         throw new JsonSyntaxException("Unknown block type '" + minecraftKey + "'");
/*     */       } 
/*     */     } 
/*  72 */     HashMap<IBlockState<?>, Object> hashMap = null;
/*  73 */     if (paramJsonObject.has("state")) {
/*  74 */       if (block == null) {
/*  75 */         throw new JsonSyntaxException("Can't define block state without a specific block type");
/*     */       }
/*  77 */       BlockStateList blockStateList = block.s();
/*  78 */       for (Map.Entry entry : ChatDeserializer.t(paramJsonObject, "state").entrySet()) {
/*  79 */         IBlockState<?> iBlockState = blockStateList.a((String)entry.getKey());
/*  80 */         if (iBlockState == null) {
/*  81 */           throw new JsonSyntaxException("Unknown block state property '" + (String)entry.getKey() + "' for block '" + Block.REGISTRY.b(block) + "'");
/*     */         }
/*  83 */         String str = ChatDeserializer.a((JsonElement)entry.getValue(), (String)entry.getKey());
/*  84 */         Optional<?> optional = iBlockState.b(str);
/*  85 */         if (optional.isPresent()) {
/*  86 */           if (hashMap == null) {
/*  87 */             hashMap = Maps.newHashMap();
/*     */           }
/*  89 */           hashMap.put(iBlockState, optional.get()); continue;
/*     */         } 
/*  91 */         throw new JsonSyntaxException("Invalid block state value '" + str + "' for property '" + (String)entry.getKey() + "' on block '" + Block.REGISTRY.b(block) + "'");
/*     */       } 
/*     */     } 
/*     */     
/*  95 */     return new b(block, hashMap);
/*     */   }
/*     */   
/*     */   public void a(EntityPlayer paramEntityPlayer, IBlockData paramIBlockData) {
/*  99 */     a a = this.b.get(paramEntityPlayer.getAdvancementData());
/* 100 */     if (a != null)
/* 101 */       a.a(paramIBlockData); 
/*     */   }
/*     */   
/*     */   public static class b
/*     */     extends CriterionInstanceAbstract {
/*     */     private final Block a;
/*     */     private final Map<IBlockState<?>, Object> b;
/*     */     
/*     */     public b(@Nullable Block param1Block, @Nullable Map<IBlockState<?>, Object> param1Map) {
/* 110 */       super(CriterionTriggerEnterBlock.b());
/* 111 */       this.a = param1Block;
/* 112 */       this.b = param1Map;
/*     */     }
/*     */     
/*     */     public boolean a(IBlockData param1IBlockData) {
/* 116 */       if (this.a != null && param1IBlockData.getBlock() != this.a) {
/* 117 */         return false;
/*     */       }
/* 119 */       if (this.b != null) {
/* 120 */         for (Map.Entry<IBlockState<?>, Object> entry : this.b.entrySet()) {
/* 121 */           if (param1IBlockData.get((IBlockState<Comparable>)entry.getKey()) != entry.getValue()) {
/* 122 */             return false;
/*     */           }
/*     */         } 
/*     */       }
/* 126 */       return true;
/*     */     }
/*     */   }
/*     */   
/*     */   static class a {
/*     */     private final AdvancementDataPlayer a;
/* 132 */     private final Set<CriterionTrigger.a<CriterionTriggerEnterBlock.b>> b = Sets.newHashSet();
/*     */     
/*     */     public a(AdvancementDataPlayer param1AdvancementDataPlayer) {
/* 135 */       this.a = param1AdvancementDataPlayer;
/*     */     }
/*     */     
/*     */     public boolean a() {
/* 139 */       return this.b.isEmpty();
/*     */     }
/*     */     
/*     */     public void a(CriterionTrigger.a<CriterionTriggerEnterBlock.b> param1a) {
/* 143 */       this.b.add(param1a);
/*     */     }
/*     */     
/*     */     public void b(CriterionTrigger.a<CriterionTriggerEnterBlock.b> param1a) {
/* 147 */       this.b.remove(param1a);
/*     */     }
/*     */     
/*     */     public void a(IBlockData param1IBlockData) {
/* 151 */       ArrayList<CriterionTrigger.a<CriterionTriggerEnterBlock.b>> arrayList = null;
/* 152 */       for (CriterionTrigger.a<CriterionTriggerEnterBlock.b> a1 : this.b) {
/* 153 */         if (((CriterionTriggerEnterBlock.b)a1.a()).a(param1IBlockData)) {
/* 154 */           if (arrayList == null) {
/* 155 */             arrayList = Lists.newArrayList();
/*     */           }
/* 157 */           arrayList.add(a1);
/*     */         } 
/*     */       } 
/* 160 */       if (arrayList != null)
/* 161 */         for (CriterionTrigger.a<CriterionTriggerEnterBlock.b> a1 : arrayList)
/* 162 */           a1.a(this.a);  
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\CriterionTriggerEnterBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */