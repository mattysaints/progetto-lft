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
L8:
 iload 0
 ldc 0
 ldc 1
 isub 
 if_icmpeq L7
 iload 0
 istore 5
L11:
 iload 5
 ldc 0
 if_icmple L10
 iload 5
 ldc 2
 isub 
 istore 5
 goto L11
L10:
 iload 5
 ldc 0
 if_icmpne L14
 iload 1
 ldc 1
 iadd 
 istore 1
 goto L12
L14:
 iload 1
 istore 1
L12:
 iload 0
 istore 5
L17:
 iload 5
 ldc 0
 if_icmple L16
 iload 5
 ldc 3
 isub 
 istore 5
 goto L17
L16:
 iload 5
 ldc 0
 if_icmpne L20
 iload 2
 ldc 1
 iadd 
 istore 2
 goto L18
L20:
 iload 2
 istore 2
L18:
 iload 0
 istore 5
L23:
 iload 5
 ldc 0
 if_icmple L22
 iload 5
 ldc 5
 isub 
 istore 5
 goto L23
L22:
 iload 5
 ldc 0
 if_icmpne L26
 iload 3
 ldc 1
 iadd 
 istore 3
 goto L24
L26:
 iload 3
 istore 3
L24:
 iload 0
 istore 5
L29:
 iload 5
 ldc 0
 if_icmple L28
 iload 5
 ldc 7
 isub 
 istore 5
 goto L29
L28:
 iload 5
 ldc 0
 if_icmpne L32
 iload 4
 ldc 1
 iadd 
 istore 4
 goto L30
L32:
 iload 4
 istore 4
L30:
 invokestatic Output/read()I
 istore 0
 goto L8
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

