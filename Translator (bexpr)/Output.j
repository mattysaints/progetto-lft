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
 if_icmplt L7
 goto L6
L7:
 goto L5
L6:
 goto L4
L5:
 ldc 0
 invokestatic Output/print(I)V
 iload 0
 invokestatic Output/print(I)V
 goto L2
L4:
 ldc 0
 istore 1
L13:
 iload 0
 ldc 3
 if_icmpge L15
 goto L14
L15:
 goto L12
L14:
 goto L11
L12:
 iload 0
 ldc 3
 isub 
 istore 0
 iload 1
 ldc 1
 iadd 
 istore 1
 goto L13
L11:
 iload 1
 invokestatic Output/print(I)V
 iload 0
 invokestatic Output/print(I)V
L2:
 ldc 0
 invokestatic Output/print(I)V
L0:
 return
.end method

.method public static main([Ljava/lang/String;)V
 invokestatic Output/run()V
 return
.end method

