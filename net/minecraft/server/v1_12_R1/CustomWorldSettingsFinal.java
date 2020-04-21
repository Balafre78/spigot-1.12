/*     */ package net.minecraft.server.v1_12_R1;
/*     */ 
/*     */ import com.google.common.annotations.VisibleForTesting;
/*     */ import com.google.gson.Gson;
/*     */ import com.google.gson.GsonBuilder;
/*     */ import com.google.gson.JsonDeserializationContext;
/*     */ import com.google.gson.JsonDeserializer;
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonObject;
/*     */ import com.google.gson.JsonParseException;
/*     */ import com.google.gson.JsonSerializationContext;
/*     */ import com.google.gson.JsonSerializer;
/*     */ import java.lang.reflect.Type;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CustomWorldSettingsFinal
/*     */ {
/*     */   public final float a;
/*     */   public final float b;
/*     */   public final float c;
/*     */   public final float d;
/*     */   public final float e;
/*     */   public final float f;
/*     */   public final float g;
/*     */   public final float h;
/*     */   public final float i;
/*     */   public final float j;
/*     */   public final float k;
/*     */   public final float l;
/*     */   public final float m;
/*     */   public final float n;
/*     */   public final float o;
/*     */   public final float p;
/*     */   public final int q;
/*     */   public final boolean r;
/*     */   public final boolean s;
/*     */   public final int t;
/*     */   public final boolean u;
/*     */   public final boolean v;
/*     */   public final boolean w;
/*     */   public final boolean x;
/*     */   public final boolean y;
/*     */   public final boolean z;
/*     */   public final boolean A;
/*     */   public final boolean B;
/*     */   public final int C;
/*     */   public final boolean D;
/*     */   public final int E;
/*     */   public final boolean F;
/*     */   public final int G;
/*     */   public final int H;
/*     */   public final int I;
/*     */   public final int J;
/*     */   public final int K;
/*     */   public final int L;
/*     */   public final int M;
/*     */   public final int N;
/*     */   public final int O;
/*     */   public final int P;
/*     */   public final int Q;
/*     */   public final int R;
/*     */   public final int S;
/*     */   public final int T;
/*     */   public final int U;
/*     */   public final int V;
/*     */   public final int W;
/*     */   public final int X;
/*     */   public final int Y;
/*     */   public final int Z;
/*     */   public final int aa;
/*     */   public final int ab;
/*     */   public final int ac;
/*     */   public final int ad;
/*     */   public final int ae;
/*     */   public final int af;
/*     */   public final int ag;
/*     */   public final int ah;
/*     */   public final int ai;
/*     */   public final int aj;
/*     */   public final int ak;
/*     */   public final int al;
/*     */   public final int am;
/*     */   public final int an;
/*     */   public final int ao;
/*     */   public final int ap;
/*     */   public final int aq;
/*     */   public final int ar;
/*     */   public final int as;
/*     */   public final int at;
/*     */   public final int au;
/*     */   public final int av;
/*     */   public final int aw;
/*     */   public final int ax;
/*     */   public final int ay;
/*     */   public final int az;
/*     */   public final int aA;
/*     */   
/*     */   private CustomWorldSettingsFinal(CustomWorldSettings paramCustomWorldSettings) {
/* 142 */     this.a = paramCustomWorldSettings.b;
/* 143 */     this.b = paramCustomWorldSettings.c;
/* 144 */     this.c = paramCustomWorldSettings.d;
/* 145 */     this.d = paramCustomWorldSettings.e;
/* 146 */     this.e = paramCustomWorldSettings.f;
/* 147 */     this.f = paramCustomWorldSettings.g;
/* 148 */     this.g = paramCustomWorldSettings.h;
/* 149 */     this.h = paramCustomWorldSettings.i;
/* 150 */     this.i = paramCustomWorldSettings.j;
/* 151 */     this.j = paramCustomWorldSettings.k;
/* 152 */     this.k = paramCustomWorldSettings.l;
/* 153 */     this.l = paramCustomWorldSettings.m;
/* 154 */     this.m = paramCustomWorldSettings.n;
/* 155 */     this.n = paramCustomWorldSettings.o;
/* 156 */     this.o = paramCustomWorldSettings.p;
/* 157 */     this.p = paramCustomWorldSettings.q;
/* 158 */     this.q = paramCustomWorldSettings.r;
/*     */     
/* 160 */     this.r = paramCustomWorldSettings.s;
/* 161 */     this.s = paramCustomWorldSettings.t;
/* 162 */     this.t = paramCustomWorldSettings.u;
/* 163 */     this.u = paramCustomWorldSettings.v;
/* 164 */     this.v = paramCustomWorldSettings.w;
/* 165 */     this.w = paramCustomWorldSettings.x;
/* 166 */     this.x = paramCustomWorldSettings.y;
/* 167 */     this.y = paramCustomWorldSettings.z;
/* 168 */     this.z = paramCustomWorldSettings.A;
/* 169 */     this.A = paramCustomWorldSettings.B;
/* 170 */     this.B = paramCustomWorldSettings.C;
/* 171 */     this.C = paramCustomWorldSettings.D;
/* 172 */     this.D = paramCustomWorldSettings.E;
/* 173 */     this.E = paramCustomWorldSettings.F;
/* 174 */     this.F = paramCustomWorldSettings.G;
/*     */     
/* 176 */     this.G = paramCustomWorldSettings.H;
/* 177 */     this.H = paramCustomWorldSettings.I;
/* 178 */     this.I = paramCustomWorldSettings.J;
/*     */     
/* 180 */     this.J = paramCustomWorldSettings.K;
/* 181 */     this.K = paramCustomWorldSettings.L;
/* 182 */     this.L = paramCustomWorldSettings.M;
/* 183 */     this.M = paramCustomWorldSettings.N;
/* 184 */     this.N = paramCustomWorldSettings.O;
/* 185 */     this.O = paramCustomWorldSettings.P;
/* 186 */     this.P = paramCustomWorldSettings.Q;
/* 187 */     this.Q = paramCustomWorldSettings.R;
/* 188 */     this.R = paramCustomWorldSettings.S;
/* 189 */     this.S = paramCustomWorldSettings.T;
/* 190 */     this.T = paramCustomWorldSettings.U;
/* 191 */     this.U = paramCustomWorldSettings.V;
/* 192 */     this.V = paramCustomWorldSettings.W;
/* 193 */     this.W = paramCustomWorldSettings.X;
/* 194 */     this.X = paramCustomWorldSettings.Y;
/* 195 */     this.Y = paramCustomWorldSettings.Z;
/* 196 */     this.Z = paramCustomWorldSettings.aa;
/* 197 */     this.aa = paramCustomWorldSettings.ab;
/* 198 */     this.ab = paramCustomWorldSettings.ac;
/* 199 */     this.ac = paramCustomWorldSettings.ad;
/* 200 */     this.ad = paramCustomWorldSettings.ae;
/* 201 */     this.ae = paramCustomWorldSettings.af;
/* 202 */     this.af = paramCustomWorldSettings.ag;
/* 203 */     this.ag = paramCustomWorldSettings.ah;
/* 204 */     this.ah = paramCustomWorldSettings.ai;
/* 205 */     this.ai = paramCustomWorldSettings.aj;
/* 206 */     this.aj = paramCustomWorldSettings.ak;
/* 207 */     this.ak = paramCustomWorldSettings.al;
/* 208 */     this.al = paramCustomWorldSettings.am;
/* 209 */     this.am = paramCustomWorldSettings.an;
/* 210 */     this.an = paramCustomWorldSettings.ao;
/* 211 */     this.ao = paramCustomWorldSettings.ap;
/* 212 */     this.ap = paramCustomWorldSettings.aq;
/* 213 */     this.aq = paramCustomWorldSettings.ar;
/* 214 */     this.ar = paramCustomWorldSettings.as;
/* 215 */     this.as = paramCustomWorldSettings.at;
/* 216 */     this.at = paramCustomWorldSettings.au;
/* 217 */     this.au = paramCustomWorldSettings.av;
/* 218 */     this.av = paramCustomWorldSettings.aw;
/* 219 */     this.aw = paramCustomWorldSettings.ax;
/* 220 */     this.ax = paramCustomWorldSettings.ay;
/* 221 */     this.ay = paramCustomWorldSettings.az;
/* 222 */     this.az = paramCustomWorldSettings.aA;
/* 223 */     this.aA = paramCustomWorldSettings.aB;
/*     */   }
/*     */   
/*     */   public static class CustomWorldSettings {
/*     */     @VisibleForTesting
/* 228 */     static final Gson a = (new GsonBuilder()).registerTypeAdapter(CustomWorldSettings.class, new CustomWorldSettingsFinal.CustomWorldSettingsSerializer()).create();
/*     */     
/*     */     public static CustomWorldSettings a(String param1String) {
/* 231 */       if (param1String.isEmpty()) {
/* 232 */         return new CustomWorldSettings();
/*     */       }
/*     */       try {
/* 235 */         return ChatDeserializer.<CustomWorldSettings>a(a, param1String, CustomWorldSettings.class);
/* 236 */       } catch (Exception exception) {
/*     */         
/* 238 */         return new CustomWorldSettings();
/*     */       } 
/*     */     }
/*     */     
/*     */     public String toString() {
/* 243 */       return a.toJson(this);
/*     */     }
/*     */ 
/*     */     
/* 247 */     public float b = 684.412F;
/* 248 */     public float c = 684.412F;
/* 249 */     public float d = 512.0F;
/* 250 */     public float e = 512.0F;
/* 251 */     public float f = 200.0F;
/* 252 */     public float g = 200.0F;
/* 253 */     public float h = 0.5F;
/* 254 */     public float i = 80.0F;
/* 255 */     public float j = 160.0F;
/* 256 */     public float k = 80.0F;
/* 257 */     public float l = 8.5F;
/* 258 */     public float m = 12.0F;
/* 259 */     public float n = 1.0F;
/*     */     public float o;
/* 261 */     public float p = 1.0F;
/*     */     public float q;
/* 263 */     public int r = 63;
/*     */     
/*     */     public boolean s = true;
/*     */     
/*     */     public boolean t = true;
/* 268 */     public int u = 8;
/*     */     public boolean v = true;
/*     */     public boolean w = true;
/*     */     public boolean x = true;
/*     */     public boolean y = true;
/*     */     public boolean z = true;
/*     */     public boolean A = true;
/*     */     public boolean B = true;
/*     */     public boolean C = true;
/* 277 */     public int D = 4;
/*     */     public boolean E = true;
/* 279 */     public int F = 80;
/*     */     
/*     */     public boolean G;
/*     */     
/* 283 */     public int H = -1;
/* 284 */     public int I = 4;
/* 285 */     public int J = 4;
/*     */ 
/*     */     
/* 288 */     public int K = 33;
/* 289 */     public int L = 10;
/*     */     public int M;
/* 291 */     public int N = 256;
/* 292 */     public int O = 33;
/* 293 */     public int P = 8;
/*     */     public int Q;
/* 295 */     public int R = 256;
/* 296 */     public int S = 33;
/* 297 */     public int T = 10;
/*     */     public int U;
/* 299 */     public int V = 80;
/* 300 */     public int W = 33;
/* 301 */     public int X = 10;
/*     */     public int Y;
/* 303 */     public int Z = 80;
/* 304 */     public int aa = 33;
/* 305 */     public int ab = 10;
/*     */     public int ac;
/* 307 */     public int ad = 80;
/* 308 */     public int ae = 17;
/* 309 */     public int af = 20;
/*     */     public int ag;
/* 311 */     public int ah = 128;
/* 312 */     public int ai = 9;
/* 313 */     public int aj = 20;
/*     */     public int ak;
/* 315 */     public int al = 64;
/* 316 */     public int am = 9;
/* 317 */     public int an = 2;
/*     */     public int ao;
/* 319 */     public int ap = 32;
/* 320 */     public int aq = 8;
/* 321 */     public int ar = 8;
/*     */     public int as;
/* 323 */     public int at = 16;
/* 324 */     public int au = 8;
/* 325 */     public int av = 1;
/*     */     public int aw;
/* 327 */     public int ax = 16;
/* 328 */     public int ay = 7;
/* 329 */     public int az = 1;
/* 330 */     public int aA = 16;
/* 331 */     public int aB = 16;
/*     */     
/*     */     public CustomWorldSettings() {
/* 334 */       a();
/*     */     }
/*     */     
/*     */     public void a() {
/* 338 */       this.b = 684.412F;
/* 339 */       this.c = 684.412F;
/* 340 */       this.d = 512.0F;
/* 341 */       this.e = 512.0F;
/* 342 */       this.f = 200.0F;
/* 343 */       this.g = 200.0F;
/* 344 */       this.h = 0.5F;
/* 345 */       this.i = 80.0F;
/* 346 */       this.j = 160.0F;
/* 347 */       this.k = 80.0F;
/* 348 */       this.l = 8.5F;
/* 349 */       this.m = 12.0F;
/* 350 */       this.n = 1.0F;
/* 351 */       this.o = 0.0F;
/* 352 */       this.p = 1.0F;
/* 353 */       this.q = 0.0F;
/* 354 */       this.r = 63;
/*     */       
/* 356 */       this.s = true;
/* 357 */       this.t = true;
/* 358 */       this.u = 8;
/* 359 */       this.v = true;
/* 360 */       this.w = true;
/* 361 */       this.x = true;
/* 362 */       this.y = true;
/* 363 */       this.z = true;
/* 364 */       this.A = true;
/* 365 */       this.B = true;
/* 366 */       this.C = true;
/* 367 */       this.D = 4;
/* 368 */       this.E = true;
/* 369 */       this.F = 80;
/* 370 */       this.G = false;
/*     */       
/* 372 */       this.H = -1;
/* 373 */       this.I = 4;
/* 374 */       this.J = 4;
/*     */       
/* 376 */       this.K = 33;
/* 377 */       this.L = 10;
/* 378 */       this.M = 0;
/* 379 */       this.N = 256;
/* 380 */       this.O = 33;
/* 381 */       this.P = 8;
/* 382 */       this.Q = 0;
/* 383 */       this.R = 256;
/* 384 */       this.S = 33;
/* 385 */       this.T = 10;
/* 386 */       this.U = 0;
/* 387 */       this.V = 80;
/* 388 */       this.W = 33;
/* 389 */       this.X = 10;
/* 390 */       this.Y = 0;
/* 391 */       this.Z = 80;
/* 392 */       this.aa = 33;
/* 393 */       this.ab = 10;
/* 394 */       this.ac = 0;
/* 395 */       this.ad = 80;
/* 396 */       this.ae = 17;
/* 397 */       this.af = 20;
/* 398 */       this.ag = 0;
/* 399 */       this.ah = 128;
/* 400 */       this.ai = 9;
/* 401 */       this.aj = 20;
/* 402 */       this.ak = 0;
/* 403 */       this.al = 64;
/* 404 */       this.am = 9;
/* 405 */       this.an = 2;
/* 406 */       this.ao = 0;
/* 407 */       this.ap = 32;
/* 408 */       this.aq = 8;
/* 409 */       this.ar = 8;
/* 410 */       this.as = 0;
/* 411 */       this.at = 16;
/* 412 */       this.au = 8;
/* 413 */       this.av = 1;
/* 414 */       this.aw = 0;
/* 415 */       this.ax = 16;
/* 416 */       this.ay = 7;
/* 417 */       this.az = 1;
/* 418 */       this.aA = 16;
/* 419 */       this.aB = 16;
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean equals(Object param1Object) {
/* 424 */       if (this == param1Object) {
/* 425 */         return true;
/*     */       }
/* 427 */       if (param1Object == null || getClass() != param1Object.getClass()) {
/* 428 */         return false;
/*     */       }
/*     */       
/* 431 */       CustomWorldSettings customWorldSettings = (CustomWorldSettings)param1Object;
/*     */       
/* 433 */       if (this.ab != customWorldSettings.ab) {
/* 434 */         return false;
/*     */       }
/* 436 */       if (this.ad != customWorldSettings.ad) {
/* 437 */         return false;
/*     */       }
/* 439 */       if (this.ac != customWorldSettings.ac) {
/* 440 */         return false;
/*     */       }
/* 442 */       if (this.aa != customWorldSettings.aa) {
/* 443 */         return false;
/*     */       }
/* 445 */       if (Float.compare(customWorldSettings.l, this.l) != 0) {
/* 446 */         return false;
/*     */       }
/* 448 */       if (Float.compare(customWorldSettings.o, this.o) != 0) {
/* 449 */         return false;
/*     */       }
/* 451 */       if (Float.compare(customWorldSettings.n, this.n) != 0) {
/* 452 */         return false;
/*     */       }
/* 454 */       if (Float.compare(customWorldSettings.q, this.q) != 0) {
/* 455 */         return false;
/*     */       }
/* 457 */       if (Float.compare(customWorldSettings.p, this.p) != 0) {
/* 458 */         return false;
/*     */       }
/* 460 */       if (this.I != customWorldSettings.I) {
/* 461 */         return false;
/*     */       }
/* 463 */       if (this.af != customWorldSettings.af) {
/* 464 */         return false;
/*     */       }
/* 466 */       if (this.ah != customWorldSettings.ah) {
/* 467 */         return false;
/*     */       }
/* 469 */       if (this.ag != customWorldSettings.ag) {
/* 470 */         return false;
/*     */       }
/* 472 */       if (this.ae != customWorldSettings.ae) {
/* 473 */         return false;
/*     */       }
/* 475 */       if (Float.compare(customWorldSettings.b, this.b) != 0) {
/* 476 */         return false;
/*     */       }
/* 478 */       if (Float.compare(customWorldSettings.h, this.h) != 0) {
/* 479 */         return false;
/*     */       }
/* 481 */       if (Float.compare(customWorldSettings.f, this.f) != 0) {
/* 482 */         return false;
/*     */       }
/* 484 */       if (Float.compare(customWorldSettings.g, this.g) != 0) {
/* 485 */         return false;
/*     */       }
/* 487 */       if (this.av != customWorldSettings.av) {
/* 488 */         return false;
/*     */       }
/* 490 */       if (this.ax != customWorldSettings.ax) {
/* 491 */         return false;
/*     */       }
/* 493 */       if (this.aw != customWorldSettings.aw) {
/* 494 */         return false;
/*     */       }
/* 496 */       if (this.au != customWorldSettings.au) {
/* 497 */         return false;
/*     */       }
/* 499 */       if (this.X != customWorldSettings.X) {
/* 500 */         return false;
/*     */       }
/* 502 */       if (this.Z != customWorldSettings.Z) {
/* 503 */         return false;
/*     */       }
/* 505 */       if (this.Y != customWorldSettings.Y) {
/* 506 */         return false;
/*     */       }
/* 508 */       if (this.W != customWorldSettings.W) {
/* 509 */         return false;
/*     */       }
/* 511 */       if (this.L != customWorldSettings.L) {
/* 512 */         return false;
/*     */       }
/* 514 */       if (this.N != customWorldSettings.N) {
/* 515 */         return false;
/*     */       }
/* 517 */       if (this.M != customWorldSettings.M) {
/* 518 */         return false;
/*     */       }
/* 520 */       if (this.K != customWorldSettings.K) {
/* 521 */         return false;
/*     */       }
/* 523 */       if (this.u != customWorldSettings.u) {
/* 524 */         return false;
/*     */       }
/* 526 */       if (this.H != customWorldSettings.H) {
/* 527 */         return false;
/*     */       }
/* 529 */       if (this.an != customWorldSettings.an) {
/* 530 */         return false;
/*     */       }
/* 532 */       if (this.ap != customWorldSettings.ap) {
/* 533 */         return false;
/*     */       }
/* 535 */       if (this.ao != customWorldSettings.ao) {
/* 536 */         return false;
/*     */       }
/* 538 */       if (this.am != customWorldSettings.am) {
/* 539 */         return false;
/*     */       }
/* 541 */       if (this.T != customWorldSettings.T) {
/* 542 */         return false;
/*     */       }
/* 544 */       if (this.V != customWorldSettings.V) {
/* 545 */         return false;
/*     */       }
/* 547 */       if (this.U != customWorldSettings.U) {
/* 548 */         return false;
/*     */       }
/* 550 */       if (this.S != customWorldSettings.S) {
/* 551 */         return false;
/*     */       }
/* 553 */       if (this.P != customWorldSettings.P) {
/* 554 */         return false;
/*     */       }
/* 556 */       if (this.R != customWorldSettings.R) {
/* 557 */         return false;
/*     */       }
/* 559 */       if (this.Q != customWorldSettings.Q) {
/* 560 */         return false;
/*     */       }
/* 562 */       if (this.O != customWorldSettings.O) {
/* 563 */         return false;
/*     */       }
/* 565 */       if (Float.compare(customWorldSettings.c, this.c) != 0) {
/* 566 */         return false;
/*     */       }
/* 568 */       if (this.aj != customWorldSettings.aj) {
/* 569 */         return false;
/*     */       }
/* 571 */       if (this.al != customWorldSettings.al) {
/* 572 */         return false;
/*     */       }
/* 574 */       if (this.ak != customWorldSettings.ak) {
/* 575 */         return false;
/*     */       }
/* 577 */       if (this.ai != customWorldSettings.ai) {
/* 578 */         return false;
/*     */       }
/* 580 */       if (this.aA != customWorldSettings.aA) {
/* 581 */         return false;
/*     */       }
/* 583 */       if (this.az != customWorldSettings.az) {
/* 584 */         return false;
/*     */       }
/* 586 */       if (this.ay != customWorldSettings.ay) {
/* 587 */         return false;
/*     */       }
/* 589 */       if (this.aB != customWorldSettings.aB) {
/* 590 */         return false;
/*     */       }
/* 592 */       if (this.F != customWorldSettings.F) {
/* 593 */         return false;
/*     */       }
/* 595 */       if (Float.compare(customWorldSettings.e, this.e) != 0) {
/* 596 */         return false;
/*     */       }
/* 598 */       if (Float.compare(customWorldSettings.i, this.i) != 0) {
/* 599 */         return false;
/*     */       }
/* 601 */       if (Float.compare(customWorldSettings.j, this.j) != 0) {
/* 602 */         return false;
/*     */       }
/* 604 */       if (Float.compare(customWorldSettings.k, this.k) != 0) {
/* 605 */         return false;
/*     */       }
/* 607 */       if (this.ar != customWorldSettings.ar) {
/* 608 */         return false;
/*     */       }
/* 610 */       if (this.at != customWorldSettings.at) {
/* 611 */         return false;
/*     */       }
/* 613 */       if (this.as != customWorldSettings.as) {
/* 614 */         return false;
/*     */       }
/* 616 */       if (this.aq != customWorldSettings.aq) {
/* 617 */         return false;
/*     */       }
/* 619 */       if (this.J != customWorldSettings.J) {
/* 620 */         return false;
/*     */       }
/* 622 */       if (this.r != customWorldSettings.r) {
/* 623 */         return false;
/*     */       }
/* 625 */       if (Float.compare(customWorldSettings.m, this.m) != 0) {
/* 626 */         return false;
/*     */       }
/* 628 */       if (Float.compare(customWorldSettings.d, this.d) != 0) {
/* 629 */         return false;
/*     */       }
/* 631 */       if (this.s != customWorldSettings.s) {
/* 632 */         return false;
/*     */       }
/* 634 */       if (this.t != customWorldSettings.t) {
/* 635 */         return false;
/*     */       }
/* 637 */       if (this.E != customWorldSettings.E) {
/* 638 */         return false;
/*     */       }
/* 640 */       if (this.G != customWorldSettings.G) {
/* 641 */         return false;
/*     */       }
/* 643 */       if (this.x != customWorldSettings.x) {
/* 644 */         return false;
/*     */       }
/* 646 */       if (this.B != customWorldSettings.B) {
/* 647 */         return false;
/*     */       }
/* 649 */       if (this.v != customWorldSettings.v) {
/* 650 */         return false;
/*     */       }
/* 652 */       if (this.y != customWorldSettings.y) {
/* 653 */         return false;
/*     */       }
/* 655 */       if (this.z != customWorldSettings.z) {
/* 656 */         return false;
/*     */       }
/* 658 */       if (this.A != customWorldSettings.A) {
/* 659 */         return false;
/*     */       }
/* 661 */       if (this.w != customWorldSettings.w) {
/* 662 */         return false;
/*     */       }
/* 664 */       if (this.C != customWorldSettings.C) {
/* 665 */         return false;
/*     */       }
/* 667 */       if (this.D != customWorldSettings.D) {
/* 668 */         return false;
/*     */       }
/*     */       
/* 671 */       return true;
/*     */     }
/*     */ 
/*     */     
/*     */     public int hashCode() {
/* 676 */       int i = (this.b == 0.0F) ? 0 : Float.floatToIntBits(this.b);
/* 677 */       i = 31 * i + ((this.c == 0.0F) ? 0 : Float.floatToIntBits(this.c));
/* 678 */       i = 31 * i + ((this.d == 0.0F) ? 0 : Float.floatToIntBits(this.d));
/* 679 */       i = 31 * i + ((this.e == 0.0F) ? 0 : Float.floatToIntBits(this.e));
/* 680 */       i = 31 * i + ((this.f == 0.0F) ? 0 : Float.floatToIntBits(this.f));
/* 681 */       i = 31 * i + ((this.g == 0.0F) ? 0 : Float.floatToIntBits(this.g));
/* 682 */       i = 31 * i + ((this.h == 0.0F) ? 0 : Float.floatToIntBits(this.h));
/* 683 */       i = 31 * i + ((this.i == 0.0F) ? 0 : Float.floatToIntBits(this.i));
/* 684 */       i = 31 * i + ((this.j == 0.0F) ? 0 : Float.floatToIntBits(this.j));
/* 685 */       i = 31 * i + ((this.k == 0.0F) ? 0 : Float.floatToIntBits(this.k));
/* 686 */       i = 31 * i + ((this.l == 0.0F) ? 0 : Float.floatToIntBits(this.l));
/* 687 */       i = 31 * i + ((this.m == 0.0F) ? 0 : Float.floatToIntBits(this.m));
/* 688 */       i = 31 * i + ((this.n == 0.0F) ? 0 : Float.floatToIntBits(this.n));
/* 689 */       i = 31 * i + ((this.o == 0.0F) ? 0 : Float.floatToIntBits(this.o));
/* 690 */       i = 31 * i + ((this.p == 0.0F) ? 0 : Float.floatToIntBits(this.p));
/* 691 */       i = 31 * i + ((this.q == 0.0F) ? 0 : Float.floatToIntBits(this.q));
/* 692 */       i = 31 * i + this.r;
/* 693 */       i = 31 * i + (this.s ? 1 : 0);
/* 694 */       i = 31 * i + (this.t ? 1 : 0);
/* 695 */       i = 31 * i + this.u;
/* 696 */       i = 31 * i + (this.v ? 1 : 0);
/* 697 */       i = 31 * i + (this.w ? 1 : 0);
/* 698 */       i = 31 * i + (this.x ? 1 : 0);
/* 699 */       i = 31 * i + (this.y ? 1 : 0);
/* 700 */       i = 31 * i + (this.z ? 1 : 0);
/* 701 */       i = 31 * i + (this.A ? 1 : 0);
/* 702 */       i = 31 * i + (this.B ? 1 : 0);
/* 703 */       i = 31 * i + (this.C ? 1 : 0);
/* 704 */       i = 31 * i + this.D;
/* 705 */       i = 31 * i + (this.E ? 1 : 0);
/* 706 */       i = 31 * i + this.F;
/* 707 */       i = 31 * i + (this.G ? 1 : 0);
/* 708 */       i = 31 * i + this.H;
/* 709 */       i = 31 * i + this.I;
/* 710 */       i = 31 * i + this.J;
/* 711 */       i = 31 * i + this.K;
/* 712 */       i = 31 * i + this.L;
/* 713 */       i = 31 * i + this.M;
/* 714 */       i = 31 * i + this.N;
/* 715 */       i = 31 * i + this.O;
/* 716 */       i = 31 * i + this.P;
/* 717 */       i = 31 * i + this.Q;
/* 718 */       i = 31 * i + this.R;
/* 719 */       i = 31 * i + this.S;
/* 720 */       i = 31 * i + this.T;
/* 721 */       i = 31 * i + this.U;
/* 722 */       i = 31 * i + this.V;
/* 723 */       i = 31 * i + this.W;
/* 724 */       i = 31 * i + this.X;
/* 725 */       i = 31 * i + this.Y;
/* 726 */       i = 31 * i + this.Z;
/* 727 */       i = 31 * i + this.aa;
/* 728 */       i = 31 * i + this.ab;
/* 729 */       i = 31 * i + this.ac;
/* 730 */       i = 31 * i + this.ad;
/* 731 */       i = 31 * i + this.ae;
/* 732 */       i = 31 * i + this.af;
/* 733 */       i = 31 * i + this.ag;
/* 734 */       i = 31 * i + this.ah;
/* 735 */       i = 31 * i + this.ai;
/* 736 */       i = 31 * i + this.aj;
/* 737 */       i = 31 * i + this.ak;
/* 738 */       i = 31 * i + this.al;
/* 739 */       i = 31 * i + this.am;
/* 740 */       i = 31 * i + this.an;
/* 741 */       i = 31 * i + this.ao;
/* 742 */       i = 31 * i + this.ap;
/* 743 */       i = 31 * i + this.aq;
/* 744 */       i = 31 * i + this.ar;
/* 745 */       i = 31 * i + this.as;
/* 746 */       i = 31 * i + this.at;
/* 747 */       i = 31 * i + this.au;
/* 748 */       i = 31 * i + this.av;
/* 749 */       i = 31 * i + this.aw;
/* 750 */       i = 31 * i + this.ax;
/* 751 */       i = 31 * i + this.ay;
/* 752 */       i = 31 * i + this.az;
/* 753 */       i = 31 * i + this.aA;
/* 754 */       i = 31 * i + this.aB;
/* 755 */       return i;
/*     */     }
/*     */     
/*     */     public CustomWorldSettingsFinal b() {
/* 759 */       return new CustomWorldSettingsFinal(this);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class CustomWorldSettingsSerializer
/*     */     implements JsonDeserializer<CustomWorldSettings>, JsonSerializer<CustomWorldSettings> {
/*     */     public CustomWorldSettingsFinal.CustomWorldSettings a(JsonElement param1JsonElement, Type param1Type, JsonDeserializationContext param1JsonDeserializationContext) throws JsonParseException {
/* 766 */       JsonObject jsonObject = param1JsonElement.getAsJsonObject();
/*     */       
/* 768 */       CustomWorldSettingsFinal.CustomWorldSettings customWorldSettings = new CustomWorldSettingsFinal.CustomWorldSettings();
/*     */       
/*     */       try {
/* 771 */         customWorldSettings.b = ChatDeserializer.a(jsonObject, "coordinateScale", customWorldSettings.b);
/* 772 */         customWorldSettings.c = ChatDeserializer.a(jsonObject, "heightScale", customWorldSettings.c);
/* 773 */         customWorldSettings.e = ChatDeserializer.a(jsonObject, "lowerLimitScale", customWorldSettings.e);
/* 774 */         customWorldSettings.d = ChatDeserializer.a(jsonObject, "upperLimitScale", customWorldSettings.d);
/* 775 */         customWorldSettings.f = ChatDeserializer.a(jsonObject, "depthNoiseScaleX", customWorldSettings.f);
/* 776 */         customWorldSettings.g = ChatDeserializer.a(jsonObject, "depthNoiseScaleZ", customWorldSettings.g);
/* 777 */         customWorldSettings.h = ChatDeserializer.a(jsonObject, "depthNoiseScaleExponent", customWorldSettings.h);
/* 778 */         customWorldSettings.i = ChatDeserializer.a(jsonObject, "mainNoiseScaleX", customWorldSettings.i);
/* 779 */         customWorldSettings.j = ChatDeserializer.a(jsonObject, "mainNoiseScaleY", customWorldSettings.j);
/* 780 */         customWorldSettings.k = ChatDeserializer.a(jsonObject, "mainNoiseScaleZ", customWorldSettings.k);
/* 781 */         customWorldSettings.l = ChatDeserializer.a(jsonObject, "baseSize", customWorldSettings.l);
/* 782 */         customWorldSettings.m = ChatDeserializer.a(jsonObject, "stretchY", customWorldSettings.m);
/* 783 */         customWorldSettings.n = ChatDeserializer.a(jsonObject, "biomeDepthWeight", customWorldSettings.n);
/* 784 */         customWorldSettings.o = ChatDeserializer.a(jsonObject, "biomeDepthOffset", customWorldSettings.o);
/* 785 */         customWorldSettings.p = ChatDeserializer.a(jsonObject, "biomeScaleWeight", customWorldSettings.p);
/* 786 */         customWorldSettings.q = ChatDeserializer.a(jsonObject, "biomeScaleOffset", customWorldSettings.q);
/* 787 */         customWorldSettings.r = ChatDeserializer.a(jsonObject, "seaLevel", customWorldSettings.r);
/*     */         
/* 789 */         customWorldSettings.s = ChatDeserializer.a(jsonObject, "useCaves", customWorldSettings.s);
/* 790 */         customWorldSettings.t = ChatDeserializer.a(jsonObject, "useDungeons", customWorldSettings.t);
/* 791 */         customWorldSettings.u = ChatDeserializer.a(jsonObject, "dungeonChance", customWorldSettings.u);
/* 792 */         customWorldSettings.v = ChatDeserializer.a(jsonObject, "useStrongholds", customWorldSettings.v);
/* 793 */         customWorldSettings.w = ChatDeserializer.a(jsonObject, "useVillages", customWorldSettings.w);
/* 794 */         customWorldSettings.x = ChatDeserializer.a(jsonObject, "useMineShafts", customWorldSettings.x);
/* 795 */         customWorldSettings.y = ChatDeserializer.a(jsonObject, "useTemples", customWorldSettings.y);
/* 796 */         customWorldSettings.z = ChatDeserializer.a(jsonObject, "useMonuments", customWorldSettings.z);
/* 797 */         customWorldSettings.A = ChatDeserializer.a(jsonObject, "useMansions", customWorldSettings.A);
/* 798 */         customWorldSettings.B = ChatDeserializer.a(jsonObject, "useRavines", customWorldSettings.B);
/* 799 */         customWorldSettings.C = ChatDeserializer.a(jsonObject, "useWaterLakes", customWorldSettings.C);
/* 800 */         customWorldSettings.D = ChatDeserializer.a(jsonObject, "waterLakeChance", customWorldSettings.D);
/* 801 */         customWorldSettings.E = ChatDeserializer.a(jsonObject, "useLavaLakes", customWorldSettings.E);
/* 802 */         customWorldSettings.F = ChatDeserializer.a(jsonObject, "lavaLakeChance", customWorldSettings.F);
/* 803 */         customWorldSettings.G = ChatDeserializer.a(jsonObject, "useLavaOceans", customWorldSettings.G);
/*     */         
/* 805 */         customWorldSettings.H = ChatDeserializer.a(jsonObject, "fixedBiome", customWorldSettings.H);
/* 806 */         if (customWorldSettings.H >= 38 || customWorldSettings.H < -1) {
/* 807 */           customWorldSettings.H = -1;
/* 808 */         } else if (customWorldSettings.H >= BiomeBase.a(Biomes.j)) {
/* 809 */           customWorldSettings.H += 2;
/*     */         } 
/* 811 */         customWorldSettings.I = ChatDeserializer.a(jsonObject, "biomeSize", customWorldSettings.I);
/* 812 */         customWorldSettings.J = ChatDeserializer.a(jsonObject, "riverSize", customWorldSettings.J);
/*     */         
/* 814 */         customWorldSettings.K = ChatDeserializer.a(jsonObject, "dirtSize", customWorldSettings.K);
/* 815 */         customWorldSettings.L = ChatDeserializer.a(jsonObject, "dirtCount", customWorldSettings.L);
/* 816 */         customWorldSettings.M = ChatDeserializer.a(jsonObject, "dirtMinHeight", customWorldSettings.M);
/* 817 */         customWorldSettings.N = ChatDeserializer.a(jsonObject, "dirtMaxHeight", customWorldSettings.N);
/* 818 */         customWorldSettings.O = ChatDeserializer.a(jsonObject, "gravelSize", customWorldSettings.O);
/* 819 */         customWorldSettings.P = ChatDeserializer.a(jsonObject, "gravelCount", customWorldSettings.P);
/* 820 */         customWorldSettings.Q = ChatDeserializer.a(jsonObject, "gravelMinHeight", customWorldSettings.Q);
/* 821 */         customWorldSettings.R = ChatDeserializer.a(jsonObject, "gravelMaxHeight", customWorldSettings.R);
/* 822 */         customWorldSettings.S = ChatDeserializer.a(jsonObject, "graniteSize", customWorldSettings.S);
/* 823 */         customWorldSettings.T = ChatDeserializer.a(jsonObject, "graniteCount", customWorldSettings.T);
/* 824 */         customWorldSettings.U = ChatDeserializer.a(jsonObject, "graniteMinHeight", customWorldSettings.U);
/* 825 */         customWorldSettings.V = ChatDeserializer.a(jsonObject, "graniteMaxHeight", customWorldSettings.V);
/* 826 */         customWorldSettings.W = ChatDeserializer.a(jsonObject, "dioriteSize", customWorldSettings.W);
/* 827 */         customWorldSettings.X = ChatDeserializer.a(jsonObject, "dioriteCount", customWorldSettings.X);
/* 828 */         customWorldSettings.Y = ChatDeserializer.a(jsonObject, "dioriteMinHeight", customWorldSettings.Y);
/* 829 */         customWorldSettings.Z = ChatDeserializer.a(jsonObject, "dioriteMaxHeight", customWorldSettings.Z);
/* 830 */         customWorldSettings.aa = ChatDeserializer.a(jsonObject, "andesiteSize", customWorldSettings.aa);
/* 831 */         customWorldSettings.ab = ChatDeserializer.a(jsonObject, "andesiteCount", customWorldSettings.ab);
/* 832 */         customWorldSettings.ac = ChatDeserializer.a(jsonObject, "andesiteMinHeight", customWorldSettings.ac);
/* 833 */         customWorldSettings.ad = ChatDeserializer.a(jsonObject, "andesiteMaxHeight", customWorldSettings.ad);
/* 834 */         customWorldSettings.ae = ChatDeserializer.a(jsonObject, "coalSize", customWorldSettings.ae);
/* 835 */         customWorldSettings.af = ChatDeserializer.a(jsonObject, "coalCount", customWorldSettings.af);
/* 836 */         customWorldSettings.ag = ChatDeserializer.a(jsonObject, "coalMinHeight", customWorldSettings.ag);
/* 837 */         customWorldSettings.ah = ChatDeserializer.a(jsonObject, "coalMaxHeight", customWorldSettings.ah);
/* 838 */         customWorldSettings.ai = ChatDeserializer.a(jsonObject, "ironSize", customWorldSettings.ai);
/* 839 */         customWorldSettings.aj = ChatDeserializer.a(jsonObject, "ironCount", customWorldSettings.aj);
/* 840 */         customWorldSettings.ak = ChatDeserializer.a(jsonObject, "ironMinHeight", customWorldSettings.ak);
/* 841 */         customWorldSettings.al = ChatDeserializer.a(jsonObject, "ironMaxHeight", customWorldSettings.al);
/* 842 */         customWorldSettings.am = ChatDeserializer.a(jsonObject, "goldSize", customWorldSettings.am);
/* 843 */         customWorldSettings.an = ChatDeserializer.a(jsonObject, "goldCount", customWorldSettings.an);
/* 844 */         customWorldSettings.ao = ChatDeserializer.a(jsonObject, "goldMinHeight", customWorldSettings.ao);
/* 845 */         customWorldSettings.ap = ChatDeserializer.a(jsonObject, "goldMaxHeight", customWorldSettings.ap);
/* 846 */         customWorldSettings.aq = ChatDeserializer.a(jsonObject, "redstoneSize", customWorldSettings.aq);
/* 847 */         customWorldSettings.ar = ChatDeserializer.a(jsonObject, "redstoneCount", customWorldSettings.ar);
/* 848 */         customWorldSettings.as = ChatDeserializer.a(jsonObject, "redstoneMinHeight", customWorldSettings.as);
/* 849 */         customWorldSettings.at = ChatDeserializer.a(jsonObject, "redstoneMaxHeight", customWorldSettings.at);
/* 850 */         customWorldSettings.au = ChatDeserializer.a(jsonObject, "diamondSize", customWorldSettings.au);
/* 851 */         customWorldSettings.av = ChatDeserializer.a(jsonObject, "diamondCount", customWorldSettings.av);
/* 852 */         customWorldSettings.aw = ChatDeserializer.a(jsonObject, "diamondMinHeight", customWorldSettings.aw);
/* 853 */         customWorldSettings.ax = ChatDeserializer.a(jsonObject, "diamondMaxHeight", customWorldSettings.ax);
/* 854 */         customWorldSettings.ay = ChatDeserializer.a(jsonObject, "lapisSize", customWorldSettings.ay);
/* 855 */         customWorldSettings.az = ChatDeserializer.a(jsonObject, "lapisCount", customWorldSettings.az);
/* 856 */         customWorldSettings.aA = ChatDeserializer.a(jsonObject, "lapisCenterHeight", customWorldSettings.aA);
/* 857 */         customWorldSettings.aB = ChatDeserializer.a(jsonObject, "lapisSpread", customWorldSettings.aB);
/* 858 */       } catch (Exception exception) {}
/*     */ 
/*     */ 
/*     */       
/* 862 */       return customWorldSettings;
/*     */     }
/*     */ 
/*     */     
/*     */     public JsonElement a(CustomWorldSettingsFinal.CustomWorldSettings param1CustomWorldSettings, Type param1Type, JsonSerializationContext param1JsonSerializationContext) {
/* 867 */       JsonObject jsonObject = new JsonObject();
/*     */       
/* 869 */       jsonObject.addProperty("coordinateScale", Float.valueOf(param1CustomWorldSettings.b));
/* 870 */       jsonObject.addProperty("heightScale", Float.valueOf(param1CustomWorldSettings.c));
/* 871 */       jsonObject.addProperty("lowerLimitScale", Float.valueOf(param1CustomWorldSettings.e));
/* 872 */       jsonObject.addProperty("upperLimitScale", Float.valueOf(param1CustomWorldSettings.d));
/* 873 */       jsonObject.addProperty("depthNoiseScaleX", Float.valueOf(param1CustomWorldSettings.f));
/* 874 */       jsonObject.addProperty("depthNoiseScaleZ", Float.valueOf(param1CustomWorldSettings.g));
/* 875 */       jsonObject.addProperty("depthNoiseScaleExponent", Float.valueOf(param1CustomWorldSettings.h));
/* 876 */       jsonObject.addProperty("mainNoiseScaleX", Float.valueOf(param1CustomWorldSettings.i));
/* 877 */       jsonObject.addProperty("mainNoiseScaleY", Float.valueOf(param1CustomWorldSettings.j));
/* 878 */       jsonObject.addProperty("mainNoiseScaleZ", Float.valueOf(param1CustomWorldSettings.k));
/* 879 */       jsonObject.addProperty("baseSize", Float.valueOf(param1CustomWorldSettings.l));
/* 880 */       jsonObject.addProperty("stretchY", Float.valueOf(param1CustomWorldSettings.m));
/* 881 */       jsonObject.addProperty("biomeDepthWeight", Float.valueOf(param1CustomWorldSettings.n));
/* 882 */       jsonObject.addProperty("biomeDepthOffset", Float.valueOf(param1CustomWorldSettings.o));
/* 883 */       jsonObject.addProperty("biomeScaleWeight", Float.valueOf(param1CustomWorldSettings.p));
/* 884 */       jsonObject.addProperty("biomeScaleOffset", Float.valueOf(param1CustomWorldSettings.q));
/* 885 */       jsonObject.addProperty("seaLevel", Integer.valueOf(param1CustomWorldSettings.r));
/*     */       
/* 887 */       jsonObject.addProperty("useCaves", Boolean.valueOf(param1CustomWorldSettings.s));
/* 888 */       jsonObject.addProperty("useDungeons", Boolean.valueOf(param1CustomWorldSettings.t));
/* 889 */       jsonObject.addProperty("dungeonChance", Integer.valueOf(param1CustomWorldSettings.u));
/* 890 */       jsonObject.addProperty("useStrongholds", Boolean.valueOf(param1CustomWorldSettings.v));
/* 891 */       jsonObject.addProperty("useVillages", Boolean.valueOf(param1CustomWorldSettings.w));
/* 892 */       jsonObject.addProperty("useMineShafts", Boolean.valueOf(param1CustomWorldSettings.x));
/* 893 */       jsonObject.addProperty("useTemples", Boolean.valueOf(param1CustomWorldSettings.y));
/* 894 */       jsonObject.addProperty("useMonuments", Boolean.valueOf(param1CustomWorldSettings.z));
/* 895 */       jsonObject.addProperty("useMansions", Boolean.valueOf(param1CustomWorldSettings.A));
/* 896 */       jsonObject.addProperty("useRavines", Boolean.valueOf(param1CustomWorldSettings.B));
/* 897 */       jsonObject.addProperty("useWaterLakes", Boolean.valueOf(param1CustomWorldSettings.C));
/* 898 */       jsonObject.addProperty("waterLakeChance", Integer.valueOf(param1CustomWorldSettings.D));
/* 899 */       jsonObject.addProperty("useLavaLakes", Boolean.valueOf(param1CustomWorldSettings.E));
/* 900 */       jsonObject.addProperty("lavaLakeChance", Integer.valueOf(param1CustomWorldSettings.F));
/* 901 */       jsonObject.addProperty("useLavaOceans", Boolean.valueOf(param1CustomWorldSettings.G));
/*     */       
/* 903 */       jsonObject.addProperty("fixedBiome", Integer.valueOf(param1CustomWorldSettings.H));
/* 904 */       jsonObject.addProperty("biomeSize", Integer.valueOf(param1CustomWorldSettings.I));
/* 905 */       jsonObject.addProperty("riverSize", Integer.valueOf(param1CustomWorldSettings.J));
/*     */       
/* 907 */       jsonObject.addProperty("dirtSize", Integer.valueOf(param1CustomWorldSettings.K));
/* 908 */       jsonObject.addProperty("dirtCount", Integer.valueOf(param1CustomWorldSettings.L));
/* 909 */       jsonObject.addProperty("dirtMinHeight", Integer.valueOf(param1CustomWorldSettings.M));
/* 910 */       jsonObject.addProperty("dirtMaxHeight", Integer.valueOf(param1CustomWorldSettings.N));
/* 911 */       jsonObject.addProperty("gravelSize", Integer.valueOf(param1CustomWorldSettings.O));
/* 912 */       jsonObject.addProperty("gravelCount", Integer.valueOf(param1CustomWorldSettings.P));
/* 913 */       jsonObject.addProperty("gravelMinHeight", Integer.valueOf(param1CustomWorldSettings.Q));
/* 914 */       jsonObject.addProperty("gravelMaxHeight", Integer.valueOf(param1CustomWorldSettings.R));
/* 915 */       jsonObject.addProperty("graniteSize", Integer.valueOf(param1CustomWorldSettings.S));
/* 916 */       jsonObject.addProperty("graniteCount", Integer.valueOf(param1CustomWorldSettings.T));
/* 917 */       jsonObject.addProperty("graniteMinHeight", Integer.valueOf(param1CustomWorldSettings.U));
/* 918 */       jsonObject.addProperty("graniteMaxHeight", Integer.valueOf(param1CustomWorldSettings.V));
/* 919 */       jsonObject.addProperty("dioriteSize", Integer.valueOf(param1CustomWorldSettings.W));
/* 920 */       jsonObject.addProperty("dioriteCount", Integer.valueOf(param1CustomWorldSettings.X));
/* 921 */       jsonObject.addProperty("dioriteMinHeight", Integer.valueOf(param1CustomWorldSettings.Y));
/* 922 */       jsonObject.addProperty("dioriteMaxHeight", Integer.valueOf(param1CustomWorldSettings.Z));
/* 923 */       jsonObject.addProperty("andesiteSize", Integer.valueOf(param1CustomWorldSettings.aa));
/* 924 */       jsonObject.addProperty("andesiteCount", Integer.valueOf(param1CustomWorldSettings.ab));
/* 925 */       jsonObject.addProperty("andesiteMinHeight", Integer.valueOf(param1CustomWorldSettings.ac));
/* 926 */       jsonObject.addProperty("andesiteMaxHeight", Integer.valueOf(param1CustomWorldSettings.ad));
/* 927 */       jsonObject.addProperty("coalSize", Integer.valueOf(param1CustomWorldSettings.ae));
/* 928 */       jsonObject.addProperty("coalCount", Integer.valueOf(param1CustomWorldSettings.af));
/* 929 */       jsonObject.addProperty("coalMinHeight", Integer.valueOf(param1CustomWorldSettings.ag));
/* 930 */       jsonObject.addProperty("coalMaxHeight", Integer.valueOf(param1CustomWorldSettings.ah));
/* 931 */       jsonObject.addProperty("ironSize", Integer.valueOf(param1CustomWorldSettings.ai));
/* 932 */       jsonObject.addProperty("ironCount", Integer.valueOf(param1CustomWorldSettings.aj));
/* 933 */       jsonObject.addProperty("ironMinHeight", Integer.valueOf(param1CustomWorldSettings.ak));
/* 934 */       jsonObject.addProperty("ironMaxHeight", Integer.valueOf(param1CustomWorldSettings.al));
/* 935 */       jsonObject.addProperty("goldSize", Integer.valueOf(param1CustomWorldSettings.am));
/* 936 */       jsonObject.addProperty("goldCount", Integer.valueOf(param1CustomWorldSettings.an));
/* 937 */       jsonObject.addProperty("goldMinHeight", Integer.valueOf(param1CustomWorldSettings.ao));
/* 938 */       jsonObject.addProperty("goldMaxHeight", Integer.valueOf(param1CustomWorldSettings.ap));
/* 939 */       jsonObject.addProperty("redstoneSize", Integer.valueOf(param1CustomWorldSettings.aq));
/* 940 */       jsonObject.addProperty("redstoneCount", Integer.valueOf(param1CustomWorldSettings.ar));
/* 941 */       jsonObject.addProperty("redstoneMinHeight", Integer.valueOf(param1CustomWorldSettings.as));
/* 942 */       jsonObject.addProperty("redstoneMaxHeight", Integer.valueOf(param1CustomWorldSettings.at));
/* 943 */       jsonObject.addProperty("diamondSize", Integer.valueOf(param1CustomWorldSettings.au));
/* 944 */       jsonObject.addProperty("diamondCount", Integer.valueOf(param1CustomWorldSettings.av));
/* 945 */       jsonObject.addProperty("diamondMinHeight", Integer.valueOf(param1CustomWorldSettings.aw));
/* 946 */       jsonObject.addProperty("diamondMaxHeight", Integer.valueOf(param1CustomWorldSettings.ax));
/* 947 */       jsonObject.addProperty("lapisSize", Integer.valueOf(param1CustomWorldSettings.ay));
/* 948 */       jsonObject.addProperty("lapisCount", Integer.valueOf(param1CustomWorldSettings.az));
/* 949 */       jsonObject.addProperty("lapisCenterHeight", Integer.valueOf(param1CustomWorldSettings.aA));
/* 950 */       jsonObject.addProperty("lapisSpread", Integer.valueOf(param1CustomWorldSettings.aB));
/*     */       
/* 952 */       return (JsonElement)jsonObject;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Utilisateur\Desktop\spigot-1.12.jar!\net\minecraft\server\v1_12_R1\CustomWorldSettingsFinal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */