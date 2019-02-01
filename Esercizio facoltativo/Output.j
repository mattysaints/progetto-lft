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
 invokestatic Output/read()I
 istore 0
 iload 0
 ldc 3
 if_icmplt L5
 goto L6
L6:
 iload 0
 ldc 7
 if_icmpgt L11
 goto L10
L11:
 iload 0
 ldc 10
 if_icmplt L12
 goto L10
L12:


 goto L5

 
 L10:
 goto L8
L8:
 iload 0
 ldc 13
 if_icmpgt L16
 goto L15
L16:
 iload 0
 ldc 17
 if_icmplt L17
 goto L15
L17:

 goto L5

L15:
 goto L13
L5:
L13:
 iload 0
 ldc 42
 if_icmpeq L19
 goto L18
L19:
 
 goto L5
 
L5:
L18:
 goto L4
L5:
 ldc 1
 invokestatic Output/print(I)V
 goto L2
L4:
 ldc 0
 invokestatic Output/print(I)V
L2:
L0:
 return
.end method

.method public static main([Ljava/lang/String;)V
 invokestatic Output/run()V
 return
.end method

