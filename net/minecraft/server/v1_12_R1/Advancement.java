/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import com.google.common.collect.ImmutableMap;
/*     */ import com.google.common.collect.Sets;
/*     */ import com.google.gson.JsonArray;
/*     */ import com.google.gson.JsonDeserializationContext;
/*     */ import com.google.gson.JsonObject;
/*     */ import com.google.gson.JsonSyntaxException;
/*     */ import java.util.Arrays;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.function.Function;
/*     */ import javax.annotation.Nullable;
/*     */ import org.apache.commons.lang3.ArrayUtils;
/*     */ import org.bukkit.craftbukkit.v1_12_R1.advancement.CraftAdvancement;
/*     */ 
/*     */ public class Advancement {
/*     */   private final Advancement a;
/*     */   private final AdvancementDisplay b;
/*     */   private final AdvancementRewards c;
/*     */   private final MinecraftKey d;
/*     */   private final Map<String, Criterion> e;
/*     */   private final String[][] f;
/*  25 */   private final Set<Advancement> g = Sets.newLinkedHashSet();
/*     */   private final IChatBaseComponent h;
/*  27 */   public final org.bukkit.advancement.Advancement bukkit = (org.bukkit.advancement.Advancement)new CraftAdvancement(this);
/*     */   
/*     */   public Advancement(MinecraftKey minecraftkey, @Nullable Advancement advancement, @Nullable AdvancementDisplay advancementdisplay, AdvancementRewards advancementrewards, Map<String, Criterion> map, String[][] astring) {
/*  30 */     this.d = minecraftkey;
/*  31 */     this.b = advancementdisplay;
/*  32 */     this.e = (Map<String, Criterion>)ImmutableMap.copyOf(map);
/*  33 */     this.a = advancement;
/*  34 */     this.c = advancementrewards;
/*  35 */     this.f = astring;
/*  36 */     if (advancement != null) {
/*  37 */       advancement.a(this);
/*     */     }
/*     */     
/*  40 */     if (advancementdisplay == null) {
/*  41 */       this.h = new ChatComponentText(minecraftkey.toString());
/*     */     } else {
/*  43 */       this.h = new ChatComponentText("[");
/*  44 */       this.h.getChatModifier().setColor(advancementdisplay.e().c());
/*  45 */       IChatBaseComponent ichatbasecomponent = advancementdisplay.a().f();
/*  46 */       ChatComponentText chatcomponenttext = new ChatComponentText("");
/*  47 */       IChatBaseComponent ichatbasecomponent1 = ichatbasecomponent.f();
/*     */       
/*  49 */       ichatbasecomponent1.getChatModifier().setColor(advancementdisplay.e().c());
/*  50 */       chatcomponenttext.addSibling(ichatbasecomponent1);
/*  51 */       chatcomponenttext.a("\n");
/*  52 */       chatcomponenttext.addSibling(advancementdisplay.b());
/*  53 */       ichatbasecomponent.getChatModifier().setChatHoverable(new ChatHoverable(ChatHoverable.EnumHoverAction.SHOW_TEXT, chatcomponenttext));
/*  54 */       this.h.addSibling(ichatbasecomponent);
/*  55 */       this.h.a("]");
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public SerializedAdvancement a() {
/*  61 */     return new SerializedAdvancement((this.a == null) ? null : this.a.getName(), this.b, this.c, this.e, this.f);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public Advancement b() {
/*  66 */     return this.a;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public AdvancementDisplay c() {
/*  71 */     return this.b;
/*     */   }
/*     */   
/*     */   public AdvancementRewards d() {
/*  75 */     return this.c;
/*     */   }
/*     */   
/*     */   public String toString() {
/*  79 */     return "SimpleAdvancement{id=" + getName() + ", parent=" + ((this.a == null) ? "null" : (String)this.a.getName()) + ", display=" + this.b + ", rewards=" + this.c + ", criteria=" + this.e + ", requirements=" + Arrays.deepToString((Object[])this.f) + '}';
/*     */   }
/*     */   
/*     */   public Iterable<Advancement> e() {
/*  83 */     return this.g;
/*     */   }
/*     */   
/*     */   public Map<String, Criterion> getCriteria() {
/*  87 */     return this.e;
/*     */   }
/*     */   
/*     */   public void a(Advancement advancement) {
/*  91 */     this.g.add(advancement);
/*     */   }
/*     */   
/*     */   public MinecraftKey getName() {
/*  95 */     return this.d;
/*     */   }
/*     */   
/*     */   public boolean equals(Object object) {
/*  99 */     if (this == object)
/* 100 */       return true; 
/* 101 */     if (!(object instanceof Advancement)) {
/* 102 */       return false;
/*     */     }
/* 104 */     Advancement advancement = (Advancement)object;
/*     */     
/* 106 */     return this.d.equals(advancement.d);
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 111 */     return this.d.hashCode();
/*     */   }
/*     */   
/*     */   public String[][] i() {
/* 115 */     return this.f;
/*     */   }
/*     */   
/*     */   public IChatBaseComponent j() {
/* 119 */     return this.h;
/*     */   }
/*     */   
/*     */   public static class SerializedAdvancement
/*     */   {
/*     */     private final MinecraftKey a;
/*     */     private Advancement b;
/*     */     private final AdvancementDisplay c;
/*     */     private final AdvancementRewards d;
/*     */     private final Map<String, Criterion> e;
/*     */     private final String[][] f;
/*     */     
/*     */     SerializedAdvancement(@Nullable MinecraftKey minecraftkey, @Nullable AdvancementDisplay advancementdisplay, AdvancementRewards advancementrewards, Map<String, Criterion> map, String[][] astring) {
/* 132 */       this.a = minecraftkey;
/* 133 */       this.c = advancementdisplay;
/* 134 */       this.d = advancementrewards;
/* 135 */       this.e = map;
/* 136 */       this.f = astring;
/*     */     }
/*     */     
/*     */     public boolean a(Function<MinecraftKey, Advancement> function) {
/* 140 */       if (this.a == null) {
/* 141 */         return true;
/*     */       }
/* 143 */       this.b = function.apply(this.a);
/* 144 */       return (this.b != null);
/*     */     }
/*     */ 
/*     */     
/*     */     public Advancement a(MinecraftKey minecraftkey) {
/* 149 */       return new Advancement(minecraftkey, this.b, this.c, this.d, this.e, this.f);
/*     */     }
/*     */     
/*     */     public void a(PacketDataSerializer packetdataserializer) {
/* 153 */       if (this.a == null) {
/* 154 */         packetdataserializer.writeBoolean(false);
/*     */       } else {
/* 156 */         packetdataserializer.writeBoolean(true);
/* 157 */         packetdataserializer.a(this.a);
/*     */       } 
/*     */       
/* 160 */       if (this.c == null) {
/* 161 */         packetdataserializer.writeBoolean(false);
/*     */       } else {
/* 163 */         packetdataserializer.writeBoolean(true);
/* 164 */         this.c.a(packetdataserializer);
/*     */       } 
/*     */       
/* 167 */       Criterion.a(this.e, packetdataserializer);
/* 168 */       packetdataserializer.d(this.f.length);
/* 169 */       String[][] astring = this.f;
/* 170 */       int i = astring.length;
/*     */       
/* 172 */       for (int j = 0; j < i; j++) {
/* 173 */         String[] astring1 = astring[j];
/*     */         
/* 175 */         packetdataserializer.d(astring1.length);
/* 176 */         String[] astring2 = astring1;
/* 177 */         int k = astring1.length;
/*     */         
/* 179 */         for (int l = 0; l < k; l++) {
/* 180 */           String s = astring2[l];
/*     */           
/* 182 */           packetdataserializer.a(s);
/*     */         } 
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public String toString() {
/* 189 */       return "Task Advancement{parentId=" + this.a + ", display=" + this.c + ", rewards=" + this.d + ", criteria=" + this.e + ", requirements=" + Arrays.deepToString((Object[])this.f) + '}';
/*     */     }
/*     */     
/*     */     public static SerializedAdvancement a(JsonObject jsonobject, JsonDeserializationContext jsondeserializationcontext) {
/* 193 */       MinecraftKey minecraftkey = jsonobject.has("parent") ? new MinecraftKey(ChatDeserializer.h(jsonobject, "parent")) : null;
/* 194 */       AdvancementDisplay advancementdisplay = jsonobject.has("display") ? AdvancementDisplay.a(ChatDeserializer.t(jsonobject, "display"), jsondeserializationcontext) : null;
/* 195 */       AdvancementRewards advancementrewards = ChatDeserializer.<AdvancementRewards>a(jsonobject, "rewards", AdvancementRewards.a, jsondeserializationcontext, AdvancementRewards.class);
/* 196 */       Map<String, Criterion> map = Criterion.b(ChatDeserializer.t(jsonobject, "criteria"), jsondeserializationcontext);
/*     */       
/* 198 */       if (map.isEmpty()) {
/* 199 */         throw new JsonSyntaxException("Advancement criteria cannot be empty");
/*     */       }
/* 201 */       JsonArray jsonarray = ChatDeserializer.a(jsonobject, "requirements", new JsonArray());
/* 202 */       String[][] astring = new String[jsonarray.size()][];
/*     */ 
/*     */       
/*     */       int i;
/*     */       
/* 207 */       for (i = 0; i < jsonarray.size(); i++) {
/* 208 */         JsonArray jsonarray1 = ChatDeserializer.n(jsonarray.get(i), "requirements[" + i + "]");
/*     */         
/* 210 */         astring[i] = new String[jsonarray1.size()];
/*     */         
/* 212 */         for (int m = 0; m < jsonarray1.size(); m++) {
/* 213 */           astring[i][m] = ChatDeserializer.a(jsonarray1.get(m), "requirements[" + i + "][" + m + "]");
/*     */         }
/*     */       } 
/*     */       
/* 217 */       if (astring.length == 0) {
/* 218 */         astring = new String[map.size()][];
/* 219 */         i = 0;
/*     */ 
/*     */ 
/*     */         
/* 223 */         for (Iterator<String> iterator = map.keySet().iterator(); iterator.hasNext(); (new String[1])[0] = s, astring[i++] = new String[1]) {
/* 224 */           String s = iterator.next();
/*     */         }
/*     */       } 
/*     */       
/* 228 */       String[][] astring1 = astring;
/* 229 */       int k = astring.length;
/*     */ 
/*     */ 
/*     */       
/* 233 */       for (int j = 0; j < k; j++) {
/* 234 */         String[] astring2 = astring1[j];
/*     */         
/* 236 */         if (astring2.length == 0 && map.isEmpty()) {
/* 237 */           throw new JsonSyntaxException("Requirement entry cannot be empty");
/*     */         }
/*     */         
/* 240 */         String[] astring3 = astring2;
/*     */         
/* 242 */         int l = astring2.length;
/*     */         
/* 244 */         for (int i1 = 0; i1 < l; i1++) {
/* 245 */           String s1 = astring3[i1];
/*     */           
/* 247 */           if (!map.containsKey(s1)) {
/* 248 */             throw new JsonSyntaxException("Unknown required criterion '" + s1 + "'");
/*     */           }
/*     */         } 
/*     */       } 
/*     */       
/* 253 */       Iterator<String> iterator1 = map.keySet().iterator();
/*     */       
/* 255 */       while (iterator1.hasNext()) {
/* 256 */         String s2 = iterator1.next();
/* 257 */         boolean flag = false;
/* 258 */         String[][] astring4 = astring;
/* 259 */         int j1 = astring.length;
/*     */         
/* 261 */         int l = 0;
/*     */ 
/*     */         
/* 264 */         while (l < j1) {
/* 265 */           String[] astring5 = astring4[l];
/*     */           
/* 267 */           if (!ArrayUtils.contains((Object[])astring5, s2)) {
/* 268 */             l++;
/*     */             
/*     */             continue;
/*     */           } 
/* 272 */           flag = true;
/*     */         } 
/*     */         
/* 275 */         if (!flag) {
/* 276 */           throw new JsonSyntaxException("Criterion '" + s2 + "' isn't a requirement for completion. This isn't supported behaviour, all criteria must be required.");
/*     */         }
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 282 */       return new SerializedAdvancement(minecraftkey, advancementdisplay, advancementrewards, map, astring);
/*     */     }
/*     */ 
/*     */     
/*     */     public static SerializedAdvancement b(PacketDataSerializer packetdataserializer) {
/* 287 */       MinecraftKey minecraftkey = packetdataserializer.readBoolean() ? packetdataserializer.l() : null;
/* 288 */       AdvancementDisplay advancementdisplay = packetdataserializer.readBoolean() ? AdvancementDisplay.b(packetdataserializer) : null;
/* 289 */       Map<String, Criterion> map = Criterion.c(packetdataserializer);
/* 290 */       String[][] astring = new String[packetdataserializer.g()][];
/*     */       
/* 292 */       for (int i = 0; i < astring.length; i++) {
/* 293 */         astring[i] = new String[packetdataserializer.g()];
/*     */         
/* 295 */         for (int j = 0; j < (astring[i]).length; j++) {
/* 296 */           astring[i][j] = packetdataserializer.e(32767);
/*     */         }
/*     */       } 
/*     */       
/* 300 */       return new SerializedAdvancement(minecraftkey, advancementdisplay, AdvancementRewards.a, map, astring);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\Advancement.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */