# Java LTS Versionshistorie (1.0 bis 1.25)

Diese Übersicht enthält die wichtigsten Meilensteine der Java-Entwicklung. Es sind ausschließlich **LTS-Versionen** (Long Term Support) aufgeführt. Alle Neuerungen der dazwischenliegenden Versionen wurden in das jeweils nächste LTS-Release konsolidiert.

| Version | Release | Wichtigste Features (Syntax & APIs) |
| :--- | :--- | :--- |
| [**1.0**](#java-10) | 1996 | [Basis-Syntax](#basis-syntax), [Applets](#applets), [AWT](#awt) |
| [**1.1**](#java-11) | 1997 | [Calendar API](#calendar-api), [Inner Classes](#inner-classes), [JDBC](#jdbc) |
| [**1.2 (Java 2)**](#java-12-java-2) | 1998 | [Collections Framework](#collections-framework), [Swing UI](#swing-ui), [JIT-Compiler](#jit-compiler) |
| [**1.3**](#java-13) | 2000 | [HotSpot JVM](#hotspot-jvm), [JNDI](#jndi) |
| [**1.4**](#java-14) | 2002 | [assert](#assert), [NIO](#nio), [Logging API](#logging-api) |
| [**1.5 (5)**](#java-15-5) | 2004 | [Generics](#generics), [Enums](#enums), [Annotations](#annotations) |
| [**1.6 (6)**](#java-16-6) | 2006 | [Web Services](#web-services), [Scripting API](#scripting-api) |
| [**1.7 (7)**](#java-17-7) | 2011 | [NIO2](#nio2), [Try-with-resources](#try-with-resources), [Diamond Operator](#diamond-operator) |
| [**1.8 (8)**](#java-18-8) | 2014 | [Lambda Expressions](#lambda-expressions), [Streams API](#streams-api), [Date/Time API](#datetime-api) |
| [**1.11 (11)**](#java-111-11) | 2018 | [Modulsystem](#modulsystem), [var](#var-type-inference), [HTTP Client](#http-client) |
| [**1.17 (17)**](#java-117-17) | 2021 | [Records](#records), [Sealed Classes](#sealed-classes), [Pattern Matching (instanceof)](#pattern-matching-instanceof), [Text Blocks](#text-blocks), [Switch Expressions](#switch-expressions) |
| [**1.21 (21)**](#java-121-21) | 2023 | [Virtual Threads](#virtual-threads), [Pattern Matching für switch](#pattern-matching-für-switch), [Sequenced Collections](#sequenced-collections) |
| [**1.25 (25)**](#java-125-25) | 2025 | [Stream Gatherers](#stream-gatherers), [Class-File API](#class-file-api), [Flexible Constructor Bodies](#flexible-constructor-bodies) |

---

<a name="java-10"></a>
## Java 1.0

<a name="basis-syntax"></a>
### Basis-Syntax
Die grundlegende Struktur einer Java-Klasse mit Attributen und Methoden.
```java
public class Grundlagen {
    // Ein einfaches Attribut (Zustand)
    private String nachricht = "Willkommen bei Java";

    // Eine Methode (Verhalten)
    public void sagHallo() {
        // Ausgabe auf der Konsole
        System.out.println(nachricht);
    }
    
    // Der Einstiegspunkt für jedes Programm
    public static void main(String[] args) {
        Grundlagen app = new Grundlagen();
        app.sagHallo();
        System.out.println("Programm erfolgreich gestartet.");
    }
}
```

<a name="applets"></a>
### Applets
Kleine Java-Programme, die direkt in Webseiten eingebettet wurden.
```java
import java.applet.Applet;
import java.awt.Graphics;

public class MeinApplet extends Applet {
    // Wird beim Laden der Webseite aufgerufen
    public void init() {
        System.out.println("Applet wird initialisiert...");
    }

    // Zeichnet grafische Elemente in den Browser-Bereich
    public void paint(Graphics g) {
        g.drawString("Java Applet läuft!", 50, 25);
        g.drawRect(20, 40, 100, 50);
    }
}
```

<a name="awt"></a>
### AWT
Das Abstract Window Toolkit war das erste Grafik-Framework.
```java
import java.awt.*;

public class AwtFenster {
    public static void main(String[] args) {
        Frame f = new Frame("Java 1.0 GUI");
        Button b = new Button("Klick mich");
        Label l = new Label("Status: Bereit");
        
        f.add(l, BorderLayout.NORTH);
        f.add(b, BorderLayout.CENTER);
        f.setSize(300, 200);
        f.setLayout(new FlowLayout());
        f.setVisible(true);
    }
}
```

---

<a name="java-11"></a>
## Java 1.1

<a name="calendar-api"></a>
### Calendar API
Ermöglichte erstmals komplexe Berechnungen mit Datumswerten und Zeitzonen.
```java
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Date;

public class KalenderDemo {
    public static void main(String[] args) {
        Calendar cal = new GregorianCalendar();
        cal.set(Calendar.YEAR, 2026);
        cal.set(Calendar.MONTH, Calendar.JANUARY);
        cal.set(Calendar.DAY_OF_MONTH, 20);
        
        Date datum = cal.getTime();
        System.out.println("Zieldatum: " + datum);
        
        cal.add(Calendar.MONTH, 6); // 6 Monate hinzufügen
        System.out.println("Datum nach 6 Monaten: " + cal.getTime());
    }
}
```

<a name="inner-classes"></a>
### Inner Classes
Strukturierung durch Klassen innerhalb von Klassen zur besseren Kapselung.
```java
public class Organisation {
    private String name = "TechCorp";

    public class Abteilung {
        public void druckeName() {
            // Inner Class hat Zugriff auf private Felder der Outer Class
            System.out.println("Abteilung gehört zu: " + name);
        }
    }

    public void start() {
        Abteilung abt = new Abteilung();
        abt.druckeName();
    }
}
```

<a name="jdbc"></a>
### JDBC
Die Java Database Connectivity erlaubt den Zugriff auf SQL-Datenbanken.
```java
import java.sql.*;

public class DatenbankDemo {
    public void ladeKunden() throws SQLException {
        String url = "jdbc:mysql://localhost/crm_db";
        try (Connection conn = DriverManager.getConnection(url, "user", "pass")) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT name, email FROM kunden");
            while(rs.next()) {
                String kName = rs.getString("name");
                String kMail = rs.getString("email");
                System.out.println("Kunde: " + kName + " (" + kMail + ")");
            }
        }
    }
}
```

---

<a name="java-12-java-2"></a>
## Java 1.2 (Java 2)

<a name="collections-framework"></a>
### Collections Framework
Einführung standardisierter Strukturen wie Listen, Mengen und Maps.
```java
import java.util.*;

public class Lagerverwaltung {
    public static void main(String[] args) {
        List produkte = new ArrayList();
        produkte.add("Monitor");
        produkte.add("Tastatur");
        produkte.add("Maus");

        // Manuelle Iteration (vor Java 5)
        for (int i = 0; i < produkte.size(); i++) {
            Object obj = produkte.get(i);
            System.out.println("Lagerartikel: " + obj.toString());
        }
    }
}
```

<a name="swing-ui"></a>
### Swing UI
Ein neues, rein in Java geschriebenes GUI-Framework für moderne Oberflächen.
```java
import javax.swing.*;

public class SwingApp {
    public static void main(String[] args) {
        JFrame frame = new JFrame("J2SE Dashboard");
        JPanel panel = new JPanel();
        JButton button = new JButton("Bestätigen");
        
        panel.add(new JLabel("Eingabe:"));
        panel.add(new JTextField(20));
        panel.add(button);
        
        frame.add(panel);
        frame.setSize(400, 150);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
```

<a name="jit-compiler"></a>
### JIT-Compiler
Der "Just-In-Time"-Compiler übersetzt Bytecode während der Laufzeit in Maschinencode.
```java
// Interner Prozess der JVM zur Beschleunigung.
// Identifiziert häufig genutzte Codeabschnitte (Hotspots) und
// übersetzt diese direkt in CPU-Befehle, anstatt
// sie Zeile für Zeile zu interpretieren.
```

---

<a name="java-13"></a>
## Java 1.3
<a name="hotspot-jvm"></a>
### HotSpot JVM
Optimierung der Speicherverwaltung (Garbage Collection) und Performance.

<a name="jndi"></a>
### JNDI
Schnittstelle für Namens- und Verzeichnisdienste (Java Naming and Directory Interface).
```java
import javax.naming.*;

public class JndiService {
    public void ressourceSuchen() throws NamingException {
        Context ctx = new InitialContext();
        // Suchen einer Ressource in einer Verzeichnisstruktur
        Object res = ctx.lookup("java:comp/env/jdbc/KundenDB");
        System.out.println("Ressource geladen: " + res.getClass().getName());
    }
}
```

---

<a name="java-14"></a>
## Java 1.4

<a name="assert"></a>
### assert
Bedingungsprüfung zur Qualitätssicherung während der Entwicklung.
```java
public class RechnungsPruefer {
    public void validiereSumme(double summe) {
        // Assertions helfen Entwicklern, Logikfehler früh zu finden
        assert summe >= 0 : "Die Rechnungssumme darf nicht negativ sein!";
        System.out.println("Rechnungssumme " + summe + " ist gültig.");
    }
    
    public static void main(String[] args) {
        new RechnungsPruefer().validiereSumme(10.50);
    }
}
```

<a name="nio"></a>
### NIO
High-Performance Dateizugriff über Buffer und Channels.
```java
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.io.*;

public class SchnellLeser {
    public void liesDatei(String pfad) throws IOException {
        FileInputStream fis = new FileInputStream(pfad);
        FileChannel channel = fis.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        
        while (channel.read(buffer) != -1) {
            buffer.flip(); // Zum Lesen umschalten
            // Daten aus dem Buffer verarbeiten...
            buffer.clear(); // Puffer für nächsten Lesevorgang leeren
        }
        fis.close();
    }
}
```

<a name="logging-api"></a>
### Logging API
Standardisiertes Protokoll für Ereignisse und Fehler.
```java
import java.util.logging.*;

public class SystemLog {
    private static final Logger logger = Logger.getLogger(SystemLog.class.getName());

    public void start() {
        logger.info("Anwendung wird initialisiert...");
        try {
            // Kritischer Prozess...
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Unerwarteter Systemfehler!", e);
        }
    }
}
```

---

<a name="java-15-5"></a>
## Java 1.5 (5)

<a name="generics"></a>
### Generics
Vermeidung von Laufzeitfehlern durch Typsicherheit bei Listen.
```java
import java.util.ArrayList;
import java.util.List;

public class Katalog {
    public static void main(String[] args) {
        // Liste akzeptiert nur 'String' Objekte
        List<String> namen = new ArrayList<String>();
        namen.add("IT-Trainer");
        
        // Kein Casting auf String mehr nötig
        String name = namen.get(0);
        System.out.println("Erster Eintrag: " + name);
    }
}
```

<a name="enums"></a>
### Enums
Sichere Aufzählungstypen statt loser Konstanten.
```java
public enum TicketStatus {
    OFFEN, IN_ARBEIT, GESCHLOSSEN;

    public boolean istAktiv() {
        return this != GESCHLOSSEN;
    }
}

public class TicketApp {
    public void verarbeite() {
        TicketStatus s = TicketStatus.OFFEN;
        if (s.istAktiv()) {
            System.out.println("Status: " + s);
        }
    }
}
```

<a name="annotations"></a>
### Annotations
Metadaten im Code zur Unterstützung von Compiler und Frameworks.
```java
public class ServiceInfo {
    @Override
    public String toString() {
        return "Service v1.5";
    }

    @Deprecated
    public void alteBerechnung() {
        // Diese Methode wird in einer künftigen Version entfernt
    }
}
```

---

<a name="java-16-6"></a>
## Java 1.6 (6)
<a name="web-services"></a>
### Web Services
Standard für Datenaustausch zwischen Systemen (JAX-WS).
<a name="scripting-api"></a>
### Scripting API
Integration von Skriptsprachen wie JavaScript in Java-Anwendungen.
```java
import javax.script.*;

public class ScriptDemo {
    public static void main(String[] args) throws ScriptException {
        ScriptEngineManager mgr = new ScriptEngineManager();
        ScriptEngine engine = mgr.getEngineByName("JavaScript");
        engine.eval("print('Skript läuft innerhalb von Java!')");
    }
}
```

---

<a name="java-17-7"></a>
## Java 1.7 (7)

<a name="nio2"></a>
### NIO2
Die moderne und intuitive Datei-API (`Path`, `Files`).
```java
import java.nio.file.*;
import java.util.List;

public class FileTools {
    public void verarbeite(String name) throws Exception {
        Path pfad = Paths.get(name);
        // Ganze Datei bequem in eine Liste lesen
        List<String> zeilen = Files.readAllLines(pfad);
        System.out.println("Gelesene Zeilen: " + zeilen.size());
        
        // Datei kopieren
        Files.copy(pfad, Paths.get(name + ".backup"), StandardCopyOption.REPLACE_EXISTING);
    }
}
```

<a name="try-with-resources"></a>
### Try-with-resources
Automatisches Schließen von Ressourcen am Ende eines Blocks.
```java
import java.io.*;

public class Archivierer {
    public void liesLog(String datei) {
        // Reader wird automatisch geschlossen, auch bei Fehlern
        try (BufferedReader br = new BufferedReader(new FileReader(datei))) {
            String zeile = br.readLine();
            System.out.println("Erste Zeile: " + zeile);
        } catch (IOException e) {
            System.err.println("Fehler beim Zugriff: " + e.getMessage());
        }
    }
}
```

<a name="diamond-operator"></a>
### Diamond Operator
Schlankere Deklaration von Generics.
```java
import java.util.*;

public class DiamondDemo {
    public void demo() {
        // Vorher: new HashMap<String, Integer>()
        Map<String, Integer> inventar = new HashMap<>();
        inventar.put("CPU", 50);
    }
}
```

---

<a name="java-18-8"></a>
## Java 1.8 (8)

<a name="lambda-expressions"></a>
### Lambda Expressions
Kurze Schreibweise für Funktionen, die als Argument übergeben werden.
```java
import java.util.Arrays;
import java.util.List;

public class MitarbeiterFilter {
    public void sortiere(List<String> namen) {
        // Kompakte Sortier-Logik direkt im Aufruf
        namen.sort((a, b) -> a.compareToIgnoreCase(b));
        namen.forEach(n -> System.out.println("Mitarbeiter: " + n));
    }
}
```

<a name="streams-api"></a>
### Streams API
Deklarative Verarbeitung von Daten (Filtern, Sortieren, Mappen).
```java
import java.util.List;
import java.util.stream.Collectors;

public class UmsatzAnalyse {
    public List<String> filtereTopKunden(List<String> kunden) {
        return kunden.stream()
            .filter(k -> k.length() > 5)   // Nur Namen länger als 5 Zeichen
            .map(String::toUpperCase)      // In Großbuchstaben umwandeln
            .sorted()                      // Alphabetisch sortieren
            .collect(Collectors.toList()); // Als neue Liste speichern
    }
}
```

<a name="datetime-api"></a>
### Date/Time API
Sicherer und logischer Umgang mit Zeiträumen und Zeitzonen.
```java
import java.time.*;
import java.time.format.DateTimeFormatter;

public class Termine {
    public void zeitBerechnung() {
        LocalDate heute = LocalDate.now();
        LocalDate projektEnde = heute.plusMonths(3).plusDays(10);
        
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        System.out.println("Geplante Deadline: " + projektEnde.format(fmt));
    }
}
```

---

<a name="java-111-11"></a>
## Java 1.11 (11)

<a name="modulsystem"></a>
### Modulsystem
Bessere Strukturierung großer Anwendungen in Bausteine (`module-info.java`).

<a name="var-type-inference"></a>
### var (Type Inference)
Weniger Schreibaufwand bei lokalen Variablen.
```java
import java.util.ArrayList;

public class VarDemo {
    public void start() {
        var kunden = new ArrayList<String>(); // Typ wird automatisch erkannt
        var nachricht = "System bereit";     // Klarer String-Typ
        
        for (var k : kunden) {
            System.out.println("Kunde: " + k);
        }
    }
}
```

<a name="http-client"></a>
### HTTP Client
Moderne Schnittstelle für Internet-Anfragen (REST APIs).
```java
import java.net.http.*;
import java.net.URI;

public class RestClient {
    public void holeDaten() throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("[https://api.github.com/zen](https://api.github.com/zen)"))
            .GET()
            .build();
            
        var response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("Antwort vom Server: " + response.body());
    }
}
```

---

<a name="java-117-17"></a>
## Java 1.17 (17)

<a name="records"></a>
### Records
Extrem kompakte Klassen für reine Datenträger.
```java
// Ersetzt ca. 40 Zeilen Standard-Code (Getter, toString, etc.)
public record Benutzer(int id, String name, String email) {}

public class RecordDemo {
    public void demo() {
        Benutzer b = new Benutzer(101, "Müller", "m@corp.de");
        System.out.println(b.name()); // Automatischer Getter
    }
}
```

<a name="sealed-classes"></a>
### Sealed Classes
Explizite Kontrolle über die Vererbungshierarchie.
```java
public abstract sealed class Dokument permits PDF, Word {}

public final class PDF extends Dokument { /* Logik für PDF */ }
public final class Word extends Dokument { /* Logik für Word */ }
```

<a name="pattern-matching-instanceof"></a>
### Pattern Matching (instanceof)
Typprüfung und automatische Umwandlung in einem Schritt.
```java
public void verarbeite(Object o) {
    if (o instanceof String text) {
        // 'text' ist hier bereits als String verfügbar
        System.out.println("Textlänge: " + text.length());
    }
}
```

<a name="text-blocks"></a>
### Text Blocks
Mehrzeilige Texte ohne mühsames Maskieren.
```java
String sql = """
    SELECT id, name, status 
    FROM mitarbeiter 
    WHERE abteilung = 'IT' 
    ORDER BY name ASC;
    """;
```

<a name="switch-expressions"></a>
### Switch Expressions
Switch liefert nun direkt ein Ergebnis zurück.
```java
public String getStatus(int code) {
    return switch (code) {
        case 0 -> "Neu";
        case 1 -> "Laufend";
        case 2 -> "Erledigt";
        default -> "Unbekannt";
    };
}
```

---

<a name="java-121-21"></a>
## Java 1.21 (21)

<a name="virtual-threads"></a>
### Virtual Threads
Ermöglicht das gleichzeitige Ausführen von Millionen Aufgaben (Skalierbarkeit).
```java
import java.util.concurrent.Executors;

public class Server {
    public void handle() {
        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            for (int i = 0; i < 10_000; i++) {
                executor.submit(() -> {
                    // Leichtgewichtiger Thread für parallele Arbeit
                    System.out.println("Anfrage wird verarbeitet...");
                    return "Erfolg";
                });
            }
        }
    }
}
```

<a name="pattern-matching-für-switch"></a>
### Pattern Matching für switch
Fallunterscheidung basierend auf dem Datentyp im Switch.
```java
public String format(Object obj) {
    return switch (obj) {
        case Integer i -> String.format("Ganzzahl: %d", i);
        case Long l    -> String.format("Große Ganzzahl: %d", l);
        case String s  -> "Dies ist ein Text: " + s;
        case null      -> "Kein Wert übergeben";
        default        -> "Typ unbekannt: " + obj.toString();
    };
}
```

<a name="sequenced-collections"></a>
### Sequenced Collections
Einheitliche Methoden für den Zugriff auf erste/letzte Elemente.
```java
import java.util.ArrayList;

public class ListenDemo {
    public void demo() {
        var l = new ArrayList<String>();
        l.addFirst("Zuerst");
        l.addLast("Zuletzt");
        
        System.out.println("Anfang der Liste: " + l.getFirst());
        System.out.println("Ende der Liste: " + l.getLast());
    }
}
```

---

<a name="java-125-25"></a>
## Java 1.25 (25)

<a name="stream-gatherers"></a>
### Stream Gatherers
Erweiterte Möglichkeiten für die Datenverarbeitung in Streams.
```java
import java.util.stream.*;

public class StreamDemo {
    public void gruppen() {
        // Erzeugt 3er-Gruppen aus einem Datenstrom (Windowing)
        var result = Stream.of(1, 2, 3, 4, 5, 6)
            .gather(Gatherers.windowFixed(3))
            .toList();
        // Resultat: [[1, 2, 3], [4, 5, 6]]
        System.out.println("Gruppierte Daten: " + result);
    }
}
```

<a name="class-file-api"></a>
### Class-File API
Modernisierte API zur Analyse von Java-Programmdateien (Bytecode).
```java
import java.lang.classfile.*;

public class BytecodeScanner {
    public void scan(byte[] data) {
        ClassModel model = ClassFile.of().parse(data);
        System.out.println("Name der Klasse: " + model.thisClass().asInternalName());
        model.methods().forEach(m -> System.out.println("Methode: " + m.methodName()));
    }
}
```

<a name="flexible-constructor-bodies"></a>
### Flexible Constructor Bodies
Erlaubt Logik (z. B. Validierung) vor dem Aufruf des Eltern-Konstruktors.
```java
public class SpezialUnterklasse extends Basisklasse {
    public SpezialUnterklasse(int wert) {
        // Logik VOR super() ist nun erlaubt!
        if (wert < 0) {
            throw new IllegalArgumentException("Der Wert darf nicht negativ sein!");
        }
        super(wert);
        System.out.println("Objekt erfolgreich erstellt.");
    }
}
```
