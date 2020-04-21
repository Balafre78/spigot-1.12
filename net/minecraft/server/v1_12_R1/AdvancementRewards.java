/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import com.google.gson.JsonArray;
/*     */ import com.google.gson.JsonDeserializationContext;
/*     */ import com.google.gson.JsonDeserializer;
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonObject;
/*     */ import com.google.gson.JsonParseException;
/*     */ import com.google.gson.JsonSyntaxException;
/*     */ import java.lang.reflect.Type;
/*     */ import java.util.Arrays;
/*     */ import java.util.Iterator;
/*     */ 
/*     */ public class AdvancementRewards
/*     */ {
/*  16 */   public static final AdvancementRewards a = new AdvancementRewards(0, new MinecraftKey[0], new MinecraftKey[0], CustomFunction.a.a);
/*     */   private final int b;
/*     */   private final MinecraftKey[] c;
/*     */   private final MinecraftKey[] d;
/*     */   private final CustomFunction.a e;
/*     */   
/*     */   public AdvancementRewards(int i, MinecraftKey[] aminecraftkey, MinecraftKey[] aminecraftkey1, CustomFunction.a customfunction_a) {
/*  23 */     this.b = i;
/*  24 */     this.c = aminecraftkey;
/*  25 */     this.d = aminecraftkey1;
/*  26 */     this.e = customfunction_a;
/*     */   }
/*     */   
/*     */   public void a(final EntityPlayer entityplayer) {
/*  30 */     entityplayer.giveExp(this.b);
/*  31 */     LootTableInfo loottableinfo = (new LootTableInfo.a(entityplayer.x())).a(entityplayer).a();
/*  32 */     boolean flag = false;
/*  33 */     MinecraftKey[] aminecraftkey = this.c;
/*  34 */     int i = aminecraftkey.length;
/*     */     
/*  36 */     for (int j = 0; j < i; j++) {
/*  37 */       MinecraftKey minecraftkey = aminecraftkey[j];
/*  38 */       Iterator<ItemStack> iterator = entityplayer.world.getLootTableRegistry().a(minecraftkey).a(entityplayer.getRandom(), loottableinfo).iterator();
/*     */       
/*  40 */       while (iterator.hasNext()) {
/*  41 */         ItemStack itemstack = iterator.next();
/*     */         
/*  43 */         if (entityplayer.c(itemstack)) {
/*  44 */           entityplayer.world.a((EntityHuman)null, entityplayer.locX, entityplayer.locY, entityplayer.locZ, SoundEffects.dx, SoundCategory.PLAYERS, 0.2F, ((entityplayer.getRandom().nextFloat() - entityplayer.getRandom().nextFloat()) * 0.7F + 1.0F) * 2.0F);
/*  45 */           flag = true; continue;
/*     */         } 
/*  47 */         EntityItem entityitem = entityplayer.drop(itemstack, false);
/*     */         
/*  49 */         if (entityitem != null) {
/*  50 */           entityitem.r();
/*  51 */           entityitem.d(entityplayer.getName());
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/*  57 */     if (flag) {
/*  58 */       entityplayer.defaultContainer.b();
/*     */     }
/*     */     
/*  61 */     if (this.d.length > 0) {
/*  62 */       entityplayer.a(this.d);
/*     */     }
/*     */     
/*  65 */     final MinecraftServer minecraftserver = entityplayer.server;
/*  66 */     CustomFunction customfunction = this.e.a(minecraftserver.aL());
/*     */     
/*  68 */     if (customfunction != null) {
/*  69 */       ICommandListener icommandlistener = new ICommandListener() {
/*     */           public String getName() {
/*  71 */             return entityplayer.getName();
/*     */           }
/*     */           
/*     */           public IChatBaseComponent getScoreboardDisplayName() {
/*  75 */             return entityplayer.getScoreboardDisplayName();
/*     */           }
/*     */           
/*     */           public void sendMessage(IChatBaseComponent ichatbasecomponent) {}
/*     */           
/*     */           public boolean a(int i, String s) {
/*  81 */             return (i <= 2);
/*     */           }
/*     */           
/*     */           public BlockPosition getChunkCoordinates() {
/*  85 */             return entityplayer.getChunkCoordinates();
/*     */           }
/*     */           
/*     */           public Vec3D d() {
/*  89 */             return entityplayer.d();
/*     */           }
/*     */           
/*     */           public World getWorld() {
/*  93 */             return entityplayer.world;
/*     */           }
/*     */           
/*     */           public Entity f() {
/*  97 */             return entityplayer;
/*     */           }
/*     */           
/*     */           public boolean getSendCommandFeedback() {
/* 101 */             return ((WorldServer)minecraftserver.worlds.get(0)).getGameRules().getBoolean("commandBlockOutput");
/*     */           }
/*     */           
/*     */           public void a(CommandObjectiveExecutor.EnumCommandResult commandobjectiveexecutor_enumcommandresult, int i) {
/* 105 */             entityplayer.a(commandobjectiveexecutor_enumcommandresult, i);
/*     */           }
/*     */           
/*     */           public MinecraftServer C_() {
/* 109 */             return entityplayer.C_();
/*     */           }
/*     */         };
/*     */       
/* 113 */       minecraftserver.aL().a(customfunction, icommandlistener);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 119 */     return "AdvancementRewards{experience=" + this.b + ", loot=" + Arrays.toString((Object[])this.c) + ", recipes=" + Arrays.toString((Object[])this.d) + ", function=" + this.e + '}';
/*     */   }
/*     */   
/*     */   public static class a
/*     */     implements JsonDeserializer<AdvancementRewards>
/*     */   {
/*     */     public AdvancementRewards a(JsonElement jsonelement, Type type, JsonDeserializationContext jsondeserializationcontext) throws JsonParseException {
/*     */       CustomFunction.a customfunction_a;
/* 127 */       JsonObject jsonobject = ChatDeserializer.m(jsonelement, "rewards");
/* 128 */       int i = ChatDeserializer.a(jsonobject, "experience", 0);
/* 129 */       JsonArray jsonarray = ChatDeserializer.a(jsonobject, "loot", new JsonArray());
/* 130 */       MinecraftKey[] aminecraftkey = new MinecraftKey[jsonarray.size()];
/*     */       
/* 132 */       for (int j = 0; j < aminecraftkey.length; j++) {
/* 133 */         aminecraftkey[j] = new MinecraftKey(ChatDeserializer.a(jsonarray.get(j), "loot[" + j + "]"));
/*     */       }
/*     */       
/* 136 */       JsonArray jsonarray1 = ChatDeserializer.a(jsonobject, "recipes", new JsonArray());
/* 137 */       MinecraftKey[] aminecraftkey1 = new MinecraftKey[jsonarray1.size()];
/*     */       
/* 139 */       for (int k = 0; k < aminecraftkey1.length; k++) {
/* 140 */         aminecraftkey1[k] = new MinecraftKey(ChatDeserializer.a(jsonarray1.get(k), "recipes[" + k + "]"));
/* 141 */         IRecipe irecipe = CraftingManager.a(aminecraftkey1[k]);
/*     */         
/* 143 */         if (irecipe == null) {
/* 144 */           throw new JsonSyntaxException("Unknown recipe '" + aminecraftkey1[k] + "'");
/*     */         }
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 150 */       if (jsonobject.has("function")) {
/* 151 */         customfunction_a = new CustomFunction.a(new MinecraftKey(ChatDeserializer.h(jsonobject, "function")));
/*     */       } else {
/* 153 */         customfunction_a = CustomFunction.a.a;
/*     */       } 
/*     */       
/* 156 */       return new AdvancementRewards(i, aminecraftkey, aminecraftkey1, customfunction_a);
/*     */     }
/*     */     
/*     */     public AdvancementRewards deserialize(JsonElement jsonelement, Type type, JsonDeserializationContext jsondeserializationcontext) throws JsonParseException {
/* 160 */       return a(jsonelement, type, jsondeserializationcontext);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\AdvancementRewards.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */