# Java Networking & Distributed Applications — Exercises

Open this folder in **IntelliJ IDEA 2026.1.1** as a **Maven project**
(File → Open → select this folder → open as project). IntelliJ will
download the JavaFX dependencies automatically (needed only for Q3).

## Quick start — one launcher for every exercise
`launcher.Main` is a single menu-driven entry point for Q1–Q4. Run it with
`mvn exec:java` or from IntelliJ (on a chat/calculator client, give each
terminal a different program argument to skip the menu):

| Option | Class it runs            | Purpose                            |
|--------|--------------------------|------------------------------------|
| 1      | `chat.ChatServer`        | Q1 server (run first)              |
| 2      | `chat.ChatClient`        | Q1 client (run several)            |
| 3      | `udp.UDPFileReceiver`    | Q2 receiver (run first)            |
| 4      | `udp.UDPFileSender`      | Q2 sender                          |
| 5      | `http.HttpHeaderViewerApp` | Q3 JavaFX header viewer          |
| 6      | `rmi.CalculatorServer`   | Q4 RMI server (run first)          |
| 7      | `rmi.CalculatorClient`   | Q4 client (enter e.g. `30 + 40`)   |

Pass no argument for the menu, e.g. `mvn exec:java`. Each exercise class
still has its own `main` method and can be run directly as described below.

## Q1 — Multi-Client Chat Room (package `chat`)
1. Run `chat.ChatServer` first.
2. Run `chat.ChatClient` two or three times (separate Run Configurations,
   or separate terminals: `java -cp target/classes chat.ChatClient`).
   Enter a different name each time (e.g. Ali, Sara, Omar).
3. Type a message in one client's console and press Enter — it is
   broadcast to every connected client's console.
4. Type `/quit` to disconnect a client gracefully.

## Q2 — UDP File Sender (package `udp`)
1. Run `udp.UDPFileReceiver` first (it listens on port 6000).
2. Run `udp.UDPFileSender` (it reads `src/main/resources/message.txt`
   and sends it line by line, 200 ms apart, with an EOF marker at the end).
   You can pass a different file path as a program argument.

## Q3 — HTTP Header & Page in a WebView (package `http`)
Run `http.HttpHeaderViewerApp` (a JavaFX application). Type a URL and
click **Load** — the HTTP status/content-type/content-length/server/date
headers are shown above, and the raw page HTML is rendered inside the
embedded WebView below.
Run configuration in IntelliJ: normal "Application" run config pointing
at `http.HttpHeaderViewerApp` (IntelliJ resolves the JavaFX modules from
the Maven dependencies automatically). Alternatively use `mvn javafx:run`.

## Q4 — Remote Calculator Client (package `rmi`, uses Java RMI)
1. Run `rmi.CalculatorServer` first (starts the RMI registry on port 1099
   and binds the calculator service).
2. Run `rmi.CalculatorClient` with 3 program arguments:
   `30 + 40`, `100 - 25`, `6 * 7`, `20 / 4`
   (In IntelliJ: Run → Edit Configurations → Program arguments field.
   On a real command line, quote `*` as `"*"` so the shell doesn't expand it.)
3. The result is printed, e.g. `30 + 40 = 70`.
   Dividing by zero prints a friendly error instead of crashing.
