# Beadandó feladat
Programozási technloógiák és Programozási környeztek
2016

##Black Jack kártya játék

####Rendszer követelmények:
- maven 3.x
- jdk 1.8.0_77
- hsqldb 2.3.3

####Futtatás:

- **1.lépés:**
Fordítás

        > mvn package

- **2.lépés:** A hsqldb adatbázis szerver futtatása (a parancsot a hsqldb könyvtárban kell kiadni)

        > java -classpath lib/hsqldb.jar org.hsqldb.server.Server --database.0 file:hsqldb/bjmaven --dbname.0 userdb

- **3.lépés:** A jaték indítása 

        > java -jar target/black-jack-1.0-jar-with-dependencies.jar



