# Beadandó feladat
Programozási technloógiák és Programozási környeztek
2016

##Black Jack kártya játék

####Rendszer követelmények:
- maven 3.x
- jdk 1.8.0_77 
- hsqldb 2.3.3 ([ >letöltés< ](https://sourceforge.net/projects/hsqldb/files/hsqldb/hsqldb_2_3/hsqldb-2.3.3.zip/download))

####Futtatás:

- **1.lépés:**
Fordítás

        > mvn package

- **2.lépés:** A hsqldb adatbázis szerver futtatása

        > mvn exec:java

- **3.lépés:** A jaték indítása 

        > java -jar target/black-jack-1.0-jar-with-dependencies.jar



