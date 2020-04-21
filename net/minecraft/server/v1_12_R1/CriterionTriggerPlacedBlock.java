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
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CriterionTriggerPlacedBlock
/*     */   implements CriterionTrigger<CriterionTriggerPlacedBlock.b>
/*     */ {
/*  30 */   private static final MinecraftKey a = new MinecraftKey("placed_block");
/*  31 */   private final Map<AdvancementDataPlayer, a> b = Maps.newHashMap();
/*     */ 
/*     */   
/*     */   public MinecraftKey a() {
/*  35 */     return a;
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(AdvancementDataPlayer paramAdvancementDataPlayer, CriterionTrigger.a<b> parama) {
/*  40 */     a a1 = this.b.get(paramAdvancementDataPlayer);
/*  41 */     if (a1 == null) {
/*  42 */       a1 = new a(paramAdvancementDataPlayer);
/*  43 */       this.b.put(paramAdvancementDataPlayer, a1);
/*     */     } 
/*  45 */     a1.a(parama);
/*     */   }
/*     */ 
/*     */   
/*     */   public void b(AdvancementDataPlayer paramAdvancementDataPlayer, CriterionTrigger.a<b> parama) {
/*  50 */     a a1 = this.b.get(paramAdvancementDataPlayer);
/*  51 */     if (a1 != null) {
/*  52 */       a1.b(parama);
/*  53 */       if (a1.a()) {
/*  54 */         this.b.remove(paramAdvancementDataPlayer);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(AdvancementDataPlayer paramAdvancementDataPlayer) {
/*  61 */     this.b.remove(paramAdvancementDataPlayer);
/*     */   }
/*     */ 
/*     */   
/*     */   public b b(JsonObject paramJsonObject, JsonDeserializationContext paramJsonDeserializationContext) {
/*  66 */     Block block = null;
/*  67 */     if (paramJsonObject.has("block")) {
/*  68 */       MinecraftKey minecraftKey = new MinecraftKey(ChatDeserializer.h(paramJsonObject, "block"));
/*  69 */       if (Block.REGISTRY.d(minecraftKey)) {
/*  70 */         block = Block.REGISTRY.get(minecraftKey);
/*     */       } else {
/*  72 */         throw new JsonSyntaxException("Unknown block type '" + minecraftKey + "'");
/*     */       } 
/*     */     } 
/*  75 */     HashMap<IBlockState<?>, Object> hashMap = null;
/*  76 */     if (paramJsonObject.has("state")) {
/*  77 */       if (block == null) {
/*  78 */         throw new JsonSyntaxException("Can't define block state without a specific block type");
/*     */       }
/*  80 */       BlockStateList blockStateList = block.s();
/*  81 */       for (Map.Entry entry : ChatDeserializer.t(paramJsonObject, "state").entrySet()) {
/*  82 */         IBlockState<?> iBlockState = blockStateList.a((String)entry.getKey());
/*  83 */         if (iBlockState == null) {
/*  84 */           throw new JsonSyntaxException("Unknown block state property '" + (String)entry.getKey() + "' for block '" + Block.REGISTRY.b(block) + "'");
/*     */         }
/*  86 */         String str = ChatDeserializer.a((JsonElement)entry.getValue(), (String)entry.getKey());
/*  87 */         Optional<?> optional = iBlockState.b(str);
/*  88 */         if (optional.isPresent()) {
/*  89 */           if (hashMap == null) {
/*  90 */             hashMap = Maps.newHashMap();
/*     */           }
/*  92 */           hashMap.put(iBlockState, optional.get()); continue;
/*     */         } 
/*  94 */         throw new JsonSyntaxException("Invalid block state value '" + str + "' for property '" + (String)entry.getKey() + "' on block '" + Block.REGISTRY.b(block) + "'");
/*     */       } 
/*     */     } 
/*     */     
/*  98 */     CriterionConditionLocation criterionConditionLocation = CriterionConditionLocation.a(paramJsonObject.get("location"));
/*  99 */     CriterionConditionItem criterionConditionItem = CriterionConditionItem.a(paramJsonObject.get("item"));
/*     */     
/* 101 */     return new b(block, hashMap, criterionConditionLocation, criterionConditionItem);
/*     */   }
/*     */   
/*     */   public void a(EntityPlayer paramEntityPlayer, BlockPosition paramBlockPosition, ItemStack paramItemStack) {
/* 105 */     IBlockData iBlockData = paramEntityPlayer.world.getType(paramBlockPosition);
/* 106 */     a a = this.b.get(paramEntityPlayer.getAdvancementData());
/* 107 */     if (a != null)
/* 108 */       a.a(iBlockData, paramBlockPosition, paramEntityPlayer.x(), paramItemStack); 
/*     */   }
/*     */   
/*     */   public static class b
/*     */     extends CriterionInstanceAbstract {
/*     */     private final Block a;
/*     */     private final Map<IBlockState<?>, Object> b;
/*     */     private final CriterionConditionLocation c;
/*     */     private final CriterionConditionItem d;
/*     */     
/*     */     public b(@Nullable Block param1Block, @Nullable Map<IBlockState<?>, Object> param1Map, CriterionConditionLocation param1CriterionConditionLocation, CriterionConditionItem param1CriterionConditionItem) {
/* 119 */       super(CriterionTriggerPlacedBlock.b());
/* 120 */       this.a = param1Block;
/* 121 */       this.b = param1Map;
/* 122 */       this.c = param1CriterionConditionLocation;
/* 123 */       this.d = param1CriterionConditionItem;
/*     */     }
/*     */     
/*     */     public boolean a(IBlockData param1IBlockData, BlockPosition param1BlockPosition, WorldServer param1WorldServer, ItemStack param1ItemStack) {
/* 127 */       if (this.a != null && param1IBlockData.getBlock() != this.a) {
/* 128 */         return false;
/*     */       }
/* 130 */       if (this.b != null) {
/* 131 */         for (Map.Entry<IBlockState<?>, Object> entry : this.b.entrySet()) {
/* 132 */           if (param1IBlockData.get((IBlockState<Comparable>)entry.getKey()) != entry.getValue()) {
/* 133 */             return false;
/*     */           }
/*     */         } 
/*     */       }
/* 137 */       if (!this.c.a(param1WorldServer, param1BlockPosition.getX(), param1BlockPosition.getY(), param1BlockPosition.getZ())) {
/* 138 */         return false;
/*     */       }
/* 140 */       if (!this.d.a(param1ItemStack)) {
/* 141 */         return false;
/*     */       }
/* 143 */       return true;
/*     */     }
/*     */   }
/*     */   
/*     */   static class a {
/*     */     private final AdvancementDataPlayer a;
/* 149 */     private final Set<CriterionTrigger.a<CriterionTriggerPlacedBlock.b>> b = Sets.newHashSet();
/*     */     
/*     */     public a(AdvancementDataPlayer param1AdvancementDataPlayer) {
/* 152 */       this.a = param1AdvancementDataPlayer;
/*     */     }
/*     */     
/*     */     public boolean a() {
/* 156 */       return this.b.isEmpty();
/*     */     }
/*     */     
/*     */     public void a(CriterionTrigger.a<CriterionTriggerPlacedBlock.b> param1a) {
/* 160 */       this.b.add(param1a);
/*     */     }
/*     */     
/*     */     public void b(CriterionTrigger.a<CriterionTriggerPlacedBlock.b> param1a) {
/* 164 */       this.b.remove(param1a);
/*     */     }
/*     */     
/*     */     public void a(IBlockData param1IBlockData, BlockPosition param1BlockPosition, WorldServer param1WorldServer, ItemStack param1ItemStack) {
/* 168 */       ArrayList<CriterionTrigger.a<CriterionTriggerPlacedBlock.b>> arrayList = null;
/* 169 */       for (CriterionTrigger.a<CriterionTriggerPlacedBlock.b> a1 : this.b) {
/* 170 */         if (((CriterionTriggerPlacedBlock.b)a1.a()).a(param1IBlockData, param1BlockPosition, param1WorldServer, param1ItemStack)) {
/* 171 */           if (arrayList == null) {
/* 172 */             arrayList = Lists.newArrayList();
/*     */           }
/* 174 */           arrayList.add(a1);
/*     */         } 
/*     */       } 
/* 177 */       if (arrayList != null)
/* 178 */         for (CriterionTrigger.a<CriterionTriggerPlacedBlock.b> a1 : arrayList)
/* 179 */           a1.a(this.a);  
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\CriterionTriggerPlacedBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */