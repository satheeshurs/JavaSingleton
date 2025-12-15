# JavaSingleton

Breaking Singleton possibilities
1) Reflection
2) Serialization/deserialization
3) Clone
4) MultiThreading
5) Multiple Class Loaders
6) Garbage Collection

1) Reflection
   Using reflection any of the method or constructor acces can be changed.
   Fix: Inside the constructor throw an exception when the reference static instance variable is null.
2)

   
