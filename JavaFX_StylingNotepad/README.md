# JavaFX — Styling & Notepad Application

Open this folder in **IntelliJ IDEA 2026.1.1** as a **Maven project**.
IntelliJ will download the JavaFX dependency automatically.

## Task 1 — Hello World (JavaFX Effects & CSS)
Run `hello.HelloWorldApp`.
- "Hello World" is centered, 56px, custom font (Trebuchet MS).
- Text is filled with a multi-color `linear-gradient` (cyan → pink → coral).
- A `reflection()` effect gives the faded mirror image below the text.
- The scene background is a complementary dark blue-to-purple gradient.
- All of the above is defined in `src/main/resources/hello/hello.css` and
  applied via `#root-pane` / `#hello-label` ids — no styling is hard-coded
  in `HelloWorldApp.java`.

## Task 2 — JFX Notepad
Run `notepad.NotepadApp`.
- Menu bar with **File** (New, Open, Save, Save As, Exit), **Edit** (Cut,
  Copy, Paste, Select All), and **Help** (About) menus.
- A large multi-line `TextArea` fills the window.
- **Open** loads a `.txt` file into the text area; **Save** / **Save As**
  write it back to disk.
- The window title shows the current file name followed by `- JFX Notepad`
  (e.g. `HelloJavaFX.txt - JFX Notepad`), and `Untitled - JFX Notepad`
  for a new/unsaved document.
- All styling (dark theme, custom fonts, menu bar colors, hover/selected
  colors, text area colors & padding) is defined in
  `src/main/resources/notepad/notepad.css` — nothing is hard-coded in Java.

## If you see "JavaFX runtime components are missing"
1. Right-click `pom.xml` → **Maven → Reload project**. This pom now
   auto-detects your OS/arch and downloads the correct *native* JavaFX
   jars (the plain dependency without a classifier has no native code,
   which is what causes this exact error).
2. If it still happens, run `hello.Launcher` / `notepad.Launcher` instead
   of `HelloWorldApp` / `NotepadApp` directly — some IDE run configs
   refuse to launch a class that itself extends `Application`; the
   Launcher class sidesteps that.
3. As a last resort, use the Maven side panel: **Plugins → javafx → javafx:run**.

## Switching which task runs via Maven
`pom.xml`'s `javafx-maven-plugin` is configured to run `hello.HelloWorldApp`
by default (`mvn javafx:run`). To run Task 2 the same way, change the
`<mainClass>` in `pom.xml` to `notepad.NotepadApp`, or simply run
`NotepadApp`'s `main` method directly from IntelliJ.
