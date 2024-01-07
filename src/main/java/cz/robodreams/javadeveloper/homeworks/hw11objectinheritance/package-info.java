/**
*
 * Studentsky IS
 *
 *     Vytvořte třídu Osoba (Person), která bude sloužit jako základní třída pro všechny osoby v institutu. Tato třída by měla obsahovat následující atributy:
 *         jmeno (String) - jméno osoby
 *         vek (int) - věk osoby
 *
 *     Dále vytvořte konstruktor pro tuto třídu a metody pro získání a nastavení hodnot těchto atributů.
 *
 *     Vytvořte podtřídu Student dědící od třídy Osoba. Tato třída bude mít navíc atributy:
 *         cisloStudenta (String) - jedinečné číslo studenta
 *         obor (String) - obor studia
 *
 *     Vytvořte konstruktor pro tuto třídu a přetěžte metodu pro získání jména a věku studenta tak, aby zahrnovala i číslo studenta a obor.
 *
 *     Vytvořte třídu Zamestnanec (Employee) dědící od třídy Osoba. Tato třída bude mít navíc atributy:
 *         cisloZamestnance (String) - jedinečné číslo zaměstnance
 *         pozice (String) - pozice zaměstnance
 *
 *     Vytvořte konstruktor pro tuto třídu a přetěžte metodu pro získání jména a věku zaměstnance tak, aby zahrnovala i číslo zaměstnance a pozici.
 *
 *     Vytvořte třídu Predmet (Subject), která bude reprezentovat předmět vzdělávacího programu. Tato třída by měla obsahovat následující atributy:
 *         nazev (String) - název předmětu
 *         kodPredmetu (String) - kód předmětu
 *         ucitel (Zamestnanec) - učitel předmětu (instance třídy Zamestnanec)
 *
 *     Vytvořte konstruktor pro tuto třídu a metody pro získání a nastavení hodnot těchto atributů.
 *
 *     V třídě Predmet vytvořte metodu popisPredmetu, která bude vypisovat informace o předmětu a jeho učiteli.
 *
 *     Vytvořte třídu TestInstitut, kde v metodě main vytvoříte instance studentů, zaměstnanců a předmětů. Přetěžte a přepište metody pro získání informací o studentech a zaměstnancích tak, aby zahrnovaly všechny relevantní informace.
 *
 * Tímto způsobem vytvoříte hierarchii tříd, která zahrnuje dědičnost (mezi třídami Osoba, Student a Zamestnanec), přetěžování metod pro získání informací o lidech a přepisování metody pro popis předmětu.
 */
package cz.robodreams.javadeveloper.homeworks.hw11objectinheritance;
