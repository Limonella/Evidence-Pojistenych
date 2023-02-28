# Evidence pojištěných
* Jedná se o webovou aplikaci.
* Projekt je rozpracovaný, ale hlavní část je hotová a funkční.

### Použité technologie a koncepty:
* Java, Spring Boot, MySQL, Thymeleaf, HTML, CSS, Bootstrap, JDBC, ORM, MVC, CRUD.

### Co je hotové:
* aplikace obsahuje kompletní správu pojištěných (např. "Jan Novák") a jejich pojištění (např. "pojištění bytu"):
  * vytvoření pojištěného,
  * vytvoření pojištění,
  * zobrazení detailu pojištěného včetně jeho pojištění,
  * zobrazení detailu pojištění,
  * zobrazení seznamu pojištěných,
  * odstranění pojištěného včetně všech jeho pojištění,
  * odstranění pojištění,
  * editace pojištěného,
  * editace pojištění pojištěného
* dané entity jsou uloženy v MySQL databázi.

### Plánovaná rozšíření:
* aplikace podporuje uživatelské role (pojištěný, administrátor),
* aplikace eviduje pojistné události pojištěných, rovněž pomocí kompletní správy (CRUD),
* aplikace podporuje rozlišení pojistníků (těch, kdo platí pojištění) a pojištěných (těch, na koho se pojištění vztahuje).
