/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import com.google.common.annotations.VisibleForTesting;
/*     */ import com.google.common.collect.Iterators;
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.Arrays;
/*     */ import java.util.IllegalFormatException;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.regex.Matcher;
/*     */ import java.util.regex.Pattern;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ChatMessage
/*     */   extends ChatBaseComponent
/*     */ {
/*     */   private final String d;
/*     */   private final Object[] e;
/*  20 */   private final Object f = new Object();
/*  21 */   private long g = -1L;
/*     */   
/*     */   @VisibleForTesting
/*  24 */   List<IChatBaseComponent> b = Lists.newArrayList();
/*     */   
/*  26 */   public static final Pattern c = Pattern.compile("%(?:(\\d+)\\$)?([A-Za-z%]|$)");
/*     */   
/*     */   public ChatMessage(String paramString, Object... paramVarArgs) {
/*  29 */     this.d = paramString;
/*  30 */     this.e = paramVarArgs;
/*     */     
/*  32 */     for (Object object : paramVarArgs) {
/*  33 */       if (object instanceof IChatBaseComponent) {
/*  34 */         ((IChatBaseComponent)object).getChatModifier().setChatModifier(getChatModifier());
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   @VisibleForTesting
/*     */   synchronized void g() {
/*  41 */     synchronized (this.f) {
/*  42 */       long l = LocaleI18n.a();
/*  43 */       if (l == this.g) {
/*     */         return;
/*     */       }
/*  46 */       this.g = l;
/*  47 */       this.b.clear();
/*     */     } 
/*     */     
/*     */     try {
/*  51 */       b(LocaleI18n.get(this.d));
/*  52 */     } catch (ChatMessageException chatMessageException) {
/*  53 */       this.b.clear();
/*     */       try {
/*  55 */         b(LocaleI18n.b(this.d));
/*  56 */       } catch (ChatMessageException chatMessageException1) {
/*  57 */         throw chatMessageException;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void b(String paramString) {
/*  63 */     boolean bool = false;
/*  64 */     Matcher matcher = c.matcher(paramString);
/*     */     
/*  66 */     byte b = 0;
/*  67 */     int i = 0;
/*     */     
/*     */     try {
/*  70 */       while (matcher.find(i)) {
/*  71 */         int j = matcher.start();
/*  72 */         int k = matcher.end();
/*     */ 
/*     */         
/*  75 */         if (j > i) {
/*  76 */           ChatComponentText chatComponentText = new ChatComponentText(String.format(paramString.substring(i, j), new Object[0]));
/*  77 */           chatComponentText.getChatModifier().setChatModifier(getChatModifier());
/*  78 */           this.b.add(chatComponentText);
/*     */         } 
/*     */         
/*  81 */         String str1 = matcher.group(2);
/*  82 */         String str2 = paramString.substring(j, k);
/*     */ 
/*     */         
/*  85 */         if ("%".equals(str1) && "%%".equals(str2)) {
/*  86 */           ChatComponentText chatComponentText = new ChatComponentText("%");
/*  87 */           chatComponentText.getChatModifier().setChatModifier(getChatModifier());
/*  88 */           this.b.add(chatComponentText);
/*  89 */         } else if ("s".equals(str1)) {
/*  90 */           String str = matcher.group(1);
/*  91 */           byte b1 = (str != null) ? (Integer.parseInt(str) - 1) : b++;
/*  92 */           if (b1 < this.e.length) {
/*  93 */             this.b.add(a(b1));
/*     */           }
/*     */         } else {
/*  96 */           throw new ChatMessageException(this, "Unsupported format: '" + str2 + "'");
/*     */         } 
/*     */         
/*  99 */         i = k;
/*     */       } 
/*     */ 
/*     */       
/* 103 */       if (i < paramString.length()) {
/* 104 */         ChatComponentText chatComponentText = new ChatComponentText(String.format(paramString.substring(i), new Object[0]));
/* 105 */         chatComponentText.getChatModifier().setChatModifier(getChatModifier());
/* 106 */         this.b.add(chatComponentText);
/*     */       } 
/* 108 */     } catch (IllegalFormatException illegalFormatException) {
/* 109 */       throw new ChatMessageException(this, illegalFormatException);
/*     */     } 
/*     */   }
/*     */   private IChatBaseComponent a(int paramInt) {
/*     */     IChatBaseComponent iChatBaseComponent;
/* 114 */     if (paramInt >= this.e.length) {
/* 115 */       throw new ChatMessageException(this, paramInt);
/*     */     }
/*     */     
/* 118 */     Object object = this.e[paramInt];
/*     */ 
/*     */     
/* 121 */     if (object instanceof IChatBaseComponent) {
/* 122 */       iChatBaseComponent = (IChatBaseComponent)object;
/*     */     } else {
/* 124 */       iChatBaseComponent = new ChatComponentText((object == null) ? "null" : object.toString());
/* 125 */       iChatBaseComponent.getChatModifier().setChatModifier(getChatModifier());
/*     */     } 
/*     */     
/* 128 */     return iChatBaseComponent;
/*     */   }
/*     */ 
/*     */   
/*     */   public IChatBaseComponent setChatModifier(ChatModifier paramChatModifier) {
/* 133 */     super.setChatModifier(paramChatModifier);
/*     */     
/* 135 */     for (Object object : this.e) {
/* 136 */       if (object instanceof IChatBaseComponent) {
/* 137 */         ((IChatBaseComponent)object).getChatModifier().setChatModifier(getChatModifier());
/*     */       }
/*     */     } 
/*     */     
/* 141 */     if (this.g > -1L) {
/* 142 */       for (IChatBaseComponent iChatBaseComponent : this.b) {
/* 143 */         iChatBaseComponent.getChatModifier().setChatModifier(paramChatModifier);
/*     */       }
/*     */     }
/*     */     
/* 147 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public Iterator<IChatBaseComponent> iterator() {
/* 152 */     g();
/*     */     
/* 154 */     return Iterators.concat(a(this.b), a(this.a));
/*     */   }
/*     */ 
/*     */   
/*     */   public String getText() {
/* 159 */     g();
/*     */     
/* 161 */     StringBuilder stringBuilder = new StringBuilder();
/*     */     
/* 163 */     for (IChatBaseComponent iChatBaseComponent : this.b) {
/* 164 */       stringBuilder.append(iChatBaseComponent.getText());
/*     */     }
/*     */     
/* 167 */     return stringBuilder.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   public ChatMessage h() {
/* 172 */     Object[] arrayOfObject = new Object[this.e.length];
/*     */     
/* 174 */     for (byte b = 0; b < this.e.length; b++) {
/* 175 */       if (this.e[b] instanceof IChatBaseComponent) {
/* 176 */         arrayOfObject[b] = ((IChatBaseComponent)this.e[b]).f();
/*     */       } else {
/* 178 */         arrayOfObject[b] = this.e[b];
/*     */       } 
/*     */     } 
/*     */     
/* 182 */     ChatMessage chatMessage = new ChatMessage(this.d, arrayOfObject);
/* 183 */     chatMessage.setChatModifier(getChatModifier().clone());
/* 184 */     for (IChatBaseComponent iChatBaseComponent : a()) {
/* 185 */       chatMessage.addSibling(iChatBaseComponent.f());
/*     */     }
/* 187 */     return chatMessage;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object paramObject) {
/* 192 */     if (this == paramObject) {
/* 193 */       return true;
/*     */     }
/*     */     
/* 196 */     if (paramObject instanceof ChatMessage) {
/* 197 */       ChatMessage chatMessage = (ChatMessage)paramObject;
/* 198 */       return (Arrays.equals(this.e, chatMessage.e) && this.d.equals(chatMessage.d) && super.equals(paramObject));
/*     */     } 
/*     */     
/* 201 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 206 */     int i = super.hashCode();
/* 207 */     i = 31 * i + this.d.hashCode();
/* 208 */     i = 31 * i + Arrays.hashCode(this.e);
/* 209 */     return i;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 214 */     return "TranslatableComponent{key='" + this.d + '\'' + ", args=" + 
/*     */       
/* 216 */       Arrays.toString(this.e) + ", siblings=" + this.a + ", style=" + 
/*     */       
/* 218 */       getChatModifier() + '}';
/*     */   }
/*     */ 
/*     */   
/*     */   public String i() {
/* 223 */     return this.d;
/*     */   }
/*     */   
/*     */   public Object[] j() {
/* 227 */     return this.e;
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\ChatMessage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */