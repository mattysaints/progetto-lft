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
 ldc 5
 istore 0
 invokestatic Output/read()I
 istore 0
 iload 0
 ldc 5
 if_icmpeq L8
 goto L7
L8:
 goto L6
L7:
 goto L5
L6:
 ldc 3
 istore 1
 goto L3
L5:
L10:
 iload 0
 ldc 3
 if_icmpne L12
 goto L11
L12:
 goto L9
L11:
 goto L3
 invokestatic Output/read()I
 istore 0
 goto L10
L3:
L3:
L0:
 return
.end method

.method public static main([Ljava/lang/String;)V
 invokestatic Output/run()V
 return
.end method

