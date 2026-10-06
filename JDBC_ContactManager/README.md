# JDBC — Connecting to a Database Server

Open this folder in **IntelliJ IDEA 2026.1.1** as a **Maven project**.
IntelliJ will download the MySQL driver (`mysql-connector-j`) automatically.
(`javax.sql.rowset.*` and the default `com.sun.rowset.*` implementation used
for JdbcRowSet/WebRowSet already ship inside the JDK — no extra dependency
needed for those.)

## Quick start — one launcher for every requirement
`launcher.Main` is a single menu-driven entry point for all three
requirements. Run it with `mvn exec:java` or from IntelliJ; pass the option
as a program argument (e.g. `java launcher.Main 2`) to skip the menu:

| Option | Class it runs            | Requirement                        |
|--------|--------------------------|------------------------------------|
| 1      | `app.CrudDemo`           | 1 — console CRUD                   |
| 2      | `gui.ContactViewerGUI`   | 2 — Swing GUI navigation           |
| 3      | `app.BatchAndRowSetDemo` | 3 — batch update + RowSets         |

Each requirement class still has its own `main` method and can be run
directly as described below.

## Setup
1. Start a MySQL server.
2. Run `sql/schema.sql` against it (creates `contactdb.contact` and the two
   sample rows: ITI, MCIT).
3. Edit the connection details in **two** places so they match your server:
   - `dao/ContactDAO.java` → `DRIVER` / `URL` / `USER` / `PASSWORD` constants
     (used by Requirement 1's hard-coded connection).
   - `src/main/resources/db.properties` (used by Requirement 2's DataSource).

## Requirement 1 — CRUD (package `app`, `dao`, `model`)
Run `app.CrudDemo`. It calls all five `ContactDAO` operations in turn
(retrieve all, retrieve by id, insert, edit, delete) and prints the result
of each, matching the exercise's sample output.

## Requirement 2 — DataSource + GUI navigation (package `gui`, `util`)
Run `gui.ContactViewerGUI`. It builds a `DataSource` from
`db.properties` (via `util.DataSourceUtil`), loads all contacts, and shows
them one at a time with **First / Previous / Next / Last** buttons.
`First`/`Previous` are disabled on the first record, `Next`/`Last` on the
last record.

## Requirement 3 — Batch update & RowSets (package `app`, extends `dao.ContactDAO`)
Run `app.BatchAndRowSetDemo`. It:
1. Sends one JDBC **batch** containing two UPDATE statements (email for
   "ITI" and email for "MCIT") together, and prints `Rows affected: [...]`.
2. Uses a **JdbcRowSet** to run a SELECT and print all contacts.
3. Uses a **WebRowSet** to run a SELECT and save the result to `contacts.xml`
   in the project's working directory.

All three requirements reuse the same `ContactPerson` and `ContactDAO`
classes, as instructed — Requirement 2 just supplies a `DataSource` to the
DAO's second constructor instead of using the hard-coded credentials, and
Requirement 3's methods live in the same `ContactDAO` class.
