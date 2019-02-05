.class public Output 
.super java/lang/Object

.method public <init>()V
 aload_0
 invokenonvirtual java/lang/Object/<init>()V
 return
.end method

.method public static print(I)V
 .limit stack 2
 getstatic java/lang/System/out Ljava/io/PrintStream;
 iload_0 
 invokestatic java/lang/Integer/toString(I)Ljava/lang/String;
 invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V
 return
.end method

.method public static read()I
 .limit stack 3
 new java/util/Scanner
 dup
 getstatic java/lang/System/in Ljava/io/InputStream;
 invokespecial java/util/Scanner/<init>(Ljava/io/InputStream;)V
 invokevirtual java/util/Scanner/next()Ljava/lang/String;
 invokestatic java/lang/Integer.parseInt(Ljava/lang/String;)I
 ireturn
.end method
.method public static run()V
 .limit stack 1024
 .limit locals 256
 ldc 0
 ldc 1
 isub 
 invokestatic Output/print(I)V
 invokestatic Output/read()I
 istore 0
 ldc 0
 istore 1
 ldc 0
 istore 2
 ldc 0
 istore 3
 ldc 0
 istore 4
L9:
 iload 0
 ldc 0
 ldc 1
 isub 
 if_icmpne L11
 goto L10
L11:
 goto L8
L10:
 goto L7
L8:
 iload 0
 istore 5
L15:
 iload 5
 ldc 0
 if_icmpgt L17
 goto L16
L17:
 goto L14
L16:
 goto L13
L14:
 iload 5
 ldc 2
 isub 
 istore 5
 goto L15
L13:
 iload 5
 ldc 0
 if_icmpeq L23
 goto L22
L23:
 goto L21
L22:
 goto L20
L21:
 iload 1
 ldc 1
 iadd 
 istore 1
 goto L18
L20:
 iload 1
 istore 1
L18:
 iload 0
 istore 5
L27:
 iload 5
 ldc 0
 if_icmpgt L29
 goto L28
L29:
 goto L26
L28:
 goto L25
L26:
 iload 5
 ldc 3
 isub 
 istore 5
 goto L27
L25:
 iload 5
 ldc 0
 if_icmpeq L35
 goto L34
L35:
 goto L33
L34:
 goto L32
L33:
 iload 2
 ldc 1
 iadd 
 istore 2
 goto L30
L32:
 iload 2
 istore 2
L30:
 iload 0
 istore 5
L39:
 iload 5
 ldc 0
 if_icmpgt L41
 goto L40
L41:
 goto L38
L40:
 goto L37
L38:
 iload 5
 ldc 5
 isub 
 istore 5
 goto L39
L37:
 iload 5
 ldc 0
 if_icmpeq L47
 goto L46
L47:
 goto L45
L46:
 goto L44
L45:
 iload 3
 ldc 1
 iadd 
 istore 3
 goto L42
L44:
 iload 3
 istore 3
L42:
 iload 0
 istore 5
L51:
 iload 5
 ldc 0
 if_icmpgt L53
 goto L52
L53:
 goto L50
L52:
 goto L49
L50:
 iload 5
 ldc 7
 isub 
 istore 5
 goto L51
L49:
 iload 5
 ldc 0
 if_icmpeq L59
 goto L58
L59:
 goto L57
L58:
 goto L56
L57:
 iload 4
 ldc 1
 iadd 
 istore 4
 goto L54
L56:
 iload 4
 istore 4
L54:
 invokestatic Output/read()I
 istore 0
 goto L9
L7:
 iload 1
 invokestatic Output/print(I)V
 iload 2
 invokestatic Output/print(I)V
 iload 3
 invokestatic Output/print(I)V
 iload 4
 invokestatic Output/print(I)V
L0:
 return
.end method

.method public static main([Ljava/lang/String;)V
 invokestatic Output/run()V
 return
.end method

