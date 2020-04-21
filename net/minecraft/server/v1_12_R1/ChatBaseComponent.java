/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import com.google.common.base.Function;
/*     */ import com.google.common.collect.Iterators;
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import javax.annotation.Nullable;
/*     */ 
/*     */ public abstract class ChatBaseComponent
/*     */   implements IChatBaseComponent {
/*  12 */   protected List<IChatBaseComponent> a = Lists.newArrayList();
/*     */   
/*     */   private ChatModifier b;
/*     */ 
/*     */   
/*     */   public IChatBaseComponent addSibling(IChatBaseComponent ichatbasecomponent) {
/*  18 */     ichatbasecomponent.getChatModifier().setChatModifier(getChatModifier());
/*  19 */     this.a.add(ichatbasecomponent);
/*  20 */     return this;
/*     */   }
/*     */   
/*     */   public List<IChatBaseComponent> a() {
/*  24 */     return this.a;
/*     */   }
/*     */   
/*     */   public IChatBaseComponent a(String s) {
/*  28 */     return addSibling(new ChatComponentText(s));
/*     */   }
/*     */   
/*     */   public IChatBaseComponent setChatModifier(ChatModifier chatmodifier) {
/*  32 */     this.b = chatmodifier;
/*  33 */     Iterator<IChatBaseComponent> iterator = this.a.iterator();
/*     */     
/*  35 */     while (iterator.hasNext()) {
/*  36 */       IChatBaseComponent ichatbasecomponent = iterator.next();
/*     */       
/*  38 */       ichatbasecomponent.getChatModifier().setChatModifier(getChatModifier());
/*     */     } 
/*     */     
/*  41 */     return this;
/*     */   }
/*     */   
/*     */   public ChatModifier getChatModifier() {
/*  45 */     if (this.b == null) {
/*  46 */       this.b = new ChatModifier();
/*  47 */       Iterator<IChatBaseComponent> iterator = this.a.iterator();
/*     */       
/*  49 */       while (iterator.hasNext()) {
/*  50 */         IChatBaseComponent ichatbasecomponent = iterator.next();
/*     */         
/*  52 */         ichatbasecomponent.getChatModifier().setChatModifier(this.b);
/*     */       } 
/*     */     } 
/*     */     
/*  56 */     return this.b;
/*     */   }
/*     */   
/*     */   public Iterator<IChatBaseComponent> iterator() {
/*  60 */     return Iterators.concat((Iterator)Iterators.forArray((Object[])new ChatBaseComponent[] { this }, ), a(this.a));
/*     */   }
/*     */   
/*     */   public final String toPlainText() {
/*  64 */     StringBuilder stringbuilder = new StringBuilder();
/*  65 */     Iterator<IChatBaseComponent> iterator = iterator();
/*     */     
/*  67 */     while (iterator.hasNext()) {
/*  68 */       IChatBaseComponent ichatbasecomponent = iterator.next();
/*     */       
/*  70 */       stringbuilder.append(ichatbasecomponent.getText());
/*     */     } 
/*     */     
/*  73 */     return stringbuilder.toString();
/*     */   }
/*     */   
/*     */   public static Iterator<IChatBaseComponent> a(Iterable<IChatBaseComponent> iterable) {
/*  77 */     Iterator<IChatBaseComponent> iterator = Iterators.concat(Iterators.transform(iterable.iterator(), new Function() {
/*     */             public Iterator<IChatBaseComponent> a(@Nullable IChatBaseComponent ichatbasecomponent) {
/*  79 */               return ichatbasecomponent.iterator();
/*     */             }
/*     */             
/*     */             public Object apply(@Nullable Object object) {
/*  83 */               return a((IChatBaseComponent)object);
/*     */             }
/*     */           }));
/*     */     
/*  87 */     iterator = Iterators.transform(iterator, new Function() {
/*     */           public IChatBaseComponent a(@Nullable IChatBaseComponent ichatbasecomponent) {
/*  89 */             IChatBaseComponent ichatbasecomponent1 = ichatbasecomponent.f();
/*     */             
/*  91 */             ichatbasecomponent1.setChatModifier(ichatbasecomponent1.getChatModifier().n());
/*  92 */             return ichatbasecomponent1;
/*     */           }
/*     */           
/*     */           public Object apply(@Nullable Object object) {
/*  96 */             return a((IChatBaseComponent)object);
/*     */           }
/*     */         });
/*  99 */     return iterator;
/*     */   }
/*     */   
/*     */   public boolean equals(Object object) {
/* 103 */     if (this == object)
/* 104 */       return true; 
/* 105 */     if (!(object instanceof ChatBaseComponent)) {
/* 106 */       return false;
/*     */     }
/* 108 */     ChatBaseComponent chatbasecomponent = (ChatBaseComponent)object;
/*     */     
/* 110 */     return (this.a.equals(chatbasecomponent.a) && getChatModifier().equals(chatbasecomponent.getChatModifier()));
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 115 */     return 31 * getChatModifier().hashCode() + this.a.hashCode();
/*     */   }
/*     */   
/*     */   public String toString() {
/* 119 */     return "BaseComponent{style=" + this.b + ", siblings=" + this.a + '}';
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\ChatBaseComponent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */