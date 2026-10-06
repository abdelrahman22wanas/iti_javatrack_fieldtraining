# JavaFX — Customized ListView, TreeView & Charts (Demos)

> **Note:** the Mahara-Tech course page (id=2269) requires login/enrollment,
> so its exact video instructions weren't accessible. These three demos
> follow the standard, well-established JavaFX patterns for each topic
> named in the course outline. If you have the specific lecture slides or
> a task PDF, share it and I'll tailor these to match exactly.

Open this folder in **IntelliJ IDEA 2026.1.1** as a **Maven project**
(same JavaFX OS-classifier setup as your other projects, so native
binaries download automatically).

## VID12 — Customized ListView (package `listview`)
Run `listview.CustomListViewDemo`.
- `Contact.java` — the model (name, email, status).
- `ContactListCell.java` — a custom `ListCell<Contact>` that overrides
  `updateItem()` to render each row as a colored avatar circle (with the
  contact's initial), a two-line name/email block, and a status dot
  (green/orange/gray for Online/Away/Offline) — instead of the default
  single line of plain text.
- The key technique: `listView.setCellFactory(lv -> new ContactListCell())`
  swaps in that custom row layout for every item.

## VID16 — TreeView (package `treeview`)
Run `treeview.TreeViewDemo`.
- Builds a small company org chart: root "Company" → department nodes
  (Engineering, Marketing, HR) → employee leaf nodes.
- Each `TreeItem` gets a custom graphic (a folder icon for departments, a
  person icon for employees) passed into its constructor.
- A selection listener on `treeView.getSelectionModel().selectedItemProperty()`
  walks up the parent chain to display the full path of whatever node you
  click, e.g. `Company > Engineering > Ali Hassan`.

## VID18 — Charts (package `charts`)
Run `charts.ChartsDemo`.
- A `TabPane` with three tabs, each showing a different chart type from
  `javafx.scene.chart` (built into `javafx-controls`, no extra dependency
  needed):
  - **Bar Chart** — quarterly sales compared across two products.
  - **Pie Chart** — browser market-share breakdown.
  - **Line Chart** — a week's temperature trend.

## If you hit "JavaFX runtime components are missing"
Same fix as your other projects: right-click `pom.xml` → **Maven → Reload
project**, or run the `Launcher` class in each package instead of the
`*Demo` class directly, or use the Maven side panel's **javafx:run** goal.
