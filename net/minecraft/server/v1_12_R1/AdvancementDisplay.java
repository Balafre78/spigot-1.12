/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import com.google.gson.JsonDeserializationContext;
/*     */ import com.google.gson.JsonObject;
/*     */ import com.google.gson.JsonSyntaxException;
/*     */ import javax.annotation.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AdvancementDisplay
/*     */ {
/*     */   private final IChatBaseComponent a;
/*     */   private final IChatBaseComponent b;
/*     */   private final ItemStack c;
/*     */   private final MinecraftKey d;
/*     */   private final AdvancementFrameType e;
/*     */   private final boolean f;
/*     */   private final boolean g;
/*     */   private final boolean h;
/*     */   private float i;
/*     */   private float j;
/*     */   
/*     */   public AdvancementDisplay(ItemStack paramItemStack, IChatBaseComponent paramIChatBaseComponent1, IChatBaseComponent paramIChatBaseComponent2, @Nullable MinecraftKey paramMinecraftKey, AdvancementFrameType paramAdvancementFrameType, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3) {
/*  28 */     this.a = paramIChatBaseComponent1;
/*  29 */     this.b = paramIChatBaseComponent2;
/*  30 */     this.c = paramItemStack;
/*  31 */     this.d = paramMinecraftKey;
/*  32 */     this.e = paramAdvancementFrameType;
/*  33 */     this.f = paramBoolean1;
/*  34 */     this.g = paramBoolean2;
/*  35 */     this.h = paramBoolean3;
/*     */   }
/*     */   
/*     */   public void a(float paramFloat1, float paramFloat2) {
/*  39 */     this.i = paramFloat1;
/*  40 */     this.j = paramFloat2;
/*     */   }
/*     */   
/*     */   public IChatBaseComponent a() {
/*  44 */     return this.a;
/*     */   }
/*     */   
/*     */   public IChatBaseComponent b() {
/*  48 */     return this.b;
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
/*     */   public AdvancementFrameType e() {
/*  61 */     return this.e;
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
/*     */   public boolean i() {
/*  77 */     return this.g;
/*     */   }
/*     */   
/*     */   public boolean j() {
/*  81 */     return this.h;
/*     */   }
/*     */   
/*     */   public static AdvancementDisplay a(JsonObject paramJsonObject, JsonDeserializationContext paramJsonDeserializationContext) {
/*  85 */     IChatBaseComponent iChatBaseComponent1 = ChatDeserializer.<IChatBaseComponent>a(paramJsonObject, "title", paramJsonDeserializationContext, IChatBaseComponent.class);
/*  86 */     IChatBaseComponent iChatBaseComponent2 = ChatDeserializer.<IChatBaseComponent>a(paramJsonObject, "description", paramJsonDeserializationContext, IChatBaseComponent.class);
/*     */     
/*  88 */     if (iChatBaseComponent1 == null || iChatBaseComponent2 == null) {
/*  89 */       throw new JsonSyntaxException("Both title and description must be set");
/*     */     }
/*  91 */     ItemStack itemStack = a(ChatDeserializer.t(paramJsonObject, "icon"));
/*  92 */     MinecraftKey minecraftKey = paramJsonObject.has("background") ? new MinecraftKey(ChatDeserializer.h(paramJsonObject, "background")) : null;
/*  93 */     AdvancementFrameType advancementFrameType = paramJsonObject.has("frame") ? AdvancementFrameType.a(ChatDeserializer.h(paramJsonObject, "frame")) : AdvancementFrameType.TASK;
/*  94 */     boolean bool1 = ChatDeserializer.a(paramJsonObject, "show_toast", true);
/*  95 */     boolean bool2 = ChatDeserializer.a(paramJsonObject, "announce_to_chat", true);
/*  96 */     boolean bool3 = ChatDeserializer.a(paramJsonObject, "hidden", false);
/*  97 */     return new AdvancementDisplay(itemStack, iChatBaseComponent1, iChatBaseComponent2, minecraftKey, advancementFrameType, bool1, bool2, bool3);
/*     */   }
/*     */   
/*     */   private static ItemStack a(JsonObject paramJsonObject) {
/* 101 */     if (!paramJsonObject.has("item")) {
/* 102 */       throw new JsonSyntaxException("Unsupported icon type, currently only items are supported (add 'item' key)");
/*     */     }
/* 104 */     Item item = ChatDeserializer.i(paramJsonObject, "item");
/* 105 */     int i = ChatDeserializer.a(paramJsonObject, "data", 0);
/* 106 */     return new ItemStack(item, 1, i);
/*     */   }
/*     */   
/*     */   public void a(PacketDataSerializer paramPacketDataSerializer) {
/* 110 */     paramPacketDataSerializer.a(this.a);
/* 111 */     paramPacketDataSerializer.a(this.b);
/* 112 */     paramPacketDataSerializer.a(this.c);
/* 113 */     paramPacketDataSerializer.a(this.e);
/* 114 */     int i = 0;
/* 115 */     if (this.d != null) {
/* 116 */       i |= 0x1;
/*     */     }
/* 118 */     if (this.f) {
/* 119 */       i |= 0x2;
/*     */     }
/* 121 */     if (this.h) {
/* 122 */       i |= 0x4;
/*     */     }
/* 124 */     paramPacketDataSerializer.writeInt(i);
/* 125 */     if (this.d != null) {
/* 126 */       paramPacketDataSerializer.a(this.d);
/*     */     }
/* 128 */     paramPacketDataSerializer.writeFloat(this.i);
/* 129 */     paramPacketDataSerializer.writeFloat(this.j);
/*     */   }
/*     */   
/*     */   public static AdvancementDisplay b(PacketDataSerializer paramPacketDataSerializer) {
/* 133 */     IChatBaseComponent iChatBaseComponent1 = paramPacketDataSerializer.f();
/* 134 */     IChatBaseComponent iChatBaseComponent2 = paramPacketDataSerializer.f();
/* 135 */     ItemStack itemStack = paramPacketDataSerializer.k();
/* 136 */     AdvancementFrameType advancementFrameType = paramPacketDataSerializer.<AdvancementFrameType>a(AdvancementFrameType.class);
/* 137 */     int i = paramPacketDataSerializer.readInt();
/* 138 */     MinecraftKey minecraftKey = ((i & 0x1) != 0) ? paramPacketDataSerializer.l() : null;
/* 139 */     boolean bool1 = ((i & 0x2) != 0) ? true : false;
/* 140 */     boolean bool2 = ((i & 0x4) != 0) ? true : false;
/* 141 */     AdvancementDisplay advancementDisplay = new AdvancementDisplay(itemStack, iChatBaseComponent1, iChatBaseComponent2, minecraftKey, advancementFrameType, bool1, false, bool2);
/* 142 */     advancementDisplay.a(paramPacketDataSerializer.readFloat(), paramPacketDataSerializer.readFloat());
/* 143 */     return advancementDisplay;
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\AdvancementDisplay.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */